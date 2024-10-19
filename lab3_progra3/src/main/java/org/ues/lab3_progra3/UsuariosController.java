package org.ues.lab3_progra3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ues.lab3_progra3.Modelos.Modelo_usuarios;
import org.ues.lab3_progra3.Utilidades.utilidades;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Optional;

public class UsuariosController {

    @FXML
    private Button btn_procesar;

    @FXML
    private Label lbl_estado;

    @FXML
    private TableColumn<Modelo_usuarios, String> tbc_clave;

    @FXML
    private TableColumn<Modelo_usuarios, String> tbc_correo;

    @FXML
    private TableColumn<Modelo_usuarios, Button> tbc_eliminar;

    @FXML
    private TableColumn<Modelo_usuarios, Integer> tbc_id;

    @FXML
    private TableColumn<Modelo_usuarios, Integer> tbc_idrol;

    @FXML
    private TableColumn<Modelo_usuarios, Button> tbc_modificar;

    @FXML
    private TableColumn<Modelo_usuarios, String> tbc_nombre;

    @FXML
    private TableColumn<Modelo_usuarios, String> tbc_nombreCompleto;

    @FXML
    private TableColumn<Modelo_usuarios, String> tbc_telefono;

    @FXML
    private TableView<Modelo_usuarios> tbl_usuarios;

    @FXML
    private TextField txt_clave;

    @FXML
    private TextField txt_correo;

    @FXML
    private TextField txt_idrol;

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_nombreCompleto;

    @FXML
    private TextField txt_telefono;

    public void initialize(){

        this.tbc_id.setCellValueFactory(new PropertyValueFactory<>("idusuario"));
        this.tbc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombreusuario"));
        this.tbc_clave.setCellValueFactory(new PropertyValueFactory<>("claveusuario"));
        this.tbc_nombreCompleto.setCellValueFactory(new PropertyValueFactory<>("nombrecompleto"));
        this.tbc_correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        this.tbc_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.tbc_idrol.setCellValueFactory(new PropertyValueFactory<>("idroles"));

        Modelo_usuarios usuarios = new Modelo_usuarios();
        this.tbl_usuarios.setItems(usuarios.get_usuarios());

        // Agregar botones de acción
        dibujar_botonEliminar();
        dibujar_botonModificar();

        // Validar que txt_idrol solo permita números enteros
        utilidades.soloPermitirNumerosEnteros(txt_idrol);

        this.btn_procesar.setOnAction(event -> evento_procesar());

    }

    public void evento_procesar() {
        Modelo_usuarios usuario = new Modelo_usuarios();

        List<TextField> campos = List.of(txt_nombre, txt_clave, txt_nombreCompleto, txt_correo, txt_telefono, txt_idrol);

        if(utilidades.validarCamposNoVacios(campos)){
            if (lbl_estado.getText().equals("0")) {
                usuario.insert_usuario(txt_nombre.getText(), txt_clave.getText(), txt_nombreCompleto.getText(), txt_correo.getText(), txt_telefono.getText(), Integer.parseInt(txt_idrol.getText()));
            } else {
                usuario.update_usuario(txt_nombre.getText(), txt_clave.getText(), txt_nombreCompleto.getText(), txt_correo.getText(), txt_telefono.getText(), Integer.parseInt(txt_idrol.getText()), Integer.parseInt(lbl_estado.getText()));
            }
            uptade_table();
            limpiar_campos();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Error al procesar no deben haber campos vacios");
            alert.showAndWait();
            limpiar_campos();
        }


    }

    public void limpiar_campos(){
        txt_nombre.setText("");
        txt_clave.setText("");
        txt_nombreCompleto.setText("");
        txt_correo.setText("");
        txt_telefono.setText("");
        txt_idrol.setText("");
        lbl_estado.setText("0");
    }

    public void uptade_table() {
        this.tbl_usuarios.getItems().clear();
        Modelo_usuarios usuario = new Modelo_usuarios();
        this.tbl_usuarios.setItems(usuario.get_usuarios());
    }

    public void dibujar_botonEliminar() {
        this.tbc_eliminar.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Button item, boolean b) {
                super.updateItem(item, b);
                if (b) {
                    setGraphic(null);
                } else {
                    Button btnEliminar = new Button("Eliminar");
                    btnEliminar.setOnAction(event -> {
                        // Agregar una advertencia antes de eliminar el registro
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Eliminar Registro");
                        alert.setContentText("¿Estas seguro de eliminar el registro?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            // Aquí se elimina el registro
                            Modelo_usuarios usuarioTemporal = getTableView().getItems().get(getIndex());
                            usuarioTemporal.delete_usuario(usuarioTemporal.getIdusuario());
                            uptade_table();
                        }
                    });
                    setGraphic(btnEliminar);
                }
            }
        });
    }

    public void dibujar_botonModificar() {
        this.tbc_modificar.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Button item, boolean b) {
                super.updateItem(item, b);
                if (b) {
                    setGraphic(null);
                } else {
                    Button btnModificar = new Button("Modificar");
                    btnModificar.setOnAction(event -> {
                        Modelo_usuarios usuarioTemporal = getTableView().getItems().get(getIndex());
                        txt_nombre.setText(usuarioTemporal.getNombreusuario());
                        txt_clave.setText(usuarioTemporal.getClaveusuario());
                        txt_nombreCompleto.setText(usuarioTemporal.getNombrecompleto());
                        txt_correo.setText(usuarioTemporal.getCorreo());
                        txt_telefono.setText(usuarioTemporal.getTelefono());
                        txt_idrol.setText(String.valueOf(usuarioTemporal.getIdroles()));
                        lbl_estado.setText(String.valueOf(usuarioTemporal.getIdusuario()));
                    });
                    setGraphic(btnModificar);
                }
            }
        });
    }

}
