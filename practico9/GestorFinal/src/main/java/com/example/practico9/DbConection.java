package com.example.practico9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConection {
    ///  Previamente se debe crear base de datos en una isntacia mysql
    // url de conexion
    private static final String URL = "jdbc:mysql://<rutaHost>:<host>/<NombreDB>";
    // usuario
    private static final String USER = "<User>";
    //contraseña
    private static final String PASSWORD = "<Password>";

    // Método para obtener conexión
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new SQLException("Driver JDBC no encontrado", e);
        }
    }
}