/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.implementation;

import be.esi.alg3.carrefour.mvc.concept.CarrefourVueInterface;
import be.esi.alg3.carrefour.mvc.concept.FeuEnum;
import be.esi.alg3.carrefour.mvc.model.Carrefour;
import be.esi.alg3.carrefour.mvc.model.CarrefourEtat;
import be.esi.g34840.carrefour.business.CarrefourClientInterface;
import be.esi.g34840.carrefour.business.CarrefourServeurInterface;
import be.esi.gui.outils.MsgOutils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author g34840
 */
public class ServeurImplementation extends java.rmi.server.UnicastRemoteObject implements CarrefourVueInterface, CarrefourServeurInterface {

    private Carrefour model;
    private List<CarrefourClientInterface> views;
    private Properties defaultProps;
    private FileInputStream in;
    private int[] rouge, orange, vert;
    private int rougeCommun;

    public ServeurImplementation() throws RemoteException {
        try {
            defaultProps = new Properties();
            in = new FileInputStream("../CarrefourInterface.properties");
            defaultProps.load(in);
            vert = new int[]{Integer.parseInt((String) defaultProps.getProperty("v1")),
                Integer.parseInt((String) defaultProps.get("v2")),
                Integer.parseInt((String) defaultProps.getProperty("vp1")),
                Integer.parseInt((String) defaultProps.getProperty("vp2"))};
            orange = new int[]{Integer.parseInt((String) defaultProps.getProperty("o1")),
                Integer.parseInt((String) defaultProps.get("o2")),
                Integer.parseInt((String) defaultProps.getProperty("op1")),
                Integer.parseInt((String) defaultProps.getProperty("op2"))};
            rouge = new int[]{Integer.parseInt((String) defaultProps.getProperty("r1")),
                Integer.parseInt((String) defaultProps.get("r2")),
                Integer.parseInt((String) defaultProps.getProperty("rp1")),
                Integer.parseInt((String) defaultProps.getProperty("rp2"))};
            rougeCommun =Integer.parseInt((String) defaultProps.getProperty("rc1"));
        } catch (FileNotFoundException ex) {
            MsgOutils.erreur("FileNotFoundException", "Fichier de configuration "
                    + "introuvable.\n Le serveur sera lancé avec une configuration par défaut.");
            defaultInit();
        } catch (IOException ex) {
            MsgOutils.erreur("IOException", "L'ouverture ou la fermeture du"
                    + " fichier de configuration s'est mal passée."
                    + "\n Le serveur sera lancé avec une configuration par défaut.");
            defaultInit();
        }
        model = new Carrefour(vert, orange, rouge, rougeCommun, 1000);
        views = new ArrayList<CarrefourClientInterface>();
        model.abonne(this);
    }

    private void defaultInit() {
        vert = new int[]{10, 10, 10, 10};
        orange = new int[]{5, 5, 5, 5};
        rouge = new int[]{17, 17, 17, 17};
        rougeCommun = 2;
    }

    private void fire() {
        try {
            for (CarrefourClientInterface uneVue : views) {
                try {
                    uneVue.update();
                } catch (RemoteException exe) {
                    try {
                        desabonne(uneVue);
                    } catch (RemoteException ex) {
                        MsgOutils.erreur("RemoteException", "Impossible de désabonner la vue : " + ex.getMessage());
                    }
                }
            }
        } catch (ConcurrentModificationException ex) {
            fire();
        }
    }

    @Override
    public void warning(boolean warning) throws RemoteException {
        model.setWarning(warning);
    }

    @Override
    public CarrefourEtat getEtat() throws RemoteException {
        return model.getEtat();
    }

    @Override
    public void abonne(CarrefourClientInterface vue) throws RemoteException {
        views.add(vue);
        fire();
    }

    @Override
    public void desabonne(CarrefourClientInterface vue) throws RemoteException {
        views.remove(vue);
        fire();
    }

    @Override
    public void update() {
        fire();
    }

    @Override
    public void isAlive() throws RemoteException {
    }

    @Override
    public void poussoir(int feu) throws RemoteException {
        if (model.getEtat().getFeux(feu%2) == FeuEnum.VERT) {
            model.poussoir(feu);
        }
    }
}
