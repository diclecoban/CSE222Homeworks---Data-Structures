/**
 * This class represents a Smartphone device.
 * It implements the {@link Device} interface, providing methods to access and manipulate Smartphone properties.
 */
public class SmartPhone implements Device {
    /**
     * Category of the smartphone.
     */
    private String category;
    /**
     * Name of the smartphone.
     */
    private String name;
    /**
     * Price of the smartphone.
     */
    private double price;
    /**
     * Quantity of the smartphone.
     */
    private int quantity;

    /**
     * Time complexity: O(1)
     *
     * @param category - category of the smartphone.
     * @param name     - name of the smartphone.
     * @param price    - price of the smartphone.
     * @param quantity - quantity of the smartphone
     */
    public SmartPhone(String category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Time complexity: O(1)
     *
     * @return category of the smartphone.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Time complexity: O(1)
     *
     * @param category - Category of the smartphone.
     */
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Time complexity: O(1)
     *
     * @return name of the smartphone.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Time complexity: O(1)
     *
     * @param name - Name of the smartphone.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Time complexity: O(1)
     *
     * @return price of the smartphone,
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Time complexity: O(1)
     *
     * @param price - price of the smartphone.
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Time complexity: O(1)
     *
     * @return quantity of the smartphone.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Time complexity: O(1)
     *
     * @param quantity - Quantity of the smartphone.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
