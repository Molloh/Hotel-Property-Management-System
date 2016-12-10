package presentation.controller;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Molloh
 * @version 2016/12/10
 * @description
 */
public class Promotion {
    private final SimpleStringProperty orderId;
    private final SimpleStringProperty orderState;
    private final SimpleStringProperty orderPrice;
    private final SimpleStringProperty orderHotel;
    private final SimpleStringProperty orderTime;
    private final SimpleStringProperty orderExeTime;

    public Promotion(String orderId, String orderState, String orderPrice, String orderHotel, String orderTime, String orderExeTime) {
        this.orderId = new SimpleStringProperty(orderId);
        this.orderState = new SimpleStringProperty(orderState);
        this.orderPrice = new SimpleStringProperty(orderPrice);
        this.orderHotel = new SimpleStringProperty(orderHotel);
        this.orderTime = new SimpleStringProperty(orderTime);
        this.orderExeTime = new SimpleStringProperty(orderExeTime);
    }

    public SimpleStringProperty orderIdProperty() {
        return orderId;
    }

    public SimpleStringProperty orderStateProperty() {
        return orderState;
    }

    public SimpleStringProperty orderPriceProperty() {
        return orderPrice;
    }

    public SimpleStringProperty orderHotelProperty() {
        return orderHotel;
    }

    public SimpleStringProperty orderTimeProperty() {
        return orderTime;
    }

    public SimpleStringProperty orderExeTimeProperty() {
        return orderExeTime;
    }
}
