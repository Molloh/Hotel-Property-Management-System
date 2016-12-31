package presentation.controller;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description
 */
public class Promotion {
    private final SimpleStringProperty promotionName;

    public Promotion(String promotionName) {
        this.promotionName = new SimpleStringProperty(promotionName);
    }

    public SimpleStringProperty promotionNameProperty() {
        return promotionName;
    }
}
