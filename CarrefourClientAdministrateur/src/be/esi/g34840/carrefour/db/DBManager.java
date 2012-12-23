package be.esi.g34840.carrefour.db;


import be.esi.g34840.carrefour.exception.PersistenceException;
import be.esi.gui.outils.MsgOutils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static Connection connection;

    private static void setConnection() throws PersistenceException {
        try {
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/carrefourDB", "app", "app");
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            MsgOutils.erreur("SQLException", "Problème de connexion.\n " + ex.getMessage());
        }
    }

    public static Connection getConnection() throws PersistenceException {
        if (connection == null) {
            setConnection();
        }
        return connection;
    }

    public static void startTransaction() throws PersistenceException {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            MsgOutils.erreur("PersistenceException", "Impossible de démarrer une transaction\n " + ex.getMessage());
        } catch (NullPointerException ex) {
            MsgOutils.erreur("NullPointerException", "Problème de connexion à la base de donnée.\n " + ex.getMessage());
        }
    }

    public static void valideTransaction() throws PersistenceException {
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new PersistenceException("Impossible de valider la transaction");
        }
    }

    public static void annuleTransaction() throws PersistenceException {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new PersistenceException("Impossible d'annuler la transaction");
        }
    }
}
