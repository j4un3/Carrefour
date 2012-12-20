/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.alg3.carrefour.mvc.model;

import be.esi.alg3.carrefour.mvc.concept.CarrefourVueInterface;
import be.esi.alg3.carrefour.mvc.concept.FeuEnum;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author g34840
 */
public final class Carrefour {

    public static int FEUX_PIETON_E_O = 3, FEUX_PIETON_N_S = 2,
            FEUX_VEHICULE_E_O = 1, FEUX_VEHICULE_N_S = 0;
    private CarrefourEtat etat;
    private Timer timer;
    private boolean warning;
    private int cptTest;
    private int[] vertTimer, saveTimerVert, orangeTimer, saveTimerOrange, rougeTimer,
            saveTimerRouge, rougeCommunTimer;
    private List<CarrefourVueInterface> views;
    private int rougeCommun;
    private boolean ok;
    private boolean allRed;

    public Carrefour(int[] vert, int[] orange, int[] rouge, int rougeCommun, int vitesseExecution) {
        views = new ArrayList<CarrefourVueInterface>();
        etat = new CarrefourEtat();
        this.saveTimerOrange = orange;
        this.saveTimerRouge = rouge;
        this.saveTimerVert = vert;
        this.rougeCommun = rougeCommun;
        this.saveTimerRouge[FEUX_VEHICULE_N_S] += rougeCommun;
        this.saveTimerRouge[FEUX_PIETON_E_O] += rougeCommun;
        warning = false;
        ok = true;
        this.reset();
        vitesseExec(vitesseExecution);
    }

