package nodes;

import static nodes.PIPCalcNode.NodeType.BinaryOperation;

/**
 * Abstract class to represent a binary operator Has a left/right child, precedence, and operator
 * @author Bri Miskovitz
 */
public abstract class BinaryOperatorNode implements PIPCalcNode {

    protected PIPCalcNode leftChild;
    protected PIPCalcNode rightChild;
    protected Precedence precedence;
    protected java.lang.String operator;

    /**
     * Binary Node Constructor

     * @param leftChild PIPCalcNode representing the left child
     * @param rightChild PIPCalcNode representing the right child
     * @param precedence Precedence of the operator
     * @param operator String representing the operator
     */
    public BinaryOperatorNode(PIPCalcNode leftChild, PIPCalcNode rightChild, Precedence precedence, String operator) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.precedence = precedence;
        this.operator = operator;
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
            case "+":
                result = new AdditionNode(leftChild, rightChild).evaluate();
                break;
            case "-":
                result = new SubtractionNode(leftChild, rightChild).evaluate();
                break;
            case "*":
                result = new MultiplicationNode(leftChild, rightChild).evaluate();
                break;
            case "//":
                result = new DivisionNode(leftChild, rightChild).evaluate();
                break;
            case "^":
                result = new PowerNode(leftChild, rightChild).evaluate();
                break;
            case ">":
                result = new GreaterThanNode(leftChild, rightChild).evaluate();
                break;
            case ">=":
                result = new GreaterThanEqualNode(leftChild, rightChild).evaluate();
                break;
            case "<":
                result = new LessThanNode(leftChild, rightChild).evaluate();
                break;
            case "<=":
                result = new LessThanEqualNode(leftChild, rightChild).evaluate();
                break;
            case "==":
                result = new EqualityNode(leftChild, rightChild).evaluate();
                break;
            case "!=":
                result = new NotEqualityNode(leftChild, rightChild).evaluate();
                break;
            default:
                System.out.println("Invalid binary operator");
                result = 0;
                break;
        }
        return result;
    }

    /**
     * Constructs prefix representation of the node
     *
     * @return prefix representation of the node
     */
    @Override
    public String toPrefixString() {
        return operator + " " + leftChild.toPrefixString() + " " + rightChild.toPrefixString();
    }

    /**
     * Constructs infix representation of the node
     *
     * @return prefix representation of the node
     */
    @Override
    public String toInfixString() {
        return "(" + leftChild.toInfixString() + " " + operator + " " + rightChild.toInfixString() + ")";
    }

    /**
     * Constructs postfix representation of the node
     *
     * @return prefix representation of the node
     */
    @Override
    public String toPostfixString() {
        return leftChild.toPostfixString() + " " + rightChild.toPostfixString() + " " + operator;
    }

    /**
     * gets the precedence of this node
     *
     * @return integer value of the node's precedence
     */
    @Override
    public int getPrecedence() {
        return this.precedence.getPrecedence();
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
        return BinaryOperation;
    }

    /**
     * Setter for left child
     * @param leftChild The PIPCalcNode to be set at this node's left child
     */
    public void setLeftChild(PIPCalcNode leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Setter for right child
     * @param rightChild The PIPCalcNode to be set at this node's right child *** (typo?)
     */
    public void setRightChild(PIPCalcNode rightChild) {
        this.rightChild = rightChild;
    }

}
