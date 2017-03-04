/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

/**
 * Square matrix is a matrix which has equal number of rows and columns.
 * 
 */
public class SquareMatrix implements Cloneable {
    /**
     * Contains elements of matrix.
     */
    private float[][] elements;

    public float[][] getElements() {
        return elements;
    }
    
    /**
     * Constructor without parameters.
     */
    public SquareMatrix() {
        
    }
    
    /**
     * Creates a matrix from array of double.
     * @param elements Two-dimensional array of the elements of the matrix.
     * 
     */
    public SquareMatrix(float[][] elements) {
        this.elements = elements;
    }
    
    /**
     * Allows to know the number of columns or rows.
     * @return Returns the number of columns or rows.
     */
    
    public MatrixSize getSize() {
        MatrixSize size = new MatrixSize(this.elements.length - 1, this.elements.length - 1);
        return size;
    }
    
    /**
     * Provides the access to the element with certain row and column indexes.
     * @param row The index of the row.
     * @param col The index of the columns
     * @return Returns the element of the matrix from cell with certain row and
     * colunm indexes.
     */
    public float getElement(int row, int col) {
        return this.elements[row][col];
    }
    
    /**
     * Sets the element of the matrix to cell with defined row and column
     * indexes.
     * @param row The index of the row.
     * @param col The index of the column.
     * @param value The value which is to be set.
     */
    public void setElement(int row, int col, float value) {
        this.elements[row][col] = value;
    }
    
    /**
     * Visual representation of the square matrix.
     * @return Returns a visual representation of the square matrix.
     */
    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i <= this.getSize().getRows(); i++) {
            for (int j = 0; j <= this.getSize().getCols(); j++) {
                sb.append(this.getElement(i, j));
                sb.append("   ");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

//    @Override
//    protected SquareMatrix clone() throws CloneNotSupportedException {
//        SquareMatrix newMatrix = new SquareMatrix(this.elements);
//        return newMatrix;
//    }
    
    
    
    /**
     * Contains the number of rows and columns of the matrix.
     */
    public class MatrixSize {
        /**
         * Number of rows of the matrix.
         */
        private int rows;
        /**
         * Number of columnts of the matrix.
         */
        private int columns;
        
        /**
         * Constructor without parameters.
         */
        public MatrixSize() {
            
        }
        
        /**
         * Constructor with parrameters setting rows and columns number.
         * @param rows The number of rows.
         * @param columns The number of columns
         */
        public MatrixSize(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
        }
        
        /**
         * 
         * @return Returns the number of rows contained in the matrix.
         */
        public int getRows() {
            return rows;
        }
        
        /**
         * 
         * @return Returns the number of columns contained in the matrix
         */
        public int getCols() {
            return columns;
        }
    }
    
}
