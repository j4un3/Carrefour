/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.exception;

/**
 *
 * @author J4un3
 */
public class PersistenceException extends Exception{
    public PersistenceException(){
        super();
    }
    public PersistenceException(String msg){
        super(msg);
    }
}
