package processors;

import nodes.*;

import java.util.ArrayList;
import java.util.Stack;

import static nodes.PIPCalcNode.NodeType.BinaryOperation;
import static nodes.PIPCalcNode.NodeType.Constant;
import static nodes.PIPCalcNode.NodeType.UnaryOperation;

/**
 * Class to process PIPCalc expressions using postfix notation
 * @author Bri Miskovitz
 */
public class PIPCalcPostfixProcessor extends PIPCalcProcessor {

    /**
     * Constructor
     */
    public PIPCalcPostfixProcessor(){}

    public PIPCalcPostfixProcessor(PIPCalcNode tree) {
        this.tree = tree;
    }

    /**
     * Constructs and assigns a PIPCalc tree from the provided list of PIPCalcNode tokens using postfix notation
     *
     * @param tokens list of PIPCalcNodes used to create the pares tree
     */
    @Override
    public void constructTree(ArrayList<String> tokens)
    {
        System.out.println("POST - > got here");
        Stack<PIPCalcNode> stack = new Stack<>();

        for (String token : tokens)
        {
            token = token.replaceAll("[()]", "");

            PIPCalcNode node = createPIPCalcNode(token);
            System.out.println("POSTFIX -> NODE TYPE " + node.getNodeType() + "\n========================");

            if (node.isOperation())
            {
                if (node.getNodeType() == BinaryOperation)
                {
                    PIPCalcNode operand_1 = stack.pop();
                    PIPCalcNode operand_2 = stack.pop();
                    ((BinaryOperatorNode) node).setLeftChild(operand_1);
                    ((BinaryOperatorNode) node ).setRightChild(operand_2);
                    stack.push(node);
                }
                else if (node.getNodeType() == UnaryOperation)
                {
                    ConstantNode operand = (ConstantNode) stack.pop();
                    ((UnaryOperatorNode) node).setChild(operand);
                    stack.push(node);
                }
            }
            else if (node.getNodeType() == Constant)
            {
                stack.push(node);
            }
        }
        this.tree = stack.pop();
    }
}
