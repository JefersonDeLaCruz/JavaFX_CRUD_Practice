package org.ues.lab3_progra3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ues.lab3_progra3.Modelos.Modelo_proveedores;
import org.ues.lab3_progra3.Utilidades.utilidades;

import java.util.List;
import java.util.Optional;

public class ProveedoresController {

    @FXML
    private Button btn_procesar;

    @FXML
    private Label lbl_estado;

    @FXML
    private TableColumn<Modelo_proveedores, String> tbc_correo;

    @FXML
    private TableColumn<Modelo_proveedores, Button> tbc_eliminar;

    @FXML
    private TableColumn<Modelo_proveedores, Integer> tbc_idproveedor;

    @FXML
    private TableColumn<Modelo_proveedores, Button> tbc_modificar;

    @FXML
    private TableColumn<Modelo_proveedores, String> tbc_nombre;

    @FXML
    private TableColumn<Modelo_proveedores, String> tbc_telefono;

    @FXML
    private TableView<Modelo_proveedores> tbl_proveedores;

    @FXML
    private TextField txt_correo;

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_telefono;

    public void initialize(){


        this.tbc_idproveedor.setCellValueFactory(new PropertyValueFactory<>("idproveedor"));
        this.tbc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombreproveedor"));
        this.tbc_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.tbc_correo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        Modelo_proveedores proveedor = new Modelo_proveedores();
        this.tbl_proveedores.setItems(proveedor.get_proveedores());

        //agregar boton eliminar
        dibujar_botonEliminar();
        //agregar boton modificar
        dibujar_botonModificar();

        this.btn_procesar.setOnAction(event -> evento_procesar());
    }

    public void evento_procesar(){
        Modelo_proveedores proveedor = new Modelo_proveedores();

        List<TextField> campos = List.of(txt_nombre, txt_telefono, txt_correo);

        if(utilidades.validarCamposNoVacios(campos)){
            if (lbl_estado.getText().equals("0")){
                proveedor.insert_proveedor(txt_nombre.getText(), txt_telefono.getText(), txt_correo.getText());
            }else{
                proveedor.update_proveedor(txt_nombre.getText(), txt_telefono.getText(), txt_correo.getText(), Integer.parseInt(lbl_estado.getText()));
            }
            uptade_table();
            limpiar_campos();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Todos los campos son obligatorios");
            alert.showAndWait();
            limpiar_campos();
        }


    }

    public void limpiar_campos(){
        txt_nombre.setText("");
        txt_telefono.setText("");
        txt_correo.setText("");
        lbl_estado.setText("0");
    }

    public void uptade_table(){
        this.tbl_proveedores.getItems().clear();
        Modelo_proveedores proveedor = new Modelo_proveedores();
        this.tbl_proveedores.setItems(proveedor.get_proveedores());
    }

    public void dibujar_botonModificar(){
        this.tbc_modificar.setCellFactory(tc -> new TableCell<>(){

            @Override
            protected void updateItem(Button item, boolean b){
                super.updateItem(item, b);
                if (b){
                    setGraphic(null);
                }else{
                    Button btnModificar = new Button("Modificar");
                    btnModificar.setOnAction(event -> {
                        Modelo_proveedores proveedorTemporal = getTableView().getItems().get(getIndex());
                        txt_nombre.setText(proveedorTemporal.getNombreproveedor());
                        txt_telefono.setText(proveedorTemporal.getTelefono());
                        txt_correo.setText(proveedorTemporal.getCorreo());
                        lbl_estado.setText(String.valueOf(proveedorTemporal.getIdproveedor()));
                    });
                    setGraphic(btnModificar);
                }
            }
        } );
    }

    public void dibujar_botonEliminar(){
        this.tbc_eliminar.setCellFactory(tc -> new TableCell<>(){

            @Override
            protected void updateItem(Button item, boolean b){
                super.updateItem(item, b);
                if (b){
                    setGraphic(null);
                }else{
                    Button btnEliminar = new Button("Eliminar");
                    btnEliminar.setOnAction(event -> {

                        //agregar una advertencia antes de eliminar el registro
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Eliminar Registro");
                        alert.setContentText("¿Estas seguro de eliminar el registro?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            //aqui se elimina el registro
                            Modelo_proveedores categoriaTemporal = getTableView().getItems().get(getIndex());
                            categoriaTemporal.delete_proveedor(categoriaTemporal.getIdproveedor());
                            uptade_table();
                        }
                    });
                    setGraphic(btnEliminar);
                }
            }
        } );
    }


}
