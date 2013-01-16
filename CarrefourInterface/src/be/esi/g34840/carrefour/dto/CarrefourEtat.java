/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.dto;

import be.esi.g34840.carrefour.concept.FeuEnum;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
  * Classe DTO(Data Transfert Object) qui permet de simplifier le transferts
 * de données entre les sous-systèmes de l'application
 * @author g34840
 */
public class CarrefourEtat extends CarrefourSimulation implements Serializable {

    private FeuEnum[] feux;
    public static int FEUX_PIETON_N_S = 2, FEUX_PIETON_E_O = 3,
            FEUX_VEHICULE_E_O = 1, FEUX_VEHICULE_N_S = 0;

    public CarrefourEtat(Date date) {
        super(date);
        feux = new FeuEnum[4];
        feux[FEUX_PIETON_N_S] = FeuEnum.ETEINT;
        feux[FEUX_PIETON_E_O] = FeuEnum.ETEINT;
        feux[FEUX_VEHICULE_E_O] = FeuEnum.ETEINT;
        feux[FEUX_VEHICULE_N_S] = FeuEnum.ETEINT;
    }

    public FeuEnum getFeux(int pos) {
        return feux[pos];
    }

    public void setFeux(int pos, FeuEnum feu) {
        feux[pos] = feu;
    }

    public CarrefourEtat(FeuEnum vehiculeNS, FeuEnum vehiculeEO, FeuEnum pietonNS, FeuEnum pietonEO,Date date) {
        this(date);
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
        if (this.feux[FEUX_PIETON_N_S].getValue() != other.feux[FEUX_PIETON_N_S].getValue()
                || this.feux[FEUX_PIETON_E_O].getValue() != other.feux[FEUX_PIETON_E_O].getValue()
                || this.feux[FEUX_VEHICULE_N_S].getValue() != other.feux[FEUX_VEHICULE_N_S].getValue()
                || this.feux[FEUX_VEHICULE_E_O].getValue() != other.feux[FEUX_VEHICULE_E_O].getValue()) {
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

    @Override
    public String toString() {
        return "CarrefourEtat{" + "Feux véhicule Nord-Sud=" + feux[FEUX_VEHICULE_N_S]
                + " Feux véhicule Est-Ouest= " + feux[FEUX_VEHICULE_E_O] + " Feu piéton Nord-Sud= " + feux[FEUX_PIETON_N_S]
                + " Feux piéton Est-Ouest= " + feux[FEUX_PIETON_E_O] + " Moment : "+ super.getDateFormat()+"}";
    }
}
