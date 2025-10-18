/**
 * This class represents a Console device.
 * It implements the {@link Device} interface, providing methods to access and manipulate Console properties.
 */
public class Console implements Device {
    /**
     * Category of the console.
     */
    private String category;
    /**
     * Name of the console.
     */
    private String name;
    /**
     * Price of the console.
     */
    private double price;
    /**
     * Quantity of the console.
     */
    private int quantity;

    /**
     * Time complexity: O(1)
     *
     * @param category - category of the console.
     * @param name     - name of the console.
     * @param price    - price of the console.
     * @param quantity - quantity of the console.
     */
    public Console(String category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Time complexity: O(1)
     *
     * @return category of the console.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Time complexity: O(1)
     *
     * @param category - Category of the console.
     */
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Time complexity: O(1)
     *
     * @return name of the console.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Time complexity: O(1)
     *
     * @param name - Name of the console.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Time complexity: O(1)
     *
     * @return price of the console,
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Time complexity: O(1)
     *
     * @param price - price of the console.
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Time complexity: O(1)
     *
     * @return quantity of the console.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Time complexity: O(1)
     *
     * @param quantity - Quantity of the console.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
