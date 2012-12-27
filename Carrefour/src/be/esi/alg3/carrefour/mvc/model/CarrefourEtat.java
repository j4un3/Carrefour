/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.alg3.carrefour.mvc.model;

import be.esi.alg3.carrefour.mvc.concept.FeuEnum;
import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author user0
 */
public class CarrefourEtat implements Serializable {

    private FeuEnum[] feux;
    public static int FEUX_PIETON_N_S = 2, FEUX_PIETON_E_O = 3,
            FEUX_VEHICULE_E_O = 1, FEUX_VEHICULE_N_S = 0;

    public CarrefourEtat() {
        feux = new FeuEnum[4];
    }

    public FeuEnum getFeux(int pos) {
        return feux[pos];
    }

    public void setFeux(int pos, FeuEnum feu) {
        feux[pos] = feu;
    }

    public CarrefourEtat(FeuEnum vehiculeNS, FeuEnum vehiculeEO, FeuEnum pietonNS, FeuEnum pietonEO) {
        this();
        feux[FEUX_PIETON_N_S] = pietonNS;
        feux[FEUX_PIETON_E_O] = pietonEO;
        feux[FEUX_VEHICULE_E_O] = vehiculeEO;
        feux[FEUX_VEHICULE_N_S] = vehiculeNS;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarrefourEtat other = (CarrefourEtat) obj;
        if (this.feux[FEUX_PIETON_N_S].getValue() != other.feux[FEUX_PIETON_N_S].getValue() ||
                this.feux[FEUX_PIETON_E_O].getValue() != other.feux[FEUX_PIETON_E_O].getValue() ||
                        this.feux[FEUX_VEHICULE_N_S].getValue() != other.feux[FEUX_VEHICULE_N_S].getValue() ||
                        this.feux[FEUX_VEHICULE_E_O].getValue() != other.feux[FEUX_VEHICULE_E_O].getValue()){
            return false;
        }
        return true;
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Arrays.deepHashCode(this.feux);
        return hash;
    }
    
}
