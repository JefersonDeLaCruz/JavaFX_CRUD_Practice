package org.ues.lab3_progra3.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.ues.lab3_progra3.ConexionDB.Conexion;

import java.sql.Connection;

public class Modelo_roles {

    private int idroles;
    private String nombrerol;

    public Modelo_roles() {
    }


    public ObservableList<Modelo_roles> get_roles(){
        Connection connection = Conexion.connection();
        ObservableList<Modelo_roles> lista_roles = FXCollections.observableArrayList();

        try {
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_roles");
            while(resultSet.next()){
                Modelo_roles rol = new Modelo_roles();
                rol.setIdroles(resultSet.getInt("idroles"));
                rol.setNombrerol(resultSet.getString("nombrerol"));

                lista_roles.add(rol);
            }
            return lista_roles;
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int insert_rol(String nombrerol){
        Connection connection = Conexion.connection();
        try {
            java.sql.Statement statement = connection.createStatement();
            return statement.executeUpdate("INSERT INTO tbl_roles(nombrerol) VALUES ('" + nombrerol + "')");
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al insertar el rol " + e);
        }
    }

    public int update_rol(String nombrerol, int idroles){
        Connection connection = Conexion.connection();
        try {
            java.sql.Statement statement = connection.createStatement();
            return statement.executeUpdate("UPDATE tbl_roles SET nombrerol = '" + nombrerol + "' WHERE idroles = " + idroles);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al actualizar el rol " + e);
        }
    }

    public int delete_rol(int idroles){
        Connection connection = Conexion.connection();
        try {
            java.sql.Statement statement = connection.createStatement();
            return statement.executeUpdate("DELETE FROM tbl_roles WHERE idroles = " + idroles);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al eliminar el rol " + e);
        }
    }



    //get y set


    public int getIdroles() {
        return idroles;
    }

    public String getNombrerol() {
        return nombrerol;
    }

    public void setIdroles(int idroles) {
        this.idroles = idroles;
    }

    public void setNombrerol(String nombrerol) {
        this.nombrerol = nombrerol;
    }


}
