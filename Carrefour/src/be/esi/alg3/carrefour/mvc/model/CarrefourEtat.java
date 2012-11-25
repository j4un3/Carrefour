/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.alg3.carrefour.mvc.model;

import be.esi.alg3.carrefour.mvc.concept.FeuEnum;
import java.io.Serializable;

/**
 *
 * @author user0
 */
public class CarrefourEtat implements Serializable {

    private FeuEnum[] feux;
    public static int FEUX_PIETON_N_S = 3;
    public static int FEUX_PIETON_E_O = 2;
    public static int FEUX_VEHICULE_N_S = 0;
    public static int FEUX_VEHICULE_E_O = 1;

    public CarrefourEtat() {
        feux = new FeuEnum[4];
    }

    public FeuEnum getFeux(int pos) {
        return feux[pos];
    }

    public void setFeux(int pos, FeuEnum feu) {
        feux[pos] = feu;
    }

    public CarrefourEtat(FeuEnum vehiculeNS, FeuEnum pietonNS, FeuEnum vehiculeEO, FeuEnum pietonEO) {
        this();
        feux[FEUX_PIETON_N_S] = pietonNS;
        feux[FEUX_PIETON_E_O] = pietonEO;
        feux[FEUX_VEHICULE_E_O] = vehiculeEO;
        feux[FEUX_VEHICULE_N_S] = vehiculeNS;
    }
}
