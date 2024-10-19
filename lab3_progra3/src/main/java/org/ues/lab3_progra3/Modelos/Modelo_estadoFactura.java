package org.ues.lab3_progra3.Modelos;

import javafx.collections.ObservableList;
import org.ues.lab3_progra3.ConexionDB.Conexion;

import java.sql.Connection;

public class Modelo_estadoFactura {

    private int idestadofactura;
    private String descripcionestado;

    public Modelo_estadoFactura() {


    }

    public ObservableList<Modelo_estadoFactura> get_estadoFacturas(){

        Connection connection = Conexion.connection();
        ObservableList<Modelo_estadoFactura> lista_estadoFacturas = javafx.collections.FXCollections.observableArrayList();

        try {
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_estado_factura");
            while(resultSet.next()){
                Modelo_estadoFactura estadoFactura = new Modelo_estadoFactura();
                estadoFactura.setIdestadofactura(resultSet.getInt("idestadofactura"));
                estadoFactura.setDescripcionestado(resultSet.getString("descripcionestado"));

                lista_estadoFacturas.add(estadoFactura);
            }
            return lista_estadoFacturas;
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insert_estadoFactura(String descripcionestado){
        Connection connection = Conexion.connection();
        try {
            java.sql.Statement statement = connection.createStatement();
            return statement.executeUpdate("INSERT INTO tbl_estado_factura(descripcionestado) VALUES ('" + descripcionestado + "')");
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al insertar el estado de factura " + e);
        }
    }

    public int update_estadoFactura(String descripcionestado, int idestadofactura){
        Connection connection = Conexion.connection();
        try {
            java.sql.Statement statement = connection.createStatement();
            return statement.executeUpdate("UPDATE tbl_estado_factura SET descripcionestado = '" + descripcionestado + "' WHERE idestadofactura = " + idestadofactura);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al actualizar el estado de factura " + e);
        }
    }

    public int delete_estadoFactura(int idestadofactura){
        Connection connection = Conexion.connection();
        try {
            java.sql.Statement statement = connection.createStatement();
            return statement.executeUpdate("DELETE FROM tbl_estado_factura WHERE idestadofactura = " + idestadofactura);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al eliminar el estado de factura " + e);
        }
    }




    //getters y setters

    public int getIdestadofactura() {
        return idestadofactura;
    }

    public String getDescripcionestado() {
        return descripcionestado;
    }

    public void setIdestadofactura(int idestadofactura) {
        this.idestadofactura = idestadofactura;
    }

    public void setDescripcionestado(String descripcionestado) {
        this.descripcionestado = descripcionestado;
    }
}

