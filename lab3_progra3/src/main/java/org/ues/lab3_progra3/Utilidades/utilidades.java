package org.ues.lab3_progra3.Utilidades;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.ues.lab3_progra3.Modelos.Modelo_categorias;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class utilidades {

    public static boolean validarCamposNoVacios(List<TextField> campos) {
        for (TextField campo : campos) {
            if (campo.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void soloPermitirNumerosEnteros(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
