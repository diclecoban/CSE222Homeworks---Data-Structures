import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A menu class for interacting with a social network graph.
 */
public class Menu {
    private SocialNetworkGraph network;
    private Scanner scanner;

    /**
     * Constructs a new Menu object.
     */
    public Menu() {
        network = new SocialNetworkGraph();
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the menu and handles user input.
     */
    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n----- Social Network Menu -----");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add Friendship");
            System.out.println("4. Remove Friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friend");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    removePerson();
                    break;
                case 3:
                    addFriendship();
                    break;
                case 4:
                    removeFriendship();
                    break;
                case 5:
                    findShortestPath();
                    break;
                case 6:
                    suggestFriend();
                    break;
                case 7:
                    countClusters();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        } while (choice != 8);
    }

    /**
     * Adds a person to the social network.
     */
    private void addPerson() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter hobbies (comma-separated): ");
        String hobbiesInput = scanner.nextLine();
        List<String> hobbies = Arrays.asList(hobbiesInput.split(","));
        network.addPerson(name, age, hobbies);
    }

    /**
     * Removes a person from the social network.
     */
    private void removePerson() {
        System.out.print("Enter name of person to remove: ");
        String name = scanner.nextLine();
        network.removePerson(name);
    }

    /**
     * Adds a friendship between two persons in the social network.
     */
    private void addFriendship() {
        System.out.print("Enter name of first person: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter name of second person: ");
        String name2 = scanner.nextLine();
        network.addFriendship(name1, name2);
    }

    /**
     * Removes a friendship between two persons in the social network.
     */
    private void removeFriendship() {
        System.out.print("Enter name of first person: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter name of second person: ");
        String name2 = scanner.nextLine();
        network.removeFriendship(name1, name2);
    }

    /**
     * Finds the shortest path between two persons in the social network.
     */
    private void findShortestPath() {
        System.out.print("Enter name of start person: ");
        String startName = scanner.nextLine();
        System.out.print("Enter name of end person: ");
        String endName = scanner.nextLine();
        network.findShortestPath(startName, endName);
    }

    /**
     * Suggests a friend for a person in the social network.
     */
    private void suggestFriend() {
        System.out.print("Enter name of person: ");
        String name = scanner.nextLine();
        System.out.print("Enter maximum number of suggestions: ");
        int maxSuggestions = scanner.nextInt();
        scanner.nextLine();

        network.suggestFriend(name, maxSuggestions);
    }

    /**
     * Counts the clusters in the social network.
     */
    private void countClusters() {
        network.countClusters();
    }
}

