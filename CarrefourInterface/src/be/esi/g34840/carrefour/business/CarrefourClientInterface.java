/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.business;

/**
 * Interface permettant de regrouper les vues qui s'abonneront au serveur
 * @author g34840
 */
public interface CarrefourClientInterface extends java.rmi.Remote{
    public void update() throws java.rmi.RemoteException;
}
