package electronicstuff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

public class MainFrameController {

    private final String NENHUMA = " ";
    private final String PRETO = "PRETO";
    private final String MARROM = "MARROM";
    private final String VERMELHO = "VERMELHO";
    private final String LARANJA = "LARANJA";
    private final String AMARELO = "AMARELO";
    private final String VERDE = "VERDE";
    private final String AZUL = "AZUL";
    private final String VIOLETA = "VIOLETA";
    private final String CINZA = "CINZA";
    private final String BRANCO = "BRANCO";
    private final String DOURADO = "DOURADO";
    private final String PRATA = "PRATA";

    private ObservableList<String> colorsList = FXCollections.observableArrayList
            (NENHUMA,PRETO, MARROM, VERMELHO, LARANJA, AMARELO, VERDE, AZUL, VIOLETA, CINZA, BRANCO, DOURADO, PRATA);

    private ObservableList<Componente> listaComponentes = FXCollections.observableArrayList();

    @FXML
    private TabPane outerTabPane;

    @FXML
    private Label labelR4, labelR5;

/** Aba do resistor de 4 faixas */
    //Menus de seleção de cores
    @FXML
    private ChoiceBox<String> menuR4F1, menuR4F2, menuR4F3, menuR4F4;
    @FXML
    private ChoiceBox<String> menuR4Num1, menuR4Num2, menuR4Mult, menuR4Tol;

    // Quadrados de cor do resistor
    @FXML
    private Shape r4f1, r4f2, r4f3, r4f4;

/** Aba do resistor de 5 faixas */
    //Menus de seleção de cores
    @FXML
    private ChoiceBox<String> menuR5F1, menuR5F2, menuR5F3, menuR5F4, menuR5F5;
    @FXML
    private ChoiceBox<String> menuR5Num1, menuR5Num2, menuR5Num3, menuR5Mult, menuR5Tol;

    // Quadrados de cor do resistor
    @FXML
    private Shape r5f1, r5f2, r5f3, r5f4, r5f5;

    //Botões
    @FXML
    private Button botaoArmazenarR4, botaoLimparR4, botaoArmazenarR5, botaoLimparR5;

    @FXML
    private TableView<Componente> bomList;

    @FXML
    private TableColumn<Resistor,Integer> qtdCol;
    @FXML
    private TableColumn<Resistor,String> resistCol, materialCol;
    @FXML
    private TableColumn<Resistor,Double> tolCol, potCol;

    // CAPACITOR:
    @FXML
    private TextField capNomenclatura,      capCapacitancia,     capCapacitanciaMili,
                      capCapacitanciaMicro, capCapacitanciaNano, capCapacitanciaPico;
    @FXML
    private Button botaoArmazenarCap, botaoLimparCap;



