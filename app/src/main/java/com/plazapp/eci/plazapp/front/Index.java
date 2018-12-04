package com.plazapp.eci.plazapp.front;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.plazapp.eci.plazapp.R;

/**
 * Created by Jeffer on 4/12/2018.
 */

public class Index extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout layout;
    private TextView tittle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.navigation.drawer_index);
        layout = findViewById(R.id.drawer_layout);
        tittle = findViewById(R.id.tittleMenu);
        tittle.setText("Hola "+PlazApp.getNameCurrentUser());
    }

    public void toogle(View v){
        layout.openDrawer(Gravity.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
