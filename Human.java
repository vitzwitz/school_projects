/*
Bri Miskovitz
Class : Human -> Human player
*/


import java.util.Scanner;
public class Human {
    /*
    A human player for 2-card poker.
    */

    // Scanner that communicates with Human
    private Scanner in;

    // Initializes human's poker hand
    private Hand hand = null;



    public Human(Scanner in){
        /*
        Initialize a human player for 2-card poker
        */
        this.in = in;
    }


    public void addCard(Card c){
        /*
        * Adds a card to the human's hand
        * Parameters:
        *       c - the card to add
        */

        if (this.hand == null) {
            this.hand = new Hand();
        }
        if (this.hand.card1 == null) {
            this.hand.card1 = c;

        } else if (this.hand.card2 == null) {
            this.hand.card2 = c;
            hand.value = hand.calculateHand();
        }
        else {
            System.out.println("You already has 2 cards.");
        }
        }


    public int compareTo(Computer computer){
        /*
        Compares the humans hand with the specified computers hand for order. Returns a:
            negative integer  :  player hand < computers hand
            zero              :  player hand == computers hand
            positive integer  :  player hand > computers hand
        Parameters:
            computer - the computer player
        Returns:
            a negative integer, zero, or a positive integer as the human is less than, equal to,
            or greater than the computer
        * */
        return value() - computer.value();
    }

    public void newHand(){
        /*
        Clears out all the cards for the human
        */
        this.hand = new Hand();
    }

    public void printHand() {
        /*
        Prints the hand in some "nice" format from function in hand class
        */
        System.out.println(this.hand.toString());
    }

    public boolean stand() {
        /*
        Asks the player if they want to stand. You should prompt the player with a suitable message,
        and then read the player's response from standard input. The response should be either "y" (stand)
        or "n" (fold).
        Returns:
            a boolean value specifying if the human wants to stand
        */
        System.out.println("Would you like to stand (y/n)?");
        String ans = this.in.nextLine();
        return ans.equals("y");

    }

    public int value(){
        /*
        This function must come up with a single integer that represents the value of the hand. You want the value to
        work such that a higher hand has a higher value. So the values should fall from highest to lowest as:
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
        - Calls function from hand class

        Returns:
            the integer representing the value of the hand
        */
        return this.hand.calculateHand();
    }
}
