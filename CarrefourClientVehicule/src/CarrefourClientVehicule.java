/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import be.esi.g34840.carrefour.business.CarrefourServeurInterface;
import be.esi.g34840.carrefour.gui.CarrefourClientVehiculeGUI;
import be.esi.gui.outils.MsgOutils;
import consoleaddition.LocalisationDialog;
import java.rmi.RemoteException;

/**
 *
 * @author j4un3
 */
public class CarrefourClientVehicule {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LocalisationDialog<CarrefourServeurInterface> config = new LocalisationDialog(true, "Carrefour");
        config.setTitle("Localisation d'un plan");
        config.setVisible(true);
        CarrefourServeurInterface carrefour = (CarrefourServeurInterface) config.getObjet();
        CarrefourClientVehiculeGUI frame = new CarrefourClientVehiculeGUI(carrefour);
        frame.pack();
        frame.setVisible(true);
    }
}
