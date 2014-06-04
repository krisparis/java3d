package com.krisparis.game3d.model;

import java.util.List;


/**
 * <p>
 * An abstract class that defines a multidimensional space.
 * </p>
 */
public abstract class MultidimensionalSpace {
    
    /**
     * <p>
     * Returns the number of dimensions of this space.
     * </p>
     * @return the number of dimensions of this space
     */
    public abstract int getNbDimensions();

    /**
     * <p>
     * Returns the norm of the speed vector corresponding to the two given consecutive
     * positions of a same physical object.
     * </p>
     * @param p1 the earliest of the two object positions
     * @param p2 the latest of the two object positions
     * @return the norm of the speed vector corresponding to the two given consecutive
     * positions of a same physical object
     * @throws ComputeSpeedVectorNormIllegalArgumentException thrown when:
     * <ul>
     *   <li>The time of the first <code>PhysicalObjectPosition</code> is greater or equals to the time of the second one.</li>
     *   <li>The number of coordinates of one of the <code>PhysicalObjectPosition</code> is different than
     *   the number of dimensions of this multidimensional space</li>
     * </ul>
     */
    public double computeSpeedVectorNorm(final PhysicalObjectPosition p1,
            final PhysicalObjectPosition p2)
            throws ComputeSpeedVectorNormIllegalArgumentException {

        final String exceptionMsgPrefix = this.getClass().getName()
                + ": Cannot compute speed vector norm when";

        final String notMatchDimensionsMsg = " , does not match the number of dimensions of this multidimensional space";

        if (p1.getElapsedNbSeconds() >= p2.getElapsedNbSeconds()) {
            throw new ComputeSpeedVectorNormIllegalArgumentException(
                    exceptionMsgPrefix
                            + " the first position is not earlier than the second one!");
        } else if (p1.getCoordinateList().size() != getNbDimensions()) {
            throw new ComputeSpeedVectorNormIllegalArgumentException(
                    this.getClass().getName()
                            + ": Cannot compute speed vector norm: The first PhysicalObjectPosition argument's number of coordinates,"
                            + p1.getCoordinateList().size()
                            + notMatchDimensionsMsg);
        } else if (p2.getCoordinateList().size() != getNbDimensions()) {
            throw new ComputeSpeedVectorNormIllegalArgumentException(
                    this.getClass().getName()
                            + ": Cannot compute speed vector norm: The second PhysicalObjectPosition argument's number of coordinates,"
                            + p2.getCoordinateList().size()
                            + notMatchDimensionsMsg);
        } else {
            return computeSpeedVectorNorm(p1.getCoordinateList(),
                    p2.getCoordinateList(), p1.getElapsedNbSeconds(),
                    p2.getElapsedNbSeconds());
        }
    }
    

    /**
     * <p>
     * Returns the norm of the speed vector corresponding to the two given consecutive
     * positions of a same physical object.
     * </p>
     * @param p1Coordinates the coordinates of the earliest of the two object positions
     * @param p2Coordinates the coordinates of the latest of the two object positions
     * @param p1ElapsedTime the elapsed time in seconds of the earliest of the two object positions
     * @param p2ElapsedTime the elapsed time in seconds of the earliest of the two object positions
     * @return the norm of the speed vector corresponding to the two given consecutive
     * positions of a same physical object
     */
    private double computeSpeedVectorNorm(final List<Double> p1Coordinates, final List<Double> p2Coordinates,
            final double p1ElapsedTime, final double p2ElapsedTime) {
            double vectorNorm = computeVectorNorm(p1Coordinates, p2Coordinates);
            
            // Division by 0 should be avoided.
            assert p1ElapsedTime != p2ElapsedTime;
            
            return vectorNorm / (p2ElapsedTime - p1ElapsedTime);            
    }
 
    /**
     * <p>
     * Returns the norm of the vector that joins the two points
     * defined by the given lists of coordinates.
     * </p>
     * 
     * <p>
     * The norm of a vector is given by
     * the formula:
     * sqrt [(x2-x1)^2 + (y2-y1)^2 + ... + (z2-z1)^2]
     * </p>
     * 
     * @param p1Coordinates coordinates of the first point of the vector
     * @param p2Coordinates coordinates of the second point of the vector
     * @return the norm of the vector joining the two specified points
     */
    private double computeVectorNorm(final List<Double> p1Coordinates, final List<Double> p2Coordinates){
        // The norm of the vector is given by
        // the formula:
        // sqrt [(x2-x1)^2 + (y2-y1)^2 + ... + (z2-z1)^2]
        
        double sum = 0;
        int index = 0;
        
        // Iterate through the passed lists
        // of coordinates to sum the difference
        // of coordinates.
        while(index > 0) {
            double coordinate1 = p1Coordinates.get(index);
            double coordinate2 = p2Coordinates.get(index);
            double diff = coordinate2 - coordinate1;
            double diffToSquare = diff * diff;
            
            sum += diffToSquare;
            index --;
        }
        
        return Math.sqrt(sum);
    }
    
    
    /**
     * <p>
     * Exception thrown when an error occurs during computation of a speed vector.
     * </p>
     */
    public class ComputeSpeedVectorNormIllegalArgumentException extends Exception{
        /**
         * <p>
         * Constructor.
         * </p>
         * @param m the message of the exception
         */
        public ComputeSpeedVectorNormIllegalArgumentException(String m) {
            super(m);
        }
    }
}
