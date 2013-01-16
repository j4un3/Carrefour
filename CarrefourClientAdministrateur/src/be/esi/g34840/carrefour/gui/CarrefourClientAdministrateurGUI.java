/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.gui;

import be.esi.g34840.carrefour.business.CarrefourServeurInterface;
import be.esi.g34840.carrefour.concept.VueClientAdmin;
import be.esi.g34840.carrefour.dto.CarrefourEtat;
import be.esi.g34840.carrefour.implementation.VueCarrefourClientAdministrateur;
import be.esi.g34840.carrefour.model.Carrefour;
import be.esi.gui.outils.MsgOutils;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JSlider;
import javax.swing.JTable;

/**
 * Classe qui crée la vue du client administrateur et permet l'interaction avec
 * le modèle du serveur
 *
 * @author user0
 */
public class CarrefourClientAdministrateurGUI extends javax.swing.JFrame {

    public static int FEUX_PIETON_E_O = 3, FEUX_PIETON_N_S = 2,
            FEUX_VEHICULE_E_O = 1, FEUX_VEHICULE_N_S = 0;
    private CarrefourServeurInterface serveur;
    private VueCarrefourClientAdministrateur client;
    private Properties defaultProps;
    private TimerTask timerTask;
    private Timer timer;
    private ArrayList<VueClientAdmin> vues;
    private CarrefourEtat etat;
    private int[] vert, orange, rouge;
    private int rougeCommun;
    private String[] nomAxe;

