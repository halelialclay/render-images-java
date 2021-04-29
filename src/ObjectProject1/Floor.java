package ObjectProject1;

import elements.Material;
import geometries.Geometries;
import geometries.Polygon;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**A class that creates a floor.
 *The floor has 2 colors.
 *getting: point,2 vector, size,spaceSize 2 color, material
 * @author haleli mrome
 */
public class Floor
{
    Geometries floorParts=new Geometries();

//************get*************
    public Geometries getFloorParts() {
        return floorParts;
    }
//*********************constructor**************************

    /**
     *Creates in double loop one slots in color 1 and the other in color 2
     * @param point-the point as begin floor
     * @param v1-the vector of the floor plan
     * @param v2-the vector of the floor plan
     * @param size-Size of floor tile
     * @param color2- color of  Double slot
     * @param color1-Slot instead of odd
     * @param material-The material from it is made of the slot
     */
    public Floor(Point3D point, Vector v1, Vector v2, double size, Color color2, Color color1, Material material,int numOfFlooring) {

        Point3D point1;
        //loop that create the flooring.
        for (int i = 1; i <numOfFlooring ; i++) //rows
        {
            point1=point.add(v1.scale(i*(size)));
            for (int j = 1; j <numOfFlooring ; j++)//columns
            {
                Point3D point2=point1.add(v1.scale(size));
                Point3D point3=point2.add(v2.scale(size));
                Point3D point4=point3.subtract(v1.scale(size));
                //for piking a different color intermittently.(color1, color2)
                if((i+j)%2==0)
                {
                    floorParts.add(new Polygon(color1, material, point1, point2, point3, point4));
                }
                else
                    floorParts.add(new Polygon(color2,material,point1,point2,point3,point4));

                point1=point4;
            }
        }
    }
}
