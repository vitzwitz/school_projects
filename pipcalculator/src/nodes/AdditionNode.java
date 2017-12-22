package nodes;
import static nodes.Precedence.ADD_SUBTRACT;

/**
 * Addition PIPCalcNode
 * @author Bri Miskovitz
 */
public class AdditionNode extends BinaryOperatorNode {


    /**
     * Constructor that sets the left/right children and sets the operator to the string + The precedence is set to
     * ADD_SUBTRACT
     * @param leftChild the PIPCalcNode representing the left child
     * @param rightChild the PIPCalcNode representing the right child
     */
    public AdditionNode(PIPCalcNode leftChild, PIPCalcNode rightChild) {
        super(leftChild, rightChild, ADD_SUBTRACT, "+");
    }


    /**
     * Evaluates the node to determine its integer value
     *
     * @return integer value of the node
     */
    @Override
    public float evaluate() {
        return leftChild.evaluate() + rightChild.evaluate();
    }
}
