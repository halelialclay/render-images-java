package elements;

/**
 * class Material-
 *
 * params:
 *  _kD,_kS,kT,kR,_nShininess
 */
public class Material {

    private double _kD;
    private double _kS;
    private double _kT;
    private double _kR;
    private int _nShininess;
//*********************constructors****************
    /**
     * constructor
     * @param kD-Attenuation factors
     * @param kS-Attenuation factors
     * @param kT- Refraction
     * @param kR- reflection
     * @param nShininess-The amount of light coming back from the pixel
     */
    public Material(double kD, double kS,int nShininess, double kT, double kR ) {

        this._kD = kD;
        this._kS = kS;
        this._kT = kT;
        this._kR = kR;
        this._nShininess = nShininess;

    }
    /**
     *  constructor
     * @param kD-Attenuation factors
     * @param kS-Attenuation factors
     * @param nShininess-The amount of light coming back from the pixel
     */
    public Material(double kD, double kS, int nShininess) {
       this(kD,kS,nShininess,0,0);

    }



    /**
     * COPY constructor
     * @param material-The material they are copying
     */

    public Material(Material material) {
        this(material._kD, material._kS, material._nShininess,material._kT,material._kR);
    }

    //*********get*****************

    public double getKd() {
        return _kD;
    }

    public double getKs() {
        return _kS;
    }

    public int getnShininess() {
        return _nShininess;
    }

    public double getKT() {
        return _kT;
    }

    public double getKR()
    {

        return _kR;
    }
}