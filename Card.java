public class Card {

    /*
    A class to represent a playing card with a rank and suit
    */

    private Ranks rank;
    private Suits suit;

    public Card(Ranks rank, Suits suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int value(){

        /*
        * Return the numerical value of the card
        * Returns:
        *     the value of the rank of the card
        */
        return rank.getValue();
    }

    public Ranks getRank(){
        /*
        * accessor for the rank
        * Returns:
        *   the Rank of the card
        */

        return rank;
    }

    public Suits getSuit(){
        /*
        * accessor for the suit
        * Returns:
        *   the Suit of the card
        */

        return suit;
    }


    public String toString() {
        /*
        *  Returns a long name for the card, ie "THREE of CLUBS"
        *  Overrides:
        *       toString in class Object
        *  Returns:
        *       the long name of the card
        */
        return rank + " of " + suit;
    }

    public String getShortName(){
        /*
        * Returns a short, three char, name for the card, ie " 3C", "10S" or " QH"
        * Returns:
        *       the short name of the card
        */
        return rank.getShortName() + suit.getShortName();
    }
}