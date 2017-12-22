package nodes;

/**
 * Node representing the greater than equal to operator
 * @author Bri Miskovitz
 */
public class GreaterThanEqualNode extends BooleanOperatorNode {


    /**
     * Constructor that sets the left/right children and sets the operator to the string >=
     *
     * @param left the PIPCalcNode representing the left child
     * @param right the PIPCalcNode representing the right child
     */
    public GreaterThanEqualNode(PIPCalcNode left, PIPCalcNode right) {
        super(left, right, ">=");
    }

    /**
     * Evaluates the node
     *
     * @return integer value of the node
     */
    @Override
    public float evaluate() {
        float result;
        if (leftChild.evaluate() >= rightChild.evaluate())
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
