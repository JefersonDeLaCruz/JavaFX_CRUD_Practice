package org.ues.lab3_progra3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ues.lab3_progra3.Modelos.Modelo_categorias;
import org.ues.lab3_progra3.Modelos.Modelo_clientes;
import org.ues.lab3_progra3.Modelos.Modelo_estadoFactura;
import org.ues.lab3_progra3.Utilidades.utilidades;

import java.util.List;
import java.util.Optional;

public class EstadoFacturasController {

    @FXML
    private Button btn_procesar;

    @FXML
    private Label lbl_estado;

    @FXML
    private TableColumn<Modelo_estadoFactura, String> tbc_descripcionEstadoFactura;

    @FXML
    private TableColumn<Modelo_estadoFactura, Button> tbc_eliminar;

    @FXML
    private TableColumn<Modelo_estadoFactura, Integer> tbc_idEstadoFactura;

    @FXML
    private TableColumn<Modelo_estadoFactura, Button> tbc_modificar;

    @FXML
    private TableView<Modelo_estadoFactura> tbl_estadoFacturas;

    @FXML
    private TextField txt_descripcionEstadoFactura;

    public void initialize(){


        this.tbc_idEstadoFactura.setCellValueFactory(new PropertyValueFactory<>("idestadofactura"));
        this.tbc_descripcionEstadoFactura.setCellValueFactory(new PropertyValueFactory<>("descripcionestado"));

        Modelo_estadoFactura estadoFactura = new Modelo_estadoFactura();
        this.tbl_estadoFacturas.setItems(estadoFactura.get_estadoFacturas());

        //agregar boton eliminar
        dibujar_botonEliminar();
        //agregar boton modificar
        dibujar_botonModificar();

        this.btn_procesar.setOnAction(event -> evento_procesar());
    }

    public void evento_procesar(){
        Modelo_estadoFactura estadoFactura = new Modelo_estadoFactura();

        List<TextField> campos = List.of(txt_descripcionEstadoFactura);
        if(utilidades.validarCamposNoVacios(campos)){
            if (lbl_estado.getText().equals("0")){
                estadoFactura.insert_estadoFactura(txt_descripcionEstadoFactura.getText());
            }else{
                estadoFactura.update_estadoFactura(txt_descripcionEstadoFactura.getText(), Integer.parseInt(lbl_estado.getText()));
            }
            uptade_table();
            limpiar_campos();

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al procesar");
            alert.setContentText("No se permiten campos vacios");
            alert.showAndWait();
            limpiar_campos();
        }



    }

    public void limpiar_campos(){
        txt_descripcionEstadoFactura.setText("");
        lbl_estado.setText("0");
    }

    public void uptade_table(){
        this.tbl_estadoFacturas.getItems().clear();
        Modelo_estadoFactura estadoFactura = new Modelo_estadoFactura();
        this.tbl_estadoFacturas.setItems(estadoFactura.get_estadoFacturas());
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
                            Modelo_estadoFactura categoriaTemporal = getTableView().getItems().get(getIndex());
                            categoriaTemporal.delete_estadoFactura(categoriaTemporal.getIdestadofactura());
                            uptade_table();
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
                        Modelo_estadoFactura estadoFacturaTemporal = getTableView().getItems().get(getIndex());
                        txt_descripcionEstadoFactura.setText(estadoFacturaTemporal.getDescripcionestado());
                        lbl_estado.setText(String.valueOf(estadoFacturaTemporal.getIdestadofactura()));
                    });
                    setGraphic(btnModificar);
                }
            }
        });
    }

}
