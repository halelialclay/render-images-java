package geometries;

import elements.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;

/**
 *  class Sphere extends RadialGeometry
 */
public class Sphere extends RadialGeometry {
    /**
     * The center of the sphere
     */
    private final Point3D _center;

    /**
     * constructor for a new sphere object.
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     * @param material - the material of the sphere.
     * @param emissionLight - the emission light of the sphere.
     * @throws IllegalArgumentException in case of negative or zero radius from RadialGeometry constructor
     */

    public Sphere(Color emissionLight, Material material, double radius, Point3D center) {
        super(emissionLight, radius, material);
        this._center = new Point3D(center);
    }

    /**
     * constructor for a new sphere object.
     * default Material=(0,0,0)
     * @param emissionLight-the emissionLight of the sphere
     * @param radius-the radius of the sphere
     * @param center-the center of the sphere
     * @throws IllegalArgumentException in case of negative or zero radius from RadialGeometry constructor
     */

    public Sphere(Color emissionLight, double radius, Point3D center) {
        this(emissionLight,new Material(0,0,0),radius,center);
    }
    /**
     * constructor for a new sphere object.
     * default Material=(0,0,0)
     * default emissionLight= black
     * @param radius-the radius of the sphere
     * @param center-the center of the sphere
     * @throws IllegalArgumentException in case of negative or zero radius from RadialGeometry constructor
     */
    public Sphere(double radius, Point3D center) {
        this(Color.BLACK,new Material(0,0,0),radius,center);
    }

    /**
     * getter for the center property
     *
     * @return the center of the sphere
     */
    public Point3D getCenter() {
        return new Point3D(_center);
    }


    /**
     * get the normal to this sphere in a given point
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector normal = point.subtract(_center);
        return normal.normalize();
    }

    /**
     * find Intersections of ray and the sphere
     * @param ray ray pointing toward a Gepmtry
     * @return List of GeoPoint Intersections on the sphere
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(new GeoPoint(this, (ray.getTargetPoint(this._radius))));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(this._radius * this._radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) {
            return List.of(
                    new GeoPoint(this,(ray.getTargetPoint(t1)))
                    ,new GeoPoint(this,(ray.getTargetPoint(t2)))); //P1 , P2
        }
        if (t1 > 0)
            return List.of(new GeoPoint(this,(ray.getTargetPoint(t1))));
        else
            return List.of(new GeoPoint(this,(ray.getTargetPoint(t2))));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Objects.equals(_center, sphere._center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_center);
    }

    /**
     * @return string of the params Sphere
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                ", _emission=" + _emission +
                ", _material=" + _material +
                '}';
    }
}