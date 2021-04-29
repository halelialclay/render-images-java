package unitTests;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertTrue;

/**
 * class CylinderTest
 * @author marom  haleli
 */
public class CylinderTest {

    @Test
    public void getNormal() {

        // ============ Equivalence Partitions Tests ==============

        //test when the point is at the the side of the cylinder
        Vector v1 = new Vector(0,1,1).normalize();
        Cylinder cylinder  = new Cylinder(5, new Ray(new Point3D(2,2,-1), v1), 10);
        Point3D point = new Point3D(7,3,0);
        assertTrue(cylinder.getNormal(point).equals(new Vector(1,0,0)));

        //test when the point is at one base
        cylinder  = new Cylinder(5, new Ray(new Point3D(2,2,-1), v1), Math.sqrt(2));
        point = new Point3D(4,3,0);
        assertTrue(cylinder.getNormal(point).equals(new Vector(0,1,1).normalize()));

        //test when  the point is at the other  base
        cylinder  = new Cylinder(5, new Ray(new Point3D(2,2,-1), v1), 10);
        point = new Point3D(3,2,-1);
        assertTrue(cylinder.getNormal(point).equals(new Vector(0,-1,-1).normalize()));


        // =============== Boundary Values Tests ==================
        //test when  the point is at the upwards base edge
        cylinder  = new Cylinder(5, new Ray(new Point3D(2,2,-1), v1), Math.sqrt(2));
        point = new Point3D(7,3,0);
        assertTrue(cylinder.getNormal(point).equals((v1.add(new Vector(1,0,0)).normalize())));

        //test when  the point is at the other  base edge
        cylinder  = new Cylinder(5, new Ray(new Point3D(2,2,-1), v1), Math.sqrt(2));
        point = new Point3D(7,2,-1);
        assertTrue(cylinder.getNormal(point).equals((v1.scale(-1).add(new Vector(1,0,0)).normalize())));
    }
}