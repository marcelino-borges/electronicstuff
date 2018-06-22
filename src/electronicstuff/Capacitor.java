package electronicstuff;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * created by Marcelino on 21/10/2017 at 17:38
 */

public class Capacitor implements Componente {

    private final SimpleDoubleProperty capacitanciaMili = new SimpleDoubleProperty(); // Valor puro da capacitância, sem qualquer formatação
    private final SimpleDoubleProperty capacitanciaMicro = new SimpleDoubleProperty();
    private final SimpleDoubleProperty capacitanciaNano = new SimpleDoubleProperty();
    private final SimpleDoubleProperty capacitanciaPico = new SimpleDoubleProperty();
    private final SimpleStringProperty material = new SimpleStringProperty(); //Eletrolítico, Poliéster, Polipropileno, cerâmico, mica etc
    private final SimpleStringProperty nomenclatura = new SimpleStringProperty();
    private SimpleIntegerProperty qtd = new SimpleIntegerProperty();

    public Capacitor(String unidadeCapacitancia, double capacitancia, String material, String nomenclatura, int qtd) {
        if(unidadeCapacitancia.equalsIgnoreCase("mili")) {
            this.capacitanciaMili.set( capacitancia);
            this.capacitanciaMicro.set(capacitancia/1000);
            this.capacitanciaNano.set( capacitancia/1000000);
            this.capacitanciaPico.set( capacitancia/1000000000);
        } else if (unidadeCapacitancia.equalsIgnoreCase("micro")) {
            this.capacitanciaMili.set( capacitancia*1000);
            this.capacitanciaMicro.set(capacitancia);
            this.capacitanciaNano.set( capacitancia/1000);
            this.capacitanciaPico.set( capacitancia/1000000);
        } else if (unidadeCapacitancia.equalsIgnoreCase("nano")) {
            this.capacitanciaMili.set( capacitancia*1000000);
            this.capacitanciaMicro.set(capacitancia*1000);
            this.capacitanciaNano.set( capacitancia);
            this.capacitanciaPico.set( capacitancia/1000);
        } else if (unidadeCapacitancia.equalsIgnoreCase("pico")) {
            this.capacitanciaMili.set( capacitancia*1000000000);
            this.capacitanciaMicro.set(capacitancia*1000000);
            this.capacitanciaNano.set( capacitancia*1000);
            this.capacitanciaPico.set( capacitancia);
        }
        this.material.set(material);
        this.nomenclatura.set(nomenclatura);
        this.qtd.set(qtd);
    }

    public Capacitor() {}

    /** ------------ GETS E SETS ------------ */

    public String getNomenclatura() {
        return nomenclatura.get();
    }

    public int getQtd() {
        return qtd.get();
    }

    public void setQtd(int qtd) {
        this.qtd.set(qtd);
    }

    public void addQtd(int qtd) {
        this.qtd.set(getQtd() + qtd);
    }

    public String getMaterial() {
        return material.get();
    }

    /** ------------ MÉTODOS ESPECIAIS ------------ */

    public double getCapacitanciaMili() {
        return capacitanciaMili.get();
    }

    public double getCapacitanciaMicro() {
        return capacitanciaMicro.get();
    }

    public double getCapacitanciaNano() {
        return capacitanciaNano.get();
    }

    public double getCapacitanciaPico() {
        return capacitanciaPico.get();
    }

    public String getValor() {
        if(getCapacitanciaMili() >= 1 && getCapacitanciaMili() <= 100)
            return getCapacitanciaMili() + " mF";
        else if(getCapacitanciaMicro() >= 1 && getCapacitanciaMicro() <= 100)
            return  getCapacitanciaMicro() + " µF";
        else if(getCapacitanciaNano() >= 1 && getCapacitanciaNano() <= 100)
            return getCapacitanciaNano() + " nF";
        else if(getCapacitanciaPico() >= 1 && getCapacitanciaPico() <= 100)
            return getCapacitanciaPico() + " pF";
        else
            return null;
    }

    @Override
    public String toString() {
        return "Valor: " + getCapacitanciaMicro() + "μF ou " + getCapacitanciaNano() + "nF ou " + getCapacitanciaPico() + "pF";
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 22;
    }

    @Override
    public boolean equals(Object capacitor) {
        if(this == capacitor)
            return true;

        if((this.getCapacitanciaMili() == ((Capacitor) capacitor).getCapacitanciaMili() ||
                this.getCapacitanciaMicro() == ((Capacitor) capacitor).getCapacitanciaMicro() ||
                this.getCapacitanciaNano() == ((Capacitor) capacitor).getCapacitanciaNano() ||
                this.getCapacitanciaPico() == ((Capacitor) capacitor).getCapacitanciaPico() ||
                this.getNomenclatura().equalsIgnoreCase(((Capacitor) capacitor).getNomenclatura())) && this.getMaterial().equalsIgnoreCase(((Capacitor) capacitor).getMaterial()) )
            return true;

        return false;
    }
}
