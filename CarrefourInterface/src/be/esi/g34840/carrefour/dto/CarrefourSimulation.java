/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author J4un3
 */
public abstract class CarrefourSimulation implements Serializable {

    private Date date;

    public CarrefourSimulation(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getDateFormat() {
        DateFormat df = new SimpleDateFormat("dd MMM yyyy à kk:mm:ss");
        return df.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
