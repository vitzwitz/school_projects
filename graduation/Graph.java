/*
 * Graph.java
 *
 * Using stacks and queues for DFS and BFS.
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Graph class.  Holds representation of a graph as well as functions to 
 * interact with the graph.
 *
 * @author atd Aaron T Deever
 * @author sps Sean Strout
 * @author (Editor) Bri Miskovitz
 */
public class Graph {

    /*
     * graph is represented using a map (dictionary).
     */
    private Map<String, Node> graph;

    /**
     * Constructor.  Loads graph from a given filename.  Assumes that each line
     * in the input file contains the names of two nodes.  Creates nodes
     * as necessary as well as undirected edges between the nodes.
     * Returns the graph in the form of a map having the names of the
     * nodes as keys, and the nodes themselves as values.
     *
     * Changes:
     *          1.) nodes only point one way
     *          2.) nodes can take multiple neighbors now
     *
     * @param filename name of the input graph specification file
     * @throws FileNotFoundException if file not found
     */
    public Graph(String filename) throws FileNotFoundException {

        // open the file for scanning
        String path = "C:/Users/Brianna/IdeaProjects/lab6/src/";
        File file = new File(path + filename);
        Scanner in = new Scanner(file);

        // create the graph
        graph = new HashMap<String, Node>();
        int layer = 0;

        // loop over and parse each line in the input file
        while (in.hasNextLine()) {
            // read and split the line into an array of strings
            // where each string is separated by a space.
            Node n1;
            String line = in.nextLine();

            String[] fields = line.split(" ");
            boolean flag = false;


            // Main Course for the line
            // creates new nodes as necessary
            if (graph.containsKey(fields[0])) {
                n1 = graph.get(fields[0]);
            } else {
                n1 = new Node(fields[0]);
                graph.put(fields[0], n1);
            }
            n1.setLayer(layer);
            layer++;

            for (int i = 1; i < fields.length; i++) {
                if (fields[i].equals("None")) {
                    flag = true;
                }
            }
            /* Pre-reqs for main course */
            if (!flag) {
                Node n;
                for (int i = 1; i < fields.length; i++) {
                    if (graph.containsKey(fields[i])) {
                        n = graph.get(fields[i]);
                        n1.addNeighbor(n);

                    } else {
                        n = new Node(fields[i]);
                        n1.addNeighbor(n);
                    }
                }
            }
        }
        in.close();
    }

    /**
     * Method to generate a string associated with the graph.  The string
     * comprises one line for each node in the graph. Overrides
     * Object toString method.
     *
     * @return string associated with the graph.
     */
    public String toString() {
        String result = "";
        for (String name : graph.keySet()) {
            result = result + graph.get(name) + "\n";
        }
        return result;
    }

    /**
     * Method to check if a given String node is in the graph.
     * @param nodeName: string name of a node
     * @return boolean true if the graph contains that key; false otherwise
     */
    public boolean isInGraph(String nodeName) {
        return graph.containsKey(nodeName);
    }

