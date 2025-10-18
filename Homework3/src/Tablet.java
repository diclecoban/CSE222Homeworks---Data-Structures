/**
 * This class represents a Tablet device.
 * It implements the {@link Device} interface, providing methods to access and manipulate Tablet properties.
 */
public class Tablet implements Device {
    /**
     * Category of the tablet.
     */
    private String category;
    /**
     * Name of the tablet.
     */
    private String name;
    /**
     * Price of the tablet.
     */
    private double price;
    /**
     * Quantity of the tablet.
     */
    private int quantity;

    /**
     * Time complexity: O(1)
     *
     * @param category - Category of the tablet.
     * @param name     -  Name of the tablet.
     * @param price    - Price of the tablet.
     * @param quantity - Quantity of the tablet.
     */
    public Tablet(String category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Time complexity: O(1)
     *
     * @return category of the tablet.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Time complexity: O(1)
     *
     * @param category - Category of the tablet.
     */
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Time complexity: O(1)
     *
     * @return name of the tablet.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Time complexity: O(1)
     *
     * @param name - Name of the tablet.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Time complexity: O(1)
     *
     * @return price of the tablet,
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Time complexity: O(1)
     *
     * @param price - price of the tablet.
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Time complexity: O(1)
     *
     * @return quantity of the tablet.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Time complexity: O(1)
     *
     * @param quantity - Quantity of the tablet.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
