package primitives;

import static primitives.Util.isZero;

/**
 * Ray class
 */
public class Ray {

    /**
     * The point from which the ray starts.
     */
    private final Point3D _point;
    /**
     * The direction of the ray.
     */
    private final Vector _direction;
    /**
     * constant for first moving rays size for shading rays, transparency and reflection
     */
    private static final double DELTA = 0.1;
    /**
     * Constructor for creating a new instance of this class
     * @param point the start of the ray.
     * @param direction the direction of the ray.
     */
    public Ray(Point3D point, Vector direction) {
        _point = new Point3D(point);
        _direction = new Vector(direction).normalized();
    }
    public Ray(Point3D point, Vector direction,Vector normal) {
        double nv=normal.dotProduct(direction.normalize());
        if(nv>0)
            _point = new Point3D(point.add(normal.scale(DELTA)));
        else
            _point = point.add(normal.scale(DELTA*(-1)));


        _direction = new Vector(direction).normalized();
    }

    /**
     * @param length - the function find the point on the ray with this distance from the ray start point
     * @return new Point3D- the point on the ray with length distance from the ray start point
     */
    public Point3D getTargetPoint(double length) {
        return isZero(length ) ? _point : _point.add(_direction.scale(length));
    }


    /**
     * Copy constructor for a deep copy of an Ray object.
     * @param other the object that being copied
     */
    public Ray(Ray other) {
        this._point = new Point3D(other._point);
        this._direction = other._direction.normalized();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return  false;
        }
        if (!(obj instanceof Ray)) {
            return false;
        }
        if (this == obj)
            return true;
        Ray other = (Ray)obj;
        return (_point.equals(other._point) &&
                _direction.equals(other._direction));
    }

    @Override
    public String toString() {
        return "point: " + _point + ", direction: " + _direction;
    }

    /**
     * Getter for the point from which the ray starts.
     * @return A new Point3D that represents the
     * point from which the ray starts.
     */
    public Point3D getPoint() {
        return new Point3D(_point);
    }

    /**
     * Getter for the direction of the ray that is
     * represented by this object.
     * @return A new Vector that represents the
     * direction of the ray that is
     * represented by this object.
     */
    public Vector getDirection() {
        return new Vector(_direction);
    }
}