package nodes;
import static nodes.Precedence.MULT_DIVIDE;


/**
 * Class representing Absolute Value [node] PIPCalcNode
 * @author Bri Miskovitz
 */
public class AbsValueNode extends UnaryOperatorNode {



    /**
     * Constructor that sets the left child and sets the operator to the string | The precedence is set to MULT_DIVIDE
     *
     * @param child PIPCalcNode that is the child of this node
     */
    public AbsValueNode(PIPCalcNode child) {
        super(child, "|", MULT_DIVIDE);
    }

    /**
     * Evaluates the node to determine its integer value
     *
     * @return integer value of the node
     */
    @Override
    public float evaluate() {
        return Math.abs(child.evaluate());
    }
}
