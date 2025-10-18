import java.util.Scanner;

/**
 * This class contains the main method to run the Electronics Shop Inventory Management System.
 * It provides a menu-driven interface for users to interact with the inventory system.
 * Users can add, remove, update, list, find the cheapest, sort, calculate total value, restock, and export the inventory report.
 */
public class Main {
    /**
     * Displays a text-based menu and handles user input to perform inventory management operations.
     *
     * @param args - It takes a string argument.
     */
    public static void main(String[] args) {
        Menu();
    }

    /**
     * This class implements a simple text-based menu for managing an inventory of electronic devices.
     * Users can perform various operations such as adding, removing, updating, and listing devices, as well
     * as exporting an inventory report. The menu continuously prompts the user for input until they choose to exit.
     * The menu options are as follows:
     * <ol>
     *     <li>Add a new device</li>
     *     <li>Remove a device</li>
     *     <li>Update device details</li>
     *     <li>List all devices</li>
     *     <li>Find the cheapest device</li>
     *     <li>Sort devices by price</li>
     *     <li>Calculate total inventory value</li>
     *     <li>Restock a device</li>
     *     <li>Export inventory report</li>
     *     <li>Exit</li>
     * </ol>
     * The menu operates in a loop until the user selects the exit option (0).
     *
     * @see Inventory#addDevice()
     * @see Inventory#removeDevice(String)
     * @see Inventory#updateStock(String)
     * @see Inventory#displayDevices()
     * @see Inventory#cheapestDevice()
     * @see Inventory#sortArrayListAndDisplay()
     * @see Inventory#calculateTotalPrice()
     * @see Inventory#restockDevice(String, int, boolean)
     * @see Inventory#exportInventoryReport()
     */
    private static void Menu() {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        int option;

        do {

            System.out.println("1. Add a new device" + "\n" +
                "2. Remove a device" + "\n" +
                "3. Update device details" + "\n" +
                "4. List all devices" + "\n" +
                "5. Find the cheapest device" + "\n" +
                "6. Sort devices by price" + "\n" +
                "7. Calculate total inventory value" + "\n" +
                "8. Restock a device" + "\n" +
                "9. Export inventory report" + "\n" +
                "0. Exit");
            System.out.print("Please enter your choice: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1: // Adding the devices.
                    inventory.addDevice();
                    System.out.print("\n");
                    break;
                case 2: // Removing the device.
                    System.out.println("Please enter the name of the device:");
                    String removeName = scanner.nextLine();
                    inventory.removeDevice(removeName);
                    System.out.print("\n");
                    break;
                case 3: // Implement update device details functionality
                    System.out.println("Please enter the name of the device that you want to change:");
                    String deviceName = scanner.nextLine();
                    inventory.updateStock(deviceName);
                    System.out.print("\n");
                    break;
                case 4: // Display all the devices.
                    inventory.displayDevices();
                    System.out.print("\n");
                    break;
                case 5: // Cheapest device
                    inventory.cheapestDevice();
                    System.out.print("\n");
                    break;
                case 6: // Sorting devices
                    inventory.sortArrayListAndDisplay();
                    System.out.print("\n");
                    break;
                case 7: // Calculate the total inventory value
                    System.out.println("Total price of the inventory: $ " + inventory.calculateTotalPrice());
                    System.out.print("\n");
                    break;
                case 8: // restock a device
                    System.out.println("Enter the name of the device to restock: ");
                    String restockName = scanner.nextLine();
                    System.out.println("Do you want to add or remove stock? (Add/Remove): ");
                    String action = scanner.nextLine();

                    if (action.equals("Add")) {
                        System.out.println("Enter the quantity to add: ");
                        int newQuantity = Integer.parseInt(scanner.nextLine());
                        inventory.restockDevice(restockName, newQuantity, true);
                    } else if (action.equals("Remove")) {
                        System.out.println("Enter the quantity to remove: ");
                        int newQuantity = Integer.parseInt(scanner.nextLine());
                        inventory.restockDevice(restockName, newQuantity, false);
                    } else {
                        System.out.println("Invalid input.");
                    }
                    System.out.print("\n");
                    break;
                case 9: // export inventory report
                    inventory.exportInventoryReport();
                    System.out.print("\n");
                    break;
                case 0: // Exit the code
                    System.out.println("Exiting the program...");
                    return;
                default: // Invalid
                    System.out.println("Invalid option.");
                    break;
            }
        } while (option != 0);

        scanner.close();
    }
}