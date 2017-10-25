
/*
 * Author : Bri Miskovitz
 * Class : Circuit extends MultiComponent
 */



/*
 Concrete class representing an electrical circuit. A Circuit is a MultiComponent.
  Must have another Circuit as its source, or else be the root circuit with no source.
  Has a list of children that can be either additional (sub)-Circuits, or else Receptacles.
  A Circuit can not have an Appliance directly connected to it (the connection must be
  through a Receptacle). Circuits are granted an unlimited number of children, but have a
  maximum total current capacity allowed before the Circuit is overloaded and the circuit
  breaker flips, resetting this Circuit (and all its children).
 */
public class Circuit extends MultiComponent {


    /* maximum capacity for this circuit (amps) before it is overloaded. */
    private int maxCapacity;


    /*
    Constructor for a Circuit. Any type of source Component is allowed when
    creating the Circuit object, but only a source that is a Circuit will allow
    this Circuit to be added successfully to the system (as a child of the source).

    Parameters:
        name - name given to this circuit
        source - source component
        maxCapacity - maximum capacity available for this circuit
     */
    public Circuit(String name, Component source, int maxCapacity) {
        super(name, source);
        this.maxCapacity = maxCapacity;
    }


    /*
    add a Component to this circuit. The object must be a Circuit or a Receptacle.
    Appliances can not be directly added to a Circuit - they must be connected
    through a Receptacle.

    Specified by:
        add in class Component

    Parameters:
        el - Component to be added to this Circuit.
    Returns:
        true if the Component has been directly added to this Circuit. Return false
        otherwise.
     */
    public boolean add(Component el) {
        if (el instanceof Circuit || el instanceof Receptacle) {
            String success = this.updateCurrent(el.currCurrent);
            if (success == null) {
                this.children.put(el.name, el);
                return true;
            } else if (success instanceof String) {
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    /*
    Output a string representation of this Circuit and its children.

    Specified by:
        display in class Component

    Parameters:
        offset - amount to indent each line. Children indented +4 spaces
     */
    protected void display(String offset) {
        System.out.println(offset + "Circuit: " + this.name + " using " + this.currCurrent + " out of " + this.maxCapacity + " available amps");
        if (this.children.size() > 0) {
            for (Component child : this.children.values()) {
                displayHelper(child);
            }
        }
    }

    /**
     * Calls appropriate display method to display component
     *
     * @param component: Component instance
     */
    private void displayHelper(Component component) {
        if (component instanceof Receptacle) {
            component.display("\t\t");
        } else if (component instanceof Circuit) {
            component.display("\t");
        } else if (component instanceof Appliance) {
            component.display("\t\t\t");
        }
    }





    /*
    Update current usage within this Circuit. A Circuit has a limit on current,
     so this may cause a failure (overload). If so, it immediately triggers a
     reset operation from this Circuit downward. It also triggers a new
     updateCurrent call to update up the chain that this reset has occurred. If
     no overload occurs, the current is adjusted and the operation is passed
     upward. Note that this implies that a given current adjustment (toggling
     an Appliance on) can cause at most ONE circuit to overload. The first
     overload that is detected triggers the reset.

    Overrides:
        updateCurrent in class Component

    Parameters:
        deltaCurrent - change in current

    Returns:
        null if no overload occurs anywhere.
        Else returns the String name of the Component that was overloaded.
     */
    protected String updateCurrent(int deltaCurrent) {
        if (deltaCurrent + this.currCurrent > this.maxCapacity) {
            this.currCurrent = 0;
            for (Component child : this.children.values()){
                child.currCurrent = 0;
            }
            return this.name;
        } else {
            this.currCurrent += deltaCurrent;
            return null;
        }

    }


}







