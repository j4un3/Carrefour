/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.concept;

import java.awt.Color;

/**
 * Enumération d'un feu
 *
 * @author g34840
 */
public enum FeuEnum {

    VERT("Vert", 0, Color.GREEN),
    ORANGE("Orange", 1, Color.ORANGE),
    ROUGE("Rouge", 2, Color.RED),
    VERT_CLIGNOTANT("Vert clignotant", 3, Color.ORANGE),
    ETEINT("Eteint", 4, Color.white),
    WARNING("WARNING", 5, Color.BLACK);
    private String libelle;
    private int value;
    private Color color;

    private FeuEnum(String libelle, int feu, Color color) {
        this.libelle = libelle;
        this.value = feu;
        this.color = color;
    }

    /**
     * Retourne le libellé d'un feu
     *
     * @return libelle le libellé d'un feu
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Retourne la valeur d'un feu
     *
     * @return value la valeur d'un feu
     */
    public int getValue() {
        return value;
    }

    /**
     * Retourne la couleur d'un feu
     *
     * @return color la couleur d'un feu
     */
    public Color getColor() {
        return color;
    }
}
