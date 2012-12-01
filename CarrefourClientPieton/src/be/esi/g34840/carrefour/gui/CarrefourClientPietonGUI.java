/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.gui;

import be.esi.g34840.carrefour.business.CarrefourServeurInterface;
import be.esi.g34840.carrefour.implementation.VueCarrefourClientPieton;
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
public class CarrefourClientPietonGUI extends JDialog {

    public static int FEUX_PIETON_N_S = 2;
    public static int FEUX_PIETON_E_O = 3;
    private Led[] leds;
    private CarrefourServeurInterface serveur;
    private JPanel led1P;
    private JPanel led2P;
    private VueCarrefourClientPieton client;
    private Timer timer;
    private TimerTask timerTask;
    private static int cptTest;

    public CarrefourClientPietonGUI(final CarrefourServeurInterface serveur) {
        super(new JFrame(), true);
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    serveur.isAlive();
                } catch (RemoteException ex) {
                    warning();
                }
            }
        };
        timer.schedule(timerTask, 0, 5000);
        try {

            setLayout(new GridLayout(1, 2));
            led1P = new JPanel();
            led1P.setBorder(BorderFactory.createTitledBorder("Feu piéton NORD-SUD"));
            led2P = new JPanel();
            led2P.setBorder(BorderFactory.createTitledBorder("Feu piéton EST-OUEST"));
            leds = new Led[2];
            leds[(FEUX_PIETON_N_S % 2)] = new Led();
            leds[(FEUX_PIETON_E_O % 2)] = new Led();
            leds[(FEUX_PIETON_N_S % 2)].setOn(true);
            leds[(FEUX_PIETON_E_O % 2)].setOn(true);
            led1P.add(leds[(FEUX_PIETON_N_S % 2)]);
            led2P.add(leds[(FEUX_PIETON_E_O % 2)]);
            add(led1P);
            add(led2P);
            this.serveur = serveur;
            this.client = new VueCarrefourClientPieton(this);
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
        } catch (ConnectException ex) {
            MsgOutils.erreur("ConnecException", "Problème de connection. L'application va se fermer.");
            System.exit(0);
        } catch (RemoteException ex) {
            MsgOutils.erreur("Client RemoteException", ex.getMessage());
            System.exit(0);
        }
    }

    private void warning() {
        timerTask.cancel();
        setFeuWarning();

    }

    private void setFeuVertClignotant(int feu) {
        System.out.println(cptTest);
        if ((cptTest++) % 2 == 0) {
            leds[(feu % 2)].setColor(Color.green);
        } else {
            leds[(feu % 2)].setColor(Color.black);
        }
    }

    public void update() throws RemoteException {
        if (serveur.getEtat().getFeux(FEUX_PIETON_E_O).getColor().equals(Color.BLACK)) {
            setFeuWarning();
        } else if (serveur.getEtat().getFeux(FEUX_PIETON_N_S).getColor().equals(Color.GRAY)) {
            leds[(FEUX_PIETON_N_S % 2)].setColor(serveur.getEtat().getFeux(FEUX_PIETON_E_O).getColor());
            setFeuVertClignotant(FEUX_PIETON_N_S);
        } else if (serveur.getEtat().getFeux(FEUX_PIETON_E_O).getColor().equals(Color.GRAY)) {
            leds[(FEUX_PIETON_N_S % 2)].setColor(serveur.getEtat().getFeux(FEUX_PIETON_N_S).getColor());
            setFeuVertClignotant(FEUX_PIETON_E_O);
        } else {
            leds[(FEUX_PIETON_N_S % 2)].setColor(serveur.getEtat().getFeux(FEUX_PIETON_N_S).getColor());
            leds[(FEUX_PIETON_E_O % 2)].setColor(serveur.getEtat().getFeux(FEUX_PIETON_E_O).getColor());
        }
    }

    private void setFeuWarning() {
        leds[(FEUX_PIETON_N_S % 2)].setColor(Color.black);
        leds[(FEUX_PIETON_E_O % 2)].setColor(Color.black);
    }
}
