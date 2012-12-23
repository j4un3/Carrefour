/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.g34840.carrefour.db;

import be.esi.g34840.carrefour.exception.PersistenceException;
import java.sql.Connection;

/**
 * Classe d'accès au gestionnaire de persistance pour les Séquences
 */
public class SequenceDB {

    static final String PLAN = "Plan";
    static final String EXIT = "Sortie";

    static synchronized int getNextNumber(String sequence) throws PersistenceException {
        try {
            Connection connexion = DBManager.getConnection();
            String query = "UPDATE Sequences SET valeur = valeur+1 WHERE id='" + sequence + "'";
            java.sql.PreparedStatement update = connexion.prepareStatement(query);
            update.execute();
            java.sql.Statement stmt = connexion.createStatement();
            query = "SELECT valeur FROM Sequences WHERE id='" + sequence + "'";
            java.sql.ResultSet rs = stmt.executeQuery(query);
            int nvId;
            if (rs.next()) {
                nvId = rs.getInt("valeur");
                return nvId;
            } else {
                throw new PersistenceException("Nouveau n° de séquence inaccessible!");
            }
        } catch (java.sql.SQLException eSQL) {
            throw new PersistenceException("Nouveau n° de séquence inaccessible!\n" + eSQL.getMessage());
        }
    }
}
