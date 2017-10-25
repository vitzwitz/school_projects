package rit.stu.act2;
import rit.cs.Stack;
import rit.stu.act1.StackNode;

public class Chopper {
    /*
    A class that represent the chopper. The chopper can hold up
     to 6 passengers aligned in a single columns of seats. There
     is only one door to the chopper that is accessible by the
     passengers. When they enter the chopper, the occupy the seat
     closest to the door and any existing passengers move one seat
     down.

    In order to preserve fuel, the chopper will only fly the
     passengers away to safety if the chopper is full, or it is
     the last group of people to rescue.
    */

    /* The maximum number of passengers the chopper can hold */
    public static final int MAX_OCCUPANCY = 6;

    /* The passengers are stored in a stack */
    private Stack<Player> chopper;

    /* the number of passengers occupying seats in the chopper */
    private int numPassengers;

    /* the total number of passengers that have been flown away to
        safety and rescued */
    private int numRescued;

    /* Whether the chopper is empty or not */
    private boolean empty;

    /* Whether or not the chopper has reached max occupancy or not */
    private boolean full;

    public Chopper() {
        /*
        Create the chopper so that all the passenger seats are
         empty and none have been rescued yet.
        */
        this.chopper = new StackNode<>();
        this.numPassengers = 0;
        this.numRescued = 0;

        this.empty = true;
        this.full = this.numPassengers == MAX_OCCUPANCY;
    }

    public boolean isEmpty(){
        /*
        Is the chopper empty?

        Returns:
            Whether the chopper is empty or not
        */
        this.full = this.numPassengers == MAX_OCCUPANCY;
        this.empty = this.numPassengers == 0;
        return this.empty;
    }

    public boolean isFull(){
        /*
        Is the chopper full?

        Returns:
            Whether or not the chopper has reached max occupancy or not
        */
        this.full = this.numPassengers == MAX_OCCUPANCY;
        this.empty = this.numPassengers == 0;
        return this.full;
    }

    public int getNumRescued(){
        /*
        Get the total number of passengers that were rescued.

        Returns:
            number of rescued passengers
        */
        return this.numRescued;
    }

    public void rescuePassengers(){
        /*
        When the chopper is full, or it is the last group of people
         to be rescued, it will fly away and rescue the passengers.
         Each passenger exits the chopper in the reverse order they
         entered it, e.g. the last passenger to enter exits first.

        As each passenger exits the chopper, print the message
         "Chopper transported {passenger} to safety!"
        */
        int temp = this.numPassengers;
        for (int i = 0; i < temp; i++){
            Player passenger = this.chopper.pop();
            this.numRescued++;
            this.numPassengers--;
            this.full = false;
            System.out.println("Chopper transported " + passenger.toString() + " to safety!");
        }
        this.empty = true;
    }

    public void boardPassengers(Player player){
        /*
        Board a passenger onto the chopper. If the chopper is full, it must
         first fly away and rescue the passengers on it (hint, use the
         rescuePassengers() helper method). Otherwise, the passenger boards
         the chopper and occupies the front seat, making everyone else in
         the chopper move down a seat. When the passenger boards the chopper
         display the message "{passenger} boards the chopper!".

        Parameters:
            player - the player boarding the chopper
        */

        if (isFull()){
            rescuePassengers();
        }
        this.chopper.push(player);
        this.numPassengers++;
        this.empty = false;
        System.out.println(player.toString() + " boards the chopper!");
    }

}
