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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.plazapp.eci.plazapp.R;

import java.util.ArrayList;

/**
 * Created by Jeffer on 4/12/2018.
 */

public class Sale extends AppCompatActivity {

    private static String type,product, measure,quantity, desc;
    private static Sale instance;
    private  static AlertDialog registro, cargando, error;
    private static AlertDialog.Builder instanceDialog;
    private String toAdd;

    public void setCargando(){
        AlertDialog.Builder l= new AlertDialog.Builder(this);
        l.setView(R.layout.ligthboxloading);
        cargando=l.create();
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
    }


    public static void showPersonalized(String tittle, String message, int icon){
        error.setTitle(tittle);
        error.setMessage(message);
        error.setIcon(icon);
        error.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

    private void initialize(){
        instanceDialog = new AlertDialog.Builder(this);
        error = instanceDialog.create();
        setCargando();
        final Spinner spinner = findViewById(R.id.Typos);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cargando.show();
                if (!spinner.getSelectedItem().toString().equals("Selecciona un tipo")) {
                    type = spinner.getSelectedItem().toString();
                    PlazApp.getProductsOfType(type);
                    findViewById(R.id.product).setEnabled(true);
                    findViewById(R.id.addpd).setEnabled(true);
                }else{
                    type="";
                    findViewById(R.id.product).setEnabled(false);
                    findViewById(R.id.addpd).setEnabled(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
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

    public void poblateTypos(ArrayList<String> typos){
        typos.add(0,"Selecciona un tipo");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, typos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.Typos);
        sItems.setAdapter(adapter);
        cargando.dismiss();
    }

    public void poblateProduct(ArrayList<String> products){
        products.add(0,"Selecciona un producto");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.product);
        sItems.setAdapter(adapter);
        cargando.dismiss();
    }

    public void poblateMeasures(ArrayList<String> measure){
        measure.add(0,"Selecciona una unidad de medida");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, measure);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.measure);
        sItems.setAdapter(adapter);
        cargando.dismiss();
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
        value = registro.findViewById(R.id.textfield2);
        String desc = value.getText().toString();
        registro.dismiss();
        cargando.show();
        PlazApp.addTypo(send);
    }

    public static void completed(){
        cargando.dismiss();
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
