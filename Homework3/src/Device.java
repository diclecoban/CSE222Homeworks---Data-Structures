/**
 * This interface represents a generic electronic device.
 * It defines methods to get and set the category, name, price, and quantity of the device.
 */
public interface Device {

    /**
     * Time complexity: O(1)
     *
     * @return category of the device.
     */
    String getCategory();

    /**
     * Time complexity: O(1)
     *
     * @return name of the device.
     */
    String getName();

    /**
     * Time complexity: O(1)
     *
     * @return price of the device,
     */
    double getPrice();

    /**
     * Time complexity: O(1)
     *
     * @return quantity of the price.
     */
    int getQuantity();

    /**
     * Time complexity: O(1)
     *
     * @param category - Category of the device.
     */
    void setCategory(String category);

    /**
     * Time complexity: O(1)
     *
     * @param name - Name of the device.
     */
    void setName(String name);

    /**
     * Time complexity: O(1)
     *
     * @param price - Price of the device.
     */
    void setPrice(double price);

    /**
     * Time complexity: O(1)
     *
     * @param quantity - Quantity of the device.
     */
    void setQuantity(int quantity);
}
