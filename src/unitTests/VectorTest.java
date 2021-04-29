package unitTests;

import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 * @author marome haleli
 */

public class VectorTest {
    /**
     * Test add
     */
    @Test
    public void add()
    {

        Vector v1=new Vector(1,2,3);
        Vector v2=new Vector(1,3,-2);
        Vector v3=new Vector(-1,-1,-1);
        Vector v4=new Vector(1,1,1);

        // ============ Equivalence Partitions Tests ==============
        Vector vector=v1.add(v2);
        assertEquals(new Vector(2,5,1),vector);


       // =============== Boundary Values Tests ==================
        // test zero vector from add  vectors
        try {


            Vector vector1 = v3.add(v4);
            fail("vector zero is unvalid");
        }
        catch (IllegalArgumentException ex)
        {

        }

    }

    /**
     * Test subtract
     */
    @Test
    public void subtract()
    {
        Vector v1=new Vector(1,2,3);
        Vector v2=new Vector(1,3,-2);
        Vector v3=new Vector(1,1,1);

        // ============ Equivalence Partitions Tests ==============
        Vector vector=v1.subtract(v2);
        assertEquals(new Vector(0,-1,5),vector);


        // =============== Boundary Values Tests ==================
        // test zero vector from subtract  vectors
        try {


            Vector vector1 = v3.subtract(v3);
            fail("vector zero is unvalid");
        }
        catch (IllegalArgumentException ex)
        {

        }
    }

    /**
     * Test scale
     */
    @Test
    public void scale()
    {
        Vector v1=new Vector(1,-2,3);


        // ============ Equivalence Partitions Tests ==============
        Vector vector=v1.scale(-2);
        assertEquals(new Vector(-2,4,-6),vector);


        // =============== Boundary Values Tests ==================
        // test zero vector from scale vectors
        try {


            Vector vector1=v1.scale(0);
            fail("vector zero is unvalid");
        }
        catch (IllegalArgumentException ex)
        {

        }
    }

    /**
     * Test dotProduct
     */
    @Test
    public void dotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        // ============ Equivalence Partitions Tests ==============
        // Test dot-productresult when the vectors are not vertical
        assertTrue("dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));



        // =============== Boundary Values Tests ==================
        //test dot-product result when the vectors are vertical
        assertTrue("dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));

    }






    @Test
    public void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }

    @Test
    public void lengthSquared() {
        Vector v1 = new Vector(1, 2, 3);
        // test lengthSquared..
        assertTrue(isZero(v1.lengthSquared() - 14));

    }

    @Test
    public void length()
    {
        // test length..

        assertTrue(isZero(new Vector(0, 3, 4).length() - 5));

    }

    @Test
    public void normalize()
    {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();
        //test  whether the normalization is on the vector and  not only on its copy
        assertTrue (vCopy == vCopyNormalize);
        //test that normalize result length is 1
        assertTrue (isZero(vCopyNormalize.length() - 1));


    }

    @Test
    public void normalized() {
        Vector v = new Vector(1, 2, 3);
        //test  whether the normalization is not on the vector and only on its copy
        Vector u = v.normalized();
        assertTrue (u != v);
    }

    @Test
    public void testConstructor()
    {

       //test
        try
        {
            Vector vector = new Vector(0,0,0);
            fail("Failed constructing a correct Vector");
        }
        catch (IllegalArgumentException ex)
        {
        }
        try
        {
            Vector vector = new Vector(new Point3D(0,0,0));
            fail("Failed constructing a correct Vector");
        }
        catch (IllegalArgumentException ex)
        {
        }
        try
        {
            Vector vector = new Vector(0,0,0);
            fail("Failed constructing a correct Vector");
        }
        catch (IllegalArgumentException ex)
        {
        }

    }
}