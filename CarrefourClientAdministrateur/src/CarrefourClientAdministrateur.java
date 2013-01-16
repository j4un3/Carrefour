import be.esi.g34840.carrefour.business.CarrefourServeurInterface;
import be.esi.g34840.carrefour.gui.CarrefourClientAdministrateurGUI;
import consoleaddition.LocalisationDialog;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
  * Classe principale permettant de lancer un client administrateur
 * @author g34840
 */
public class CarrefourClientAdministrateur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LocalisationDialog<CarrefourServeurInterface> config = new LocalisationDialog(true, "Carrefour");
        config.setTitle("Localisation d'un plan");
        config.setVisible(true);
        CarrefourServeurInterface carrefour = (CarrefourServeurInterface) config.getObjet();
        CarrefourClientAdministrateurGUI frame = new CarrefourClientAdministrateurGUI(carrefour);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);    }
}
