/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.implementation;

import be.esi.alg3exam.janv2013.Params;
import be.esi.g34840.carrefour.business.CarrefourClientInterface;
import be.esi.g34840.carrefour.business.CarrefourServeurInterface;
import be.esi.g34840.carrefour.concept.CarrefourVueInterface;
import be.esi.g34840.carrefour.concept.FeuEnum;
import be.esi.g34840.carrefour.dto.CarrefourAlert;
import be.esi.g34840.carrefour.dto.CarrefourEtat;
import be.esi.g34840.carrefour.dto.CarrefourParam;
import be.esi.g34840.carrefour.dto.CarrefourSimulation;
import be.esi.g34840.carrefour.entity.CarrefourAlertDB;
import be.esi.g34840.carrefour.entity.CarrefourEtatDB;
import be.esi.g34840.carrefour.entity.CarrefourParamDB;
import be.esi.g34840.carrefour.entity.SimulationDB;
import be.esi.g34840.carrefour.exception.MailException;
import be.esi.g34840.carrefour.mail.Mailer;
import be.esi.g34840.carrefour.model.Carrefour;
import be.esi.gui.outils.MsgOutils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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
    private String[] nomAxe;
    private int rougeCommun, min, max;
    private CarrefourEtat saveEtat;
    private static HashMap parameters = new HashMap<String, Object>();

    public ServeurImplementation() throws RemoteException {
        ObjEtudiantImplementation objEtudiantImplementation = new ObjEtudiantImplementation(this);
        init();
        model = new Carrefour(vert, orange, rouge, rougeCommun, 1000, nomAxe);
        views = new ArrayList<CarrefourClientInterface>();
        model.abonne(this);
    }

    /**
     * Initialise le carrefour
     */
    private void init() {
        saveEtat = new CarrefourEtat(new Date());
        defaultProps = new Properties();
        try {
            in = new FileInputStream("../CarrefourInterface.properties");
        } catch (FileNotFoundException ex) {
            MsgOutils.erreur("FileNotFoundException", "Fichier de configuration "
                    + "introuvable.\n Le serveur sera lancé avec une configuration par défaut.");
            defaultInit();
        }
        try {
            defaultProps.load(in);
            initValeur();
        } catch (IOException ex) {
            MsgOutils.erreur("IOException", "La lecture du"
                    + " fichier de configuration s'est mal passée."
                    + " Le programme va se fermer.");
            System.exit(0);
        }
        CarrefourParamDB param = new CarrefourParamDB(vert[0], vert[1], vert[2],
                vert[3], orange[0], orange[1], orange[2], orange[3], rouge[0],
                rouge[1], rouge[2], rouge[3], rougeCommun, new Date());
        try {
            persist(param);
        } catch (PersistenceException ex) {
            MsgOutils.erreur("ConnectException", "Base de donnée introuvable "
                    + "\n Veuillez en crée une sur JavaDB avec comme nom CarrefourDB, comme login : app et password : app");
            System.exit(0);
        }

    }

    public Params getParam() {
        int[][][][] param = new int[2][3][2][4];
        //clignotant
        param[0][0][0][0] = -1;
        param[0][0][1][0] = -1;
        param[0][1][0][0] = -1;
        param[0][1][1][0] = -1;
        param[0][2][0][0] = -1;
        param[0][2][1][0] = -1;
        param[1][0][0][0] = -1;
        param[1][0][1][0] = -1;
        param[1][1][0][0] = -1;
        param[1][1][1][0] = -1;
        param[1][2][0][0] = -1;
        param[1][2][1][0] = -1;
        //orange
        param[0][0][0][1] = orange[0];
        param[0][0][1][1] = orange[1];
        param[0][1][0][1] = min;
        param[0][1][1][1] = min;
        param[0][2][0][1] = max;
        param[0][2][1][1] = max;
        param[1][0][0][1] = orange[2];
        param[1][0][1][1] = orange[3];
        param[1][1][0][1] = min;
        param[1][1][1][1] = min;
        param[1][2][0][1] = max;
        param[1][2][1][1] = max;
        //rouge
        param[0][0][0][2] = rouge[0];
        param[0][0][1][2] = rouge[1];
        param[0][1][0][2] = min;
        param[0][1][1][2] = min;
        param[0][2][0][2] = max;
        param[0][2][1][2] = max;
        param[1][0][0][2] = rouge[2];
        param[1][0][1][2] = rouge[3];
        param[1][1][0][2] = min;
        param[1][1][1][2] = min;
        param[1][2][0][2] = max;
        param[1][2][1][2] = max;
        //vert
        param[0][0][0][3] = vert[0];
        param[0][0][1][3] = vert[1];
        param[0][1][0][3] = min;
        param[0][1][1][3] = min;
        param[0][2][0][3] = max;
        param[0][2][1][3] = max;
        param[1][0][0][3] = vert[2];
        param[1][0][1][3] = vert[3];
        param[1][1][0][3] = min;
        param[1][1][1][3] = min;
        param[1][2][0][3] = max;
        param[1][2][1][3] = max;
        return new Params(param);
    }

    /**
     * initialise les valeurs du carrefour
     */
    private void initValeur() {
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
        nomAxe = new String[]{defaultProps.getProperty("axeNS"), defaultProps.getProperty("axeEO")};
        min = Integer.parseInt((String) defaultProps.getProperty("minValue"));
        max = Integer.parseInt((String) defaultProps.getProperty("maxValue"));
    }

    /**
     * initialise a défaut si il n'y a pas de fichier properties
     */
    private void defaultInit() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("../CarrefourInterface.properties");
        } catch (FileNotFoundException ex) {
            MsgOutils.erreur("FileNotFoundException", "Ne peut créer le fichier properties à cet emplacement.");
        }
        defaultProps.setProperty("v1", "10");
        defaultProps.setProperty("v2", "10");
        defaultProps.setProperty("vp1", "10");
        defaultProps.setProperty("vp2", "10");
        defaultProps.setProperty("o1", "5");
        defaultProps.setProperty("o2", "5");
        defaultProps.setProperty("op1", "5");
        defaultProps.setProperty("op2", "5");
        defaultProps.setProperty("r1", "17");
        defaultProps.setProperty("r2", "17");
        defaultProps.setProperty("rp1", "17");
        defaultProps.setProperty("rp2", "17");
        defaultProps.setProperty("rc1", "2");
        defaultProps.setProperty("cycle", "32");
        defaultProps.setProperty("minValueCommun", "1");
        defaultProps.setProperty("maxValueCommun", "10");
        defaultProps.setProperty("minValue", "5");
        defaultProps.setProperty("maxValue", "25");
        defaultProps.setProperty("axeNS", "Nord-Sud");
        defaultProps.setProperty("axeEO", "Est-Ouest");
        defaultProps.setProperty("email", "votreEmail@mail.com");
        try {
            defaultProps.store(out, "--saveConfig--");
        } catch (IOException ex) {
            MsgOutils.erreur("IOException", "L'écriture du"
                    + " fichier de configuration s'est mal passée."
                    + "\n Le programme va se fermer.");
            System.exit(0);
        }
        try {
            in = new FileInputStream("../CarrefourInterface.properties");
        } catch (FileNotFoundException ex) {
            // Ne devrais jamais arriver
            MsgOutils.erreur("FileNotFoundException", "Fichier de configuration "
                    + "introuvable.\n Le programme va se fermer.");
            System.exit(0);
        }

    }

    /**
     * Persiste un état s'il est différent de l'ancien
     */
    private void persistEtat() {
        CarrefourEtat etat = model.getEtat();
        if (!(etat.equals(saveEtat))) {
            saveEtat = new CarrefourEtat(etat.getFeux(0), etat.getFeux(1), etat.getFeux(2), etat.getFeux(3), etat.getDate());
            CarrefourEtatDB unEtat = new CarrefourEtatDB(saveEtat.getFeux(0),
                    saveEtat.getFeux(1), saveEtat.getFeux(2),
                    saveEtat.getFeux(3), new Date());
            try {
                persist(unEtat);
            } catch (PersistenceException ex) {
                MsgOutils.erreur("ConnectException", "Base de donnée introuvable "
                        + "\n Veuillez en créer une sur JavaDB avec comme nom CarrefourDB, login : app et password : app");
                System.exit(0);
            }
        }
    }

    /**
     * Notifie les changements
     */
    private void fire() {
        List<CarrefourClientInterface> viewsCopy = new ArrayList<CarrefourClientInterface>(views);
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
        persistEtat();
    }

    @Override
    public String[] getNomAxe() throws RemoteException {
        return model.getNomAxe();
    }

    @Override
    public void warning(boolean warning) throws RemoteException {
        if (model.getEtat().getFeux(0).getValue() == 5) {
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("dd MMM yyyy à kk:mm:ss");
            String corps = " Le carrefour est passé en état warning ce  " + df.format(date) + ".";
            try {
                Mailer.send(defaultProps.getProperty("email"), "Carrefour - Alerte", corps);
            } catch (MailException ex) {
                MsgOutils.erreur("MailException", ex.getMessage());
            }
            CarrefourAlertDB alert = new CarrefourAlertDB(defaultProps.getProperty("email"), corps, date);
            persist(alert);
        }
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
        String corps = "\n Redémarrage du modèle avec les "
                + "paramètres suivants : " + "\n Vert véhicule Nord-Sud : " + vert[0]
                + "\n Vert véhicule Est-Ouest : " + vert[1] + "\n Vert piéton Nord-Sud " + vert[2]
                + "\n Vert piéton Est-Ouest : " + vert[3];
//        try {
//            Mailer.send(defaultProps.getProperty("email"), "Carrefour - Alerte", corps);
//        } catch (MailException ex) {
//            MsgOutils.erreur("MailException", ex.getMessage());
//        }
        CarrefourAlertDB alert = new CarrefourAlertDB(defaultProps.getProperty("email"), corps, new Date());
        persist(alert);
        model.reboot(vert, orange, rouge, rougeCommun, nomAxe);
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
            // Optimization #3 - read-only
            query.setHint("eclipselink.read-only", "true");
            CarrefourEtatDB etatDB = (CarrefourEtatDB) query.getSingleResult();
            etat = new CarrefourEtat(etatDB.getFeuVehiculeNS(),
                    etatDB.getFeuVehiculeEO(),
                    etatDB.getFeuVPietonNS(),
                    etatDB.getFeuPietonEO(), etatDB.getDateDB());
            em.getTransaction().commit();
        } catch (NonUniqueResultException e) {
            System.out.println("Probleme à GERE deux persistence au début");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return etat;
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
            // Optimization #3 - read-only
            query.setHint("eclipselink.read-only", "true");
            CarrefourParamDB paramDB = (CarrefourParamDB) query.getSingleResult();
            param = new CarrefourParam(paramDB.getFeuVertVehiculeNordSud(),
                    paramDB.getFeuVertVehiculeEstOuest(), paramDB.getFeuVertPietonNordSud(),
                    paramDB.getFeuVertPietonEstOuest(), paramDB.getFeuOrangeVehiculeNordSud(),
                    paramDB.getFeuOrangeVehiculeEstOuest(), paramDB.getFeuOrangePietonNordSud(),
                    paramDB.getFeuOrangePietonEstOuest(), paramDB.getFeuRougeVehiculeNordSud(),
                    paramDB.getFeuRougeVehiculeEstOuest(), paramDB.getFeuRougePietonNordSud(),
                    paramDB.getFeuRougePietonEstOuest(), paramDB.getFeuRougeCommun(), paramDB.getDateDB());
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
            // Optimization #3 - read-only
            query.setHint("eclipselink.read-only", "true");
            nbEtat = Integer.parseInt(query.getSingleResult().toString());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return nbEtat;
    }

    @Override
    public ArrayList<CarrefourAlert> getAlertDB(Date dateA, Date dateB) throws RemoteException {
        ArrayList<CarrefourAlert> alertes = new ArrayList<CarrefourAlert>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrefourServeurPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createNamedQuery("CarrefourAlert.findPeriod");
            query.setParameter("dateA", new Timestamp(dateA.getTime()), TemporalType.TIMESTAMP);
            query.setParameter("dateB", new Timestamp(dateB.getTime()), TemporalType.TIMESTAMP);
            // Optimization #3 - read-only
            query.setHint("eclipselink.read-only", "true");
            List<CarrefourAlertDB> alertesDB = (List<CarrefourAlertDB>) query.getResultList();
            for (CarrefourAlertDB uneAlerteDB : alertesDB) {
                alertes.add(new CarrefourAlert(uneAlerteDB.getDestinataire(),
                        uneAlerteDB.getCorps(), uneAlerteDB.getDateDB()));
            }
            em.getTransaction().commit();
        } catch (NonUniqueResultException e) {
            System.out.println("Probleme à GERE deux persistence au début");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return alertes;
    }

    @Override
    public ArrayList<CarrefourSimulation> getSimulationDB(Date dateA, Date dateB) throws RemoteException {
        ArrayList<CarrefourSimulation> simulation = new ArrayList<CarrefourSimulation>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrefourServeurPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createNamedQuery("Simulation.findPeriod");
            query.setParameter("dateA", new Timestamp(dateA.getTime()), TemporalType.TIMESTAMP);
            query.setParameter("dateB", new Timestamp(dateB.getTime()), TemporalType.TIMESTAMP);
            // Optimization #3 - read-only
            query.setHint("eclipselink.read-only", "true");
            List<SimulationDB> simulationDB = (List<SimulationDB>) query.getResultList();

            for (SimulationDB uneSimulationDB : simulationDB) {
                CarrefourEtatDB etatDB = em.find(CarrefourEtatDB.class, uneSimulationDB.getId());
                if (etatDB != null) {
                    simulation.add(new CarrefourEtat(etatDB.getFeuVehiculeNS(),
                            etatDB.getFeuVehiculeEO(),
                            etatDB.getFeuVPietonNS(),
                            etatDB.getFeuPietonEO(), etatDB.getDateDB()));
                } else {
                    CarrefourAlertDB uneAlerteDB = em.find(CarrefourAlertDB.class, uneSimulationDB.getId());
                    if (uneAlerteDB != null) {
                        simulation.add(new CarrefourAlert(uneAlerteDB.getDestinataire(),
                                uneAlerteDB.getCorps(), uneAlerteDB.getDateDB()));
                    } else {
                        CarrefourParamDB paramDB = em.find(CarrefourParamDB.class, uneSimulationDB.getId());
                        if (paramDB != null) {
                            simulation.add(new CarrefourParam(paramDB.getFeuVertVehiculeNordSud(),
                                    paramDB.getFeuVertVehiculeEstOuest(), paramDB.getFeuVertPietonNordSud(),
                                    paramDB.getFeuVertPietonEstOuest(), paramDB.getFeuOrangeVehiculeNordSud(),
                                    paramDB.getFeuOrangeVehiculeEstOuest(), paramDB.getFeuOrangePietonNordSud(),
                                    paramDB.getFeuOrangePietonEstOuest(), paramDB.getFeuRougeVehiculeNordSud(),
                                    paramDB.getFeuRougeVehiculeEstOuest(), paramDB.getFeuRougePietonNordSud(),
                                    paramDB.getFeuRougePietonEstOuest(), paramDB.getFeuRougeCommun(), paramDB.getDateDB()));
                        }
                    }
                }

            }
            em.getTransaction().commit();
        } catch (NonUniqueResultException e) {
            System.out.println("Probleme à GERE deux persistence au début");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return simulation;
    }

    @Override
    public void JasperEtat(Date dateEtatA, Date dateEtatB) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrefourServeurPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            java.sql.Connection conn = em.unwrap(java.sql.Connection.class);
            em.getTransaction().commit();
            Properties properties = new Properties();
            FileInputStream jasperProperties = new FileInputStream("../CarrefourJasper.properties");
            properties.load(jasperProperties);
            Timestamp dateA = new Timestamp(dateEtatA.getTime());
            dateA.setNanos(0);
            Timestamp dateB = new Timestamp(dateEtatB.getTime());
            dateB.setNanos(0);
            parameters.put("nomSociete", properties.getProperty("nomSociete"));
            parameters.put("adrSociete", properties.getProperty("adreSociete"));
            parameters.put("logo", properties.getProperty("pathLogo"));
            parameters.put("debut", dateA);
            parameters.put("fin", dateB);
            //Pour recuperer un état d'un moment
