package processors;

import nodes.BinaryOperatorNode;
import nodes.ConstantNode;
import nodes.PIPCalcNode;
import nodes.UnaryOperatorNode;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;

import static nodes.PIPCalcNode.NodeType.BinaryOperation;
import static nodes.PIPCalcNode.NodeType.Constant;
import static nodes.PIPCalcNode.NodeType.UnaryOperation;

/**
 * Class to process PIPCalc expressions using prefix notation
 * @author Bri Miskovitz
 */
public class PIPCalcPrefixProcessor extends PIPCalcProcessor {


    /**
     * Constructor
     */
    public PIPCalcPrefixProcessor() {

    }

    public PIPCalcPrefixProcessor(PIPCalcNode tree) {
        this.tree = tree;
    }

    /**
     * Constructs and assigns a PIPCalc tree from the provided list of PIPCalcNode tokens using prefix notation
     *
     * @param tokens list of PIPCalcNodes used to create the pares tree
     */
    @Override
    public void constructTree(ArrayList<String> tokens)
    {
        System.out.println("PRE - > got here");
        Stack<PIPCalcNode> operators = new Stack<>();
        Stack<PIPCalcNode> operands = new Stack<>();
        Boolean pending_operand = false;

        for (String token : tokens)
        {
            PIPCalcNode node = createPIPCalcNode(token);
            token = token.replaceAll("[()]", "");

            System.out.println("PREFIX -> NODE TYPE " + node.getNodeType() + "\n========================");

            if (node.getNodeType() == BinaryOperation || node.getNodeType() == UnaryOperation)
            {
                operators.push(node);
                pending_operand = false;
            }
            else if (node.getNodeType() == Constant)
            {
                PIPCalcNode operand = node;
                if (pending_operand)
                {
                    while (!operands.isEmpty())
                    {
                        PIPCalcNode operator = operators.pop();
                        if (operator.getNodeType() == UnaryOperation)
                        {
                            ((UnaryOperatorNode) operator).setChild(operand);
                            operand = operator;
                        }
                        else if (operator.getNodeType() == BinaryOperation)
                        {
                            PIPCalcNode operand_1 = operands.pop();
                            ((BinaryOperatorNode) operator).setLeftChild(operand_1);
                            ((BinaryOperatorNode) operator).setRightChild(operand);
                            operand = operator;
                        }
                    }
                }
                operands.push(operand);
                pending_operand = true;
            }
        }
        this.tree = operands.pop();
        }
    }

