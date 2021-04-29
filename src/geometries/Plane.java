package geometries;

import elements.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class Plane extends Geometry- has point and normal
 * @author haleli marom
 */
public class Plane extends Geometry {
// point on the plan
    Point3D _p;
// vector that vertical to the plan
    Vector _normal;
    //***************constructors*********************

    /**
     *
     * @param emissionLight -the emission of the plan
     * @param material- the material of the plan
     * 3 points that construct a plane
     * @param p1-  point on the plan.
     * @param p2 -point on the plan.
     * @param p3 -point on the plan.
     */
    public Plane(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight, material);

        _p = new Point3D(p1);

        Vector U = new Vector(p1, p2);
        Vector V = new Vector(p1, p3);
        Vector N = U.crossProduct(V);
        N.normalize();

        _normal = N;
//        _normal = N.scale(-1);

    }

    /**
     * @param emissionLight-the emission light of the plane.
     * @param material - the material of the plane.
     * @param p1 - one point on the plane( state the position)
     * @param normal -the normal to the plane (vertical to the plane, with length 1)
     */
    public Plane(Color emissionLight, Material material, Point3D p1,Vector normal) {
        super(emissionLight, material);

        _p = new Point3D(p1);
        _normal = normal.normalized();


    }

    /**
     * Uses the previous constructor
     * material default value= (0,0,0)
     * @param emissionLight-the emission of the plan
     *3 points in the plan
     * @param p1- point of the plane
     * @param p2 -point of the plane
     * @param p3- point of the plane
     */
    public Plane(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        this(emissionLight, new Material(0, 0, 0), p1, p2, p3);
    }

    /**
     *  Uses the previous constructor
     *  emission-black
     *  material= (0,0,0)
     *  3 points in the plan
     * @param p1- point of the plane
     * @param p2 -point of the plane
     * @param p3- point of the plane
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        this(Color.BLACK, p1, p2, p3);
    }

    /**
     * emission-black
     *  material= (0,0,0)
     * @param _p the point of the plan
     * @param _normal - vector that vertical to the plan
     */

    public Plane(Point3D _p, Vector _normal) {
        super(Color.BLACK, new Material(0, 0, 0));

        this._p = new Point3D(_p);
        this._normal = new Vector(_normal);
    }
    //**********getters***************

    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    //because polygon needs geNormal without parameter
    public Vector getNormal() {
        return getNormal(null);
    }

    public Point3D get_p() {
        return _p;
    }

    /**
     * find point Intersection of the ray with the plane
     * @param ray ray pointing toward a Gepmtry
     * @return point Intersection
     */
    @Override
    public List<Intersectable.GeoPoint> findIntersections(Ray ray) {
        Vector p0Q;
        try {
            p0Q = _p.subtract(ray.getPoint());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from point Q - no intersections
        }

        double nv = _normal.dotProduct(ray.getDirection());
        if (isZero(nv)) // ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(_normal.dotProduct(p0Q) / nv);

        if (t <= 0) {
            return null;
        }

        GeoPoint geo = new GeoPoint(this, ray.getTargetPoint(t));
        return List.of(geo);
    }


}