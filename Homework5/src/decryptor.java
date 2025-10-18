import java.util.Map;
import java.util.Iterator;

public class decryptor {
    private Map<Character, Map<Character, Character>> map;
    private String key;
    private String keystream = "";
    private String plain_text = "";
    private String cipher_text;

    public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
        map = _map;
        key = _key;
        cipher_text = text;
    }

    public void decrypt() {
        // do not edit this method
        generate_keystream();
        generate_plain_text();
    }

    // This method generates the keystream based on the length relationship between the cipher text and the key.
    private void generate_keystream() {
        if(cipher_text == null){
            System.out.println("Cipher text is null!");
            return;
        }
        int textLength = cipher_text.length();
        int keyLength = key.length();

        if (textLength < keyLength) { // key is shorter
            // If the key is shorter, I use only the beginning portion of the key as the keystream
            keystream = key.substring(0, textLength);
        } else if (textLength > keyLength) { // key is longerr
            // If the key is longer, I repeat the key to match the length of the cipher text
            StringBuilder sb = new StringBuilder();
            int repetitions = textLength / keyLength;
            int remainder = textLength % keyLength;
            for (int i = 0; i < repetitions; i++) {
                sb.append(key);
            }
            if (remainder > 0) { // I Append the remaining part of the key to match the length of the cipher text
                sb.append(key.substring(0, remainder));
            }
            keystream = sb.toString();
        } else { // same length
            keystream = key; // If the lengths are the same, I use the key as the keysrteam
        }
    }

    // This method generates the plaintext by decrypting the cipher text using the keystream.
    private void generate_plain_text() {
        // I iterate through in the cipher text.
        int index = 0;
        while (index < cipher_text.length()) {
            char keyCharacter = keystream.charAt(index);
            char cipherCharacter = cipher_text.charAt(index);
            Iterator<Character> keyIterator = map.get(keyCharacter).keySet().iterator();
            // I iterate through the characters mapped to the key character.
            while (keyIterator.hasNext()) {
                // If keyIterator has next I jump to the other one
                char plainCharacter = keyIterator.next();
                // I checked if the mapped value of the plain character matches the cipher character.
                if (map.get(keyCharacter).get(plainCharacter).equals(cipherCharacter)) {
                    // If a match is found, I add the plain character to the plaintext and exit the loop.
                    plain_text += plainCharacter;
                    break;
                }
            }
            index++; // I increment the index until index is the same as cipher_text length
        }
    }

    // This method returns the keystream.
    public String get_keystream() {
        return keystream;
    }


// This method returns the plain text.
    public String get_plain_text() {
        return plain_text;
    }
}
