package org.ues.lab3_progra3.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.ues.lab3_progra3.ConexionDB.Conexion;

import java.sql.Connection;

public class Modelo_proveedores {

    private int idproveedor;
    private String nombreproveedor;
    private String telefono;
    private String correo;

    public Modelo_proveedores(){

    }

    public ObservableList<Modelo_proveedores> get_proveedores(){

        Connection connection = Conexion.connection();
        ObservableList<Modelo_proveedores> lista_proveedores = FXCollections.observableArrayList();

        try {
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_proveedores");
            while(resultSet.next()){
                Modelo_proveedores proveedor = new Modelo_proveedores();
                proveedor.setIdproveedor(resultSet.getInt("idproveedor"));
                proveedor.setNombreproveedor(resultSet.getString("nombreproveedor"));
                proveedor.setTelefono(resultSet.getString("telefono"));
                proveedor.setCorreo(resultSet.getString("correo"));

                lista_proveedores.add(proveedor);
            }
            return lista_proveedores;
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public int insert_proveedor(String nombreproveedor, String telefono, String correo){
        Connection connection = Conexion.connection();
        try {
            java.sql.Statement statement = connection.createStatement();
            return statement.executeUpdate("INSERT INTO tbl_proveedores(nombreproveedor, telefono, correo) VALUES ('" + nombreproveedor + "', '" + telefono + "', '" + correo + "')");
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al insertar el proveedor " + e);
        }
    }

    public int update_proveedor(String nombreproveedor, String telefono, String correo, int idproveedor){
        Connection connection = Conexion.connection();
        try {
            java.sql.Statement statement = connection.createStatement();
            return statement.executeUpdate("UPDATE tbl_proveedores SET nombreproveedor = '" + nombreproveedor + "', telefono = '" + telefono + "', correo = '" + correo + "' WHERE idproveedor = " + idproveedor);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al actualizar el proveedor " + e);
        }
    }

    public int delete_proveedor(int idproveedor){
        Connection connection = Conexion.connection();
        try {
            java.sql.Statement statement = connection.createStatement();
            return statement.executeUpdate("DELETE FROM tbl_proveedores WHERE idproveedor = " + idproveedor);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al eliminar el proveedor " + e);
        }
    }





    //geteter y setter


    public int getIdproveedor() {
        return idproveedor;
    }

    public String getNombreproveedor() {
        return nombreproveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public void setNombreproveedor(String nombreproevedor) {
        this.nombreproveedor = nombreproevedor;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
