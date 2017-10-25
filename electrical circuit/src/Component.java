/*
 * Author : Bri Miskovitz
 */

/*
 * Class :
        Component

   Description:
        Abstract base class for electricity lab. This class is used to represent any component of the overall electrical
        system. Every component (whether a Circuit, Receptacle or Appliance) has a measure of the current current utilized
        by that component, a parent component (what does this component belong to) that is only null in the case of the root
        circuit of the system, and a name associated with this component (e.g. main, dishwasher, outlet1). The main
        functionality for the lab includes adding components to the system, displaying the components of the system from a
        given starting point, resetting the components of the system from a given starting point if this is an overload,
        and updating the current utilized by a given component.

*/
public abstract class Component {

    /*
     current being drawn at this moment by this object. In the case of circuits and
     receptacles, this refers to the aggregate current drawn by appliances using
     this circuit/receptacle.
     */
    protected int currCurrent;

    /*
    source of this component. Indicates where this component sits in overall system.
    Is null only for the root object (often main circuit).
     */
    protected Component source;

    /*
    name associated with this component.
     */
    protected String name;

    /*
    Basic constructor for an electrical Component involves a name and a source. All objects
     have a source in the system except for the root circuit. Current is always initialized
     to 0.

    Parameters:
        name - name of this Component in the electrical system
        source - source of this Component in the system
     */
    protected Component(String name, Component source) {
        this.source = source;
        this.name = name;
        this.currCurrent = 0;
    }

    /*
    Add a new electrical Component as a child of this element. Returns true if successful.
     Returns false if addition was unsuccessful.

    Parameters:
        newElem - Component object to be added.

    Returns:
        true if successful addition.
        False otherwise.
     */
    public abstract boolean add(Component newElem);

    /*
     Reset this object along with all of its children. This involves turning off all descendant Appliances so that they are
      not drawing any current.
     */
    public abstract void reset();

    /*
    Output a string representation of this Component and its children. The public method is called without any parameter.
     This method needs only call the protected version of the method with an initial offset that is the empty string.
     */
    public void display() {
        if (this instanceof Circuit) {
            display("\t");
        } else if (this instanceof Receptacle) {
            display("\t\t");
        } else if (this instanceof Appliance) {
            display("\t\t\t");
        } else {
            display("");
        }
    }


    /*
    Output a string representation of the given electrical Component and its children.

    Parameters:
        offset - indicates how much to indent each line
     */
    protected abstract void display(String offset);

    /*
    Update current. The default behavior for Appliances and Receptacles is to update and propagate up the source chain.
     A Circuit has to do an additional check that this update has not caused the circuit to be overloaded.

    Parameters:
        deltaCurrent - change in current. May be positive or negative.
    Returns:
        null if no overload occurs anywhere.
        Else returns the String name of the Component that was overloaded.
     */
    protected String updateCurrent(int deltaCurrent) {
        Component component = this;
        // Propogate through Circuit Board
        while (component != null) {
            if (deltaCurrent != 0){
            }
            // Send Circuits to Circuit updateCurrent method
            if (component instanceof Circuit) {
                String isOverload = component.updateCurrent(deltaCurrent);
                if (isOverload != null) {
                    return isOverload;
                }
            } else {
                component.currCurrent += deltaCurrent;
            }
            component = component.source;
        }
        return null;
    }
}










