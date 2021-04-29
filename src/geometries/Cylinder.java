package geometries;

import elements.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class Cylinder extends Tube
 * Cylinder is afinite Tube with a certain _height
 */
public class Cylinder extends Tube {
    /**
     *height-height of the cylinder
     */
    private double _height;

    /**
     * Cylinder constructor
     *
     * @param _radius רradius of the Cylinder
     * @param _ray    direction and reference point  of the cylinder
     * @param _height height of the cylinder (from the referenced point)
     */
    public Cylinder(double _radius, Ray _ray, double _height) {
      this(Color.BLACK,new Material(0,0,0),_radius,_ray,_height);

    }

    public Cylinder(Color color, Material material,double _radius, Ray _ray, double _height) {
        super(color,material,_radius, _ray);
        _emission=color;
        _material=material;
        this._height = _height;
        System.out.println("HALELI"+getEmissionLight().getColor());
    }

    public Cylinder(Cylinder cylinder) {
        this(cylinder._emission,cylinder._material,cylinder._radius,cylinder._ray,cylinder._height);
    }
    //************get**********

    public double get_height() {
        return _height;
    }

    /**
     * @param point point to calculate the normal
     * @return normal
     * @author Dan Zilberstein
     */
    @Override
    public Vector getNormal(Point3D point) {
        Point3D o = _ray.getPoint();
        Vector v = _ray.getDirection();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(point.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        o = o.add(v.scale(t));
        return point.subtract(o).normalize();
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
                List<GeoPoint> intersections = super.findIntersections(ray);
                List<GeoPoint> result = new LinkedList<>();
                if (intersections != null) {
                    for (GeoPoint geoPoint : intersections)
                    {
                        result.add(new GeoPoint(this, geoPoint.getPoint()));
                    }
                    return result;
        }
        return null;
    }
}