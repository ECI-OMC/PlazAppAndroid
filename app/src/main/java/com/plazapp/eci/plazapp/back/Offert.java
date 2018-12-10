package com.plazapp.eci.plazapp.back;

import java.util.ArrayList;

/**
 * Created by Jeffer on 8/12/2018.
 */

public class Offert {
    private String offerter;
    private String product;
    private String type;
    private String unitMeasure;
    private int quantity;
    private int price;
    private String description;
    private String term;
    private ArrayList<String> interested;

    public Offert(String offerter, String product, String type, String unitMeasure, String quantity,String description, String price, String term, ArrayList<String> interested) {
        this.offerter = offerter;
        this.product = product;
        this.type = type;
        this.unitMeasure = unitMeasure;
        this.description = description;
        this.interested = interested;
        this.quantity = toInt(quantity);
        this.price = toInt(price);
        this.term = term;
    }
    
    private int toInt(String value){
        int ans;
        try {
            ans = Integer.parseInt(value);
        }catch (Exception e){
            ans = 0;
        }
        return ans;
    }

    public String getOfferter() {
        return offerter;
    }

    public void setOfferter(String offerter) {
        this.offerter = offerter;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getInterested() {
        return interested;
    }

    public void setInterested(ArrayList<String> interested) {
        this.interested = interested;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isOffert(String offerter, String product, String type, String unitMeasure, String quantity,String description){
        return (this.offerter.equals(offerter) && this.product.equals(product) && this.type.equals(type) && this.unitMeasure.equals(unitMeasure) && this.quantity==toInt(quantity) && this.description.equals(description));
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
