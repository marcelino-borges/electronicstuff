package electronicstuff;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * created by Marcelino on 19/10/2017 at 22:50
 */

public class DialogController {
    @FXML
    private TextField potField, qtdField, alterarQtdField, tipoPotField;
    @FXML
    private ComboBox<String> materialBox;
    @FXML
    private Label tipoPotLabel, labelPotencia, descPot, descTipoPot;
    @FXML
    private Label labelGrafico;
    @FXML
    private ImageView grafico;

    @FXML
    private DialogPane dialogArmazenar;

    private ObservableList<String> tiposResistores = FXCollections.observableArrayList("Filme Metálico", "Óxido Metálico", "Filme de Carbono",
                                        "Carbono", "Fio", "SMD", "Trimpot", "Potenciômetro");
    private ObservableList<String> potenciasDisponiveis = FXCollections.observableArrayList("0","1/8","1/4","1/2","1","2","3","4","5","10");
    private ObservableList<String> tiposPotenciometros = FXCollections.observableArrayList("A","LOG","LOGARITIMO","LOGARÍTIMO",
                                                                                              "B","LIN","LINEAR",
                                                                                              "C","REVLOG", "REV-LOG", "REVERSE LOG");

    public void initialize() {
        materialBox.setItems(tiposResistores);
        materialBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equalsIgnoreCase("Potenciômetro")) {
                    //Campos tipo de potenciômetro
                    tipoPotLabel.setTextFill(Color.web("#950005"));
                    disableFieldsTipoPot(false);
                    descTipoPot.setTextFill(Color.BLACK);
                    descTipoPot.setDisable(false);

                    // Gráfico
                    labelGrafico.setOpacity(1);
                    grafico.setOpacity(1);

                    //Campos Potência
                    potField.setDisable(true);
                    labelPotencia.setOpacity(0.1);
                    labelPotencia.setTextFill(Color.GREY);
                    descPot.setDisable(true);
                } else {
                    //Campos tipo de potenciômetro
                    tipoPotLabel.setTextFill(Color.GREY);
                    disableFieldsTipoPot(true);
                    descTipoPot.setTextFill(Color.GREY);
                    descTipoPot.setDisable(true);

                    // Gráfico
                    labelGrafico.setOpacity(0.1);
                    grafico.setOpacity(0.1);

                    //Campos Potência
                    potField.setDisable(false);
                    labelPotencia.setOpacity(1);
                    labelPotencia.setTextFill(Color.web("#950005"));
                    descPot.setDisable(false);
                }
            }
        });

        //Campos tipo de potenciômetro
        tipoPotLabel.setTextFill(Color.GREY);
        disableFieldsTipoPot(true);
        descTipoPot.setDisable(true);

        //Campos Potência
        labelPotencia.setDisable(true);
        labelPotencia.setTextFill(Color.GREY);
        potField.setDisable(true);
        descPot.setDisable(true);

        //Gráfico
        labelGrafico.setOpacity(0.1);
        grafico.setOpacity(0.1);



    }

    public String getPotencia() {
        if(potField.isDisabled())
            return "0";

        String input = potField.getText().trim();
        boolean found = false;

        for (String potencia : potenciasDisponiveis) {
            if (input.equalsIgnoreCase(potencia))
                found = true;
        }

        if(!input.isEmpty() && found) {
            return input;
        } else {
            Alerta.exibirAlertaErro("POTÊNCIA INEXISTENTE!", "Potências disponíveis: 0, 1/8, 1/4, 1/2, 1, 2, 3, 4, 5 e 10W.\nInformado: " + input);
        }

        return null;
    }

    public int getQtd() {
        String input = qtdField.getText().trim();
        int qtd = -1;

        if(!input.isEmpty()) {
            try {
                qtd = Integer.parseInt(input);
            } catch (NumberFormatException npe) {
                Alerta.exibirAlertaErro("OPS! ALGO DEU ERRADO!", "Campo QUANTIDADE só pode conter números.");
            }
        }
        return qtd;
    }

    public String getMaterial() {
        String input = materialBox.getValue();
        boolean found = false;

        for (String potencia : tiposResistores) {
            if (potencia.equalsIgnoreCase(input))
                found = true;
        }

        if(found && !input.equalsIgnoreCase("tipo do resistor")) {
            return input;
        }

        return null;
    }

    public int alterarQtd() {
        try {
            return Integer.parseInt(alterarQtdField.getText().trim());
        } catch (NumberFormatException npe) {
            Alerta.exibirAlertaErro("OPS! ALGO DEU ERRADO!", "Campo QUANTIDADE só pode conter números.");
        }
        return -1;
    }

    public String getTipoPot() {
        String input = tipoPotField.getText().trim();
        boolean found = false;

        for (String potenciometro : tiposPotenciometros) {
            if (input.equalsIgnoreCase(potenciometro))
                found = true;
        }

        if(found)
            return input;

        return null;
    }

    public void disableFieldsTipoPot (boolean trueFalse) {
        tipoPotField.setDisable(trueFalse);
        tipoPotLabel.setDisable(trueFalse);
    }





}
