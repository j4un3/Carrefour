/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.implementation;

import be.esi.g34840.carrefour.business.CarrefourClientInterface;
import be.esi.g34840.carrefour.gui.CarrefourClientPietonGUI;
import java.rmi.RemoteException;

/**
 *
 * @author user0
 */
public class VueCarrefourClientPieton extends java.rmi.server.UnicastRemoteObject implements CarrefourClientInterface{
    CarrefourClientPietonGUI gui;
    public VueCarrefourClientPieton(CarrefourClientPietonGUI gui) throws java.rmi.RemoteException{
        this.gui = gui;
    }
    @Override
    public void update() throws RemoteException {
        gui.update();
    }
    
}
