/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.gui;

import be.esi.g34840.carrefour.business.CarrefourServeurInterface;
import be.esi.g34840.carrefour.implementation.VueCarrefourClientVehicule;
import be.esi.gui.outils.MsgOutils;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author user0
 */
public class CarrefourClientVehiculeGUI extends JDialog {

    public static int FEUX_VEHICULE_N_S = 0;
    public static int FEUX_VEHICULE_E_O = 1;
    private Led[] leds;
    private CarrefourServeurInterface serveur;
    private JPanel led1P;
    private JPanel led2P;
    private VueCarrefourClientVehicule client;
    private Timer timer;
    private boolean ok;
    private TimerTask timerTask;

    public CarrefourClientVehiculeGUI(final CarrefourServeurInterface serveur) {
        super(new JFrame(), true);
        timer = new Timer();
        ok = true;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    serveur.connect();
                } catch (RemoteException ex) {
                    warning();
                }
            }
        };
        timer.schedule(timerTask, 0, 5000);
        try {

            setLayout(new GridLayout(1, 2));
            led1P = new JPanel();
            led1P.setBorder(BorderFactory.createTitledBorder("Feu véhicule NORD-SUD"));
            led2P = new JPanel();
            led2P.setBorder(BorderFactory.createTitledBorder("Feu véhicule EST-OUEST"));
            leds = new Led[2];
            leds[FEUX_VEHICULE_N_S] = new Led();
            leds[FEUX_VEHICULE_E_O] = new Led();
            leds[FEUX_VEHICULE_N_S].setOn(true);
            leds[FEUX_VEHICULE_E_O].setOn(true);
            led1P.add(leds[FEUX_VEHICULE_N_S]);
            led2P.add(leds[FEUX_VEHICULE_E_O]);
            add(led1P);
            add(led2P);
            this.serveur = serveur;
            this.client = new VueCarrefourClientVehicule(this);
            serveur.inscription(client);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        serveur.desinscription(client);
                        System.exit(0);
                    } catch (RemoteException ex) {
                        MsgOutils.erreur("RemoteException", ex.getMessage());
                        System.exit(0);
                    }
                }
            });
                  } catch (ConnectException ex){
                MsgOutils.erreur("ConnecException", "Problème de connection. L'application va se fermer.");
                    System.exit(0);
        } catch (RemoteException ex) {
            MsgOutils.erreur("Client RemoteException", ex.getMessage());
            System.exit(0);
        }
    }

    private void warning() {
        timerTask.cancel();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setFeuWarning();
            }
        }, 0, 1000);
    }

    private void setFeuWarning() {
        if (ok) {
            leds[FEUX_VEHICULE_N_S].setColor(Color.white);
            leds[FEUX_VEHICULE_E_O].setColor(Color.white);
        } else {
            leds[FEUX_VEHICULE_E_O].setColor(Color.orange);
            leds[FEUX_VEHICULE_N_S].setColor(Color.orange);
        }
        ok = !ok;
    }

    public void update() throws RemoteException {
        if(serveur.getEtat().getFeux(FEUX_VEHICULE_N_S).getColor().equals(Color.BLACK)){
            setFeuWarning();
        }
        else{
        leds[FEUX_VEHICULE_N_S].setColor(serveur.getEtat().getFeux(FEUX_VEHICULE_N_S).getColor());
        leds[FEUX_VEHICULE_E_O].setColor(serveur.getEtat().getFeux(FEUX_VEHICULE_E_O).getColor());
        }
    }
}
