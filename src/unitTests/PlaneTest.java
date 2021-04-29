package unitTests;

import geometries.Intersectable;
import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for primitives.Vector class
 * @author marome haleli
 */

public class PlaneTest {

    @Test
    public void getNormal()
    {
        Plane plane = new Plane(
                new Point3D(1,2,3),
                new Point3D(1,1,1),
                new Point3D(0,0,1));
        assertEquals(new Vector(-2d/3,2d/3,-1d/3), plane.getNormal(new Point3D(1,2,3)));
    }

    @Test
    public void testConstructor()
    {
        try {
        Plane plane = new Plane(
                new Point3D(1,1,1),
                new Point3D(1,1,1),
                new Point3D(0,0,1));
            fail("Failed constructing a correct Plane");
        }
        catch (IllegalArgumentException ex)
        {

        }

        try {
            Plane plane = new Plane(
                    new Point3D(2,2,2),
                    new Point3D(1,1,1),
                    new Point3D(1,1,1));
            fail("Failed constructing a correct Plane");
        }
        catch (IllegalArgumentException ex)
        {

        }
    }
    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(
                new Point3D(1,1,1),
                new Point3D(2,1,0),
                new Point3D(2,3,1));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane (point:1)
        Ray ray=new Ray(new Point3D(1,1,2),new Vector(0,0,-1));
        List<Intersectable.GeoPoint> result=plane.findIntersections(ray);
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Wrong point",List.of(new Point3D(1,1,1)),result);



        // TC02: Ray does not intersect the plane (point:0)
        ray=new Ray(new Point3D(1,1,2),new Vector(-1,-2,1).normalize());
        result = plane.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);

        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane
        // TC3: the ray included in the plane (point:0)
        ray =new Ray(new Point3D(1,1,1),new Vector(1,0,-1).normalize());
        result = plane.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);
        // TC4: the ray not included in the plane (point:0)
        ray =new Ray(new Point3D(1,1,2),new Vector(1,0,-1).normalize());
        result = plane.findIntersections(ray);

        assertEquals("Wrong number of points", null, result);
        // **** Group:Ray is orthogonal to the plane
        Vector vector=plane.getNormal();
        // TC5: Ray starts before the plain (1 points)
        Point3D p1=new Point3D(2,3,1).add(vector.scale(-1));
        result=plane.findIntersections(new Ray(p1,vector));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Wrong point",List.of(new Point3D(2,3,1)),result);
        // TC6: Ray starts in the plain (0 points)
         p1=new Point3D(2,3,1);
        result=plane.findIntersections(new Ray(p1,vector));
        assertEquals("Wrong number of points", null, result);

       // TC7: Ray starts after the plain (0 points)
         p1=new Point3D(2,3,1).add(vector);
        result=plane.findIntersections(new Ray(p1,vector.normalize()));
        assertEquals("Wrong number of points", null, result);


        // **** TC8 :Group:Ray is neither orthogonal nor parallel to and begins at the plane (0 points)
        vector =new Point3D(1,1,2).subtract(new Point3D(2,1,0));
        p1=new Point3D(2,1,0);
        result=plane.findIntersections(new Ray(p1,vector.normalize()));
        assertEquals("Wrong number of points", null, result);


        // ****TC9 Group: Ray is neither orthogonal nor parallel to the plane and begins in
        // the same point which appears as reference point in the plane (Q)
        p1=plane.get_p();
        result=plane.findIntersections(new Ray(p1,vector.normalize()));
        assertEquals("Wrong number of points", null, result);
    }

}