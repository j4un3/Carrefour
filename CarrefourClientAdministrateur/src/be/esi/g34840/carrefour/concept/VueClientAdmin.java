/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.concept;

/**
 * Classe qui permet de rassembler toutes les VueClientAdmin
 * Permet d'éviter de surcharger le serveur de vues
 * @author J4un3
 */
public interface VueClientAdmin {
    /**
     * Permet de mettre à jour les vueClientAdmin abonnées au proxy
     */
    public void update();
}
