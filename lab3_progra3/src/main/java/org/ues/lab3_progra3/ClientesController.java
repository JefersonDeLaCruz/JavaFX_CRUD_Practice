package org.ues.lab3_progra3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ues.lab3_progra3.Modelos.Modelo_categorias;
import org.ues.lab3_progra3.Modelos.Modelo_clientes;
import org.ues.lab3_progra3.Utilidades.utilidades;

import java.util.List;
import java.util.Optional;

public class ClientesController {

    @FXML
    private Button btn_procesar;

    @FXML
    private Label lbl_estado;

    @FXML
    private TableColumn<Modelo_clientes, Integer> tbc_id;

    @FXML
    private TableColumn<Modelo_clientes, Button> tbc_modificar;

    @FXML
    private TableColumn<Modelo_clientes, String> tbc_nit;

    @FXML
    private TableColumn<Modelo_clientes, String> tbc_nombre;

    @FXML
    private TableColumn<Modelo_clientes, String> tbc_telefono;

    @FXML
    private TableColumn<Modelo_clientes, String> tcb_direccion;

    @FXML
    private TableColumn<Modelo_clientes, String> tcb_dui;

    @FXML
    private TableColumn<Modelo_clientes, Button> tcb_eliminar;

    @FXML
    private TextField txt_direccion;

    @FXML
    private TextField txt_dui;

    @FXML
    private TextField txt_nit;

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_telefono;
    @FXML
    private TableView<Modelo_clientes> tbl_clientes;

    public void initialize(){

        this.tbc_id.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        this.tbc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombreclientes"));
        this.tcb_dui.setCellValueFactory(new PropertyValueFactory<>("dui"));
        this.tbc_nit.setCellValueFactory(new PropertyValueFactory<>("nit"));
        this.tcb_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        this.tbc_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        Modelo_clientes cliente = new Modelo_clientes();

        this.tbl_clientes.setItems(cliente.get_clientes());

        //agregar boton eliminar
        dibujar_botonEliminar();
        //agregar boton modificar
        dibujar_botonModificar();

        this.btn_procesar.setOnAction(event -> evento_procesar());
    }

    public void evento_procesar(){
        Modelo_clientes cliente = new Modelo_clientes();

        List<TextField> campos = List.of(txt_nombre, txt_dui, txt_nit, txt_direccion, txt_telefono);
        if (utilidades.validarCamposNoVacios(campos)){
            if (lbl_estado.getText().equals("0")){
                cliente.insert_cliente(txt_nombre.getText(), txt_dui.getText(), txt_nit.getText(), txt_direccion.getText(), txt_telefono.getText());
            }else{
                cliente.update_cliente(txt_nombre.getText(), txt_dui.getText(), txt_nit.getText(), txt_direccion.getText(), txt_telefono.getText(), Integer.parseInt(lbl_estado.getText()));
            }
            uptade_table();
            limpiar_campos();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Todos los campos son obligatorios");
            alert.showAndWait();
            limpiar_campos();
        }

    }

    public void limpiar_campos(){
        txt_nombre.setText("");
        txt_dui.setText("");
        txt_nit.setText("");
        txt_direccion.setText("");
        txt_telefono.setText("");
        lbl_estado.setText("0");
    }

    public void uptade_table(){
        this.tbl_clientes.getItems().clear();
        Modelo_clientes cliente = new Modelo_clientes();
        this.tbl_clientes.setItems(cliente.get_clientes());
    }

    public void dibujar_botonEliminar(){
        this.tcb_eliminar.setCellFactory(tc -> new TableCell<>(){

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
                            Modelo_clientes categoriaTemporal = getTableView().getItems().get(getIndex());
                            categoriaTemporal.delete_cliente(categoriaTemporal.getIdcliente());
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
                        Modelo_clientes clienteTemporal = getTableView().getItems().get(getIndex());
                        txt_nombre.setText(clienteTemporal.getNombreclientes());
                        txt_dui.setText(clienteTemporal.getDui());
                        txt_nit.setText(clienteTemporal.getNit());
                        txt_direccion.setText(clienteTemporal.getDireccion());
                        txt_telefono.setText(clienteTemporal.getTelefono());
                        lbl_estado.setText(String.valueOf(clienteTemporal.getIdcliente()));
                    });
                    setGraphic(btnModificar);
                }
            }
        } );
    }




}
