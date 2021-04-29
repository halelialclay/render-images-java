package unitTests;

import ObjectProject1.SnookerBall;
import ObjectProject1.Table;
import elements.*;
import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class MiniProject1Test {

    //Scene of a snooker table without depth of field
    @Test
    public void scene1Test() {
        double radius=3;
        Scene scene = new Scene("Test scene");
        Table table=new Table(90,250,20,3,4.6,3,
                8,new Vector(1,0,0),new Vector(0,-0.1,1),new Point3D(0,20,-400));
        scene.setCamera(new Camera(new Point3D(40, -10, -700), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(220,225,200));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.black), 0.08));

        scene.addGeometries( table.getTableParts(), new Plane(new Point3D(0,120,0),new Vector(0,1,0)),
                new Plane(new Color(30,30,30) ,new Material(0.2,0.2,10,0,0.4),new Point3D(103, -10, -130),new Vector(0,-0.15,1)),
                new SnookerBall(new Color(0,0,255),radius,
                        new Point3D(49,7.5,-275).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(2,-1,3)),
                new SnookerBall(new Color(0,180,0),radius,
                        new Point3D(28,19,-390).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(1,-1,8),new Vector(-1,1,0.25)),
                new SnookerBall(new Color(235,0,0),radius,
                        new Point3D(45,13.5,-335).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-5,-1,3),new Vector(-1,4,1)),
                new SnookerBall(new Color(0,0,205),radius,
                        new Point3D(15,-4,-160).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new  Vector(2,-1,3),new Vector(5,-2,-4)),
                new SnookerBall(new Color(235,90,0),radius,
                        new Point3D(70,-3.5,-165).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,-1.7,1)),
                new SnookerBall(new  Color(110,0,110),radius,
                        new Point3D(80,4,-240).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(2,-2,9)),
                new SnookerBall(new Color(205,203,150),radius,
                        new Point3D(75,14,-340).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(235,235,0),radius,
                        new Point3D(5,-2,-180).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(110,0,110),radius,
                        new Point3D(19,16,-360).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-4,11,9),new Vector(7,2,0)),
                new SnookerBall(new Color(128,0,0),radius,
                        new Point3D(31,11,-310).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(0,0,0),radius,
                        new Point3D(18.5,4,-240).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,-2,-1)));

        scene.addLights(new PointLight(new Color(150, 200, 200),
                new Point3D(0, -200, -200), 1, 4E-4, 2E-5));

        scene.addLights( new SpotLight(new Color(180,180,120),
                new Point3D(0, -200, -200),new Vector(40,213.5,135),1, 4E-4, 2E-5));
        scene.addLights( new SpotLight(new Color(110,110,80),
                new Point3D(45, -126.5, -349),new Vector(0,1,0.1).normalize(),1, 4E-4, 2E-5));



        ImageWriter imageWriter = new ImageWriter("scene1Test", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene)
                .setMultithreading(5).setDebugPrint();

        render.renderImage();

        render.writeToImage();
    }

    //The scene of a snooker table with depth of field
    @Test
    public void scene1DepthOfFieldTest() {
        double radius=3;
        Scene scene = new Scene("Test scene");
        Table table=new Table(90,250,20,3,4.6,3,
                8,new Vector(1,0,0),new Vector(0,-0.1,1),new Point3D(0,20,-400));
        scene.setCamera(new Camera(new Point3D(40, -10, -700), new Vector(0, 0, 1), new Vector(0, -1, 0),1.5,400,true,13));
        scene.setDistance(1000);
        scene.setBackground(new Color(220,225,200));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.black), 0.08));

        scene.addGeometries( table.getTableParts(), new Plane(new Point3D(0,120,0),new Vector(0,1,0)),
                new Plane(new Color(30,30,30) ,new Material(0.2,0.2,10,0,0.4),new Point3D(103, -10, -130),new Vector(0,-0.15,1)),
                new SnookerBall(new Color(0,0,255),radius,
                        new Point3D(49,7.5,-275).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(2,-1,3)),
                new SnookerBall(new Color(0,180,0),radius,
                        new Point3D(28,19,-390).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(1,-1,8),new Vector(-1,1,0.25)),
                new SnookerBall(new Color(235,0,0),radius,
                        new Point3D(45,13.5,-335).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-5,-1,3),new Vector(-1,4,1)),
                new SnookerBall(new Color(0,0,205),radius,
                        new Point3D(15,-4,-160).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new  Vector(2,-1,3),new Vector(5,-2,-4)),
                new SnookerBall(new Color(235,90,0),radius,
                        new Point3D(70,-3.5,-165).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,-1.7,1)),
                new SnookerBall(new  Color(110,0,110),radius,
                        new Point3D(80,4,-240).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(2,-2,9)),
                new SnookerBall(new Color(205,203,150),radius,
                        new Point3D(75,14,-340).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(235,235,0),radius,
                        new Point3D(5,-2,-180).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(110,0,110),radius,
                        new Point3D(19,16,-360).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-4,11,9),new Vector(7,2,0)),
                new SnookerBall(new Color(128,0,0),radius,
                        new Point3D(31,11,-310).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(0,0,0),radius,
                        new Point3D(18.5,4,-240).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,-2,-1)));

        scene.addLights(new PointLight(new Color(150, 200, 200),
                new Point3D(0, -200, -200), 1, 4E-4, 2E-5));

        scene.addLights( new SpotLight(new Color(180,180,120),
                new Point3D(0, -200, -200),new Vector(40,213.5,135),1, 4E-4, 2E-5));
        scene.addLights( new SpotLight(new Color(110,110,80),
                new Point3D(45, -126.5, -349),new Vector(0,1,0.1).normalize(),1, 4E-4, 2E-5));



        ImageWriter imageWriter = new ImageWriter("scene1DepthOfFieldTest", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene)
                .setMultithreading(5).setDebugPrint();

        render.renderImage();

        render.writeToImage();
    }
}
