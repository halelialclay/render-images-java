package geometries;

import elements.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * abstract class Geometry implements Intersectable
 * interface Geometry is the basic interface for all geometric objects
 * who are implementing getNormal method.
 *
 * @author haleli,marom
 */
public abstract class Geometry implements Intersectable {
    /**
     * _emission-the color of geometry
     */
    protected Color _emission;
    /**
     * _material-The material from which the geometry is made
     */
    protected Material _material;

    /**
     * constrictor
     * @param emission of the geometry
     * @param material of the geometry
     */
    public Geometry(Color emission, Material material) {
        this._emission = new Color(emission);
        //this._material = new Material(material);
        this._material = material;
    }

    /**
     * constrictor
     * @param _emission
     * material with default parameter (0,0,0)
     */
    public Geometry(Color _emission) {
        this(_emission, new Material(0d, 0d, 0));
    }

    /**
     * default constrictor
     *  material with default parameter (0,0,0)
     *  emission color blak
     */
    public Geometry() {
        this(Color.BLACK);
    }

//**********get************
    public Color getEmissionLight() {
        return (_emission);
    }

    public Color getEmissionLight(GeoPoint point) {
        return getEmissionLight();
    }

    public Material getMaterial() {

        return _material;
    }

    /**
     *
     * @param p -point on the geometry
     * @return the normal to the geometry  in the point p
     */
    abstract public Vector getNormal(Point3D p);
}