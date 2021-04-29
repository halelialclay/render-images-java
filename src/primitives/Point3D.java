package primitives;

/**
 * Class Point3D is the basic class representing a point in a
 * 3D system.
 *
 */
public class Point3D {
    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    public final static Point3D ZERO = new Point3D(0.0, 0.0, 0.0);

    /**
     *constructor
     * @param _x coordinate on the X axis
     * @param _y coordinate on the Y axis
     * @param _z coordinate on the Z axis
     */
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this._x = _x;
        this._y = _y;
        this._z = _z;
    }

    /**
     *copy constructor
     * @param p- point
     */
    public Point3D(Point3D p) {
        this._x = new Coordinate(p._x);
        this._y = new Coordinate(p._y);
        this._z = new Coordinate(p._z);
    }

    /**
     *constructor
     * @param _x double on the X axis
     * @param _y double on the Y axis
     * @param _z double on the Z axis
     */
    public Point3D(double _x, double _y, double _z) {
        this(new Coordinate(_x), new Coordinate(_y), new Coordinate(_z));
    }
//*********getters**************
    public Coordinate get_x() {
        return new Coordinate(_x);
    }

    public Coordinate get_y() {
        return new Coordinate(_y);
    }

    public Coordinate get_z() {
        return new Coordinate(_z);
    }

    /**
     *
     * @param other other point
     * @return distance Squared between tow points
     */
    public double distanceSquared(Point3D other)
    {
        return ( (other._x._coord - this._x._coord) * (other._x._coord - this._x._coord) +
                (other._y._coord - this._y._coord) * (other._y._coord - this._y._coord) +
                (other._z._coord - this._z._coord) * (other._z._coord - this._z._coord));
    }

    /**
     *
     * @param other other point
     * @return distance between tow points
     */
    public double distance (Point3D other){
        return Math.sqrt(distanceSquared(other));
    }

    /**
     * add Vector to point
     * @param v- Vector
     * @return new point
     */
    public Point3D add(Vector v) {
        return new Point3D(this._x._coord + v._head._x._coord,
                this._y._coord + v._head._y._coord,
                this._z._coord + v._head._z._coord);
    }

    /**
     * subtract Vector to point
     * @param v- Vector
     * @return new point
     */

    public Point3D subtract(Vector v) {
        return new Point3D(this._x._coord - v._head._x._coord,
                this._y._coord - v._head._y._coord,
                this._z._coord - v._head._z._coord);
    }
    /**
     * subtract point to point
     * @param p- point
     * @return new vector
     */
    public Vector subtract(Point3D p) {
        return new Vector(new Point3D(
                this._x._coord - p._x._coord,
                this._y._coord - p._y._coord,
                this._z._coord - p._z._coord));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) &&
                _y.equals(point3D._y) &&
                _z.equals(point3D._z);
    }

    @Override
    public String toString() {
        return "(" +
                _x +
                ", " + _y +
                ", " + _z +
                ')';
    }


}