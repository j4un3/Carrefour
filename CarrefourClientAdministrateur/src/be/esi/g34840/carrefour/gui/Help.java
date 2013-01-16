/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.gui;

import javax.swing.JFrame;

/**
 *
 * @author J4un3
 */
public class Help extends javax.swing.JDialog {

    /**
     * Creates new form Help
     */
    public Help() {
        super(new JFrame(), false);
        setResizable(false);
        initComponents();
        configuration();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boutonPanel = new javax.swing.JPanel();
        carrefourHisto = new javax.swing.JButton();
        carrefourWarning = new javax.swing.JButton();
        carrefourDefault = new javax.swing.JButton();
        carrefourPrevi = new javax.swing.JButton();
        carrefourConfig = new javax.swing.JButton();
        carrefourSave = new javax.swing.JButton();
        descriptionP = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionText = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        carrefourHisto.setText("Historique");
        carrefourHisto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrefourHistoActionPerformed(evt);
            }
        });

        carrefourWarning.setText("Warning");
        carrefourWarning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrefourWarningActionPerformed(evt);
            }
        });

        carrefourDefault.setText("Par défaut");
        carrefourDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrefourDefaultActionPerformed(evt);
            }
        });

        carrefourPrevi.setText("Prévisualiser");
        carrefourPrevi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrefourPreviActionPerformed(evt);
            }
        });

        carrefourConfig.setText("Configuration");
        carrefourConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrefourConfigActionPerformed(evt);
            }
        });

        carrefourSave.setText("Sauvegarder");
        carrefourSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrefourSaveActionPerformed(evt);
            }
        });

        descriptionP.setBorder(javax.swing.BorderFactory.createTitledBorder("aaaaaaaaaaaaa"));

        jScrollPane1.setViewportView(descriptionText);

        org.jdesktop.layout.GroupLayout descriptionPLayout = new org.jdesktop.layout.GroupLayout(descriptionP);
        descriptionP.setLayout(descriptionPLayout);
        descriptionPLayout.setHorizontalGroup(
            descriptionPLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(descriptionPLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addContainerGap())
        );
        descriptionPLayout.setVerticalGroup(
            descriptionPLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1)
        );

        org.jdesktop.layout.GroupLayout boutonPanelLayout = new org.jdesktop.layout.GroupLayout(boutonPanel);
        boutonPanel.setLayout(boutonPanelLayout);
        boutonPanelLayout.setHorizontalGroup(
            boutonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(boutonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(boutonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(carrefourWarning)
                    .add(carrefourHisto)
                    .add(carrefourSave)
                    .add(carrefourPrevi)
                    .add(carrefourDefault)
                    .add(carrefourConfig))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(descriptionP, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        boutonPanelLayout.linkSize(new java.awt.Component[] {carrefourConfig, carrefourDefault, carrefourHisto, carrefourPrevi, carrefourSave, carrefourWarning}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        boutonPanelLayout.setVerticalGroup(
            boutonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(boutonPanelLayout.createSequentialGroup()
                .add(carrefourConfig)
                .add(18, 18, 18)
                .add(carrefourDefault)
                .add(18, 18, 18)
                .add(carrefourPrevi)
                .add(18, 18, 18)
                .add(carrefourSave)
                .add(18, 18, 18)
                .add(carrefourWarning)
                .add(18, 18, 18)
                .add(carrefourHisto)
                .add(0, 0, Short.MAX_VALUE))
            .add(boutonPanelLayout.createSequentialGroup()
                .add(descriptionP, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(boutonPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(boutonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void carrefourConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrefourConfigActionPerformed
 configuration();
    }//GEN-LAST:event_carrefourConfigActionPerformed
    private void configuration(){
               descriptionP.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuration"));
        descriptionText.setText("La configuration est divisée en un tableau qui représente "
                + "le temps de chaque couleur du feu, un slider qui représente la durée "
                + "d'un cycle, un jslider qui représente la durée du rouge commun entre "
                + "les feux et les deux noms des axes du carrefour.\n"
                + "Toutes ses variables sont modifiable pour autant que la modification "
                + "respecte le concept même du carrefour.\n"
                + "Pour vous aidez à regulariser les cycles de chaques feu, la dernière"
                + " colonne du tableau nommé \"Durée cycle\" permet de savoir le cycle du feu.\n");
    }
    private void carrefourDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrefourDefaultActionPerformed
        descriptionP.setBorder(javax.swing.BorderFactory.createTitledBorder("Par défaut"));
                descriptionText.setText("Le bouton \"Par défaut\" permet de remettre "
                        + "toute la configuration à la dernière sauvegarde effectuée.");
    }//GEN-LAST:event_carrefourDefaultActionPerformed

    private void carrefourPreviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrefourPreviActionPerformed
    descriptionP.setBorder(javax.swing.BorderFactory.createTitledBorder("Prévisualisation"));
                descriptionText.setText("Le bouton \"Prévisualiser\" permet d'obtenir "
                        + "une nouvelle fenêtre où vous pourrez tester la configuration "
                        + "du tableau tout en ayant le contrôle de la durée d'une seconde en temps réel.");
    }//GEN-LAST:event_carrefourPreviActionPerformed

    private void carrefourSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrefourSaveActionPerformed
    descriptionP.setBorder(javax.swing.BorderFactory.createTitledBorder("Sauvegarder"));
                descriptionText.setText("Le bouton \"Sauvergarder\" permet de "
                        + "sauvegarder la configuration du tableau dans un fichier "
                        + "properties afin de l'appliquer sur le modèle directement.\n"
                        + "Le modèle attendra un nouveau cycle pour appliquer les changements effectués.");
    }//GEN-LAST:event_carrefourSaveActionPerformed

    private void carrefourWarningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrefourWarningActionPerformed
    descriptionP.setBorder(javax.swing.BorderFactory.createTitledBorder("Warning"));
                descriptionText.setText("Le bouton \"Warning\"/\"Cycle normal\"  "
                        + "permet de mettre le carrefour à un état \"tous feux "
                        + "clignotants\" à un état \"cycle normal\" et vice-versa\n"
                        + "Ce dernier enverra un émail pour prevenir l'administrateur "
                        + "du moment de la mise en warning\n"
                        + "Le nom du bouton donne l'état du moment. Une fois celui-ci activé, "
                        + "il faudra attendre que les feux soit en état \"warning\" avant de "
                        + "pouvoir relancer l'état \"cycle normal\".");
    }//GEN-LAST:event_carrefourWarningActionPerformed

    private void carrefourHistoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrefourHistoActionPerformed
    descriptionP.setBorder(javax.swing.BorderFactory.createTitledBorder("Historique"));
                descriptionText.setText("Le bouton \"Historique\" permet d'obtenir "
                        + "une nouvelle fenêtre où l'on pourra recuperer les traces et "
                        + "les statistiques du carrefour.\n"
                        + "Ces traces et statistiques sont recuperer d'une base de "
                        + "donné gràce à une période ou un moment donné.");
    }//GEN-LAST:event_carrefourHistoActionPerformed

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
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Help dialog = new Help();
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
    private javax.swing.JPanel boutonPanel;
    private javax.swing.JButton carrefourConfig;
    private javax.swing.JButton carrefourDefault;
    private javax.swing.JButton carrefourHisto;
    private javax.swing.JButton carrefourPrevi;
    private javax.swing.JButton carrefourSave;
    private javax.swing.JButton carrefourWarning;
    private javax.swing.JPanel descriptionP;
    private javax.swing.JTextPane descriptionText;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}