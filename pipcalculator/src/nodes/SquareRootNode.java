package nodes;

import static nodes.Precedence.POWER;

/**
 * Class representing Square Root [node] PIPCalcNode
 * @author Bri Miskovitz
 */
public class SquareRootNode extends UnaryOperatorNode {


    /**
     * Constructor that sets the left child and sets the operator to the string @ The precedence is set to POWER
     *
     * @param child PIPCalcNode that is the child of this node
     */
    public SquareRootNode(PIPCalcNode child) {
        super(child, "@", POWER);
    }

    /**
     * Evaluates the node to determine its integer value
     * Errors if the child evaluates to a negative number
     *
     * @return integer value of the node
     */
    @Override
    public float evaluate() {
        float result = 0;
        if (child.evaluate() >= 0)
        {
            result = (int) Math.sqrt(child.evaluate());
        }
        else
        {
            System.out.println("Cannot take the square root of a negative number");
        }
        return result;
    }
}
