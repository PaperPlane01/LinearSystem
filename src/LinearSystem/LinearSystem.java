/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinearSystem;

import Matrix.MatrixActions;
import Matrix.OneColMatrix;
import Matrix.SquareMatrix;

/**
 * Linear system of equastions is a is a collection of two or more linear 
 * equations involving the same set of variables.
 * Linear system can be presented as collection of two matrixes: the matrix of
 * coefficients and the matrix of the constant terms.
 */
public class LinearSystem implements Cloneable {

   /**
    * The matrix of coefficients.
    */
    private SquareMatrix coefficients;
    /**
     * The matrix of constant terms.
     */
    private OneColMatrix constantTerms;
    //private OneColMatrix roots;

    /**
     * Constructor without parameters.
     */
    public LinearSystem() {
    }

    /**
     * Creates a linear system with defined matrix of coefficients
     * and matrix of constant terms.
     * @param coefficients The matrix of coefficients.
     * @param constantTerms The matrix of constant terms.
     */
    public LinearSystem(SquareMatrix coefficients, OneColMatrix constantTerms) {
        this.coefficients = coefficients;
        this.constantTerms = constantTerms;
    }

    /**
     * Creates a linear system with deifned matrix of coefficients 
     * and matrix of constant terms.
     * @param coefficients The matrix of coefficients represented as two-dimensional
     * array of double.
     * @param constantTerms The matrix of constant terms represented as one-dimensional
     * array of double.
     */
    public LinearSystem(float[][] coefficients, float[] constantTerms) {
        this.coefficients = new SquareMatrix(coefficients);
        this.constantTerms = new OneColMatrix(constantTerms);
    }

    /**
     * Provides the access to the matrix of coefficients.
     * @return Returns the matrix of coefficients.
     */
    public SquareMatrix getCoefficients() {
        return coefficients;
    }

    /**
     * Provides the access to the matrix of constant terms.
     * @return The matrix of constant terms.
     */
    public OneColMatrix getConstantTerms() {
        return constantTerms;
    }

   /**
    * Allows to know the order of the system.
    * @return Returns the order (number of variables) of the system.
    */
    public int getSize() {
        return this.constantTerms.getSize();
    }

    /**
     * Visual representation of the linear system.
     * @return Returns a visual representation on the linear system.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int maxLength;
        int length1 = MatrixActions.maxLentgh(coefficients);
        int length2 = MatrixActions.maxLength(constantTerms);

        if (length1 >= length2) {
            maxLength = length1;
        } else {
            maxLength = length2;
        }

        for (int i = 0; i <= this.coefficients.getSize().getRows(); i++) {
            for (int j = 0; j <= this.coefficients.getSize().getCols(); j++) {
                String tmp = "" + this.coefficients.getElement(i, j);
                if (tmp.length() < maxLength) {
                    for (int k = 0; k < maxLength - tmp.length(); k++) {
                        sb.append(" ");
                    }
                }

                sb.append(this.coefficients.getElement(i, j));
                sb.append("  ");
            }
            
            String tmp = "" + this.constantTerms.getElement(i);
            if (tmp.length() < maxLength) {
                for (int k = 0; k < maxLength - tmp.length(); k++) {
                    sb.append(" ");
                }
            }
            sb.append(this.constantTerms.getElement(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Visual representation of the solution of the system with Gaussian elimination.
     */
    public void solveUsingGauusMethod() throws CloneNotSupportedException {
        LinearSystem newSystem = this;

        LinearSystem solvedSystem = LinearSystemActions.solveUsingGaussMethod(newSystem);
        System.out.println("Матрица системы, приведённая к треугольному виду:");
        System.out.println(solvedSystem);

        OneColMatrix solution = LinearSystemActions.backSubstitution(solvedSystem);
        System.out.println("Корни системы:");
        for (int i = 0; i <= solution.getSize(); i++) {
            System.out.println("x" + i + " = " + solution.getElement(i));
        }

        OneColMatrix accuracy = LinearSystemActions.checkAccuracy(newSystem, solution);
        System.out.println("\nРазница между исходными данными и данными, полученными в результате подстановки корней:");
        System.out.println(accuracy);
        
        MatrixActions.writeToFile(solution, "GaussSolution.txt");
    }
    
   /**
    * Visual representation of the solution of the system with Jordan method. 
    */
    public void solveUsingJordanMethod() throws CloneNotSupportedException {
        LinearSystem newSystem = this;
        
        LinearSystem solvedSystem = LinearSystemActions.solveUsingJordanMethod(newSystem);
        System.out.println("Матрица системы, приведённая к диагональному виду:");
        System.out.println(solvedSystem);
        
        OneColMatrix solution = LinearSystemActions.backSubstitution(solvedSystem);
        System.out.println("Корни системы:");
        for (int i = 0; i <= solution.getSize(); i++) {
            System.out.println("x" + i + " = " + solution.getElement(i));
        }
        
        OneColMatrix accuracy = LinearSystemActions.checkAccuracy(newSystem, solution);
        System.out.println("\nРазница между исходными данными и данными, полученными в результате подстановки корней:");
        System.out.println(accuracy);
        
        MatrixActions.writeToFile(solution, "JordanSolution.txt");
    }

    @Override
    protected LinearSystem clone() throws CloneNotSupportedException {
        SquareMatrix coefficients = new SquareMatrix(this.coefficients.getElements());
        OneColMatrix constantTerms = new OneColMatrix(this.constantTerms.getElements());
        
        LinearSystem newSystem = new LinearSystem(coefficients, constantTerms);
        
        return newSystem;
    }
    
    

}
