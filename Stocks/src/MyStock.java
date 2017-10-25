import java.util.*;


/**
 * A class that represents a stock.
 * @author Bri Miskovitz (modified)
 */
public class MyStock implements Comparable<MyStock> {

    /** the stock name */
    private String tickerSymbol;

    /** the price per share */
    private int pricePerShare;

    /** total number of shares held */
    private int sharesHeld;

    /**
     * Create a new MyStock object
     *
     * @param tickerSymbol stock name
     * @param pricePerShare price per share
     * @param sharesHeld total shares held
     */
    public MyStock(String tickerSymbol, int pricePerShare, int sharesHeld) {
        this.tickerSymbol = tickerSymbol;
        this.pricePerShare = pricePerShare;
        this.sharesHeld = sharesHeld;
    }

    /**
     * Get the stock name
     * @return stock name
     */
    public String getTickerSymbol() {
        return tickerSymbol;
    }

    /**
     * Gets the number of shares of the stock
     * @return the number of shares
     */
    public int getSharesHeld() { return sharesHeld; }

    /**
     * Gets price of stock
     * @return price of stock
     */
    public int getPricePerShare() {
        return pricePerShare;
    }


    /**
    * Update number of shares of the stock
    * @param numShares change in number of shares
    *   Positive - Increase the number of shares held
    *   Negative - Decrease the number of shares held
    *             (Checks if there are enough shares to remove)
    * @return boolean for success of update in share holds
    * */
    public boolean updateSharesHeld(int numShares) {
        if (numShares < 0){
            if ( this.sharesHeld < Math.abs(numShares) ){
                return false;
            } else {
                this.sharesHeld += numShares;
                return true;
            }
        } else {
            this.sharesHeld += numShares;
            return true;
        }
    }

    /**
     * Updates the price of the stock
     * @param priceChange integer that represents the change in price of the stock
     * pre-condition : Price change has already been checked when market is updated,
     *                    so it is no greater than the current price of the stock
     *
     * */
    public void updatePrice(int priceChange) {
       this.pricePerShare = priceChange;
    }


    /**
     * Returns total value of stock
     * @return integer of the total value of the stock
     * */
    public int getTotalValue(){
        return this.pricePerShare*this.sharesHeld;
    }

    @Override
    public String toString() {
        return "MyStock{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", pricePerShare=" + pricePerShare +
                ", sharesHeld=" + sharesHeld +
                '}';
    }

    /**
     * Two MyStock objects are equal if they have the same name
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof MyStock) {
            if (((MyStock) o).tickerSymbol.equals(this.tickerSymbol)) {
                return true;
            }
        }
        return false;
    }


    /**
     * The hash code of a MyStock object is the hash code of the name
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return this.tickerSymbol.hashCode();
    }

    /**
     * The natural order comparison of MyStock object is that a MyStock
     * object with a higher price per share comes before one with a
     * lower.  If two MyStock objects have the same price, the tiebreaker
     * is alphabetical by name.
     *
     * @param o the other MyStock object to compare against
     * @return less than 0 if this object price is higher than the others,
     * 0 if they are equal, and greater than 0 if the other objects price
     * is higher than this objects
     */
    @Override
    public int compareTo(MyStock o) {
        int price = 0;
        if (!this.equals(o)){
            price = o.getTotalValue() - this.getTotalValue();
            if (price == 0){
                price = this.tickerSymbol.compareTo(o.getTickerSymbol());

            }
        }
        return price;
    }
}


/**
 * A class that overrides the natural order comparison of MyStock objects
 * and compares them alphabetically by name.
 */
class MyStockComparator implements Comparator<MyStock> {
    @Override


/**
 * Compares two stocks based on the names of the stocks
 * @param o1 MyStock stock to compare
 * @param o2 MyStock stock to compare
 * @return com
 * */
    public int compare(MyStock o1, MyStock o2) {
        // notice the need to use accessors here

        return o1.getTickerSymbol().compareTo(o2.getTickerSymbol());
    }
}
