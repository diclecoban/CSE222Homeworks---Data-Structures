import java.util.Scanner;

/**
 * The Main class serves as the entry point for the file system management application.
 * It provides a user interface through the console to interact with the file system.
 */
public class Main {
    /** The file system instance for managing files and directories. */
    private static FileSystem fs = new FileSystem();
    /** The scanner object for reading user input from the console. */
    private static Scanner scanner = new Scanner(System.in);
    /** The current directory being operated on in the file system. */
    private static Directory currentDirectory;

    /**
     * The main method, entry point of the application.
     * It displays the menu options and handles user input.
     *
     * @param args The command-line arguments (unused).
     */
    public static void main(String[] args) {
        currentDirectory = fs.getRoot();

        while(true){
            System.out.println("Current Directory: " + fs.getCurrentPath(currentDirectory));
            System.out.println("***** File System Management Menu ****");
            System.out.println("1. Change Directory");
            System.out.println("2. List Directory Contents");
            System.out.println("3. Create File");
            System.out.println("4. Create Directory");
            System.out.println("5. Delete File");
            System.out.println("6. Delete Directory");
            System.out.println("7. Move File/Directory");
            System.out.println("8. Search File/Directory");
            System.out.println("9. Print Directory Tree");
            System.out.println("10. Sort Contents by Date");
            System.out.println("11. Exit");
            System.out.println("Please Select an Option");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    changeDirectory();
                    break;
                case 2:
                    listContents();
                    break;
                case 3:
                    createFile();
                    break;
                case 4:
                    createDirectory();
                    break;
                case 5:
                    deleteFile();
                    break;
                case 6:
                    deleteDirectory();
                    break;
                case 7:
                    moveElement();
                    break;
                case 8:
                    search();
                    break;
                case 9:
                    printDirectoryTree();
                    break;
                case 10:
                    sortDirectoryByDate();
                    break;
                case 11:
                    System.out.println("Exitting...");
                    return;
                default:
                    System.out.println("You enter an invalid option.");
                    return;
            }
        }
    }

    /**
     * Changes the current directory based on user input.
     */
    private static void changeDirectory(){
        System.out.println("Please enter the path you want to go:");
        String path = scanner.nextLine();
        currentDirectory = fs.changeDirectory(path);
    }

    /**
     * Lists the contents of the current directory.
     */
    private static void listContents(){
        fs.listContents(currentDirectory);
    }
    /**
     * Creates a new file in the current directory based on user input.
     */
    private static void createFile(){
        System.out.println("Enter file name to create");
        String name = scanner.nextLine();
        fs.createFile(name,currentDirectory);
        System.out.println(name + " file created");
    }
    /**
     * Creates a new directory in the current directory based on user input.
     */
    private static void createDirectory(){
        System.out.println("Enter directory name to create: ");
        String name = scanner.nextLine();
        fs.createDirectory(name,currentDirectory);
        System.out.println(name + " directory created");
    }
    /**
     * Deletes a file in the current directory based on user input.
     */
    private static void deleteFile(){
        System.out.println("Enter file name to delete");
        String name = scanner.nextLine();
        fs.deleteFile(name, currentDirectory);
        System.out.println(name + " file deleted");
    }
    /**
     * Deletes a directory in the current directory based on user input.
     */
    private static void deleteDirectory(){
        System.out.println("Enter directory name to delete");
        String name = scanner.nextLine();
        fs.deleteDirectory(name, currentDirectory);
    }
    /**
     * Moves a file or directory to a new location based on user input.
     */
    private static void moveElement() {
        System.out.println("Enter the name of file/directory to move: ");
        String name = scanner.nextLine();
        System.out.println("Enter new directory path: ");
        String newPath = scanner.nextLine();
        Directory newParent = fs.getDirectory(newPath);
        if (newParent == null) {
            System.out.println("Invalid directory path.");
            return;
        }
        fs.moveElement(name, newParent);
    }

    /**
     * Searches for a file or directory based on user input.
     */
    private static void search(){
        System.out.println("Enter search query");
        String query = scanner.nextLine();
        boolean found = fs.search(query);
        System.out.println("Search result: " + (found ? "Found" : "Not found"));
    }
    /**
     * Prints the directory tree structure of the file system.
     */
    private static void printDirectoryTree(){
        fs.printDirectoryTree();
    }
    /**
     * Sorts the contents of the current directory by date.
     */
    private static void sortDirectoryByDate(){
        fs.sortDirectoryByDate(currentDirectory);
    }
}