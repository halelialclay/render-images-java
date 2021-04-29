package unitTests;

import ObjectProject1.Floor;
import ObjectProject1.Lamp;
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

public class MiniProject2Test {

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


    //The scene of a snooker table with depth of field And Adaptive SuperSampling
    @Test
    public void scene1DepthOfFieldAndAdaptiveSuperSamplingTest() {
        double radius=3;
        Scene scene = new Scene("Test scene");
        Table table=new Table(90,250,20,3,4.6,3,
                8,new Vector(1,0,0),new Vector(0,-0.1,1),new Point3D(0,20,-400));
        scene.setCamera(new Camera(new Point3D(40, -10, -700), new Vector(0, 0, 1), new Vector(0, -1, 0),2,400,true));
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



        ImageWriter imageWriter = new ImageWriter("scene1DepthOfFieldTestAdaptiveSuperSampling", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene)
                .setMultithreading(5).setDebugPrint();

        render.renderImage();

        render.writeToImage();
    }


    @Test
    public void mini_project2_light() {
        double radius=3.5;
        Scene scene = new Scene("Test scene");
        Table table=new Table(90,200,20,3,4.6,3,
                8,new Vector(1,0,0),new Vector(0,-0.1,1),new Point3D(0,20,-400));
        scene.setCamera(new Camera(new Point3D(290, -30, -550),
                new Vector(-1, 0.2, 1).normalize(), new Vector(-0.1, -1, 0.1).normalize()
                ,1.5,350,true));


        scene.setDistance(300);
        scene.setBackground(new Color(220,225,200));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.black), 0.01));
        Floor floor= new Floor(new Point3D(7.0, 72.33596799730513, -387.731490265485).
                subtract(new Vector(1,0,0).scale(300).subtract(new Vector(0,-0.1,1).scale(-150)))
                ,new Vector(0,-0.1,1),new Vector(1,0,0),
                40,new Color(100,100,100),Color.BLACK,new Material(0,0,200,0,0.6),20);
       scene.addGeometries(floor.getFloorParts());
        scene.addGeometries( table.getTableParts(),
                new Plane(new Color(69,69,69) ,new Material(0.2,0.2,10,0,0.4),
                        new Point3D(103, -10, -130),new Vector(0,-0.15,1)),
                new Plane(new Color(69,69,69) ,new Material(0.2,0.2,10),
                        new Point3D(-300,20,-400),new Vector(1,0,0)),
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
                        new Point3D(15,1,-210).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new  Vector(2,-1,3),new Vector(5,-2,-4)),
                new SnookerBall(new Color(235,90,0),radius,
                        new Point3D(70,1.5,-215).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,-1.7,1)),
                new SnookerBall(new  Color(110,0,110),radius,
                        new Point3D(80,4,-240).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(2,-2,9)),
                new SnookerBall(new Color(205,203,150),radius,
                        new Point3D(75,14,-340).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(150,150,0),radius,
                        new Point3D(5,3,-230).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(110,0,110),radius,
                        new Point3D(19,16,-360).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-4,11,9),new Vector(7,2,0)),
                new SnookerBall(new Color(128,0,0),radius,
                        new Point3D(31,11,-310).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(0,0,0),radius,
                        new Point3D(42.5,4,-240).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,-2,-1)));

        Lamp lamp1=new Lamp(new Color(206,206,100),new Color(0,0,30),7,2,new SpotLight(new Color(30,30,30)
                ,new Point3D(55,-48,-369),new Vector(0,1,0.1),1,0,0),new Vector(0,-1,-0.1),new Vector(1,0,0));
        scene.addGeometries(lamp1.getLampParts());
        scene.addLights(lamp1.getLightLamp());

        Lamp lamp2=new Lamp(
                new Color(206,206,150),
                new Color(0,0,30),
                7,2,new SpotLight(new Color(30,30,30)
                ,new Point3D(55,-52,-329),new Vector(0,1,0.1),1,0,0),new Vector(0,-1,-0.1),new Vector(1,0,0));
        scene.addGeometries(lamp2.getLampParts());
        scene.addLights(lamp2.getLightLamp());

        Lamp lamp3=new Lamp(new Color(206,206,150),new Color(0,0,30),7,2,new SpotLight(new Color(30,30,30)
                ,new Point3D(55,-56,-289),new Vector(0,1,0.1),1,0,0),new Vector(0,-1,-0.1),new Vector(1,0,0));
        scene.addGeometries(lamp3.getLampParts());
        scene.addLights(lamp3.getLightLamp());


        scene.addLights( new SpotLight(new Color(110,110,80),
                new Point3D(45, -50, -349),new Vector(0,1,0.7).normalize(),1, 4E-4, 2E-5));
        scene.addLights(new DirectionalLight(new Color(50,50,50),new Vector(-0.4, 0, 0.7).normalize()));
        scene.addLights(new DirectionalLight(new Color(100,100,100),new Vector(0.2,0,0.2).normalize()));



        ImageWriter imageWriter = new ImageWriter("MINI PROJECT DIRECTION1", 200, 200, 800, 800);
        Render render = new Render(imageWriter, scene)
                .setMultithreading(5)
                .setDebugPrint();

        render.renderImage();

        render.writeToImage();
    }


    @Test
    public void mini_project2_non_mirror() {
        double radius=3.5;
        Scene scene = new Scene("Test scene");
        Table table=new Table(90,200,20,3,4.6,3,
                8,new Vector(1,0,0),new Vector(0,-0.1,1),new Point3D(0,20,-400));
        scene.setCamera(new Camera(new Point3D(290, -50, -550),
                new Vector(-1, 0.2, 1).normalize(), new Vector(-0.1, -1, 0.1).normalize()
                ,3,310,true));


        scene.setDistance(500);
        scene.setBackground(new Color(220,225,200));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.black), 0.01));
        Floor floor= new Floor(new Point3D(7.0, 72.33596799730513, -387.731490265485).
                subtract(new Vector(1,0,0).scale(300).subtract(new Vector(0,-0.1,1).scale(-150)))
                ,new Vector(0,-0.1,1),new Vector(1,0,0),
                40,new Color(100,100,100),Color.BLACK,new Material(0,0,200,0,0),20);
        scene.addGeometries(floor.getFloorParts());
        scene.addGeometries( table.getTableParts(),
                new Plane(new Color(69,69,69) ,new Material(0.2,0.2,10,0,0.4),
                        new Point3D(103, -10, -130),new Vector(0,-0.15,1)),
                new Plane(new Color(69,69,69) ,new Material(0.2,0.2,10),
                        new Point3D(-300,20,-400),new Vector(1,0,0)),
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
                        new Point3D(15,1,-210).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new  Vector(2,-1,3),new Vector(5,-2,-4)),
                new SnookerBall(new Color(235,90,0),radius,
                        new Point3D(70,1.5,-215).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,-1.7,1)),
                new SnookerBall(new  Color(110,0,110),radius,
                        new Point3D(80,4,-240).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(2,-2,9)),
                new SnookerBall(new Color(205,203,150),radius,
                        new Point3D(75,14,-340).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(150,150,0),radius,
                        new Point3D(5,3,-230).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(110,0,110),radius,
                        new Point3D(19,16,-360).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-4,11,9),new Vector(7,2,0)),
                new SnookerBall(new Color(128,0,0),radius,
                        new Point3D(31,11,-310).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(0,0,0),radius,
                        new Point3D(42.5,4,-240).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,-2,-1)));

        Lamp lamp1=new Lamp(new Color(206,206,100),new Color(0,0,30),7,2,new SpotLight(new Color(30,30,30)
                ,new Point3D(55,-48,-369),new Vector(0,1,0.1),1,0,0),new Vector(0,1,0.1),new Vector(1,0,0));
        scene.addGeometries(lamp1.getLampParts());
        scene.addLights(lamp1.getLightLamp());

        Lamp lamp2=new Lamp(new Color(206,206,150),new Color(0,0,30),7,2,new SpotLight(new Color(30,30,30)
                ,new Point3D(55,-52,-329),new Vector(0,1,0.1),1,0,0),new Vector(0,1,0.1),new Vector(1,0,0));
        scene.addGeometries(lamp2.getLampParts());
        scene.addLights(lamp2.getLightLamp());

        Lamp lamp3=new Lamp(new Color(206,206,150),new Color(0,0,30),7,2,new SpotLight(new Color(30,30,30)
                ,new Point3D(55,-56,-289),new Vector(0,1,0.1),1,0,0),new Vector(0,1,0.1),new Vector(1,0,0));
        scene.addGeometries(lamp3.getLampParts());
        scene.addLights(lamp3.getLightLamp());


        scene.addLights( new SpotLight(new Color(110,110,80),
                new Point3D(45, -50, -349),new Vector(0,1,0.7).normalize(),1, 4E-4, 2E-5));
        scene.addLights(new DirectionalLight(new Color(50,50,50),new Vector(-0.4, 0, 0.7).normalize()));
        scene.addLights(new DirectionalLight(new Color(100,100,100),new Vector(0.2,0,0.2).normalize()));



        ImageWriter imageWriter = new ImageWriter("MINI PROJECT DIRECTION2", 200, 200, 700, 700);
        Render render = new Render(imageWriter, scene)
                .setMultithreading(5).setDebugPrint();

        render.renderImage();

        render.writeToImage();
    }



    @Test
    public void mini_project2_front() {
        double radius=3.5;
        Scene scene = new Scene("Test scene");
        Table table=new Table(90,200,20,3,4.6,3,
                8,new Vector(1,0,0),new Vector(0,-0.1,1),new Point3D(0,20,-400));
        scene.setCamera(new Camera(new Point3D(290, -30, -550),
                new Vector(-1, 0.2, 1).normalize(), new Vector(-0.1, -1, 0.1).normalize()
                ,1.5,350,false));


        scene.setDistance(300);
        scene.setBackground(new Color(220,225,200));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.black), 0.01));
        Floor floor= new Floor(new Point3D(7.0, 72.33596799730513, -387.731490265485).
                subtract(new Vector(1,0,0).scale(300).subtract(new Vector(0,-0.1,1).scale(-150)))
                ,new Vector(0,-0.1,1),new Vector(1,0,0),
                40,new Color(100,100,100),Color.BLACK,new Material(0,0,200,0,0.6),20);
        scene.addGeometries(floor.getFloorParts());
        scene.addGeometries( table.getTableParts(),
                new Plane(new Color(69,69,69) ,new Material(0.2,0.2,10,0,0.4),
                        new Point3D(103, -10, -130),new Vector(0,-0.15,1)),
                new Plane(new Color(69,69,69) ,new Material(0.2,0.2,10),
                        new Point3D(-300,20,-400),new Vector(1,0,0)),
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
                        new Point3D(15,1,-210).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new  Vector(2,-1,3),new Vector(5,-2,-4)),
                new SnookerBall(new Color(235,90,0),radius,
                        new Point3D(70,1.5,-215).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,-1.7,1)),
                new SnookerBall(new  Color(110,0,110),radius,
                        new Point3D(80,4,-240).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(2,-2,9)),
                new SnookerBall(new Color(205,203,150),radius,
                        new Point3D(75,14,-340).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(150,150,0),radius,
                        new Point3D(5,3,-230).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(110,0,110),radius,
                        new Point3D(19,16,-360).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-4,11,9),new Vector(7,2,0)),
                new SnookerBall(new Color(128,0,0),radius,
                        new Point3D(31,11,-310).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(0,0,0),radius,
                        new Point3D(42.5,4,-240).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new
                                Vector(-1,-2,-1)));

        Lamp lamp1=new Lamp(new Color(206,206,100),new Color(0,0,30),7,2,new SpotLight(new Color(30,30,30)
                ,new Point3D(55,-48,-369),new Vector(0,1,0.1),1,0,0),new Vector(0,-1,-0.1),new Vector(1,0,0));
        scene.addGeometries(lamp1.getLampParts());
        scene.addLights(lamp1.getLightLamp());

        Lamp lamp2=new Lamp(new Color
                (206,206,150),new Color(0,0,30),7,2,new SpotLight(new Color(30,30,30)
                ,new Point3D(55,-52,-329),new Vector(0,1,0.1),1,0,0),new Vector(0,-1,-0.1),new Vector(1,0,0));
        scene.addGeometries(lamp2.getLampParts());
        scene.addLights(lamp2.getLightLamp());

        Lamp lamp3=new Lamp(new Color(206,206,150),new Color(0,0,30),7,2,new SpotLight(new Color(30,30,30)
                ,new Point3D(55,-56,-289),new Vector(0,1,0.1),1,0,0),new Vector(0,-1,-0.1),new Vector(1,0,0));
        scene.addGeometries(lamp3.getLampParts());
        scene.addLights(lamp3.getLightLamp());


        scene.addLights( new SpotLight(new Color(110,110,80),
                new Point3D(45, -50, -349),new Vector(0,1,0.7).normalize(),1, 4E-4, 2E-5));
        scene.addLights(new DirectionalLight(new Color(50,50,50),new Vector(-0.4, 0, 0.7).normalize()));
        scene.addLights(new DirectionalLight(new Color(100,100,100),new Vector(0.2,0,0.2).normalize()));



        ImageWriter imageWriter = new ImageWriter("MINI PROJECT front", 200, 200, 1000, 1000);
        Render render = new Render(imageWriter, scene)
                .setMultithreading(5).setDebugPrint();

        render.renderImage();

        render.writeToImage();
    }

    @Test
    public void mini_project2_side3() {
        double radius=3.5;
        Scene scene = new Scene("Test scene");
        Table table=new Table(90,200,20,3,4.6,3,
                8,new Vector(1,0,0),new Vector(0,-0.1,1),new Point3D(0,20,-400));
        scene.setCamera(new Camera(new Point3D(40, -10, -700),
                new Vector(0, 0, 1).normalize(), new Vector(0, -1, 0).normalize()
                ,1.5,350,false));


        scene.setDistance(300);
        scene.setBackground(new Color(220,225,200));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.black), 0.01));
        Floor floor= new Floor(new Point3D(7.0, 72.33596799730513, -387.731490265485).
                subtract(new Vector(1,0,0).scale(300).subtract(new Vector(0,-0.1,1).scale(-150)))
                ,new Vector(0,-0.1,1),new Vector(1,0,0),
                40,new Color(100,100,100),Color.BLACK,new Material(0,0,200,0,0.6),20);
        scene.addGeometries(floor.getFloorParts());
        scene.addGeometries( table.getTableParts(),
                new Plane(new Color(69,69,69) ,new Material(0.2,0.2,10,0,0.4),
                        new Point3D(103, -10, -130),new Vector(0,-0.15,1)),
                new Plane(new Color(69,69,69) ,new Material(0.2,0.2,10),
                        new Point3D(-300,20,-400),new Vector(1,0,0)),
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
                        new Point3D(15,1,-210).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new  Vector(2,-1,3),new Vector(5,-2,-4)),
                new SnookerBall(new Color(235,90,0),radius,
                        new Point3D(70,1.5,-215).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,-1.7,1)),
                new SnookerBall(new  Color(110,0,110),radius,
                        new Point3D(80,4,-240).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(2,-2,9)),
                new SnookerBall(new Color(205,203,150),radius,
                        new Point3D(75,14,-340).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1
                                ,1,1)),
                new SnookerBall(new Color(150,150,0),radius,
                        new Point3D(5,3,-230).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(110,0,110),radius,
                        new Point3D(19,16,-360).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-4,11,9),new Vector(7,2,0)),
                new SnookerBall(new Color(128,0,0),radius,
                        new Point3D(31,11,-310).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new Vector(-1,1,1)),
                new SnookerBall(new Color(0,0,0),radius,
                        new Point3D(42.5,4,-240).add(new Vector(0,-1,-0.1).normalize().scale(radius)),
                        new
                                Vector(-1,-2,-1)));

        Lamp lamp1=new Lamp(new Color(206,206,100),new Color(0,0,30),7,2,new SpotLight(new Color(30,30,30)
                ,new Point3D(55,-48,-369),new Vector(0,1,0.1),1,0,0),new Vector(0,-1,-0.1),new Vector(1,0,0));
        scene.addGeometries(lamp1.getLampParts());
        scene.addLights(lamp1.getLightLamp());

        Lamp lamp2=new Lamp(new Color(206,206,150),new Color(0,0,30),7,2,new SpotLight(new Color(30,30,30)
                ,new Point3D(55,-52,-329),new Vector(0,1,0.1),1,0,0),new Vector(0,-1,-0.1),new Vector(1,0,0));
        scene.addGeometries(lamp2.getLampParts());
        scene.addLights(lamp2.getLightLamp());

        Lamp lamp3=new Lamp(new Color(206,206,150),new Color(0,0,30),7,2,new SpotLight(new Color(30,30,30)
                ,new Point3D(55,-56,-289),new Vector(0,1,0.1),1,0,0),new Vector(0,-1,-0.1),new Vector(1,0,0));
        scene.addGeometries(lamp3.getLampParts());
        scene.addLights(lamp3.getLightLamp());


        scene.addLights( new SpotLight(new Color(110,110,80),
                new Point3D(45, -50, -349),new Vector(0,1,0.7).normalize(),1, 4E-4, 2E-5));
        scene.addLights(new DirectionalLight(new Color(50,50,50),new Vector(-0.4, 0, 0.7).normalize()));
        scene.addLights(new DirectionalLight(new Color(100,100,100),new Vector(0.2,0,0.2).normalize()));



        ImageWriter imageWriter = new ImageWriter("MINI PROJECT DIRECTION3", 200, 200, 700, 700);
        Render render = new Render(imageWriter, scene)
                .setMultithreading(5).setDebugPrint();

        render.renderImage();

        render.writeToImage();
    }


}
