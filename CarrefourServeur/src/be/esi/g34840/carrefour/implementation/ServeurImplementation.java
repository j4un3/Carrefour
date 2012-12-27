/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.implementation;

import be.esi.alg3.carrefour.mvc.concept.CarrefourVueInterface;
import be.esi.alg3.carrefour.mvc.concept.FeuEnum;
import be.esi.alg3.carrefour.mvc.model.Carrefour;
import be.esi.alg3.carrefour.mvc.model.CarrefourEtat;
import be.esi.alg3.carrefour.mvc.model.CarrefourParam;
import be.esi.g34840.carrefour.business.CarrefourClientInterface;
import be.esi.g34840.carrefour.business.CarrefourServeurInterface;
import be.esi.g34840.carrefour.entity.CarrefourEtatDB;
import be.esi.g34840.carrefour.entity.CarrefourParamDB;
import be.esi.gui.outils.MsgOutils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author g34840
 */
public class ServeurImplementation extends java.rmi.server.UnicastRemoteObject implements CarrefourVueInterface, CarrefourServeurInterface {

    private Carrefour model;
    private List<CarrefourClientInterface> views;
    private Properties defaultProps;
    private FileInputStream in;
    private int[] rouge, orange, vert;
    private int rougeCommun;
    private CarrefourEtat saveEtat;

    public ServeurImplementation() throws RemoteException {
        init();
        model = new Carrefour(vert, orange, rouge, rougeCommun, 1000);
        views = new ArrayList<CarrefourClientInterface>();
        model.abonne(this);
    }

    private void init() {
        try {
            defaultProps = new Properties();
            in = new FileInputStream("../CarrefourInterface.properties");
            defaultProps.load(in);
            vert = new int[]{Integer.parseInt((String) defaultProps.getProperty("v1")),
                Integer.parseInt((String) defaultProps.get("v2")),
                Integer.parseInt((String) defaultProps.getProperty("vp1")),
                Integer.parseInt((String) defaultProps.getProperty("vp2"))};
            orange = new int[]{Integer.parseInt((String) defaultProps.getProperty("o1")),
                Integer.parseInt((String) defaultProps.get("o2")),
                Integer.parseInt((String) defaultProps.getProperty("op1")),
                Integer.parseInt((String) defaultProps.getProperty("op2"))};
            rouge = new int[]{Integer.parseInt((String) defaultProps.getProperty("r1")),
                Integer.parseInt((String) defaultProps.get("r2")),
                Integer.parseInt((String) defaultProps.getProperty("rp1")),
                Integer.parseInt((String) defaultProps.getProperty("rp2"))};
            rougeCommun = Integer.parseInt((String) defaultProps.getProperty("rc1"));
            CarrefourParamDB param = new CarrefourParamDB(vert[0], vert[1], vert[2], vert[3], orange[0], orange[1], orange[2], orange[3], rouge[0], rouge[1], rouge[2], rouge[3], rougeCommun, new Date());
            try {
                persist(param);
            } catch (PersistenceException ex) {
                MsgOutils.erreur("ConnectException", "Base de donée introuvable "
                        + "\n Veuillez en crée une sur JavaDB avec comme nom CarrefourDB, comme login : app et password : app");
                System.exit(0);
            }
        } catch (FileNotFoundException ex) {
            MsgOutils.erreur("FileNotFoundException", "Fichier de configuration "
                    + "introuvable.\n Le serveur sera lancé avec une configuration par défaut.");
            defaultInit();
        } catch (IOException ex) {
            MsgOutils.erreur("IOException", "L'ouverture ou la fermeture du"
                    + " fichier de configuration s'est mal passée."
                    + "\n Le serveur sera lancé avec une configuration par défaut.");
            defaultInit();
        }

    }

    private void defaultInit() {
        vert = new int[]{10, 10, 10, 10};
        orange = new int[]{5, 5, 5, 5};
        rouge = new int[]{17, 17, 17, 17};
        rougeCommun = 2;
    }

    private void fire() {
        List<CarrefourClientInterface> viewsCopy = new ArrayList<CarrefourClientInterface>(views);
        try {
            for (CarrefourClientInterface uneVue : viewsCopy) {
                try {
                    uneVue.update();
                } catch (RemoteException exe) {
                    try {
                        desabonne(uneVue);
                    } catch (RemoteException ex) {
                        MsgOutils.erreur("RemoteException", "Impossible de désabonner la vue : " + ex.getMessage());
                    }
                }
            }
        } catch (ConcurrentModificationException ex) {
            fire();
        }
    }

    @Override
    public void warning(boolean warning) throws RemoteException {
        model.setWarning(warning);
    }

