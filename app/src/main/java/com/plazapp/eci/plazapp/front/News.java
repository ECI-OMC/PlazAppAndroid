package com.plazapp.eci.plazapp.front;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.plazapp.eci.plazapp.R;
import com.plazapp.eci.plazapp.back.Offert;

import java.util.ArrayList;

/**
 * Created by Jeffer on 4/12/2018.
 */

public class News extends AppCompatActivity {

    private static ArrayList<Offert> listContent;
    private static ListViewAdapter lva;
    private static ListView reference;
    private static News refernce;
    private static AlertDialog cargando;
    private String type;
    private String product;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
        refernce = this;
        initialize();
    }

    public static News getInstance(){
        return refernce;
    }

    private void setCargando(){
        AlertDialog.Builder l= new AlertDialog.Builder(this);
        l.setView(R.layout.ligthboxloading);
        cargando=l.create();
        cargando.setCanceledOnTouchOutside(false);
        cargando.setCancelable(false);
    }

    private void initialize() {
        setCargando();
        cargando.show();
        /**PlazApp.poblateTyposNews();
        //final Spinner filterType = findViewById(R.id.typeFilter);
        //filterType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (!filterType.getSelectedItem().toString().equals("Selecciona un filtro para el tipo")) {
                    cargando.show();
                    type = filterType.getSelectedItem().toString();
                    PlazApp.getProductsOfTypeToNews(type);
                    Spinner prod = findViewById(R.id.product);
                    prod.setSelection(0);
                    prod.setEnabled(true);
                    filterByType();
                }else{
                    type="";
                    Spinner prod = findViewById(R.id.product);
                    prod.setSelection(0);
                    prod.setEnabled(false);
                    noFilter();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        final Spinner filterProd = findViewById(R.id.productFilter);
        filterProd.setEnabled(false);
        filterProd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (!filterProd.getSelectedItem().toString().equals("Selecciona un filtro para producto")) {
                    cargando.show();
                    product = filterProd.getSelectedItem().toString();
                    filterByProduct();
                }else{
                    if(type.equals("")){
                        noFilter();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });**/
        listContent = new ArrayList<>();
        PlazApp.getOfferts();
        reference = findViewById(R.id.offertsList);
        lva = new ListViewAdapter(News.getInstance(),listContent);
    }

    public void filterByType(){

    }

    public void filterByProduct(){

    }

    public void noFilter(){

    }

    public static void poblateListView(ArrayList<Offert> offerts) {
        lva.setContent(offerts);
        reference.setAdapter(lva);
        cargando.dismiss();
    }

    /**
    public void poblateTypos(ArrayList<String> typos) {
        typos.add(0,"Selecciona un filtro para el tipo");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, typos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.typeFilter);
        sItems.setAdapter(adapter);
        cargando.dismiss();
    }

    public void poblateProductFilter(ArrayList<String> products){
        if (products==null) {
            products = new ArrayList<String>();
        }
        products.add(0, "Selecciona un filtro para producto");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.productFilter);
        sItems.setAdapter(adapter);
        cargando.dismiss();
    }**/
}
