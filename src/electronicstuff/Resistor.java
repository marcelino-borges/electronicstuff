package electronicstuff;

import javafx.beans.property.*;

/**
 * created by Marcelino on 17/10/2017 at 20:34
 */

public class Resistor implements Componente {

    private final SimpleStringProperty valor = new SimpleStringProperty(); // Valor da resistência formatada com k, M, símbolo do Ohms
    private final SimpleStringProperty material = new SimpleStringProperty();
    private final SimpleDoubleProperty resistencia = new SimpleDoubleProperty(); // Valor puro da resistência, sem qualquer formatação
    private final SimpleDoubleProperty tolerancia = new SimpleDoubleProperty();
    private final SimpleStringProperty potencia = new SimpleStringProperty();
    private final SimpleStringProperty corFaixa1 = new SimpleStringProperty();
    private final SimpleStringProperty corFaixa2 = new SimpleStringProperty();
    private final SimpleStringProperty corFaixa3 = new SimpleStringProperty();
    private final SimpleStringProperty corFaixa4 = new SimpleStringProperty();
    private final SimpleStringProperty corFaixa5 = new SimpleStringProperty();
    private SimpleIntegerProperty qtd = new SimpleIntegerProperty();

    /** RESISTOR 4 BANDAS */
    public Resistor(double resistencia, String material, double tolerancia, String potencia, int qtd,
                    String corFaixa1, String corFaixa2, String corFaixa3, String corFaixa4) {
        if(resistencia >= 1000 && resistencia < 1000000)
            if((resistencia/1000) % (int)(resistencia/1000) < 0.1)
                this.valor.set(((int)(resistencia/1000) + "K Ω"));
            else
                this.valor.set((resistencia/1000 + "K Ω "));
        else if (resistencia >= 1000000)
            if((resistencia/1000000) % (int)(resistencia/1000000) < 0.1)
                this.valor.set(((int)(resistencia/1000000) + "M Ω"));
            else
                this.valor.set((resistencia/1000000 + "M Ω"));
        else if(resistencia % (int) resistencia < 0.1)
            this.valor.set((String.format("%d", (int) resistencia).replace(',','.') + " Ω"));
        else
            this.valor.set((String.format("%.1f", resistencia).replace(',','.') + " Ω"));

        this.material.set(material);
        this.resistencia.set(resistencia);
        this.tolerancia.set(tolerancia);
        this.potencia.set(potencia);
        this.qtd.set(qtd);
        this.corFaixa1.set(corFaixa1);
        this.corFaixa2.set(corFaixa2);
        this.corFaixa3.set(corFaixa3);
        this.corFaixa4.set(corFaixa4);
        this.corFaixa5.set(null);
    }

    /** RESISTOR 5 BANDAS */
    public Resistor(double valor, String material, double tolerancia, String potencia, int qtd,
                    String corFaixa1, String corFaixa2, String corFaixa3,
                    String corFaixa4, String corFaixa5) {

        if(valor >= 1000 && valor < 1000000)
            if((valor/1000) % (int)(valor/1000) < 0.1)
            this.valor.set(((int)(valor/1000) + "K Ω"));
            else
                this.valor.set((valor/1000 + "K Ω "));
        else if (valor >= 1000000)
            if((valor/1000000) % (int)(valor/1000000) < 0.1)
                this.valor.set(((int)(valor/1000000) + "M Ω"));
            else
                this.valor.set((valor/1000000 + "M Ω"));
        else if(valor % (int) valor < 0.1)
            this.valor.set((String.format("%d", (int) valor).replace(',','.') + " Ω"));
        else
            this.valor.set((String.format("%.1f", valor).replace(',','.') + " Ω"));

        this.material.set(material);
        this.resistencia.set(valor);
        this.tolerancia.set(tolerancia);
        this.potencia.set(potencia);
        this.qtd.set(qtd);
        this.corFaixa1.set(corFaixa1);
        this.corFaixa2.set(corFaixa2);
        this.corFaixa3.set(corFaixa3);
        this.corFaixa4.set(corFaixa4);
        this.corFaixa5.set(corFaixa5);
    }
    /** PADRÃO */
    public Resistor() {
    }

    /** ------------ GETS E SETS ------------ */
    public String getValor() {
        return valor.get();
    }

    public double getResistencia() {
        return resistencia.get();
    }

    public double getTolerancia() {
        return tolerancia.get();
    }

    public String getPotencia() {
        return potencia.get();
    }

    public String getMaterial() {
        return material.get();
    }

    public int getQtd() {
        return qtd.get();
    }

    @Override
    public void addQtd(int qtd) {
        this.qtd.set(getQtd() + qtd);
    }

    public void setQtd(int qtd) {
        this.qtd.set(qtd);
    }

    public String getCorFaixa1() {
        return corFaixa1.get();
    }
    public String getCorFaixa2() {
        return corFaixa2.get();
    }
    public String getCorFaixa3() {
        return corFaixa3.get();
    }
    public String getCorFaixa4() {
        return corFaixa4.get();
    }
    public String getCorFaixa5() {
        return corFaixa5.get();
    }


    /** ------------ MÉTODOS ESPECIAIS ------------ */

    public String getCores() {
        String cores = getCorFaixa1().toUpperCase() + " - " + getCorFaixa2().toUpperCase() + " - " +
                getCorFaixa3().toUpperCase() + " - " + getCorFaixa4().toUpperCase() +
               ((getCorFaixa5() != null) ? (  " - " + getCorFaixa5().toUpperCase()) : "");

        return cores;
    }

    @Override
    public String toString() {
        if(this.resistencia.get() >= 1000 && this.resistencia.get() < 1000000)
            if((this.resistencia.get()/1000) % (int)(this.resistencia.get()/1000) < 0.1)
                return ((int)(this.resistencia.get()/1000) + "K Ω - " + getTolerancia() + "% tol - " + getPotencia() + "W - " + getMaterial());
            else
                return (this.resistencia.get()/1000 + "K Ω - " + getTolerancia() + "% tol - " + getPotencia() + "W - " + getMaterial());
        else if (this.resistencia.get() >= 1000000)
            if((this.resistencia.get()/1000000) % (int)(this.resistencia.get()/1000000) < 0.1)
                return ((int)(this.resistencia.get()/1000000) + "M Ω - " + getTolerancia() + "% tol - " + getPotencia() + "W - " + getMaterial());
            else
                return (this.resistencia.get()/1000000 + "M Ω - " + getTolerancia() + "% tol - " + getPotencia() + "W - " + getMaterial());
        else
        if(this.resistencia.get() % (int) this.resistencia.get() < 0.1)
            return (String.format("%d", (int) this.resistencia.get()).replace(',','.') + " Ω - " + getTolerancia() + "% tol - " + getPotencia() + "W - " + getMaterial());
        else
            return (String.format("%.1f", this.resistencia.get()).replace(',','.') + " Ω - " + getTolerancia() + "% tol - " + getPotencia() + "W - " + getMaterial());
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 11;
    }

    @Override
    public boolean equals(Object resistor) {
        if(this == resistor)
            return true;

        if(this.getResistencia() == ((Resistor)resistor).getResistencia() &&
           this.getPotencia().equalsIgnoreCase(((Resistor)resistor).getPotencia()) &&
           this.getTolerancia()  == ((Resistor)resistor).getTolerancia() &&
           this.getMaterial().equalsIgnoreCase(((Resistor)resistor).getMaterial()))
                return true;

        return false;
    }
}
