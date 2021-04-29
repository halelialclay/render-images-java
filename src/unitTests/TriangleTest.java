package unitTests;

import geometries.Intersectable;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

public class TriangleTest {

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Point3D p1 =new Point3D(1,1,1);
        Point3D p2 =new Point3D(2,3,4);
        Point3D p3 =new Point3D(5,4,2);
        Triangle triangle = new Triangle(p1,p2,p3);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Inside triangle(1 point)
        Vector v1=p2.subtract(p1);
        Vector v2=p3.subtract(p2);
        Point3D p4=p1.add(v1.scale(0.5).add(v2.scale(0.2)));
        Vector vector=new Vector(1,2,0);
        Ray ray=new Ray(p4.add(vector.scale(-1)),vector.normalize());
        List<Intersectable.GeoPoint> result=triangle.findIntersections(ray);
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p4), result);



        // TC02: Outside against edge (0 points)
        p4=p1.add(v1.scale(1.5).add(v2.scale(0.5)));
        ray=new Ray(p4.add(vector.scale(-1)),vector.normalize());
        result=triangle.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);

        // TC03: Outside against vertex (0 point)
        Vector v3=v1.add(v2);
        p4=p1.add(v1.scale(2).add(v3.scale(0.5)));
        ray=new Ray(p4.add(vector.scale(-1)),vector.normalize());
        result=triangle.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);


        // =============== Boundary Values Tests ==================

        // TC04: On edge (0 points)
        p4=p1.add(v1.scale(0.5));
        ray=new Ray(p4.add(vector.scale(-1)),vector.normalize());
        result=triangle.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);



        // TC05: In vertex (0 points)
        ray=new Ray(p1.add(vector.scale(-1)),vector.normalize());
        result=triangle.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);

        // TC06:On edge's continuation (0 points)
        p4=p1.add(v1.scale(2));
        ray=new Ray(p4.add(vector.scale(-1)),vector.normalize());
        result=triangle.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);




    }

}