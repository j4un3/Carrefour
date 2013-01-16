/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe DTO(Data Transfert Object) qui permet de simplifier le transferts
 * de données entre les sous-systèmes de l'application
 * @author J4un3
 */
public class CarrefourAlert extends CarrefourSimulation implements Serializable{

    private String destinataire;
    private String corps;

    public CarrefourAlert(String destinataire, String corps,Date date) {
        super(date);
        this.destinataire = destinataire;
        this.corps = corps;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    @Override
    public String toString() {
        return "CarrefourAlert{" + "destinataire=" + destinataire + ", corps=" + corps + "}";
    }
    
}
