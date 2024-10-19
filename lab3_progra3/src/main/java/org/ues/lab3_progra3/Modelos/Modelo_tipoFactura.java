package org.ues.lab3_progra3.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.ues.lab3_progra3.ConexionDB.Conexion;

import java.sql.Connection;

public class Modelo_tipoFactura {

    private int idtipofactura;
    private String nombretipofactura;

    public Modelo_tipoFactura() {
    }

    public ObservableList<Modelo_tipoFactura> get_tipoFactura(){

        Connection connection = Conexion.connection();
        ObservableList<Modelo_tipoFactura> lista_tipoFactura = FXCollections.observableArrayList();

        try {
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_tipofactura");
            while(resultSet.next()){
                Modelo_tipoFactura tipoFactura = new Modelo_tipoFactura();
                tipoFactura.setIdtipofactura(resultSet.getInt("idtipofactura"));
                tipoFactura.setNombretipofactura(resultSet.getString("nombretipofactura"));

                lista_tipoFactura.add(tipoFactura);
            }
            return lista_tipoFactura;
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insert_tipoFactura(String nombretipofactura){
        Connection connection = Conexion.connection();
        try {
            java.sql.Statement statement = connection.createStatement();
            return statement.executeUpdate("INSERT INTO tbl_tipofactura(nombretipofactura) VALUES ('" + nombretipofactura + "')");
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al insertar el tipo de factura " + e);
        }
    }

    public int update_tipoFactura(String nombretipofactura, int idtipofactura){
        Connection connection = Conexion.connection();
        try {
            java.sql.Statement statement = connection.createStatement();
            return statement.executeUpdate("UPDATE tbl_tipofactura SET nombretipofactura = '" + nombretipofactura + "' WHERE idtipofactura = " + idtipofactura);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al actualizar el tipo de factura " + e);
        }
    }

    public int delete_tipoFactura(int idtipofactura){
        Connection connection = Conexion.connection();
        try {
            java.sql.Statement statement = connection.createStatement();
            return statement.executeUpdate("DELETE FROM tbl_tipofactura WHERE idtipofactura = " + idtipofactura);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al eliminar el tipo de factura " + e);
        }
    }

    //setters y getters

    public int getIdtipofactura() {
        return idtipofactura;
    }

    public String getNombretipofactura() {
        return nombretipofactura;
    }

    public void setIdtipofactura(int idtipofactura) {
        this.idtipofactura = idtipofactura;
    }

    public void setNombretipofactura(String nombretipofactura) {
        this.nombretipofactura = nombretipofactura;
    }
}
