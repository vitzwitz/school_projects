package nodes;

import static nodes.Precedence.ADD_SUBTRACT;

/**
 * Class representing Substraction [Node] PIPCalcNode
 * @author Bri Miskovitz
 */
public class SubtractionNode extends BinaryOperatorNode {


    /**
     * Constructor that sets the left/right children and sets the operator to the string - The precedence is set to
     * ADD_SUBTRACT
     *
     * @param left the PIPCalcNode representing the left child
     * @param right the PIPCalcNode representing the right child
     */
    public SubtractionNode(PIPCalcNode left, PIPCalcNode right) {
        super(left, right, ADD_SUBTRACT, "-");
    }

    /**
     * Evaluates the node to determine its integer value
     *
     * @return integer value of the node
     */
    @Override
    public float evaluate() {
        return leftChild.evaluate() - rightChild.evaluate();
    }
}
