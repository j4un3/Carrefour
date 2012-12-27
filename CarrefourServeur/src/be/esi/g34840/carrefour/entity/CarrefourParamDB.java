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

/**
 *
 * @author J4un3
 */
@Entity
@Table(name = "CARREFOURPARAM")
@NamedQueries({
    @NamedQuery(name = "CarrefourParam.count", query = "SELECT count(p) FROM CarrefourParamDB p WHERE p.dateParam between :dateA and :dateB"),
    @NamedQuery(name = "CarrefourParam.findMoment", query = "SELECT p FROM CarrefourParamDB p WHERE p.dateParam = :dateMoment")})
public class CarrefourParamDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private int feuVertVehiculeNordSud;
    @Column(nullable = false)
    private int feuVertVehiculeEstOuest;
    @Column(nullable = false)
    private int feuVertPietonNordSud;
    @Column(nullable = false)
    private int feuVertPietonEstOuest;
    @Column(nullable = false)
    private int feuOrangeVehiculeNordSud;
    @Column(nullable = false)
    private int feuOrangeVehiculeEstOuest;
    @Column(nullable = false)
    private int feuOrangePietonNordSud;
    @Column(nullable = false)
    private int feuOrangePietonEstOuest;
    @Column(nullable = false)
    private int feuRougeVehiculeNordSud;
    @Column(nullable = false)
    private int feuRougeVehiculeEstOuest;
    @Column(nullable = false)
    private int feuRougePietonNordSud;
    @Column(nullable = false)
    private int feuRougePietonEstOuest;
    @Column(nullable = false)
    private int feuRougeCommun;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateParam;
    
    public CarrefourParamDB() {
    }
    

    public CarrefourParamDB(int feuVertVehiculeNordSud, int feuVertVehiculeEstOuest, int feuVertPietonNordSud, int feuVertPietonEstOuest, int feuOrangeVehiculeNordSud, int feuOrangeVehiculeEstOuest, int feuOrangePietonNordSud, int feuOrangePietonEstOuest, int feuRougeVehiculeNordSud, int feuRougeVehiculeEstOuest, int feuRougePietonNordSud, int feuRougePietonEstOuest, int feuRougeCommun,Date dateParam) {
        this.feuVertVehiculeNordSud = feuVertVehiculeNordSud;
        this.feuVertVehiculeEstOuest = feuVertVehiculeEstOuest;
        this.feuVertPietonNordSud = feuVertPietonNordSud;
        this.feuVertPietonEstOuest = feuVertPietonEstOuest;
        this.feuOrangeVehiculeNordSud = feuOrangeVehiculeNordSud;
        this.feuOrangeVehiculeEstOuest = feuOrangeVehiculeEstOuest;
        this.feuOrangePietonNordSud = feuOrangePietonNordSud;
        this.feuOrangePietonEstOuest = feuOrangePietonEstOuest;
        this.feuRougeVehiculeNordSud = feuRougeVehiculeNordSud;
        this.feuRougeVehiculeEstOuest = feuRougeVehiculeEstOuest;
        this.feuRougePietonNordSud = feuRougePietonNordSud;
        this.feuRougePietonEstOuest = feuRougePietonEstOuest;
        this.feuRougeCommun = feuRougeCommun;
        Timestamp date = new Timestamp(dateParam.getTime());
        date.setNanos(0);
        this.dateParam = new Date(date.getTime());
        
    }
        public String getDate() {
        DateFormat df = new SimpleDateFormat("dd MMM yyyy à kk:mm");
        return df.format(dateParam);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateParam() {
        return dateParam;
    }

    public void setDateParam(Date dateParam) {
        this.dateParam = dateParam;
    }

    public int getFeuVertVehiculeNordSud() {
        return feuVertVehiculeNordSud;
    }

    public void setFeuVertVehiculeNordSud(int feuVertVehiculeNordSud) {
        this.feuVertVehiculeNordSud = feuVertVehiculeNordSud;
    }

    public int getFeuVertVehiculeEstOuest() {
        return feuVertVehiculeEstOuest;
    }

    public void setFeuVertVehiculeEstOuest(int feuVertVehiculeEstOuest) {
        this.feuVertVehiculeEstOuest = feuVertVehiculeEstOuest;
    }

    public int getFeuVertPietonNordSud() {
        return feuVertPietonNordSud;
    }

    public void setFeuVertPietonNordSud(int feuVertPietonNordSud) {
        this.feuVertPietonNordSud = feuVertPietonNordSud;
    }

    public int getFeuVertPietonEstOuest() {
        return feuVertPietonEstOuest;
    }

    public void setFeuVertPietonEstOuest(int feuVertPietonEstOuest) {
        this.feuVertPietonEstOuest = feuVertPietonEstOuest;
    }

    public int getFeuOrangeVehiculeNordSud() {
        return feuOrangeVehiculeNordSud;
    }

    public void setFeuOrangeVehiculeNordSud(int feuOrangeVehiculeNordSud) {
        this.feuOrangeVehiculeNordSud = feuOrangeVehiculeNordSud;
    }

    public int getFeuOrangeVehiculeEstOuest() {
        return feuOrangeVehiculeEstOuest;
    }

    public void setFeuOrangeVehiculeEstOuest(int feuOrangeVehiculeEstOuest) {
        this.feuOrangeVehiculeEstOuest = feuOrangeVehiculeEstOuest;
    }

    public int getFeuOrangePietonNordSud() {
        return feuOrangePietonNordSud;
    }

    public void setFeuOrangePietonNordSud(int feuOrangePietonNordSud) {
        this.feuOrangePietonNordSud = feuOrangePietonNordSud;
    }

    public int getFeuOrangePietonEstOuest() {
        return feuOrangePietonEstOuest;
    }

    public void setFeuOrangePietonEstOuest(int feuOrangePietonEstOuest) {
        this.feuOrangePietonEstOuest = feuOrangePietonEstOuest;
    }

    public int getFeuRougeVehiculeNordSud() {
        return feuRougeVehiculeNordSud;
    }

    public void setFeuRougeVehiculeNordSud(int feuRougeVehiculeNordSud) {
        this.feuRougeVehiculeNordSud = feuRougeVehiculeNordSud;
    }

    public int getFeuRougeVehiculeEstOuest() {
        return feuRougeVehiculeEstOuest;
    }

    public void setFeuRougeVehiculeEstOuest(int feuRougeVehiculeEstOuest) {
        this.feuRougeVehiculeEstOuest = feuRougeVehiculeEstOuest;
    }

    public int getFeuRougePietonNordSud() {
        return feuRougePietonNordSud;
    }

    public void setFeuRougePietonNordSud(int feuRougePietonNordSud) {
        this.feuRougePietonNordSud = feuRougePietonNordSud;
    }

    public int getFeuRougePietonEstOuest() {
        return feuRougePietonEstOuest;
    }

    public void setFeuRougePietonEstOuest(int feuRougePietonEstOuest) {
        this.feuRougePietonEstOuest = feuRougePietonEstOuest;
    }

    public int getFeuRougeCommun() {
        return feuRougeCommun;
    }

    public void setFeuRougeCommun(int feuRougeCommun) {
        this.feuRougeCommun = feuRougeCommun;
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
        if (!(object instanceof CarrefourParamDB)) {
            return false;
        }
        CarrefourParamDB other = (CarrefourParamDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.esi.g34840.carrefour.entity.CarrefourParamDB[ id=" + id + " ]";
    }
}
