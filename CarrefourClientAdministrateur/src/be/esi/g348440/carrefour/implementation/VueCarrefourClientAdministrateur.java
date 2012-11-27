/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g348440.carrefour.implementation;

import be.esi.g34840.carrefour.business.CarrefourClientInterface;
import be.esi.g34840.carrefour.gui.CarrefourClientAdministrateurGUI;
import java.rmi.RemoteException;

/**
 *
 * @author user0
 */
public class VueCarrefourClientAdministrateur extends java.rmi.server.UnicastRemoteObject implements CarrefourClientInterface{
    CarrefourClientAdministrateurGUI gui;
    public VueCarrefourClientAdministrateur(CarrefourClientAdministrateurGUI gui) throws java.rmi.RemoteException{
        this.gui = gui;
    }
    @Override
    public void update() throws RemoteException {
        gui.update();
    }
}
