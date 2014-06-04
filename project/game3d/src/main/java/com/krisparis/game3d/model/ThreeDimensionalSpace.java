package com.krisparis.game3d.model;

/**
 * <p>
 * A subclass of {@link MultidimensionalSpace}
 * where the number of dimensions is set to three. 
 * </p>
 */
public class ThreeDimensionalSpace extends MultidimensionalSpace {

    /**
     * <p>
     * The number of dimensions of this space.
     * </p>
     */
    private static final int NB_DIMENSIONS = 3;
    
    @Override
    public final int getNbDimensions() {
        return NB_DIMENSIONS;
    }
    
    
}
