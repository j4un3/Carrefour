/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.model;

import be.esi.g34840.carrefour.concept.CarrefourVueInterface;
import be.esi.g34840.carrefour.concept.FeuEnum;
import be.esi.g34840.carrefour.dto.CarrefourEtat;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe Carrefour qui est le modele du programme
 *
 * @author g34840
 */
public final class Carrefour {

    public static int FEUX_PIETON_E_O = 3, FEUX_PIETON_N_S = 2,
            FEUX_VEHICULE_E_O = 1, FEUX_VEHICULE_N_S = 0;
    public static int AXE_N_S = 0, AXE_E_O = 1;
    private CarrefourEtat etat;
    private Timer timer;
    private boolean warning;
    private int[] vertTimer, saveTimerVert, orangeTimer, saveTimerOrange, rougeTimer,
            saveTimerRouge;
    private List<CarrefourVueInterface> views;
    private int rougeCommun;
    private boolean accident;
    private boolean allRed;
    private String[] nomAxe;

    /**
     * Construit une instance de la classe Carrefour
     *
     * @param vert un tableau contenant la durée de vert de chaque feu
     * @param orange un tableau contenant la durée de orange de chaque feu
     * @param rouge un tableau contenant la durée de rouge de chaque feu
     * @param rougeCommun la durée du rouge commun (Très simpliste comparer aux
     * vrais carrefours)
     * @param vitesseExecution la durée d'une seconde du carrefour
     * @param nomAxe un tableau contenant les noms des axes
     */
    public Carrefour(int[] vert, int[] orange, int[] rouge, int rougeCommun, int vitesseExecution, String[] nomAxe) {
        views = new ArrayList<CarrefourVueInterface>();
        etat = new CarrefourEtat(new Date());
        this.saveTimerOrange = orange;
        this.saveTimerRouge = rouge;
        this.saveTimerVert = vert;
        this.rougeCommun = rougeCommun;
        this.saveTimerRouge[FEUX_VEHICULE_N_S] += rougeCommun;
        this.saveTimerRouge[FEUX_PIETON_E_O] += rougeCommun;
        this.nomAxe = nomAxe;
        this.reset();
        vitesseExec(vitesseExecution);
    }

    /**
     * Permet de donné la vitesse d'execution du modele
     *
     * @param ms la durée en ms
     */
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

    public String[] getNomAxe() {
        return nomAxe;
    }

    /**
     * Retourne l'état du carrefour
     *
     * @return etat l'état du carrefour
     */
    public CarrefourEtat getEtat() {
        return etat;
    }

    /**
     * Abonne un CarrefourVueInterface au modèle
     *
     * @param vue la vue qui s'abonne au modèle
     */
    public void abonne(CarrefourVueInterface vue) {
        views.add(vue);
        fire();
    }

    /**
     * Désabonne un CarrefourVueInterface au modèle
     *
     * @param vue la vue qui se désabonne au modèle
     */
    public void desabonne(CarrefourVueInterface vue) {
        views.remove(vue);
        fire();
    }

