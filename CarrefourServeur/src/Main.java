
import be.esi.gui.outils.MsgOutils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author g34840
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileInputStream in = null;
        try {
            Properties defaultProps = new Properties();
            in = new FileInputStream("ServeurProperties");
            defaultProps.load(in);
            try {
                java.rmi.Naming.rebind("rmi://" + defaultProps.getProperty("host")
                        + ":" + defaultProps.getProperty("port") + "/Carrefour", null);
            } catch (RemoteException ex) {
                MsgOutils.erreur("Serveur RemoteException", ex.getMessage());
            } catch (MalformedURLException ex) {
                MsgOutils.erreur("Serveur MalformedURLException", ex.getMessage());
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
