import java.util.*;

/**
 * Represents a person in a social network.
 */
public class Person {
    /**
     * The name of the person.
     */
    String name;
    /**
     * The age of the person.
     */
    int age;
    /**
     * The list of hobbies of the person.
     */
    List<String> hobbies;
    /**
     * The timestamp when the person was added to the network.
     */
    Date timestamp;

    /**
    * Constructs a new Person object with the specified name, age, and hobbies.
    *
    * @param name    the name of the person
    * @param age     the age of the person
    * @param hobbies the list of hobbies of the person
    */
    public Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
        this.timestamp = new Date();
    }

    /**
     * Returns a string representation of the person.
     *
     * @return a string representation of the person
     */
    @Override
    public String toString() {
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ")";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Objects.equals(name, person.name);
    }

    /**
     * Returns a hash code value for the person.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
