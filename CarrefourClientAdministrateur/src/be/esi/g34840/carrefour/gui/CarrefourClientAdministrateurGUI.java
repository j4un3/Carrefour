/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.gui;

import be.esi.alg3.carrefour.mvc.model.CarrefourEtat;
import be.esi.g34840.carrefour.business.CarrefourServeurInterface;
import be.esi.g34840.carrefour.implementation.VueCarrefourClientAdministrateur;
import be.esi.gui.outils.MsgOutils;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author user0
 */
public class CarrefourClientAdministrateurGUI extends javax.swing.JDialog {

    public static int FEUX_PIETON_E_O = 3, FEUX_PIETON_N_S = 2,
            FEUX_VEHICULE_E_O = 1, FEUX_VEHICULE_N_S = 0;
    private CarrefourServeurInterface serveur;
    private VueCarrefourClientAdministrateur client;
    private Properties defaultProps;
    private TimerTask timerTask;
    private Timer timer;
    private Led led;

    /**
     * Creates new form CarrefourClientAdministrateurGUI
     */
    public CarrefourClientAdministrateurGUI(final CarrefourServeurInterface serveur) {
        super(new JFrame(), true);

        FileInputStream in = null;
        try {
            this.serveur = serveur;
            defaultProps = new Properties();
            in = new FileInputStream("../CarrefourInterface.properties");
            defaultProps.load(in);
            initComponents();
            client = new VueCarrefourClientAdministrateur(this);
            this.serveur.abonne(client);
            init();
        } catch (ConnectException ex) {
            MsgOutils.erreur("ConnectException", "Problème de connection avec le serveur.\n L'application va se terminer.");
            System.exit(0);
        } catch (FileNotFoundException ex) {
            MsgOutils.erreur("FileNotFoundException", "Fichier de configuration introuvable.");
        } catch (IOException ex) {
            MsgOutils.erreur("IOException", "L'ouverture ou la fermeture du fichier de configuration s'est mal passée.");
            saveB.setEnabled(false);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                MsgOutils.erreur("IOException", "L'ouverture ou la fermeture du fichier de configuration s'est mal passée.");
                saveB.setEnabled(false);
            }
        }
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    serveur.isAlive();
                } catch (RemoteException ex) {
                    timerTask.cancel();
                    MsgOutils.warning("Attention RemoteException", "Vous avez perdu la connection avec le serveur.\n Le contrôle du carrefour en temps réel n'est plus disponible.\n Mais vous pouvez toujours configurer ou voir l'historique du carrefour.");
                    cycleB.setEnabled(false);
                }
            }
        };
        timer.schedule(timerTask, 0, 5000);
        addWindowListener(new WindowAdapter() {
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
    }

    private void init() {
        avertissementL.setText("Les durées sont calculées en secondes et "
                + "leur valeur doit être comprise entre "
                + defaultProps.getProperty("minValue") + " et "
                + defaultProps.getProperty("maxValue") + " secondes.");
        jSliderL.setText(defaultProps.getProperty("rc1") + " s");
        jSlider1.setValue(Integer.parseInt(defaultProps.getProperty("rc1")));
        avertissementL.setForeground(Color.RED);
        jTable1.setValueAt(Integer.parseInt(defaultProps.getProperty("v1")), 0, 1);
        jTable1.setValueAt(Integer.parseInt(defaultProps.getProperty("v2")), 1, 1);
        jTable1.setValueAt(Integer.parseInt(defaultProps.getProperty("vp1")), 2, 1);
        jTable1.setValueAt(Integer.parseInt(defaultProps.getProperty("vp2")), 3, 1);
        jTable1.setValueAt(Integer.parseInt(defaultProps.getProperty("o1")), 0, 2);
        jTable1.setValueAt(Integer.parseInt(defaultProps.getProperty("o2")), 1, 2);
        jTable1.setValueAt(Integer.parseInt(defaultProps.getProperty("op1")), 2, 2);
        jTable1.setValueAt(Integer.parseInt(defaultProps.getProperty("op2")), 3, 2);
        jTable1.setValueAt(Integer.parseInt(defaultProps.getProperty("r1")), 0, 3);
        jTable1.setValueAt(Integer.parseInt(defaultProps.getProperty("r2")), 1, 3);
        jTable1.setValueAt(Integer.parseInt(defaultProps.getProperty("rp1")), 2, 3);
        jTable1.setValueAt(Integer.parseInt(defaultProps.getProperty("rp2")), 3, 3);

    }

    public void update() {
        try {
            CarrefourEtat etat = serveur.getEtat();
            feuVehiculeNSL.setText(etat.getFeux(FEUX_VEHICULE_N_S).getLibelle());
            feuVehiculeEOL.setText(etat.getFeux(FEUX_VEHICULE_E_O).getLibelle());
            feuPietonEOL.setText(etat.getFeux(FEUX_PIETON_E_O).getLibelle());
            feuPietonNSL.setText(etat.getFeux(FEUX_PIETON_N_S).getLibelle());
        } catch (RemoteException ex) {
            MsgOutils.erreur("RemoteException", "Vous avez perdu la connection avec le serveur.");
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        previsualB = new javax.swing.JButton();
        saveB = new javax.swing.JButton();
        avertissementL = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jSliderL = new javax.swing.JLabel();
        pDefautB = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        feuVehiculeNSL = new javax.swing.JLabel();
        feuVehiculeEOL = new javax.swing.JLabel();
        feuPietonNSL = new javax.swing.JLabel();
        feuPietonEOL = new javax.swing.JLabel();
        historiqueB = new javax.swing.JButton();
        cycleB = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Client Administrateur du carrefour");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuration des durées en secondes des feux d'un carrefour"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Feu véhicule Nord-Sud", null, null, null},
                {"Feu véhicule Est-Ouest", null, null, null},
                {"Feu piéton Nord-sud", null, null, null},
                {"Feu piéton Est-Ouest", null, null, null}
            },
            new String [] {
                "Feux", "Vert", "Orange", "Rouge"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoscrolls(false);
        jScrollPane1.setViewportView(jTable1);

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

        jLabel5.setText("Feu rouge commun :");

        jSlider1.setMajorTickSpacing(2);
        jSlider1.setMaximum(Integer.parseInt(defaultProps.getProperty("maxValueCommun")));
        jSlider1.setMinimum(Integer.parseInt(defaultProps.getProperty("minValueCommun")));
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.setValue(Integer.parseInt(defaultProps.getProperty("rc1")));
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jSliderL.setText("1 s");

        pDefautB.setText("Par défaut");
        pDefautB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pDefautBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(avertissementL)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pDefautB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(previsualB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saveB))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSliderL)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(avertissementL)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addComponent(jSliderL)
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(previsualB)
                                    .addComponent(saveB)
                                    .addComponent(pDefautB)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Carrefour contrôle en temps réel"));

        jLabel1.setText("Feu véhicule Nord-Sud :");

        jLabel2.setText("Feu véhicule Est-Ouest :");

        jLabel3.setText("Feu piéton Nord-Sud :");

        jLabel4.setText("Feu piéton Est-Ouest :");

        feuVehiculeNSL.setText("1");

        feuVehiculeEOL.setText("1");

        feuPietonNSL.setText("1");

        feuPietonEOL.setText("1");

        historiqueB.setText("Historique");

        cycleB.setText("Warning");
        cycleB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cycleBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(feuVehiculeEOL, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(feuPietonNSL))
                        .addGap(89, 89, 89)
                        .addComponent(cycleB, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(historiqueB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(122, 122, 122))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(feuVehiculeNSL)
                            .addComponent(feuPietonEOL))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(feuVehiculeNSL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(historiqueB)
                        .addComponent(cycleB))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(feuVehiculeEOL, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(feuPietonNSL))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(feuPietonEOL))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, jPanel2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void previsualBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previsualBActionPerformed
        PrevisualisationGUI pGUI = new PrevisualisationGUI(this);
        pGUI.setVisible(true);
        pGUI.pack();
    }//GEN-LAST:event_previsualBActionPerformed

    private void cycleBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cycleBActionPerformed
        try {
            serveur.warning(cycleB.isSelected());
            cycleB.setText(cycleB.isSelected() ? "Cycle normal" : "Warning");
        } catch (RemoteException ex) {
            MsgOutils.erreur("RemoteException", "Vous avez perdu la connection avec le serveur. L'application va se fermer ...");
            System.exit(0);
        }
    }//GEN-LAST:event_cycleBActionPerformed

    private void saveBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBActionPerformed
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if ((Integer) jTable1.getValueAt(i, j) < Integer.parseInt(
                            defaultProps.getProperty("minValue")) || (Integer) jTable1.getValueAt(i, j) > Integer.parseInt(
                            defaultProps.getProperty("maxValue"))) {
                        throw new NumberFormatException();
                    }
                }
            }

            if (MsgOutils.confirmation("Sauvegarde", "Voulez-vous sauvegarder les données?")) {
                FileOutputStream out;
                try {
                    out = new FileOutputStream("../CarrefourInterface.properties");
                    defaultProps.setProperty("v1", jTable1.getValueAt(0, 1).toString());
                    defaultProps.setProperty("v2", jTable1.getValueAt(1, 1).toString());
                    defaultProps.setProperty("vp1", jTable1.getValueAt(2, 1).toString());
                    defaultProps.setProperty("vp2", jTable1.getValueAt(3, 1).toString());
                    defaultProps.setProperty("o1", jTable1.getValueAt(0, 2).toString());
                    defaultProps.setProperty("o2", jTable1.getValueAt(1, 2).toString());
                    defaultProps.setProperty("op1", jTable1.getValueAt(2, 2).toString());
                    defaultProps.setProperty("op2", jTable1.getValueAt(3, 2).toString());
                    defaultProps.setProperty("r1", jTable1.getValueAt(0, 3).toString());
                    defaultProps.setProperty("r2", jTable1.getValueAt(1, 3).toString());
                    defaultProps.setProperty("rp1", jTable1.getValueAt(2, 3).toString());
                    defaultProps.setProperty("rp2", jTable1.getValueAt(3, 3).toString());
                    try {
                        defaultProps.store(out, "--saveConfig--");
                    } catch (IOException ex) {
                        MsgOutils.erreur("IOException", "L'ouverture ou la fermeture du fichier de configuration s'est mal passée.");
                    }
                } catch (FileNotFoundException ex) {
                    MsgOutils.erreur("FileNotFoundException", "Fichier de configuration introuvable.");
                }
            }
        } catch (NumberFormatException ex) {
            MsgOutils.erreur("Exception", "Les valeurs doivent être numérique et"
                    + " valoir entre " + defaultProps.getProperty("minValue")
                    + " et " + defaultProps.getProperty("maxValue") + " secondes.");
        } catch (NullPointerException ex) {
            MsgOutils.erreur("NullPointerException", "Veuillez remplir toute les cases du tableaux !!!");
        }
    }//GEN-LAST:event_saveBActionPerformed

    public JTable getjTable1() {
        return jTable1;
    }

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        jSliderL.setText(jSlider1.getValue() + " s");
    }//GEN-LAST:event_jSlider1StateChanged

    private void pDefautBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pDefautBActionPerformed
        init();        // TODO add your handling code here:
    }//GEN-LAST:event_pDefautBActionPerformed

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
            java.util.logging.Logger.getLogger(CarrefourClientAdministrateurGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarrefourClientAdministrateurGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarrefourClientAdministrateurGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarrefourClientAdministrateurGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JToggleButton cycleB;
    private javax.swing.JLabel feuPietonEOL;
    private javax.swing.JLabel feuPietonNSL;
    private javax.swing.JLabel feuVehiculeEOL;
    private javax.swing.JLabel feuVehiculeNSL;
    private javax.swing.JButton historiqueB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JLabel jSliderL;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton pDefautB;
    private javax.swing.JButton previsualB;
    private javax.swing.JButton saveB;
    // End of variables declaration//GEN-END:variables
}
