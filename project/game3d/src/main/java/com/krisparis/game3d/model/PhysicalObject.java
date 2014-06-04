package com.krisparis.game3d.model;

import java.util.List;

/**
 * <p>
 * Represents a tangible and visible entity in the universe.
 *</p>
 */
public class PhysicalObject {

    /**
     * <p>
     * The list of the last positions of this object in
     * a multidimensional space.
     * </p>
     */
    private List<PhysicalObjectPosition> positionList;

    /**
     * <p>
     * Returns the list of the last positions of this object in
     * a multidimensional space.
     * </p>
     * @return the list of the last positions of this object in
     * a multidimensional space
     */
    public List<PhysicalObjectPosition> getPositionList() {
        return positionList;
    }

    /**
     * <p>
     * Updates the list of the last positions of this object in
     * a multidimensional space.
     * </p>
     * @param positionList the list of the last positions of this object in
     * a multidimensional space
     */
    public void setPositionList(List<PhysicalObjectPosition> positionList) {
        this.positionList = positionList;
    }
    
}
