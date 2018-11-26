package com.plazapp.eci.plazapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.plazapp.eci.plazapp.app.App;

import java.util.Timer;
import java.util.TimerTask;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Jeffer on 29/10/2018.
 */

public class PlazApp extends AppCompatActivity{

    public static final App application = new App();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plazapp_layout);
    }

    @Override
    protected void onStart(){
        super.onStart();
            startActivity(new Intent(PlazApp.this, LoginActivity.class));

    }

    public static final boolean loginApp(String un, String pswd){
        return application.handleLogin(un,pswd);
    }

    public static final boolean registerUser(String userName, String nick,String email, String rol, String id, String url){
        return application.handleRegister(userName,nick,email,rol,id,url);
    }


}
