package nodes;

import static nodes.Precedence.MULT_DIVIDE;

/**
 * Class representing Negation [node] PIPCalcNode
 * @author Bri Miskovitz
 */
public class NegationNode extends UnaryOperatorNode {


    /**
     * Constructor that sets the left child and sets the operator to the string _ The precedence is set to MULT_DIVIDE
     *
     * @param child PIPCalcNode that is the child of this node
     */
    public NegationNode(PIPCalcNode child) {
        super(child, "_", MULT_DIVIDE);
    }

    /**
     * Evaluates the node to determine its integer value
     *
     * @return integer value of the node
     */
    @Override
    public float evaluate() {
        return child.evaluate()*-1;
        //return Math.negateExact(child.evaluate());
    }
}
