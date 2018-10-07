package vgjavafx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
    private StringProperty anv = new SimpleStringProperty();
    private String los;
    private String konto;
    private IntegerProperty saldo = new SimpleIntegerProperty();


    public Account(){
        anv.set("");
        this.los = "";
        this.konto = "";
        saldo.set(0);
    }

    public String getAnv() {
        return anv.get();
    }

    public void setAnv(String anv) { this.anv.set(anv); }

    public String getLos() {
        return los;
    }

    public void setLos(String los) {
        this.los = los;
    }

    public String getKonto() {
        return konto;
    }

    public void setKonto(String konto) {
        this.konto = konto;
    }

    public int getSaldo() { return saldo.get(); }

    public void setSaldo(int saldo) { this.saldo.set(saldo); }

    public String toString() {
        return getAnv() + ";" + getLos() + ";" + getKonto() + ";" + getSaldo();

    }

    public StringProperty anvProperty() {
        return anv;
    }

    public IntegerProperty saldoProperty() {
        return saldo;
    }
}
