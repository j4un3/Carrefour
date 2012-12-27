/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author J4un3
 */
@Entity
@Table(name = "CARREFOURETAT")
@NamedQueries({
    @NamedQuery(name = "CarrefourEtat.count", query = "SELECT count(e) FROM CarrefourEtatDB e WHERE e.dateEtat between :dateA and :dateB"),
    @NamedQuery(name = "CarrefourEtat.findMoment", query = "SELECT e FROM CarrefourEtatDB e WHERE e.dateEtat = :dateMoment")})
public class CarrefourEtatDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private int feuVehiculeNS;
    @Column(nullable = false)
    private int feuVehiculeEO;
    @Column(nullable = false)
    private int feuVPietonNS;
    @Column(nullable = false)
    private int feuPietonEO;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEtat;

    public CarrefourEtatDB() {
    }

    public CarrefourEtatDB(int feuVehiculeNS, int feuVehiculeEO, int feuVPietonNS, int feuPietonEO, Date dateEtat) {
        this.feuVehiculeNS = feuVehiculeNS;
        this.feuVehiculeEO = feuVehiculeEO;
        this.feuVPietonNS = feuVPietonNS;
        this.feuPietonEO = feuPietonEO;
        Timestamp date = new Timestamp(dateEtat.getTime());
        date.setNanos(0);
        this.dateEtat = new Date(date.getTime());
        System.out.println(dateEtat);
    }

    public String getDate() {
        DateFormat df = new SimpleDateFormat("dd MMM yyyy à kk:mm");
        return df.format(dateEtat);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFeuVehiculeNS() {
        return feuVehiculeNS;
    }

    public void setFeuVehiculeNS(int feuVehiculeNS) {
        this.feuVehiculeNS = feuVehiculeNS;
    }

    public int getFeuVehiculeEO() {
        return feuVehiculeEO;
    }

    public void setFeuVehiculeEO(int feuVehiculeEO) {
        this.feuVehiculeEO = feuVehiculeEO;
    }

    public int getFeuVPietonNS() {
        return feuVPietonNS;
    }

    public void setFeuVPietonNS(int feuVPietonNS) {
        this.feuVPietonNS = feuVPietonNS;
    }

    public int getFeuPietonEO() {
        return feuPietonEO;
    }

    public void setFeuPietonEO(int feuPietonEO) {
        this.feuPietonEO = feuPietonEO;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarrefourEtatDB)) {
            return false;
        }
        CarrefourEtatDB other = (CarrefourEtatDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.esi.g34840.carrefour.entity.CarrefourDB[ id=" + id + " ]";
    }
}
