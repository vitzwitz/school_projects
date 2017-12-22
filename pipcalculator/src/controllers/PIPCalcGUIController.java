package controllers;

import nodes.PIPCalcNode;
import processors.PIPCalcInfixProcessor;
import processors.PIPCalcPostfixProcessor;
import processors.PIPCalcPrefixProcessor;
import processors.PIPCalcProcessor;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Controller for GUI View
 */
public class PIPCalcGUIController {

    private PIPCalcProcessor model;


    /**
     * Constructor for PIPCAlcText Controller.
     *
     * The scanner and writer allow for file or command line
     * input/output
     *
     * @param model the initial model for this controllers
     */

    public PIPCalcGUIController(PIPCalcProcessor model){
        this.model = model;
    }

    /**
     * changes the current model to the provided model
     * @param mode desired mode
     */
    public void changeModel(String mode){
        PIPCalcNode tree = model.getTree();
        switch (mode)
        {
            case "prefix":
                this.model = new PIPCalcPrefixProcessor(tree);
                break;
            case "infix":
                this.model = new PIPCalcInfixProcessor(tree);
                break;
            case "postfix":
                this.model = new PIPCalcPostfixProcessor(tree);
                break;
        }
    }

    /**
     * Constructs and evaluates the provided string using the current model
     *
     * Note: It does not return the result. The model should update the views.
     * @param statement the string representing an expression to process
     */
    public void process(String statement) {
        ArrayList<String> tokens =
                new ArrayList<String>(Arrays.asList(statement.split(" ")));

        this.model.constructTree(tokens);
        this.model.evaluateTree();
    }



    /**
     * Converts the provided expression into the requested form.
     *
     * Note: It does not return the converted expression. The model should update the views.
     * @param mode the mode to convert the expression into
     */
    public void convert(String statement, String mode) {


        if (!statement.equals(""))
        {
            ArrayList<String> tokens =
                    new ArrayList<String>(Arrays.asList(statement.split(" ")));
            System.out.println("TOKENS -> " + tokens);
            System.out.println("STATEMENT -> " + statement);

            this.model.constructTree(tokens);
            this.model.displayTree(mode);
        }
    }
}
