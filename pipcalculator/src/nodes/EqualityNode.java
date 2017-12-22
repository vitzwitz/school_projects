package nodes;

/**
 * Node representing the equality boolean operator
 * @author Bri Miskovitz
 */
public class EqualityNode extends BooleanOperatorNode {


    /**
     * Constructor that sets the left/right children and sets the operator to the string ==
     *
     * @param left the PIPCalcNode representing the left child
     * @param right the PIPCalcNode representing the right child
     */
    public EqualityNode(PIPCalcNode left, PIPCalcNode right) {
        super(left, right, "==");
    }

    /**
     * Evaluates the node to determine its integer value 1 for true, zero for false
     *
     * @return integer value of the node
     */
    @Override
    public float evaluate() {
        float result;
        if (leftChild.evaluate() == rightChild.evaluate())
        {
            result = 1;
        }
        else
        {
            result = 0;
        }
        return result;
    }
}
