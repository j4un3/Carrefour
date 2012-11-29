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

    public static int FEUX_PIETON_N_S = 3, FEUX_PIETON_E_O = 2,
            FEUX_VEHICULE_E_O = 1, FEUX_VEHICULE_N_S = 0;
    private CarrefourEtat etat;
    private Timer timer;
    private boolean warning;
    private int cptTest;
    private int[] vertTimer, saveTimerVert, orangeTimer, saveTimerOrange, rougeTimer,
            saveTimerRouge, rougeCommunTimer, saveRougeCommunTimer;
    private List<CarrefourVueInterface> views;

    public Carrefour(int[] vert, int[] orange, int[] rouge, int[] rougeCommun) {
        views = new ArrayList<CarrefourVueInterface>();
        etat = new CarrefourEtat();
        this.saveTimerOrange = orange;
        this.saveTimerRouge = rouge;
        this.saveTimerVert = vert;
        warning = false;
        this.reset();
        vitesseExec(1000);
    }

    public void vitesseExec(int ms) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                changeEtat();
            }
        }, 0, ms);
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
        for (int i = 0; i < 4; i++) {
            switch (etat.getFeux(i).getValue()) {
                case 0:
                    vert(i);
                    break;
                case 1:
                    orange(i);
                    break;
                case 2:
                    rouge(i);
                    break;
                case 3:
                    orange(i);
            }
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

    private void vert(int pos) {
        if (--vertTimer[pos] < 1) {
            if (pos == FEUX_PIETON_E_O || pos == FEUX_PIETON_N_S) {
                etat.setFeux(pos, FeuEnum.VERT_CLIGNOTANT);
            } else {
                etat.setFeux(pos, FeuEnum.ORANGE);
            }
        }
    }

    private void orange(int pos) {
        if (--orangeTimer[pos] < 1) {
            etat.setFeux(pos, FeuEnum.ROUGE);
        }
    }

    private void rouge(int pos) {
        if (--rougeTimer[pos] < 1) {
            etat.setFeux(pos, FeuEnum.VERT);
            resetTimer(pos);
        }
    }

    private void reset() {
        this.orangeTimer = this.saveTimerOrange.clone();
        this.vertTimer = this.saveTimerVert.clone();
        this.rougeTimer = this.saveTimerRouge.clone();
        etat.setFeux(FEUX_PIETON_N_S, FeuEnum.ROUGE);
        etat.setFeux(FEUX_PIETON_E_O, FeuEnum.VERT);
        etat.setFeux(FEUX_VEHICULE_N_S, FeuEnum.VERT);
        etat.setFeux(FEUX_VEHICULE_E_O, FeuEnum.ROUGE);
    }

    private void fire() {
        for (CarrefourVueInterface vue : views) {
            vue.update();
        }
    }

    private void resetTimer(int pos) {
        vertTimer[pos] = this.saveTimerVert[pos];
        orangeTimer[pos] = this.saveTimerOrange[pos];
        rougeTimer[pos] = this.saveTimerRouge[pos];
    }
}
