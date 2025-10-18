import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;
// ÜÇ TANE GRAFİK AYNI ANDA AÇILDIĞINDA BAZEN ÜÇÜNCÜ GRAFİK BAŞLANGIÇTA GÖRÜNMEYEBİLİYOR HOCAM LÜTFEN TAM EKRAN YAPIP GERİ ÇIKIN GÖRÜNMEYEN GRAFİK İÇİN SONRA GÖRÜNECEKTİR.
/**
 * The Main class serves as the entry point for the program.
 * It reads commands from an input file and processes them accordingly,
 * then performs a performance analysis and visualizes the results.
 */
public class Main {
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Generates a random stock symbol of the specified size.
     * @param size The size of the symbol to generate.
     * @return The randomly generated stock symbol.
     */
    private static String generateRandomSymbol(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            char c = (char) (RANDOM.nextInt(26) + 'A'); // Generate a random uppercase letter
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Main method to run the program.
     * @param args Command-line arguments. Expects the path to the input file.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        String inputFile = args[0];
        StockDataManager manager = new StockDataManager();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, manager);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Perform a detailed performance analysis and visualize it
        visualizePerformanceAnalysis(manager, 10000); // Increased test number to 10000
    }

    /**
     * Processes a command read from the input file.
     * @param line The command to process.
     * @param manager The StockDataManager instance to use for processing the command.
     */
    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" ");
        String command = tokens[0];

        switch (command) {
            case "ADD":
                manager.addOrUpdateStock(generateRandomSymbol(4), Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                break;
            case "REMOVE":
                manager.removeStock(tokens[1]);
                break;
            case "SEARCH":
                Stock stock = manager.searchStock(tokens[1]);
                if (stock != null) {
                    System.out.println(stock);
                } else {
                    System.out.println("Stock not found: " + tokens[1]);
                }
                break;
            case "UPDATE":
                manager.updateStock(tokens[1], Double.parseDouble(tokens[3]), Long.parseLong(tokens[4]), Long.parseLong(tokens[5]));
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    /**
     * Performs a performance analysis and visualizes the results.
     * @param manager The StockDataManager instance to use for performance analysis.
     * @param maxSize The maximum size of the test data to analyze.
     */
    private static void visualizePerformanceAnalysis(StockDataManager manager, int maxSize) {
        List<Long> dataPointsYAdd = new ArrayList<>();
        List<Long> dataPointsYSearch = new ArrayList<>();
        List<Long> dataPointsYRemove = new ArrayList<>();

        for (int size = 10; size <= 1000; size += 10) { // Adjust the loop to go from 10 to 1000 with increments of 10
            manager = new StockDataManager();
            List<String> stocks = new ArrayList<>();
            long totalAddTime = 0;
            for (int i = 0; i < size; i++) {
                String symbol = generateRandomSymbol(4);
                double price = Math.random() * 100;
                long volume = (long) (Math.random() * 1000000);
                long marketCap = (long) (Math.random() * 1000000000);
                manager.addOrUpdateStock(symbol, price, volume, marketCap);
                stocks.add(symbol); // Store the generated stock symbols for later use
            }
            for (int i = 0; i < size; i++) {
                long startTime = System.nanoTime();
                String symbol = generateRandomSymbol(4);
                double price = Math.random() * 100;
                long volume = (long) (Math.random() * 1000000);
                long marketCap = (long) (Math.random() * 1000000000);
                manager.addOrUpdateStock(symbol, price, volume, marketCap);
                long endTime = System.nanoTime();
                totalAddTime += (endTime - startTime);
                manager.removeStock(symbol); // Remove the stock immediately after adding
            }
            long averageAddTime = totalAddTime / 10; // Calculate the average time after the loop
            dataPointsYAdd.add(averageAddTime);    // Store the average time directly

            // Measure time for SEARCH operation
            long startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                int index = RANDOM.nextInt(size);
                manager.searchStock(stocks.get(index));
            }
            long endTime = System.nanoTime();
            dataPointsYSearch.add((endTime - startTime)/10); // Store the elapsed time

            // Measure time for REMOVE operation
            long removeTimeTotal = 0;
            for (int i = 0; i < size; i++) {
                int index = RANDOM.nextInt(stocks.size());
                startTime = System.nanoTime();
                manager.removeStock(stocks.get(index));
                endTime = System.nanoTime();
                removeTimeTotal += (endTime - startTime);

                manager.addOrUpdateStock(stocks.get(index), Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            }
            long averageRemoveTime = removeTimeTotal / 10; // Calculate the average time after the loop
            dataPointsYRemove.add(averageRemoveTime); // Store the elapsed time
        }

        // Display the performance analysis graphs
        displayGraph("ADD Operation Performance", generateXPoints(10, 1000, 10), dataPointsYAdd);
        displayGraph("SEARCH Operation Performance", generateXPoints(10, 1000, 10), dataPointsYSearch);
        displayGraph("REMOVE Operation Performance", generateXPoints(10, 1000, 10), dataPointsYRemove);
    }

    /**
     * Generates a list of x-axis points for the graphs.
     *
     * @param start The starting value of the range.
     * @param end The ending value of the range.
     * @param step The step value for the range.
     * @return A list of integers representing the x-axis points.
     */
    private static List<Integer> generateXPoints(int start, int end, int step) {
        List<Integer> xPoints = new ArrayList<>();
        for (int i = start; i <= end; i += step) {
            xPoints.add(i);
        }
        return xPoints;
    }

    /**
     * Displays a graph with the given title and data points.
     *
     * @param title The title of the graph.
     * @param dataPointsX The x-axis data points.
     * @param dataPointsY The y-axis data points.
     */
    private static void displayGraph(String title, List<Integer> dataPointsX, List<Long> dataPointsY) {
        // Create a GUIVisualization instance and display the graph
        GUIVisualization visualization = new GUIVisualization("line", dataPointsX, dataPointsY);
        visualization.setTitle(title); // Set a unique title for each graph
        visualization.setVisible(true);
    }
}
