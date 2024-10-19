package org.ues.lab3_progra3.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.ues.lab3_progra3.ConexionDB.Conexion;

import java.sql.*;

public class Modelo_clientes {

    private int idcliente;
    private String nombreclientes;
    private String dui;
    private String nit;
    private String direccion;
    private String telefono;

    public Modelo_clientes(){


    }
    public ObservableList<Modelo_clientes> get_clientes(){
        Connection connection = Conexion.connection();

        //inicializmos el observable list
        ObservableList<Modelo_clientes> lista_clientes = FXCollections.observableArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_clientes");
            while(resultSet.next()){
                Modelo_clientes cliente = new Modelo_clientes();
                cliente.setIdcliente(resultSet.getInt("idcliente"));
                cliente.setNombreclientes(resultSet.getString("nombreclientes"));
                cliente.setDui(resultSet.getString("dui"));
                cliente.setNit(resultSet.getString("nit"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setTelefono(resultSet.getString("telefono"));

                lista_clientes.add(cliente);

            }
            return lista_clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insert_cliente(String nombreclientes, String dui, String nit, String direccion, String telefono){
        Connection connection = Conexion.connection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("INSERT INTO tbl_clientes(nombreclientes, dui, nit, direccion, telefono) VALUES ('" + nombreclientes + "', '" + dui + "', '" + nit + "', '" + direccion + "', '" + telefono + "')");
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el cliente " + e);
        }
    }

    public int update_cliente(String nombreclientes, String dui, String nit, String direccion, String telefono, int idcliente){
        Connection connection = Conexion.connection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("UPDATE tbl_clientes SET nombreclientes = '" + nombreclientes + "', dui = '" + dui + "', nit = '" + nit + "', direccion = '" + direccion + "', telefono = '" + telefono + "' WHERE idcliente = " + idcliente);
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el cliente " + e);
        }
    }

    public int delete_cliente(int idcliente){
        Connection connection = Conexion.connection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("DELETE FROM tbl_clientes WHERE idcliente = " + idcliente);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el cliente " + e);
        }
    }









    //metodos get y set


    public int getIdcliente() {
        return idcliente;
    }

    public String getNombreclientes() {
        return nombreclientes;
    }

    public String getDui() {
        return dui;
    }

    public String getNit() {
        return nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
    ////


    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public void setNombreclientes(String nombreclientes) {
        this.nombreclientes = nombreclientes;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
