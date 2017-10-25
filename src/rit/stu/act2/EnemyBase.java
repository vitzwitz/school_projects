package rit.stu.act2;

import rit.cs.Queue;
import rit.cs.QueueList;
import rit.cs.Stack;
import rit.cs.StackList;
import rit.stu.act1.QueueNode;
import rit.stu.act1.StackNode;

/*
Author: Bri Miskovitz
This class represents the enemy base.
*/
public class EnemyBase {
    /*
    It contains a guard line of guerillas that are guarding a group of hostages
    stored in a narrow cave with only an entrance and now way for the hostages
    to reorder themselves.
     */

    /* the cave of hostages is a stack */
    private Stack<Hostage> hostages;

    /* the guard line of guerillas is a queue */
    private Queue<Guerilla> guerillas;

    /* how many hostages are currently being detained in the cave
 */
    private int numHostages;

    /* the number of guerillas in the guard line */
    private int numGuerillas;

    public EnemyBase(int numHostages, int numGuerillas) {
        /*
         Create the enemy base with a number of hostages,
         1-numHostages, pushed into the cave in order,
         and a number of guerillas, 1-numGuerillas,
          added to the guard line in order.
         */
        this.hostages = new StackNode<>();
        this.guerillas = new QueueNode<>();

        for (int i=0; i<numHostages; i++){
            Hostage hostage = new Hostage(i+1);
            addHostage(hostage);
        }
        for (int i=0; i<numGuerillas; i++){
            Guerilla guerilla = new Guerilla(i+1);
            addGuerilla(guerilla);
        }
    }

    public void addGuerilla(Guerilla guerilla) {
        /*
            Add a guerilla to the end of the guard line.
            Parameters:
                guerilla - guerilla to add
         */
        this.guerillas.enqueue(guerilla);
        this.numGuerillas++;
    }
    public void addHostage(Hostage hostage) {
        /*
            Add a hostage to the front of the cave.
            Parameters:
                hostage - hostage to add
         */
        this.hostages.push(hostage);
        this.numHostages++;
    }

    public Hostage getHostage() {
        /*
            /*
         Remove a hostage from the head of the cave.
         Returns:
              the hostage at the head of the cave
         Precondition
              the cave is not empty
         */
        this.numHostages--;
        return this.hostages.pop();
    }

    public Guerilla getGuerilla() {
        /*
         Remove a guerilla from the front of the guard line.
         Returns:
            the front guerilla
         Precondition
            the guerilla line is not empty
         */
        this.numGuerillas--;
        return this.guerillas.dequeue();
    }

    public Hostage rescueHostage(Soldier soldier){
        /*
        A soldier enters the enemy base and attempts to rescue a hostage.
        First the must defeat the guerilla at the front of the guard line,
        then they can rescue one hostage at the front of the cave.
        Follow these steps:
            1. Print the message "{soldier} enters enemy base...".
            2. Remove and hold onto the hostage at the front of the cave.
            3. If there are no guerillas left in the base, return the hostage to the caller.
            4. Otherwise get the next guerilla from the guard line.
            5. Have the guerilla roll the die, 1-100, and print the message,
               "{soldier} battles {guerilla} who rolls a #'.
            6. If the die roll is greater than the chance to defeat the soldier,
               the soldier declares victory over the guerilla and the guerilla declares
               defeat to the soldier. The front hostage is then returned from the method.
            7. Otherwise the guerilla declares victory over the soldier and the soldier
               declares defeat to the guerilla. The hostage is pushed back onto the head
               of cave and the guerilla is added to the end of the guard line.
               No hostage is returned, e.g. null.
        Parameters:
            soldier - the rescuing solder.
        Returns:
            if a hostage was rescued the hostage, otherwise if the soldier failed, null

         */
        System.out.println(soldier.toString() + " enters enemy base...");
        Hostage hostage = getHostage();
        if (numGuerillas == 0){
            return hostage;
        } else {
            Guerilla guerilla = getGuerilla();
            int roll = Battlefield.nextInt(1,100);
            System.out.println(soldier.toString() + " battles " + guerilla.toString() + " who rolls a " + roll);
            if (roll > guerilla.CHANCE_TO_BEAT_SOLDIER){
                soldier.victory(guerilla);
                guerilla.defeat(soldier);
                return hostage;
            } else {
                guerilla.victory(soldier);
                soldier.defeat(guerilla);
                addHostage(hostage);
                addGuerilla(guerilla);
                return null;
            }
        }
    }


    public int getNumHostages() {
        /*
         Get the number of hostages in the cave.
         Returns:
            number of hostages
         */
        return this.numHostages;
    }

    public int getNumGuerillas() {
        /*
         Get the number of guerillas in the guard line.
         Returns:
            number of guerillas
         */
        return this.numGuerillas;
    }
}