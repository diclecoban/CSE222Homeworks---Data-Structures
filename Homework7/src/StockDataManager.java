/**
 * A class to manage stocks using an AVL tree data structure.
 * Provides methods to add or update stocks, remove stocks, search for stocks, and update stock details.
 */
public class StockDataManager {
    /**
     * AVL tree to store and manage stocks.
     */
    private AVLTree avlTree;
    /**
     * Constructs a StockDataManager object with an empty AVL tree.
     */
    public StockDataManager() {
        avlTree = new AVLTree();
    }

    /**
     * Adds or updates a stock with the specified details.
     * If a stock with the given symbol already exists, its details are updated.
     * If not, a new stock is created and inserted into the AVL tree.
     *
     * @param symbol    The symbol of the stock.
     * @param price     The price of the stock.
     * @param volume    The volume of the stock.
     * @param marketCap The market capitalization of the stock.
     */
    public void addOrUpdateStock(String symbol, double price, long volume, long marketCap) {
        Stock existingStock = avlTree.search(symbol);
        if (existingStock != null) {
            existingStock.setPrice(price);
            existingStock.setVolume(volume);
            existingStock.setMarketCap(marketCap);
        } else {
            Stock newStock = new Stock(symbol, price, volume, marketCap);
            avlTree.insert(newStock);
        }
    }


    /**
     * Removes a stock with the specified symbol from the AVL tree.
     *
     * @param symbol The symbol of the stock to remove.
     */
    public void removeStock(String symbol) {
        avlTree.delete(symbol);
    }

    /**
     * Searches for a stock with the specified symbol in the AVL tree.
     *
     * @param symbol The symbol of the stock to search for.
     * @return The Stock object if found, or null if not found.
     */
    public Stock searchStock(String symbol) {
        return avlTree.search(symbol);
    }

    /**
     * Updates the details of a stock with the specified symbol.
     * If the stock is found, its price, volume, and market capitalization are updated.
     *
     * @param symbol       The symbol of the stock to update.
     * @param newPrice     The new price of the stock.
     * @param newVolume    The new volume of the stock.
     * @param newMarketCap The new market capitalization of the stock.
     */

    public void updateStock(String symbol, double newPrice, long newVolume, long newMarketCap) {
        Stock stock = avlTree.search(symbol);
        if (stock != null) {
            stock.setPrice(newPrice);
            stock.setVolume(newVolume);
            stock.setMarketCap(newMarketCap);
        }
    }

    /**
     * Main method for testing the StockDataManager class.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        StockDataManager manager = new StockDataManager();
        manager.addOrUpdateStock("AAPL", 150.0, 1000000, 2500000000L);
        manager.addOrUpdateStock("GOOGL", 2800.0, 500000, 1500000000L);
        System.out.println(manager.searchStock("AAPL"));
        manager.removeStock("AAPL");
        System.out.println(manager.searchStock("AAPL"));
    }
}