    /**
     * Le moteur du modele se lance après chaque ms seconde Permet de changer
     * l'état des feux selon la durée de la coleur de leurs feux
     */
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
        //Redemarre le modele apres une sauvegarde
        if(allRed && rougeTimer[FEUX_VEHICULE_N_S]<1 && rougeTimer[FEUX_VEHICULE_E_O]<1){
            reset();
        }else if (warning) {
            setWarning(warning);
        }
    }

    /**
     * Retourne vrai si tous les feux sont clignotant
     *
     * @return warning booléen vrai ou faux selon l'état du carrefour
     */
    public boolean isWarning() {
        return warning;
    }

    /**
     * Permet de mettre le carrefour en état tout feux clignotants
     *
     * @param warning la variable qui définit le warning
     */
    public void setWarning(boolean warning) {
        this.warning = warning;
        allRed = true;
        if (warning) {
            if (etat.getFeux(FEUX_VEHICULE_N_S).getValue() == 2 && etat.getFeux(FEUX_VEHICULE_E_O).getValue() == 2) {
                for (int i = 0; i < 4; i++) {
                    etat.setFeux(i, FeuEnum.WARNING);
                }
            }
        } else if(etat.getFeux(FEUX_VEHICULE_N_S).getValue()==5) {
            reset();
        }
    }

    /**
     * Retourne une représentation textuelle du modèle (Permet le débogage)
     *
     * @return str une représentation textuelle du modèle
     */
    @Override
    public String toString() {
        String str = "\n\n\n\n\n";
        str = str + "AXE------------N-S";
        str = str + "\nPietons : " + etat.getFeux(FEUX_PIETON_N_S).getLibelle();
        str = str + "\nVéhicules : " + etat.getFeux(FEUX_VEHICULE_N_S).getLibelle();
        str = str + "\nAXE------------E-O";
        str = str + "\nVéhicules : " + etat.getFeux(FEUX_VEHICULE_E_O).getLibelle();
        str = str + "\nPietons : " + etat.getFeux(FEUX_PIETON_E_O).getLibelle();
        return str;
    }

    /**
     * Methode qui régule le temps de vert d'un feux et change selon le feu
     *
     * @param pos la position du feu (0 pour Feu véhicule Nord-sud, 1 pour Feu
     * véhicule Est-Ouest, 2 pour Feu piéton Nord-Sud, 3 pour Feu piéton
     * Est-Ouest)
     */
    private void vert(int pos) {
        if (--vertTimer[pos] < 1) {
            if (pos == FEUX_PIETON_E_O || pos == FEUX_PIETON_N_S) {
                etat.setFeux(pos, FeuEnum.VERT_CLIGNOTANT);
            } else {
                etat.setFeux(pos, FeuEnum.ORANGE);
            }
        }
    }

    /**
     * Methode qui régule le temps de orange d'un feux et change selon le feu
     *
     * @param pos la position du feu (0 pour Feu véhicule Nord-sud, 1 pour Feu
     * véhicule Est-Ouest, 2 pour Feu piéton Nord-Sud, 3 pour Feu piéton
     * Est-Ouest)
     */
    private void orange(int pos) {
        if (--orangeTimer[pos] < 1) {
            etat.setFeux(pos, FeuEnum.ROUGE);
            if (pos == 1 || pos == 2) {
                rougeTimer[pos] = rougeTimer[pos] + rougeCommun;
            }
        }
    }

    /**
     * Methode qui régule le temps de rouge d'un feux et change selon le feu
     *
     * @param pos la position du feu (0 pour Feu véhicule Nord-sud, 1 pour Feu
     * véhicule Est-Ouest, 2 pour Feu piéton Nord-Sud, 3 pour Feu piéton
     * Est-Ouest)
     */
    private void rouge(int pos) {
        if (--rougeTimer[pos] < 1) {
            if (!allRed) {
                etat.setFeux(pos, FeuEnum.VERT);
                resetTimer(pos);
            }
        }
    }

    /**
     * Reset le modele
     */
    private void reset() {
        warning = false;
        accident = false;
        allRed = false;
        this.orangeTimer = this.saveTimerOrange.clone();
        this.vertTimer = this.saveTimerVert.clone();
        this.rougeTimer = this.saveTimerRouge.clone();
        etat.setFeux(FEUX_PIETON_N_S, FeuEnum.ROUGE);
        etat.setFeux(FEUX_PIETON_E_O, FeuEnum.VERT);
        etat.setFeux(FEUX_VEHICULE_N_S, FeuEnum.VERT);
        etat.setFeux(FEUX_VEHICULE_E_O, FeuEnum.ROUGE);
    }

    /**
     * Permet de notifier aux vues des changements du modèle
     */
    private void fire() {
        int i = 0;
        List<CarrefourVueInterface> viewsCopy = new ArrayList<CarrefourVueInterface>(views);
        int cpt = 0;
        for (CarrefourVueInterface vue : viewsCopy) {
            vue.update();
        }
    }

    /**
     * Remet les timers à l'état initiales
     *
     * @param pos
     */
    private void resetTimer(int pos) {
        vertTimer[pos] = this.saveTimerVert[pos];
        orangeTimer[pos] = this.saveTimerOrange[pos];
        rougeTimer[pos] = this.saveTimerRouge[pos];
    }

    /**
     * Stop le modèle
     */
    public void stop() {
        timer.cancel();
    }

    /**
     * Permet de réduire le temps d'attente d'un feu piéton
     *
     * @param feu le feu piéton
     */
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
            int vert = (vertTimer[FEUX_VEHICULE_E_O] - 3);
            if (vert > 0) {
                vertTimer[FEUX_VEHICULE_E_O] -= vert;
                vertTimer[FEUX_PIETON_N_S] -= vert;
                rougeTimer[FEUX_VEHICULE_N_S] -= vert;
                rougeTimer[FEUX_PIETON_E_O] -= vert;
            }
        }
    }

    /**
     * Methode qui verifie s'il y a un risque d'accident
     */
    private void verification() {
        if (etat.getFeux(FEUX_VEHICULE_N_S).getColor().equals(Color.green)) {
            if (etat.getFeux(FEUX_VEHICULE_E_O).getColor().equals(Color.green) || etat.getFeux(FEUX_VEHICULE_E_O).getColor().equals(Color.orange)) {
                accident = true;
            }
            if (etat.getFeux(FEUX_PIETON_N_S).getColor().equals(Color.green) || etat.getFeux(FEUX_PIETON_N_S).getColor().equals(Color.orange)) {
                accident = true;
            }
        }
        if (etat.getFeux(FEUX_VEHICULE_E_O).getColor().equals(Color.green)) {
            if (etat.getFeux(FEUX_VEHICULE_N_S).getColor().equals(Color.green) || etat.getFeux(FEUX_VEHICULE_N_S).getColor().equals(Color.orange)) {
                accident = true;
            }
            if (etat.getFeux(FEUX_PIETON_E_O).getColor().equals(Color.green) || etat.getFeux(FEUX_PIETON_E_O).getColor().equals(Color.orange)) {
                accident = true;
            }
        }
        if (etat.getFeux(FEUX_PIETON_N_S).getColor().equals(Color.green)) {
            if (etat.getFeux(FEUX_VEHICULE_N_S).getColor().equals(Color.green) || etat.getFeux(FEUX_VEHICULE_N_S).getColor().equals(Color.orange)) {
                accident = true;
            }
            if (etat.getFeux(FEUX_PIETON_E_O).getColor().equals(Color.green) || etat.getFeux(FEUX_PIETON_E_O).getColor().equals(Color.orange)) {
                accident = true;
            }
        }
        if (etat.getFeux(FEUX_PIETON_E_O).getColor().equals(Color.green)) {
            if (etat.getFeux(FEUX_VEHICULE_E_O).getColor().equals(Color.green) || etat.getFeux(FEUX_VEHICULE_E_O).getColor().equals(Color.orange)) {
                accident = true;
            }
            if (etat.getFeux(FEUX_PIETON_N_S).getColor().equals(Color.green) || etat.getFeux(FEUX_PIETON_N_S).getColor().equals(Color.orange)) {
                accident = true;
            }
        }
    }

    /**
     * Redémarre le modèle avec les nouveaux paramètres
     *
     * @param vert un tableau contenant la durée de vert de chaque feu
     * @param orange un tableau contenant la durée de orange de chaque feu
     * @param rouge un tableau contenant la durée de rouge de chaque feu
     * @param rougeCommun la durée du rouge commun (Très simpliste comparer aux
     * @param nomAxe un tableau contenant les noms des axes
     */
    public void reboot(int[] vert, int[] orange, int[] rouge, int rougeCommun, String[] nomAxe) {
        this.saveTimerVert = vert;
        this.saveTimerOrange = orange;
        this.saveTimerRouge = rouge;
        this.saveTimerRouge[FEUX_VEHICULE_N_S] += rougeCommun;
        this.saveTimerRouge[FEUX_PIETON_E_O] += rougeCommun;
        this.rougeCommun = rougeCommun;
        this.nomAxe = nomAxe;
        allRed = true;
    }

    /**
     * Retourne vrai si il y a un risque d'accident sinon faux
     *
     * @return accident booléen vrai ou faux selon le risque d'accident
     */
    public boolean checkAccident() {
        while (rougeTimer[FEUX_VEHICULE_N_S] > 1) {
            changeEtat();
            verification();
        }
        return accident;
    }
}
