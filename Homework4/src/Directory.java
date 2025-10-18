import java.util.LinkedList;

/**
 * The Directory class represents a directory in the file system.
 * It extends the FileSystemElement class.
 */
public class Directory extends FileSystemElement{
    /** A list to store the children (files or subdirectories) of this directory. */
    LinkedList<FileSystemElement> children;

    /**
     * Constructs a new Directory object with the specified name and parent.
     *
     * @param _name   The name of the directory.
     * @param _parent The parent directory of the directory.
     */
    public Directory(String _name, FileSystemElement _parent){
        super(_name,_parent);
        children = new LinkedList<>();

    }
    /**
     * Adds a new element (file or subdirectory) to this directory.
     *
     * @param newElement The new element to be added.
     */
    public void addElement(FileSystemElement newElement){
        children.add(newElement);
    }
    /**
     * Removes an element (file or subdirectory) from this directory.
     *
     * @param element The element to be removed.
     */
    public void removeElement(FileSystemElement element){
        children.remove(element);
    }
    /**
     * Gets the category of the directory.
     *
     * @return The category of the directory, which is "D" indicating it's a directory.
     */
    @Override
    public String getCategory(){
        return "D";
    }


    /**
     * Finds and returns the element with the specified name within this directory.
     *
     * @param _name The name of the element to find.
     * @return The element with the specified name, or null if not found.
     */
    public FileSystemElement findElement(String _name){
        for(FileSystemElement element : children){
            if(element.getName().equals(_name)){
                return element;
            }
        }
        System.out.println("Element couldn't found.");
        return null;
    }
    /**
     * Recursively finds and returns the element with the specified name within this directory and its subdirectories.
     *
     * @param name      The name of the element to find.
     * @param directory The directory to search within.
     * @return The element with the specified name, or null if not found.
     */
    public FileSystemElement findElementByName(String name, Directory directory) {
        if (directory.getName().equals(name)) {
            return directory;
        }
        if (directory instanceof Directory) {
            Directory dir = (Directory) directory;
            for (FileSystemElement element : children) {
                if (element.getName().equals(name)) {
                    return element;
                }
                if (element instanceof Directory) {
                    FileSystemElement foundElement = findElementByName(name, (Directory) element);
                    if (foundElement != null) {
                        return foundElement;
                    }
                }
            }
        }

        return null;
    }

}
