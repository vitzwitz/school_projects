/*
 * Node.java
 *
 * Representation of a graph node.
 *
 */

import java.util.*;

/**
 * Class representing a node in a graph.
 *
 * @author atd Aaron T Deever
 * @author (Editor) Bri Miskovitz
 *
 */
public class Node {

    /*
     *  Name associated with this node.
     */
    private String name;

    /*
     * Neighbors of this node are stored as a list (adjacency list).
     */
    private List<Node> neighbors;


    /*
    * Boolean used for bfs and dfs algorithms -> node has been visited (true) or not (false)
    */
    private boolean visited;

    /*
    * Integer the layer the node exists on relative to a graph
    * */
    private int layer;


    /**
     * Constructor.  Initialized with an empty list of neighbors.
     *
     * @param name string representing the name associated with the node.
     */
    public Node(String name) {
        this.name = name;
        this.neighbors = new LinkedList<Node>();
        this.visited = false;
    }

    /**
     * Copy constructor. Initialized a node using another node; makes a copy of a node
     * @param n Node node to make copy of
     * */
    public Node(Node n) {
        this.name = n.name;
        this.neighbors = n.neighbors;
        this.visited = n.visited;
    }

    /**
     * Get the String name associated with this object.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }


    /**
     * Method to see if node is visited (true) or not
     *
     * @return Boolean visited attribute of node
     * */
    public boolean getVisited() {return this.visited;}

    /**
     * Method to find what layer Node exists on relative to a graph
     *
     * @return Integer layer node exists on
     * */
    public int getLayer() {return this.layer;}


    /**
     * Method to set the layer of the node
     *
     * @param theLayer integer the layer the node exists on relative to a graph
     * */
    public void setLayer(int theLayer) {this.layer = theLayer;}



    /**
     * Add a neighbor to this node.  Checks if already present, and does not
     * duplicate in this case.
     *
     * @param n: node to add as neighbor.
     */
    public void addNeighbor(Node n) {
        if(!neighbors.contains(n)) {
            neighbors.add(n);
        }
    }

    /**
     * Method to return the adjacency list for this node containing all
     * of its neighbors.
     *
     * @return the list of neighbors of the given node
     */
    public List<Node> getNeighbors() {
        return new LinkedList<Node>(neighbors);
    }


    /**
     * Method to set Node node to visited (true) or not visited (false)
     * for dfs algorithm
     *
     * @param visit Boolean true (visited) or false (not visited)
     * */
    public void setVisited(boolean visit) { this.visited = visit; }


    /**
     * Method to generate a string associated with the node, including the
     * name of the node followed by the names of its neighbors.  Overrides
     * Object toString method.
     *
     * @return string associated with the node.
     */
    @Override
    public String toString() {
        String result;
        result = name + ":  ";

        for(Node nbr : neighbors) {
            result = result + nbr.getName() + ", ";
        }
        // remove last comma and space, or just spaces in the case of no neighbors
        return (result.substring(0, result.length()-2));
    }

    /**
     *  Two Nodes are equal if they have the same name.
     *  @param other The other object to check equality with
     *  @return true if equal; false otherwise
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Node) {
            Node n = (Node) other;
            result = this.name.equals(n.name);
        }
        return result;
    }


    /**
     * The hash code of a Node is just the hash code of the name,
     * since no two nodes can have the same name.
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
