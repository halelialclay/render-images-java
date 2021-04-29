package geometries;

import primitives.*;


import java.util.List;

/**
 * Intersectable is a common interface for all geometries that are able
 * to intersect from a ray to their entity (Shape)
 */
public interface Intersectable {

    /**
     * @param ray ray pointing toward a Gepmtry
     * @return List geoPoint return values
     */
    List<GeoPoint> findIntersections(Ray ray);

    /**
     *      * GeoPoint is just a tuple holding
     *      * references to a specific point ain a specific geometry
     */
    public static class GeoPoint {

        protected final Geometry _geometry;
        protected final Point3D point;

        public GeoPoint(Geometry _geometry, Point3D pt) {
            this._geometry = _geometry;
            point = pt;
        }
        //**************************getters**********************

        public Point3D getPoint() {
            return point;
        }

        public Geometry getGeometry() {
            return _geometry;
        }
    }

}