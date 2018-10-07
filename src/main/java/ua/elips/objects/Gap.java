package ua.elips.objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Gap {

    private final IntegerProperty id;
    private final StringProperty x;
    private final StringProperty y;
    private final StringProperty d;
    private final StringProperty a;
    private final StringProperty dd;
    private final StringProperty db;

 //* Пустой конструктор.
    public Gap () {
        this.id = new SimpleIntegerProperty(0);
        this.x = new SimpleStringProperty("");
        this.y = new SimpleStringProperty("");
        this.d = new SimpleStringProperty("");
        this.a = new SimpleStringProperty("");
        this.dd = new SimpleStringProperty("");
        this.db = new SimpleStringProperty("");
    }

//Констрултор
    public Gap(Integer id, String x, String y, String d, String a, String dd, String db) {
        this.id = new SimpleIntegerProperty(id);
        this.x = new SimpleStringProperty(x);
        this.y = new SimpleStringProperty(y);
        this.d = new SimpleStringProperty(d);
        this.a = new SimpleStringProperty(a);
        this.dd = new SimpleStringProperty(dd);
        this.db = new SimpleStringProperty(db);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getX() {
        return x.get();
    }

    public StringProperty xProperty() {
        return x;
    }

    public void setX(String x) {
        this.x.set(x);
    }

    public String getY() {
        return y.get();
    }

    public StringProperty yProperty() {
        return y;
    }

    public void setY(String y) {
        this.y.set(y);
    }

    public String getD() {
        return d.get();
    }

    public StringProperty dProperty() {
        return d;
    }

    public void setD(String d) {
        this.d.set(d);
    }

    public String getA() {
        return a.get();
    }

    public StringProperty aProperty() {
        return a;
    }

    public void setA(String a) {
        this.a.set(a);
    }

    public String getDd() {
        return dd.get();
    }

    public StringProperty ddProperty() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd.set(dd);
    }

    public String getDb() {
        return db.get();
    }

    public StringProperty dbProperty() {
        return db;
    }

    public void setDb(String db) {
        this.db.set(db);
    }

}
