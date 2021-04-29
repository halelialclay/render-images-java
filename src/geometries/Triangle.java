package geometries;

import elements.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * class Triangle extends Polygon- polygon with 3 vertexes.
 */
public class Triangle extends Polygon {
    /**
     * constructor
     * @param emissionLight - the emissionLight of the Triangle
     * @param material- the material of the Triangle
     * 3 points in the Triangle
     * @param p1 -vertex point of the triangle.
     * @param p2 -vertex point of the triangle.
     * @param p3 -vertex point of the triangle.
     */
    public Triangle(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,material,p1,p2,p3);
    }

    /**
     * default material=(0,0,0)
     * @param emissionLight- the emissionLight of the Triangle
     * 3 points in the Triangle
     * @param p1 -vertex point of the triangle.
     * @param p2 -vertex point of the triangle.
     * @param p3 -vertex point of the triangle.
     */
    public Triangle(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,p1, p2, p3);
    }

    /**
     * default material=(0,0,0)
     ** default emissionLight=black
     * 3 points in the Triangle
     * @param p1 -vertex point of the triangle.
     * @param p2 -vertex point of the triangle.
     * @param p3 -vertex point of the triangle.
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    /**
     * find Intersections of ray and the Triangle
     * @param ray ray pointing toward a Gepmtry
     * @return List of GeoPoint Intersections on the triangel
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> planeIntersections = _plane.findIntersections(ray);
        if (planeIntersections == null) return null;

        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            //for GeoPoint
            List<GeoPoint> result = new LinkedList<>();
            for (GeoPoint geo : planeIntersections) {
                result.add(new GeoPoint(this, geo.getPoint()));
            }
            return result;
        }

        return null;

    }
    /**
     * @return  string of the Triangle
     */
    @Override
    public String toString() {
        String result = "";
        for (Point3D p : _vertices) {
            result += p.toString();
        }
        return result;
    }
}