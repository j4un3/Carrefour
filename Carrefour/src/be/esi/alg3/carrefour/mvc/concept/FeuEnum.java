/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.alg3.carrefour.mvc.concept;

import java.awt.Color;

/**
 *
 * @author g34840
 */
public enum FeuEnum {

    VERT("Vert", 0, Color.GREEN),
    ORANGE("Orange", 1, Color.ORANGE),
    ROUGE("Rouge", 2, Color.RED),
    VERT_CLIGNOTANT("Vert clignotant", 3, Color.GRAY),
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

    public String getLibelle() {
        return libelle;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }
}