//            parameters.put("monWhere", "WHERE CARREFOURETAT.\"DATEETAT\"  ="
//                    + " (SELECT max(CARREFOURETAT2.\"DATEETAT\") FROM \"APP\".\"CARREFOURETAT\""
//                    + " CARREFOURETAT2 WHERE CARREFOURETAT2.\"DATEETAT\" <= '" + dateA + "')");
            //Pour recuperer la liste des états d'une période
            parameters.put("monWhere", "WHERE SIMULATIONDB.\"DATEDB\" BETWEEN '"
                    + dateA + "' AND '" + dateB + "'");
            InputStream carrefourJasperEtat = ClassLoader.getSystemResourceAsStream("be/esi/g34840/carrefour/jasper/CarrefourJasperEtat.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(carrefourJasperEtat, parameters, conn);
            JasperExportManager.exportReportToPdfFile(jasperPrint, properties.getProperty("pathPDF") + "CarrefourJasperEtat.pdf");
            Mailer.send(defaultProps.getProperty("email"), "Carrefour Jasper Etat", "Le pdf de l'état du carrefour", "CarrefourJasperEtat.pdf", false);
        } catch (MailException ex) {
            MsgOutils.erreur("MailException", "L'envoi du mail n'a pas abouti. " + ex.getMessage());
        } catch (JRException ex) {
            MsgOutils.erreur("JRException", "Il y a une erreur à la création de JasperReport");
        } catch (IOException ex) {
            MsgOutils.erreur("IOException", "La lecture ou l'écriture du fichier properties (CarrefourJasper.properties) est erronée");
        }
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrefourServeurPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
