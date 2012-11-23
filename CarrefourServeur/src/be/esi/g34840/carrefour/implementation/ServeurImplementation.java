/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.implementation;

import be.esi.alg3.carrefour.mvc.model.Carrefour;
import be.esi.g34840.carrefour.business.CarrefourInterface;
import java.rmi.RemoteException;

/**
 *
 * @author g34840
 */
public class ServeurImplementation extends java.rmi.server.UnicastRemoteObject implements CarrefourInterface{
    Carrefour model;
    
    public ServeurImplementation() throws RemoteException {
     model = new Carrefour(5, 5, 5);   
    }

    public void notifyChange() {
    }

    @Override
    public void demarre() {
        model.start();
    }
}
