package electronicstuff;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.util.Iterator;

/**
 * created by Marcelino on 17/10/2017 at 21:34
 */

public final class Resistor5Data {

    private SimpleIntegerProperty numeroFaixa1 = new SimpleIntegerProperty();
    private SimpleIntegerProperty numeroFaixa2 = new SimpleIntegerProperty();
    private SimpleIntegerProperty numeroFaixa3 = new SimpleIntegerProperty();
    private SimpleDoubleProperty mult = new SimpleDoubleProperty();
    private SimpleDoubleProperty tol = new SimpleDoubleProperty();
    private SimpleDoubleProperty resistencia = new SimpleDoubleProperty(0);
    private SimpleStringProperty corFaixa1 = new SimpleStringProperty();
    private SimpleStringProperty corFaixa2 = new SimpleStringProperty();
    private SimpleStringProperty corFaixa3 = new SimpleStringProperty();
    private SimpleStringProperty corFaixa4 = new SimpleStringProperty();
    private SimpleStringProperty corFaixa5 = new SimpleStringProperty();

    private static Resistor5Data instance = new Resistor5Data();



    /** ------------ GETS E SETS ------------ */
    public static Resistor5Data getInstance() {
        return instance;
    }

    public void setNumeroFaixa1(int numeroFaixa1) {
        this.numeroFaixa1.set(numeroFaixa1);
    }

    public void setNumeroFaixa2(int numeroFaixa2) {
        this.numeroFaixa2.set(numeroFaixa2);
    }

    public void setNumeroFaixa3(int numeroFaixa3) {
        this.numeroFaixa3.set(numeroFaixa3);
    }

    public double getMult() {
        return mult.get();
    }
    public void setMult(double mult) {
        this.mult.set(mult);
    }

    public double getTol() {
        return tol.get();
    }
    public void setTol(double tol) {
        this.tol.set(tol);
    }

    public double getResistencia() {
        return resistencia.get();
    }
    public void setResistencia(double resistencia) {
        this.resistencia.set(resistencia);
    }

    public String getCorFaixa1() {
        return corFaixa1.get();
    }
    public void setCorFaixa1(String corFaixa1) {
        this.corFaixa1.set(corFaixa1);
    }

    public String getCorFaixa2() {
        return corFaixa2.get();
    }
    public void setCorFaixa2(String corFaixa2) {
        this.corFaixa2.set(corFaixa2);
    }

    public String getCorFaixa3() {
        return corFaixa3.get();
    }
    public void setCorFaixa3(String corFaixa3) {
        this.corFaixa3.set(corFaixa3);
    }

    public String getCorFaixa4() {
        return corFaixa4.get();
    }
    public void setCorFaixa4(String corFaixa4) {
        this.corFaixa4.set(corFaixa4);
    }

    public String getCorFaixa5() {
        return corFaixa5.get();
    }
    public void setCorFaixa5(String corFaixa5) {
        this.corFaixa5.set(corFaixa5);
    }

    /** ------------ MÉTODOS ESPECIAIS ------------ */

    public void armazenarResistor (ObservableList<Componente> listaResistores, String pot, int qtd, String material) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ARMAZENAMENTO");
        Resistor novoResistor = new Resistor(getResistencia(), material, getTol(), pot, qtd, getCorFaixa1(),
                getCorFaixa2(), getCorFaixa3(), getCorFaixa4(), getCorFaixa5());
        boolean localizado = false;
        for (Componente res : listaResistores) {
            Resistor r = (Resistor)res;
            if(r.equals(novoResistor)) {
                localizado = true;
                res.addQtd(qtd);
                alert.setHeaderText("RESISTOR JÁ EXISTE!");
                alert.setContentText("Resistor de " + novoResistor + " já existe na lista, porém foi adicionado mais " + qtd + " dele.");
                alert.showAndWait();
            }
        }
        if(getResistencia() != 0) {
            if(!localizado) {
                listaResistores.add(novoResistor);
                alert.setHeaderText("DEU TUDO CERTO!");
                alert.setContentText("Resistor de " + novoResistor + " armazenado com sucesso! Verificar Lista de Materiais!");
                alert.showAndWait();
            }
        }
        limparValores();
    }

    public Resistor localizarResistor(ObservableList<Componente> listaResistores, Resistor resistor) {
        Iterator<Componente> iterator = listaResistores.iterator();
        while(iterator.hasNext()) {
            Resistor res = (Resistor)iterator.next();
            if(res.equals(resistor)) {
                return res;
            }
        }
        return null;
    }

    public void limparValores() {
        setNumeroFaixa1(0);
        setNumeroFaixa2(0);
        setNumeroFaixa3(0);
        setMult(1);
        setTol(0);
        setResistencia(0);
        setCorFaixa1("PRETO");
        setCorFaixa2("PRETO");
        setCorFaixa3("PRETO");
        setCorFaixa4("PRETO");
        setCorFaixa5("PRETO");
    }

    public String calcularResistencia() {
        String faixas = "";
        if(numeroFaixa1.getValue() > 0)
            faixas += String.valueOf(numeroFaixa1.getValue());
        faixas += String.valueOf(numeroFaixa2.getValue());
        faixas += String.valueOf(numeroFaixa3.getValue());
        long decimais = Long.parseLong(faixas);
        setResistencia(decimais*getMult());

        double novaResistencia = getResistencia();

        if(novaResistencia >= 1000 && novaResistencia < 1000000)
            if((novaResistencia/1000) % (int)(novaResistencia/1000) < 0.1)
                return ((int)(novaResistencia/1000) + "K Ω - " + getTol() + "% Tol.");
            else
                return (novaResistencia/1000 + "K Ω - " + getTol() + "% Tol.");
        else if (novaResistencia >= 1000000)
            if((novaResistencia/1000000) % (int)(novaResistencia/1000000) < 0.1)
                return ((int)(novaResistencia/1000000) + "M Ω - " + getTol() + "% Tol.");
            else
                return (novaResistencia/1000000 + "M Ω - " + getTol() + "% Tol.");
        else if(novaResistencia % (int) novaResistencia < 0.1)
            return (String.format("%d", (int)novaResistencia).replace(',','.') + " Ω - " + getTol() + "% Tol.");
        else
            return (String.format("%.1f", novaResistencia).replace(',','.') + " Ω - " + getTol() + "% Tol.");
    }
}
