package scene;

import elements.*;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * class Scene -Creating all parts of the scene: lighting, geometry, background color and camera
 *
 * Within a class you can add objects to delete objects, add lighting fixtures
 */
public class Scene {
    //name of scene
    private final String _name;
    private final Geometries _geometries = new Geometries();

    private Color _background;
    private Camera _camera;
    private double _distance;
    private AmbientLight _ambientLight;
    private List<LightSource> _lights = null;

//****************getters**********************
    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public Camera getCamera() {
        return _camera;
    }

    public Geometries getGeometries() {
        return _geometries;
    }

    public double getDistance() {
        return _distance;
    }

    public Color getBackground() {
        return this._background;
    }

    public List<LightSource> getLightSources() {
        return _lights;
    }

    /**
     * constructor
     * @param _sceneName the name of the scene
     */

    public Scene(String _sceneName) {
        this._name = _sceneName;
    }


    /**
     * add Geometries of the scene
     * @param intersectables - is a list of geometries
     */
    public void addGeometries(Intersectable... intersectables) {
        for (Intersectable i : intersectables) {
            _geometries.add(i);
        }
    }
    /**
     * remove Geometries of the scene
     * @param intersectables - is a list of geometries
     */
    public void removeGeometries(Intersectable... intersectables) {
        for (Intersectable i : intersectables) {
            _geometries.remove(i);
        }
    }

    /**
     *add Lights Geometries of the scene
     * @param light add from the scene
     */
    public void addLights(LightSource light) {
        if (_lights == null) {
            _lights = new LinkedList<>();
        }
        _lights.add(light);
    }

    public void setBackground(Color _background) {
        this._background = _background;
    }

    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    public void setDistance(double _distance) {
        this._distance = _distance;
    }

    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public void setLights(List<LightSource> _lights) {
        this._lights = _lights;
    }


    public static class SceneBuilder {
        private String name;
        private Color background;
        private Camera camera;
        private double distance;
        private AmbientLight ambientLight;

        public SceneBuilder(String name) {
            this.name = name;
        }

        public SceneBuilder addBackground(Color background) {
            this.background = background;
            return this;
        }

        public SceneBuilder addCamera(Camera camera) {
            this.camera = camera;
            return this;
        }

        public SceneBuilder addDistance(double distance) {
            this.distance = distance;
            return this;
        }

        public SceneBuilder addAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public Scene build() {
            Scene scene = new Scene(this.name);
            scene._camera = this.camera;
            scene._distance = this.distance;
            scene._background = this.background;
            scene._ambientLight = this.ambientLight;
            validateUserObject(scene);
            return scene;
        }

        private void validateUserObject(Scene scene) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }
}