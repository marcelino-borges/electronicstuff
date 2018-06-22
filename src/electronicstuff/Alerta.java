package electronicstuff;

import javafx.event.EventDispatchChain;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;

import java.util.Optional;

/**
 * created by Marcelino on 24/10/2017 at 20:24
 */

public class Alerta {

    public Alerta() {
    }

    static Optional<ButtonType> exibirAlertaErro (String header, String content, Window owner) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.initOwner(owner);
        alerta.setTitle("ATENÇÃO!");
        alerta.setHeaderText(header);
        alerta.setContentText(content);
        return alerta.showAndWait();
    }

    static Optional<ButtonType> exibirAlertaErro (String header, String content) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("ATENÇÃO!");
        alerta.setHeaderText(header);
        alerta.setContentText(content);
        return alerta.showAndWait();
    }

    static Optional<ButtonType> exibirInfoCores (String header, String content, Window owner) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.initOwner(owner);
        alerta.setTitle("CORES DO RESISTOR!");
        alerta.setHeaderText(header);
        alerta.setContentText(content);
        return alerta.showAndWait();
    }

}
