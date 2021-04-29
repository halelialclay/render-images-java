package elements;

import primitives.Color;

/**
 * class AmbientLight
 * An ambient light source represents a fixed-intensity and
 * fixedcolor light source that affects all objects in the scene equally.
 * @author haleli,marom
 */
public class AmbientLight  extends  Light{
    /**
     *The function receives the light intensity and attenuation factor and performs scalar multiplication
     * @param ia - Lighting intensity
     * @param ka-Fixed attenuation
     */
    public AmbientLight(Color ia, double ka) {
        this._intensity = ia.scale(ka);
    }
}