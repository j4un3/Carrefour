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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entité CarrefourParam
 *
 * @author J4un3
 */
@Entity
@Table(name = "CARREFOURPARAM")
@NamedQueries({
    @NamedQuery(name = "CarrefourParam.count", query = "SELECT count(p) FROM CarrefourParamDB p WHERE p.dateDB between :dateA and :dateB"),
    @NamedQuery(name = "CarrefourParam.findMoment", query = "SELECT p FROM CarrefourParamDB p WHERE p.dateDB = (select max(p2.dateDB) from CarrefourParamDB p2 where p2.dateDB <= :dateMoment)")})
public class CarrefourParamDB extends SimulationDB implements Serializable {
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


    public CarrefourParamDB() {
    }

    public CarrefourParamDB(int feuVertVehiculeNordSud, int feuVertVehiculeEstOuest, int feuVertPietonNordSud, int feuVertPietonEstOuest, int feuOrangeVehiculeNordSud, int feuOrangeVehiculeEstOuest, int feuOrangePietonNordSud, int feuOrangePietonEstOuest, int feuRougeVehiculeNordSud, int feuRougeVehiculeEstOuest, int feuRougePietonNordSud, int feuRougePietonEstOuest, int feuRougeCommun, Date dateParam) {
        super();
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
        super.setDateDB(new Date(date.getTime()));
    }

    public String getDate() {
        DateFormat df = new SimpleDateFormat("dd MMM yyyy à kk:mm:ss");
        return df.format(super.getDateDB());
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
        int hash = 7;
        hash = 73 * hash + this.feuVertVehiculeNordSud;
        hash = 73 * hash + this.feuVertVehiculeEstOuest;
        hash = 73 * hash + this.feuVertPietonNordSud;
        hash = 73 * hash + this.feuVertPietonEstOuest;
        hash = 73 * hash + this.feuOrangeVehiculeNordSud;
        hash = 73 * hash + this.feuOrangeVehiculeEstOuest;
        hash = 73 * hash + this.feuOrangePietonNordSud;
        hash = 73 * hash + this.feuOrangePietonEstOuest;
        hash = 73 * hash + this.feuRougeVehiculeNordSud;
        hash = 73 * hash + this.feuRougeVehiculeEstOuest;
        hash = 73 * hash + this.feuRougePietonNordSud;
        hash = 73 * hash + this.feuRougePietonEstOuest;
        hash = 73 * hash + this.feuRougeCommun;
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
        final CarrefourParamDB other = (CarrefourParamDB) obj;
        if (this.feuVertVehiculeNordSud != other.feuVertVehiculeNordSud) {
            return false;
        }
        if (this.feuVertVehiculeEstOuest != other.feuVertVehiculeEstOuest) {
            return false;
        }
        if (this.feuVertPietonNordSud != other.feuVertPietonNordSud) {
            return false;
        }
        if (this.feuVertPietonEstOuest != other.feuVertPietonEstOuest) {
            return false;
        }
        if (this.feuOrangeVehiculeNordSud != other.feuOrangeVehiculeNordSud) {
            return false;
        }
        if (this.feuOrangeVehiculeEstOuest != other.feuOrangeVehiculeEstOuest) {
            return false;
        }
        if (this.feuOrangePietonNordSud != other.feuOrangePietonNordSud) {
            return false;
        }
        if (this.feuOrangePietonEstOuest != other.feuOrangePietonEstOuest) {
            return false;
        }
        if (this.feuRougeVehiculeNordSud != other.feuRougeVehiculeNordSud) {
            return false;
        }
        if (this.feuRougeVehiculeEstOuest != other.feuRougeVehiculeEstOuest) {
            return false;
        }
        if (this.feuRougePietonNordSud != other.feuRougePietonNordSud) {
            return false;
        }
        if (this.feuRougePietonEstOuest != other.feuRougePietonEstOuest) {
            return false;
        }
        if (this.feuRougeCommun != other.feuRougeCommun) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarrefourParamDB{" + "feuVertVehiculeNordSud=" + feuVertVehiculeNordSud + ", feuVertVehiculeEstOuest=" + feuVertVehiculeEstOuest + ", feuVertPietonNordSud=" + feuVertPietonNordSud + ", feuVertPietonEstOuest=" + feuVertPietonEstOuest + ", feuOrangeVehiculeNordSud=" + feuOrangeVehiculeNordSud + ", feuOrangeVehiculeEstOuest=" + feuOrangeVehiculeEstOuest + ", feuOrangePietonNordSud=" + feuOrangePietonNordSud + ", feuOrangePietonEstOuest=" + feuOrangePietonEstOuest + ", feuRougeVehiculeNordSud=" + feuRougeVehiculeNordSud + ", feuRougeVehiculeEstOuest=" + feuRougeVehiculeEstOuest + ", feuRougePietonNordSud=" + feuRougePietonNordSud + ", feuRougePietonEstOuest=" + feuRougePietonEstOuest + ", feuRougeCommun=" + feuRougeCommun + " Moment : " + getDate() + '}';
    }


       
}
