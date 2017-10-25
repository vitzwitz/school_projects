/*
    Author: Bri Miskovitz
    Class: Appliance extends Component

    An electrical Appliance. (Appliance is a general name, and is meant to include
     all objects such as toasters, furnaces, dishwashers, lamps, clock-radios, etc.)
     This is a concrete class. The object extends the parent class Component, giving
     it a source (which must be a Receptacle), a name and a currCurrent, which will
     start at 0, when the appliance is added to the system. The current used by this
     appliance will always be either 0 (the appliance is OFF), or the required current
     for the device (the appliance is ON).
 */


public class Appliance extends Component {

    /*
    The required current (in amps) for this Appliance. This represents the peak current
     used by this Appliance, and is the value used in all calculations when this
     appliance is ON.
     */
    private int reqCurrent;

    /*
    Indicates if the device is currently in use. Not strictly necessary, as we could
     infer from currCurrent. Note that when an Appliance is not in use, its current
     usage is 0. There is no residual current usage when the device is turned off.
     Initially, when an Appliance is added to the system, it is not inUse.
     */
    private boolean inUse;



    /*
    Constructor for the Appliance. Constructor does not check that the source is correct
     type of Component (must be a Receptacle). This is checked when this element is
     attempted to be added to the system. Appliance starts out not inUse.

    Parameters:
        name - name of the Appliance
        source - source component
        reqCurr - required current for this Appliance
     */
    public Appliance(String name, Component source, int reqCurr) {
        super(name, source);
        this.reqCurrent = reqCurr;
        this.inUse = false;
    }

    /*
    Turns this appliance off. Sets boolean inUse flag to false, and sets currCurrent to 0.
     */
    public void reset(){
        this.inUse = false;
        this.currCurrent = 0;
    }

    /*
    This automatically returns false, as for this simulation, an Appliance can not be the
     source for any other Component.

    Specified by:
        add in class Component

    Parameters:
        el - Component to be added to this Appliance

    Returns:
        false - nothing can be added to an Appliance.
     */
    public boolean add(Component el){
        return false;
    }

    /*
    Output a string representation for this Appliance, indenting by the given offset

    Specified by:
        display in class Component

    Parameters:
        offset - indicates how much to indent
     */
    protected void display(String offset){
        System.out.println(offset + "Appliance: " + this.name + " using " + this.currCurrent + " amps");
    }


    /*
    toggle usage on/off for this Appliance. Calls updateCurrent to handle with appropriate
     change. Returns null if no overload caused. Else returns circuit object that
     overloaded.

    Returns:
        null if no overload occurs anywhere.
        Else returns the String name of the Component that was overloaded.
     */
    public String toggleUsage(){
        int newCurrent = this.reqCurrent;
        if (this.inUse) {
            newCurrent = 0 - newCurrent;
        }
        return this.updateCurrent(newCurrent);

    }



    /*
    Accessor for whether device is currently in use.

    Returns:
        boolean indicating if currently in use.
     */
    public boolean getInUse(){
        return this.inUse;
    }

















}
