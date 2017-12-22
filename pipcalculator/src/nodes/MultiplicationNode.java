package nodes;

import static nodes.Precedence.MULT_DIVIDE;

/**
 * Class representing Multiplication Node
 * @author Bri Miskovitz
 */
public class MultiplicationNode extends BinaryOperatorNode {

    /**
     * Constructor that sets the left/right children and sets the operator to the string * The precedence is set to
     * MULT_DIVIDE
     *
     * @param left the PIPCalcNode representing the left child
     * @param right the PIPCalcNode representing the right child
     */
    public MultiplicationNode(PIPCalcNode left, PIPCalcNode right) {
        super(left, right, MULT_DIVIDE, "*");
    }

    /**
     * Evaluates the node to determine its integer value
     * @return the integer value of this node
     */
    public float evaluate() {
        return leftChild.evaluate() * rightChild.evaluate();
    }


}
