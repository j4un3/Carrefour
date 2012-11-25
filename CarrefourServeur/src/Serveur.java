
import be.esi.g34840.carrefour.implementation.ServeurImplementation;
import be.esi.gui.outils.MsgOutils;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Properties;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author g34840
 */
public class Serveur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileInputStream in = null;
        try {
            Properties defaultProps = new Properties();
            in = new FileInputStream("ServeurProperties.properties");
            defaultProps.load(in);
            System.setSecurityManager(new RMISecurityManager());
            ServeurImplementation serveur = new ServeurImplementation();
            try {
                java.rmi.Naming.rebind("rmi://" + defaultProps.getProperty("host")
                        + ":" + defaultProps.getProperty("port") + "/Carrefour", serveur);
            } catch (RemoteException ex) {
                MsgOutils.erreur("Serveur RemoteException", ex.getMessage());
                System.exit(0);
            } catch (MalformedURLException ex) {
                MsgOutils.erreur("Serveur MalformedURLException", ex.getMessage());
                System.exit(0);
            }
        } catch (IOException ex) {
            MsgOutils.erreur("Serveur IOException", ex.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                MsgOutils.erreur("Serveur IOException", ex.getMessage());
            }
        }
    }
}
