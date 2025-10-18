import java.util.Comparator;
import java.util.LinkedList;

/**
 * The FileSystem class represents a file system containing directories and files.
 */
public class FileSystem {
    private Directory currentDir;
    private Directory root;
    /**
     * Constructs a new FileSystem object with a root directory.
     */
    public FileSystem(){
        this.root = new Directory("root", null);
    }

    /**
     * Gets the root directory of the file system.
     *
     * @return The root directory.
     */
    public Directory getRoot(){
        return root;
    }

    /**
     * Sets the current directory of the file system.
     *
     * @param dir The directory to set as the current directory.
     */
    public void setCurrentDirectory(Directory dir){
        this.currentDir = dir;
    }
    /**
     * Creates a new file in the specified parent directory.
     *
     * @param _name   The name of the file to create.
     * @param _parent The parent directory in which to create the file.
     */
    public void createFile(String _name, Directory _parent){
        File newFile = new File(_name, _parent);
        _parent.addElement(newFile);
    }

    /**
     * Creates a new directory in the specified parent directory.
     *
     * @param _name   The name of the directory to create.
     * @param _parent The parent directory in which to create the directory.
     */
    public void createDirectory(String _name, Directory _parent){
        Directory newDirectory = new Directory(_name,_parent);
        _parent.addElement(newDirectory);
    }
    /**
     * Deletes a file from the specified parent directory.
     *
     * @param _name   The name of the file to delete.
     * @param _parent The parent directory from which to delete the file.
     */
    public void deleteFile(String _name, Directory _parent){
        for(FileSystemElement element : _parent.children){
            if(element.getCategory().equals("F") && element.getName().equals(_name)){
                _parent.removeElement(element);
                return;
            }
        }
        System.out.println("File can't found.");
    }
    /**
     * Deletes a directory and all its contents recursively from the specified parent directory.
     *
     * @param _name   The name of the directory to delete.
     * @param _parent The parent directory from which to delete the directory.
     */
    public void deleteDirectory(String _name, Directory _parent) {
        Directory directoryToDelete = null;
        for (FileSystemElement element : _parent.children) {
            if (element.getCategory().equals("D") && element.getName().equals(_name)) {
                directoryToDelete = (Directory) element;
                break;
            }
        }
        if (directoryToDelete == null) {
            System.out.println("Directory not found.");
            return;
        }
        for (FileSystemElement element : new LinkedList<>(directoryToDelete.children)) {
            if (element.getCategory().equals("D")) {
                deleteDirectory(element.getName(), directoryToDelete);
            } else {
                deleteFile(element.getName(), directoryToDelete);
            }
        }
        _parent.removeElement(directoryToDelete);
        System.out.println(_name + " directory deleted");
    }
    /**
     * Moves a file or directory to a new parent directory.
     *
     * @param _name      The name of the file or directory to move.
     * @param newParent The new parent directory to move the file or directory to.
     */
    public void moveElement(String _name, Directory newParent) {
        FileSystemElement elementToMove = currentDir.findElementByName(_name,currentDir);

        if (elementToMove == null) {
            System.out.println("Element not found in current directory.");
            return;
        }
        if (newParent == currentDir) {
            System.out.println("The element is already in the target directory.");
            return;
        }
        currentDir.removeElement(elementToMove);
        elementToMove.setParent(newParent);
        newParent.addElement(elementToMove);
        System.out.println("File moved: " + _name + " to " + newParent.getName());
    }

    /**
     * Searches for a file or directory with the specified name in the file system.
     *
     * @param query The name of the file or directory to search for.
     * @return True if the file or directory is found, otherwise false.
     */
    public boolean search(String query) {
        System.out.println("Search query: " + query);
        System.out.println("Searching from root...");
        return searchRecursive(query, root, "");
    }

