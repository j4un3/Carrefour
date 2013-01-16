/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.business;

import be.esi.g34840.carrefour.dto.CarrefourAlert;
import be.esi.g34840.carrefour.dto.CarrefourEtat;
import be.esi.g34840.carrefour.dto.CarrefourParam;
import be.esi.g34840.carrefour.dto.CarrefourSimulation;
import java.util.ArrayList;
import java.util.Date;

/**
 * Interface qui permet aux abonnées d'intéragir avec le serveur plus facilement
 *
 * @author g34840
 */
public interface CarrefourServeurInterface extends java.rmi.Remote {

    /**
     * Permet de basculer le modele en mode warning selon le booléen warning
     *
     * @param warning booléen vrai pour activer le mode warning faux sinon
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public void warning(boolean warning) throws java.rmi.RemoteException;

    /**
     * Retourne l'état du carrefour
     *
     * @return l'état du carrefour
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public CarrefourEtat getEtat() throws java.rmi.RemoteException;

    /**
     * Permet aux CarrefourClientInterface de s'abonner au serveur
     *
     * @param vue Le carrefourClientInterface qui s'abonne
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public void abonne(CarrefourClientInterface vue) throws java.rmi.RemoteException;

    /**
     * Permet aux CarrefourClientInterface de se désabonner du serveur
     *
     * @param vue Le CarrefourClientInterface qui se désabonne
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public void desabonne(CarrefourClientInterface vue) throws java.rmi.RemoteException;

    /**
     * Permet de savoir si on à toujours une connection avec le serveur
     *
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public void isAlive() throws java.rmi.RemoteException;

    /**
     * Permet au client piéton qui à son feu à rouge et le feu véhicule sur
     * l'axe que le piéton demande à traverser est au vert
     *
     * @param feu la position du feu piéton
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public void poussoir(int feu) throws java.rmi.RemoteException;

    /**
     * Permet de redémarrer le serveur c'est à dire relancer le modèle avec les
     * nouveaux paramètres
     *
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public void reboot() throws java.rmi.RemoteException;

    /**
     * Retourne un état du carrefour grace à un moment donnée (par le biais de
     * JavaDB)
     *
     * @param dateEtat le moment recherché
     * @return l'état du carrefour au moment donnée
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public CarrefourEtat getEtatDB(Date dateEtat) throws java.rmi.RemoteException;

    /**
     * Retourne le nombre de changement d'état entre deux périodes (par le biais
     * de JavaDB)
     *
     * @param dateA période début
     * @param dateB période fin
     * @return l'entier représentant le nombre de changement d'état entre deux
     * périodes
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public int getNombreEtatDB(Date dateA, Date dateB) throws java.rmi.RemoteException;

    /**
     * Retourne les paramètres du carrefour à un moment donnée (par le biais de
     * JavaDB)
     *
     * @param dateParam un moment donnée
     * @return les paramètres du carrefour à un moment donnée
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public CarrefourParam getParamDB(Date dateParam) throws java.rmi.RemoteException;

    /**
     * Retourne la liste des alertes du carrefour entre deux périodes (par le
     * biais de JavaDB)
     *
     * @param dateA période début
     * @param dateB période fin
     * @return la liste des alertes du carrefour entre deux périodes
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public ArrayList<CarrefourAlert> getAlertDB(Date dateA, Date dateB) throws java.rmi.RemoteException;

    /**
     * Retourne la simulation d'un carrefour entre deux périodes (par le biais
     * de JavaDB)
     *
     * @param dateA période début
     * @param dateB période fin
     * @return la simulation d'un carrefour entre deux périodes
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public ArrayList<CarrefourSimulation> getSimulationDB(Date dateA, Date dateB) throws java.rmi.RemoteException;

    /**
     * Imprime un pdf et l'envoie par mail des états d'un carrefour pendant une période
     * donnée ( par le biais de JavaDB)
     *
     * @param dateEtatA le début de la période
     * @param dateEtatB la fin de la période
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public void JasperEtat(Date dateEtatA,Date DateEtatB) throws java.rmi.RemoteException;

    /**
     * Retourne un tableau de string contenant les noms des axes
     *
     * @return un tableau de string contenant les noms des axes
     * @throws java.rmi.RemoteException si la connection avec le serveur est
     * perdue
     */
    public String[] getNomAxe() throws java.rmi.RemoteException;
}
