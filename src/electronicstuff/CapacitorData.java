package electronicstuff;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

/**
 * created by Marcelino on 21/10/2017 at 18:32
 */

public class CapacitorData {

    private static CapacitorData instance = new CapacitorData();

    private final SimpleDoubleProperty valor = new SimpleDoubleProperty();
    private final SimpleStringProperty nomenclatura = new SimpleStringProperty();

    /** ------------ GETS E SETS ------------ */

    public static CapacitorData getInstance() {
        return instance;
    }

    public double getValor() {
        return valor.get();
    }

    public void setValor(double valor) {
        this.valor.set(valor);
    }

    public String getNomenclatura() {
        return nomenclatura.get();
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura.set(nomenclatura);
    }

    /** ------------ MÉTODOS ESPECIAIS ------------ */

    public void armazenarCapacitor (List<Capacitor> listaCapacitores, double pot, int qtd) {
    }



}