    private boolean searchRecursive(String query, Directory currentDirectory, String path) {
        for (FileSystemElement element : currentDirectory.children) {
            if (element.getName().equals(query)) {
                System.out.println("Found: " + path + "/" + query);
                return true;
            }
        }
        for (FileSystemElement element : currentDirectory.children) {
            if (element.getCategory().equals("D")) {
                Directory subDirectory = (Directory) element;
                boolean found = searchRecursive(query, subDirectory, path + "/" + subDirectory.getName());
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Prints the directory tree structure of the file system.
     */
    public void printDirectoryTree() {
        printDirectoryTreeRecursive(root , "", root == currentDir);
    }

    private void printDirectoryTreeRecursive(Directory dir, String prefix, boolean isCurrentDir) {
        // Print the current directory
        if (isCurrentDir) {
            System.out.println("* " + prefix + dir.getName() + "/ (Current Directory)");
        } else {
            System.out.println("* " + prefix + dir.getName() + "/");
        }

        // Add indentation for subdirectories
        String indent = "  " + prefix;

        // Print contents of the directory
        for (FileSystemElement element : dir.children) {
            if (element.getCategory().equals("D")) {
                Directory subDirectory = (Directory) element;
                printDirectoryTreeRecursive(subDirectory, indent, subDirectory == currentDir);
            } else {
                System.out.println(indent + element.getName());
            }
        }
    }

    /**
     * Lists the contents of the specified directory.
     *
     * @param dir The directory whose contents to list.
     */
    public void listContents(Directory dir) {
        for(FileSystemElement element: dir.children){
            System.out.println(element.getName());
        }
    }
    /**
     * Sorts the contents of the specified directory by date created.
     *
     * @param dir The directory whose contents to sort.
     */
    public void sortDirectoryByDate(Directory dir){
        dir.children.sort(Comparator.comparing(FileSystemElement::getDateCreated));
        for (FileSystemElement element : dir.children) {
            System.out.println(element.getName() + " -- " + element.getDateCreated());
        }
    }
    /**
     * Gets the current path of the specified directory.
     *
     * @param dir The directory to get the current path for.
     * @return The current path of the directory.
     */
    public String getCurrentPath(Directory dir) {
        StringBuilder path = new StringBuilder();
        FileSystemElement current = dir;
        while (current != null) {
            if (!path.toString().isEmpty()) {
                path.insert(0, "/");
            }
            path.insert(0, current.getName());
            current = current.getParent();
        }
        return path.toString();
    }

    /**
     * Gets the directory at the specified path.
     *
     * @param path The path of the directory to get.
     * @return The directory at the specified path, or null if not found.
     */
    public Directory getDirectory(String path) {

        Directory currentDirectory = root;
        if (path.equals("/")) {
            System.out.println("Directory changed to: /");
            return root;
        }
        if (path.startsWith("/")) {
            currentDirectory = root;
            path = path.substring(1);
        }
        String[] directories = path.split("/");
        for (String directory : directories) {
            if (directory.isEmpty()) {
                continue;
            }
            FileSystemElement child = currentDirectory.findElement(directory);
            if (child != null && child.getCategory().equals("D")) {
                currentDirectory = (Directory) child;
            } else {
                System.out.println("Directory " + directory + " not found.");
                return null;
            }
        }
        return currentDirectory;
    }
    /**
     * Changes the current directory to the directory at the specified path.
     *
     * @param path The path of the directory to change to.
     * @return The directory at the specified path, or null if not found.
     */
    public Directory changeDirectory(String path) {
        Directory currentDirectory = root;
        if (path.equals("/")) {
            System.out.println("Directory changed to: /");
            return root;
        }
        if (path.startsWith("/")) {
            currentDirectory = root;
            path = path.substring(1);
        }
        String[] directories = path.split("/");
        for (String directory : directories) {
            if (directory.isEmpty()) {
                continue;
            }
            FileSystemElement child = currentDirectory.findElement(directory);
            if (child != null && child.getCategory().equals("D")) {
                currentDirectory = (Directory) child;
            } else {
                System.out.println("Directory " + directory + " not found.");
                return null;
            }
        }
        System.out.println("Directory changed to: " + currentDirectory.getName());
        this.currentDir = currentDirectory;
        return currentDirectory;
    }

}
