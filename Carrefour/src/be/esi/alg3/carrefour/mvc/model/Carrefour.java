/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.alg3.carrefour.mvc.model;

import be.esi.alg3.carrefour.mvc.concept.CarrefourVueInterface;
import be.esi.alg3.carrefour.mvc.concept.FeuEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author g34840
 */
public class Carrefour {

    public static int FEUX_PIETON_N_S = 3;
    public static int FEUX_PIETON_E_O = 2;
    public static int FEUX_VEHICULE_N_S = 0;
    public static int FEUX_VEHICULE_E_O = 1;
    public static int VERT = 0;
    public static int ORANGE = 1;
    public static int ROUGE = 2;
    private CarrefourEtat etat;
    private Timer timer;
    private boolean warning;
    private int[] timers;
    private int cptTest;
    private int vertTimer;
    private int orangeTimer;
    private int rougeTimer;
    private List<CarrefourVueInterface> views;

    public Carrefour(int vert, int orange, int rouge) {
        views = new ArrayList<CarrefourVueInterface>();
        etat = new CarrefourEtat();
        timers = new int[4];
        this.orangeTimer = orange;
        this.vertTimer = vert;
        this.rougeTimer = rouge;
        warning = false;
        this.reset();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                changeEtat();
            }
        }, 0, 1000);
    }

    public CarrefourEtat getEtat() {
        return etat;
    }

    public void inscription(CarrefourVueInterface vue) {
        views.add(vue);
        fire();
    }

    public void desinscription(CarrefourVueInterface vue) {
        views.remove(vue);
        fire();
    }

    private void changeEtat() {
        if (timers[0] > 1 || timers[1] > 1 || timers[2] > 1) {
            switch (etat.getFeux(FEUX_VEHICULE_N_S).getValue()) {
                case 0:
                    vert();
                    break;
                case 1:
                    orange();
                    break;
                case 2:
                    rouge();
                    break;
            }
        } else {
            reset();
        }
        System.out.println(toString());
        fire();
    }

    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
        if (warning) {
            for (int i = 0; i < 4; i++) {
                etat.setFeux(i, FeuEnum.WARNING);
            }
        } else {
            reset();
        }
        fire();
    }

    @Override
    public String toString() {
        String str = "\n\n\n\n\n" + ++cptTest;
        str = str + "AXE------------N-S";
        str = str + "\nPietons : " + etat.getFeux(FEUX_PIETON_N_S).getLibelle();
        str = str + "\nVéhicules : " + etat.getFeux(FEUX_VEHICULE_N_S).getLibelle();
        str = str + "\nAXE------------E-O";
        str = str + "\nVéhicules : " + etat.getFeux(FEUX_VEHICULE_E_O).getLibelle();
        str = str + "\nPietons : " + etat.getFeux(FEUX_PIETON_E_O).getLibelle();
        return str;
    }

    private void vert() {
        etat.setFeux(FEUX_PIETON_E_O, FeuEnum.VERT);
        if (timers[VERT] <= (vertTimer / 3)) {
            etat.setFeux(FEUX_PIETON_E_O, FeuEnum.VERT_CLIGNOTANT);
        }
        if (--timers[VERT] < 1) {
            etat.setFeux(FEUX_VEHICULE_N_S, FeuEnum.ORANGE);
            etat.setFeux(FEUX_PIETON_E_O, FeuEnum.ROUGE);
        }
    }

    private void orange() {
        if (--timers[ORANGE] < 1) {
            etat.setFeux(FEUX_VEHICULE_N_S, FeuEnum.ROUGE);
        }
    }

    private void rouge() {
        etat.setFeux(FEUX_VEHICULE_E_O, FeuEnum.VERT);
        etat.setFeux(FEUX_PIETON_N_S, FeuEnum.VERT);
        if (timers[ROUGE] <= (rougeTimer / 3)) {
            etat.setFeux(FEUX_PIETON_N_S, FeuEnum.VERT_CLIGNOTANT);
        }
        if (timers[ROUGE] <= (rougeTimer / 4)) {
            etat.setFeux(FEUX_VEHICULE_E_O, FeuEnum.ORANGE);
            etat.setFeux(FEUX_PIETON_N_S, FeuEnum.ROUGE);
        }
        if (--timers[ROUGE] < 1) {
            etat.setFeux(FEUX_VEHICULE_E_O, FeuEnum.ROUGE);
        }
    }

    private void reset() {
        timers[VERT] = this.vertTimer;
        timers[ORANGE] = this.orangeTimer;
        timers[ROUGE] = this.rougeTimer;
        etat.setFeux(FEUX_PIETON_N_S, FeuEnum.ROUGE);
        etat.setFeux(FEUX_PIETON_E_O, FeuEnum.ROUGE);
        etat.setFeux(FEUX_VEHICULE_N_S, FeuEnum.VERT);
        etat.setFeux(FEUX_VEHICULE_E_O, FeuEnum.ROUGE);
    }

    private void fire() {
        for (CarrefourVueInterface vue : views) {
            vue.update();
        }
    }
}
