package geometries;

import elements.Material;
import primitives.Color;

public abstract class FlatGeometry extends Geometry{

    public FlatGeometry(Color emission, Material material) {
        super(emission, material);
    }

    public FlatGeometry(Color _emission) {
        super(_emission);
    }
    public FlatGeometry()
    {
        super();
    }
}
