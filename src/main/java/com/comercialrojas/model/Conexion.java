package com.comercialrojas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Ruta de conexión hacia su base de datos local
    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ComercialRojasDB;encrypt=true;trustServerCertificate=true;";
    private static final String USUARIO = "sa";
    private static final String PASSWORD = "alianza1901";

    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexion exitosa a la base de datos ComercialRojasDB.");
            return conexion;
        } catch (SQLException e) {
            System.err.println("Error de conexion: " + e.getMessage());
            return null;
        }
    }

    // Método ejecutable solo para realizar la prueba
    public static void main(String[] args) {
        conectar();
    }
}