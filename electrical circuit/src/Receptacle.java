/*
 * Author : Bri Miskovitz
 * Class : Receptacle extends MultiComponent
 */

/*
 Concrete Receptacle class. Note that we treat a 6-pack power strip
  (or other similar devices) as an example of a receptacle. Thus it
  is possible for a Receptacle to have a child (or a source) that is
  also a Receptacle. Additional allowed relationships are that a
  Receptacle has a Circuit as a source (e.g. wall outlet connected
  to the circuit), and a Receptacle can have Appliances as children.
  A Receptacle has a defined maximum number of children, but has no
  specified limit on its capacity (the circuit that this receptacle
  is part of defines the maximum current allowed).
 */
public class Receptacle extends MultiComponent {

    /* maximum number of children (e.g. plugs supported in outlet) */
    private int maxChildren;


    /*
    Constructor for a Receptacle. Constructor does not check that the source is
     correct type of Component (must be a Receptacle or Circuit). This is checked
     when this element is attempted to be added to the system.

    Parameters:
        name - name associated with this Receptacle
        source - source object
        maxEl - maximum number of elements connected to this Receptacle
     */
    public Receptacle(String name, Component source, int maxE1) {
        super(name, source);
        this.maxChildren = maxE1;
    }

    /*
    add a Component to this Receptacle. The object must be another Receptacle
     (e.g. a 6-pack power strip) or an Appliance (e.g. a toaster). Moreover,
     there must still remain an available socket (e.g. space for plug in a wall
     outlet) for this Receptacle to add another child.

    Specified by:
        add in class Component

    Parameters:
        el - Component to be added to this Receptacle

    Returns:
        true if the Component has been directly added to this Receptacle.
        Return false otherwise.
     */
    public boolean add(Component el) {
        if (el instanceof Receptacle || el instanceof Appliance) {
            if (this.maxChildren > this.children.size()) {
                String success = updateCurrent(el.currCurrent);
                if (success == null) {
                    this.children.put(el.name, el);
                    return true;
                }
            }
        }
        return false;
    }


    /*
    Output a string representation of this Receptacle and its children.

    Specified by:
        display in class Component

    Parameters:
        offset - indicates how much to indent each line. Children indented +4 spaces.
     */
    protected void display(String offset) {
        System.out.println(offset + "Receptacle: " + this.name + " using " + this.currCurrent + " amps");
        if (this.children.size() > 0) {
            for (Component child : this.children.values()) {
                displayHelper(child);
            }
        }
    }




    private void displayHelper(Component component){
        if (component instanceof Receptacle) {
            component.display("\t\t");
        } else if (component instanceof Circuit){
            component.display("\t");
        } else if (component instanceof  Appliance) {
            component.display("\t\t\t");
        }
    }

}
















