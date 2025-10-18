public class preprocessor {
    private String initial_string;
    private String preprocessed_string;

    public preprocessor(String str) {
        this.initial_string = str;
    }

    public void preprocess() {
        // do not edit this method
        capitalize();
        clean();
    }

    // This method capitalizes all letters in the initial string.
    private void capitalize() {
        preprocessed_string = initial_string.toUpperCase();
    }

    // This method removes all non-alphabetic characters from the preprocessed string.
    private void clean() {
        preprocessed_string = preprocessed_string.replaceAll("[^a-zA-Z]", "");
    }

    public String get_preprocessed_string() {
        return preprocessed_string;
    }
}