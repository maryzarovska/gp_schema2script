package edu.rit.croatia.iste422.g1.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableRow {
    private final StringProperty name;
    private final StringProperty type;

    public TableRow(String name, String type) {
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }
}
