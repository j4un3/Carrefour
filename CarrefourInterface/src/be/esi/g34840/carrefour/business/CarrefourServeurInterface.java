/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.business;

import be.esi.alg3.carrefour.mvc.model.CarrefourEtat;
import be.esi.alg3.carrefour.mvc.model.CarrefourParam;
import java.util.Date;

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
    public void poussoir(int feu) throws java.rmi.RemoteException;
    public void reboot() throws java.rmi.RemoteException;
    public CarrefourEtat getEtatDB(Date dateEtat) throws java.rmi.RemoteException;
    public int getNombreEtatDB(Date dateA, Date dateB) throws java.rmi.RemoteException;
    public CarrefourParam getParamDB(Date dateParam) throws java.rmi.RemoteException;
}
