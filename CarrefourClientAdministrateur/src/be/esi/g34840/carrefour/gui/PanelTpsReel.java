/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author J4un3
 */
public class PanelTpsReel extends JComponent {

    public static int FEUX_PIETON_E_O = 3, FEUX_PIETON_N_S = 2,
            FEUX_VEHICULE_E_O = 1, FEUX_VEHICULE_N_S = 0;
    private Image bg;
    private CarrefourClientAdministrateurGUI admin;
    private boolean  warning;

    /**
     * Creates new form PanelTpsReel
     */
    public PanelTpsReel(CarrefourClientAdministrateurGUI admin) {
        this.admin = admin;
        warning = false;
        initComponents();
        feuOn(true);
        bg = new ImageIcon("bg.png").getImage();
    }

    /**
     * Surcharge de la fonction paintComponent() pour afficher notre image
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    private void feuOn(boolean ok) {
        ledNR.setOn(ok);
        ledNO.setOn(ok);
        ledNV.setOn(ok);
        ledEO.setOn(ok);
        ledER.setOn(ok);
        ledEV.setOn(ok);
        ledOV.setOn(ok);
        ledOR.setOn(ok);
        ledSO.setOn(ok);
        ledSR.setOn(ok);
        ledSV.setOn(ok);
        ledOO.setOn(ok);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ledNO = new be.esi.g34840.carrefour.gui.Led();
        ledNV = new be.esi.g34840.carrefour.gui.Led();
        ledNR = new be.esi.g34840.carrefour.gui.Led();
        ledER = new be.esi.g34840.carrefour.gui.Led();
        ledEO = new be.esi.g34840.carrefour.gui.Led();
        ledEV = new be.esi.g34840.carrefour.gui.Led();
        ledSV = new be.esi.g34840.carrefour.gui.Led();
        ledSO = new be.esi.g34840.carrefour.gui.Led();
        ledSR = new be.esi.g34840.carrefour.gui.Led();
        ledOR = new be.esi.g34840.carrefour.gui.Led();
        ledOO = new be.esi.g34840.carrefour.gui.Led();
        ledOV = new be.esi.g34840.carrefour.gui.Led();

        ledNO.setBackground(new java.awt.Color(0, 0, 0));
        ledNO.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout ledNOLayout = new org.jdesktop.layout.GroupLayout(ledNO);
        ledNO.setLayout(ledNOLayout);
        ledNOLayout.setHorizontalGroup(
            ledNOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        ledNOLayout.setVerticalGroup(
            ledNOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        ledNV.setBackground(new java.awt.Color(0, 0, 0));
        ledNV.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout ledNVLayout = new org.jdesktop.layout.GroupLayout(ledNV);
        ledNV.setLayout(ledNVLayout);
        ledNVLayout.setHorizontalGroup(
            ledNVLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        ledNVLayout.setVerticalGroup(
            ledNVLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        ledNR.setBackground(new java.awt.Color(0, 0, 0));
        ledNR.setAlignmentX(0.0F);
        ledNR.setAlignmentY(0.0F);
        ledNR.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout ledNRLayout = new org.jdesktop.layout.GroupLayout(ledNR);
        ledNR.setLayout(ledNRLayout);
        ledNRLayout.setHorizontalGroup(
            ledNRLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 18, Short.MAX_VALUE)
        );
        ledNRLayout.setVerticalGroup(
            ledNRLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        ledER.setBackground(new java.awt.Color(0, 0, 0));
        ledER.setAlignmentX(0.0F);
        ledER.setAlignmentY(0.0F);
        ledER.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout ledERLayout = new org.jdesktop.layout.GroupLayout(ledER);
        ledER.setLayout(ledERLayout);
        ledERLayout.setHorizontalGroup(
            ledERLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        ledERLayout.setVerticalGroup(
            ledERLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        ledEO.setBackground(new java.awt.Color(0, 0, 0));
        ledEO.setAlignmentX(0.0F);
        ledEO.setAlignmentY(0.0F);
        ledEO.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout ledEOLayout = new org.jdesktop.layout.GroupLayout(ledEO);
        ledEO.setLayout(ledEOLayout);
        ledEOLayout.setHorizontalGroup(
            ledEOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 10, Short.MAX_VALUE)
        );
        ledEOLayout.setVerticalGroup(
            ledEOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        ledEV.setBackground(new java.awt.Color(0, 0, 0));
        ledEV.setAlignmentX(0.0F);
        ledEV.setAlignmentY(0.0F);
        ledEV.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout ledEVLayout = new org.jdesktop.layout.GroupLayout(ledEV);
        ledEV.setLayout(ledEVLayout);
        ledEVLayout.setHorizontalGroup(
            ledEVLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        ledEVLayout.setVerticalGroup(
            ledEVLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        ledSV.setBackground(new java.awt.Color(0, 0, 0));
        ledSV.setAlignmentX(0.0F);
        ledSV.setAlignmentY(0.0F);
        ledSV.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout ledSVLayout = new org.jdesktop.layout.GroupLayout(ledSV);
        ledSV.setLayout(ledSVLayout);
        ledSVLayout.setHorizontalGroup(
            ledSVLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 12, Short.MAX_VALUE)
        );
        ledSVLayout.setVerticalGroup(
            ledSVLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        ledSO.setBackground(new java.awt.Color(0, 0, 0));
        ledSO.setAlignmentX(0.0F);
        ledSO.setAlignmentY(0.0F);
        ledSO.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout ledSOLayout = new org.jdesktop.layout.GroupLayout(ledSO);
        ledSO.setLayout(ledSOLayout);
        ledSOLayout.setHorizontalGroup(
            ledSOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 12, Short.MAX_VALUE)
        );
        ledSOLayout.setVerticalGroup(
            ledSOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        ledSR.setBackground(new java.awt.Color(0, 0, 0));
        ledSR.setAlignmentX(0.0F);
        ledSR.setAlignmentY(0.0F);
        ledSR.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout ledSRLayout = new org.jdesktop.layout.GroupLayout(ledSR);
        ledSR.setLayout(ledSRLayout);
        ledSRLayout.setHorizontalGroup(
            ledSRLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 12, Short.MAX_VALUE)
        );
        ledSRLayout.setVerticalGroup(
            ledSRLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        ledOR.setBackground(new java.awt.Color(0, 0, 0));
        ledOR.setAlignmentX(0.0F);
        ledOR.setAlignmentY(0.0F);
        ledOR.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout ledORLayout = new org.jdesktop.layout.GroupLayout(ledOR);
        ledOR.setLayout(ledORLayout);
        ledORLayout.setHorizontalGroup(
            ledORLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 12, Short.MAX_VALUE)
        );
        ledORLayout.setVerticalGroup(
            ledORLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        ledOO.setBackground(new java.awt.Color(0, 0, 0));
        ledOO.setAlignmentX(0.0F);
        ledOO.setAlignmentY(0.0F);
        ledOO.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout ledOOLayout = new org.jdesktop.layout.GroupLayout(ledOO);
        ledOO.setLayout(ledOOLayout);
        ledOOLayout.setHorizontalGroup(
            ledOOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        ledOOLayout.setVerticalGroup(
            ledOOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 10, Short.MAX_VALUE)
        );

        ledOV.setBackground(new java.awt.Color(0, 0, 0));
        ledOV.setAlignmentX(0.0F);
        ledOV.setAlignmentY(0.0F);
        ledOV.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout ledOVLayout = new org.jdesktop.layout.GroupLayout(ledOV);
        ledOV.setLayout(ledOVLayout);
        ledOVLayout.setHorizontalGroup(
            ledOVLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        ledOVLayout.setVerticalGroup(
            ledOVLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 14, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(151, 151, 151)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(ledNV, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(ledNO, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(ledNR, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(147, 147, 147)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, ledEO, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, ledER, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(ledEV, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(74, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(ledOR, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(ledOO, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(ledOV, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(ledSR, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(ledSO, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(ledSV, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(171, 171, 171))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(22, 22, 22)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(ledNR, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(ledER, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(ledNO, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(ledEO, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(ledNV, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(ledEV, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(60, 60, 60)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(ledSR, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(ledSO, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(ledSV, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(ledOR, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(ledOO, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(ledOV, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private be.esi.g34840.carrefour.gui.Led ledEO;
    private be.esi.g34840.carrefour.gui.Led ledER;
    private be.esi.g34840.carrefour.gui.Led ledEV;
    private be.esi.g34840.carrefour.gui.Led ledNO;
    private be.esi.g34840.carrefour.gui.Led ledNR;
    private be.esi.g34840.carrefour.gui.Led ledNV;
    private be.esi.g34840.carrefour.gui.Led ledOO;
    private be.esi.g34840.carrefour.gui.Led ledOR;
    private be.esi.g34840.carrefour.gui.Led ledOV;
    private be.esi.g34840.carrefour.gui.Led ledSO;
    private be.esi.g34840.carrefour.gui.Led ledSR;
    private be.esi.g34840.carrefour.gui.Led ledSV;
    // End of variables declaration//GEN-END:variables

    public void update() {
        for (int i = 0; i < 4; i++) {
            switch (admin.getEtat().getFeux(i).getValue()) {
                case 0:
                    setFeuVert(i);
                    break;
                case 1:
                    setFeuOrange(i);
                    break;
                case 2:
                    setFeuRouge(i);
                    break;
                case 3:
                    setFeuOrange(i);
                    break;
                case 5:
                    setFeuWarning();
            }
        }
    }

    private void setFeuVert(int i) {
        switch (i) {
            case 0:
                ledNV.setColor(Color.green);
                ledSV.setColor(Color.green);
                ledNR.setColor(Color.black);
                ledSR.setColor(Color.black);
                break;
            case 1:
                ledEV.setColor(Color.green);
                ledER.setColor(Color.black);
                ledOR.setColor(Color.black);
                ledOV.setColor(Color.green);
                break;
            case 2:
            case 3:
        }
    }

    private void setFeuOrange(int i) {
        switch (i) {
            case 0:
                ledSO.setColor(Color.orange);
                ledNO.setColor(Color.orange);
                ledNV.setColor(Color.black);
                ledSV.setColor(Color.black);
                break;
            case 1:
                ledEO.setColor(Color.orange);
                ledOO.setColor(Color.orange);
                ledEV.setColor(Color.black);
                ledOV.setColor(Color.black);
                break;
            case 2:
            case 3:
        }
    }

    private void setFeuRouge(int i) {
        switch (i) {
            case 0:
                ledNR.setColor(Color.red);
                ledSR.setColor(Color.red);
                ledNO.setColor(Color.black);
                ledSO.setColor(Color.black);
                break;
            case 1:
                ledER.setColor(Color.red);
                ledOR.setColor(Color.red);
                ledEO.setColor(Color.black);
                ledOO.setColor(Color.black);
                break;
            case 2:
            case 3:
        }
    }

    private void setFeuWarning() {
        if (warning) {
            ledNO.setColor(Color.black);
            ledSO.setColor(Color.black);
            ledEO.setColor(Color.black);
            ledOO.setColor(Color.black);
        } else {
            ledSO.setColor(Color.orange);
            ledNO.setColor(Color.orange);
            ledEO.setColor(Color.orange);
            ledOO.setColor(Color.orange);
        }
            warning = !warning;

    }
}
