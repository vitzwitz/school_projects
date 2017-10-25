package rit.stu.act2;


/* A class to represent a guerilla, each whom has a unique ID.
* Author: Bri Miskovitz
*/
public class Guerilla implements Player {

    /* the id of this guerilla */
    private int id;

    /* The chance the guerilla has to defeat a soldier. */
    public static final int CHANCE_TO_BEAT_SOLDIER = 20;

    public Guerilla(int id) {
        /*
        Create a new guerilla.
         */
        this.id = id;
    }

    public void victory(Player player){
        /*
        If t"{guerilla} yells, 'Victoria sobre {player}!'".
        Specified by:
            victory in interface Player
        the guerilla is triumphant over player, it displays the message,
            Parameters:
            player - the player that I defeated
         */
        System.out.println(this.toString() + " yells, 'Victoria sobre" + player.toString() + "!'");
    }

    public void defeat(Player player){
        /*
         If the guerilla losses to player, it displays the message,
          "{guerilla} cries, 'Derrotado por {player}!'".

         Specified by:
            defeat in interface Player
         Parameters:
            player - the player that defeated me
         */
        System.out.println(this.toString() + " cries, 'Derrotado por " + player.toString() + "'!");
    }


    @Override
    public String toString() {
        /*
         The string representation of a guerilla is:
            "Guerilla #n", where n is their id.

         Overrides:
            toString in class Object
         Returns:
            the guerilla string
         */
        return "Guerilla #"  + id;
    }
}