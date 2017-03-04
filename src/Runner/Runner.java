/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runner;

import LinearSystem.LinearSystem;
import LinearSystem.LinearSystemActions;
import Matrix.MatrixActions;
import Matrix.OneColMatrix;
import Matrix.SquareMatrix;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Администратор
 */
public class Runner {

    /**
     * Runs the programm.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, CloneNotSupportedException {
        /*
         * double[][] coefficients = {{2, 1, -1}, {-3, -1, 2}, {-2, 1, 2}};
         * double[] freeArguments = {8, -11, -3};
         *
         * LinearSystem mySystem = new LinearSystem(coefficients,
         * freeArguments);
         *
         * System.out.println(mySystem);
         *
         * LinearSystem solvedSystem = LinearSystemActions.solve(mySystem);
         * System.out.println(); System.out.println(solvedSystem);
         */

        LinearSystem mySystem2 = LinearSystemActions.readFromFile("E:\\M11");
        System.out.println(mySystem2);
        mySystem2.solveUsingGauusMethod();
        mySystem2.solveUsingJordanMethod();

    }
}
