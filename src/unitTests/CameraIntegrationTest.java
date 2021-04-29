package unitTests;

import elements.Camera;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 *  Camera and Geometries Integration Tests
 * @author marom  haleli
 */
public class CameraIntegrationTest {
    @Test
    public void integrationCameraTriangle ()
    {
        List listOfPoints=new ArrayList();
        Ray ray;
        //TC01:The triangle is so small (1 point)
        Triangle triangle=new Triangle(new Point3D(0,-1,2),new Point3D(1,1,2),new Point3D(-1,1,2));
        Camera camera=new Camera(new Point3D(0,0,0),new Vector(0,0,1),new Vector(0,-1,0));
        int count=0;
        for (int i = 0; i <3 ; i++)
        {
            for (int j = 0; j <3 ; j++)
            {
                ray=camera.constructRayThroughPixel(3,3, j, i,1,3,3);

                listOfPoints=triangle.findIntersections(ray);

                if(listOfPoints!=null) {
                    count += listOfPoints.size();
                }

            }
        }

        assertEquals("Wrong number of points",1,count);



        //TC02:The triangle is larger but the lower side is still opposite the lower side of the viewing plain   (2 point)
        triangle=new Triangle(new Point3D(0,-20,2),new Point3D(1,1,2),new Point3D(-1,1,2));
        camera=new Camera(new Point3D(0,-0.5,0),new Vector(0,0,1),new Vector(0,-1,0));

        count=0;
        for (int i = 0; i <3 ; i++)
        {
            for (int j = 0; j <3 ; j++)
            {
                ray=camera.constructRayThroughPixel(3,3, j, i,1,3,3);
                listOfPoints=triangle.findIntersections(ray);
                if(listOfPoints!=null) {
                    count += listOfPoints.size();
                }

            }
        }
        assertEquals("Wrong number of points",2,count);

    }
    @Test
    public void integrationCameraSphere(){
        Camera camera;
        Sphere sphere;
        Ray ray;
        List listOfPoints=new ArrayList();
        //TC01: The sphere is after the view plain (2 points)
        sphere=new Sphere(1,new Point3D(0,0,3));
        camera=new Camera(new Point3D(0,0,0),new Vector(0,0,1),new Vector(0,-1,0));
        int count=0;
        for (int i = 0; i <3 ; i++)
        {
            for (int j = 0; j <3 ; j++)
            {
                ray=camera.constructRayThroughPixel(3,3, j, i,1,3,3);
                listOfPoints=sphere.findIntersections(ray);
                if(listOfPoints!=null)
                    count+=listOfPoints.size();

            }
        }

        assertEquals("Wrong number of points",2,count);


        //TC02:  the all view plain is inside the plain and camera   (18 points)
        sphere=new Sphere(2.5,new Point3D(0,0,2.5));
        camera=new Camera(new Point3D(0,0,-0.5),new Vector(0,0,1),new Vector(0,-1,0));
        count=0;
        for (int i = 0; i <3 ; i++)
        {
            for (int j = 0; j <3 ; j++)
            {
                ray=camera.constructRayThroughPixel(3,3, j, i,1,3,3);
                listOfPoints=sphere.findIntersections(ray);
                if(listOfPoints!=null)
                    count+=listOfPoints.size();

            }
        }
        assertEquals("Wrong number of points",18,count);

        //TC03: part of the view plain is inside the plain and camera  (10 points)
        sphere=new Sphere(2,new Point3D(0,0,2));
        count=0;
        for (int i = 0; i <3 ; i++)
        {
            for (int j = 0; j <3 ; j++)
            {
                ray=camera.constructRayThroughPixel(3,3, j, i,1,3,3);
                listOfPoints=sphere.findIntersections(ray);
                if(listOfPoints!=null)
                    count+=listOfPoints.size();

            }
        }
        assertEquals("Wrong number of points",10,count);



        //TC04:The view plain and the camera are inside the sphere (9 points)
        sphere=new Sphere(4,new Point3D(0,0,2.5));
        camera=new Camera(new Point3D(0,0,-0.5),new Vector(0,0,1),new Vector(0,-1,0));
        count=0;
        for (int i = 0; i <3 ; i++)
        {
            for (int j = 0; j <3 ; j++)
            {
                ray=camera.constructRayThroughPixel(3,3, j, i,1,3,3);
                listOfPoints=sphere.findIntersections(ray);
                if(listOfPoints!=null)
                    count+=listOfPoints.size();

            }
        }
        assertEquals("Wrong number of points",9,count);



        //TC05: the all sphere is behind the camera (0 points)
        sphere=new Sphere(0.5,new Point3D(0,0,-1));
        camera=new Camera(new Point3D(0,0,0),new Vector(0,0,1),new Vector(0,1,0));
        count=0;
        for (int i = 0; i <3 ; i++)
        {
            for (int j = 0; j <3 ; j++)
            {
                ray=camera.constructRayThroughPixel(3,3, j, i,1,3,3);
                listOfPoints=sphere.findIntersections(ray);
                if(listOfPoints!=null)
                    count+=listOfPoints.size();

            }
        }
        assertEquals("Wrong number of points",0,count);


    }
    @Test
    public void integrationCameraPlane()
    {
        //TC01: The surface is parallel to the viewing plane  (9 points)
        List listOfPoints=new ArrayList();
        Plane plane=new Plane( new Point3D(3,0,2),new  Point3D(3,1,2),new Point3D(0,1,2));
        Camera camera=new Camera(new Point3D(0,0,1),new Vector(0,0,1),new Vector(0,-1,0));
        Ray ray;
        int count=0;
        for (int i = 0; i <3 ; i++)
        {
            for (int j = 0; j <3 ; j++)
            {
                ray=camera.constructRayThroughPixel(3,3, j, i,1,3,3);
                listOfPoints=plane.findIntersections(ray);
                if(listOfPoints!=null)
                    count+=listOfPoints.size();

            }
        }
        assertEquals("Wrong number of points",9,count);



        //TC02:The plain gradient is sharp with respect to the Y axis (9 points)

        plane=new Plane( new Point3D(0,0,2),new  Point3D(1,2,3),new Point3D(-1,2,3));
        camera=new Camera(new Point3D(0,0,0),new Vector(0,0,1),new Vector(0,-1,0));
        count=0;
        for (int i = 0; i <3 ; i++)
        {
            for (int j = 0; j <3 ; j++)
            {
                ray=camera.constructRayThroughPixel(3,3, j, i,1,3,3);
                listOfPoints=plane.findIntersections(ray);
                if(listOfPoints!=null)
                {
                    count += listOfPoints.size();
                }

            }
        }
        assertEquals("Wrong number of points",9,count);

        //TC03:The plain slope is less sharp with respect to the Y axis  (6 points)

        plane=new Plane( new Point3D(0,0,3),new  Point3D(1,1,4),new Point3D(-1,1,4));
        camera=new Camera(new Point3D(0,0,0),new Vector(0,0,1),new Vector(0,-1,0));
        count=0;
        for (int i = 0; i <3 ; i++)
        {
            for (int j =0; j <3 ; j++)
            {
                ray=camera.constructRayThroughPixel(3,3, j, i,1,3,3);
                listOfPoints=plane.findIntersections(ray);
                if(listOfPoints!=null)
                    count+=listOfPoints.size();

            }
        }
        assertEquals("Wrong number of points",6,count);
    }
}