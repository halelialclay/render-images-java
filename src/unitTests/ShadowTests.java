package unitTests;

import elements.AmbientLight;
import elements.Camera;
import elements.Material;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * Testing basic shadows
 *
 * @author Dan
 */
public class ShadowTests {

    /**
     * Produce a picture of a sphere and triangle with point light and shade
     */
    @Test
    public void SphereTriangleInitial() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        60, new Point3D(0, 0, 200)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

        scene.addLights(new SpotLight(new Color(400, 240, 0), //
                new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

        ImageWriter imageWriter = new ImageWriter("sphereTriangleInitial", 200, 200, 400, 400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Sphere-Triangle shading - move triangle up-right
     */
    @Test
    public void SphereTriangleMove1() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        60, new Point3D(0, 0, 200)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        new Point3D(-60, 30, 0), new Point3D(-30, 60, 0), new Point3D(-58, 58, 4)));
        scene.addLights(new SpotLight(new Color(400, 240, 0), //
                new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

        ImageWriter imageWriter = new ImageWriter("sphereTriangleMove1", 200, 200, 400, 400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Sphere-Triangle shading - move triangle upper-righter
     */
    @Test
    public void SphereTriangleMove2() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        60, new Point3D(0, 0, 200)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        new Point3D(-50, 20, 0), new Point3D(-20, 50, 0), new Point3D(-48, 48, 4)));

        scene.addLights(new SpotLight(new Color(400, 240, 0), //
                new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

        ImageWriter imageWriter = new ImageWriter("sphereTriangleMove2", 200, 200, 400, 400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Sphere-Triangle shading - move spot closer
     */
    @Test
    public void SphereTriangleSpot1() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        60, new Point3D(0, 0, 200)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

        scene.addLights(new SpotLight(new Color(400, 240, 0), //
                new Point3D(-85, 85, -155), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

        ImageWriter imageWriter = new ImageWriter("sphereTriangleSpot1", 200, 200, 400, 400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Sphere-Triangle shading - move spot even more close
     */
    @Test
    public void SphereTriangleSpot2() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        60, new Point3D(0, 0, 200)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

        scene.addLights(new SpotLight(new Color(400, 240, 0), //
                new Point3D(-67.5, 67.5, -40), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

        ImageWriter imageWriter = new ImageWriter("sphereTriangleSpot2", 200, 200, 400, 400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a Sphere producing a shading
     */




}
