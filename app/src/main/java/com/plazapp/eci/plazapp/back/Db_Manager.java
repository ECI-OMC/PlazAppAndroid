package com.plazapp.eci.plazapp.back;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.plazapp.eci.plazapp.front.PlazApp;

import java.lang.reflect.Array;

/**
 * Created by Jeffer on 4/12/2018.
 */

public class Db_Manager {

    private static final String routeUsers = "users";
    private static final String routeProducts = "products";
    private static final String routeSellXproducts = "userXproduct";
    private static final String routeConsumerXproduct = "consumerXproduct";
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef ;
    private static boolean isLogged = false;
    private static User current;


    public static void insertUser(String userName, String email, String rating, String pass){
        User user = new User(userName, email, rating, pass);
        myRef = database.getReference(routeUsers);
        myRef.child(emailFlat(email)).setValue(user);
        verifyIsRegistered(emailFlat(email));
    }

    public static void tryRegister( String un, String email, String pswd, String rting){
        final String userName=un, em=email, pass=pswd, rating=rting;
        DatabaseReference userNameRef = database.getReference(routeUsers).child(emailFlat(email));
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    PlazApp.userExist();
                }else{
                   insertUser(userName,em,rating,pass);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        userNameRef.addListenerForSingleValueEvent(eventListener);
    }


    private static void verifyIsRegistered(String email) {
        DatabaseReference userNameRef = myRef.child(email);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    PlazApp.notifyUserRegistered(true);
                }else{
                    PlazApp.notifyUserRegistered(false);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        userNameRef.addListenerForSingleValueEvent(eventListener);
    }


    private static String emailFlat(String email){
        String[] ef = email.split("\\.");
        return ef[0]+ef[1];
    }

    public static void insertProduct(Product product){
        myRef = database.getReference(routeProducts);
    }

    public static void insertSale(User buyer, User seller, Product product){
        //
    }

    public static void login(String email, String pswd){
        DatabaseReference userNameRef = database.getReference(routeUsers).child(emailFlat(email));
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {

                    PlazApp.userExist();
                }else{
                    PlazApp.notifyFromServer(false);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        userNameRef.addListenerForSingleValueEvent(eventListener);
    }

    private static boolean userExist(String email){
        return false;
    }

    public static boolean isIsLogged(){
        return isLogged;
    }

    public static User getCurrent(){
        return current;
    }

}
