/*
*   Class : Hand -> Poker hand
*   Author : Bri Miskovitz
*   Computer Science II
*   9 / 7 / 17
*/

public class Hand {
    /*
    Represents a poker hand
    */

    // First card : card object
    public Card card1 = null;

    // Second card : card object
    public Card card2 = null;

    // value of hand : int
    public int value = 0;


    public Hand(){
        /*
        Constructor: The two cards in the hand as Card objects and the value of
                        the hand
        Return:
            Hand(card1, card2, value)
        */
        this.card1 = card1;
        this.card2 = card2;
        this.value = value;
    }

    public int calculateHand(){
        /*
        *   Calculates the 2-card hand using an algorithm:
        *       High Card: Higher Card*20 + Lower Card
        *       Flush: Higher Card*20 + Lower Card + 300
        *       Pair: Higher Card*20 + Lower Card + 600
        *
        *   Return (int) :
        *       Value of hand
        *   Post-condition:
        *       If hand has same rank and suit, should return 0
        */
        int result = 0;
        if ((this.card1.getRank() == this.card2.getRank()) && (this.card1.getSuit().equals(this.card2.getSuit()))){
            // impossible combo
            result = 0;
        }
        else if (this.card1.getRank() == this.card2.getRank()){
            // pair
            result = (this.card1.getRank().getValue()*20 + this.card2.getRank().getValue()) + 600;
        }
        else if (this.card1.getSuit().equals(this.card2.getSuit())){
            // flush
            if (this.card1.getRank().getValue() > this.card2.getRank().getValue()) {
                result = (14 * this.card1.getRank().getValue() + this.card2.getRank().getValue()) + 300;
            }
            else if (this.card1.getRank().getValue() < this.card2.getRank().getValue()){
                result = (this.card1.getRank().getValue() + 20 * this.card2.getRank().getValue()) + 300;
            }
        }
        else {
            // high card
            if (this.card1.getRank().getValue() > this.card2.getRank().getValue()) {
                result = (20 * this.card1.getRank().getValue() + this.card2.getRank().getValue());
            }
            else if (this.card1.getRank().getValue() < this.card2.getRank().getValue()){
                result = (this.card1.getRank().getValue() + 20 * this.card2.getRank().getValue());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        /*
         Converts object into string and into pretty format
        */
        return "       --------\n" +
        " -----| "+ card1.getShortName() + "    |\n"
        + "| "+ card2.getShortName() +" |        |\n"
        + "Total: " + this.value;
    }





}
