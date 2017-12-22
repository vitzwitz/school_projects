package nodes;

/**
 * Abstract class to represent a boolean operator Has a left/right child, precedence, and operator. Assumes non-zero
 * values are true, zero is false.
 * @author Bri Miskovitz
 */
public abstract class BooleanOperatorNode extends  BinaryOperatorNode {


    /**
     * Constructor for Boolean operation nodes The precedence is set to BOOLEAN
     *
     * @param leftChild the left child for this operation
     * @param rightChild the right child for this operation
     * @param operator the string representing the operation for this node
     */
    public BooleanOperatorNode(PIPCalcNode leftChild, PIPCalcNode rightChild, String operator) {
        super(leftChild, rightChild, Precedence.BOOLEAN, operator);
    }

    /**
     * Returns the precedence of this node
     *
     * @return returns the precedence of BOOLEAN
     */
    public int getPrecedence() {
        return this.precedence.getPrecedence();
    }
}
