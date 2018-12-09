package com.plazapp.eci.plazapp.back;

import java.util.ArrayList;

/**
 * Created by Jeffer on 8/12/2018.
 */

public class TypeProduct {
    ArrayList<String> products;
    String name;

    public TypeProduct(ArrayList<String> productsOfType, String name) {
        this.products = productsOfType;
        this.name = name;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
