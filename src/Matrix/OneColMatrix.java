/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

/**
 * One column matrix is a matrix which contains 1 column.
 * 
 */
public class OneColMatrix implements Cloneable {
    /**
     * Array of double which contains the elemens of the matrix.
     */
    private float[] elements;

    public float[] getElements() {
        return elements;
    }
    
    
    
    /**
     * Constructor without parameters
     */
    public OneColMatrix() {
        
    }
    
    /**
     * Creates an empty (filled with 0s) matrix with defined size.
     * @param size The size of the matrix
     */
    public OneColMatrix(int size) {
        this.elements = new float[size + 1];
    }
    
    
    /**
     * Creates a matrix from the one-dimensional array of double.
     * @param elements The elements of the matrix.
     */
    public OneColMatrix(float[] elements) {
        this.elements = elements;
    }
    
    /**
     * Provides the access to the element with a certain index.
     * @param index The index of the element.
     * @return Returns an element with a certain index.
     */
    public float getElement(int index) {
        return this.elements[index];
    }
    
    /**
     * Sets a new element to the cell with a certain index.
     * @param index The index of the cell.
     * @param value The value of the set element.
     */
    public void setElement(int index, float value) {
        this.elements[index] = value;
    }
    
    /**
     * Allows to know the size of the matrix.
     * @return Returns the size of the matrix.
     */
    public int getSize() {
        return this.elements.length - 1;
    }
    
    /**
     * Visual representation of the one-column matrix.
     * @return Returns a visual representation of the one-column matrix.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i <= this.getSize(); i++) {
            sb.append(this.getElement(i));
            sb.append("\n");
        }
        return sb.toString();
    }

//    @Override
//    protected OneColMatrix clone() throws CloneNotSupportedException {
//        OneColMatrix newMatrix = new OneColMatrix(this.elements);
//        return newMatrix;
//    }
    
    
    
}
