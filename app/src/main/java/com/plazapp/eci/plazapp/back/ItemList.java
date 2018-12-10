package com.plazapp.eci.plazapp.back;

/**
 * Created by Jeffer on 10/12/2018.
 */

public class ItemList {
    private int image;
    private String tittle;
    private String offerter;
    private String price;
    private String quantity;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    private String term;

    public ItemList(int image, String tittle, String offerter, String price, String quantity, String term) {
        this.image = image;
        this.tittle = tittle;
        this.offerter = offerter;
        this.price = price;
        this.quantity = quantity;
        this.term = term;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getOfferter() {
        return offerter;
    }

    public void setOfferter(String offerter) {
        this.offerter = offerter;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
