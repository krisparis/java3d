package com.krisparis.game3d.model;

import java.util.List;

/**
 * <p>
 * Represents the position of a physical object
 * at a specific time in a certain  multidimensional space.
 * </p>
 */
public class PhysicalObjectPosition {

    /**
     * <p>
     * The list of coordinates of the physical object
     * in the multidimensional space.
     * </p>
     */
    private List<Double> coordinateList;

    /**
     * <p>
     * The physical object corresponding to this position.
     * </p>
     */
    private PhysicalObject object;

    /**
     * <p>
     * The space in which this position is determined.
     * </p>
     */    
    private MultidimensionalSpace space;
    
    /**
     * <p>
     * The elapsed number of milliseconds at the time
     * this position was captured.
     * </p>
     * 
     */
    private double elapsedNbSeconds;
    
    /**
     * <p>
     * Returns the list of coordinates of
     * this position.
     * </p>
     * 
     * @return the list of coordinates of
     * this position
     */    
    public List<Double> getCoordinateList() {
        return coordinateList;
    }

    /**
     * <p>
     * Sets the list of coordinates of
     * this position.
     * </p>
     * 
     * @param coordinateList the list of coordinates
     * of this position
     */        
    public void setCoordinateList(final List<Double> coordinateList) {
        this.coordinateList = coordinateList;
    }

    /**
     * <p>
     * Returns elapsed number of seconds
     * at the time this position was captured.
     * </p>
     * 
     * @return the elapsed number of seconds
     * at the time this position was captured
     */
    public double getElapsedNbSeconds() {
        return elapsedNbSeconds;
    }

    /**
     * <p>
     * Sets the elapsed number of seconds
     * at the time this position was captured.
     * </p>
     * 
     * @param elapsedNbSeconds the elapsed number of seconds
     * at the time this position was captured
     */
    public void setElapsedNbSeconds(final double elapsedNbSeconds) {
        this.elapsedNbSeconds = elapsedNbSeconds;
    }

    /**
     * <p>
     * Returns the physical object corresponding to
     * this position.
     * </p>
     * 
     * @return the physical object corresponding to
     * this position
     */
    public PhysicalObject getObject() {
        return object;
    }

    /**
     * <p>
     * Sets the physical object corresponding to
     * this position.
     * </p>
     * 
     * @return the physical object corresponding to
     * this position
     */
    public void setObject(final PhysicalObject object) {
        this.object = object;
    }    
    
    /**
     * <p>
     * Returns the multidimensional space associated with
     * this position.
     * </p>
     * 
     * @return the multidimensional space associated with
     * this position
     */    
    public MultidimensionalSpace getSpace() {
        return space;
    }

    /**
     * <p>
     * Sets the multidimensional space associated with
     * this position.
     * </p>
     * 
     * @param space the multidimensional space associated with
     * this position
     */        
    public void setSpace(final MultidimensionalSpace space) {
        this.space = space;
    }    
    
}
