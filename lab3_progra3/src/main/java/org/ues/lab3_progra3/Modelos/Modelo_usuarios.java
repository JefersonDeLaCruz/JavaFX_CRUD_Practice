package org.ues.lab3_progra3.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.ues.lab3_progra3.ConexionDB.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo_usuarios {

    private int idusuario;
    private String nombreusuario;
    private String claveusuario;
    private String nombrecompleto;
    private String correo;
    private String telefono;
    private int idroles;

    public void setIdroles(int idroles) {
        this.idroles = idroles;
    }

    public int getIdroles() {
        return idroles;
    }

    public Modelo_usuarios() {
    }

    public ObservableList<Modelo_usuarios> get_usuarios(){

        Connection connection = Conexion.connection();
        ObservableList<Modelo_usuarios> lista_usuarios = FXCollections.observableArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_usuarios");
            while(resultSet.next()){
                Modelo_usuarios usuario = new Modelo_usuarios();
                usuario.setIdusuario(resultSet.getInt("idusuario"));
                usuario.setNombreusuario(resultSet.getString("nombreusuario"));
                usuario.setClaveusuario(resultSet.getString("claveusuario"));
                usuario.setNombrecompleto(resultSet.getString("nombrecompleto"));
                usuario.setCorreo(resultSet.getString("correo"));
                usuario.setTelefono(resultSet.getString("telefono"));
                usuario.setIdroles(resultSet.getInt("idroles"));

                lista_usuarios.add(usuario);
            }

            return lista_usuarios;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insert_usuario(String nombreusuario, String claveusuario, String nombrecompleto, String correo, String telefono, int idrol){
        Connection connection = Conexion.connection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("INSERT INTO tbl_usuarios(nombreusuario, claveusuario, nombrecompleto, correo, telefono, idroles) VALUES ('" + nombreusuario + "', '" + claveusuario + "', '" + nombrecompleto + "', '" + correo + "', '" + telefono + "', " + idrol + ")");
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el usuario " + e);
        }
    }

    public int update_usuario(String nombreusuario, String claveusuario, String nombrecompleto, String correo, String telefono, int idrol, int idusuario){
        Connection connection = Conexion.connection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("UPDATE tbl_usuarios SET nombreusuario = '" + nombreusuario + "', claveusuario = '" + claveusuario + "', nombrecompleto = '" + nombrecompleto + "', correo = '" + correo + "', telefono = '" + telefono + "', idroles = " + idrol + " WHERE idusuario = " + idusuario);
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el usuario " + e);
        }
    }

    public int delete_usuario(int idusuario){
        Connection connection = Conexion.connection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("DELETE FROM tbl_usuarios WHERE idusuario = " + idusuario);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el usuario " + e);
        }
    }


    public int getIdusuario() {
        return idusuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public String getClaveusuario() {
        return claveusuario;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }




    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public void setClaveusuario(String claveusuario) {
        this.claveusuario = claveusuario;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


}
