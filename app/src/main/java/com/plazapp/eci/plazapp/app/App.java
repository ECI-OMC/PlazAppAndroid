package com.plazapp.eci.plazapp.app;

import com.plazapp.eci.plazapp.app.User;
/**
 * Created by Jeffer on 27/10/2018.
 */

public class App {

    static boolean isLogged;
    static User current;
    static final String defaultFile = "route";

    public App(){
        isLogged=false;
    }

    public boolean statusLogged(){
        return this.isLogged;
    }

    public User currentUser(){
        return this.current;
    }

    public boolean handleLogin(String userName, String password){
        //code to login in the app
        return false;
    }

    public boolean handleRegister(String userName, String nickName, String email, String rol, String id, String urlPhoto){
        //code to register user
        return false;
    }

}