    public void vitesseExec(int ms) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                changeEtat();
                fire();
            }
        }, 0, ms);
    }

    public CarrefourEtat getEtat() {
        return etat;
    }

    public void abonne(CarrefourVueInterface vue) {
        views.add(vue);
        fire();
    }

    public void desabonne(CarrefourVueInterface vue) {
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
    }

    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
        allRed = true;
        if (etat.getFeux(FEUX_PIETON_N_S).getValue() == 2
                && etat.getFeux(FEUX_PIETON_E_O).getValue() == 2
                && etat.getFeux(FEUX_VEHICULE_E_O).getValue() == 2
                && etat.getFeux(FEUX_VEHICULE_N_S).getValue() == 2) {
            if (warning) {
                for (int i = 0; i < 4; i++) {
                    etat.setFeux(i, FeuEnum.WARNING);
                }
            } else {
                reset();
            }
            fire();
        }
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
            if (pos == 1 || pos == 2) {
                rougeTimer[pos] = rougeTimer[pos] + rougeCommun;
            }
        }
    }

    private void rouge(int pos) {
        if (--rougeTimer[pos] < 1) {
            etat.setFeux(pos, FeuEnum.VERT);
            if (!allRed) {
                resetTimer(pos);
            }
        }
    }

    private void reset() {
        allRed = false;
        this.orangeTimer = this.saveTimerOrange.clone();
        this.vertTimer = this.saveTimerVert.clone();
        this.rougeTimer = this.saveTimerRouge.clone();
        etat.setFeux(FEUX_PIETON_N_S, FeuEnum.ROUGE);
        etat.setFeux(FEUX_PIETON_E_O, FeuEnum.VERT);
        etat.setFeux(FEUX_VEHICULE_N_S, FeuEnum.VERT);
        etat.setFeux(FEUX_VEHICULE_E_O, FeuEnum.ROUGE);
    }

    private void fire() {
        List<CarrefourVueInterface> viewsCopy = new ArrayList<CarrefourVueInterface>(views);
        for (CarrefourVueInterface vue : viewsCopy) {
            vue.update();
        }
    }

    private void resetTimer(int pos) {
        vertTimer[pos] = this.saveTimerVert[pos];
        orangeTimer[pos] = this.saveTimerOrange[pos];
        rougeTimer[pos] = this.saveTimerRouge[pos];
    }

    public void stop() {
        timer.cancel();
    }

    public void poussoir(int feu) {
        if (feu == FEUX_PIETON_N_S) {
            int vert = (vertTimer[FEUX_VEHICULE_N_S] - 3);
            if (vert > 0) {
                rougeTimer[FEUX_PIETON_N_S] -= vert;
                rougeTimer[FEUX_VEHICULE_E_O] -= vert;
                vertTimer[FEUX_VEHICULE_N_S] -= vert;
                vertTimer[FEUX_PIETON_E_O] -= vert;
            }
        } else {
            int vert = (vertTimer[FEUX_PIETON_E_O] - 3);
            if (vert > 0) {
                vertTimer[FEUX_VEHICULE_E_O] -= vert;
                vertTimer[FEUX_PIETON_N_S] -= vert;
                rougeTimer[FEUX_VEHICULE_N_S] -= vert;
                rougeTimer[FEUX_PIETON_E_O] -= vert;
            }
        }
    }

    private void verification() {
        if (etat.getFeux(FEUX_VEHICULE_N_S).getColor().equals(Color.green)) {
            if (etat.getFeux(FEUX_VEHICULE_E_O).getColor().equals(Color.green) || etat.getFeux(FEUX_VEHICULE_E_O).getColor().equals(Color.orange)) {
                ok = false;
            }
            if (etat.getFeux(FEUX_PIETON_N_S).getColor().equals(Color.green) || etat.getFeux(FEUX_PIETON_N_S).getColor().equals(Color.orange)) {
                ok = false;
            }
        }
        if (etat.getFeux(FEUX_VEHICULE_E_O).getColor().equals(Color.green)) {
            if (etat.getFeux(FEUX_VEHICULE_N_S).getColor().equals(Color.green) || etat.getFeux(FEUX_VEHICULE_N_S).getColor().equals(Color.orange)) {
                ok = false;
            }
            if (etat.getFeux(FEUX_PIETON_E_O).getColor().equals(Color.green) || etat.getFeux(FEUX_PIETON_E_O).getColor().equals(Color.orange)) {
                ok = false;
            }
        }
        if (etat.getFeux(FEUX_PIETON_N_S).getColor().equals(Color.green)) {
            if (etat.getFeux(FEUX_VEHICULE_N_S).getColor().equals(Color.green) || etat.getFeux(FEUX_VEHICULE_N_S).getColor().equals(Color.orange)) {
                ok = false;
            }
            if (etat.getFeux(FEUX_PIETON_E_O).getColor().equals(Color.green) || etat.getFeux(FEUX_PIETON_E_O).getColor().equals(Color.orange)) {
                ok = false;
            }
        }
        if (etat.getFeux(FEUX_PIETON_E_O).getColor().equals(Color.green)) {
            if (etat.getFeux(FEUX_VEHICULE_E_O).getColor().equals(Color.green) || etat.getFeux(FEUX_VEHICULE_E_O).getColor().equals(Color.orange)) {
                ok = false;
            }
            if (etat.getFeux(FEUX_PIETON_N_S).getColor().equals(Color.green) || etat.getFeux(FEUX_PIETON_N_S).getColor().equals(Color.orange)) {
                ok = false;
            }
        }
    }

    public void reboot(int[] vert, int[] orange, int[] rouge, int rougeCommun) {
        this.saveTimerVert = vert;
        this.saveTimerOrange = orange;
        this.saveTimerRouge = rouge;
        this.saveTimerRouge[FEUX_VEHICULE_N_S] += rougeCommun;
        this.saveTimerRouge[FEUX_PIETON_E_O] += rougeCommun;
        this.rougeCommun = rougeCommun;
        allRed = true;
        reset();
    }

    public boolean checkAccident() {
        while (rougeTimer[FEUX_VEHICULE_N_S] > 1) {
            changeEtat();
            verification();
        }
        return ok;
    }
}
