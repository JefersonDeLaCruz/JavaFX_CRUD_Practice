package org.ues.lab3_progra3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ues.lab3_progra3.Modelos.Modelo_proveedores;
import org.ues.lab3_progra3.Modelos.Modelo_roles;
import org.ues.lab3_progra3.Utilidades.utilidades;

import java.util.List;
import java.util.Optional;

public class RolesController {
    @FXML
    private Button btn_procesar;

    @FXML
    private Label lbl_estado;

    @FXML
    private TableColumn<Modelo_roles, Button> tbc_eliminar;

    @FXML
    private TableColumn<Modelo_roles, Integer> tbc_idrol;

    @FXML
    private TableColumn<Modelo_roles, Button> tbc_modificar;

    @FXML
    private TableColumn<Modelo_roles, String> tbc_nombrerol;

    @FXML
    private TableView<Modelo_roles> tbl_roles;

    @FXML
    private TextField txt_nombrerol;

    public void initialize(){


        this.tbc_idrol.setCellValueFactory(new PropertyValueFactory<>("idroles"));
        this.tbc_nombrerol.setCellValueFactory(new PropertyValueFactory<>("nombrerol"));

        Modelo_roles roles = new Modelo_roles();
        this.tbl_roles.setItems(roles.get_roles());

        //agregar boton eliminar
        dibujar_botonEliminar();
        //agregar boton modificar
        dibujar_botonModificar();

        this.btn_procesar.setOnAction(event -> evento_procesar());
    }

    public void evento_procesar(){
        Modelo_roles roles = new Modelo_roles();

        List<TextField> campos = List.of(txt_nombrerol);

        if(utilidades.validarCamposNoVacios(campos)){
            if (lbl_estado.getText().equals("0")){
                roles.insert_rol(txt_nombrerol.getText());
            }else{
                roles.update_rol(txt_nombrerol.getText(), Integer.parseInt(lbl_estado.getText()));
            }
            uptade_table();

            limpiar_campos();
        }else{

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No se permiten campos vacios");
            alert.showAndWait();
            limpiar_campos();
        }



    }

    public void limpiar_campos(){
        txt_nombrerol.setText("");
        lbl_estado.setText("0");
    }

    public void uptade_table(){
        Modelo_roles roles = new Modelo_roles();
        this.tbl_roles.getItems().clear();
        this.tbl_roles.setItems(roles.get_roles());

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
                            Modelo_roles categoriaTemporal = getTableView().getItems().get(getIndex());
                            categoriaTemporal.delete_rol(categoriaTemporal.getIdroles());
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
                        Modelo_roles rolesTemporal = getTableView().getItems().get(getIndex());
                        txt_nombrerol.setText(rolesTemporal.getNombrerol());
                        lbl_estado.setText(String.valueOf(rolesTemporal.getIdroles()));
                    });
                    setGraphic(btnModificar);
                }
            }
        } );
    }

}
