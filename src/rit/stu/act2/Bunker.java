package rit.stu.act2;

import rit.cs.Queue;
import rit.cs.QueueList;
import rit.stu.act1.QueueNode;

/*
Author : Bri Miskovitz
A class that represents the bunker of soldiers. */
public class Bunker {

    /* the queue of soldiers in the bunker */
    private Queue<Soldier> bunker;

    /* the number of soldiers currently in the bunker */
    private int numSoldiers;

    public Bunker(int numSoldiers){
        /*
        Create the bunker. Based on the total number of soldiers,
         each one should be created here and then added to the
         bunker, with id's ranging from 1-numSoldiers.
        */

        this.bunker = new QueueNode<>();
        for (int i = 0; i<numSoldiers; i++){
            Soldier soldier = new Soldier(i+1);
            fortifySoldiers(soldier);
        }
    }

    public boolean hasSoldiers(){
        /*
        Are their soldiers left in the bunker?

        Returns:
            whether the bunker has soldiers or not
        */
        if (this.numSoldiers > 0){
            return true;
        } else if (this.numSoldiers == 0){
            return false;
        } else {
            System.out.println("Cannot have negative Soldiers.");
            return false;
        }
    }

    public Soldier deployNextSoldier() {
        /*
        Remove the next soldier from the front of the bunker to be deployed on a rescue attempt.

        Returns:
            the soldier at the front of the bunker
        Precondition
            the bunker is not empty
        */
//        assert hasSoldiers();
        this.numSoldiers -= 1;
        return bunker.dequeue();
    }

    public int getNumSoldiers(){
        /*
        Get how many soldiers are in the bunker.

        Returns:
            number of soldiers in the bunker
        */
        return this.numSoldiers;
    }

    public void fortifySoldiers(Soldier soldier){
        /*
        Add a new soldier to the end of the bunker.

        Parameters:
            soldier - the new soldier to add
        */
        this.numSoldiers += 1;
        bunker.enqueue(soldier);
    }
}
