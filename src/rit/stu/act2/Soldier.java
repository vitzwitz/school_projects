package rit.stu.act2;

/*
Author : Bri Miskovitz
*/

public class Soldier implements Player {
    /* A class to represent a soldier, each whom has a unique ID. */

    /* the id of this soldier */
    private int id;

    public Soldier(int id) {
        /*
        Create a new soldier.
         */
        this.id = id;
    }

    public void victory(Player player){
    /*
     If the soldier is triumphant over player, it displays the message,
      "{soldier} yells, 'Sieg über {player}!'".

     Specified by:
        victory in interface Player
     Parameters:
        player - the player that I defeated
     */
        System.out.println(this.toString() + " yells, 'Sieg über "+ player.toString() +"!'");
    }

    public void defeat(Player player){
    /*
    If the soldier losses to player, it displays the message,
     "{soldier} cries, 'Besiegt von {player}!'".

    Specified by:
        defeat in interface Player
    Parameters:
        player - the player that defeated me
    */
        System.out.println(this.toString() + " cries, 'Besiegt von " + player.toString() + "!'");
    }


    @Override
    public String toString() {
        /*
         The string representation of a soldier is:
         "Soldier #n", where n is their id.

         Overrides:
            toString in class Object
         Returns:
            the soldier string
         */
        return "Soldier #" + id;
    }
}