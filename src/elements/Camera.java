package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * class camera
 *
 *  _p0,_vTo, _vUp ,_vRight,_apertureSize,_focalLength,DepthOfFiled
 *  @author haleli marom
 */
public class Camera {
    Point3D _p0;
    Vector _vTo;
    Vector _vUp;
    Vector _vRight;
   double _apertureSize;
   double _focalLength;
   int numberOfRays;
    /**
     *render with depth of filed when DepthOfFiled=true;
     */
    private boolean DepthOfFiled;
    /**
     * constructor
     * @param _p0-the position of the camera
     * @param _vTo-the forward vector
     * @param _vUp-the up vector
     */
    public Camera(Point3D _p0, Vector _vTo, Vector _vUp) {

        this(_p0,_vTo,_vUp,1,1,false);

    }

    /**
     * constructor
     * @param _p0-the position of the camera
     * @param _vTo-the forward vector
     * @param _vUp-the up vector
     * @param _apertureSize-the size of the aperture of the camera for depth of field
     * @param _focalLength- the distance of the focal plan from  the camera
     * @param DepthOfFiled-if true use the Depth Of Filed
     */
    public Camera(Point3D _p0, Vector _vTo, Vector _vUp,double _apertureSize,double _focalLength,boolean DepthOfFiled ) {
        //if the the vectors are not orthogonal, throw exception.
        if (_vUp.dotProduct(_vTo) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");

        this._p0 = new Point3D(_p0);
        this._vTo = _vTo.normalized();
        this._vUp = _vUp.normalized();

        _vRight = this._vTo.crossProduct(this._vUp).normalize();
        this._apertureSize=_apertureSize;
        this._focalLength=_focalLength;
        this.DepthOfFiled=DepthOfFiled;
    }

    public Camera(Point3D _p0, Vector _vTo, Vector _vUp, double _apertureSize, double _focalLength, boolean depthOfFiled, int numberOfRays) {
        this._p0 = _p0;
        this._vTo = _vTo;
        this._vUp = _vUp;
        _vRight = this._vTo.crossProduct(this._vUp).normalize();
        this._apertureSize = _apertureSize;
        this._focalLength = _focalLength;
        this.numberOfRays = numberOfRays;
        DepthOfFiled = depthOfFiled;
    }




    //*********************get***********************
    public Point3D getP0() {
        return new Point3D(_p0);
    }

    public Vector getVTo() {
        return new Vector(_vTo);
    }

    public Vector getVUp() {
        return new Vector(_vUp);
    }

    public Vector getVRight() {
        return new Vector(_vRight);
    }

    public double getApertureSize() { return _apertureSize; }

    public double getFocalLength() {
        return _focalLength;
    }

//**************rays constructors ************************

    /**
     * Constructing a ray through a pixel
     * @param nX - the number of columns
     * @param nY -the number of rows
     * @param j -the column of pixel
     * @param i -the row of pixel
     * @param screenDistance- the distance from the camera to the view plane
     * @param screenWidth -the size of the screen width
     * @param screenHeight - the size of the screen height
     * @return ray Through Pixel
     */

    public Ray constructRayThroughPixel(int nX, int nY,
                                        int j, int i, double screenDistance,
                                        double screenWidth, double screenHeight) {
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        Point3D Pc = _p0.add(_vTo.scale(screenDistance));

        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;

        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = Pc;

        if (!isZero(xj)) {
            Pij = Pij.add(_vRight.scale(xj));
        }
        if (!isZero(yi)) {
            Pij = Pij.subtract(_vUp.scale(yi)); // Pij.add(_vUp.scale(-yi))
        }

        Vector Vij = Pij.subtract(_p0);

        return new Ray(_p0, Vij);

    }

    public boolean isDepthOfFiled() {
        return DepthOfFiled;
    }
    /**
     * construct list of rays Through Pixel
     * call constructRaysForDepthOfField
     @param nX - the number of columns
      * @param nY -the number of rows
     * @param j -the column of pixel
     * @param i -the row of pixel
     * @param screenDistance- the distance from the camera to the view plane
     * @param screenWidth -the size of the screen width
     * @param screenHeight - the size of the screen height
     * @return list of rays throw the focal point
     */
   public List<Ray> constructRaysThroughPixel(int nX, int nY,
                                              int j, int i, double screenDistance,
                                              double screenWidth, double screenHeight)
    {
        List<Ray> rays=new ArrayList<>();
        //find the ray through pixel.
        Ray ray=constructRayThroughPixel( nX,  nY, j,  i,  screenDistance,
                screenWidth,  screenHeight);

        if(this.DepthOfFiled==false) //do'nt activate the effect depth of field
            return List.of( ray);  //the one original ray


        Point3D edgePoint=_p0.add(_vUp.scale((_apertureSize/2)).subtract(_vRight.scale((_apertureSize/2))));
        return constructRaysForDepthOfField(ray,edgePoint,_apertureSize);//(ray,screenDistance); //activate the effect depth of field

    }

    /**
     * find Focal Point with ray, by using law of cosines on the triangle that the ray and vto create.
     * @param ray-The ray that passes through the middle of pixel.
     * @return focal point
     */
    public Point3D findFocalPoint( Ray ray)
    {

        double cosine=_vTo.dotProduct(ray.getDirection()); //the cosine of the angle between vto and the vector of ray from pixel
        double distance=this.getFocalLength()/cosine; //the length of the ray from the camera to the focal point by using the law of cosines
        Point3D focalPoint=ray.getTargetPoint(distance); //find focal point by the ray and the distance from camera.
        return focalPoint;
    }
    /**
     *construct Rays For Depth Of Field and with performance improvement(ADAPTIVE SUPERSAMPLING)
     * The function gets a the up and right point of the square
     * and a length of the square sizes (the length and the location of the point depend on the calcColor recursion),
     * which creates the three others edges. and return the rays from them to the focal point
     * @param ray- the original ray middle from the of pixel
     * @param p1- the up and right point of the square
     * @param _apertureSize- the size of the aperture
     * @return list of 5 rays, four from the edges and one is the original ray from the middle of pixel(for the recursive)
     */
    public List<Ray> constructRaysForDepthOfField(Ray ray,Point3D p1,double _apertureSize )
    {

        Point3D focalPoint=this.findFocalPoint(ray); //find the focal point
        List<Ray> rays=new ArrayList<>();

        //p1 is the left up edge of the square
        Point3D p2=p1.add(_vRight.scale(_apertureSize));//p2 is at the right up edge.
        Point3D p3=p2.subtract(_vUp.scale(_apertureSize));//p3 is at the right down edge.
        Point3D p4=p3.subtract(_vRight.scale(_apertureSize));//p4 is at the left down edge.

        //create ray from every one of the four points to the focal point.
        rays.add(new Ray(p1,new Vector(focalPoint.subtract(p1))));
        rays.add(new Ray(p2,new Vector(focalPoint.subtract(p2))));
        rays.add(new Ray(p3,new Vector(focalPoint.subtract(p3))));
        rays.add(new Ray(p4,new Vector(focalPoint.subtract(p4))));
        rays.add(ray); //the original ray of the pixel.

        return rays;
    }

    /**
     * construct Rays For Depth Of Field and without performance improvement(ADAPTIVE SUPERSAMPLING)
     * @param ray- the ray from the middle of pixel
     * @param screenDistance-The distance between the camera and the viewing plane
     * @return list of rays
     */
    public List<Ray> constructRaysForDepthOfField(Ray ray,double screenDistance)
    {
        Point3D focalPoint=this.findFocalPoint(ray);//find focal point of ray
        //width and height are the size of the aperture
        double width = this._apertureSize;
        double height = this._apertureSize;

        //Nx and Ny are the width and height of the number of rays.
        int Nx = numberOfRays; //columns
        int Ny = numberOfRays; //rows
        // the right down edge of the rect around the camera.
        Point3D p1=_p0.subtract(_vRight.scale((double)width/2d)).subtract(_vUp.scale((double)height/2d));

        //list of the rays through the focal point.
        List<Ray> rays=new ArrayList<>();
        //create grid around the camera (numberOfRays X numberOfRays) and construct rays from the middle of every square.
        for (int row = 0; row < Ny; ++row) {
            for (int column = 0; column < Nx; ++column) {
                Point3D point= p1.add(_vRight.scale((0.5+row)*(height/Ny))).add(_vUp.scale((0.5+column)*(width/Nx)));// the point at the middle of every square
                Vector vector=focalPoint.subtract(point); //the vector from the point to the focal point
                Ray ray1= new Ray(point, vector);//the new ray that start in the point and have the direction of the vector.
                rays.add(ray1);
            }
        }
        return rays;
    }
}