package com.plazapp.eci.plazapp.front;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.plazapp.eci.plazapp.R;
import com.plazapp.eci.plazapp.back.ItemList;

import java.util.ArrayList;

/**
 * Created by Jeffer on 4/12/2018.
 */

public class News extends AppCompatActivity {

    private ArrayList<ItemList> listContent;
    private ListViewAdapter lva;
    private ListView reference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
        initialize();
    }

    private void initialize() {
        listContent = new ArrayList<>();
        listContent.add(new ItemList(R.drawable.verdura,"Cebolla","jefferdcc@gmailcom","1500","2","No negociable"));
        reference = findViewById(R.id.offertsList);
        lva = new ListViewAdapter(this,listContent);
        reference.setAdapter(lva);


    }


}
