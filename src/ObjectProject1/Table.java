package ObjectProject1;

import elements.Material;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Polygon;
import geometries.Sphere;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * A class that makes up a snooker table with rectangles.
 * A table consists of a list of geometries.
 * A table base is built from a group of rectangles. And the legs of the table are made of balls and rectangles.
 */
public class Table
{
    /**
     * the table plane Width
     */
    double tableWidth;
    /**
     * the table plane Length
     */
    double tableLength;
    /**
     * the size of the holes of the table
     */
    double holeSize;
    /**
     * the width of the edge of the table.
     */
    double edgeWidth;
    /**
     * the Height of the edge of the table.
     */
    double edgeHeight;
    /**
     * the width of the wood part of the table.
     */
    double woodWidth;
    double edgeWidth2;
    /**
     * the list of the geometries that build the table.
     */
    private  Geometries tableParts = new Geometries();
    /**
     * the point that state the position of the table.
     */
    Point3D position;
    /**
     * the vector that state the direction of the width of the table
     */
    Vector widthVec;
    /**
     * the vector that state the direction of the length of the table
     */
    Vector lengthVec;


    /**
     * construct the tableParts.
     * The constructor creates all the geometries that make up it.
     * The constructor starts creating them from the starting point(position) with the directions(widthVec,lengthVec) and
     * sizes(tableWidth,tableLength,holeSize,edgeWidth,edgeHeight,edgeWidth2,woodWidth) of the table.
     * @param tableWidth-the table of width
     * @param tableLength- the table of length
     * @param holeSize- the size of hole
     * @param edgeWidth- the edge of width
     * @param edgeHeight- the edge of height
     * @param edgeWidth2-the edge of width2
     * @param woodWidth-the edge the wood width
     * @param widthVec- the vector of the width
     * @param lengthVec- the vector of the length
     * @param position- The starting point of the table
     */
    public Table(double tableWidth, double tableLength, double holeSize, double edgeWidth, double edgeHeight,
                 double edgeWidth2,double woodWidth,
                  Vector widthVec,Vector lengthVec,
                  Point3D position) {
        this.tableWidth = tableWidth;
        this.tableLength = tableLength;
        this.holeSize = holeSize;
        this.edgeWidth = edgeWidth;
        this.edgeHeight = edgeHeight;
        this.position=position;
        this.woodWidth=woodWidth;
        this.edgeWidth2=edgeWidth2;

        this.lengthVec=lengthVec.normalize();
        this.widthVec=widthVec.normalize();

        Vector heightVec= widthVec.crossProduct(lengthVec).normalize();


        Material materialWood=new Material(0.7,0.7,200);
        Material materialGreen=new Material(0.7,0.7,100);
        Color colorWood=new Color(60,10,0);
        Color colorGreen=new Color(10,135,47);

//*******************TablePlane*************************
        Point3D point1=position;
        Point3D point2=point1.add(widthVec.scale(tableWidth));
        Point3D point3=point2.add(lengthVec.scale(tableLength));
        Point3D point4=position.add(lengthVec.scale(tableLength));
        Polygon TablePlane=new Polygon(colorGreen,materialGreen,point1, point2, point3, point4);

//*******************rect11,rect12,rect13,rect14,rect15,rect16*************************
        Point3D point111=point1.subtract(widthVec.scale(edgeWidth));
        Point3D point112=point111.add(lengthVec.scale((tableLength+1-holeSize)/2));
        Point3D point113=point112.add(widthVec.scale(edgeWidth));
        Polygon rect11=new Polygon(colorGreen,materialGreen,point1, point111, point112,point113);

        Point3D point121=point2.add(widthVec.scale(edgeWidth));
        Point3D point122=point121.add(lengthVec.scale((tableLength+1-holeSize)/2));
        Point3D point123=point122.subtract(widthVec.scale(edgeWidth));
        Polygon rect12=new Polygon(colorGreen,materialGreen,point2, point121, point122,point123);

        Point3D point131=point3.add(widthVec.scale(edgeWidth));
        Point3D point132=point131.subtract(lengthVec.scale((tableLength+1-holeSize)/2));
        Point3D point133=point132.subtract(widthVec.scale(edgeWidth));
        Polygon rect13=new Polygon(colorGreen,materialGreen,point3, point131, point132,point133);

        Point3D point141=point4.subtract(widthVec.scale(edgeWidth));
        Point3D point142=point141.subtract(lengthVec.scale((tableLength+1-holeSize)/2));
        Point3D point143=point142.add(widthVec.scale(edgeWidth));
        Polygon rect14=new Polygon(colorGreen,materialGreen,point4, point141, point142,point143);


        Point3D point151=point1.subtract(lengthVec.scale(edgeWidth));
        Point3D point152=point2.subtract(lengthVec.scale(edgeWidth));
        Polygon rect15=new Polygon(colorGreen,materialGreen,point1,point151, point152,point2);

        Point3D point161=point3.add(lengthVec.scale(edgeWidth));
        Point3D point162=point4.add(lengthVec.scale(edgeWidth));
        Polygon rect16=new Polygon(colorGreen,materialGreen,point3, point161, point162,point4);



//*******************rect21,rect22,rect23,rect24,rect25,rect26*************************

        Point3D point211=point111.add(heightVec.add(new Vector(0.2,0,0)).normalize().scale(edgeHeight));
        Point3D point212=point211.add(lengthVec.scale((tableLength-holeSize)/2));
        Polygon rect21=new Polygon(colorGreen,materialGreen,point111, point211, point212,point112);

        Point3D point221=point121.add(heightVec.add(new Vector(-0.2,0,0)).normalize().scale(edgeHeight));
        Point3D point222=point221.add(lengthVec.scale((tableLength-holeSize)/2));
        Polygon rect22=new Polygon(colorGreen,materialGreen,point121, point221, point222,point122);

        Point3D point231=point131.add(heightVec.add(new Vector(-0.2,0,0)).normalize().scale(edgeHeight));
        Point3D point232=point231.subtract(lengthVec.scale((tableLength-holeSize)/2));
        Polygon rect23=new Polygon(colorGreen,materialGreen,point131, point231, point232,point132);

        Point3D point241=point141.add(heightVec.add(new Vector(0.2,0,0)).normalize().scale(edgeHeight));
        Point3D point242=point241.subtract(lengthVec.scale((tableLength-holeSize)/2));
        Polygon rect24=new Polygon(colorGreen,materialGreen,point141, point241, point242,point142);


        Point3D point251=point151.add(heightVec.add(new Vector(0,0,0.2)).normalize().scale(edgeHeight));
        Point3D point252=point152.add(heightVec.add(new Vector(0,0,0.2)).scale(edgeHeight));
        Polygon rect25=new Polygon(colorGreen,materialGreen,point151, point251, point252,point152);

        Point3D point261=point161.add(heightVec.add(new Vector(0,0,-0.2)).scale(edgeHeight));
        Point3D point262=point162.add(heightVec.add(new Vector(0,0,-0.2)).scale(edgeHeight));
        Polygon rect26=new Polygon(colorGreen,materialGreen,point161, point261, point262,point162);


//*******************rect31,rect32,rect33,rect34,rect35,rect36*************************
        Point3D point311=point211.subtract(widthVec.scale(edgeWidth2));
        Point3D point312=point212.subtract(widthVec.scale(edgeWidth2));
        Polygon rect31=new Polygon(colorGreen,materialGreen,point211, point311, point312,point212);

        Point3D point321=point221.add(widthVec.scale(edgeWidth2));
        Point3D point322=point222.add(widthVec.scale(edgeWidth2));
        Polygon rect32=new Polygon(colorGreen,materialGreen,point221, point321, point322,point222);

        Point3D point331=point231.add(widthVec.scale(edgeWidth2));
        Point3D point332=point232.add(widthVec.scale(edgeWidth2));
        Polygon rect33=new Polygon(colorGreen,materialGreen,point331, point231, point232,point332);

        Point3D point341=point241.subtract(widthVec.scale(edgeWidth2));
        Point3D point342=point242.subtract(widthVec.scale(edgeWidth2));
        Polygon rect34=new Polygon(colorGreen,materialGreen,point241, point341, point342,point242);


        Point3D point351=point251.subtract(lengthVec.scale(edgeWidth2));
        Point3D point352=point252.subtract(lengthVec.scale(edgeWidth2));
        Polygon rect35=new Polygon(colorGreen,materialGreen,point351, point251, point252,point352);

        Point3D point361=point261.add(lengthVec.scale(edgeWidth2));
        Point3D point362=point262.add(lengthVec.scale(edgeWidth2));
        Polygon rect36=new Polygon(colorGreen,materialGreen,point361, point261, point262,point362);



//*******************rect41,rect42,rect43,rect44*************************

        Point3D point411=point311.subtract(lengthVec.scale(edgeWidth+edgeWidth2));
        Point3D point412=point411.subtract(widthVec.scale(woodWidth));
        Point3D point413=point341.add(lengthVec.scale(edgeWidth+edgeWidth2));
        Point3D point414=point413.subtract(widthVec.scale(woodWidth));
        Polygon rect41=new Polygon(colorWood,materialWood,point411, point412, point414,point413);


        Point3D point421=point321.subtract(lengthVec.scale(edgeWidth+edgeWidth2));
        Point3D point422=point421.add(widthVec.scale(woodWidth));
        Point3D point423=point331.add(lengthVec.scale(edgeWidth+edgeWidth2));
        Point3D point424=point423.add(widthVec.scale(woodWidth));
        Polygon rect42=new Polygon(colorWood,materialWood,point421, point422, point424,point423);

        Point3D point431=point412.subtract(lengthVec.scale(woodWidth));
        Point3D point432=point422.subtract(lengthVec.scale(woodWidth));
        Polygon rect43=new Polygon(colorWood,materialWood,point431, point432, point422,point412);


        Point3D point441=point414.add(lengthVec.scale(woodWidth));
        Point3D point442=point424.add(lengthVec.scale(woodWidth));
        Polygon rect44=new Polygon(colorWood,materialWood,point441, point442, point424,point414);

//*******************rect51,rect52,rect53,rect54*************************
        Point3D point511=point441.subtract(heightVec.scale(edgeHeight*2));
        Point3D point512=point442.subtract(heightVec.scale(edgeHeight*2));
        Polygon rect51=new Polygon(colorWood,materialWood,point511, point441, point442,point512);


        Point3D point521=point431.subtract(heightVec.scale(edgeHeight*2));
        Point3D point522=point432.subtract(heightVec.scale(edgeHeight*2));
        Polygon rect52=new Polygon(colorWood,materialWood,point521, point431, point432,point522);


        Polygon rect53=new Polygon(colorWood,materialWood,point431,point521,point511,point441);
        Polygon rect54=new Polygon(colorWood,materialWood,point432,point522,point512,point442);

//*******************rect61,rect62,rect63,rect64*************************

        Point3D point611=point511.add(heightVec.scale(2).add(widthVec.scale(15)).subtract(lengthVec.scale(15)));
        Point3D point612=point512.add(heightVec.scale(2).subtract(widthVec.scale(15))).subtract(lengthVec.scale(15));
        Point3D point613=point611.subtract(heightVec.scale(12));
        Point3D point614=point612.subtract(heightVec.scale(12));
        Polygon rect61=new Polygon(colorWood,materialWood,point611,point612,point614,point613);


        Point3D point621=point521.add(heightVec.scale(2).add(widthVec.scale(15)).add(lengthVec.scale(15)));
        Point3D point622=point621.subtract(heightVec.scale(12));
        Polygon rect62=new Polygon(colorWood,materialWood,point621,point622,point613,point611);


        Point3D point631=point522.add(heightVec.scale(2).subtract(widthVec.scale(15)).add(lengthVec.scale(15)));
        Point3D point632=point631.subtract(heightVec.scale(12));
        Polygon rect63=new Polygon(colorWood,materialWood,point631,point632,point622,point621);


        Polygon rect64=new Polygon(colorWood,materialWood,point612,point614,point632,point631);


//*******************rect65*************************
        Polygon rect65=new Polygon(colorWood,materialWood,point631,point621,point611,point612);



        double legStartLength=18;
        double legStartWidth=1;


 List<Geometry> geometries=CreateLeg(point1,legStartLength,legStartWidth, heightVec,widthVec,lengthVec,colorWood,  materialWood );
        for (Geometry geometry:geometries
             ) {
            tableParts.add(geometry);

        }
        double len=15.033296378372908;

         geometries=CreateLeg(point2,legStartLength,legStartWidth, heightVec,widthVec.scale(-1),lengthVec,colorWood,  materialWood );
        for (Geometry geometry:geometries
        ) {
            tableParts.add(geometry);

        }
        geometries=CreateLeg(point3,legStartLength,legStartWidth, heightVec,widthVec.scale(-1),lengthVec.scale(-1),colorWood,  materialWood );
        for (Geometry geometry:geometries
        ) {
           tableParts.add(geometry);

        }
        geometries=CreateLeg(point4,legStartLength,legStartWidth, heightVec,widthVec,lengthVec.scale(-1),colorWood,  materialWood );
        for (Geometry geometry:geometries
        ) {
            tableParts.add(geometry);

        }



        tableParts.add(TablePlane,rect11,rect12,rect13,rect14,rect15,rect16,rect21,rect22,rect23
        ,rect24,rect25,rect26,rect31,rect32,rect33,rect34,rect35,rect36,rect41,rect42,rect43,rect44,rect51,
                rect52,rect53,rect54,rect61,rect62,rect63,rect64,rect65);



    }