    @Override
    public CarrefourEtat getEtat() throws RemoteException {
        return model.getEtat();
    }

    @Override
    public void abonne(CarrefourClientInterface vue) throws RemoteException {
        views.add(vue);
        fire();
    }

    @Override
    public void desabonne(CarrefourClientInterface vue) throws RemoteException {
        views.remove(vue);
        fire();
    }

    @Override
    public void update() {
        CarrefourEtat etat = model.getEtat();
        if (!(etat.equals(saveEtat))) {
            saveEtat = new CarrefourEtat(etat.getFeux(0), etat.getFeux(1), etat.getFeux(2), etat.getFeux(3));
            CarrefourEtatDB unEtat = new CarrefourEtatDB(saveEtat.getFeux(0).getValue(),
                    saveEtat.getFeux(1).getValue(), saveEtat.getFeux(2).getValue(),
                    saveEtat.getFeux(3).getValue(), new Date());
            try {
                persist(unEtat);
            } catch (PersistenceException ex) {
                MsgOutils.erreur("ConnectException", "Base de donnée introuvable "
                        + "\n Veuillez en créer une sur JavaDB avec comme nom CarrefourDB, login : app et password : app");
                System.exit(0);
            }
        }
        fire();
    }

    @Override
    public void isAlive() throws RemoteException {
    }

    @Override
    public void poussoir(int feu) throws RemoteException {
        if (model.getEtat().getFeux(feu % 2) == FeuEnum.VERT) {
            model.poussoir(feu);
        }
    }

    @Override
    public void reboot() throws RemoteException {
        init();
        model.reboot(vert, orange, rouge, rougeCommun);
    }

    public void persist(Object object) throws PersistenceException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrefourServeurPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public CarrefourEtat getEtatDB(Date dateEtat) throws RemoteException {
        CarrefourEtat etat = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrefourServeurPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createNamedQuery("CarrefourEtat.findMoment");
            query.setParameter("dateMoment", new Timestamp(dateEtat.getTime()), TemporalType.TIMESTAMP);
            CarrefourEtatDB etatDB = (CarrefourEtatDB) query.getSingleResult();
            etat = new CarrefourEtat(getFeuEnum(etatDB.getFeuVehiculeNS()),
                    getFeuEnum(etatDB.getFeuVehiculeEO()),
                    getFeuEnum(etatDB.getFeuVPietonNS()),
                    getFeuEnum(etatDB.getFeuPietonEO()));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return etat;
    }

    private FeuEnum getFeuEnum(int a) {
        FeuEnum feu = null;
        switch (a) {
            case 0:
                feu = FeuEnum.VERT;
                break;
            case 1:
                feu = FeuEnum.ORANGE;
                break;
            case 2:
                feu = FeuEnum.ROUGE;
                break;
            case 3:
                feu = FeuEnum.VERT_CLIGNOTANT;
                break;
            case 5:
                feu = FeuEnum.WARNING;
                break;
        }
        return feu;
    }

    @Override
    public CarrefourParam getParamDB(Date dateParam) throws RemoteException {
        CarrefourParam param = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrefourServeurPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createNamedQuery("CarrefourParam.findMoment");
            query.setParameter("dateMoment", new Timestamp(dateParam.getTime()), TemporalType.TIMESTAMP);
            CarrefourParamDB paramDB = (CarrefourParamDB) query.getSingleResult();
            param = new CarrefourParam(paramDB.getFeuVertVehiculeNordSud(),
                    paramDB.getFeuVertVehiculeEstOuest(), paramDB.getFeuVertPietonNordSud(),
                    paramDB.getFeuVertPietonEstOuest(), paramDB.getFeuOrangeVehiculeNordSud(),
                    paramDB.getFeuOrangeVehiculeEstOuest(), paramDB.getFeuOrangePietonNordSud(),
                    paramDB.getFeuOrangePietonEstOuest(), paramDB.getFeuRougeVehiculeNordSud(),
                    paramDB.getFeuRougeVehiculeEstOuest(), paramDB.getFeuRougePietonNordSud(),
                    paramDB.getFeuRougePietonEstOuest(), paramDB.getFeuRougeCommun());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return param;
    }

    @Override
    public int getNombreEtatDB(Date dateA, Date dateB) throws RemoteException {
        int nbEtat = 0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrefourServeurPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createNamedQuery("CarrefourEtat.count");
            query.setParameter("dateA", new Timestamp(dateA.getTime()), TemporalType.TIMESTAMP);
            query.setParameter("dateB", new Timestamp(dateB.getTime()), TemporalType.TIMESTAMP);
            nbEtat = (Integer) query.getSingleResult();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return nbEtat;
    }
}
