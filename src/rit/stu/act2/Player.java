package rit.stu.act2;
/*
Author: Bri Miskovitz
*/


/* An interface to represent a player in the game. */
public interface Player {
    /*
     An interface to represent a player in the game. A player is any
     entity that can come into conflict with another player, where battle
     yields one victor and one loser.
      */

    /*
    What to do when this player defeats another player.

    Parameters:
        player - the player that I defeated
    */
    public void victory(Player player);


    /*
    What to do when this player loses to another player.

    Parameters:
        player - the player that defeated me
    */
    public void defeat(Player player);
}