    public Geometries getTableParts() {
        return tableParts;
    }

    /**
     * creating led to the table
     * @param point- the point of the start of the leg
     * @param legStartLength - the length of the first part of the leg.
     * @param legStartWidth - the width of the first part of the leg.
     * @param heightVec- the vector of the height of the table.
     * @param widthV -the vector that state which leg to create.
     * @param lengthV -the vector that state which leg to create.
     * @param colorWood -the color of the leg.
     * @param materialWood -the material of the leg.
     * @return the geometry of the leg
     */
    private List<Geometry> CreateLeg(Point3D point,double legStartLength,double legStartWidth, Vector heightVec,Vector widthV,Vector lengthV,Color colorWood, Material materialWood )
    {
       //*************************the first part of the leg****************************
        Point3D pointFirst=point;
        Point3D point1=point.subtract(heightVec.scale(legStartLength));
        Point3D point2,point3,point4;
        Polygon rect;
        List<Geometry> geometries=new ArrayList<>();
        //side one
        for(int i=0; i<14; i++)
        {
            if(i%2==0)
                point2=point.add(widthV.scale(legStartWidth).subtract(lengthV.scale(legStartWidth)));
            else
                point2=point.add(widthV.scale(legStartWidth).add(lengthV.scale(legStartWidth)));
            point3=point2.subtract(heightVec.scale(legStartLength));
            geometries.add(new Polygon(colorWood,materialWood,point,point2,point3,point1));
            point=point2;
            point1=point3;
        }
        //side two
        for(int i=0; i<14; i++)
        {
            if(i%2==0)
                point2=point.add(lengthV.scale(legStartWidth).subtract(widthV.scale(legStartWidth)));
            else
                point2=point.add(lengthV.scale(legStartWidth).add(widthV.scale(legStartWidth)));
            point3=point2.subtract(heightVec.scale(legStartLength));
            geometries.add(new Polygon(colorWood, materialWood, point, point2, point3, point1));
            point=point2;
            point1=point3;
        }
        Point3D pointFinal=point; //to help with finding the middle of this part of the leg.
        //side three
        for(int i=0; i<14; i++) {
            if (i % 2 == 0)
                point2 = point.subtract(widthV.scale(legStartWidth).subtract(lengthV.scale(legStartWidth)));
            else
                point2 = point.subtract(widthV.scale(legStartWidth).add(lengthV.scale(legStartWidth)));
            point3 = point2.subtract(heightVec.scale(legStartLength));
            geometries.add(new Polygon(colorWood, materialWood, point, point2, point3, point1));
            point = point2;
            point1 = point3;
        }
        //side four
        for(int i=0; i<14; i++)
        {
            if(i%2==0)
                point2=point.subtract(lengthV.scale(legStartWidth).subtract(widthV.scale(legStartWidth)));
            else
                point2=point.subtract(lengthV.scale(legStartWidth).add(widthV.scale(legStartWidth)));
            point3=point2.subtract(heightVec.scale(legStartLength));
            geometries.add(new Polygon(colorWood, materialWood, point, point2, point3, point1));
            point=point2;
            point1=point3;
        }
        //double d=pointFirst.subtract(point).length();
        Vector vector=pointFirst.subtract(pointFinal);//find the middle of the first part of the leg


        //***********************the second part of the leg*********************
        Point3D center=pointFirst.subtract(vector.scale(0.5)).subtract(heightVec.scale(20));
        Sphere ball1=new Sphere(colorWood,materialWood,4,center);
        geometries.add(ball1);


        //***********************the third part of the leg******************
        double miniRad=0.95;
        double radius=7;
        center=center.subtract(heightVec.scale(radius));
        for (int i = 0; i <10 ; i++) {
            center=center.subtract(heightVec.scale(0.3*radius));
            radius*=miniRad;
            geometries.add(new Sphere(colorWood,materialWood,radius,center));

        }
        miniRad=0.967;
        for (int i = 0; i <5 ; i++) {
            center=center.subtract(heightVec.scale(0.2*radius));
            radius/=miniRad;
            geometries.add(new Sphere(colorWood,materialWood,radius,center));

        }

        return geometries;


    }
}



