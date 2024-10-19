package org.ues.lab3_progra3;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.ues.lab3_progra3.Modelos.Modelo_categorias;
import org.ues.lab3_progra3.Utilidades.utilidades;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainController {
    //declaracion de variables


    @FXML
    private Label lbl_estado;

    @FXML
    private Label lbl_mensaje;

    @FXML
    private MenuItem menuItem_catgorias;

    @FXML
    private MenuItem menuItem_clientes;

    @FXML
    private MenuItem menuItem_estadoFactura;

    @FXML
    private MenuItem menuItem_proveedores;

    @FXML
    private MenuItem menuItem_roles;

    @FXML
    private MenuItem menuItem_tipoFactura;

    @FXML
    private MenuItem menuItem_users;

    @FXML
    private StackPane stackpane_centro;

    @FXML
    private MenuItem menuitem_close;


    public void initialize(){

        menuItem_catgorias.setOnAction(e -> cargarTabla("categorias"));
        menuItem_clientes.setOnAction(e -> cargarTabla("clientes"));
        menuItem_estadoFactura.setOnAction(e -> cargarTabla("estadoFactura"));
        menuItem_proveedores.setOnAction(e -> cargarTabla("proveedores"));
        menuItem_roles.setOnAction(e -> cargarTabla("roles"));
        menuItem_tipoFactura.setOnAction(e -> cargarTabla("tipoFactura"));
        menuItem_users.setOnAction(e -> cargarTabla("users"));

        menuitem_close.setOnAction(e -> Platform.exit());

    }

    private void cargarTabla(String tabla) {
        try {
            // Limpiar el contenido del StackPane
            stackpane_centro.getChildren().clear();
            lbl_estado.setText("");

            // Cargar la vista correspondiente según la tabla seleccionada
            String fxmlFile = "";

            switch (tabla) {
                case "categorias":
                    fxmlFile = "vista_categorias.fxml";
                    lbl_estado.setText("Tabla de Categorias");
                    break;
                case "clientes":
                    fxmlFile = "vista_clientes.fxml";
                    lbl_estado.setText("Tabla de Clientes");
                    break;
                case "estadoFactura":
                    fxmlFile = "vista_estadoFactura.fxml";
                    lbl_estado.setText("Tabla de Estado de Facturas");
                    break;
                case "proveedores":
                    fxmlFile = "vista_proveedores.fxml";
                    lbl_estado.setText("Tabla de Proveedores");
                    break;
                case "roles":
                    fxmlFile = "vista_roles.fxml";
                    lbl_estado.setText("Tabla de Roles");
                    break;
                case "tipoFactura":
                    fxmlFile = "vista_tipoFactura.fxml";
                    lbl_estado.setText("Tabla de Tipo de Facturas");
                    break;
                case "users":
                    fxmlFile = "vista_users.fxml";
                    lbl_estado.setText("Tabla de Usuarios");
                    break;
                default:
                    break;
            }

            // Cargar el FXML si se ha especificado uno
            if (!fxmlFile.isEmpty()) {
                Node nuevaVista = FXMLLoader.load(getClass().getResource(fxmlFile));
                stackpane_centro.getChildren().add(nuevaVista);
                lbl_mensaje.setVisible(false); // Ocultar el Label al cargar una vista
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


