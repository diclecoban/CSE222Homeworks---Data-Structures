import java.util.Map;

public class encryptor {
    private Map<Character, Map<Character, Character>> map;
    private String key;
    private String keystream = "";
    private String plain_text;
    private String cipher_text = "";

    public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
        map = _map;
        key = _key;
        plain_text = text;
    }

    public void encrypt() {
        // do not edit this method
        generate_keystream();
        generate_cipher_text();
    }

    // This method generates the keystream based on the length relationship between the plain text and the key.
    private void generate_keystream() {
        if(plain_text == null){
            System.out.println("Plain text is null!");
            return;
        }
        int textLength = plain_text.length();
        int keyLength = key.length();

        if (textLength < keyLength) { // text is shorter
            keystream = key.substring(0, textLength);
        } else if (textLength > keyLength) { // text is longer
            // If the text is longer, I repeat the key to match the length of the plain text
            StringBuilder sb = new StringBuilder();
            int repetitions = textLength / keyLength;
            int remainder = textLength % keyLength;

            for (int i = 0; i < repetitions; i++) { sb.append(key);}
            if (remainder > 0) { // I Append the remaining part of the key to match the length of the plain text
                sb.append(key.substring(0, remainder));
            }

            keystream = sb.toString();
        } else { // same length
            keystream = key; // If the lengths are the same, I use the key as the keystream
        }
    }

    // This method generates the cipher text by encrypting the plain text using the keystream and the substitution map.
    private void generate_cipher_text() {
        int keystreamIndex = 0;
        for (int i = 0; i < plain_text.length(); i++) {
            char keystreamChar = keystream.charAt(keystreamIndex % keystream.length());
            char plainTextChar = plain_text.charAt(i);
            Map<Character, Character> innerMap = map.get(keystreamChar);

            // Check if the substitution map exists
            if (innerMap != null) {
                Character cipherTextChar = innerMap.get(plainTextChar);
                if (cipherTextChar != null) {
                    cipher_text += cipherTextChar;  // If it exists, I add it to the cipher text
                } else {
                    cipher_text += plainTextChar; // If not, I add the plain text character as is to the cipher text
                }
            } else {
                cipher_text += plainTextChar; // If the substitution map doesn't exist, I add the plain text character as is to the cipher text
            }
            keystreamIndex++; // I increment the index
        }
    }

    // This method returns the keystream
    public String get_keystream() {
        return keystream;
    }

    // This method returns the cipher text
    public String get_cipher_text() {
        return cipher_text;
    }
}
