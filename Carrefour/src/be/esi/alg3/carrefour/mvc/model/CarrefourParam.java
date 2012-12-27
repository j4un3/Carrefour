/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.alg3.carrefour.mvc.model;

import java.io.Serializable;

/**
 *
 * @author J4un3
 */
public class CarrefourParam implements Serializable{

    private int feuVertVehiculeNordSud;
    private int feuVertVehiculeEstOuest;
    private int feuVertPietonNordSud;
    private int feuVertPietonEstOuest;
    private int feuOrangeVehiculeNordSud;
    private int hiculeEstOuest;
    private int feuOrangePietonNordSud;
    private int feuOrangeVehiculeEstOuest;
    private int etonEstOuest;
    private int feuRougeVehiculeNordSud;
    private int feuOrangePietonEstOuest;
    private int feuRougeVehiculeEstOuest;
    private int feuRougePietonNordSud;
    private int feuRougePietonEstOuest;
    private int feuRougeCommun;

    public CarrefourParam(int feuVertVehiculeNordSud, int feuVertVehiculeEstOuest, int feuVertPietonNordSud, int feuVertPietonEstOuest, int feuOrangeVehiculeNordSud, int feuOrangeVehiculeEstOuest, int feuOrangePietonNordSud, int feuOrangePietonEstOuest, int feuRougeVehiculeNordSud, int feuRougeVehiculeEstOuest, int feuRougePietonNordSud, int feuRougePietonEstOuest, int feuRougeCommun) {
        this.feuVertVehiculeNordSud = feuVertVehiculeNordSud;
        this.feuVertVehiculeEstOuest = feuVertVehiculeEstOuest;
        this.feuVertPietonNordSud = feuVertPietonNordSud;
        this.feuVertPietonEstOuest = feuVertPietonEstOuest;
        this.feuOrangeVehiculeNordSud = feuOrangeVehiculeNordSud;
        this.feuOrangeVehiculeEstOuest = feuOrangeVehiculeEstOuest;
        this.feuOrangePietonNordSud = feuOrangePietonNordSud;
        this.feuOrangePietonEstOuest = feuOrangePietonEstOuest;
        this.feuRougeVehiculeNordSud = feuRougeVehiculeNordSud;
        this.feuRougeVehiculeEstOuest = feuRougeVehiculeEstOuest;
        this.feuRougePietonNordSud = feuRougePietonNordSud;
        this.feuRougePietonEstOuest = feuRougePietonEstOuest;
        this.feuRougeCommun = feuRougeCommun;
    }

    public int getFeuVertVehiculeNordSud() {
        return feuVertVehiculeNordSud;
    }

    public void setFeuVertVehiculeNordSud(int feuVertVehiculeNordSud) {
        this.feuVertVehiculeNordSud = feuVertVehiculeNordSud;
    }

    public int getFeuVertVehiculeEstOuest() {
        return feuVertVehiculeEstOuest;
    }

    public void setFeuVertVehiculeEstOuest(int feuVertVehiculeEstOuest) {
        this.feuVertVehiculeEstOuest = feuVertVehiculeEstOuest;
    }

    public int getFeuVertPietonNordSud() {
        return feuVertPietonNordSud;
    }

    public void setFeuVertPietonNordSud(int feuVertPietonNordSud) {
        this.feuVertPietonNordSud = feuVertPietonNordSud;
    }

    public int getFeuVertPietonEstOuest() {
        return feuVertPietonEstOuest;
    }

    public void setFeuVertPietonEstOuest(int feuVertPietonEstOuest) {
        this.feuVertPietonEstOuest = feuVertPietonEstOuest;
    }

    public int getFeuOrangeVehiculeNordSud() {
        return feuOrangeVehiculeNordSud;
    }

    public void setFeuOrangeVehiculeNordSud(int feuOrangeVehiculeNordSud) {
        this.feuOrangeVehiculeNordSud = feuOrangeVehiculeNordSud;
    }

    public int getHiculeEstOuest() {
        return hiculeEstOuest;
    }

    public void setHiculeEstOuest(int hiculeEstOuest) {
        this.hiculeEstOuest = hiculeEstOuest;
    }

    public int getFeuOrangePietonNordSud() {
        return feuOrangePietonNordSud;
    }

    public void setFeuOrangePietonNordSud(int feuOrangePietonNordSud) {
        this.feuOrangePietonNordSud = feuOrangePietonNordSud;
    }

    public int getFeuOrangeVehiculeEstOuest() {
        return feuOrangeVehiculeEstOuest;
    }

    public void setFeuOrangeVehiculeEstOuest(int feuOrangeVehiculeEstOuest) {
        this.feuOrangeVehiculeEstOuest = feuOrangeVehiculeEstOuest;
    }

    public int getEtonEstOuest() {
        return etonEstOuest;
    }

    public void setEtonEstOuest(int etonEstOuest) {
        this.etonEstOuest = etonEstOuest;
    }

    public int getFeuRougeVehiculeNordSud() {
        return feuRougeVehiculeNordSud;
    }

    public void setFeuRougeVehiculeNordSud(int feuRougeVehiculeNordSud) {
        this.feuRougeVehiculeNordSud = feuRougeVehiculeNordSud;
    }

    public int getFeuOrangePietonEstOuest() {
        return feuOrangePietonEstOuest;
    }

    public void setFeuOrangePietonEstOuest(int feuOrangePietonEstOuest) {
        this.feuOrangePietonEstOuest = feuOrangePietonEstOuest;
    }

    public int getFeuRougeVehiculeEstOuest() {
        return feuRougeVehiculeEstOuest;
    }

    public void setFeuRougeVehiculeEstOuest(int feuRougeVehiculeEstOuest) {
        this.feuRougeVehiculeEstOuest = feuRougeVehiculeEstOuest;
    }

    public int getFeuRougePietonNordSud() {
        return feuRougePietonNordSud;
    }

    public void setFeuRougePietonNordSud(int feuRougePietonNordSud) {
        this.feuRougePietonNordSud = feuRougePietonNordSud;
    }

    public int getFeuRougePietonEstOuest() {
        return feuRougePietonEstOuest;
    }

    public void setFeuRougePietonEstOuest(int feuRougePietonEstOuest) {
        this.feuRougePietonEstOuest = feuRougePietonEstOuest;
    }

    public int getFeuRougeCommun() {
        return feuRougeCommun;
    }

    public void setFeuRougeCommun(int feuRougeCommun) {
        this.feuRougeCommun = feuRougeCommun;
    }
    
}
