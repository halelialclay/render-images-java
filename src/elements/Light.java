package elements;

import primitives.Color;

/**
 * class Light is abstract class- all the lights extend it.
 */
public abstract class Light {
    /**
     * _intensity value, set to protected
     *
     */
    protected Color _intensity;
//*************get*****************
    public Color getIntensity() {
        return new Color(_intensity);
    }
}