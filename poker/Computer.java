/*
Bri Miskovitz
Class : Computer -> House player
*/

public class Computer {
    /*
     * Initalize a computer player for 2-card poker
     */


    // Initializes the house's poker hand
    private Hand hand = null;

    public void addCard(Card c){
        /*
        Adds a card to the computer's hand
        Parameters:
            c - the card to add
        */
        if (this.hand == null) {
            this.hand = new Hand();
        }

        if (hand.card1 == null) {
            this.hand.card1 = c;
        } else if (hand.card2 == null) {
            this.hand.card2 = c;
            this.hand.value = hand.calculateHand();
        }
        else {
            System.out.println("House already has 2 cards.");
        }
    }

    public void newHand(){
        /*
        Clears out all the cards for the computer
        */
        this.hand = new Hand();
    }

    public void printHand() {
        /*
        Prints the hand in some "nice" format
        */
        System.out.println(this.hand.toString());
    }

    public boolean stand(){
        /*
        Determines if the computer should stand (vs fold). Note the computer will stand if it
        has a greater than 50% chance of winning (Based on other work, a High Card hand with a
        Q and J beats 50% of the other possible hands). For the complete odds of winning see
        chance.html for tables containing the chance to win for 2-cards of the same suit, and
        2 cards of unmatched suits

        Returns:
            a boolean value specifying if the computer wants to stand
        */

        if (value() >= 20*Ranks.QUEEN.getValue() + Ranks.JACK.getValue() ){
            return true;
        } else {
            return false;
        }

    }

    public int value(){
        /*
        This function must come up with a single integer that represents the value of the hand. You want the value to work such that a higher hand has a higher value. So the values should fall from highest to lowest as:
            pair of Aces
            pair of Kings
            ...
            pair of Twos
            Ace/King flush (the same suit)
            Ace/Queen flush
            ...
            three/two flush
            Ace/King high card (not the same suit)
            Ace/Queen high card
            ...
            Three/Two high card
        Returns:
            the integer representing the value of the hand
        */

        return this.hand.calculateHand();
    }
}
