/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.alg3.carrefour.mvc.model;

import be.esi.alg3.carrefour.mvc.concept.CarrefourModel;
import be.esi.alg3.carrefour.mvc.concept.FeuEnum;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author g34840
 */
public class Carrefour implements CarrefourModel {
    public static int FEUX_PIETON_N_S = 2;
    public static int FEUX_PIETON_E_O = 3;
    public static int FEUX_VEHICULE_N_S = 0;
    public static int FEUX_VEHICULE_E_O = 1;
    private Timer timer;
    private boolean warning;
    private int delay;
    private FeuEnum[] feux;
    
    public Carrefour(int vert, int orange, int rouge) {
        feux = new FeuEnum[4];
        warning = false;
        for (int i = 0; i < feux.length; i++) {
            feux[i] = FeuEnum.ETEINT;
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                changeEtat();
            }
        }, 0, 1000);
        
    }

    public void start() {
        warning = false;
        feux[FEUX_PIETON_N_S] = FeuEnum.VERT;
        feux[FEUX_PIETON_E_O] = FeuEnum.ROUGE;
        feux[FEUX_VEHICULE_N_S] = FeuEnum.ROUGE;
        feux[FEUX_VEHICULE_E_O] = FeuEnum.VERT;
    }

    private void changeEtat() {
        System.out.println(toString());

    }

    public void stop() {
        timer.cancel();
        warning = true;
    }
    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }

    public String toString() {
        String str = null;
        str = str +"\n------------N-S";
        str = str +"\nPietons : " + feux[FEUX_PIETON_N_S].getLibelle();
        str = str +"\nVéhicules : " + feux[FEUX_VEHICULE_N_S].getLibelle();
        str = str + "\n------------E-O";
        str = str +"\nPietons : " + feux[FEUX_PIETON_E_O].getLibelle();
        str = str +"\nVéhicules : " + feux[FEUX_VEHICULE_E_O].getLibelle();
        return str;
    }
    
}
