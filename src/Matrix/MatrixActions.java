/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Администратор
 */
public class MatrixActions {
    public static SquareMatrix swapLine(SquareMatrix matrix, int swopLine, int targetLine) {
        float temp;
        int cols = matrix.getSize().getCols();
        int rows = matrix.getSize().getRows();
        
        for (int i = 0; i <= cols; i++) {
            temp = matrix.getElement(swopLine, i);
            matrix.setElement(swopLine, i, matrix.getElement(targetLine, i));
            matrix.setElement(targetLine, i, temp);
        }
        
        return matrix;
    }
    
    public static OneColMatrix swapLine(OneColMatrix matrix, int swopLine, int targetLine) {
        float temp;
        
        temp = matrix.getElement(targetLine);
        matrix.setElement(targetLine, matrix.getElement(swopLine));
        matrix.setElement(swopLine, temp);
        
        return matrix;
    }
    
    public static int maxLentgh(SquareMatrix matrix) {
        int maxLength = 0;
        for (int i = 0; i <= matrix.getSize().getRows(); i++) {
            for (int j = 0; j <= matrix.getSize().getCols(); j++) {
               String temp ="" + matrix.getElement(i, j);
               if (temp.length() > maxLength) {
                   maxLength = temp.length();
               }
            }
        }
        return maxLength;
    }
    
    public static int maxLength (OneColMatrix matrix) {
        int maxLength = 0;
        for (int i =0; i <= matrix.getSize(); i++) {
            String temp = "" + matrix.getElement(i);
            if (temp.length() > maxLength) {
                maxLength = temp.length();
            }
        }
        return maxLength;
    }
    
    public static void writeToFile(OneColMatrix solution, String file) {
        try {
            FileWriter writer = new FileWriter(new File(file));

            for (int i = 0; i < solution.getSize(); i++) {
                writer.write("x"+ i + " = " +solution.getElement(i) + "\r\n");
            }
            writer.flush();
            writer.close();

        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
    
}
