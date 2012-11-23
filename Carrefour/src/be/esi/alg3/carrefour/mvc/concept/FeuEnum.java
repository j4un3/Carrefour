/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.alg3.carrefour.mvc.concept;

/**
 *
 * @author g34840
 */
public enum FeuEnum {

    WARNING("WARNING", 6),
    ETEINT("Eteint", 5),
    VERT_CLIGNOTANT("Vert clignotant", 4),
    VERT("Vert", 3),
    ORANGE("Orange", 2),
    ROUGE("Rouge", 1);
    
    private String libelle;
    private int feu;

    private FeuEnum(String libelle, int feu) {
        this.libelle = libelle;
        this.feu = feu;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getFeu() {
        return feu;
    }
}
