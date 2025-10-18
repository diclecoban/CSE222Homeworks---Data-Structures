/**
 * The File class represents a file in the file system.
 * It extends the FileSystemElement class.
 */
public class File extends FileSystemElement{
    /**
     * Constructs a new File object with the specified name and parent.
     *
     * @param _name   The name of the file.
     * @param _parent The parent directory of the file.
     */
    public File(String _name, FileSystemElement _parent) {
        super(_name,_parent);
    }
    /**
     * Gets the category of the file.
     *
     * @return The category of the file, which is "F" indicating it's a file.
     */
    @Override
    public String getCategory(){
        return "F";
    }
}
