package processors;

import nodes.*;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Abstract class for PIPCalcProcessors
 */
public abstract class PIPCalcProcessor extends Observable{

    protected PIPCalcNode tree;

    /**
     * determines if a string is numeric
     * @param s the string to determine if it is numeric
     * @return true if it is numeric, false otherwise
     */
    public static Boolean isNumeric( String s ){
        return s.matches( "[-+]?\\d+" );
    }

    /**
     * Getter for the underlying parse tree
     * @return an  PIPCalcNode representing the root of the parse tree
     */
    public PIPCalcNode getTree() {

        return tree;
    }

    /**
     * Creates an PIPCalcNode based on the string passed in
     * @param s the string used to determine the type of PIPCalcNode to return
     * @return the PIPCalcNode represented by the string
     */
    protected PIPCalcNode createPIPCalcNode(String s){
        if(isNumeric(s)){
            return new ConstantNode(Float.parseFloat(s));
        }
        switch (s){
            case "+":
                return new AdditionNode(null, null);
            case "-":
                return new SubtractionNode(null, null);
            case "//":
                return new DivisionNode(null, null);
            case "*":
                return new MultiplicationNode(null, null);
            case "^":
                return new PowerNode(null, null);
            case "|":
                return new AbsValueNode(null);
            case "_":
                return new NegationNode(null);
            case "@":
                return new SquareRootNode(null);
            case ">":
                return new GreaterThanNode(null, null);
            case ">=":
                return new GreaterThanEqualNode(null, null);
            case "<":
                return new LessThanNode(null, null);
            case "<=":
                return new LessThanEqualNode(null, null);
            case "==":
                return new EqualityNode(null, null);
            case "!=":
                return new NotEqualityNode(null, null);
            default:
        }
        try
        {
            Float num = Float.parseFloat(s);
            return new ConstantNode(num);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }

    /**
     * Evaluates the underlying parse tree
     */
    public void evaluateTree(){

        this.setChanged();
        this.notifyObservers(Float.toString(this.tree.evaluate()));
    }

    /**
     * Constructs a parse tree from a list of IerpNodes
     * @param tokens list of PIPCalcNodes used to create the pares tree
     */
    public abstract void constructTree( ArrayList<String> tokens );

    public void displayTree(String mode){
        String result = "";
        switch(mode){
            case "infix":
                result = this.tree.toInfixString();
                break;
            case "prefix":
                result = this.tree.toPrefixString();
                break;
            case "postfix":
                result = this.tree.toPostfixString();
                break;
        }
        this.setChanged();
        this.notifyObservers(result);
    }


}