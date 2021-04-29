package unitTests;

import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertTrue;

/**
 * class TubeTest
 * @author marom  haleli
 */
public class TubeTest {
    /**
     * test- getNormal in Tube
     */
    @Test
    public void getNormal() {
        Vector v1 = new Vector(0,1,1).normalize();
        Tube tube = new Tube(5, new Ray(new Point3D(2,2,-1), v1));
        Point3D point = new Point3D(7,3,0);
        assertTrue(tube.getNormal(point).equals(new Vector(1,0,0)));

    }
}
