/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.implementation;

import be.esi.alg3exam.janv2013.AxeEnum;
import be.esi.alg3exam.janv2013.CouleurEnum;
import be.esi.alg3exam.janv2013.Etat;
import be.esi.alg3exam.janv2013.Etudiant;
import be.esi.alg3exam.janv2013.ObjEtudiant;
import be.esi.alg3exam.janv2013.ObjProf;
import be.esi.alg3exam.janv2013.Params;
import be.esi.g34840.carrefour.GUI.ObjGUI;
import be.esi.g34840.carrefour.dto.CarrefourEtat;
import consoleaddition.LocalisationDialog;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author g34840
 */
public class ObjEtudiantImplementation extends java.rmi.server.UnicastRemoteObject implements ObjEtudiant {

    private ServeurImplementation serveur;
    private ObjProf prof;

    public ObjEtudiantImplementation(ServeurImplementation serveur) throws RemoteException {
        this.serveur = serveur;
        LocalisationDialog<ObjEtudiant> config = new LocalisationDialog(true, "objProf");
        config.setTitle("Localisation du serveur Prof");
        config.setVisible(true);
        prof = (ObjProf) config.getObjet();
        ObjGUI gui = new ObjGUI(prof, this);
        gui.pack();
        gui.setVisible(true);
    }

    @Override
    public Etat getEtat(Date date) throws RemoteException {
        if (date.after(
                new Date())) {
            return null;
        }
        Map<AxeEnum, CouleurEnum> mapV = new EnumMap<AxeEnum, CouleurEnum>(AxeEnum.class);
        Map<AxeEnum, CouleurEnum> mapP = new EnumMap<AxeEnum, CouleurEnum>(AxeEnum.class);
        CarrefourEtat etat = serveur.getEtatDB(date);
        if (etat == null) {
            return null;
        }
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    switch (etat.getFeux(i).getValue()) {
                        case 0:
                            mapV.put(AxeEnum.NS, CouleurEnum.VERT);
                            break;
                        case 1:
                            mapV.put(AxeEnum.NS, CouleurEnum.ORANGE);
                            break;
                        case 2:
                            mapV.put(AxeEnum.NS, CouleurEnum.ROUGE);
                            break;
                        case 5:
                            mapV.put(AxeEnum.NS, CouleurEnum.CLIGNOTANT);
                            break;
                    }
                    ;
                    break;
                case 1:
                    switch (etat.getFeux(i).getValue()) {
                        case 0:
                            mapV.put(AxeEnum.OE, CouleurEnum.VERT);
                            break;
                        case 1:
                            mapV.put(AxeEnum.OE, CouleurEnum.ORANGE);
                            break;
                        case 2:
                            mapV.put(AxeEnum.OE, CouleurEnum.ROUGE);
                            break;
                        case 5:
                            mapV.put(AxeEnum.OE, CouleurEnum.CLIGNOTANT);
                            break;
                    }
                    ;
                    break;
                case 2:
                    switch (etat.getFeux(i).getValue()) {
                        case 0:
                            mapP.put(AxeEnum.NS, CouleurEnum.VERT);
                            break;
                        case 1:
                            mapP.put(AxeEnum.NS, CouleurEnum.ORANGE);
                            break;
                        case 2:
                            mapP.put(AxeEnum.NS, CouleurEnum.ROUGE);
                            break;
                        case 3:
                            mapP.put(AxeEnum.NS, CouleurEnum.ORANGE);
                            break;
                        case 5:
                            mapP.put(AxeEnum.NS, CouleurEnum.CLIGNOTANT);
                            break;
                    }
                    ;
                    break;
                case 3:
                    switch (etat.getFeux(i).getValue()) {
                        case 0:
                            mapP.put(AxeEnum.OE, CouleurEnum.VERT);
                            break;
                        case 1:
                            mapP.put(AxeEnum.OE, CouleurEnum.ORANGE);
                            break;
                        case 2:
                            mapP.put(AxeEnum.OE, CouleurEnum.ROUGE);
                            break;
                        case 3:
                            mapP.put(AxeEnum.OE, CouleurEnum.ORANGE);
                            break;
                        case 5:
                            mapP.put(AxeEnum.OE, CouleurEnum.CLIGNOTANT);
                            break;
                    }
                    ;
                    break;
            }
        }
        return new Etat(mapV, mapP);
    }

    @Override
    public Params getParam() throws RemoteException {
        return serveur.getParam();
    }

    @Override
    public Etudiant getEtudiant() throws RemoteException {
        return new Etudiant("Nguyen", "G34840");
    }
}
