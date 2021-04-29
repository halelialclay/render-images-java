package unitTests;

import geometries.*;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * class GeometriesTest
 * @author marom  haleli
 */
public class GeometriesTest
{

    @Test
    public void testFindIntersections() {

        Geometries geometries=new Geometries();
        Point3D p1=new Point3D(-1,0,0);
        Vector v1=new Vector(1,1,0);
        Ray ray=new Ray(p1,v1.normalize());

        // =============== Boundary Values Tests ==================
        // TC01:there is no geometries in the list (0 points)
        List<Intersectable.GeoPoint> result=geometries.findIntersections(ray);
        assertEquals("Wrong number of points", null,result);

        //TC02: The ray has no interactions point with any geometries in the array
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));
        Plane plane=new Plane(new Point3D(2,2,2),new Point3D(1,2,3),p1);
        Triangle triangle=new Triangle(new Point3D(1,-1,0),new Point3D(3,2,2),p1);
        geometries.add(sphere, plane,triangle);
        result=geometries.findIntersections(ray);
        assertEquals("Wrong number of points", null,result);
        //TC03:The ray has a interactions point with only one shape

        Plane plane1=new Plane(new Point3D(3,3,2),new Point3D(2,3,3),p1.add(new Vector(1,1,0)));
        geometries.add( plane1);
        result=geometries.findIntersections(ray);
        assertEquals("Wrong number of points", 1,result.size());



        //TC04 :All geometries have a cut point with the ray
        Sphere sphere1=new Sphere(2d,new Point3D(-1,0,0));
        Triangle triangle1=new Triangle(new Point3D(-11,13,7),new Point3D(1,1,6),new Point3D(1,1,1));
         Geometries geometries1=new Geometries(sphere1,plane1,triangle1);
        result=geometries1.findIntersections(ray);
        assertEquals("Wrong number of points", 3,result.size());


        // ============ Equivalence Partitions Tests ==============

        //TC05:The ray has a interactions point with some of the geometries in the array
        geometries.add(sphere1);
        result=geometries.findIntersections(ray);
        assertEquals("Wrong number of points", 2,result.size());





    }

}