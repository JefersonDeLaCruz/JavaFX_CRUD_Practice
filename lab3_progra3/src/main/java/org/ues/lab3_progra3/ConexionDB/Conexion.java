package org.ues.lab3_progra3.ConexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String url="jdbc:postgresql://localhost:5432/dbpuntoventa";
    private static final String USER ="postgres";
    private static final String PASS = "140103";



    public static Connection connection(){
        try {
            Connection conectar = DriverManager.getConnection(url, USER, PASS);
            System.out.println("Conectado a la base");
            return conectar;
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base " + e);
        }
    }

}
