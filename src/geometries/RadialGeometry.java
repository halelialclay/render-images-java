package geometries;

import elements.Material;
import primitives.Color;

import static primitives.Util.isZero;

/**
 * RadialGeometry is an abstract class that defines
 * all radial geometries.
 * <p>
 *     radius- the radius of the radial geometry.
 * </p>
 */
public abstract class RadialGeometry extends Geometry {
    protected double _radius;

    /*
     * constructor for a new extended  RadialGeometry object.
     * @param emissionLight
     * @param radius   the radius of the RadialGeometry
     * @param material the material of the RadialGeometry
     */
    public RadialGeometry(Color emissionLight, double radius, Material material) {
        super(emissionLight, material);
        setRadius(radius);
    }

    /**
     * constructor
     *
     * @param emissionLight-the emissionLight of the RadialGeometry
     * @param radius-the radius of the RadialGeometry

     */
    public RadialGeometry(Color emissionLight, double radius) {
        super(emissionLight);
        setRadius(radius);
    }

    /**
     * constructor
     * emissionLight-black(default)
     * @param radius-the radius of the RadialGeometry
     */
    public RadialGeometry(double radius) {
        super();
        setRadius(radius);
    }

    /**
     * copy constructor
     * @param other-RadialGeometry to copy to a new RadialGeometry
     */
    public RadialGeometry(RadialGeometry other) {
        super(other._emission, other._material);
        setRadius(other._radius);
    }

    /**
     * get Radius
     * @return the radius of geometry
     */


    public double getRadius() {
        return _radius;
    }

    /**
     * set Radius
     * @param radius of the radial geometry
     * @throws IllegalArgumentException in case of negative or zero radius from RadialGeometry constructor
     */

    public void setRadius(double radius) {

        if (isZero(radius) || (radius < 0.0))
            throw new IllegalArgumentException("radius " + radius + " is not valid");
        this._radius = radius;
    }


}