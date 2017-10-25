import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class PanelSimulator {
    /*
    public static void runSimulation()

    Run simulation. Read input from System.in. User-input loop accepts following commands:
        A (for add) followed by:
        C name sourceName maxCurrent (circuit add)
        R name sourceName maxAttach (receptacle add)
        A name sourceName reqCurr (appliance add)
        T (for toggle use) followed by:
            name (for appliance)
        D (for display) followed by:
            name (for component)
        Q (for quit)
    */



    public static void runSimulation(){
        System.out.print("PanelSimulator: ");
        Scanner in = new Scanner(System.in);
        Circuit root = null;
        int i = 1;
        while (in.hasNext()) {
            String line = in.nextLine();
            if (!line.equals("")) {
                String[] infoList = line.split(" ");
                char c = line.charAt(0);
                switch (c) {
                    // Add Component
                    case 'A':
                        char d = line.charAt(2);
                        switch (d) {

                            // Add Circuit
                            case 'C':
                                int maxCap = Integer.parseInt(infoList[4]);
                                if (infoList[3].equals("ROOT")) {
                                    root = new Circuit(infoList[2], null, maxCap);
                                    System.out.print(root.name + " added as root circuit\n");
                                } else {
                                    if (infoList[3].equals("main")) {
                                        Circuit newCirc = new Circuit(infoList[2], root, maxCap);
                                        if (root.add(newCirc)) {
                                            root.children.put(newCirc.name, newCirc);
                                            System.out.print("PanelSimilator input: Circuit '" + newCirc.name + "' added successfully\n");
                                        }

                                    } else if (root != null) {
                                        // Checks if component already exists in system
                                        if (root.children.containsKey(infoList[2])) {
                                            continue;
                                        }
                                        // Checks if source exists
                                        Component sourceC = root.children.get(infoList[3]);
                                        if (sourceC == null) {
                                            continue;
                                        }
                                        Circuit newCirc = new Circuit(infoList[2], sourceC, maxCap);
                                        if (sourceC.add(newCirc)) {
                                            root.children.put(newCirc.name, newCirc);
                                            System.out.print("PanelSimilator input: Circuit '" + newCirc.name + "' added successfully\n");
                                        }
                                    }
                                }
                                break;
                            // Add Receptacle
                            case 'R':
                                // Checks if component already exists in system
                                if (root.children.containsKey(infoList[2])) {
                                    System.out.println("I win");
                                }
                                if (infoList[3].equals("main")){
                                    int numPlugs = Integer.parseInt(infoList[4]);
                                    Receptacle newRecep = new Receptacle(infoList[2], root, numPlugs);
                                    if (root.add(newRecep)) {
                                        root.children.put(newRecep.name, newRecep);
                                        System.out.print("PanelSimilator input: Receptacle '" + newRecep.name + "' added successfully\n");
                                    }

                                } else {
                                    Component sourceR = root.children.get(infoList[3]);
                                    if (sourceR == null) {
                                        continue;
                                    }
                                    int numPlugs = Integer.parseInt(infoList[4]);
                                    Receptacle newRecep = new Receptacle(infoList[2], sourceR, numPlugs);
                                    if (sourceR.add(newRecep)) {
                                        root.children.put(newRecep.name, newRecep);
                                        System.out.print("PanelSimilator input: Receptacle '" + newRecep.name + "' added successfully\n");
                                    }
                                }
                                break;
                            // Add Appliance
                            case 'A':
                                // Checks if component already exists in system
                                if (root.children.containsKey(infoList[2])) {
                                    continue;
                                }
                                Component sourceA = root.children.get(infoList[3]);
                                if (sourceA == null) {
                                    continue;
                                }
                                int reqCurrent = Integer.parseInt(infoList[4]);
                                Appliance newAppl = new Appliance(infoList[2], sourceA, reqCurrent);
                                if (sourceA.add(newAppl)) {
                                    root.children.put(newAppl.name, newAppl);
                                    System.out.print("PanelSimilator input: Appliance '" + newAppl.name + "' added successfully\n");
                                }
                                break;
                        }
                    break;

                    // Display
                    case 'D':
                        if (infoList[1].equals("main")){
                            System.out.println("PanelSimulator input: ");
                            root.display();
                        } else {
                            Component component = root.children.get(infoList[1]);
                            if (component != null) {
                                System.out.println("PanelSimulator input: ");
                                component.display();
                            }
                        }
                        break;

                    // Toggle
                    case 'T':
                        if (root.children.containsKey(infoList[1])){
                        }
                        Component component = root.children.get(infoList[1]);
                        if (component != null) {
                            // Check if component is appliance
                            if (component instanceof Appliance) {
                                // Checks if appliance is in use
                                String overloadedComp = ((Appliance) component).toggleUsage();
                                //Checks if there is circuit overloads
                                if (overloadedComp == null) {
                                    System.out.print(component.name + " turned on.\n");
                                } else {
                                    System.out.print(overloadedComp + " circuit overload!");
                                    root.reset();
                                }
                            }
                        }
                        break;

                    // Quit
                    case 'Q':
                        System.out.println("Quitting!");
                        System.exit(0);
                        break;

                }
                i++;
            }
        }
    }

    /*
     Main method. Calls static method to run simulation. Command line arguments
     are not used. All input is received through System.in.

    Parameters:
        args - command line args. Not used.
     */
    public static void main(String[] args) {
        runSimulation();
    }
}
