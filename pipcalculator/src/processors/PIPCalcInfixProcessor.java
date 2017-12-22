package processors;

import nodes.BinaryOperatorNode;
import nodes.ConstantNode;
import nodes.PIPCalcNode;
import nodes.UnaryOperatorNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static nodes.PIPCalcNode.NodeType.BinaryOperation;
import static nodes.PIPCalcNode.NodeType.Constant;
import static nodes.PIPCalcNode.NodeType.UnaryOperation;

/**
 * Class to process PIPCalc expressions using infix notation
 * @author Bri Miskovitz
 */
public class PIPCalcInfixProcessor extends PIPCalcProcessor {

    /**
     * Constructor
     */
    public PIPCalcInfixProcessor() {}

    public PIPCalcInfixProcessor(PIPCalcNode tree) {
        this.tree = tree;
    }

    /**
     * Constructs and assigns a PIPCalc tree from the provided list of PIPCalcNode tokens using infix notation
     *
     * @param tokens list of PIPCalcNodes used to create the pares tree
     */
    @Override
    public void constructTree(ArrayList<String> tokens)
    {
        System.out.println("PRE - > got here");

        Stack<PIPCalcNode> other = new Stack<>();
        Stack<PIPCalcNode> operators = new Stack<>();
        for (String token : tokens)
        {
            token = token.replaceAll("[()]", "");

            System.out.println("TOKEN ->" + token);

            PIPCalcNode node = createPIPCalcNode(token);
            System.out.println("INFIX -> NODE TYPE " + node.getNodeType() + "\n========================");

            if (node.getNodeType() == Constant)
            {
                other.push(node);
            }
            else if (node.getNodeType() == UnaryOperation)
            {
                operators.push(node);
            }
            else if (node.getNodeType() == BinaryOperation)
            {
                if (!operators.isEmpty())
                {
                    PIPCalcNode top = operators.peek();
                    while (top.getPrecedence() <= node.getPrecedence() && !operators.isEmpty() && !other.isEmpty()) {
                        top = operators.pop();
                        if (top.getNodeType() == BinaryOperation) {
                            ((BinaryOperatorNode) top).setRightChild(other.pop());
                            ((BinaryOperatorNode) top).setLeftChild(other.pop());
                        } else if (top.getNodeType() == UnaryOperation) {
                            ((UnaryOperatorNode) top).setChild(other.pop());
                        }
                        other.push(top);
                    }
                }
                operators.push(node);
            }
        }
        while (!operators.isEmpty())
        {
            PIPCalcNode top = operators.pop();
            if (top.getNodeType() == BinaryOperation)
            {
                ((BinaryOperatorNode) top).setRightChild(other.pop());
                ((BinaryOperatorNode) top).setLeftChild(other.pop());
                other.push(top);
            }
            else if (top.getNodeType() == UnaryOperation)
            {
                ((UnaryOperatorNode) top).setChild(other.pop());
                other.push(top);
            }
        }
        this.tree = other.pop();
    }
}
