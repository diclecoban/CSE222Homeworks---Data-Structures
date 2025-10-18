import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;
public class alphabet {
    private Set<Character> english_alphabet = new LinkedHashSet<Character>();
    private Map<Character, Map<Character, Character>> map = new HashMap<Character,  Map<Character, Character>>();

    public alphabet() {
        // do not edit this method
        fill_english_alphabet();
        fill_map();
    }

    private void fill_english_alphabet() {
        // do not edit this method
        for(char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            english_alphabet.add(c);
        }
    }


    // This method fills a map with a mapping of each character in the English alphabet to another character,
    // where each character is mapped to the following characters in alphabetical order.
    private void fill_map() {
        Iterator<Character> iterator = english_alphabet.iterator();
        while (iterator.hasNext()) { // Basically It is an inner while loops
            char char1 = iterator.next();
            Map<Character, Character> char2 = new HashMap<>();
            char current = char1;
            Iterator<Character> alphabetIterator = english_alphabet.iterator();
            while (alphabetIterator.hasNext()) {
                char alphabetLetter = alphabetIterator.next();
                char2.put(alphabetLetter, current);
                current++;
                if (current > 'Z') { // If the current character exceeds 'Z', I reset it to 'A'
                    current = 'A';
                }
            }
            map.put(char1, char2); // I add the substitution mappings for the current character to the main map
        }
    }

    public void print_map() {
        // do not edit this method
        System.out.println("*** Viegenere Cipher ***\n\n");
        System.out.println("    " + english_alphabet);
        System.out.print("    ------------------------------------------------------------------------------");
        for(Character k: map.keySet()) {
            System.out.print("\n" + k + " | ");
            System.out.print(map.get(k).values());
        }
        System.out.println("\n");

    }

    public Map get_map() {
        return this.map;
    }
}