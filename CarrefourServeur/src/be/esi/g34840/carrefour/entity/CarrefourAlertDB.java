/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entité CarrefourAlert
 *
 * @author J4un3
 */
@Entity
@Table(name = "CARREFOURALERT")
@NamedQueries({
    @NamedQuery(name = "CarrefourAlert.findPeriod", query = "SELECT a from CarrefourAlertDB a where a.dateDB between :dateA and :dateB")
})
public class CarrefourAlertDB extends SimulationDB implements Serializable {

    @Column(nullable = false)
    private String destinataire;
    @Column(nullable = false)
    private String corps;

    /**
     * Constructeur par défaut de CarrefourAlertDB
     */
    public CarrefourAlertDB() {
    }
    /**
     * Construit un CarrefourAlertDB avec un message contenant( un destinataire, un corps et la date)
     * @param destinataire le destinataire de l'alerte
     * @param corps le corps de l'alerte
     * @param dateAlert la date de l'alerte
     */
    public CarrefourAlertDB(String destinataire, String corps, Date dateAlert) {
        this.destinataire = destinataire;
        this.corps = corps;
        Timestamp date = new Timestamp(dateAlert.getTime());
        date.setNanos(0);
        super.setDateDB(new Date(date.getTime()));
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
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + (this.destinataire != null ? this.destinataire.hashCode() : 0);
        hash = 13 * hash + (this.corps != null ? this.corps.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarrefourAlertDB other = (CarrefourAlertDB) obj;
        if ((this.destinataire == null) ? (other.destinataire != null) : !this.destinataire.equals(other.destinataire)) {
            return false;
        }
        if ((this.corps == null) ? (other.corps != null) : !this.corps.equals(other.corps)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarrefourAlertDB{" + "destinataire=" + destinataire + ", corps=" + corps + '}';
    }

 
}
