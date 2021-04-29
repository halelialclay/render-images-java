package ObjectProject1;

import elements.Material;
import elements.PointLight;
import geometries.Geometries;
import geometries.Polygon;
import geometries.Sphere;
import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * Lamp class: Creates lampshade
 *lightLamp,list of geometries lampParts
 */
public class Lamp

{
    private Geometries lampParts=new Geometries();
    PointLight lightLamp;

    /**
     * constructor. transparency ball that contain a point light/ spot light. and a long wire.
     * @param color- the color of the light ball
     * @param lampShadeColor-the color of lamp Shade
     * @param radius-the radius the light ball
     * @param radius1- the radius of little ball between the lamp and the wire.
     * @param lightLamp- the light of the lamp.
     * @param v1 - the direction of the lamp.
     * @param v2 - a vector that vertical to v1.
     * @throws IllegalArgumentException in case the vectors v1 and v2 are not vertical.
     */
    public Lamp(Color color, Color lampShadeColor, double radius,double radius1 ,PointLight lightLamp,Vector v1, Vector v2) {
        this.lightLamp=lightLamp;

        if(Util.alignZero(v1.dotProduct(v2))!=0) //for the lamp will not be warp.
        {
            throw  new IllegalArgumentException("the vectors of the lamp must be vertix");
        }

        Material materialLamp=new Material(0.2,0.2,100,0.8,0);// the sphere that surrounding a point light/ spot light have to be transparency for the light to be seen.
        Material lampShadeMaterial=new Material(0.2,0.2,200,0,0);

        //the lamp ball
        Point3D center=lightLamp.get_position();
        Sphere s=new Sphere(color,materialLamp,radius,center);
        //The lampshade
        Vector v3=v1.crossProduct(v2);
        Point3D p1=center.add(v1.scale(2*radius));
        Point3D p2=center.add(v2.scale(2*radius));
        Point3D p3=center.add(v3.scale(2*radius));
        Point3D p4=center.subtract(v2.scale(2*radius));
        Point3D p5=center.subtract(v3.scale(2*radius));
        Sphere s1=new Sphere(new Color(140,140,140),lampShadeMaterial,radius1,p1);

        //the cord of the lamp
        Point3D pCord1=p1.add(v2.scale(0.5));
        Point3D pCord2=p1.subtract(v2.scale(0.5));
        Point3D pCord3=p1.add(v3.scale(0.5));
        Point3D pCord4=p1.subtract(v3.scale(0.5));



        //The shapes that make up the lamp


        lampParts.add(s,s1, new Polygon(lampShadeColor,lampShadeMaterial,p1,p2,p3),
                new Polygon(lampShadeColor,lampShadeMaterial,p1,p3,p4),
                new Polygon(lampShadeColor,lampShadeMaterial,p1,p2,p5),
                new Polygon(lampShadeColor,lampShadeMaterial,p1,p4,p5),
                new Polygon(new Color(140,140,140),lampShadeMaterial,pCord1,pCord2,pCord2.add(v1.scale(100)),pCord1.add(v1.scale(100))),
                new Polygon(new Color(140,140,140),lampShadeMaterial,pCord3,pCord4,pCord4.add(v1.scale(100)),pCord3.add(v1.scale(100))));




    }

    public Geometries getLampParts() {
        return lampParts;
    }

    public PointLight getLightLamp() {
        return lightLamp;
    }
}
