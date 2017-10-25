import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Implementation of the Brokerage class.  In this simplified simulation
 * the brokerage will manage a single client's investments.  It will
 * also track the movement of the market as a whole.
 *
 * @author atd: Aaron T Deever
 * @author Bri Miskovitz
 *
 */
public class Brokerage {

    /* Map containing stocks available and their current price per share.
     */
    private Map<String, Integer> market =
            new HashMap<>();

    /* Portfolio containing investor's stocks */
    private Portfolio portfolio;

    /**
     * Constructor.  Initializes the investor and the market as a whole.
     * In this simplified simulation there is just a single investor and the
     * whole market is tracked by the brokerage.
     * @param initialInvestment initial investment
     */
    public Brokerage(int initialInvestment) {

        /* initialize the market */
        market.put("GOOG", 1183);
        market.put("AMZN", 360);
        market.put("AAPL", 532);
        market.put("YHOO", 38);
        market.put("MSFT", 40);
        market.put("EBAY", 57);

        /* initialize investor's portfolio */
        this.portfolio = new Portfolio(initialInvestment);
    }


    /**
     * Add to Investor's holding.  This function should error-check to 
     * ensure the ticker symbol exists, the number of shares requested
     * is a positive value, and that the client has sufficient funds.
     * @param tickerSymbol the particular stock to buy
     * @param shares the number of shares requested
     * @return true if transaction is completed.  False otherwise.
     */
    public boolean increaseHolding(String tickerSymbol, int shares) {

        if (this.market.containsKey(tickerSymbol)) {
            if (shares > 0) {
                int numShares = shares;
                while (numShares > 0){
                    if (this.portfolio.getInvestment() >= this.market.get(tickerSymbol)*numShares){
                        this.portfolio.updatePortfolio(new MyStock(tickerSymbol, this.market.get(tickerSymbol), numShares), "A");
                         tickerUpdate();
                        return true;
                    }
                    numShares--;
                }
            }
        }
        return false;
    }

    /**
     * Reduce Investor's holding.  This function should error-check to 
     * ensure the ticker symbol exists, and the number of shares to reduce
     * is a positive value no greater than the number currently held.
     * @param tickerSymbol the particular stock to sell
     * @param shares the number of shares to sell
     * @return true if transaction is completed.  False otherwise.
     */
    public boolean reduceHolding(String tickerSymbol, int shares) {

        if (shares > 0) {
            MyStock stock = this.portfolio.findStock(new MyStock(tickerSymbol, this.market.get(tickerSymbol), shares));
            if (stock != null){
                this.portfolio.updatePortfolio(new MyStock(tickerSymbol, this.market.get(tickerSymbol), shares), "R");
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a string to represent the investor's portfolio.  Can be
     * requested in alphabetical order, or in decreasing order of the
     * value of the holdings (shares * price per share).
     * @param choice "N" for by name, "V" for by value
     * @return String representing the portfolio.  This string must
     * include the name, number of shares, price per share, and total 
     * value for each stock in the portfolio.  The entries must be
     * sorted according to the input request.
     * pre-conditions : choice is either "N" or "V"
     */
    public String accessPortfolio(String choice) {

        if (choice.equals("N")){
            return this.portfolio.unnatural();
        } else {
            return this.portfolio.natural();
        }
    }

    /**
     * Update the price per share of each stock using a random value to
     * determine the change.  A multiplier is applied to the stock price and
     * the result is rounded to the nearest integer.  A minimum price of $1 is
     * required. (For the given inputs, this constraint will always hold
     * without checking). This method can also be used to update the price of
     * a stock inside any stock object that contains that information.
     * @return A string "ticker" that indicates
     *         the ticker symbols and their prices.
     */
    public String tickerUpdate() {

        String output = "";
        for(String str : market.keySet()) {
            int currVal = market.get(str);
            int num = (int)(Math.random() * 5);
            int newVal;
            switch(num) {
                case 0:
                    newVal = (int)(currVal * .9 + 0.5);
                    break;
                case 1:
                    newVal = (int)(currVal * .95 + 0.5);
                    break;
                case 2:
                    newVal = currVal;
                    break;
                case 3:
                    newVal = (int)(currVal * 1.1 + 0.5);
                    break;
                case 4:
                default:
                    newVal = (int)(currVal * 1.2 + 0.5);
                    break;
            }
            if (newVal < 1){
                newVal *= 1.2;
            }
            market.put(str,  newVal);

            /* Update stock if it exists in portfolio in the portfolio to the market prices */
            if ( this.portfolio.getHoldings().size() != 0 && this.portfolio.findStock(new MyStock(str, newVal, 0)) != null){
                this.portfolio.findStock(new MyStock(str, newVal, 0)).updatePrice(newVal);
            }


            output += str + " " + newVal + "      ";
        }

        return output;
    }

    /**
     * Sell all remaining stocks in the portfolio.
     * @return the cash value of the portfolio.
     */
    public int closeAccount() {

        Iterator<MyStock> holdings = this.portfolio.getHoldings().iterator();
        while (holdings.hasNext()){
            MyStock stock = holdings.next();
            this.portfolio.updatePortfolio(stock, "R");
            holdings = this.portfolio.getHoldings().iterator();

        }
        return this.portfolio.getInvestment();
    }
}