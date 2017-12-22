package nodes;

import static nodes.Precedence.POWER;

/**
 * Class representing Power Node
 * @author Bri Miskovitz
 */
public class PowerNode extends BinaryOperatorNode {


    /**
     * Constructor that sets the left/right children and sets the operator to the string ^ The precedence is set to
     * POWER
     *
     * @param left the PIPCalcNode representing the base
     * @param right the PIPCalcNode representing the power
     */
    public PowerNode(PIPCalcNode left, PIPCalcNode right) {
        super(left, right, POWER, "^");
    }

    /**
     * Evaluates the node to determine its integer value
     *
     * @return integer value of the node
     */
    @Override
    public float evaluate() {
        return (float) Math.pow(leftChild.evaluate(), rightChild.evaluate());
    }
}
