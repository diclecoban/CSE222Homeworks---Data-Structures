import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class represents an inventory for storing electronic devices.
 * It manages the addition, removal, update, listing, sorting, calculation of total value,
 * restocking, and exporting of inventory reports.
 */
public class Inventory {

    Scanner scanner = new Scanner(System.in);

    /**
     * Formats a double value to a string with two decimal places.
     * Time complexity: O(1)
     * <p>
     *
     * @param value - the double value to be formatted,
     * @return the formatted string
     */
    private String format(double value) {
        return String.format("%.2f", value);
    }

    /**
     * Linked list containing array lists of devices.
     * Each node of the linked list holds an array list containing devices of a specific category.
     */
    private LinkedList<ArrayList<Device>> linkedList;

    /**
     * Constructs a new Inventory object with an empty linked list.
     * The linked list will be used to store array lists of devices,
     * with each node representing a different category of devices.
     */
    public Inventory() {
        linkedList = new LinkedList<>();
    }

    /**
     * Adds a new device to the inventory.
     * Time complexity: O(1).
     */
    public void addDevice() {
        System.out.println("Enter device details:");
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        Device device;
        switch (category) {
            case "TV":
                device = new TV(category, name, price, quantity);
                break;
            case "Console":
                device = new Console(category, name, price, quantity);
                break;
            case "Laptop":
                device = new Laptop(category, name, price, quantity);
                break;
            case "SmartPhone":
                device = new SmartPhone(category, name, price, quantity);
                break;
            case "Tablet":
                device = new Tablet(category, name, price, quantity);
                break;
            default:
                System.out.println("Invalid category!");
                return;
        }
        linkedList.add(new ArrayList<>());
        linkedList.getLast().add(device);
        System.out.println("Device added successfully!");
    }

    /**
     * Removes the device with the specified name from the inventory.
     * If a device with the given name is found, it is removed from the inventory.
     * Only the first occurrence of the device with the specified name is removed.
     *
     * @param deviceName the name of the device to be removed
     *                   Time complexity: O(m*n) = O(5n) = O(n), where 'n' is the total number of devices
     *                   and 'm' is the maximum number of device categories (which is 5 in this case).
     */
    public void removeDevice(String deviceName) {
        for (ArrayList<Device> nodeList : linkedList) {
            for (Device device : nodeList) {
                if (device.getName().equals(deviceName)) {
                    nodeList.remove(device);
                    return;
                }
            }
        }
    }

    /**
     * Updates the stock details (price and quantity) of a device with the specified name in the inventory.
     * If a device with the given name is found, the user is prompted to enter new price and quantity values.
     * The device's price and quantity are updated accordingly.
     * If the user leaves the input blank for price or quantity, the current values are retained.
     *
     * @param deviceName - the name of the device to be updated
     *                   Time complexity: O(m*n) = O(5n) = O(n), where 'n' is the total number of devices
     *                   and 'm' is the maximum number of device categories (which is 5 in this case).
     */
    public void updateStock(String deviceName) {
        boolean deviceFound = false;
        for (ArrayList<Device> nodeList : linkedList) {
            for (Device device : nodeList) {
                if (device.getName().equals(deviceName)) {
                    System.out.print("Enter new price (leave blank to keep current price): ");
                    String priceInput = scanner.nextLine();
                    double newPrice = (priceInput.isEmpty()) ? device.getPrice() : Double.parseDouble(priceInput);
                    System.out.print("Enter new quantity (leave blank to keep current quantity): ");
                    String quantityInput = scanner.nextLine();
                    int newQuantity = (quantityInput.isEmpty()) ? device.getQuantity() : Integer.parseInt(quantityInput);
                    device.setPrice(newPrice);
                    device.setQuantity(newQuantity);
                    deviceFound = true;
                    System.out.println(deviceName + " details updated: Price - $" + newPrice + ", Quantity - " + newQuantity);
                    break;
                }
            }
            if (deviceFound) {
                break;
            }
        }
        if (!deviceFound) {
            System.out.println("Device not found.");
        }
    }

    /**
     * Displays the details of all devices in the inventory.
     * It iterates through the array lists containing devices of different categories
     * and prints the details of each device, including category, name, price, and quantity.
     * Time complexity: O(m*n) = O(5n) = O(n), where 'n' is the total number of devices
     * and 'm' is the maximum number of device categories (which is 5 in this case).
     */
    public void displayDevices() {
        // It iterates the arrayLists and display them individually.
        for (ArrayList<Device> nodeList : linkedList) {
            for (Device device : nodeList) {
                System.out.println("Category: " + device.getCategory() +
                    ", Name: " + device.getName() +
                    ", Price: $" + device.getPrice() +
                    ", Quantity: " + device.getQuantity());
            }
        }
    }

