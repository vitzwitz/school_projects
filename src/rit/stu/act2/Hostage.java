package rit.stu.act2;

/*
Author: Bri Miskovitz
A class to represent a hostage, each whom has a unique ID. */
public class Hostage implements Player{

    /* the id of this hostage */
    private int id;

    public Hostage(int id) {
        /*
         Create a new hostage.

        Parameters:
            id - the id of the hostage
         */
        this.id = id;
    }

    public void victory(Player player){
        /*
         If the hostage is triumphant over player, it displays the message,
          "{hostage} yells, 'Victory over {player}!'".

         Specified by:
            victory in interface Player
        Parameters:
            player - the player that I defeated
         */
        System.out.println(this.toString() + " yells, 'Victory over " + player.toString() + "!'");
    }

    public void defeat(Player player){
        /*
         If the hostage losses to player, it displays the message,
          "{hostage} cries, 'Defeated by {player}!'".
         Specified by:
            defeat in interface Player
         Parameters:
            player - the player that defeated me
         */
        System.out.println(this.toString() + " cries, 'Defeated by "+ player.toString() +"!'");
    }

    @Override
    public String toString() {
        /*
         The string representation of a hostage is: "Hostage #n", where n is their id.

         Overrides:
            toString in class Object
         Returns:
            the hostage string
         */
        return "Hostage #"+ id;
    }
}
