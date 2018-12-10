package com.plazapp.eci.plazapp.front;

import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.plazapp.eci.plazapp.R;

import java.util.ArrayList;

/**
 * Created by Jeffer on 4/12/2018.
 */

public class Sale extends AppCompatActivity {

    private static String type,product, measure,quantity, desc, price, term, defaultStringType="Seleccione un tipo", defaultStringProduct="Seleccione un producto", defaultStringMeasure="Seleccione una unidad";
    private static Sale instance;
    private  static AlertDialog registro, cargando, error;
    private static AlertDialog.Builder instanceDialog;
    private static ArrayAdapter<String> empty;
    private String toAdd;

    public void setCargando(){
        AlertDialog.Builder l= new AlertDialog.Builder(this);
        l.setView(R.layout.ligthboxloading);
        cargando=l.create();
        cargando.setCanceledOnTouchOutside(false);
        cargando.setCancelable(false);
    }

    public void getValues(){
        Spinner var =  findViewById(R.id.Typos);
        type = var.getSelectedItem().toString();
        var = findViewById(R.id.product);
        product = var.getSelectedItem().toString();
        var = findViewById(R.id.measure);
        measure = var.getSelectedItem().toString();
        EditText texts = findViewById(R.id.quantity);
        quantity = texts.getText().toString();
        texts = findViewById(R.id.description);
        desc = texts.getText().toString();
        texts = findViewById(R.id.price);
        price = texts.getText().toString();
    }

    public void registered(){
        Spinner var =  findViewById(R.id.Typos);
        var.setSelection(0);
        var = findViewById(R.id.product);
        var.setSelection(0);;
        var = findViewById(R.id.measure);
        var.setSelection(0);
        EditText texts = findViewById(R.id.quantity);
        texts.setText("");
        texts = findViewById(R.id.description);
        texts.setText("");
        texts = findViewById(R.id.price);
        texts.setText("");
        RadioButton rb = findViewById(R.id.fixedPrice);
        rb.setChecked(true);
        rb = findViewById(R.id.noFixed);
        rb.setChecked(false);
    }

    public void createOffert(View v){
        cargando.show();
        getValues();
        boolean invalidData = type.equals(defaultStringType) || product.equals(defaultStringProduct) || measure.equals(defaultStringMeasure) || quantity.isEmpty() || price.isEmpty();
        if (invalidData){
            showPersonalized("Datos invalidos","serciorese de que todos los campos han sido llenados correctamente",R.drawable.alerta);
        }else{
            PlazApp.insertNewOffert(type,product,measure,quantity,desc, price, term);
        }
    }

    public static void showPersonalized(String tittle, String message, int icon){
        completed();
        error.setTitle(tittle);
        error.setMessage(message);
        error.setIcon(icon);
        error.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        error.show();
    }

    private void initialize(){
        ArrayList<String> reset = new ArrayList<>();
        reset.add(defaultStringProduct);
        empty = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, reset);
        empty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resetProductsList();
        instanceDialog = new AlertDialog.Builder(this);
        error = instanceDialog.create();
        setCargando();
        final Spinner spinner = findViewById(R.id.Typos);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (!spinner.getSelectedItem().toString().equals(defaultStringType)) {
                    cargando.show();
                    type = spinner.getSelectedItem().toString();
                    PlazApp.getProductsOfType(type);
                    findViewById(R.id.product).setEnabled(true);
                    findViewById(R.id.addpd).setEnabled(true);
                }else{
                    type="";
                    resetProductsList();
                    findViewById(R.id.product).setEnabled(false);
                    findViewById(R.id.addpd).setEnabled(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        RadioButton fixed = findViewById(R.id.fixedPrice);
        fixed.setChecked(true);
        fixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton noFixed = findViewById(R.id.noFixed);
                noFixed.setChecked(false);
                term = "No negociable";
            }

        });

        fixed = findViewById(R.id.noFixed);
        fixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton noFixed = findViewById(R.id.fixedPrice);
                noFixed.setChecked(false);
                term = "Negociable";
            }
        });
        cargando.show();
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.sales_layout);
        initialize();
        PlazApp.poblateSales();
    }

    public static Sale getInstance(){
        return instance;
    }

    public void resetProductsList(){
        Spinner sItems = findViewById(R.id.product);
        sItems.setAdapter(empty);
    }

    public void poblateTypos(ArrayList<String> typos){
        typos.add(0,defaultStringType);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, typos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.Typos);
        sItems.setAdapter(adapter);
        completed();
    }

    public void poblateProduct(ArrayList<String> products){
        if (products==null) {
            products = new ArrayList<String>();
        }
        products.add(0, defaultStringProduct);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.product);
        sItems.setAdapter(adapter);
        completed();
    }

    public void poblateMeasures(ArrayList<String> measure){
        measure.add(0,defaultStringMeasure);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, measure);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.measure);
        sItems.setAdapter(adapter);
        completed();
    }

    public static void completed(){
        if (cargando.isShowing()) {
            cargando.dismiss();
        }
    }

    private void setAlerDialog(String layout){

        if (layout.equals("t")){
            instanceDialog.setView(R.layout.add_type_layout);
        }else if (layout.equals("p")){;
            instanceDialog.setView(R.layout.newproductlayout);
        }else if (layout.equals("m")){
            instanceDialog.setView(R.layout.newmeasurelayout);
        }
        registro = instanceDialog.create();
    }

    public void addTypo(View v){
        setAlerDialog("t");
        registro.show();
    }

    public void addProduct(View v){
        setAlerDialog("p");
        registro.show();
        TextView text = registro.findViewById(R.id.fl);
        text.setText("Cree un producto de tipo: "+type);
    }

    public void addMeasure(View v){
        setAlerDialog("m");
        registro.show();
    }

    public void newType(View v){
        EditText value = registro.findViewById(R.id.textfield);
        String send = value.getText().toString();
        registro.dismiss();
        cargando.show();
        PlazApp.addTypo(send);
    }

    public void newProduct(View v){
        EditText value = registro.findViewById(R.id.newProduct);
        String send = value.getText().toString();
        registro.dismiss();
        cargando.show();
        PlazApp.addProduct(send,type);
    }

    public void newMeasure(View v){
        EditText value = registro.findViewById(R.id.nameMeasure);
        String send = value.getText().toString();
        value = registro.findViewById(R.id.prefix);
        String pref = value.getText().toString();
        registro.dismiss();
        cargando.show();
        PlazApp.addMeasure(send,pref);
    }

}
