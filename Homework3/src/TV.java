/**
 * This class represents a television (TV) device.
 * It implements the {@link Device} interface, providing methods to access and manipulate TV properties.
 */
public class TV implements Device {
    /**
     * Category of the TV.
     */
    private String category;
    /**
     * Name of the TV.
     */
    private String name;
    /**
     * Price of the TV.
     */
    private double price;
    /**
     * Quantity of the TV.
     */
    private int quantity;

    /**
     * Time complexity: O(1)
     *
     * @param category - Category of the TV.
     * @param name     -  Name of the TV.
     * @param price    - Price of the TV.
     * @param quantity - Quantity of the TV.
     */
    public TV(String category, String name, double price, int quantity) {
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
     * @param category - Category of the TV.
     */
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Time complexity: O(1)
     *
     * @return name of the TV.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Time complexity: O(1)
     *
     * @param name - Name of the TV.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Time complexity: O(1)
     *
     * @return price of the TV,
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Time complexity: O(1)
     *
     * @param price - price of the TV.
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Time complexity: O(1)
     *
     * @return quantity of the TV.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Time complexity: O(1)
     *
     * @param quantity - Quantity of the TV.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
