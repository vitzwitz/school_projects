import java.util.*;

/**
 * Implementation of the Portfolio class.  In this simplified simulation
 * the portfolio will hold all of the investor's stocks, the investor's money,
 * be sorted in alphabetical order (by name of stock) and by decreasing value
 * (of the stock), and return a string representation
 * @author Bri Miskovitz
 *
 */
public class Portfolio {


    /* the investor's total investment */
    private int investment;

    /* Set that contains the holdings */
    private HashSet<MyStock> holdings;

    /* String representations of the portfolio (unnatural ordering) */
    private String unnaturalTable;

    /* String representations of the portfolio (natural ordering) */
    private String naturalTable;

    /**
     * Constructor.  Initializes the investor's portfolio and includes their
     * investment.
     *
     * @param initialInvestment initial investment
     */
    public Portfolio(int initialInvestment) {
        this.investment = initialInvestment;
        this.holdings = new HashSet<>();
        this.unnaturalTable = "";
        this.naturalTable = "";
    }

    /**
     * Getter.  Returns total cash available
     *
     * @return remaining investment
     */
    public int getInvestment() {
        return this.investment;
    }

    /**
     * Getter.  Returns all stocks in portfolio
     *
     * @return MyStock stocks in portfolio
     */
    public HashSet<MyStock> getHoldings() {return holdings;}


    /**
     * Finds out if the portfolio contains the stock, contains the amount
     * of shares asked, and returns stock if found; otherwise, return null
     *
     * @param stock MyStock containing info for the desired stock
     * @return MyStock stock that exists in portfolio if found; null otherwise
     */
    public MyStock findStock(MyStock stock) {
        if (this.holdings.contains(stock)) {
            Iterator<MyStock> Holdings = this.holdings.iterator();
            while (Holdings.hasNext()) {
                MyStock holding = Holdings.next();
                if (holding.equals(stock) && holding.getSharesHeld() >= stock.getSharesHeld()) {
                    return holding;
                }
            }
        }
        return null;
    }

    /**
     * Update the portfolio & investor's amount of money
     * (A)dds / (R)emoves a stock to the portfolio
     * pre-condition : holding exists in portfolio
     * @param holding MyStock of the new stock
     * @param action
     *          "A" - add stock
     *          "R" - remove stock
     */
    public void updatePortfolio(MyStock holding, String action) {

        if (action.equals("R")){
            Iterator<MyStock> Holdings = this.holdings.iterator();

            while (Holdings.hasNext()) {
                MyStock STOCK = Holdings.next();

                if (STOCK.equals(holding) && STOCK.getSharesHeld() > holding.getSharesHeld()) {
                    STOCK.updateSharesHeld(-1 * holding.getSharesHeld());
                    this.investment += holding.getTotalValue();
                    return;

                } else if (STOCK.equals(holding) && STOCK.getSharesHeld() == holding.getSharesHeld()){

                    int money = holding.getTotalValue();
                    this.holdings.remove(STOCK);
                    this.investment += money;
                    return;
                }
            }


        } else if (action.equals("A")){
            if (this.holdings.contains(holding)) {
                Iterator<MyStock> Holdings = this.holdings.iterator();
                while (Holdings.hasNext()) {
                    MyStock STOCK = Holdings.next();
                    if (STOCK.equals(holding) && STOCK.getSharesHeld() >= holding.getSharesHeld()) {
                        STOCK.updateSharesHeld(holding.getSharesHeld());
                        this.investment -= (holding.getTotalValue());
                        return;
                    }
                }
            } else {
                int money = holding.getTotalValue();
                this.holdings.add(holding);
                this.investment -= money;
            }

        }
    }


    /**
    * Prints stocks in portfolio in descending order
    * @return string of portfolio
    * */
    public String natural(){
        this.naturalTable = "";
        TreeSet<MyStock> numerical = new TreeSet<>();
        numerical.addAll(this.holdings);
        this.naturalTable += "CURRENT PORTFOLIO\n" +
                             "Cash Available: " + this.investment +
                             "\nSYMBOL | SHARES | PRICE | TOTAL VALUE\n" +
                             "====================================";
        numerical.forEach((MyStock sh) -> { naturalTable += "\n" +
                sh.getTickerSymbol() +
                "\t" + sh.getSharesHeld() +
                String.format("%7d", sh.getPricePerShare()) +
                "\t" + String.format("%7d", sh.getTotalValue()); });
        return this.naturalTable;
    }


    /**
     * Prints stocks in portfolio alphabetically
     * @return string of portfolio
    * */
    public String unnatural(){
        this.unnaturalTable = "";
        TreeSet<MyStock> alphabetical = new TreeSet<>(new MyStockComparator());
        alphabetical.addAll(this.holdings);
        this.unnaturalTable += "CURRENT PORTFOLIO\n" +
                                "Cash Available: " + this.investment +
                                "\nSYMBOL | SHARES | PRICE | TOTAL VALUE\n" +
                                "====================================";
        alphabetical.forEach((MyStock sh) -> { unnaturalTable += "\n" +
                                                sh.getTickerSymbol() +
                                                "\t" + sh.getSharesHeld() +
                                                String.format("%7d", sh.getPricePerShare()) +
                                                "\t" + String.format("%7d", sh.getTotalValue()); });
        return this.unnaturalTable;
    }


}

