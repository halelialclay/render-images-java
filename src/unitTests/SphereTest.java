package unitTests;

import geometries.Intersectable;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * SphereTest
 * @author marom  haleli
 */
public class SphereTest {

    /**
     * getNormal Test in Sphere
     */
    @Test
    public void getNormal()
        {
            Sphere sphere=new Sphere(5,new Point3D(1,2,2));
            Vector normal=new Vector(0,-4d/5,-3d/5).normalize();
            assertTrue(sphere.getNormal(new Point3D(1,-2,-1)).equals(normal));


        }

    /**
     * Test method for .
     */
    @Test
    public void testFindIntersections() {

        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0).normalize())));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Intersectable.GeoPoint> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0).normalize()));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getPoint().get_x().get() > result.get(1).getPoint().get_x().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        p2=new Point3D(0.5,0,0);
        Vector vector=p1.subtract(p2).normalize();
        result = sphere.findIntersections(new Ray(new Point3D(0.5, 0, 0),
                vector));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p1), result);

        // TC04: Ray starts after the sphere (0 points)
        p1 = new Point3D(3,0, 0);
        vector= new Vector(1,0,0);
        result = sphere.findIntersections(new Ray(new Point3D(3, 0, 0),
                vector));
        assertEquals("Wrong number of points", null, result);


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)

        // TC11: Ray starts at sphere and goes inside (1 points)
         p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
         p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
         Vector vector1=p2.subtract(p1).normalize();
         Ray ray=new Ray(p1,vector1);
         result=sphere.findIntersections(ray);
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p2), result);

        // TC12: Ray starts at sphere and goes outside (0 points)
        ray=new Ray(p1,vector1.scale(-1));
        result=sphere.findIntersections(ray);
        assertEquals("Wrong number of points",  null, result);


        // **** Group: Ray's line goes through the center
        p2=p1.add(sphere.getCenter().subtract(p1).scale(2));
        vector1=p2.subtract(p1).normalize();
        // TC13: Ray starts before the sphere (2 points)
        Point3D p3=p1.add(vector1.scale(-1));
        ray=new Ray(p3,vector1);
        result=sphere.findIntersections(ray);
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getPoint().get_x().get() > result.get(1).getPoint().get_x().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC14: Ray starts at sphere and goes inside (1 points)
        ray=new Ray(p1,vector1);
        result=sphere.findIntersections(ray);
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p2), result);
        // TC15: Ray starts inside (1 points)
        p3=p1.add(vector1.scale(1.5));
        ray=new Ray(p3,vector1);
        result=sphere.findIntersections(ray);
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p2), result);

        // TC16: Ray starts at the center (1 points)
        ray=new Ray(sphere.getCenter(),vector1);
        result=sphere.findIntersections(ray);
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p2), result);
        // TC17: Ray starts at sphere and goes outside (0 points)
        ray=new Ray(p2,vector1);
        result=sphere.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);
        // TC18: Ray starts after sphere (0 points)
        p3=p2.add(vector1.scale(0.5));
        ray=new Ray(p3,vector1);
        result=sphere.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        Vector vector2=new Vector(0,0,1);
        // TC19: Ray starts before the tangent point
      p3=p2.add(vector2.scale(-1));
       ray=new Ray(p3,vector2);
       result=sphere.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);
        // TC20: Ray starts at the tangent point
        ray=new Ray(p2,vector2);
        result=sphere.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);
        // TC21: Ray starts after the tangent point
        p3=p2.add(vector2.scale(2));
        ray=new Ray(p2,vector2);
        result=sphere.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        p3= p2.add(vector1);
        ray=new Ray(p3,vector2);
        result=sphere.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);

    }





}