    /**
     * For a given start and finish node, we simply want to know whether
     * a path exists, or not, between them. This is the precursor to
     * searchDFS().
     * @param start the name associated with the node from which to start the search
     * @param finish the name associated with the destination node
     * @return boolean true if a path exists; false otherwise
     */
    public boolean canReachDFS(String start, String finish) {
        // assumes input check occurs previously
        Node startNode, finishNode;
        startNode = graph.get(start);
        finishNode = graph.get(finish);

        // prime the stack with the starting node
        Stack<Node> stack = new Stack<Node>();
        stack.push(startNode);

        // create a visited set to prevent cycles
        Set<Node> visited = new HashSet<Node>();
        // add start node to it
        visited.add(startNode);

        // loop until either the finish node is found (path exists), or the
        // dispenser is empty (no path)
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (current == finishNode) {
                return true;
            }
            // loop over all neighbors of current
            for (Node nbr : current.getNeighbors()) {
                // process unvisited neighbors
                if (!visited.contains(nbr)) {
                    visited.add(nbr);
                    stack.push(nbr);
                }
            }
        }
        return false;
    }


    /**
     * Method that visits all nodes reachable from the given starting node
     * in depth-first search fashion usin  A predeceg a stack, stopping only if the finishing
     * node is reached or the search is exhausted.ssors map
     * keeps track of which nodes have been visited and along what path
     * they were first reached.
     *
     * @param start the name associated with the node from which to start the search
     * @param finish the name associated with the destination node
     * @return path the path from start to finish.  Empty if there is no such path.
     *
     * Precondition: the inputs correspond to nodes in the graph.
     */
    public List<Node> searchDFS(String start, String finish) {

        // assumes input check occurs previously
        Node startNode, finishNode;
        startNode = graph.get(start);
        finishNode = graph.get(finish);

        // prime the dispenser (stack) with the starting node
        List<Node> dispenser = new LinkedList<Node>();
        dispenser.add(0, startNode);

        // construct the predecessors data structure
        Map<Node, Node> predecessors = new HashMap<Node,Node>();
        // put the starting node in, and just assign itself as predecessor
        predecessors.put(startNode, startNode);

        // loop until either the finish node is found, or the
        // dispenser is empty (no path)
        while (!dispenser.isEmpty()) {
            Node current = dispenser.remove(0);
            if (current == finishNode) {
                break;
            }
            // loop over all neighbors of current
            for (Node nbr : current.getNeighbors()) {
                // process unvisited neighbors
                if(!predecessors.containsKey(nbr)) {
                    predecessors.put(nbr, current);
                    dispenser.add(0, nbr);
                }
            }
        }

        return constructPath(predecessors, startNode, finishNode);
    }



    /**
     * Method that visits all nodes reachable from the given starting node
     * in breadth-first search fashion using a queue, stopping only if the finishing
     * node is reached or the search is exhausted.  A predecessors map
     * keeps track of which nodes have been visited and along what path
     * they were first reached.
     *
     * @param start the name associated with the node from which to start the search
     * @param finish the name associated with the destination node
     * @return path the path from start to finish.  Empty if there is no such path.
     *
     * Precondition: the inputs correspond to nodes in the graph.
     */
    public List<Node> searchBFS(String start, String finish) {

        // assumes input check occurs previously
        Node startNode, finishNode;
        startNode = graph.get(start);
        finishNode = graph.get(finish);

        // prime the dispenser (queue) with the starting node
        List<Node> dispenser = new LinkedList<Node>();
        dispenser.add(startNode);

        // construct the predecessors data structure
        Map<Node, Node> predecessors = new HashMap<Node,Node>();
        // put the starting node in, and just assign itself as predecessor
        predecessors.put(startNode, startNode);

        // loop until either the finish node is found, or the
        // dispenser is empty (no path)
        while (!dispenser.isEmpty()) {
            Node current = dispenser.remove(0);
            if (current == finishNode) {
                break;
            }
            // loop over all neighbors of current
            for (Node nbr : current.getNeighbors()) {
                // process unvisited neighbors
                if(!predecessors.containsKey(nbr)) {
                    predecessors.put(nbr, current);
                    dispenser.add(nbr);
                }
            }
        }

        return constructPath(predecessors, startNode, finishNode);
    }


    /**
     * Method to return a path from the starting to finishing node.
     *
     * @param predecessors Map used to reconstruct the path
     * @param startNode starting node
     * @param finishNode finishing node
     * @return a list containing the sequence of nodes comprising the path.
     * Empty if no path exists.
     */
    private List<Node> constructPath(Map<Node,Node> predecessors,
                                     Node startNode, Node finishNode) {

        // use predecessors to work backwards from finish to start,
        // all the while dumping everything into a linked list
        List<Node> path = new LinkedList<Node>();

        if(predecessors.containsKey(finishNode)) {
            Node currNode = finishNode;
            while (currNode != startNode) {
                path.add(0, currNode);
                currNode = predecessors.get(currNode);
            }
            path.add(0, startNode);
        }

        return path;
    }

    /**
     * Method to return a set of all keys (course names) in the graph.
     * @return Set of course names
     */
    public Set<String> getAllCourseNames() {
        return this.graph.keySet();
    }


    /**
     * DFS algorithm to find all of a course's prerequisites.  Method
     * recursively adds all of vertex's neighbors, its children's neighbors,
     * etc
     * @param vertex Node of vertex of graph that represents a class
     * @param preReqs List<Node> of list of all of a class's prerequisites represented
     *                by a vertex's children, sub-children, etc
     * @return updated preReq list
     *
     **/
    public List<Node> dfs(Node vertex, List<Node> preReqs, Integer size)
    {
        vertex.setVisited(true);
        System.out.println("Vertex: " + vertex.getName());

        for (Node nbr : vertex.getNeighbors()) {
            if (!nbr.getVisited()) {
                if (preReqs.isEmpty())
                {
                preReqs.add(nbr);
                }
                else
                {
                    preReqs.add(size, nbr);
                }
                size++;
                dfs(nbr, preReqs, size);
            }
            size = preReqs.size();
        }
        return preReqs;
    }


    /**
     * DFS algorithm to find all of a course's prerequisites.  Method
     * recursively adds all of vertex's neighbors before the vertex,
     * its children's neighbors before its children, etc
     *
     * @param vertex a node in a graph
     * @param preReqs list of prerequisites
     *
     * @return updated list of pre-requisites in specific order
     * */
    public List<Node> mod_dfs(Node vertex, List<Node> preReqs)
    {
        vertex.setVisited(true);
        int i = 0;
        for (Node nbr : vertex.getNeighbors()) {
            if (!nbr.getVisited()) {
                preReqs.add(i, nbr);
                mod_dfs(nbr, preReqs);
            }
            i++;
        }
        return preReqs;
    }



    /**
     * Resets a vertex, its children, etc visited all to false
     * @param vertex Node vertex in graph (class)
     * */
    public void resetGraphDFS(Node vertex)
    {
        vertex.setVisited(false);
        for (Node nbr : vertex.getNeighbors()) {
            if (nbr.getVisited()) {
                resetGraphDFS(nbr);
            }
        }
    }



    /**
     * Method to compute all prerequisites required before
     * a certain course can be taken.  This includes not only
     * direct prerequisites, but also prerequisites
     * of the prerequisites, etc.
     * <p>
     * Precondition:  the input course name is assumed to be valid
     *
     * @param courseName name of the course of interest
     * @return a list of Nodes corresponding to courses
     * that must be taken before the course of interest.
     * Does not include the course of interest itself.
     */
    public List<Node> computeAllPrereqs(String courseName) {

        resetGraphDFS(this.graph.get(courseName));

        System.out.println("=============================================");
        List<Node> Reqs = dfs(this.graph.get(courseName), new LinkedList<Node>(), 0);
        System.out.println();
        for (Node n: Reqs
             ) {
            System.out.print("*** " + n.getName());
        }
        System.out.println();

        resetGraphDFS(this.graph.get(courseName));

        while (Reqs.contains(this.graph.get(courseName)))
        {
            Reqs.remove(this.graph.get(courseName));
        }
        return Reqs;
    }


    /**
     * Method to find longest chain of pre-requisites for a node
     *  -> Uses modified DFS algorithm
     * @param node Node node in a graph
     * @return list of prerequisites for a node in correct order
     * */
    public List<Node> calculateGraduationHelper(Node node)
    {
        List<Node> result = new LinkedList<Node>();
        for (Node nbr : node.getNeighbors())
        {
            List<Node> reqs = new LinkedList<Node>();
            reqs.add(node);
            reqs.add(0,nbr);

            List<Node> rqs = new LinkedList<Node>();
            rqs.addAll(reqs);

            resetGraphDFS(nbr);
            reqs = mod_dfs(nbr, reqs);
            resetGraphDFS(nbr);

            if (reqs.size() > result.size())
            {
                result = reqs;
            }
        }
        return result;
    }

    /**
     * Method to compute the longest chain of prerequisites in the graph.
     * <p>
     * Precondition - graph must be acyclic, or this may not terminate.
     *
     * @return List of Nodes corresponding to the longest chain of nodes.
     *
     */
    public List<Node> longestChainOfPrereqs()
    {
        List<Node> result = new LinkedList<Node>();
        for (String crs : getAllCourseNames())
        {
//            System.out.println("==========================================================");
//            System.out.println("Course: " + crs);

            List<Node> cl = calculateGraduationHelper(this.graph.get(crs));
//            System.out.println("Path: " + cl);
            if (cl.size() > result.size())
            {
                result = cl;
//                System.out.println("Result Update ->" + result.toString());
            }
        }
        return result;
    }
}


