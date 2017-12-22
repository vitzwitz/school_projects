package nodes;

import com.sun.org.apache.xpath.internal.operations.Neg;

import static nodes.PIPCalcNode.NodeType.UnaryOperation;

/**
 * Abstract class to represent a unary operator Unary operators only have a single child
 * @author Bri Miskovitz
 */
public abstract class UnaryOperatorNode implements PIPCalcNode {

    protected PIPCalcNode child;
    protected java.lang.String operator;
    protected Precedence precedence;

    /**
     * Constructor
     * @param child the PIPCalcNode representing the child
     * @param operator operator for node
     * @param precedence precedence for node
     */
    public UnaryOperatorNode(PIPCalcNode child, String operator, Precedence precedence) {
        this.child = child;
        this.operator = operator;
        this.precedence = precedence;
    }


    /**
     * Evaluates the node
     *
     * @return integer value of the node
     */
    @Override
    public float evaluate() {

        float result;
        switch (operator)
        {
            case "|":
                result = new AbsValueNode(child).evaluate();
                break;
            case "_":
                result = new NegationNode(child).evaluate();
                break;
            case "@":
                result = new SquareRootNode(child).evaluate();
                break;
            default:
                System.out.println("Invalid unary operator");
                result = child.evaluate();
        }

        return result;
    }

    /**
     * Sets the child of this node
     * @param child the PIPCalcNode representing the child
     */
    public void setChild(PIPCalcNode child) {
        this.child = child;
    }

    /**
     * Constructs prefix representation of the node
     *
     * @return prefix representation of the node
     */
    @Override
    public String toPrefixString() {
        return operator + " " + child.toPrefixString();
    }

    /**
     * Constructs infix representation of the node
     *
     * @return prefix representation of the node
     */
    @Override
    public String toInfixString() {
        return "(" + operator + " " + child.toInfixString() + ")";
    }

    /**
     * Constructs postfix representation of the node
     *
     * @return prefix representation of the node
     */
    @Override
    public String toPostfixString() {
        return child.toPostfixString() + " " + operator;
    }

    /**
     * gets the precedence of this node
     *
     * @return integer value of the node's precedence
     */
    @Override
    public int getPrecedence() {
        return precedence.getPrecedence();
    }

    /**
     * determines if the node is an operation node
     *
     * @return - true if an operation node, false otherwise
     */
    @Override
    public boolean isOperation() {
        return true;
    }

    /**
     * Determines the node type
     *
     * @return the type of this node
     */
    @Override
    public NodeType getNodeType() {
        return UnaryOperation;
    }
}
