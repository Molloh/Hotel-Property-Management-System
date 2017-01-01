package presentation.controller.unity;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Molloh
 * @version 2016/12/10
 * @description
 */
public class Hotel {
    private final SimpleStringProperty hotelNameWithId;
    private final SimpleStringProperty hotelStar;
    private final SimpleStringProperty hotelPoint;
    private final SimpleStringProperty hotelAddress;
    private final SimpleStringProperty hotelPrice;


    public Hotel(String hotelNameWithId, String hotelStar, String hotelPoint, String hotelAddress, String hotelPrice) {
        this.hotelNameWithId = new SimpleStringProperty(hotelNameWithId);
        this.hotelStar = new SimpleStringProperty(hotelStar);
        this.hotelPoint = new SimpleStringProperty(hotelPoint);
        this.hotelAddress = new SimpleStringProperty(hotelAddress);
        this.hotelPrice = new SimpleStringProperty(hotelPrice);
    }

    public String getName() {
        return hotelNameWithId.get();
    }

    public String getStar() {
        return hotelStar.get();
    }

    public String getPoint() {
        return hotelPoint.get();
    }

    public String getAddress() {
        return hotelAddress.get();
    }

    public String getPrice() {
        return hotelPrice.get();
    }

    public SimpleStringProperty hotelNameProperty() {
        return hotelNameWithId;
    }

    public SimpleStringProperty hotelStarProperty() {
        return hotelStar;
    }

    public SimpleStringProperty hotelPointProperty() {
        return hotelPoint;
    }

    public SimpleStringProperty hotelAddressProperty() {
        return hotelAddress;
    }

    public SimpleStringProperty hotelPriceProperty() {
        return hotelPrice;
    }
}
