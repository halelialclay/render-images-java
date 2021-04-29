/**
 *
 */
package unitTests;

import geometries.Intersectable;
import geometries.Polygon;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Testing Polygons
 @author marom  haleli
 *
 */
public class PolygonTests {

    /**
     * Test method for
     * .
     */

    @Test
    public void testConstructor() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order

        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
         //============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
               new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }

    @Test
    public void testFindIntersections() {
        Point3D p1 =new Point3D(1,1,1);
        Point3D p2=new Point3D(0.563348747140634, 0.7727345260726346, 1.1113277113629085);
        Point3D p3 =new Point3D(2,3,4);
        Point3D p4 =new Point3D(5,4,2);


        Polygon polygon = new Polygon(p1,p2,p3,p4);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Inside triangle(1 point)
        Vector v1=p2.subtract(p1);
        Vector v2=p3.subtract(p2);
        Point3D p6=p1.add(v1.scale(0.5).add(v2.scale(0.2)));
        Vector vector=new Vector(1,2,0);
        Ray ray=new Ray(p6.add(vector.scale(-1)),vector.normalize());
        List<Intersectable.GeoPoint> result=polygon.findIntersections(ray);
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p6), result);



        // TC02: The point outside against edge  (0 points)
        Vector v3=v1.add(v2);
        p6=p1.add(v1.scale(2).add(v3.scale(0.5)));
        ray=new Ray(p6.add(vector.scale(-1)),vector.normalize());
        result=polygon.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);

        // TC03: The point outside against vertex (0 point)
        p6=p1.add(v1.scale(1.5).add(v2.scale(0.5)));
        ray=new Ray(p6.add(vector.scale(-1)),vector.normalize());
        result=polygon.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);




        // =============== Boundary Values Tests ==================

        // TC04: On edge (0 points)
        p6=p1.add(v1.scale(0.5));
        ray=new Ray(p6.add(vector.scale(-1)),vector.normalize());
        result=polygon.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);


        // TC05: In vertex (0 points)
        ray=new Ray(p1.add(vector.scale(-1)),vector.normalize());
        result=polygon.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);

        // TC06:On edge's continuation (0 points)
        p6=p1.add(v1.scale(2));
        ray=new Ray(p6.add(vector.scale(-1)),vector.normalize());
        result=polygon.findIntersections(ray);
        assertEquals("Wrong number of points", null, result);




    }


}
