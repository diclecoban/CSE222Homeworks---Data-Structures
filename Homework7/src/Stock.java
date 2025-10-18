/**
 * Represents a stock with its symbol, price, volume, and market capitalization.
 */
public class Stock {
    private String getSymbol;
    private double price;
    private long volume;
    private long marketCap;

    /**
     * Constructs a new Stock object with the specified attributes.
     *
     * @param symbol    The symbol of the stock.
     * @param price     The price of the stock.
     * @param volume    The volume of the stock.
     * @param marketCap The market capitalization of the stock.
     */
    public Stock(String symbol, double price, long volume, long marketCap) {
        this.getSymbol = symbol;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
    }

    /**
     * Gets the symbol of the stock.
     *
     * @return The symbol of the stock.
     */
    public String getSymbol() {
        return getSymbol;
    }

    /**
     * Sets the symbol of the stock.
     *
     * @param symbol The symbol of the stock.
     */
    public void setSymbol(String symbol) {
        this.getSymbol = symbol;
    }

    /**
     * Gets the price of the stock.
     *
     * @return The price of the stock.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the stock.
     *
     * @param price The price of the stock.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the volume of the stock.
     *
     * @return The volume of the stock.
     */
    public long getVolume() {
        return volume;
    }

    /**
     * Sets the volume of the stock.
     *
     * @param volume The volume of the stock.
     */
    public void setVolume(long volume) {
        this.volume = volume;
    }

    /**
     * Gets the market capitalization of the stock.
     *
     * @return The market capitalization of the stock.
     */
    public long getMarketCap() {
        return marketCap;
    }

    /**
     * Sets the market capitalization of the stock.
     *
     * @param marketCap The market capitalization of the stock.
     */
    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    /**
     * Returns a string representation of the Stock object.
     *
     * @return A string representation of the Stock object.
     */
    @Override
    public String toString() {
        return "Stock [symbol=" + getSymbol + ", price=" + price + ", volume=" + volume + ", marketCap=" + marketCap + "]";
    }
}
