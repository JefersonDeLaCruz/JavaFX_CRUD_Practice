package org.ues.lab3_progra3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ues.lab3_progra3.Modelos.Modelo_categorias;
import org.ues.lab3_progra3.Utilidades.utilidades;

import java.util.List;
import java.util.Optional;

public class CategoriasController {

    @FXML
    private Button btn_procesar;

    @FXML
    private Label lbl_estado;

    @FXML
    private TableColumn<Modelo_categorias, Integer> tbc_id;

    @FXML
    private TableColumn<Modelo_categorias, String> tbc_nombreCategoria;

    @FXML
    private TableView<Modelo_categorias> tbl_categorias;

    @FXML
    private TextField txt_nombreCat;

    @FXML
    private TableColumn<Modelo_categorias, Button> tbc_eliminar;

    @FXML
    private TableColumn<Modelo_categorias, Button> tbc_modificar;

    public void initialize(){

        this.tbc_id.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));
        this.tbc_nombreCategoria.setCellValueFactory(new PropertyValueFactory<>("nombre_categoria"));
        Modelo_categorias categoria = new Modelo_categorias();
        this.tbl_categorias.setItems(categoria.get_categorias());

        //agregar boton eliminar
        dibujar_botonEliminar();
        //agregar boton modificar
        dibujar_botonModificar();

        this.btn_procesar.setOnAction(event -> evento_procesar());

    }

    public void evento_procesar(){
        Modelo_categorias categoria = new Modelo_categorias();

        List<TextField> campos = List.of(txt_nombreCat);
        if(utilidades.validarCamposNoVacios(campos)){
            if (lbl_estado.getText().equals("0")){
                categoria.insert_categoria(txt_nombreCat.getText());
            }else{
                categoria.update_categoria(txt_nombreCat.getText(), Integer.parseInt(lbl_estado.getText()));
            }
            uptade_table();
            limpiar_campos();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No puedes dejar campos vacios");
            alert.showAndWait();
            limpiar_campos();
        }

    }

    public void limpiar_campos(){
        txt_nombreCat.setText("");
        lbl_estado.setText("0");
    }

    public void uptade_table(){
        this.tbl_categorias.getItems().clear();
        Modelo_categorias categoria = new Modelo_categorias();
        this.tbl_categorias.setItems(categoria.get_categorias());
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
                            Modelo_categorias categoriaTemporal = getTableView().getItems().get(getIndex());
                            categoriaTemporal.delete_categoria(categoriaTemporal.getId_categoria());
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
                        Modelo_categorias categoriaTemporal = getTableView().getItems().get(getIndex());
                        txt_nombreCat.setText(categoriaTemporal.getNombre_categoria());
                        lbl_estado.setText(String.valueOf(categoriaTemporal.getId_categoria()));
                    });
                    setGraphic(btnModificar);
                }
            }
        } );
    }

}
