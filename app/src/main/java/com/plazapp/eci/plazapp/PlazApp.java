package com.plazapp.eci.plazapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.plazapp.eci.plazapp.app.App;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Jeffer on 29/10/2018.
 */

public class PlazApp extends AppCompatActivity{

    static final App application = new App();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(PlazApp.this, LoginActivity.class));
    }

    public static final void loginApp(String un, String pswd){
        application.handleLogin(un,pswd);
    }

    public static final void registerUser(String userName, String nick,String email, String rol, String id, String url){
        application.handleRegister(userName,nick,email,rol,id,url);
    }

}
