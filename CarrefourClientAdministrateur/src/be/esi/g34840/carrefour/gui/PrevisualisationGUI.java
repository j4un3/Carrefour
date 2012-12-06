/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.gui;

import be.esi.alg3.carrefour.mvc.concept.CarrefourVueInterface;
import be.esi.alg3.carrefour.mvc.model.Carrefour;
import be.esi.alg3.carrefour.mvc.model.CarrefourEtat;
import javax.swing.JFrame;

/**
 *
 * @author J4un3
 */
public class PrevisualisationGUI extends javax.swing.JDialog implements CarrefourVueInterface {

    public static int FEUX_PIETON_E_O = 3, FEUX_PIETON_N_S = 2,
            FEUX_VEHICULE_E_O = 1, FEUX_VEHICULE_N_S = 0;
    /**
     * Creates new form PrevisualisationGUI
     */
    private int[] vert, orange, rouge;
    private int rougeCommun;
    private Carrefour carrefour;

    public PrevisualisationGUI(CarrefourClientAdministrateurGUI gui) {
        super(new JFrame(), true);
        initComponents();
        vert = new int[]{(Integer) gui.getjTable1().getValueAt(0, 1), (Integer) gui.getjTable1().getValueAt(1, 1), (Integer) gui.getjTable1().getValueAt(2, 1), (Integer) gui.getjTable1().getValueAt(3, 1)};
        orange = new int[]{(Integer) gui.getjTable1().getValueAt(0, 2), (Integer) gui.getjTable1().getValueAt(1, 2), (Integer) gui.getjTable1().getValueAt(2, 2), (Integer) gui.getjTable1().getValueAt(3, 2)};
        rouge = new int[]{(Integer) gui.getjTable1().getValueAt(0, 3), (Integer) gui.getjTable1().getValueAt(1, 3), (Integer) gui.getjTable1().getValueAt(2, 3), (Integer) gui.getjTable1().getValueAt(3, 3)};
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        feuVNSL = new javax.swing.JLabel();
        feuVEOL = new javax.swing.JLabel();
        feuPNSL = new javax.swing.JLabel();
        feuPEOL = new javax.swing.JLabel();
        ledFeuVNS = new be.esi.g34840.carrefour.gui.Led();
        ledFeuVEO = new be.esi.g34840.carrefour.gui.Led();
        ledFeuPNS = new be.esi.g34840.carrefour.gui.Led();
        ledFeuPEO = new be.esi.g34840.carrefour.gui.Led();
        jPanel2 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        jLabel5 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        msL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Prévisualisation du carrefour"));

        jLabel1.setText("Feu véhicule Nord-Sud :");

        jLabel2.setText("Feu véhicule Est-Ouest :");

        jLabel3.setText("Feu piéton Nord-Sud :");

        jLabel4.setText("Feu piéton Est-Ouest :");

        feuVNSL.setText("1");

        feuVEOL.setText("1");

        feuPNSL.setText("1");

        feuPEOL.setText("1");

        org.jdesktop.layout.GroupLayout ledFeuVNSLayout = new org.jdesktop.layout.GroupLayout(ledFeuVNS);
        ledFeuVNS.setLayout(ledFeuVNSLayout);
        ledFeuVNSLayout.setHorizontalGroup(
            ledFeuVNSLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 18, Short.MAX_VALUE)
        );
        ledFeuVNSLayout.setVerticalGroup(
            ledFeuVNSLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 22, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout ledFeuVEOLayout = new org.jdesktop.layout.GroupLayout(ledFeuVEO);
        ledFeuVEO.setLayout(ledFeuVEOLayout);
        ledFeuVEOLayout.setHorizontalGroup(
            ledFeuVEOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 19, Short.MAX_VALUE)
        );
        ledFeuVEOLayout.setVerticalGroup(
            ledFeuVEOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 22, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout ledFeuPNSLayout = new org.jdesktop.layout.GroupLayout(ledFeuPNS);
        ledFeuPNS.setLayout(ledFeuPNSLayout);
        ledFeuPNSLayout.setHorizontalGroup(
            ledFeuPNSLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 19, Short.MAX_VALUE)
        );
        ledFeuPNSLayout.setVerticalGroup(
            ledFeuPNSLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 22, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout ledFeuPEOLayout = new org.jdesktop.layout.GroupLayout(ledFeuPEO);
        ledFeuPEO.setLayout(ledFeuPEOLayout);
        ledFeuPEOLayout.setHorizontalGroup(
            ledFeuPEOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 19, Short.MAX_VALUE)
        );
        ledFeuPEOLayout.setVerticalGroup(
            ledFeuPEOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 22, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel4)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel3)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel2)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(feuPNSL)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(ledFeuPNS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(feuPEOL)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(ledFeuPEO, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(feuVNSL)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(ledFeuVNS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(feuVEOL)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(ledFeuVEO, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(189, 189, 189))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel1)
                        .add(feuVNSL))
                    .add(ledFeuVNS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel2)
                        .add(feuVEOL))
                    .add(ledFeuVEO, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel3)
                        .add(feuPNSL))
                    .add(ledFeuPNS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel4)
                        .add(feuPEOL))
                    .add(ledFeuPEO, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuration de la prévisualisation"));

        jSlider1.setMajorTickSpacing(100);
        jSlider1.setMaximum(10000);
        jSlider1.setMinimum(1);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setToolTipText("vitesse d'un tick qui vaut en réalité 1 seconde");
        jSlider1.setValue(1000);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel5.setText("Vitesse de la prévisualisation :");

        jToggleButton1.setText("Visualiser");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        msL.setText("1000 ms");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 197, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSlider1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 190, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(msL))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(181, 181, 181)
                        .add(jToggleButton1)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSlider1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel5)
                            .add(msL))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 26, Short.MAX_VALUE)
                .add(jToggleButton1)
                .add(26, 26, 26))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if (jToggleButton1.isSelected()) {
            jToggleButton1.setText("Stop");
            carrefour = new Carrefour(vert, orange, rouge, rougeCommun, jSlider1.getValue());
            carrefour.inscription(this);
            jSlider1.setEnabled(false);
            ledFeuPEO.setOn(true);
            ledFeuVNS.setOn(true);
            ledFeuVEO.setOn(true);
            ledFeuPNS.setOn(true);
        } else {
            ledFeuPEO.setOn(false);
            ledFeuVNS.setOn(false);
            ledFeuVEO.setOn(false);
            ledFeuPNS.setOn(false);
            carrefour.desinscription(this);
            jSlider1.setEnabled(true);
            jToggleButton1.setText("Prévisualiser");
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        msL.setText(jSlider1.getValue() + " ms");
    }//GEN-LAST:event_jSlider1StateChanged

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
            java.util.logging.Logger.getLogger(PrevisualisationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrevisualisationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrevisualisationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrevisualisationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PrevisualisationGUI dialog = new PrevisualisationGUI(null);
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
    private javax.swing.JLabel feuPEOL;
    private javax.swing.JLabel feuPNSL;
    private javax.swing.JLabel feuVEOL;
    private javax.swing.JLabel feuVNSL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JToggleButton jToggleButton1;
    private be.esi.g34840.carrefour.gui.Led led4;
    private be.esi.g34840.carrefour.gui.Led led5;
    private be.esi.g34840.carrefour.gui.Led ledFeuPEO;
    private be.esi.g34840.carrefour.gui.Led ledFeuPNS;
    private be.esi.g34840.carrefour.gui.Led ledFeuVEO;
    private be.esi.g34840.carrefour.gui.Led ledFeuVNS;
    private javax.swing.JLabel msL;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
        CarrefourEtat etat = carrefour.getEtat();
        feuVNSL.setText(etat.getFeux(FEUX_VEHICULE_N_S).getLibelle());
        feuVEOL.setText(etat.getFeux(FEUX_VEHICULE_E_O).getLibelle());
        feuPNSL.setText(etat.getFeux(FEUX_PIETON_N_S).getLibelle());
        feuPEOL.setText(etat.getFeux(FEUX_PIETON_E_O).getLibelle());

        ledFeuVNS.setColor(etat.getFeux(FEUX_VEHICULE_N_S).getColor());
        ledFeuVEO.setColor(etat.getFeux(FEUX_VEHICULE_E_O).getColor());
        ledFeuPNS.setColor(etat.getFeux(FEUX_PIETON_N_S).getColor());
        ledFeuPEO.setColor(etat.getFeux(FEUX_PIETON_E_O).getColor());
    }
}
