/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.business;

import be.esi.alg3.carrefour.mvc.model.CarrefourEtat;

/**
 *
 * @author g34840
 */
public interface CarrefourServeurInterface extends java.rmi.Remote{
    public void warning(boolean warning) throws java.rmi.RemoteException;
    public CarrefourEtat getEtat() throws java.rmi.RemoteException;
    public void abonne(CarrefourClientInterface vue) throws java.rmi.RemoteException;
    public void desabonne(CarrefourClientInterface vue) throws java.rmi.RemoteException;
    public void isAlive() throws java.rmi.RemoteException;
    public void poussoire(int feu) throws java.rmi.RemoteException;
}
