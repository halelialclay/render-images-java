package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * The SpotLight object specifies an attenuated light source at a fixed point in space that radiates light
 * in a specified direction from the light source. A SpotLight has the same attributes as a PointLight node,
 * with the addition of the following:
 * <p>
 * Direction - The axis of the cone of light.
 * <p>
 * Concentration exponent - Specifies how quickly the light intensity attenuates.
 * It attenuates as a function of the angle of radiation as measured from the direction of radiation.
 * The light's intensity is highest at the center of the cone and is attenuated toward the edges of the cone
 * by the cosine of the angle between the direction of the light and the direction from the light
 * to the object being lit, raised to the power of the spot concentration exponent.
 * The higher the concentration value, the more focused the light source.
 * <p>
 * A spot light contributes to diffuse and specular reflections, which depend on the orientation and position
 * of an object's surface. A spot light does not contribute to ambient reflections.
 */
public class SpotLight extends PointLight {
    Vector _direction;
    double _concentration;

    /**
     * constructor
     * @param colorIntensity -the intensity of the spot light.
     * @param position - the position of the spot Light.
     * @param direction - the direction of the light of the spot light.
     * @param kC- the kC of the spot light.
     * @param kL-the kL of the spot Light
     * @param kQ- the kQ of the spot light.
     * @param concentration- the concentration of the spot light.
     */
    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ, double concentration) {
        super(colorIntensity, position, kC, kL, kQ);
        this._direction = new Vector(direction).normalized();
        this._concentration = concentration;
    }

    /**
     * constructor
     @param colorIntensity -the intensity of the spot light.
      * @param position - the position of the spot Light.
     * @param direction - the direction of the light of the spot light.
     * @param kC- the kC of the spot light.
     * @param kL-the kL of the spot Light
     * @param kQ- the kQ of the spot light.
     */

    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
        this(colorIntensity, position, direction, kC, kL, kQ, 1);
    }


    /**
     * @param p-the point on the geometry
     * @return spotlight intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        double projection = _direction.dotProduct(getL(p));

        if (Util.isZero(projection)) {
            return Color.BLACK;
        }
        double factor = Math.max(0, projection);
        Color pointlightIntensity = super.getIntensity(p);

        if (_concentration != 1) {
            factor = Math.pow(factor, _concentration);
        }

        return (pointlightIntensity.scale(factor));
    }
}