package com.plazapp.eci.plazapp.back;

/**
 * Created by Jeffer on 4/12/2018.
 */

public class Product {
    private String name;
    private String type;
    private String price;
    private String seller;
    private String buyer;

    public Product(String name, String type, String price, String seller, String buyer) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.seller = seller;
        this.buyer = buyer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}
