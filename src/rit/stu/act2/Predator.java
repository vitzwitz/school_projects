package rit.stu.act2;


/*
    Author : Bri Miskovitz
    A class to represent the predator.
    */
public class Predator implements Player {


    /* The chance the predator defeats a hostage. */
    public static final int CHANCE_TO_BEAT_HOSTAGES = 75;

    /* The chance the predator defeats a soldier. */
    public static final int CHANCE_TO_BEAT_SOLDIER = 50;

    public Predator(){
        /*
         Empty constructor
         */
    }

    public void victory(Player player){
        /*
        If the predator wins, they display the message, "The predator yells
         out in triumphant victory over {player}!".

        Specified by:
            victory in interface Player
        Parameters:
            player - the player that loss to the predator
         */
        System.out.println("The " + this.toString() +" yells out in triumphant victory over "+ player.toString() +"!");

    }
    public void defeat(Player player){
        /*
         If the predator loses, the display the message, "The predator cries
         out in glorious defeat to {player}!".

         Specified by:
            defeat in interface Player
        Parameters:
            player - the player that defeated the predator
         */
        System.out.println("The " + this.toString() +" cries out in glorious defeat to " + player.toString() +"!");
    }

    @Override
    public String toString() {
        /*
        The string representation of the predator is simply, "Predator".

        Overrides:
            toString in class Object

        Returns:
            the predator string
         */
        return "Predator";
    }
}
