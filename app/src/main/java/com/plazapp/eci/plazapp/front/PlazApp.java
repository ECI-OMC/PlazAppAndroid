package com.plazapp.eci.plazapp.front;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.plazapp.eci.plazapp.R;
import com.plazapp.eci.plazapp.back.App;

/**
 * Created by Jeffer on 29/10/2018.
 * esta es la que se crea de primeras
 */

public class PlazApp extends AppCompatActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.initialize();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plazapp_layout);
    }

    @Override
    protected void onStart(){
        super.onStart();
            startActivity(new Intent(PlazApp.this, LoginActivity.class));
    }

    public static final boolean loginApp(String un, String pswd){
        boolean ans = App.handleLogin(un,pswd);
        return ans;
    }

    public static final boolean registerUser(String userName,String email, String rating, String url, String pass){
        return App.handleRegister(userName,email,rating,pass, url);
    }

    public static String getNameCurrentUser(){
        return App.currentUser().getUserName();
    }

}
