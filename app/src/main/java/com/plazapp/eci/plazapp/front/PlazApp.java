package com.plazapp.eci.plazapp.front;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.plazapp.eci.plazapp.R;
import com.plazapp.eci.plazapp.back.Db_Manager;
import com.plazapp.eci.plazapp.back.Offert;

import java.util.ArrayList;

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

    //SECTIONS OF APP
    //LOGIN SECTION

    /**
     * login in the app with un (user Name) and pswd (password)
     * @param un
     * @param pswd
     */
    public static final void loginApp(String un, String pswd){
        Db_Manager.login(un,pswd);
    }
    /**
     * Set the status of the login
     * @param status
     */
    public static void loginStatus(boolean status) {
        if (status){
            LoginActivity.getInstance().loginSuccesfull();
        }else{
            LoginActivity.loginFailed();
        }
    }

    //SIGN UP SECTION
    public static void tryRegister(String name, String email, String pass, String rating) {
        Db_Manager.tryRegister( name, email, pass, rating);
    }
    public static void userExist() {
        RegisterUser.notifyUserExist();
    }
    public static void notifyUserRegistered(boolean status) {
        RegisterUser.notifyFromServer(status);
    }

    //INDEX SECTION
    public static String getNameCurrentUser(){
        return Db_Manager.getCurrent().getUserName();
    }
    public static String getEmailCurrentUser(){
        return Db_Manager.getCurrent().getEmail();
    }

    //SALE SECTION
    public static void poblateSales() {
        Db_Manager.getTypes();
        Db_Manager.getMeasures();
    }
        //Add types of product logic
    public static void addTypo(String send) {
        Db_Manager.addTypo(send);
    }
    public static  void getProductsOfType(String type){
        Db_Manager.getProducts(type);
    }
    public static void notifyChargedTypos(ArrayList<String> typos){
        Sale.getInstance().poblateTypos(typos);
    }
    public static void notifyTypeExist(String typo) {
        Sale.showPersonalized("No se pudo registrar","El tipo "+typo+" ya existe!!",R.drawable.error);
    }
        //Add products logic
    public static void addProduct(String send, String type) {
        Db_Manager.addProduct(send, type);
    }
    public static void notifyChargedProducts(ArrayList<String> populate) {
        Sale.getInstance().poblateProduct(populate);
    }
    public static void notifyProductExist (String p , String t){
        Sale.showPersonalized("No se pudo registrar","El producto "+p+" de tipo: "+t+"ya existe!!",R.drawable.error);
    }
        //Add measure Logic
    public static void addMeasure(String send, String prefix) {
        Db_Manager.addMeasure(send, prefix);
    }
    public static void notifyChargedMeasure(ArrayList<String> populate) {
        Sale.getInstance().poblateMeasures(populate);
    }
    public static void notifyMeasureExist(String send) {
        Sale.showPersonalized("No se pudo registrar","la unidad de medida "+send+" ya existe!!",R.drawable.error);
    }

        //insert offert
    public static void insertNewOffert(String type, String product, String measure, String quantity, String desc, String price, String term) {
        Db_Manager.insertProduct(new Offert(Db_Manager.getCurrent().getEmail(),product,type,measure,quantity,desc, price, term, new ArrayList<String>()));
    }

    public static void savedOffert(boolean ans) {
        if (ans){
            Sale.showPersonalized("Listo!","tu oferta se ha registrado con exito\nlos demas usuarios la podran ver en unos minutos ☺",R.drawable.aceptado);
            Sale.getInstance().registered();
        }else{
            Sale.showPersonalized("Lo lamentamos :(","tu oferta no se publico, intenta de nuevo",R.drawable.error);
        }
    }

    //NEWS SECTION
        //get offerts query
    public static void getOfferts() {
        Db_Manager.getOfferts();
    }

        //notify to view
    public static void notifyChargedOffertsWithoutFilter(ArrayList<Offert> offerts) {
        News.poblateListView(offerts);
    }

    public static void poblateTyposNews(){
        Db_Manager.getTypesNews();
    }
    public static void notifyChargedTyposNews(ArrayList<String> typos){
        //News.getInstance().poblateTypos(typos);
    }

    public static void getProductsOfTypeToNews(String type) {
        Db_Manager.getProductsToNews(type);
    }

    public static void notifyChargedProductsToNews(ArrayList<String> products) {
        //News.getInstance().poblateProductFilter(products);
    }
}
