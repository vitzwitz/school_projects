package tester;

import com.sun.org.apache.xpath.internal.operations.Div;
import controllers.PIPCalcController;
import nodes.*;
import processors.PIPCalcInfixProcessor;
import processors.PIPCalcPostfixProcessor;
import processors.PIPCalcPrefixProcessor;

import java.util.ArrayList;

public class myTester1 {

    public void test1()
    {
        int v = -12;
        int w = -5;
        int x = 7;
        int y = 3;
        int z = 1;

        ConstantNode V = new ConstantNode(v);
        ConstantNode W = new ConstantNode(w);
        ConstantNode X = new ConstantNode(x);
        ConstantNode Y = new ConstantNode(y);
        ConstantNode Z = new ConstantNode(z);

        AdditionNode YplusZ = new AdditionNode(Y, Z);
        SubtractionNode YminusZ = new SubtractionNode(Y, Z);
        MultiplicationNode YtimesZ = new MultiplicationNode(Y, Z);
        DivisionNode YdivideZ = new DivisionNode(Y,Z);

        System.out.println("TEST 1 - simple binary operation w/ positive numbers");
        System.out.println("\t" + y + " + " + z + " = " + YplusZ.evaluate());
        System.out.println("\t" + y + " - " + z + " = " + YminusZ.evaluate());
        System.out.println("\t" + y + " x " + z + " = " +  YtimesZ.evaluate());
        System.out.println("\t" + y + " // " + z + " = " + YdivideZ.evaluate());

        AdditionNode vplusZ = new AdditionNode(V, Z);
        SubtractionNode vminusZ = new SubtractionNode(V, Z);
        MultiplicationNode vtimesZ = new MultiplicationNode(V, Z);
        DivisionNode vdivideZ = new DivisionNode(V,Z);
        PowerNode vPowX = new PowerNode(V,X);

        System.out.println("\nTEST 2 - simple binary operation w/ positive & negative numbers");
        System.out.println("\t" + v + " + " + z + " = " + vplusZ.evaluate());
        System.out.println("\t" + v + " - " + z + " = " + vminusZ.evaluate());
        System.out.println("\t" + v + " x " + z + " = " +  vtimesZ.evaluate());
        System.out.println("\t" + v + " // " + z + " = " + vdivideZ.evaluate());
        System.out.println("\t" + v + " ^ " + x + " = " + vPowX.evaluate());

        NegationNode vNeg = new NegationNode(V);
        AbsValueNode vAbs = new AbsValueNode(V);
        SquareRootNode zSqRt = new SquareRootNode(Z);

        System.out.println("\nTEST 3 - unary operations");
        System.out.println("\t" + "_" + v + " = " + vNeg.evaluate());
        System.out.println("\t" + "|" + v + "|" + " = " + vAbs.evaluate());
        System.out.println("\t" + "@" + z + " = " +  zSqRt.evaluate());
    }




    public static void main(String[] args)
    {
        PIPCalcInfixProcessor modelIn = new PIPCalcInfixProcessor();
        PIPCalcPrefixProcessor modelPre = new PIPCalcPrefixProcessor();
        PIPCalcPostfixProcessor modelPost = new PIPCalcPostfixProcessor();

        StringBuilder statement = new StringBuilder();
        for (String x : args)
        {
            statement.append(x);
        }

        PIPCalcController controllerPre = new PIPCalcController(modelPre);
        controllerPre.convert(statement.toString(), "prefix");



    }
}