    /**
     * Finds and displays the details of the cheapest device in the inventory.
     * It iterates through the array lists containing devices of different categories
     * and compares the price of each device to find the cheapest one.
     * If the inventory is empty, it prints a message indicating that there are no devices in the inventory.
     * Time complexity: O(m*n) = O(5n) = O(n), where 'n' is the total number of devices
     * and 'm' is the maximum number of device categories (which is 5 in this case).
     *
     * @see Device
     */
    public void cheapestDevice() {
        if (linkedList.isEmpty()) {
            System.out.println("No devices in inventory.");
            return;
        }
        Device cheapestDevice = linkedList.getFirst().get(0);
        for (ArrayList<Device> arrayList : linkedList) {
            for (Device device : arrayList) {
                if (device.getPrice() < cheapestDevice.getPrice()) {
                    cheapestDevice = device;
                }
            }
        }
        System.out.println("Cheapest device:");
        System.out.println("Category: " + cheapestDevice.getCategory());
        System.out.println("Name: " + cheapestDevice.getName());
        System.out.println("Price: $" + cheapestDevice.getPrice());
        System.out.println("Quantity: " + cheapestDevice.getQuantity());
    }

    /**
     * Sorts the devices within each ArrayList in the linked list by their prices in ascending order
     * using Java's built-in sorting algorithm and displays the sorted devices.
     * Time complexity: O(nlogn), where 'n' is the total number of devices in all ArrayLists.
     * The sorting algorithm used is Timsort, which has an average time complexity of O(nlogn).
     */
    public void sortArrayListAndDisplay() {
        ArrayList<Device> sortedDevices = new ArrayList<>();

        for (ArrayList<Device> arrayList : linkedList) {
            sortedDevices.addAll(arrayList);
        }
        sortedDevices.sort(Comparator.comparingDouble(Device::getPrice));

        System.out.println("Sorted devices in the current list:");
        for (Device device : sortedDevices) {
            System.out.println("Category: " + device.getCategory() +
                ", Name: " + device.getName() +
                ", Price: $" + device.getPrice() +
                ", Quantity: " + device.getQuantity());
        }
    }

    /**
     * Calculates the total price of all devices in the inventory by summing the product of each device's price
     * and quantity.
     * Time complexity: O(n), where 'n' is the total number of devices in all ArrayLists.
     * This method iterates through all devices in the inventory once to calculate the total price.
     *
     * @return The total price of all devices in the inventory.
     */
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (ArrayList<Device> arrayList : linkedList) {
            for (Device device : arrayList) {
                totalPrice += device.getPrice() * device.getQuantity();
            }
        }
        return totalPrice;
    }

    /**
     * Restocks or removes stock for a device in the inventory based on the specified parameters.
     * Time complexity: O(n), where 'n' is the total number of devices in all ArrayLists.
     * This method iterates through all devices in the inventory to find the device with the specified name.
     *
     * @param deviceName  The name of the device to restock or remove.
     * @param newQuantity The quantity to add or remove from the device's stock.
     * @param action      A boolean value indicating whether to add (true) or remove (false) the specified quantity.
     */
    public void restockDevice(String deviceName, int newQuantity, boolean action) {
        for (ArrayList<Device> arrayList : linkedList) {
            for (Device device : arrayList) {
                if (device.getName().equals(deviceName)) {
                    if (action) { //Add
                        device.setQuantity(device.getQuantity() + newQuantity);
                        System.out.println(device.getName() + " restocked." + "New quantity is: " + device.getQuantity());
                    } else { // Remove
                        device.setQuantity(device.getQuantity() - newQuantity);
                        System.out.println(device.getName() + " restocked." + "New quantity is: " + device.getQuantity());
                    }
                }
            }
        }
    }

    /**
     * It writes the informations about the devices into the file.
     * Time complexity: O(m*n) = O(5n) = O(n) m can be maximum 5. The code has fixed Device number.
     */
    public void exportInventoryReport() {
        try {
            String filename = "ExportStock.txt";
            FileWriter writer = new FileWriter(filename);

            writer.write("Dicle Sara's Electro World\n");
            writer.write("Generated on: " + java.time.LocalDate.now() + "\n");
            writer.write("---------------------------------------\n");
            writer.write("| No. | Category  | Name                  | Price  | Quantity |\n");
            writer.write("---------------------------------------\n");
            int count = 1;
            double totalValue = 0.0;
            for (ArrayList<Device> nodeList : linkedList) {
                for (Device device : nodeList) {
                    writer.write(String.format("| %-3d | %-10s | %-20s | $%-5s | %-8d |\n",
                        count, device.getCategory(), device.getName(), device.getPrice(), device.getQuantity()));
                    totalValue += device.getPrice() * device.getQuantity();
                    count++;
                }
            }
            writer.write("---------------------------------------\n");
            writer.write("Summary:\n");
            writer.write("- Total Number of Devices: " + (count - 1) + "\n");
            writer.write("- Total Inventory Value: $" + this.calculateTotalPrice() + "\n");

            writer.close();
            System.out.println("Exporting is successful.");
        } catch (Exception e) {
            System.out.println("Error.");
        }
    }
}
