/**
 * This class represents a Computer (Laptop) device.
 * It implements the {@link Device} interface, providing methods to access and manipulate Laptop properties.
 */
public class Laptop implements Device {
    /**
     * Category of the laptop.
     */
    private String category;
    /**
     * Name of the laptop.
     */
    private String name;
    /**
     * Price of the laptop.
     */
    private double price;
    /**
     * Quantity of the laptop.
     */
    private int quantity;

    /**
     * Time complexity: O(1)
     *
     * @param category - category of the laptop.
     * @param name     - name of the laptop.
     * @param price    - price of the laptop.
     * @param quantity - quantity of the laptop
     */
    public Laptop(String category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Time complexity: O(1)
     *
     * @return category of the device.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Time complexity: O(1)
     *
     * @param category - Category of the laptop.
     */
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Time complexity: O(1)
     *
     * @return name of the device.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Time complexity: O(1)
     *
     * @param name - Name of the laptop.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Time complexity: O(1)
     *
     * @return price of the device,
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Time complexity: O(1)
     *
     * @param price - Price of the laptop.
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Time complexity: O(1)
     *
     * @return quantity of the price.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Time complexity: O(1)
     *
     * @param quantity - Quantity of the laptop.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