    /** Inicializar */
    public void initialize() {

        Resistor4Data.getInstance().setMult(1);
        //r5Mult.set(1);

        //Resistor4Data 4 faixas
        ObservableList<String> colorsR4F12 = FXCollections.observableArrayList(colorsList);
        colorsR4F12.removeAll(DOURADO, PRATA);
        menuR4F1.setItems(colorsR4F12);
        menuR4F1.setOnAction(e -> mudarCorFaixaR4(menuR4F1));
        Resistor4Data.getInstance().setCorFaixa1(PRETO);
        r4f1.setFill(Color.BLACK);
        menuR4F2.setItems(colorsR4F12);
        menuR4F2.setOnAction(e -> mudarCorFaixaR4(menuR4F2));
        Resistor4Data.getInstance().setCorFaixa2(PRETO);
        r4f2.setFill(Color.BLACK);

        ObservableList<String> colorsR4F3 = FXCollections.observableArrayList(colorsList);
        colorsR4F3.removeAll(CINZA, BRANCO);
        menuR4F3.setItems(colorsR4F3);
        menuR4F3.setOnAction(e -> mudarCorFaixaR4(menuR4F3));
        Resistor4Data.getInstance().setCorFaixa3(PRETO);
        r4f3.setFill(Color.BLACK);

        ObservableList<String> colorsR4F4 = FXCollections.observableArrayList(colorsList);
        colorsR4F4.removeAll(PRETO, LARANJA, AMARELO, BRANCO);
        menuR4F4.setItems(colorsR4F4);
        menuR4F4.setStyle("-fx-background-color: " + traduzirCor(PRETO) + ";");
        Resistor4Data.getInstance().setCorFaixa4(NENHUMA);
        r4f4.setFill(Color.BLACK);
        Resistor4Data.getInstance().setTol(0);
        menuR4F4.setOnAction(e -> mudarCorFaixaR4(menuR4F4));

        // Resistor4Data 4 faixas - Menus para saber cores
        menuR4Num1.setItems(FXCollections.observableArrayList(NENHUMA,"1","2","3","4","5","6","7","8","9"));
        menuR4Num1.setOnAction(e -> mudarValorR4(menuR4Num1));
        menuR4Num2.setItems(FXCollections.observableArrayList(NENHUMA,"0","1","2","3","4","5","6","7","8","9"));
        menuR4Num2.setOnAction(e -> mudarValorR4(menuR4Num2));
        menuR4Mult.setItems(FXCollections.observableArrayList(NENHUMA,"1","10","100","1000","10000","100000","1000000","10000000","0.1","0.01"));
        menuR4Mult.setOnAction(e -> mudarValorR4(menuR4Mult));
        menuR4Tol.setItems(FXCollections.observableArrayList(NENHUMA,"1","2","0.5","0.25","0.1","0.05","5","10"));
        menuR4Tol.setOnAction(e -> mudarValorR4(menuR4Tol));

        //Resistor4Data 5 faixas
        ObservableList<String> colorsR5F123 = FXCollections.observableArrayList(colorsList);
        colorsR5F123.removeAll(DOURADO, PRATA);
        menuR5F1.setItems(colorsR5F123);
        menuR5F1.setOnAction(e -> mudarCorFaixaR5(menuR5F1));
        Resistor5Data.getInstance().setCorFaixa1(PRETO);
        r5f1.setFill(Color.BLACK);
        menuR5F2.setItems(colorsR5F123);
        menuR5F2.setOnAction(e -> mudarCorFaixaR5(menuR5F2));
        Resistor5Data.getInstance().setCorFaixa2(PRETO);
        r5f2.setFill(Color.BLACK);
        menuR5F3.setItems(colorsR5F123);
        menuR5F3.setOnAction(e -> mudarCorFaixaR5(menuR5F3));
        Resistor5Data.getInstance().setCorFaixa3(PRETO);
        r5f3.setFill(Color.BLACK);

        ObservableList<String> colorsR5F4 = FXCollections.observableArrayList(colorsList);
        colorsR5F4.removeAll(CINZA, BRANCO);
        menuR5F4.setItems(colorsR5F4);
        menuR5F4.setOnAction(e -> mudarCorFaixaR5(menuR5F4));
        Resistor5Data.getInstance().setCorFaixa4(PRETO);
        r5f4.setFill(Color.BLACK);

        ObservableList<String> colorsR5F5 = FXCollections.observableArrayList(colorsList);
        colorsR5F5.removeAll(PRETO, LARANJA, AMARELO, BRANCO);
        menuR5F5.setItems(colorsR5F5);
        menuR5F5.setStyle("-fx-background-color: " + traduzirCor(PRETO) + ";");
        Resistor5Data.getInstance().setCorFaixa5(NENHUMA);
        r5f5.setFill(Color.BLACK);
        Resistor5Data.getInstance().setTol(0);
        menuR5F5.setOnAction(e -> mudarCorFaixaR5(menuR5F5));

        // Resistor5Data 5 faixas - Menus para saber cores
        menuR5Num1.setItems(FXCollections.observableArrayList(NENHUMA,"1","2","3","4","5","6","7","8","9"));
        menuR5Num1.setOnAction(e -> mudarValorR5(menuR5Num1));
        menuR5Num2.setItems(FXCollections.observableArrayList(NENHUMA,"0","1","2","3","4","5","6","7","8","9"));
        menuR5Num2.setOnAction(e -> mudarValorR5(menuR5Num2));
        menuR5Num3.setItems(FXCollections.observableArrayList(NENHUMA,"0","1","2","3","4","5","6","7","8","9"));
        menuR5Num3.setOnAction(e -> mudarValorR5(menuR5Num3));
        menuR5Mult.setItems(FXCollections.observableArrayList(NENHUMA,"1","10","100","1000","10000","100000","1000000","10000000","0.1","0.01"));
        menuR5Mult.setOnAction(e -> mudarValorR5(menuR5Mult));
        menuR5Tol.setItems(FXCollections.observableArrayList(NENHUMA,"1","2","0.5","0.25","0.1","0.05","5","10"));
        menuR5Tol.setOnAction(e -> mudarValorR5(menuR5Tol));

        labelR4.setText(Resistor4Data.getInstance().calcularResistencia());
        labelR5.setText(Resistor5Data.getInstance().calcularResistencia());

        botaoArmazenarR4.setOnAction(e -> armazenarResistor(botaoArmazenarR4));
        botaoLimparR4.setOnAction(e -> limparDados(botaoLimparR4));
        botaoArmazenarR5.setOnAction(e -> armazenarResistor(botaoArmazenarR5));
        botaoLimparR5.setOnAction(e -> limparDados(botaoLimparR5));

        //TableView
        resistCol.setCellValueFactory(new PropertyValueFactory<Resistor,String>("valor"));
        materialCol.setCellValueFactory(new PropertyValueFactory<Resistor,String>("material"));
        qtdCol.setCellValueFactory(new PropertyValueFactory<Resistor,Integer>("qtd"));
        tolCol.setCellValueFactory(new PropertyValueFactory<Resistor,Double>("tolerancia"));
        potCol.setCellValueFactory(new PropertyValueFactory<Resistor,Double>("potencia"));
        bomList.setItems(listaComponentes);
        bomList.getSelectionModel().selectFirst();

        MenuItem qtdMenu1 = new MenuItem("Alterar Quantidade");
        MenuItem qtdMenu2 = new MenuItem("Alterar Quantidade");
        MenuItem deleteMenu1 = new MenuItem("Deletar");
        MenuItem deleteMenu2 = new MenuItem("Deletar");
        MenuItem coresMenu = new MenuItem("Cores");
        MenuItem nomenclaturaMenu = new MenuItem("Nomenclatura");

        ContextMenu contextMenuResistor = new ContextMenu(qtdMenu1, deleteMenu1, coresMenu);
        ContextMenu contextMenuCapacitor = new ContextMenu(qtdMenu2, deleteMenu2, nomenclaturaMenu);

        bomList.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Componente compSelecionado = bomList.getSelectionModel().getSelectedItem();
                if(event.getButton() == MouseButton.SECONDARY) {
                    if(compSelecionado instanceof Resistor)
                        contextMenuResistor.show(outerTabPane, event.getScreenX(), event.getScreenY());
                    else if (compSelecionado instanceof Capacitor)
                        contextMenuCapacitor.show(outerTabPane, event.getScreenX(), event.getScreenY());
                } else {
                    contextMenuResistor.hide();
                    contextMenuCapacitor.hide();
                }
            }
        });

        qtdMenu1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editarQuantidade(bomList.getSelectionModel().getSelectedItem());
            }
        });

        qtdMenu2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editarQuantidade(bomList.getSelectionModel().getSelectedItem());
            }
        });

        deleteMenu1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deletarComponente();
            }
        });

        deleteMenu2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deletarComponente();
            }
        });

        coresMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mostrarCores();
            }
        });

        nomenclaturaMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mostrarNomenclatura();
            }
        });

        outerTabPane.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.DELETE) {
                deletarComponente();
            }
        });


    }

    @FXML
    private void mudarValorR4(ChoiceBox<String> choiceBox) {
        String valorSelecOriginal = choiceBox.getValue();
        float valorSelecFloat;
        String corSelecionada = "";

        if(valorSelecOriginal.equalsIgnoreCase(NENHUMA))
            valorSelecFloat = -1;
        else
            valorSelecFloat = Float.parseFloat(valorSelecOriginal);

        String menuName = choiceBox.getId();

        switch (menuName) {
            case "menuR4Num1":
                if(valorSelecFloat > 0) {
                    if (valorSelecFloat == 1) {
                        menuR4F1.setValue(MARROM);
                        mudarCorFaixaR4(menuR4F1);
                    } else if (valorSelecFloat == 2) {
                        menuR4F1.setValue(VERMELHO);
                        mudarCorFaixaR4(menuR4F1);
                    } else if (valorSelecFloat == 3) {
                        menuR4F1.setValue(LARANJA);
                        mudarCorFaixaR4(menuR4F1);
                    } else if (valorSelecFloat == 4) {
                        menuR4F1.setValue(AMARELO);
                        mudarCorFaixaR4(menuR4F1);
                    } else if (valorSelecFloat == 5) {
                        menuR4F1.setValue(VERDE);
                        mudarCorFaixaR4(menuR4F1);
                    } else if (valorSelecFloat == 6) {
                        menuR4F1.setValue(AZUL);
                        mudarCorFaixaR4(menuR4F1);
                    } else if (valorSelecFloat == 7) {
                        menuR4F1.setValue(VIOLETA);
                        mudarCorFaixaR4(menuR4F1);
                    } else if (valorSelecFloat == 8) {
                        menuR4F1.setValue(CINZA);
                        mudarCorFaixaR4(menuR4F1);
                    } else if (valorSelecFloat == 9) {
                        menuR4F1.setValue(BRANCO);
                        mudarCorFaixaR4(menuR4F1);
                    }
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                    choiceBox.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
                }
                corSelecionada = menuR4F1.getValue();
                if(corSelecionada.equalsIgnoreCase(BRANCO) || corSelecionada.equalsIgnoreCase(AMARELO) ||
                        corSelecionada.equalsIgnoreCase(CINZA) || corSelecionada.equalsIgnoreCase(PRATA)) {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorClara");
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                }
                choiceBox.setStyle("-fx-background-color: " + traduzirCor(menuR4F1.getValue()) + "; -fx-mark-color: #950005;");
                break;
            case "menuR4Num2":
                if(valorSelecFloat >= 0) {
                    if (valorSelecFloat == 0) {
                        menuR4F2.setValue(PRETO);
                        mudarCorFaixaR4(menuR4F2);
                    } else if (valorSelecFloat == 1) {
                        menuR4F2.setValue(MARROM);
                        mudarCorFaixaR4(menuR4F2);
                    } else if (valorSelecFloat == 2) {
                        menuR4F2.setValue(VERMELHO);
                        mudarCorFaixaR4(menuR4F2);
                    } else if (valorSelecFloat == 3) {
                        menuR4F2.setValue(LARANJA);
                        mudarCorFaixaR4(menuR4F2);
                    } else if (valorSelecFloat == 4) {
                        menuR4F2.setValue(AMARELO);
                        mudarCorFaixaR4(menuR4F2);
                    } else if (valorSelecFloat == 5) {
                        menuR4F2.setValue(VERDE);
                        mudarCorFaixaR4(menuR4F2);
                    } else if (valorSelecFloat == 6) {
                        menuR4F2.setValue(AZUL);
                        mudarCorFaixaR4(menuR4F2);
                    } else if (valorSelecFloat == 7) {
                        menuR4F2.setValue(VIOLETA);
                        mudarCorFaixaR4(menuR4F2);
                    } else if (valorSelecFloat == 8) {
                        menuR4F2.setValue(CINZA);
                        mudarCorFaixaR4(menuR4F2);
                    } else if (valorSelecFloat == 9) {
                        menuR4F2.setValue(BRANCO);
                        mudarCorFaixaR4(menuR4F2);
                    }
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                    choiceBox.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
                }
                corSelecionada = menuR4F2.getValue();
                if(corSelecionada.equalsIgnoreCase(BRANCO) || corSelecionada.equalsIgnoreCase(AMARELO) ||
                        corSelecionada.equalsIgnoreCase(CINZA) || corSelecionada.equalsIgnoreCase(PRATA)) {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorClara");
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                }
                choiceBox.setStyle("-fx-background-color: " + traduzirCor(menuR4F2.getValue()) + "; -fx-mark-color: #950005;");
                break;
            case "menuR4Mult":
                if(valorSelecFloat >= 0) {
                    if (valorSelecFloat == 1) {
                        menuR4F3.setValue(PRETO);
                        mudarCorFaixaR4(menuR4F3);
                    } else if (valorSelecFloat == 10) {
                        menuR4F3.setValue(MARROM);
                        mudarCorFaixaR4(menuR4F3);
                    } else if (valorSelecFloat == 100) {
                        menuR4F3.setValue(VERMELHO);
                        mudarCorFaixaR4(menuR4F3);
                    } else if (valorSelecFloat == 1000) {
                        menuR4F3.setValue(LARANJA);
                        mudarCorFaixaR4(menuR4F3);
                    } else if (valorSelecFloat == 10000) {
                        menuR4F3.setValue(AMARELO);
                        mudarCorFaixaR4(menuR4F3);
                    } else if (valorSelecFloat == 100000) {
                        menuR4F3.setValue(VERDE);
                        mudarCorFaixaR4(menuR4F3);
                    } else if (valorSelecFloat == 1000000) {
                        menuR4F3.setValue(AZUL);
                        mudarCorFaixaR4(menuR4F3);
                    } else if (valorSelecFloat == 10000000) {
                        menuR4F3.setValue(VIOLETA);
                        mudarCorFaixaR4(menuR4F3);
                    } else if (valorSelecOriginal.equalsIgnoreCase("0.1")) {
                        menuR4F3.setValue(DOURADO);
                        mudarCorFaixaR4(menuR4F3);
                    } else if (valorSelecOriginal.equalsIgnoreCase("0.01")) {
                        menuR4F3.setValue(PRATA);
                        mudarCorFaixaR4(menuR4F3);
                    }
                }
                corSelecionada = menuR4F3.getValue();
                if(corSelecionada.equalsIgnoreCase(BRANCO) || corSelecionada.equalsIgnoreCase(AMARELO) ||
                        corSelecionada.equalsIgnoreCase(CINZA) || corSelecionada.equalsIgnoreCase(PRATA)) {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorClara");
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                }
                choiceBox.setStyle("-fx-background-color: " + traduzirCor(menuR4F3.getValue()) + "; -fx-mark-color: #950005;");
                break;
            case "menuR4Tol":
                if(valorSelecFloat >= 0) {
                    if (valorSelecFloat == 1) {
                        menuR4F4.setValue(MARROM);
                        mudarCorFaixaR4(menuR4F4);
                    } else if (valorSelecFloat == 2) {
                        menuR4F4.setValue(VERMELHO);
                        mudarCorFaixaR4(menuR4F4);
                    } else if (valorSelecFloat == 0.5) {
                        menuR4F4.setValue(VERDE);
                        mudarCorFaixaR4(menuR4F4);
                    } else if (valorSelecFloat == 0.25) {
                        menuR4F4.setValue(AZUL);
                        mudarCorFaixaR4(menuR4F4);
                    } else if (valorSelecOriginal.equalsIgnoreCase("0.1")) {
                        menuR4F4.setValue(VIOLETA);
                        mudarCorFaixaR4(menuR4F4);
                    } else if (valorSelecOriginal.equalsIgnoreCase("0.05")) {
                        menuR4F4.setValue(CINZA);
                        mudarCorFaixaR4(menuR4F4);
                    } else if (valorSelecFloat == 5) {
                        menuR4F4.setValue(DOURADO);
                        mudarCorFaixaR4(menuR4F4);
                    } else if (valorSelecFloat == 10) {
                        menuR4F4.setValue(PRATA);
                        mudarCorFaixaR4(menuR4F4);
                    }
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                    choiceBox.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
                }
                corSelecionada = menuR4F4.getValue();
                if(corSelecionada.equalsIgnoreCase(BRANCO) || corSelecionada.equalsIgnoreCase(AMARELO) ||
                        corSelecionada.equalsIgnoreCase(CINZA) || corSelecionada.equalsIgnoreCase(PRATA)) {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorClara");
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                }
                choiceBox.setStyle("-fx-background-color: " + traduzirCor(menuR4F4.getValue()) + "; -fx-mark-color: #950005;");
                break;
        }
        labelR4.setText(Resistor4Data.getInstance().calcularResistencia());
    }

    @FXML
    private void mudarValorR5(ChoiceBox<String> choiceBox) {
        String valorSelecOriginal = choiceBox.getValue();
        float valorSelecFloat;
        String corSelecionada = "";

        if(valorSelecOriginal.equalsIgnoreCase(NENHUMA))
            valorSelecFloat = -1;
        else
            valorSelecFloat = Float.parseFloat(valorSelecOriginal);

        String menuName = choiceBox.getId();

        switch (menuName) {
            case "menuR5Num1":
                if(valorSelecFloat > 0) {
                    if        (valorSelecFloat == 1) {
                        menuR5F1.setValue(MARROM);
                        mudarCorFaixaR5(menuR5F1);
                    } else if (valorSelecFloat == 2) {
                        menuR5F1.setValue(VERMELHO);
                        mudarCorFaixaR5(menuR5F1);
                    } else if (valorSelecFloat == 3) {
                        menuR5F1.setValue(LARANJA);
                        mudarCorFaixaR5(menuR5F1);
                    } else if (valorSelecFloat == 4) {
                        menuR5F1.setValue(AMARELO);
                        mudarCorFaixaR5(menuR5F1);
                    } else if (valorSelecFloat == 5) {
                        menuR5F1.setValue(VERDE);
                        mudarCorFaixaR5(menuR5F1);
                    } else if (valorSelecFloat == 6) {
                        menuR5F1.setValue(AZUL);
                        mudarCorFaixaR5(menuR5F1);
                    } else if (valorSelecFloat == 7) {
                        menuR5F1.setValue(VIOLETA);
                        mudarCorFaixaR5(menuR5F1);
                    } else if (valorSelecFloat == 8) {
                        menuR5F1.setValue(CINZA);
                        mudarCorFaixaR5(menuR5F1);
                    } else if (valorSelecFloat == 9) {
                        menuR5F1.setValue(BRANCO);
                        mudarCorFaixaR5(menuR5F1);
                    }
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                    choiceBox.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
                }
                corSelecionada = menuR5F1.getValue();
                if(corSelecionada.equalsIgnoreCase(BRANCO) || corSelecionada.equalsIgnoreCase(AMARELO) ||
                        corSelecionada.equalsIgnoreCase(CINZA) || corSelecionada.equalsIgnoreCase(PRATA)) {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorClara");
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                }
                choiceBox.setStyle("-fx-background-color: " + traduzirCor(menuR5F1.getValue()) + "; -fx-mark-color: #950005;");
                break;
            case "menuR5Num2":
                if(valorSelecFloat >= 0) {
                    if (valorSelecFloat == 0) {
                        menuR5F2.setValue(PRETO);
                        mudarCorFaixaR5(menuR5F2);
                    } else if (valorSelecFloat == 1) {
                        menuR5F2.setValue(MARROM);
                        mudarCorFaixaR5(menuR5F2);
                    } else if (valorSelecFloat == 2) {
                        menuR5F2.setValue(VERMELHO);
                        mudarCorFaixaR5(menuR5F2);
                    } else if (valorSelecFloat == 3) {
                        menuR5F2.setValue(LARANJA);
                        mudarCorFaixaR5(menuR5F2);
                    } else if (valorSelecFloat == 4) {
                        menuR5F2.setValue(AMARELO);
                        mudarCorFaixaR5(menuR5F2);
                    } else if (valorSelecFloat == 5) {
                        menuR5F2.setValue(VERDE);
                        mudarCorFaixaR5(menuR5F2);
                    } else if (valorSelecFloat == 6) {
                        menuR5F2.setValue(AZUL);
                        mudarCorFaixaR5(menuR5F2);
                    } else if (valorSelecFloat == 7) {
                        menuR5F2.setValue(VIOLETA);
                        mudarCorFaixaR5(menuR5F2);
                    } else if (valorSelecFloat == 8) {
                        menuR5F2.setValue(CINZA);
                        mudarCorFaixaR5(menuR5F2);
                    } else if (valorSelecFloat == 9) {
                        menuR5F2.setValue(BRANCO);
                        mudarCorFaixaR5(menuR5F2);
                    }
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                    choiceBox.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
                }
                corSelecionada = menuR5F2.getValue();
                if(corSelecionada.equalsIgnoreCase(BRANCO) || corSelecionada.equalsIgnoreCase(AMARELO) ||
                        corSelecionada.equalsIgnoreCase(CINZA) || corSelecionada.equalsIgnoreCase(PRATA)) {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorClara");
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                }
                choiceBox.setStyle("-fx-background-color: " + traduzirCor(menuR5F2.getValue()) + "; -fx-mark-color: #950005;");
                break;
            case "menuR5Num3":
                if(valorSelecFloat >= 0) {
                    if (valorSelecFloat == 0) {
                        menuR5F3.setValue(PRETO);
                        mudarCorFaixaR5(menuR5F3);
                    } else if (valorSelecFloat == 1) {
                        menuR5F3.setValue(MARROM);
                        mudarCorFaixaR5(menuR5F3);
                    } else if (valorSelecFloat == 2) {
                        menuR5F3.setValue(VERMELHO);
                        mudarCorFaixaR5(menuR5F3);
                    } else if (valorSelecFloat == 3) {
                        menuR5F3.setValue(LARANJA);
                        mudarCorFaixaR5(menuR5F3);
                    } else if (valorSelecFloat == 4) {
                        menuR5F3.setValue(AMARELO);
                        mudarCorFaixaR5(menuR5F3);
                    } else if (valorSelecFloat == 5) {
                        menuR5F3.setValue(VERDE);
                        mudarCorFaixaR5(menuR5F3);
                    } else if (valorSelecFloat == 6) {
                        menuR5F3.setValue(AZUL);
                        mudarCorFaixaR5(menuR5F3);
                    } else if (valorSelecFloat == 7) {
                        menuR5F3.setValue(VIOLETA);
                        mudarCorFaixaR5(menuR5F3);
                    } else if (valorSelecFloat == 8) {
                        menuR5F3.setValue(CINZA);
                        mudarCorFaixaR5(menuR5F3);
                    } else if (valorSelecFloat == 9) {
                        menuR5F3.setValue(BRANCO);
                        mudarCorFaixaR5(menuR5F3);
                    }
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                    choiceBox.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
                }
                corSelecionada = menuR5F3.getValue();
                if(corSelecionada.equalsIgnoreCase(BRANCO) || corSelecionada.equalsIgnoreCase(AMARELO) ||
                        corSelecionada.equalsIgnoreCase(CINZA) || corSelecionada.equalsIgnoreCase(PRATA)) {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorClara");
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                }
                choiceBox.setStyle("-fx-background-color: " + traduzirCor(menuR5F3.getValue()) + "; -fx-mark-color: #950005;");
                break;
            case "menuR5Mult":
                if(valorSelecFloat >= 0) {
                    if (valorSelecFloat == 1) {
                        menuR5F4.setValue(PRETO);
                        mudarCorFaixaR5(menuR5F4);
                    } else if (valorSelecFloat == 10) {
                        menuR5F4.setValue(MARROM);
                        mudarCorFaixaR5(menuR5F4);
                    } else if (valorSelecFloat == 100) {
                        menuR5F4.setValue(VERMELHO);
                        mudarCorFaixaR5(menuR5F4);
                    } else if (valorSelecFloat == 1000) {
                        menuR5F4.setValue(LARANJA);
                        mudarCorFaixaR5(menuR5F4);
                    } else if (valorSelecFloat == 10000) {
                        menuR5F4.setValue(AMARELO);
                        mudarCorFaixaR5(menuR5F4);
                    } else if (valorSelecFloat == 100000) {
                        menuR5F4.setValue(VERDE);
                        mudarCorFaixaR5(menuR5F4);
                    } else if (valorSelecFloat == 1000000) {
                        menuR5F4.setValue(AZUL);
                        mudarCorFaixaR5(menuR5F4);
                    } else if (valorSelecFloat == 10000000) {
                        menuR5F4.setValue(VIOLETA);
                        mudarCorFaixaR5(menuR5F4);
                    } else if (valorSelecOriginal.equalsIgnoreCase("0.1")) {
                        menuR5F4.setValue(DOURADO);
                        mudarCorFaixaR5(menuR5F4);
                    } else if (valorSelecOriginal.equalsIgnoreCase("0.01")) {
                        menuR5F4.setValue(PRATA);
                        mudarCorFaixaR5(menuR5F4);
                    }
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                    choiceBox.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
                }
                corSelecionada = menuR5F4.getValue();
                if(corSelecionada.equalsIgnoreCase(BRANCO) || corSelecionada.equalsIgnoreCase(AMARELO) ||
                        corSelecionada.equalsIgnoreCase(CINZA) || corSelecionada.equalsIgnoreCase(PRATA)) {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorClara");
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                }
                choiceBox.setStyle("-fx-background-color: " + traduzirCor(menuR5F4.getValue()) + "; -fx-mark-color: #950005;");
                break;
            case "menuR5Tol":
                if(valorSelecFloat >= 0) {
                    if (valorSelecFloat == 1) {
                        menuR5F5.setValue(MARROM);
                        mudarCorFaixaR5(menuR5F5);
                    } else if (valorSelecFloat == 2) {
                        menuR5F5.setValue(VERMELHO);
                        mudarCorFaixaR5(menuR5F5);
                    } else if (valorSelecFloat == 0.5) {
                        menuR5F5.setValue(VERDE);
                        mudarCorFaixaR5(menuR5F5);
                    } else if (valorSelecFloat == 0.25) {
                        menuR5F5.setValue(AZUL);
                        mudarCorFaixaR5(menuR5F5);
                    } else if (valorSelecOriginal.equalsIgnoreCase("0.1")) {
                        menuR5F5.setValue(VIOLETA);
                        mudarCorFaixaR5(menuR5F5);
                    } else if (valorSelecOriginal.equalsIgnoreCase("0.05")) {
                        menuR5F5.setValue(CINZA);
                        mudarCorFaixaR5(menuR5F5);
                    } else if (valorSelecFloat == 5) {
                        menuR5F5.setValue(DOURADO);
                        mudarCorFaixaR5(menuR5F5);
                    } else if (valorSelecFloat == 10) {
                        menuR5F5.setValue(PRATA);
                        mudarCorFaixaR5(menuR5F5);
                    }
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                    choiceBox.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
                }
                corSelecionada = menuR5F5.getValue();
                if(corSelecionada.equalsIgnoreCase(BRANCO) || corSelecionada.equalsIgnoreCase(AMARELO) ||
                        corSelecionada.equalsIgnoreCase(CINZA) || corSelecionada.equalsIgnoreCase(PRATA)) {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorClara");
                } else {
                    choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
                }
                choiceBox.setStyle("-fx-background-color: " + traduzirCor(menuR5F5.getValue()) + "; -fx-mark-color: #950005;");
                break;
        }
        labelR5.setText(Resistor5Data.getInstance().calcularResistencia());
    }

    @FXML
    public void mudarCorFaixaR4 (ChoiceBox<String> choiceBox) {
        String corSelecionada = choiceBox.getValue();
        String menuName = choiceBox.getId().toLowerCase();
        System.out.println("Cor " + corSelecionada.toLowerCase() + " selecionada no " + menuName);

        switch (corSelecionada.toUpperCase()) {
            case PRETO:
                switch (menuName) {
                    case "menur4f1":
                        r4f1.setFill(Color.BLACK);
                        Resistor4Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa1(0);
                        menuR4Num1.setValue(NENHUMA);
                        break;
                    case "menur4f2":
                        r4f2.setFill(Color.BLACK);
                        Resistor4Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa2(0);
                        menuR4Num2.setValue("0");
                        break;
                    case "menur4f3":
                        r4f3.setFill(Color.BLACK);
                        Resistor4Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor4Data.getInstance().setMult(1);
                        menuR4Mult.setValue("1");
                        break;
                }
                break;
            case MARROM:
                switch (menuName) {
                    case "menur4f1":
                        r4f1.setFill(Color.SADDLEBROWN);
                        Resistor4Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa1(1);
                        menuR4Num1.setValue("1");
                        break;
                    case "menur4f2":
                        r4f2.setFill(Color.SADDLEBROWN);
                        Resistor4Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa2(1);
                        menuR4Num2.setValue("1");
                        break;
                    case "menur4f3":
                        r4f3.setFill(Color.SADDLEBROWN);
                        Resistor4Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor4Data.getInstance().setMult(10);
                        menuR4Mult.setValue("10");
                        break;
                    case "menur4f4":
                        r4f4.setFill(Color.SADDLEBROWN);
                        Resistor4Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor4Data.getInstance().setTol(1);
                        menuR4Tol.setValue("1");
                        break;
                }
                break;
            case VERMELHO:
                switch (menuName) {
                    case "menur4f1":
                        r4f1.setFill(Color.RED);
                        Resistor4Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa1(2);
                        menuR4Num1.setValue("2");
                        break;
                    case "menur4f2":
                        r4f2.setFill(Color.RED);
                        Resistor4Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa2(2);
                        menuR4Num2.setValue("2");
                        break;
                    case "menur4f3":
                        r4f3.setFill(Color.RED);
                        Resistor4Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor4Data.getInstance().setMult(100);
                        menuR4Mult.setValue("100");
                        break;
                    case "menur4f4":
                        r4f4.setFill(Color.RED);
                        Resistor4Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor4Data.getInstance().setTol(2);
                        menuR4Tol.setValue("2");
                        break;
                }
                break;
            case LARANJA:
                switch (menuName) {
                    case "menur4f1":
                        r4f1.setFill(Color.ORANGERED);
                        Resistor4Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa1(3);
                        menuR4Num1.setValue("3");
                        break;
                    case "menur4f2":
                        r4f2.setFill(Color.ORANGERED);
                        Resistor4Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa2(3);
                        menuR4Num2.setValue("3");
                        break;
                    case "menur4f3":
                        r4f3.setFill(Color.ORANGERED);
                        Resistor4Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor4Data.getInstance().setMult(1000);
                        menuR4Mult.setValue("1000");
                        break;
                }
                break;
            case AMARELO:
                switch (menuName) {
                    case "menur4f1":
                        r4f1.setFill(Color.YELLOW);
                        Resistor4Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa1(4);
                        menuR4Num1.setValue("4");
                        break;
                    case "menur4f2":
                        r4f2.setFill(Color.YELLOW);
                        Resistor4Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa2(4);
                        menuR4Num2.setValue("4");
                        break;
                    case "menur4f3":
                        r4f3.setFill(Color.YELLOW);
                        Resistor4Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor4Data.getInstance().setMult(10000);
                        menuR4Mult.setValue("10000");
                        break;
                }
                break;
            case VERDE:
                switch (menuName) {
                    case "menur4f1":
                        r4f1.setFill(Color.GREEN);
                        Resistor4Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa1(5);
                        menuR4Num1.setValue("5");
                        break;
                    case "menur4f2":
                        r4f2.setFill(Color.GREEN);
                        Resistor4Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa2(5);
                        menuR4Num2.setValue("5");
                        break;
                    case "menur4f3":
                        r4f3.setFill(Color.GREEN);
                        Resistor4Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor4Data.getInstance().setMult(100000);
                        menuR4Mult.setValue("100000");
                        break;
                    case "menur4f4":
                        r4f4.setFill(Color.GREEN);
                        Resistor4Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor4Data.getInstance().setTol(0.5);
                        menuR4Tol.setValue("0.5");
                        break;
                }
                break;
            case AZUL:
                switch (menuName) {
                    case "menur4f1":
                        r4f1.setFill(Color.BLUE);
                        Resistor4Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa1(6);
                        menuR4Num1.setValue("6");
                        break;
                    case "menur4f2":
                        r4f2.setFill(Color.BLUE);
                        Resistor4Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa2(6);
                        menuR4Num2.setValue("6");
                        break;
                    case "menur4f3":
                        r4f3.setFill(Color.BLUE);
                        Resistor4Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor4Data.getInstance().setMult(1000000);
                        menuR4Mult.setValue("1000000");
                        break;
                    case "menur4f4":
                        r4f4.setFill(Color.BLUE);
                        Resistor4Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor4Data.getInstance().setTol(0.25);
                        menuR4Tol.setValue("0.25");
                        break;
                }
                break;
            case VIOLETA:
                switch (menuName) {
                    case "menur4f1":
                        r4f1.setFill(Color.VIOLET);
                        Resistor4Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa1(7);
                        menuR4Num1.setValue("7");
                        break;
                    case "menur4f2":
                        r4f2.setFill(Color.VIOLET);
                        Resistor4Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa2(7);
                        menuR4Num2.setValue("7");
                        break;
                    case "menur4f3":
                        r4f3.setFill(Color.VIOLET);
                        Resistor4Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor4Data.getInstance().setMult(10000000);
                        menuR4Mult.setValue("10000000");
                        break;
                    case "menur4f4":
                        r4f4.setFill(Color.VIOLET);
                        Resistor4Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor4Data.getInstance().setTol(0.1);
                        menuR4Tol.setValue("0.1");
                        break;
                }
                break;
            case CINZA:
                switch (menuName) {
                    case "menur4f1":
                        r4f1.setFill(Color.GREY);
                        Resistor4Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa1(8);
                        menuR4Num1.setValue("8");
                        break;
                    case "menur4f2":
                        r4f2.setFill(Color.GREY);
                        Resistor4Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa2(8);
                        menuR4Num2.setValue("8");
                        break;
                    case "menur4f4":
                        r4f4.setFill(Color.GREY);
                        Resistor4Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor4Data.getInstance().setTol(0.05);
                        menuR4Tol.setValue("0.05");
                        break;
                }
                break;
            case BRANCO:
                switch (menuName) {
                    case "menur4f1":
                        r4f1.setFill(Color.WHITE);
                        Resistor4Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa1(9);
                        menuR4Num1.setValue("9");
                        break;
                    case "menur4f2":
                        r4f2.setFill(Color.WHITE);
                        Resistor4Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor4Data.getInstance().setNumeroFaixa2(9);
                        menuR4Num2.setValue("9");
                        break;
                }
                break;
            case DOURADO:
                switch (menuName) {
                    case "menur4f3":
                        r4f3.setFill(Color.DARKGOLDENROD);
                        Resistor4Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor4Data.getInstance().setMult(0.1);
                        menuR4Mult.setValue("0.1");
                        break;
                    case "menur4f4":
                        r4f4.setFill(Color.DARKGOLDENROD);
                        Resistor4Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor4Data.getInstance().setTol(5);
                        menuR4Tol.setValue("5");
                        break;
                }
                break;
            case PRATA:
                switch (menuName) {
                    case "menur4f3":
                        r4f3.setFill(Color.SILVER);
                        Resistor4Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor4Data.getInstance().setMult(0.01);
                        menuR4Mult.setValue("0.01");
                        break;
                    case "menur4f4":
                        r4f4.setFill(Color.SILVER);
                        Resistor4Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor4Data.getInstance().setTol(10);
                        menuR4Tol.setValue("10");
                        break;
                }
                break;
        }

        if(corSelecionada.equalsIgnoreCase(BRANCO) || corSelecionada.equalsIgnoreCase(AMARELO) ||
                corSelecionada.equalsIgnoreCase(CINZA) || corSelecionada.equalsIgnoreCase(PRATA)) {
            choiceBox.getStyleClass().setAll("choice-box","itemCorClara");
        } else {
            choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
        }

        choiceBox.setStyle("-fx-background-color: " + traduzirCor(choiceBox.getValue()) + "; -fx-mark-color: #950005;");
        labelR4.setText(Resistor4Data.getInstance().calcularResistencia());
    }

    @FXML
    public void mudarCorFaixaR5 (ChoiceBox<String> choiceBox) {
        String corSelecionada = choiceBox.getValue();
        String menuName = choiceBox.getId().toLowerCase();
        System.out.println("Cor " + corSelecionada.toLowerCase() + " selecionada no " + menuName);

        switch (corSelecionada.toUpperCase()) {
            case PRETO:
                switch (menuName) {
                    case "menur5f1":
                        r5f1.setFill(Color.BLACK);
                        Resistor5Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa1(0);
                        menuR5Num1.setValue(NENHUMA);
                        break;
                    case "menur5f2":
                        r5f2.setFill(Color.BLACK);
                        Resistor5Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa2(0);
                        menuR5Num2.setValue("0");
                        break;
                    case "menur5f3":
                        r5f3.setFill(Color.BLACK);
                        Resistor5Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa3(0);
                        menuR5Num3.setValue("0");
                        break;
                    case "menur5f4":
                        r5f4.setFill(Color.BLACK);
                        Resistor5Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor5Data.getInstance().setMult(1);
                        menuR5Mult.setValue("1");
                        break;
                }
                break;
            case MARROM:
                switch (menuName) {
                    case "menur5f1":
                        r5f1.setFill(Color.SADDLEBROWN);
                        Resistor5Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa1(1);
                        menuR5Num1.setValue("1");
                        break;
                    case "menur5f2":
                        r5f2.setFill(Color.SADDLEBROWN);
                        Resistor5Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa2(1);
                        menuR5Num2.setValue("1");
                        break;
                    case "menur5f3":
                        r5f3.setFill(Color.SADDLEBROWN);
                        Resistor5Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa3(1);
                        menuR5Num3.setValue("1");
                        break;
                    case "menur5f4":
                        r5f4.setFill(Color.SADDLEBROWN);
                        Resistor5Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor5Data.getInstance().setMult(10);
                        menuR5Mult.setValue("10");
                        break;
                    case "menur5f5":
                        r5f5.setFill(Color.SADDLEBROWN);
                        Resistor5Data.getInstance().setCorFaixa5(corSelecionada);
                        Resistor5Data.getInstance().setTol(1);
                        menuR5Tol.setValue("1");
                        break;
                }
                break;
            case VERMELHO:
                switch (menuName) {
                    case "menur5f1":
                        r5f1.setFill(Color.RED);
                        Resistor5Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa1(2);
                        menuR5Num1.setValue("2");
                        break;
                    case "menur5f2":
                        r5f2.setFill(Color.RED);
                        Resistor5Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa2(2);
                        menuR5Num2.setValue("2");
                        break;
                    case "menur5f3":
                        r5f3.setFill(Color.RED);
                        Resistor5Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa3(2);
                        menuR5Num3.setValue("2");
                        break;
                    case "menur5f4":
                        r5f4.setFill(Color.RED);
                        Resistor5Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor5Data.getInstance().setMult(100);
                        menuR5Mult.setValue("100");
                        break;
                    case "menur5f5":
                        r5f5.setFill(Color.RED);
                        Resistor5Data.getInstance().setCorFaixa5(corSelecionada);
                        Resistor5Data.getInstance().setTol(2);
                        menuR5Tol.setValue("2");
                        break;
                }
                break;
            case LARANJA:
                switch (menuName) {
                    case "menur5f1":
                        r5f1.setFill(Color.ORANGERED);
                        Resistor5Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa1(3);
                        menuR5Num1.setValue("3");
                        break;
                    case "menur5f2":
                        r5f2.setFill(Color.ORANGERED);
                        Resistor5Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa2(3);
                        menuR5Num2.setValue("3");
                        break;
                    case "menur5f3":
                        r5f3.setFill(Color.ORANGERED);
                        Resistor5Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa3(3);
                        menuR5Num3.setValue("3");
                        break;
                    case "menur5f4":
                        r5f4.setFill(Color.ORANGERED);
                        Resistor5Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor5Data.getInstance().setMult(1000);
                        menuR5Mult.setValue("1000");
                        break;
                }
                break;
            case AMARELO:
                switch (menuName) {
                    case "menur5f1":
                        r5f1.setFill(Color.YELLOW);
                        Resistor5Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa1(4);
                        menuR5Num1.setValue("4");
                        break;
                    case "menur5f2":
                        r5f2.setFill(Color.YELLOW);
                        Resistor5Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa2(4);
                        menuR5Num2.setValue("4");
                        break;
                    case "menur5f3":
                        r5f3.setFill(Color.YELLOW);
                        Resistor5Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa3(4);
                        menuR5Num3.setValue("4");
                        break;
                    case "menur5f4":
                        r5f4.setFill(Color.YELLOW);
                        Resistor5Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor5Data.getInstance().setMult(10000);
                        menuR5Mult.setValue("10000");
                        break;
                }
                break;
            case VERDE:
                switch (menuName) {
                    case "menur5f1":
                        r5f1.setFill(Color.GREEN);
                        Resistor5Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa1(5);
                        menuR5Num1.setValue("5");
                        break;
                    case "menur5f2":
                        r5f2.setFill(Color.GREEN);
                        Resistor5Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa2(5);
                        menuR5Num2.setValue("5");
                        break;
                    case "menur5f3":
                        r5f3.setFill(Color.GREEN);
                        Resistor5Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa3(5);
                        menuR5Num3.setValue("5");
                        break;
                    case "menur5f4":
                        r5f4.setFill(Color.GREEN);
                        Resistor5Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor5Data.getInstance().setMult(100000);
                        menuR5Mult.setValue("100000");
                        break;
                    case "menur5f5":
                        r5f5.setFill(Color.GREEN);
                        Resistor5Data.getInstance().setCorFaixa5(corSelecionada);
                        Resistor5Data.getInstance().setTol(0.5);
                        break;
                }
                break;
            case AZUL:
                switch (menuName) {
                    case "menur5f1":
                        r5f1.setFill(Color.BLUE);
                        Resistor5Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa1(6);
                        menuR5Num1.setValue("6");
                        break;
                    case "menur5f2":
                        r5f2.setFill(Color.BLUE);
                        Resistor5Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa2(6);
                        menuR5Num2.setValue("6");
                        break;
                    case "menur5f3":
                        r5f3.setFill(Color.BLUE);
                        Resistor5Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa3(6);
                        menuR5Num3.setValue("6");
                        break;
                    case "menur5f4":
                        r5f4.setFill(Color.BLUE);
                        Resistor5Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor5Data.getInstance().setMult(1000000);
                        menuR5Mult.setValue("1000000");
                        break;
                    case "menur5f5":
                        r5f5.setFill(Color.BLUE);
                        Resistor5Data.getInstance().setCorFaixa5(corSelecionada);
                        Resistor5Data.getInstance().setTol(0.25);
                        menuR5Num1.setValue("0.25");
                        break;
                }
                break;
            case VIOLETA:
                switch (menuName) {
                    case "menur5f1":
                        r5f1.setFill(Color.VIOLET);
                        Resistor5Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa1(7);
                        menuR5Num1.setValue("7");
                        break;
                    case "menur5f2":
                        r5f2.setFill(Color.VIOLET);
                        Resistor5Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa2(7);
                        menuR5Num2.setValue("7");
                        break;
                    case "menur5f3":
                        r5f3.setFill(Color.VIOLET);
                        Resistor5Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa3(7);
                        menuR5Num3.setValue("7");
                        break;
                    case "menur5f4":
                        r5f4.setFill(Color.VIOLET);
                        Resistor5Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor5Data.getInstance().setMult(10000000);
                        menuR5Mult.setValue("10000000");
                        break;
                    case "menur5f5":
                        r5f5.setFill(Color.VIOLET);
                        Resistor5Data.getInstance().setCorFaixa5(corSelecionada);
                        Resistor5Data.getInstance().setTol(0.1);
                        menuR5Tol.setValue("0.1");
                        break;
                }
                break;
            case CINZA:
                switch (menuName) {
                    case "menur5f1":
                        r5f1.setFill(Color.GREY);
                        Resistor5Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa1(8);
                        menuR5Num1.setValue("8");
                        break;
                    case "menur5f2":
                        r5f2.setFill(Color.GREY);
                        Resistor5Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa2(8);
                        menuR5Num2.setValue("8");
                        break;
                    case "menur5f3":
                        r5f3.setFill(Color.GREY);
                        Resistor5Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa3(8);
                        menuR5Num3.setValue("8");
                        break;
                    case "menur5f5":
                        r5f5.setFill(Color.GREY);
                        Resistor5Data.getInstance().setCorFaixa5(corSelecionada);
                        Resistor5Data.getInstance().setTol(0.05);
                        menuR5Tol.setValue("0.05");
                        break;
                }
                break;
            case BRANCO:
                switch (menuName) {
                    case "menur5f1":
                        r5f1.setFill(Color.WHITE);
                        Resistor5Data.getInstance().setCorFaixa1(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa1(9);
                        menuR5Num1.setValue("9");
                        break;
                    case "menur5f2":
                        r5f2.setFill(Color.WHITE);
                        Resistor5Data.getInstance().setCorFaixa2(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa2(9);
                        menuR5Num2.setValue("9");
                        break;
                    case "menur5f3":
                        r5f3.setFill(Color.WHITE);
                        Resistor5Data.getInstance().setCorFaixa3(corSelecionada);
                        Resistor5Data.getInstance().setNumeroFaixa3(9);
                        menuR5Num3.setValue("9");
                        break;
                }
                break;
            case DOURADO:
                switch (menuName) {
                    case "menur5f4":
                        r5f4.setFill(Color.DARKGOLDENROD);
                        Resistor5Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor5Data.getInstance().setMult(0.1);
                        menuR5Mult.setValue("0.1");
                        break;
                    case "menur5f5":
                        r5f5.setFill(Color.DARKGOLDENROD);
                        Resistor5Data.getInstance().setCorFaixa5(corSelecionada);
                        Resistor5Data.getInstance().setTol(5);
                        menuR5Tol.setValue("5");
                        break;
                }
                break;
            case PRATA:
                switch (menuName) {
                    case "menur5f4":
                        r5f4.setFill(Color.SILVER);
                        Resistor5Data.getInstance().setCorFaixa4(corSelecionada);
                        Resistor5Data.getInstance().setMult(0.01);
                        menuR5Mult.setValue("0.01");
                        break;
                    case "menur5f5":
                        r5f5.setFill(Color.SILVER);
                        Resistor5Data.getInstance().setCorFaixa5(corSelecionada);
                        Resistor5Data.getInstance().setTol(10);
                        menuR5Tol.setValue("10");
                        break;
                }
                break;
        }
        if(corSelecionada.equalsIgnoreCase(BRANCO) || corSelecionada.equalsIgnoreCase(AMARELO) ||
            corSelecionada.equalsIgnoreCase(CINZA) || corSelecionada.equalsIgnoreCase(PRATA)) {
                choiceBox.getStyleClass().setAll("choice-box","itemCorClara");
        } else {
            choiceBox.getStyleClass().setAll("choice-box","itemCorEscura");
        }
        choiceBox.setStyle("-fx-background-color: " + traduzirCor(choiceBox.getValue()) + "; -fx-mark-color: #950005;");
        labelR5.setText(Resistor5Data.getInstance().calcularResistencia());
    }

    private String traduzirCor(String cor) {
        switch (cor) {
            case "PRETO":
                return "black";
            case "MARROM":
                return "saddlebrown";
            case "VERMELHO":
                return "red";
            case "LARANJA":
                return "orangered";
            case "AMARELO":
                return "yellow";
            case "VERDE":
                return "green";
            case "AZUL":
                return "blue";
            case "VIOLETA":
                return "violet";
            case "CINZA":
                return "grey";
            case "BRANCO":
                return "white";
            case "DOURADO":
                return "darkgoldenrod";
            case "PRATA":
                return "silver";
            default:
                return "black";
        }
    }

    public void armazenarResistor(Button botaoArmazenar) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setResizable(false);
        dialog.initOwner((outerTabPane.getScene().getWindow()));
        dialog.setTitle("ARMAZENAR RESISTOR");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("frameArmazenar.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("\n#\tNão foi possível abrir diálogo de detalhes do resistor (armazenar).");
            Alerta.exibirAlertaErro("OPS! ALGO DEU ERRADO!", "Este armazenamento não é permitido. Alterar o valores e tente novamente.");
            return;
        }

        DialogController dialogController = fxmlLoader.getController();

        if ((botaoArmazenar.getId().equalsIgnoreCase("botaoArmazenarR4") && Resistor4Data.getInstance().getResistencia() <= 0) ||
            (botaoArmazenar.getId().equalsIgnoreCase("botaoArmazenarR5") && Resistor5Data.getInstance().getResistencia() <= 0)) {
                Alerta.exibirAlertaErro("OPS! ALGO DEU ERRADO!", "Resistência precisa ser maior que 0 para ser armazenada.", outerTabPane.getScene().getWindow());
                return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            String pot = null;
            int qtd = -1;
            String material = null;
            String tipoPot = null;

            try {
                pot = dialogController.getPotencia();
                qtd = dialogController.getQtd();
                material = dialogController.getMaterial();
                tipoPot = dialogController.getTipoPot();
            } catch (NullPointerException npe) {
                System.out.println("#\tExceção NullPointer. Não pôde criar as variáveis para o novo resistor.");
            } catch (NumberFormatException nfe) {
                System.out.println("#\tExceção NumberFormat. Não pôde criar as variáveis para o novo resistor.");
            }

            if (tipoPot != null)
                material += " - Pot. tipo " + tipoPot.toLowerCase();

            if(pot != null && qtd > 0 && material != null) {
                if (botaoArmazenar.getId().equalsIgnoreCase("botaoArmazenarR4")) {
                    Resistor4Data.getInstance().armazenarResistor(listaComponentes, pot, qtd, material); // Armazena, limpa os valores e reseta as cores para as iniciais
                    resetarMenus();
                } else if (botaoArmazenar.getId().equalsIgnoreCase("botaoArmazenarR5")) {
                    Resistor5Data.getInstance().armazenarResistor(listaComponentes, pot, qtd, material); // Armazena, limpa os valores e reseta as cores para as iniciais
                    resetarMenus();
                }
            } else if (pot == null || qtd < 0 || material == null) {
                Alerta.exibirAlertaErro("OPS! ALGO DEU ERRADO!", "Dica: POTÊNCIA e QUANTIDADE devem conter apenas números e o MATERIAL precisa ser selecionado.", outerTabPane.getScene().getWindow());
            }
        } else {
            System.out.println("\n>\tDiálogo cancelado.");
        }
        imprimirBom();
        bomList.refresh();
    }

    public void imprimirBom () {
        if(!listaComponentes.isEmpty()) {
            System.out.println("LISTA DE COMPONENTES ATUAL: ");
            Iterator iterator = listaComponentes.iterator();
            while (iterator.hasNext()) {
                Resistor res = (Resistor) iterator.next();
                System.out.println(">   " + res.toString());
            }
        } else {
            System.out.println("LISTA DE COMPONENTES VAZIA! ");
        }
    }

    private void resetarMenus() {
        //R4
        Resistor4Data.getInstance().limparValores();
        //Seta cores das faixas dos resistores na imagem
        r4f1.setFill(Color.BLACK);
        r4f2.setFill(Color.BLACK);
        r4f3.setFill(Color.BLACK);
        r4f4.setFill(Color.BLACK);
        //seta cor e formatação dos menus da seção "Quero apenas a resistência"
        menuR4F1.getStyleClass().setAll("choice-box","itemCorEscura");
        menuR4F1.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
        menuR4F1.setValue(" ");
        menuR4F2.getStyleClass().setAll("choice-box","itemCorEscura");
        menuR4F2.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
        menuR4F2.setValue(" ");
        menuR4F3.getStyleClass().setAll("choice-box","itemCorEscura");
        menuR4F3.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
        menuR4F3.setValue(" ");
        menuR4F4.getStyleClass().setAll("choice-box","itemCorEscura");
        menuR4F4.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
        menuR4F4.setValue(" ");

        menuR4Num1.setValue(NENHUMA);
        menuR4Num2.setValue(NENHUMA);
        menuR4Mult.setValue(NENHUMA);
        menuR4Tol.setValue(NENHUMA);

        //R5
        Resistor5Data.getInstance().limparValores();
        r5f1.setFill(Color.BLACK);
        r5f2.setFill(Color.BLACK);
        r5f3.setFill(Color.BLACK);
        r5f4.setFill(Color.BLACK);
        r5f5.setFill(Color.BLACK);
        menuR5F1.getStyleClass().setAll("choice-box","itemCorEscura");
        menuR5F1.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
        menuR5F1.setValue(" ");
        menuR5F2.getStyleClass().setAll("choice-box","itemCorEscura");
        menuR5F2.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
        menuR5F2.setValue(" ");
        menuR5F3.getStyleClass().setAll("choice-box","itemCorEscura");
        menuR5F3.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
        menuR5F3.setValue(" ");
        menuR5F4.getStyleClass().setAll("choice-box","itemCorEscura");
        menuR5F4.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
        menuR5F4.setValue(" ");
        menuR5F5.getStyleClass().setAll("choice-box","itemCorEscura");
        menuR5F5.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
        menuR5F5.setValue(" ");

        menuR5Num1.setValue(NENHUMA);
        menuR5Num2.setValue(NENHUMA);
        menuR5Num3.setValue(NENHUMA);
        menuR5Mult.setValue(NENHUMA);
        menuR5Tol.setValue(NENHUMA);
    }

    public void limparDados(Button botaoLimpar) {
        if(botaoLimpar.getId().equalsIgnoreCase("botaoLimparR4")) {
            Resistor4Data.getInstance().limparValores();
            //Seta cores das faixas dos resistores na imagem
            r4f1.setFill(Color.BLACK);
            r4f2.setFill(Color.BLACK);
            r4f3.setFill(Color.BLACK);
            r4f4.setFill(Color.BLACK);
            //seta cor e formatação dos menus da seção "Quero apenas a resistência"
            menuR4F1.getStyleClass().setAll("choice-box","itemCorEscura");
            menuR4F1.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
            menuR4F1.setValue(" ");
            menuR4F2.getStyleClass().setAll("choice-box","itemCorEscura");
            menuR4F2.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
            menuR4F2.setValue(" ");
            menuR4F3.getStyleClass().setAll("choice-box","itemCorEscura");
            menuR4F3.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
            menuR4F3.setValue(" ");
            menuR4F4.getStyleClass().setAll("choice-box","itemCorEscura");
            menuR4F4.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
            menuR4F4.setValue(" ");

            menuR4Num1.setValue(NENHUMA);
            menuR4Num2.setValue(NENHUMA);
            menuR4Mult.setValue(NENHUMA);
            menuR4Tol.setValue(NENHUMA);
        } else if(botaoLimpar.getId().equalsIgnoreCase("botaoLimparR5")) {
            Resistor5Data.getInstance().limparValores();
            r5f1.setFill(Color.BLACK);
            r5f2.setFill(Color.BLACK);
            r5f3.setFill(Color.BLACK);
            r5f4.setFill(Color.BLACK);
            r5f5.setFill(Color.BLACK);
            menuR5F1.getStyleClass().setAll("choice-box","itemCorEscura");
            menuR5F1.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
            menuR5F1.setValue(" ");
            menuR5F2.getStyleClass().setAll("choice-box","itemCorEscura");
            menuR5F2.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
            menuR5F2.setValue(" ");
            menuR5F3.getStyleClass().setAll("choice-box","itemCorEscura");
            menuR5F3.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
            menuR5F3.setValue(" ");
            menuR5F4.getStyleClass().setAll("choice-box","itemCorEscura");
            menuR5F4.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
            menuR5F4.setValue(" ");
            menuR5F5.getStyleClass().setAll("choice-box","itemCorEscura");
            menuR5F5.setStyle("-fx-background-color: black; -fx-mark-color: #950005;");
            menuR5F5.setValue(" ");

            menuR5Num1.setValue(NENHUMA);
            menuR5Num2.setValue(NENHUMA);
            menuR5Num3.setValue(NENHUMA);
            menuR5Mult.setValue(NENHUMA);
            menuR5Tol.setValue(NENHUMA);
        }

    }

    private void deletarComponente() {
        Componente componenteSelecionado = bomList.getSelectionModel().getSelectedItem();
        String tipoComponente = descobrirTipoComp(componenteSelecionado);
        if (componenteSelecionado != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setResizable(false);
            dialog.initOwner((outerTabPane.getScene().getWindow()));
            dialog.setTitle("DELETAR " + tipoComponente.toUpperCase());

            dialog.setContentText("Tem certeza de que deseja deletar o " + tipoComponente + " de "
                    + ((componenteSelecionado instanceof Resistor) ? ((Resistor)componenteSelecionado).getValor() : ((Capacitor)componenteSelecionado).getValor()) + " da lista?");

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                listaComponentes.remove(componenteSelecionado);
                Alerta.exibirAlertaErro("DEU TUDO CERTO!", "Componente deletado com sucesso!");
                bomList.refresh();
            }
        } else {
            Alerta.exibirAlertaErro("OPS! ALGO DEU ERRADO!", "Nenhum componente selecionado! Tente novamente!");
        }
    }

    private void editarQuantidade(Object componente) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner((outerTabPane.getScene().getWindow()));
        dialog.setTitle("ALTERAR QUANTIDADE");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("alterarQtd.fxml"));
        dialog.setResizable(false);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("\n>\tNão foi possível abrir a janela para ler potência de resistor.\n");
            return;
        }
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController dialogController = fxmlLoader.getController();
            System.out.println("\n>\tOK pressionado.");
            if (componente instanceof Resistor) {
                Resistor resistor = (Resistor) componente;
                int novaQtd = dialogController.alterarQtd();
                resistor.setQtd(novaQtd);
            } else if (componente instanceof Capacitor) {
                Capacitor capacitor = (Capacitor) componente;
                int novaQtd = dialogController.alterarQtd();
                capacitor.setQtd(novaQtd);
            }
        }
        bomList.refresh();
    }

    private String descobrirTipoComp (Object componente) {
        if(componente instanceof  Resistor) {
            return "resistor";
        } else if (componente instanceof Capacitor) {
            return "capacitor";
        }
        return null;
    }

    private void mostrarCores() {
        Componente itemSelecionado = bomList.getSelectionModel().getSelectedItem();
        if(itemSelecionado instanceof Resistor) {
            Resistor resistorSelecionado = (Resistor) itemSelecionado;
            Alerta.exibirInfoCores(("O resistor de " + resistorSelecionado.getValor() + " possui as seguintes cores:"),resistorSelecionado.getCores(),outerTabPane.getScene().getWindow());
        } else {
            Alerta.exibirAlertaErro("Não foi possível obter cores!","O componente selecionado não é um resistor.");
        }
    }

    private void mostrarNomenclatura() {
        Componente itemSelecionado = bomList.getSelectionModel().getSelectedItem();
        if(itemSelecionado instanceof Capacitor) {
            Capacitor capacitorSelecionado = (Capacitor) itemSelecionado;
            Alerta.exibirInfoCores(("O capacitor de " + capacitorSelecionado.getValor() + " possui a nomenclatura "),capacitorSelecionado.getNomenclatura(),outerTabPane.getScene().getWindow());
        } else {
            Alerta.exibirAlertaErro("Não foi possível obter nomenclatura!","O componente selecionado não é um capacitor.");
        }
    }

}
