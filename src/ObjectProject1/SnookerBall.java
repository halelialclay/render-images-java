package ObjectProject1;
import elements.Material;
import geometries.Sphere;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * A class that creates a snooker ball and extends Sphere
 * their is tow type of snooker balls- one with one color, and one with white big circles on his side.
 */
public class SnookerBall extends Sphere {
    /**
     * the radius od all snooker balls.
     */
    final static double RADIUS=5;
    /**
     * the Min cosine of the angle between the vector vecLittleCircle and the vector from the sphere center to the point, that in the white circle.
     */
    final static double COSLITTLECIRCLE=0.939;
    /**
     * the Min cosine of the angle between the vector vecBigCircle and the vector from the sphere center to the point, that in the white circle.
     */
    final static double COSBIGCIRCLE=0.642;
    //2 vectors that determine the location of the white circles
    private Vector vecLittleCircle;
    private Vector vecBigCircle;
    //A Boolean variable that determines if there is one or two white circles
    private boolean oneColor;

    /**
     * constructor-Constructor for a ball with 2 colors
     * @param emissionLight -The background color of the ball
     * @param radius - the radius of SnookerBall
     * @param center -the point os  SnookerBall
     * @param vecLittleCircle-A vector that creates a ray from the center of the ball that creates a white color on the ball
     * @param vecBigCircle-A vector that creates a ray from the center of the ball that creates a white color on the ball
     */
    public SnookerBall(Color emissionLight,  double radius, Point3D center, Vector vecLittleCircle, Vector vecBigCircle) {
        this(emissionLight, radius, center, vecLittleCircle);
        this.vecBigCircle = vecBigCircle.normalize();
        this.oneColor=false;
    }

    /**
     * constructor-Constructor for one color ball
     * @param emissionLight -The background color of the ball
     * @param radius - the radius of SnookerBall
     * @param center -the point on  SnookerBall
     * @param vecLittleCircle-A vector that creates a ray from the center of the ball that creates a white color on the ball
     */
    public SnookerBall(Color emissionLight, double radius, Point3D center, Vector vecLittleCircle) {
        super(emissionLight, radius, center);
        this._material=new Material(0.8,0.2,200,0,0.2);
        this.vecLittleCircle = vecLittleCircle.normalize();
        this.oneColor=true;
    }

    /**
     * Overrides the getEmissionLight function to return the color to its location on the ball.
     * For each point on the ball, the function is checked if the vector coming from the center of the circle to a point
     * is in a spot that must be white or the color of the ball.
     * @param gp - the geoPoint the function find the emission light.
     * @return get Emission Light
     */
    @Override
    public Color getEmissionLight(GeoPoint gp) {

        //for the ball without white big circles on his side.
        if(this.oneColor==false)
        {
            //if the point is in the big white circle
            // angle between the vector vecBigCircle and the vector from the sphere center to the point is > COSBIGCIRCLE
            if (gp.getPoint().subtract(this.getCenter()).normalize().dotProduct(vecBigCircle)>=this.COSBIGCIRCLE ||
                    gp.getPoint().subtract(this.getCenter()).normalize().dotProduct(vecBigCircle.scale(-1))>=this.COSBIGCIRCLE)
                return (new Color(225,223,170));//return almost white
        }
        //for the all balls -if the point is in the little white circle
        //if the angle between the vector vecLittleCircle and the vector from the sphere center to the point(gp) is > COSBIGCIRCLE
        if(gp.getPoint().subtract(this.getCenter()).normalize().dotProduct(vecLittleCircle)>=this.COSLITTLECIRCLE||
                gp.getPoint().subtract(this.getCenter()).normalize().dotProduct(vecLittleCircle.scale(-1))>=this.COSLITTLECIRCLE)
            return (new Color(225,223,170));//return almost white
        return super.getEmissionLight();// return the color of the ball
    }
}
