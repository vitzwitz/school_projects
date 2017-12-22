package nodes;

import static nodes.Precedence.MULT_DIVIDE;

/**
 * Class representing Division Node
 * @author Bri Miskovitz
 */
public class DivisionNode extends BinaryOperatorNode {

    /**
     * Constructor that sets the left/right children and sets the operator to the string // The precedence is set to
     * MULT_DIVIDE
     *
     * @param left the PIPCalcNode representing the left child
     * @param right the PIPCalcNode representing the right child
     */
    public DivisionNode(PIPCalcNode left, PIPCalcNode right) {
        super(left, right, MULT_DIVIDE, "//");
    }

    /**
     * Evaluates the node to determine its integer value
     * Errors if the right child evaluates to zero
     *
     * @return integer value of the node
     */
    @Override
    public float evaluate() {
        float result = 0;
        if (rightChild.evaluate() != 0)
        {
            result = leftChild.evaluate() / rightChild.evaluate();
        }
        else
        {
            System.out.println("Cannot take the square root of a negative number");
        }
        return result;
    }
}
