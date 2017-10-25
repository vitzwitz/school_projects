/*
 * Author : Bri Miskovitz
 * Class : MultiComponent extends Component
 */

import java.util.HashMap;

public abstract class MultiComponent extends Component{

    /* map of all children connected to this Component */
    protected HashMap<String,Component> children;


    /*
    Basic constructor for MultiComponent object. Calls parent constructor, and
    also initializes a list of children objects.

    Parameters:
        name - name of this Component in the electrical system
        source - source of this Component in the system
    */
    protected MultiComponent(String name, Component source){
        super(name, source);
        this.children = new HashMap<String,Component>();
    }
//
//    /*
//    * findSource : finds source of component in map that matches name
//    *
//    * Parameters:
//    *   map : map of components (hashMap<String, Component>)
//    *   sourceName : name (String)
//    * Returns:
//    *   Component if its found
//    *   null if its not
//    * */
//    public Component findSource(HashMap<String, Component> map, String sourceName){
//        if (map.size() == 0){
//            return null;
//        } else {
//            for (Component rootChild : map.values()){
//                if (rootChild.name.equals(sourceName)){
//                    return rootChild;
//                }
//                if (rootChild instanceof MultiComponent){
//                    if (((MultiComponent) rootChild).children.containsKey(sourceName)){
//                        return ((MultiComponent) rootChild).children.get(sourceName);
//                    } else {
//                        if ( ((MultiComponent) rootChild).children.size() > 0){
//                            findSource(((MultiComponent) rootChild).children, sourceName);
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }


//    /*
//* hasDuplicate : finds component if component is in map
//*
//* Parameters:
//*   map : map of components (hashMap<String, Component>)
//*   componentName : name (String)
//* Returns:
//*   true if its found
//*   false if its not
//* */
//    public boolean hasDuplicate(HashMap<String, Component> map, String componentName){
//        if (map.size() == 0){
//            return false;
//        } else {
//            for (Component rootChild : map.values()){
//                if (rootChild.name.equals(componentName)){
//                    return true;
//                }
//                if (rootChild instanceof MultiComponent){
//                    if (((MultiComponent) rootChild).children.containsKey(componentName)){
//                        return true;
//                    } else {
//                        System.out.println(rootChild.name + " : " + ((MultiComponent) rootChild).children.keySet() + " Size: " + ((MultiComponent) rootChild).children.size());
//                        if ( ((MultiComponent) rootChild).children.size() > 0){
//                            return hasDuplicate(((MultiComponent) rootChild).children, componentName);
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }

/*
* findComponent : finds component in map that matches name
*
* Parameters:
*   map : map of components (hashMap<String, Component>)
*   componentName : name (String)
* Returns:
*   Component if its found
*   null if its not
* */
//    public Component findComponent(HashMap<String, Component> map, String componentName){
//        System.out.println("Children: " + map.keySet());
//        if (map.size() == 0){
//            return null;
//        } else {
//            for (Component rootChild : map.values()){
//                if (rootChild.name.equals(componentName)){
//                    return rootChild;
//                }
//                if (!(rootChild instanceof Appliance)){
//                    if (rootChild instanceof MultiComponent){
//                        if (((MultiComponent) rootChild).children.containsKey(componentName)){
//                            return ((MultiComponent) rootChild).children.get(componentName);
//                        } else {
//                            if (((MultiComponent) rootChild).children.size() > 0) {
//                                findComponent(((MultiComponent) rootChild).children, componentName);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }


    /*
    Reset this MultiComponent to have a current usage of 0, and call reset
    on all children.

    Specified by:
        reset in class Component
     */
    public void reset(){
        this.currCurrent = 0;
        for (Component comp : this.children.values()){
            comp.reset();
        }
    }
}
