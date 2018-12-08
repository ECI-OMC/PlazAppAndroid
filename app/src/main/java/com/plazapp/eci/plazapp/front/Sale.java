package com.plazapp.eci.plazapp.front;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
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
    private  static AlertDialog registro, cargando;
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




    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.sales_layout);
        PlazApp.poblateSales();
    }

    public static Sale getInstance(){
        return instance;
    }

    public void poblateTypos(ArrayList<String> typos){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, typos);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.Typos);
        sItems.setAdapter(adapter);
    }

    public void poblateProduct(ArrayList<String> products){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.product);
        sItems.setAdapter(adapter);
    }

    public void poblateMeasures(ArrayList<String> measure){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, measure);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.measure);
        sItems.setAdapter(adapter);
    }

    private void setAlerDialog(String layout){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = (size.y);
        View v;
        AlertDialog.Builder l= new AlertDialog.Builder(this);

        if (layout.equals("t")){
            v= getLayoutInflater().inflate(R.layout.addlayout, null);
            TextView tiitle = v.findViewById(R.id.tittle);
            tiitle.setText("Registra una nueva clase de producto");
            l.setView(R.layout.addlayout);

        }else if (layout.equals("p")){
            v = getLayoutInflater().inflate(R.layout.newproductlayout, null);
            TextView tiitle = v.findViewById(R.id.tittle);
            tiitle.setText("Registra un nuevo producto");
            l.setView(R.layout.newproductlayout);

        }else{
            v = getLayoutInflater().inflate(R.layout.newmeasurelayout, null);
            TextView tiitle = v.findViewById(R.id.tittle);
            tiitle.setText("Registra una nueva unidad de medida");
            l.setView(R.layout.newmeasurelayout);

        }
        registro = l.create();
        registro.getWindow().setLayout(width, height);
    }


    public void addTypo(View v){
        setAlerDialog("t");
        registro.show();
    }

    public void addProduct(View v){
        setAlerDialog("p");
        registro.show();
    }

    public void addMeasure(View v){
        setAlerDialog("m");
        registro.show();
    }

    public void newType(View v){
        registro.dismiss();
        View vm = getLayoutInflater().inflate(R.layout.addlayout, null);
        EditText value = vm.findViewById(R.id.textfield);
        String send = value.getText().toString();
        PlazApp.addTypo(send);
    }

    public void newProduct(View v){
        registro.dismiss();
        View vm = getLayoutInflater().inflate(R.layout.newproductlayout, null);
        EditText value = vm.findViewById(R.id.newProduct);
        String send = value.getText().toString();
        Spinner var = findViewById(R.id.types);
        String type = "";
        if (var.getSelectedItem().toString().length()<2){
            type="verdura";
        }else{
            type=var.getSelectedItem().toString();
        }
        PlazApp.addProduct(send,type);
    }

    public void newMeasure(View v){
        registro.dismiss();
        View vm = getLayoutInflater().inflate(R.layout.newmeasurelayout, null);
        EditText value = vm.findViewById(R.id.textfield);
        String send = value.getText().toString();
        PlazApp.addMeasure(send);
    }

}
