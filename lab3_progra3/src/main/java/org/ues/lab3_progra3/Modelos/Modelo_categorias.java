package org.ues.lab3_progra3.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.ues.lab3_progra3.ConexionDB.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo_categorias {

    private int id_categoria;
    private String nombre_categoria;

    public Modelo_categorias() {


    }

    public ObservableList<Modelo_categorias> get_categorias() {
        //inicio de la conexion
        Connection connection = Conexion.connection();

        ObservableList<Modelo_categorias> lista_categorias = FXCollections.observableArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_categorias");
            while (resultSet.next()) {
                Modelo_categorias categoria = new Modelo_categorias();
                categoria.setId_categoria(resultSet.getInt("idcategoria"));
                categoria.setNombre_categoria(resultSet.getString("nombrecategoria"));


                lista_categorias.add(categoria);

            }
            return lista_categorias;
        } catch (SQLException e) {
            throw new RuntimeException("Error " + e);
        }

    }

    public int insert_categoria(String nombre_categoria) {
        Connection connection = Conexion.connection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("INSERT INTO tbl_categorias(nombrecategoria) VALUES ('" + nombre_categoria + "')");
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la categoria " + e);
        }
    }

    public int update_categoria(String nombre_categoria, int id_categoria) {
        Connection connection = Conexion.connection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("UPDATE tbl_categorias SET nombrecategoria = '" + nombre_categoria + "' WHERE idcategoria = " + id_categoria);
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la categoria " + e);
        }
    }

    public int delete_categoria(int id_categoria) {
        Connection connection = Conexion.connection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("DELETE FROM tbl_categorias WHERE idcategoria = " + id_categoria);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la categoria " + e);
        }
    }

    public ObservableList<Modelo_categorias> update_table(){
        return get_categorias();
    }


    //metodos getter y setters


    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }
}
