package com.plazapp.eci.plazapp.front;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.plazapp.eci.plazapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffer on 4/12/2018.
 */

public class Sale extends AppCompatActivity {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_layout);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Verdura");
        spinnerArray.add("Granos");
        spinnerArray.add("Frutas");

        List<String> verdura =  new ArrayList<String>();
        spinnerArray.add("Cebolla");
        spinnerArray.add("Habichuela");
        spinnerArray.add("Coliflor");
        spinnerArray.add("Auyama");

        List<String> fruta =  new ArrayList<String>();
        spinnerArray.add("Mango");
        spinnerArray.add("Coco");
        spinnerArray.add("Manzana");
        spinnerArray.add("Naranja");

        List<String> grano =  new ArrayList<String>();
        spinnerArray.add("Frijol");
        spinnerArray.add("Garbanzo");
        spinnerArray.add("Lenteja");
        spinnerArray.add("Arroz");



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.tipo);
        sItems.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, verdura);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems2= findViewById(R.id.tipo);
        sItems2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, fruta);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems3 = findViewById(R.id.tipo);
        sItems3.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, grano);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems4 = findViewById(R.id.tipo);
        sItems4.setAdapter(adapter4);


    }


}