    /**
     * Creates new form CarrefourClientAdministrateurGUI
     */
    public CarrefourClientAdministrateurGUI(final CarrefourServeurInterface serveur) {
        vues = new ArrayList<VueClientAdmin>();
        // Chargement du fichier properties
        FileInputStream in = null;
        try {
            this.serveur = serveur;
            defaultProps = new Properties();
            in = new FileInputStream("../CarrefourInterface.properties");
            defaultProps.load(in);
        } catch (FileNotFoundException ex) {
            MsgOutils.erreur("FileNotFoundException", "Fichier de configuration introuvable."
                    + "\n Veuillez relancer le serveur pour crée un fichier de configuration valide");
        } catch (IOException ex) {
            MsgOutils.erreur("IOException", "L'ouverture ou la fermeture du"
                    + " fichier de configuration s'est mal passée.\n Un fichier"
                    + " de configuration à été crée à votre place veuillez changer "
                    + "à la place de VotreEmail@mail.com par le votre pour obtenir "
                    + "les avertissements par email");
        }
        initComponents();
        try {
            client = new VueCarrefourClientAdministrateur(this);
            this.serveur.abonne(client);
        } catch (RemoteException ex) {
            MsgOutils.erreur("ConnectException", "Problème de connection avec "
                    + "le serveur.\n L'application va se terminer.");
            System.exit(0);
        }
        init();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    serveur.isAlive();
                } catch (RemoteException ex) {
                    MsgOutils.warning("Attention RemoteException", "Vous avez "
                            + "perdu la connection avec le serveur.\n"
                            + "Le programme va se fermer");
                    System.exit(0);
                }
            }
        };

        timer.schedule(timerTask,
                0, 5000);
        addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        try {
                            serveur.desabonne(client);
                            System.exit(0);
                        } catch (RemoteException ex) {
                            MsgOutils.erreur("RemoteException", ex.getMessage());
                            System.exit(0);
                        }
                    }
                });
        setResizable(false);

    }

    public CarrefourEtat getEtat() {
        return etat;
    }

    /**
     * Permet de mettre d'assigner toutes les valeurs aux bonnes variables
     */
    private void init() {
        avertissementL.setText("Les durées sont calculées en secondes et "
                + "leur valeur doit être comprise entre "
                + defaultProps.getProperty("minValue") + " et "
                + defaultProps.getProperty("maxValue") + " secondes.");
        sliderRougeCommunL.setText(defaultProps.getProperty("rc1") + " s");
        sliderRougeCommun.setValue(Integer.parseInt(defaultProps.getProperty("rc1")));
        avertissementL.setForeground(Color.RED);
        tableConfig.setValueAt(Integer.parseInt(defaultProps.getProperty("v1")), 0, 1);
        tableConfig.setValueAt(Integer.parseInt(defaultProps.getProperty("v2")), 1, 1);
        tableConfig.setValueAt(Integer.parseInt(defaultProps.getProperty("vp1")), 2, 1);
        tableConfig.setValueAt(Integer.parseInt(defaultProps.getProperty("vp2")), 3, 1);
        tableConfig.setValueAt(Integer.parseInt(defaultProps.getProperty("o1")), 0, 2);
        tableConfig.setValueAt(Integer.parseInt(defaultProps.getProperty("o2")), 1, 2);
        tableConfig.setValueAt(Integer.parseInt(defaultProps.getProperty("op1")), 2, 2);
        tableConfig.setValueAt(Integer.parseInt(defaultProps.getProperty("op2")), 3, 2);
        tableConfig.setValueAt(Integer.parseInt(defaultProps.getProperty("r1")), 0, 3);
        tableConfig.setValueAt(Integer.parseInt(defaultProps.getProperty("r2")), 1, 3);
        tableConfig.setValueAt(Integer.parseInt(defaultProps.getProperty("rp1")), 2, 3);
        tableConfig.setValueAt(Integer.parseInt(defaultProps.getProperty("rp2")), 3, 3);
        sliderCycle.setValue(Integer.parseInt(defaultProps.getProperty("cycle")));
        setCycleJTable();
        sliderCycleL.setText(defaultProps.getProperty("cycle") + " s");
        nomAxeNSTF.setText(defaultProps.getProperty("axe1"));
        nomAxeEOTF.setText(defaultProps.getProperty("axe2"));
    }

    /**
     * Donne le cycle d'un feu
     */
    private void setCycleJTable() {
        tableConfig.setValueAt((Integer) tableConfig.getValueAt(0, 1)
                + (Integer) tableConfig.getValueAt(0, 2) + (Integer) tableConfig.getValueAt(0, 3), 0, 4);
        tableConfig.setValueAt((Integer) tableConfig.getValueAt(1, 1)
                + (Integer) tableConfig.getValueAt(1, 2) + (Integer) tableConfig.getValueAt(1, 3), 1, 4);
        tableConfig.setValueAt((Integer) tableConfig.getValueAt(2, 1)
                + (Integer) tableConfig.getValueAt(2, 2) + (Integer) tableConfig.getValueAt(2, 3), 2, 4);
        tableConfig.setValueAt((Integer) tableConfig.getValueAt(3, 1)
                + (Integer) tableConfig.getValueAt(3, 2) + (Integer) tableConfig.getValueAt(3, 3), 3, 4);

    }

    /**
     * Permet de mettre à jour la vue client administrateur
     */
    public void update() {
        try {
            etat = serveur.getEtat();
            feuVehiculeNSL.setText(etat.getFeux(FEUX_VEHICULE_N_S).getLibelle());
            feuVehiculeEOL.setText(etat.getFeux(FEUX_VEHICULE_E_O).getLibelle());
            feuPietonEOL.setText(etat.getFeux(FEUX_PIETON_E_O).getLibelle());
            feuPietonNSL.setText(etat.getFeux(FEUX_PIETON_N_S).getLibelle());
            fire();
        } catch (RemoteException ex) {
            MsgOutils.erreur("RemoteException", "Vous avez perdu la connection avec le serveur.");
        }

    }

    /**
     * Permet à la VueClientAdmin de s'abonner au modèle du serveur via le proxy
     *
     * @param vue La vue qui s'abonne
     */
    public void abonne(VueClientAdmin vue) {
        vues.add(vue);
        fire();
    }

    /**
     * Permet à la VueClientAdmin de se désabonner
     *
     * @param vue la vue qui se désabonne
     */
    public void desabonne(VueClientAdmin vue) {
        vues.remove(vue);
        fire();
    }

    /**
     * Notifie au vues des changements
     */
    private void fire() {
        for (VueClientAdmin vue : vues) {
            vue.update();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        carrefourConfigP = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableConfig = new javax.swing.JTable();
        previsualB = new javax.swing.JButton();
        saveB = new javax.swing.JButton();
        avertissementL = new javax.swing.JLabel();
        rougeCommunL = new javax.swing.JLabel();
        sliderRougeCommun = new javax.swing.JSlider();
        sliderRougeCommunL = new javax.swing.JLabel();
        pDefautB = new javax.swing.JButton();
        cycleL = new javax.swing.JLabel();
        sliderCycle = new javax.swing.JSlider();
        sliderCycleL = new javax.swing.JLabel();
        nomAxeNSTF = new javax.swing.JTextField();
        nomAxeEOTF = new javax.swing.JTextField();
        axeNSL = new javax.swing.JLabel();
        axeEOL = new javax.swing.JLabel();
        carrefourControlP = new javax.swing.JPanel();
        feuVNSL = new javax.swing.JLabel();
        feuVEOL = new javax.swing.JLabel();
        feuPNSL = new javax.swing.JLabel();
        feuPEOL = new javax.swing.JLabel();
        feuVehiculeNSL = new javax.swing.JLabel();
        feuVehiculeEOL = new javax.swing.JLabel();
        feuPietonNSL = new javax.swing.JLabel();
        feuPietonEOL = new javax.swing.JLabel();
        historiqueB = new javax.swing.JButton();
        cycleB = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        carrefourAbout = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        quitCarrefour = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        graphicView = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        carrefourHelp = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Client Administrateur du carrefour");

        carrefourConfigP.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuration des durées en secondes des feux d'un carrefour"));

        tableConfig.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Feu véhicule Nord-Sud", null, null, null, null},
                {"Feu véhicule Est-Ouest", null, null, null, null},
                {"Feu piéton Nord-sud", null, null, null, null},
                {"Feu piéton Est-Ouest", null, null, null, null}
            },
            new String [] {
                "Feux", "Vert", "Orange", "Rouge", "Durée cycle"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableConfig.setAutoscrolls(false);
        tableConfig.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tableConfigFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(tableConfig);

        previsualB.setText("Prévisualiser");
        previsualB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previsualBActionPerformed(evt);
            }
        });

        saveB.setText("Sauvegarder");
        saveB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBActionPerformed(evt);
            }
        });

        avertissementL.setText("1");

        rougeCommunL.setText("Feu rouge commun :");

        sliderRougeCommun.setMajorTickSpacing(2);
        sliderRougeCommun.setMaximum(Integer.parseInt(defaultProps.getProperty("maxValueCommun")));
        sliderRougeCommun.setMinimum(Integer.parseInt(defaultProps.getProperty("minValueCommun")));
        sliderRougeCommun.setMinorTickSpacing(1);
        sliderRougeCommun.setPaintLabels(true);
        sliderRougeCommun.setPaintTicks(true);
        sliderRougeCommun.setSnapToTicks(true);
        sliderRougeCommun.setValue(Integer.parseInt(defaultProps.getProperty("rc1")));
        sliderRougeCommun.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderRougeCommunStateChanged(evt);
            }
        });

        sliderRougeCommunL.setText("1 s");

        pDefautB.setText("Par défaut");
        pDefautB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pDefautBActionPerformed(evt);
            }
        });

        cycleL.setText("La durée d'un cycle :");

        sliderCycle.setMajorTickSpacing(10);
        sliderCycle.setMaximum(60);
        sliderCycle.setMinimum(30);
        sliderCycle.setMinorTickSpacing(5);
        sliderCycle.setPaintLabels(true);
        sliderCycle.setPaintTicks(true);
        sliderCycle.setValue(Integer.parseInt(defaultProps.getProperty("cycle")));
        sliderCycle.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderCycleStateChanged(evt);
            }
        });

        sliderCycleL.setText("1 s");

        nomAxeNSTF.setText(" ");

        nomAxeEOTF.setText("                 ");

        axeNSL.setText("Axe Nord-Sud :");

        axeEOL.setText("Axe Est-Ouest :");

        javax.swing.GroupLayout carrefourConfigPLayout = new javax.swing.GroupLayout(carrefourConfigP);
        carrefourConfigP.setLayout(carrefourConfigPLayout);
        carrefourConfigPLayout.setHorizontalGroup(
            carrefourConfigPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carrefourConfigPLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(avertissementL)
                .addContainerGap())
            .addGroup(carrefourConfigPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(carrefourConfigPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(carrefourConfigPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(carrefourConfigPLayout.createSequentialGroup()
                        .addComponent(rougeCommunL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sliderRougeCommun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(carrefourConfigPLayout.createSequentialGroup()
                        .addComponent(cycleL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sliderCycle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(carrefourConfigPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(carrefourConfigPLayout.createSequentialGroup()
                        .addGroup(carrefourConfigPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, carrefourConfigPLayout.createSequentialGroup()
                                .addComponent(sliderCycleL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(axeNSL))
                            .addComponent(axeEOL, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(carrefourConfigPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomAxeNSTF, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomAxeEOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(carrefourConfigPLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pDefautB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(previsualB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveB))
                    .addGroup(carrefourConfigPLayout.createSequentialGroup()
                        .addComponent(sliderRougeCommunL)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        carrefourConfigPLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {nomAxeEOTF, nomAxeNSTF});

        carrefourConfigPLayout.setVerticalGroup(
            carrefourConfigPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carrefourConfigPLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(avertissementL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(carrefourConfigPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(carrefourConfigPLayout.createSequentialGroup()
                        .addComponent(cycleL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, carrefourConfigPLayout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(sliderCycle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(carrefourConfigPLayout.createSequentialGroup()
                        .addGroup(carrefourConfigPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomAxeNSTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(axeNSL)
                            .addComponent(sliderCycleL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(carrefourConfigPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomAxeEOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(axeEOL))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(carrefourConfigPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, carrefourConfigPLayout.createSequentialGroup()
                        .addComponent(sliderRougeCommun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, carrefourConfigPLayout.createSequentialGroup()
                        .addComponent(sliderRougeCommunL)
                        .addGap(4, 4, 4)
                        .addGroup(carrefourConfigPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(previsualB)
                            .addComponent(saveB)
                            .addComponent(pDefautB)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, carrefourConfigPLayout.createSequentialGroup()
                        .addComponent(rougeCommunL)
                        .addGap(35, 35, 35))))
        );

        carrefourControlP.setBorder(javax.swing.BorderFactory.createTitledBorder("Carrefour contrôle en temps réel"));

        feuVNSL.setText("Feu véhicule Nord-Sud :");

        feuVEOL.setText("Feu véhicule Est-Ouest :");

        feuPNSL.setText("Feu piéton Nord-Sud :");

        feuPEOL.setText("Feu piéton Est-Ouest :");

        feuVehiculeNSL.setText("1");

        feuVehiculeEOL.setText("1");

        feuPietonNSL.setText("1");

        feuPietonEOL.setText("1");

        historiqueB.setText("Historique");
        historiqueB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historiqueBActionPerformed(evt);
            }
        });

        cycleB.setText("Warning");
        cycleB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cycleBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout carrefourControlPLayout = new javax.swing.GroupLayout(carrefourControlP);
        carrefourControlP.setLayout(carrefourControlPLayout);
        carrefourControlPLayout.setHorizontalGroup(
            carrefourControlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carrefourControlPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(carrefourControlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, carrefourControlPLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(feuPNSL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(feuVEOL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, carrefourControlPLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(feuVNSL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, carrefourControlPLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(feuPEOL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(carrefourControlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(carrefourControlPLayout.createSequentialGroup()
                        .addComponent(feuPietonEOL, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(carrefourControlPLayout.createSequentialGroup()
                        .addGroup(carrefourControlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(feuVehiculeNSL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(feuPietonNSL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(feuVehiculeEOL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addComponent(cycleB, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(historiqueB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(96, 96, 96))))
        );
        carrefourControlPLayout.setVerticalGroup(
            carrefourControlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carrefourControlPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(carrefourControlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(feuVNSL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(feuVehiculeNSL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(carrefourControlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(carrefourControlPLayout.createSequentialGroup()
                        .addGroup(carrefourControlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(carrefourControlPLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(feuVehiculeEOL, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(carrefourControlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(feuVEOL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(carrefourControlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(feuPNSL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(feuPietonNSL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(carrefourControlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(feuPEOL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(feuPietonEOL))
                        .addContainerGap())
                    .addGroup(carrefourControlPLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(carrefourControlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(historiqueB)
                            .addComponent(cycleB))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jMenu1.setText("Carrefour");

        carrefourAbout.setText("A propos de Carrefour");
        carrefourAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrefourAboutActionPerformed(evt);
            }
        });
        jMenu1.add(carrefourAbout);
        jMenu1.add(jSeparator1);

        quitCarrefour.setText("Quitter Carrefour");
        quitCarrefour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitCarrefourActionPerformed(evt);
            }
        });
        jMenu1.add(quitCarrefour);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Vue");

        graphicView.setText("Vue graphique");
        graphicView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphicViewActionPerformed(evt);
            }
        });
        jMenu2.add(graphicView);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Aide");

        carrefourHelp.setText("Aide de Carrefour");
        carrefourHelp.setActionCommand("jMenuItem4");
        carrefourHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrefourHelpActionPerformed(evt);
            }
        });
        jMenu3.add(carrefourHelp);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(carrefourConfigP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(carrefourControlP, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(carrefourConfigP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carrefourControlP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void previsualBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previsualBActionPerformed
        PrevisualisationGUI pGUI = new PrevisualisationGUI(this);
        pGUI.pack();
        pGUI.setLocationRelativeTo(null);
        pGUI.setVisible(true);

    }//GEN-LAST:event_previsualBActionPerformed

    private void cycleBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cycleBActionPerformed

        try {
            if (serveur.getEtat().getFeux(FEUX_VEHICULE_N_S).getValue() == 5) {
                cycleB.setText("Cycle normal");
            } else {
                cycleB.setText("Warning");
                cycleB.setSelected(true);
            };
            serveur.warning(cycleB.isSelected());
        } catch (RemoteException ex) {
            MsgOutils.erreur("RemoteException", "Vous avez perdu la connection "
                    + "avec le serveur. L'application va se fermer ...");
            System.exit(0);
        }
    }//GEN-LAST:event_cycleBActionPerformed

    private void saveBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBActionPerformed
        if (!checkValeursJTable()) {
            MsgOutils.erreur("Problème de cycle", "Un des feux ne respecte "
                    + "pas la durée du cycle.");
        } else {
            try {
                for (int i = 0; i < 4; i++) {
                    for (int j = 1; j < 4; j++) {
                        if ((Integer) tableConfig.getValueAt(i, j) < Integer.parseInt(
                                defaultProps.getProperty("minValue"))
                                || (Integer) tableConfig.getValueAt(i, j) > Integer.parseInt(
                                defaultProps.getProperty("maxValue"))) {
                            throw new NumberFormatException();
                        }
                    }
                }
                vert = new int[]{(Integer) tableConfig.getValueAt(0, 1),
                    (Integer) tableConfig.getValueAt(1, 1),
                    (Integer) tableConfig.getValueAt(2, 1),
                    (Integer) tableConfig.getValueAt(3, 1)};
                orange = new int[]{(Integer) tableConfig.getValueAt(0, 2),
                    (Integer) tableConfig.getValueAt(1, 2),
                    (Integer) tableConfig.getValueAt(2, 2),
                    (Integer) tableConfig.getValueAt(3, 2)};
                rouge = new int[]{(Integer) tableConfig.getValueAt(0, 3),
                    (Integer) tableConfig.getValueAt(1, 3),
                    (Integer) tableConfig.getValueAt(2, 3),
                    (Integer) tableConfig.getValueAt(3, 3)};
                rougeCommun = sliderRougeCommun.getValue();
                nomAxe = new String[]{nomAxeNSTF.getText(), nomAxeEOTF.getText()};
                Carrefour modele = new Carrefour(vert, orange, rouge, rougeCommun, 1000, nomAxe);
                modele.stop();
                if (modele.checkAccident()) {
                    MsgOutils.erreur("Problème de circulation", "Il y a un risque d'accident.\n"
                            + "Vous ne pouvez sauvergarder.");
                } else {
                    if (MsgOutils.confirmation("Sauvegarde", "Voulez-vous sauvegarder les données?")) {
                        FileOutputStream out;
                        try {
                            out = new FileOutputStream("../CarrefourInterface.properties");
                            defaultProps.setProperty("v1", tableConfig.getValueAt(0, 1).toString());
                            defaultProps.setProperty("v2", tableConfig.getValueAt(1, 1).toString());
                            defaultProps.setProperty("vp1", tableConfig.getValueAt(2, 1).toString());
                            defaultProps.setProperty("vp2", tableConfig.getValueAt(3, 1).toString());
                            defaultProps.setProperty("o1", tableConfig.getValueAt(0, 2).toString());
                            defaultProps.setProperty("o2", tableConfig.getValueAt(1, 2).toString());
                            defaultProps.setProperty("op1", tableConfig.getValueAt(2, 2).toString());
                            defaultProps.setProperty("op2", tableConfig.getValueAt(3, 2).toString());
                            defaultProps.setProperty("r1", tableConfig.getValueAt(0, 3).toString());
                            defaultProps.setProperty("r2", tableConfig.getValueAt(1, 3).toString());
                            defaultProps.setProperty("rp1", tableConfig.getValueAt(2, 3).toString());
                            defaultProps.setProperty("rp2", tableConfig.getValueAt(3, 3).toString());
                            defaultProps.setProperty("rc1", "" + sliderRougeCommun.getValue());
                            defaultProps.setProperty("cycle", "" + sliderCycle.getValue());
                            defaultProps.setProperty("axeNS", nomAxeNSTF.getText());
                            defaultProps.setProperty("axeEO", nomAxeEOTF.getText());
                            try {
                                defaultProps.store(out, "--saveConfig--");
                                serveur.reboot();
                            } catch (IOException ex) {
                                MsgOutils.erreur("IOException", "L'ouverture"
                                        + " ou la fermeture du fichier de configuration s'est mal passée.");
                            }
                        } catch (FileNotFoundException ex) {
                            MsgOutils.erreur("FileNotFoundException",
                                    "Fichier de configuration introuvable.");
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                MsgOutils.erreur("Exception", "Les valeurs doivent être numérique et"
                        + " valoir entre " + defaultProps.getProperty("minValue")
                        + " et " + defaultProps.getProperty("maxValue") + " secondes.");
            } catch (NullPointerException ex) {
                MsgOutils.erreur("NullPointerException", "Veuillez remplir toutes les cases du tableaux.");
            }
        }
    }//GEN-LAST:event_saveBActionPerformed
    /**
     * Verifie si le cycle des feux sont egaux
     *
     * @return vrai si le cycle des feux sont égaux sinon faux
     */
    private boolean checkValeursJTable() {
        int cycle = sliderCycle.getValue();
        if (cycle != tableConfig.getValueAt(0, 4) || cycle != tableConfig.getValueAt(1, 4) || cycle != tableConfig.getValueAt(2, 4) || cycle != tableConfig.getValueAt(3, 4)) {
            return false;
        }
        return true;

    }

    public JTable getJTable1() {
        return tableConfig;
    }

    public String[] getNomAxe() {
        return nomAxe;
    }

    public JSlider getJSlider1() {
        return sliderRougeCommun;
    }
    private void sliderRougeCommunStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderRougeCommunStateChanged
        sliderRougeCommunL.setText(sliderRougeCommun.getValue() + " s");
    }//GEN-LAST:event_sliderRougeCommunStateChanged

    private void pDefautBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pDefautBActionPerformed
        init();
    }//GEN-LAST:event_pDefautBActionPerformed

    private void graphicViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphicViewActionPerformed
        VueTpsReel vueGraphique = new VueTpsReel(this);
        vueGraphique.pack();
        vueGraphique.setLocationRelativeTo(null);
        vueGraphique.setVisible(true);
    }//GEN-LAST:event_graphicViewActionPerformed

    private void sliderCycleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderCycleStateChanged
        sliderCycleL.setText(sliderCycle.getValue() + " s");
    }//GEN-LAST:event_sliderCycleStateChanged

    private void tableConfigFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tableConfigFocusGained
        setCycleJTable();
    }//GEN-LAST:event_tableConfigFocusGained

    private void historiqueBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historiqueBActionPerformed
        Historique historique = new Historique(serveur);
        historique.pack();
        historique.setLocationRelativeTo(null);
        historique.setVisible(true);
    }//GEN-LAST:event_historiqueBActionPerformed

    private void quitCarrefourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitCarrefourActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitCarrefourActionPerformed

    private void carrefourHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrefourHelpActionPerformed
        Help help = new Help();
        help.pack();
        help.setLocationRelativeTo(null);
        help.setVisible(true);
    }//GEN-LAST:event_carrefourHelpActionPerformed

    private void carrefourAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrefourAboutActionPerformed
        About about = null;
        try {
            about = new About();
            about.setLocationRelativeTo(null);
        } catch (URISyntaxException ex) {
            MsgOutils.erreur("URISyntaxException", "L'url de la fenêtre About est invalide");
        }
        about.pack();
        about.setVisible(true);
    }//GEN-LAST:event_carrefourAboutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;




                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CarrefourClientAdministrateurGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarrefourClientAdministrateurGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarrefourClientAdministrateurGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarrefourClientAdministrateurGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CarrefourClientAdministrateurGUI dialog = new CarrefourClientAdministrateurGUI(null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avertissementL;
    private javax.swing.JLabel axeEOL;
    private javax.swing.JLabel axeNSL;
    private javax.swing.JMenuItem carrefourAbout;
    private javax.swing.JPanel carrefourConfigP;
    private javax.swing.JPanel carrefourControlP;
    private javax.swing.JMenuItem carrefourHelp;
    private javax.swing.JToggleButton cycleB;
    private javax.swing.JLabel cycleL;
    private javax.swing.JLabel feuPEOL;
    private javax.swing.JLabel feuPNSL;
    private javax.swing.JLabel feuPietonEOL;
    private javax.swing.JLabel feuPietonNSL;
    private javax.swing.JLabel feuVEOL;
    private javax.swing.JLabel feuVNSL;
    private javax.swing.JLabel feuVehiculeEOL;
    private javax.swing.JLabel feuVehiculeNSL;
    private javax.swing.JMenuItem graphicView;
    private javax.swing.JButton historiqueB;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField nomAxeEOTF;
    private javax.swing.JTextField nomAxeNSTF;
    private javax.swing.JButton pDefautB;
    private javax.swing.JButton previsualB;
    private javax.swing.JMenuItem quitCarrefour;
    private javax.swing.JLabel rougeCommunL;
    private javax.swing.JButton saveB;
    private javax.swing.JSlider sliderCycle;
    private javax.swing.JLabel sliderCycleL;
    private javax.swing.JSlider sliderRougeCommun;
    private javax.swing.JLabel sliderRougeCommunL;
    private javax.swing.JTable tableConfig;
    // End of variables declaration//GEN-END:variables
}
