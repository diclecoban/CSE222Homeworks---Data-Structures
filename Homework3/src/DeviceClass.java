/**
 * This class represents a generic device.
 * It implements the {@link Device} interface, providing methods to access and manipulate device properties.
 */
public class DeviceClass implements Device {
    /**
     * Category of the device.
     */
    private String category;
    /**
     * Name of the device.
     */
    private String name;
    /**
     * Price of the device.
     */
    private double price;
    /**
     * Quantity of the device.
     */
    private int quantity;

    /**
     * Time complexity: O(1)
     *
     * @param category - category of the device.
     * @param name     - name of the device.
     * @param price    - price of the device.
     * @param quantity - quantity of the device.
     */
    public DeviceClass(String category, String name, double price, int quantity) {
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
     * O(1)
     *
     * @param category - Category of the device.
     */
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * O(1)
     *
     * @return name of the device.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * O(1)
     *
     * @param name - Name of the device.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * O(1)
     *
     * @return price of the device,
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * O(1)
     *
     * @param price - Price of the device.
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * O(1)
     *
     * @return quantity of the price.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * O(1)
     *
     * @param quantity - Quantity of the device.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
