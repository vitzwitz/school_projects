package nodes;
import static nodes.PIPCalcNode.NodeType.Constant;
import static nodes.Precedence.CONSTANT;


/**
 * Constant PIPCalcNode
 * @author Bri Miskovitz
 */
public class ConstantNode implements PIPCalcNode {

    /**
     * value of the node
     */
    private float value;

    /**
     * Constructor that sets the value of this node
     * @param value Constructor that sets the value of this node
     */
    public ConstantNode(float value) {
        this.value = value;
    }

    /**
     * Evaluates the node
     *
     * @return integer value of the node
     */
    @Override
    public float evaluate() {
        return this.value;
    }

    /**
     * Constructs prefix representation of the node
     *
     * @return prefix representation of the node
     */
    @Override
    public String toPrefixString() {
        return String.valueOf(value);
    }

    /**
     * Constructs infix representation of the node
     *
     * @return infix representation of the node
     */
    @Override
    public String toInfixString() {
        return String.valueOf(value);
    }

    /**
     * Constructs postfix representation of the node
     *
     * @return postfix representation of the node
     */
    @Override
    public String toPostfixString() {
        return String.valueOf(value);
    }

    /**
     * gets the precedence of this node
     *
     * @return integer value of the node's precedence
     */
    @Override
    public int getPrecedence() {
        return CONSTANT.getPrecedence();
    }

    /**
     * determines if the node is an operation node
     *
     * @return - true if an operation node, false otherwise
     */
    @Override
    public boolean isOperation() {
        return false;
    }

    /**
     * Determines the node type
     *
     * @return the type of this node
     */
    @Override
    public NodeType getNodeType() {
        return Constant;
    }
}
