import java.util.*;

/**
 * SocialNetworkGraph represents a social network using a graph data structure.
 * It allows adding and removing people, establishing friendships, suggesting friends,
 * finding shortest paths, and counting clusters.
 */
public class SocialNetworkGraph {
    Map<String, Person> people = new HashMap<>();
    Map<Person, List<Person>> friendships = new HashMap<>();

    /**
     * Adds a new person to the social network.
     *
     * @param name    the name of the person.
     * @param age     the age of the person.
     * @param hobbies a list of the person's hobbies.
     */
    public void addPerson(String name, int age, List<String> hobbies) {
        Person person = new Person(name, age, hobbies);
        people.put(name, person);
        friendships.put(person, new ArrayList<>());
        System.out.println("Person added: " + person + " (" + person.timestamp + ")");
    }

    /**
     * Removes a person from the social network.
     *
     * @param name the name of the person to remove.
     */
    public void removePerson(String name) {
        Person personToRemove = people.get(name);
        if (personToRemove != null) {
            // Remove person from friendships
            for (List<Person> friendList : friendships.values()) {
                friendList.remove(personToRemove);
            }
            // Remove person from people map
            people.remove(name);
            System.out.println("Person removed: " + personToRemove);
        } else {
            System.out.println("Person not found in the network.");
        }
    }

    /**
     * Establishes a friendship between two people in the social network.
     *
     * @param name1 the name of the first person.
     * @param name2 the name of the second person.
     */
    public void addFriendship(String name1, String name2) {
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        if (person1 != null && person2 != null) {
            friendships.get(person1).add(person2);
            friendships.get(person2).add(person1);
            System.out.println("Friendship added between " + person1.name + " and " + person2.name);
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    /**
     * Removes a friendship between two people in the social network.
     *
     * @param name1 the name of the first person.
     * @param name2 the name of the second person.
     */
    public void removeFriendship(String name1, String name2) {
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        if (person1 != null && person2 != null) {
            friendships.get(person1).remove(person2);
            friendships.get(person2).remove(person1);
            System.out.println("Friendship removed between " + person1.name + " and " + person2.name);
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    /**
     * Finds the shortest path between two people in the social network using BFS.
     *
     * @param startName the name of the starting person.
     * @param endName   the name of the ending person.
     */
    public void findShortestPath(String startName, String endName) {
        Person start = people.get(startName);
        Person end = people.get(endName);
        if (start == null || end == null) {
            System.out.println("One or both persons not found in the network.");
            return;
        }

        Queue<Person> queue = new LinkedList<>();
        Map<Person, Person> prev = new HashMap<>();
        Set<Person> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            if (current.equals(end)) {
                printPath(start, end, prev);
                return;
            }

            for (Person neighbor : friendships.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                }
            }
        }

        System.out.println("No path found between " + startName + " and " + endName);
    }

    /**
     * Prints the shortest path from the start person to the end person.
     *
     * @param start the starting person.
     * @param end   the ending person.
     * @param prev  a map of the previous person in the path for each person.
     */
    private void printPath(Person start, Person end, Map<Person, Person> prev) {
        List<Person> path = new ArrayList<>();
        for (Person at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        System.out.println("Shortest path: " + path);
    }

    /**
     * Counts and prints the clusters of connected people in the social network.
     */
    public void countClusters() {
        Set<Person> visited = new HashSet<>();
        int clusters = 0;

        for (Person person : people.values()) {
            if (!visited.contains(person)) {
                List<Person> cluster = new ArrayList<>();
                bfs(person, visited, cluster);
                clusters++;
                System.out.println("Cluster " + clusters + ": " + cluster);
            }
        }

        System.out.println("Total clusters: " + clusters);
    }

    /**
     * Suggests friends for a person in the social network.
     *
     * @param name           the name of the person.
     * @param maxSuggestions the maximum number of friends to suggest.
     */
    public void suggestFriend(String name, int maxSuggestions) {
        Person person = people.get(name);
        if (person == null) {
            System.out.println("Person not found in the network.");
            return;
        }

        List<Person> allPeople = new ArrayList<>(people.values());
        allPeople.remove(person); // Remove the person from the list of all people

        Map<Person, Double> friendScores = new HashMap<>();
        for (Person potentialFriend : allPeople) {
            double score = calculateScore(person, potentialFriend);
            friendScores.put(potentialFriend, score);
        }

        List<Map.Entry<Person, Double>> sortedFriends = new ArrayList<>(friendScores.entrySet());
        sortedFriends.sort((a, b) -> Double.compare(b.getValue(), a.getValue())); // Sort by score in descending order

        List<Map.Entry<Person, Double>> topSuggestions = sortedFriends.subList(0, Math.min(maxSuggestions, sortedFriends.size()));

        System.out.println("Top " + maxSuggestions + " suggested friends for " + name + ":");
        for (Map.Entry<Person, Double> entry : topSuggestions) {
            Person suggestedFriend = entry.getKey();
            double score = entry.getValue();
            int mutualFriends = countMutualFriends(person, suggestedFriend);
            int commonHobbies = countCommonHobbies(person, suggestedFriend);
            System.out.println("- " + suggestedFriend.name + " (Score: " + score +
                    ", Mutual Friends: " + mutualFriends +
                    ", Common Hobbies: " + commonHobbies );
        }
    }

    /**
     * Calculates the friend suggestion score based on mutual friends and common hobbies.
     *
     * @param person the person for whom the friend is being suggested.
     * @param friend the potential friend.
     * @return the calculated score.
     */
    private double calculateScore(Person person, Person friend) {
        int mutualFriends = countMutualFriends(person, friend);
        int commonHobbies = countCommonHobbies(person, friend);
        return mutualFriends * 1 + commonHobbies * 0.5;
    }

    /**
     * Counts the number of mutual friends between two people.
     *
     * @param person1 the first person.
     * @param person2 the second person.
     * @return the count of mutual friends.
     */
    private int countMutualFriends(Person person1, Person person2) {
        List<Person> friends1 = friendships.get(person1);
        List<Person> friends2 = friendships.get(person2);
        int count = 0;
        if (friends1 != null && friends2 != null) {
            for (Person friend : friends1) {
                if (friends2.contains(friend)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Counts the number of common hobbies between two people.
     *
     * @param person1 the first person.
     * @param person2 the second person.
     * @return the count of common hobbies.
     */
    private int countCommonHobbies(Person person1, Person person2) {
        Set<String> hobbies1 = new HashSet<>(person1.hobbies);
        Set<String> hobbies2 = new HashSet<>(person2.hobbies);
        hobbies1.retainAll(hobbies2);
        return hobbies1.size();
    }

    /**
     * Performs a breadth-first search (BFS) to find all people in the cluster containing the start person.
     *
     * @param start    the starting person.
     * @param visited  a set of visited people.
     * @param cluster  the list of people in the current cluster.
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            cluster.add(current);

            for (Person neighbor : friendships.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
}
