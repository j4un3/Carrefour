/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.implementation;

import be.esi.alg3.carrefour.mvc.concept.CarrefourVueInterface;
import be.esi.alg3.carrefour.mvc.model.Carrefour;
import be.esi.alg3.carrefour.mvc.model.CarrefourEtat;
import be.esi.g34840.carrefour.business.CarrefourClientInterface;
import be.esi.g34840.carrefour.business.CarrefourServeurInterface;
import be.esi.gui.outils.MsgOutils;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g34840
 */
public class ServeurImplementation extends java.rmi.server.UnicastRemoteObject implements CarrefourVueInterface, CarrefourServeurInterface {

    private Carrefour model;
    private List<CarrefourClientInterface> views;

    public ServeurImplementation() throws RemoteException {
        int[] rouge,orange,vert,rougeCommun;
        vert = new int[]{5,5,5,5};
        orange = new int[]{3,3,3,3};
        rouge = new int[]{5,5,5,5};
        rougeCommun = new int[]{2,2,2,2};
        model = new Carrefour(vert,orange,rouge,rougeCommun);
        views = new ArrayList<CarrefourClientInterface>();
        model.inscription(this);
    }

    private void fire() {
        for (CarrefourClientInterface uneVue : views) {
            try {
                uneVue.update();
            } catch (RemoteException ex) {
                MsgOutils.erreur("Serveur RemoteException", ex.getMessage());
            }
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
    public void inscription(CarrefourClientInterface vue) throws RemoteException {
        views.add(vue);
        fire();
    }

    @Override
    public void desinscription(CarrefourClientInterface vue) throws RemoteException {
        views.remove(vue);
        fire();
    }

    @Override
    public void update() {
        fire();
    }

    @Override
    public void connect() throws RemoteException {
    }
}
