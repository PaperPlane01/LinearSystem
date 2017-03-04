/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinearSystem;

import Matrix.MatrixActions;
import Matrix.OneColMatrix;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Actions performed with linear system
 */
public class LinearSystemActions {

    /**
     * Creates a linear system from the file.
     *
     * @param path The path to the file.
     * @return Returns a linear system based on the data written in the file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static LinearSystem readFromFile(String path) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        List<String> lines = new ArrayList();

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        float[][] coefficients = new float[lines.size()][lines.size()];
        float[] constantTerms = new float[lines.size()];

        for (int i = 0; i < lines.size(); i++) {
            String temp[] = lines.get(i).trim().split("\\s+");

            for (int j = 0; j < temp.length; j++) {
                if (j != temp.length - 1) {
                    coefficients[i][j] = Float.parseFloat(temp[j]);
                } else {
                    constantTerms[i] = Float.parseFloat(temp[j]);
                }

            }
        }

        LinearSystem result = new LinearSystem(coefficients, constantTerms);
        return result;

    }

    /**
     * Converts the system to triangular form using Gaussian elimination.
     *
     * @param mySystem The linear system which is to be transformed.
     * @return Returns triangular form of the linear system.
     */
    public static LinearSystem solveUsingGaussMethod(LinearSystem mySystem) throws CloneNotSupportedException {
        int order = mySystem.getSize();
        float multiplicator;
        LinearSystem solution = mySystem.clone();

        for (int k = 0; k <= order; k++) {
            for (int j = k + 1; j <= order; j++) {
                if (solution.getCoefficients().getElement(k, k) == 0) {
                    int n = k;
                    while (solution.getCoefficients().getElement(n, n) == 0) {
                        n++;
                    }
                    MatrixActions.swapLine(solution.getCoefficients(), k, n);
                    MatrixActions.swapLine(solution.getConstantTerms(), k, n);
                }
                multiplicator = solution.getCoefficients().getElement(j, k) / 
                        solution.getCoefficients().getElement(k, k);
                for (int i = k; i <= order; i++) {
                    float newElement = solution.getCoefficients().getElement(j, i)
                            - multiplicator * solution.getCoefficients().getElement(k, i);
                    solution.getCoefficients().setElement(j, i, newElement);
                }

                float newConstantTerm = solution.getConstantTerms().getElement(j)
                        - multiplicator * solution.getConstantTerms().getElement(k);
                solution.getConstantTerms().setElement(j, newConstantTerm);
            }
        }

        return solution;
    }

    /**
     * Converts system to diagonal form using Jordan method.
     * @param mySystem Linear system which is to be converted.
     * @return
     * @throws CloneNotSupportedException 
     */
    public static LinearSystem solveUsingJordanMethod(LinearSystem mySystem) throws CloneNotSupportedException {
        int order = mySystem.getSize();
        float multiplicator;
        LinearSystem solution = mySystem.clone();

        for (int k = 0; k <= order; k++) {
            for (int j = k + 1; j <= order; j++) {
                if (solution.getCoefficients().getElement(k, k) == 0) {
                    int n = k;
                    while (solution.getCoefficients().getElement(n, n) == 0) {
                        n++;
                    }
                    MatrixActions.swapLine(solution.getCoefficients(), k, n);
                    MatrixActions.swapLine(solution.getConstantTerms(), k, n);
                }
                multiplicator = solution.getCoefficients().getElement(j, k) 
                        / solution.getCoefficients().getElement(k, k);
                for (int i = k; i <= order; i++) {
                    float newElement = solution.getCoefficients().getElement(j, i)
                            - multiplicator * solution.getCoefficients().getElement(k, i);
                    solution.getCoefficients().setElement(j, i, newElement);
                }

                float newConstantTerm = solution.getConstantTerms().getElement(j)
                        - multiplicator * solution.getConstantTerms().getElement(k);
                solution.getConstantTerms().setElement(j, newConstantTerm);
            }
        }
        
        for (int k = order; k >= 0; k--) {
            for (int j = k - 1; j >= 0; j--) {
                multiplicator = solution.getCoefficients().getElement(j, k) 
                        / solution.getCoefficients().getElement(k, k);
                for (int i = k; i >= 0; i--) {
                    float newCoefficient = solution.getCoefficients().getElement(j, i)
                            - multiplicator * solution.getCoefficients().getElement(k, i);
                    solution.getCoefficients().setElement(j, i, newCoefficient);
                }

                float newConstantTerm = solution.getConstantTerms().getElement(j)
                        - multiplicator * solution.getConstantTerms().getElement(k);
                solution.getConstantTerms().setElement(j, newConstantTerm);
            }
        }
        
        for (int k = 0; k <= order; k++) {
            float newConstantTerm = solution.getConstantTerms().getElement(k) 
                    / solution.getCoefficients().getElement(k, k);
            solution.getConstantTerms().setElement(k, newConstantTerm);
            float newCoefficient = solution.getCoefficients().getElement(k, k) 
                    / solution.getCoefficients().getElement(k, k);
            solution.getCoefficients().setElement(k, k, newCoefficient);
        }
        
        return solution;

    }

    /**
     * Calculates the roots of the linear system.
     *
     * @param solvedSystem The linear system which had been transformed to the
     * triangular form.
     * @return Returns roots of the linear system.
     */
    public static OneColMatrix backSubstitution(LinearSystem solvedSystem) {
        int size = solvedSystem.getSize();
        float[] roots = new float[size + 1];
        float sum;

        for (int i = size; i >= 0; i--) {
            float tmp = 0;
            for (int j = i + 1; j < size + 1; j++) {
                sum = solvedSystem.getCoefficients().getElement(i, j)
                        * roots[j];
                tmp += sum;
            }
            roots[i] = (solvedSystem.getConstantTerms().getElement(i) - tmp)
                    / solvedSystem.getCoefficients().getElement(i, i);
        }

        OneColMatrix solution = new OneColMatrix(roots);
        return solution;
    }

    /**
     * Checks accuracy of the solution.
     *
     * @param checkedSystem The original system.
     * @param roots Calculated roots of the system.
     * @return Returns the result of comparing the original constant terms
     * (right sides of equations) of equation and consant term calculated using
     * calculated roots of the system.
     */
    public static OneColMatrix checkAccuracy(LinearSystem checkedSystem,
            OneColMatrix roots) {
        int size = roots.getSize();
        float sum;
        OneColMatrix result = new OneColMatrix(size);

        for (int i = 0; i <= size; i++) {
            float tmp = 0;
            for (int j = 0; j <= size; j++) {
                sum = checkedSystem.getCoefficients().getElement(i, j)
                        * roots.getElement(j);
                tmp += sum;
            }
            float checked = checkedSystem.getConstantTerms().getElement(i)
                    - tmp;
            result.setElement(i, checked);
        }
        return result;
    }
}
