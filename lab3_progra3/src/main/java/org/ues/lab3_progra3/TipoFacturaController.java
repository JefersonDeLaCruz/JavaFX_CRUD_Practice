package org.ues.lab3_progra3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ues.lab3_progra3.Modelos.Modelo_roles;
import org.ues.lab3_progra3.Modelos.Modelo_tipoFactura;
import org.ues.lab3_progra3.Utilidades.utilidades;

import java.util.List;
import java.util.Optional;

public class TipoFacturaController {

    @FXML
    private Button btn_procesar;

    @FXML
    private Label lbl_estado;

    @FXML
    private TableColumn<Modelo_tipoFactura, Button> tbc_eliminar;

    @FXML
    private TableColumn<Modelo_tipoFactura, Integer> tbc_id;

    @FXML
    private TableColumn<Modelo_tipoFactura, Button> tbc_modificar;

    @FXML
    private TableColumn<Modelo_tipoFactura, String> tbc_nombre;

    @FXML
    private TableView<Modelo_tipoFactura> tbl_tipoFactura;

    @FXML
    private TextField txt_nombre;

    public void initialize(){


        this.tbc_id.setCellValueFactory(new PropertyValueFactory<>("idtipofactura"));
        this.tbc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombretipofactura"));
        Modelo_tipoFactura tipoFactura = new Modelo_tipoFactura();


        this.tbl_tipoFactura.setItems(tipoFactura.get_tipoFactura());
        //agregar boton eliminar
        dibujar_botonEliminar();
        //agregar boton modificar
        dibujar_botonModificar();
        this.btn_procesar.setOnAction(event -> evento_procesar());
    }

    public void evento_procesar(){
        Modelo_tipoFactura tipoFactura = new Modelo_tipoFactura();

        List<TextField> campos = List.of(txt_nombre);
        if(utilidades.validarCamposNoVacios(campos)){
            if (lbl_estado.getText().equals("0")){
                tipoFactura.insert_tipoFactura(txt_nombre.getText());
            }else{
                tipoFactura.update_tipoFactura(txt_nombre.getText(), Integer.parseInt(lbl_estado.getText()));
            }
            update_table();
            limpiar_campos();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No se permiten campos vacios");
            alert.show();
            limpiar_campos();
        }



    }

    public void limpiar_campos(){
        txt_nombre.setText("");
        lbl_estado.setText("0");
    }


    public void update_table(){
        Modelo_tipoFactura tipoFactura = new Modelo_tipoFactura();
        this.tbl_tipoFactura.getItems().clear();
        this.tbl_tipoFactura.setItems(tipoFactura.get_tipoFactura());
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
                            Modelo_tipoFactura categoriaTemporal = getTableView().getItems().get(getIndex());
                            categoriaTemporal.delete_tipoFactura(categoriaTemporal.getIdtipofactura());
                            update_table();
                        }
                    });
                    setGraphic(btnEliminar);
                }
            }
        } );

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
                        Modelo_tipoFactura rolesTemporal = getTableView().getItems().get(getIndex());
                        txt_nombre.setText(rolesTemporal.getNombretipofactura());
                        lbl_estado.setText(String.valueOf(rolesTemporal.getIdtipofactura()));
                    });
                    setGraphic(btnModificar);
                }
            }
        } );
    }


}
