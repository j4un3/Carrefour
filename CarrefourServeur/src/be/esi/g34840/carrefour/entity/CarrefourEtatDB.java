/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.entity;

import be.esi.g34840.carrefour.concept.FeuEnum;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entité CarrefourEtat
 *
 * @author J4un3
 */
@Entity
@Table(name = "CARREFOURETAT")
@NamedQueries({
    @NamedQuery(name = "CarrefourEtat.count", query = "SELECT count(e) FROM CarrefourEtatDB e WHERE e.dateDB between :dateA and :dateB"),
    @NamedQuery(name = "CarrefourEtat.findMoment", query = "SELECT e FROM CarrefourEtatDB e WHERE e.dateDB = (select max(e2.dateDB) from CarrefourEtatDB e2 where e2.dateDB <= :dateMoment)"),
    @NamedQuery(name = "CarrefourEtat.findPeriod", query = "SELECT e FROM CarrefourEtatDB e WHERE e.dateDB between :dateA and :dateB")})
public class CarrefourEtatDB extends SimulationDB implements Serializable {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FeuEnum feuVehiculeNS;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FeuEnum feuVehiculeEO;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FeuEnum feuPietonNS;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FeuEnum feuPietonEO;

    /**
     * Constructeur par défaut de la classe CarrefourEtatDB
     */
    public CarrefourEtatDB() {
    }

    /**
     * Construit un CarrefourEtatDB qui contient les états du carrefour (Feu
     * véhicule nord-sud, Feu véhicule est-ouest, Feu piéton nord-sud, Feu
     * piéton est-ouest et la date)
     *
     * @param feuVehiculeNS la couleur du feu véhicule nord-sud
     * @param feuVehiculeEO la couleur du feu véhicule est-ouest
     * @param feuVPietonNS la couleur du feu piéton nord-sud
     * @param feuPietonEO la couleur du feu piéton est-ouest
     * @param dateEtat
     */
    public CarrefourEtatDB(FeuEnum feuVehiculeNS, FeuEnum feuVehiculeEO, FeuEnum feuVPietonNS, FeuEnum feuPietonEO, Date dateEtat) {
        this.feuVehiculeNS = feuVehiculeNS;
        this.feuVehiculeEO = feuVehiculeEO;
        this.feuPietonNS = feuVPietonNS;
        this.feuPietonEO = feuPietonEO;
        Timestamp date = new Timestamp(dateEtat.getTime());
        date.setNanos(0);
        super.setDateDB(new Date(date.getTime()));
    }

    public String getDate() {
        DateFormat df = new SimpleDateFormat("dd MMM yyyy à kk:mm:ss");
        return df.format(super.getDateDB());
    }

    public FeuEnum getFeuVehiculeNS() {
        return feuVehiculeNS;
    }

    public void setFeuVehiculeNS(FeuEnum feuVehiculeNS) {
        this.feuVehiculeNS = feuVehiculeNS;
    }

    public FeuEnum getFeuVehiculeEO() {
        return feuVehiculeEO;
    }

    public void setFeuVehiculeEO(FeuEnum feuVehiculeEO) {
        this.feuVehiculeEO = feuVehiculeEO;
    }

    public FeuEnum getFeuVPietonNS() {
        return feuPietonNS;
    }

    public void setFeuVPietonNS(FeuEnum feuVPietonNS) {
        this.feuPietonNS = feuVPietonNS;
    }

    public FeuEnum getFeuPietonEO() {
        return feuPietonEO;
    }

    public void setFeuPietonEO(FeuEnum feuPietonEO) {
        this.feuPietonEO = feuPietonEO;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.feuVehiculeNS != null ? this.feuVehiculeNS.hashCode() : 0);
        hash = 37 * hash + (this.feuVehiculeEO != null ? this.feuVehiculeEO.hashCode() : 0);
        hash = 37 * hash + (this.feuPietonNS != null ? this.feuPietonNS.hashCode() : 0);
        hash = 37 * hash + (this.feuPietonEO != null ? this.feuPietonEO.hashCode() : 0);
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
        final CarrefourEtatDB other = (CarrefourEtatDB) obj;
        if (this.feuVehiculeNS != other.feuVehiculeNS) {
            return false;
        }
        if (this.feuVehiculeEO != other.feuVehiculeEO) {
            return false;
        }
        if (this.feuPietonNS != other.feuPietonNS) {
            return false;
        }
        if (this.feuPietonEO != other.feuPietonEO) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarrefourEtatDB{" + "feuVehiculeNS=" + feuVehiculeNS + ", feuVehiculeEO=" + feuVehiculeEO + ", feuPietonNS=" + feuPietonNS + ", feuPietonEO=" + feuPietonEO + " Moment : " + getDate() + '}';
    }


}
