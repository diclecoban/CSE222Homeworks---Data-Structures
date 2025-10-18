import java.sql.Timestamp;

/**
 * The FileSystemElement class represents a generic file system element, such as a file or directory.
 * It provides common attributes and methods shared by both files and directories.
 */
public abstract class FileSystemElement {
    /** The name of the file system element. */
    protected String name;

    /** The timestamp indicating the creation date of the file system element. */
    protected Timestamp dateCreated;

    /** The parent directory of the file system element. */
    protected FileSystemElement parent;

    /**
     * Constructor to initialize a file system element with a name and parent directory.
     *
     * @param _name   The name of the file system element.
     * @param _parent The parent directory of the file system element.
     */
    public FileSystemElement(String _name, FileSystemElement _parent){
        name = _name;
        parent = _parent;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Retrieves the name of the file system element.
     *
     * @return The name of the file system element.
     */
    public String getName(){
      return name;
    }
    /**
     * Retrieves the parent directory of the file system element.
     *
     * @return The parent directory of the file system element.
     */
    public FileSystemElement getParent(){
        return parent;
    }

    /**
     * Retrieves the creation timestamp of the file system element.
     *
     * @return The creation timestamp of the file system element.
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    /**
     * Sets the name of the file system element.
     *
     * @param newName The new name for the file system element.
     */
    public void setName(String newName){
        name = newName;
    }
    /**
     * Sets the parent directory of the file system element.
     *
     * @param newParent The new parent directory for the file system element.
     */
    public void setParent(FileSystemElement newParent){
        parent = newParent;
    }
    /**
     * Abstract method to be implemented by subclasses to retrieve the category of the file system element.
     *
     * @return The category of the file system element (e.g., "F" for file, "D" for directory).
     */
    public abstract String getCategory();
}
