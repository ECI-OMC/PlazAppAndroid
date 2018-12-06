package com.plazapp.eci.plazapp.front;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.plazapp.eci.plazapp.R;
import com.plazapp.eci.plazapp.back.Db_Manager;

/**
 * Created by Jeffer on 29/10/2018.
 * esta es la que se crea de primeras
 */

public class PlazApp extends AppCompatActivity{

    private static Intent la;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plazapp_layout);
        la = new Intent(PlazApp.this,LoginActivity.class);
    }

    @Override
    protected void onStart(){
        super.onStart();
        startActivity(la);
    }

    public static final void loginApp(String un, String pswd){
        Db_Manager.login(un,pswd);
    }

    public static final void registerUser(String userName,String email, String rating, String pass){
        Db_Manager.insertUser(userName,email,rating,pass);
    }

    public static String getNameCurrentUser(){
        return Db_Manager.getCurrent().getUserName();
    }

    public static String getEmailCurrentUser(){
        return Db_Manager.getCurrent().getEmail();
    }

    public static void notifyUserRegistered(boolean status) {
        RegisterUser.notifyFromServer(status);
    }


    public static void tryRegister(String name, String email, String pass, String rating) {
        Db_Manager.tryRegister( name, email, pass, rating);
    }

    public static void userExist() {
        RegisterUser.notifyUserExist();
    }

    public static void loginStatus(boolean status) {
        if (status){
            LoginActivity.getInstance().loginSuccesfull();
        }else{
            LoginActivity.loginFailed();
        }
    }
}
