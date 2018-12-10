package com.plazapp.eci.plazapp.front;

import android.content.Intent;
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

    private TextView tittle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        tittle = findViewById(R.id.tittleMenu);
        tittle.setText("Hola "+PlazApp.getNameCurrentUser().split(" ")[0]);
        setSideNavBar();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setSideNavBar(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername =  headerView.findViewById(R.id.nameUser);
        navUsername.setText(PlazApp.getNameCurrentUser());
        TextView navEmailUser = headerView.findViewById(R.id.emailUser);
        navEmailUser.setText(PlazApp.getEmailCurrentUser());
    }

    public void toogle(View v){
        DrawerLayout layout = findViewById(R.id.drawer_layout);
        layout.openDrawer(Gravity.START);
    }

    @Override
    public void onBackPressed (){

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void vender(View v){
        startActivity(new Intent(Index.this, Sale.class));
    }

    public void navega(View v){
        startActivity(new Intent(Index.this, News.class));
    }

    public void info(View v){
        startActivity(new Intent(Index.this, Index.class));

    }

    public void news(View v){
        startActivity(new Intent(Index.this, News.class));
    }

}
