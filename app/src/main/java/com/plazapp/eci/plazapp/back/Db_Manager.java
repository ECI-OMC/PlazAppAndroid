package com.plazapp.eci.plazapp.back;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.plazapp.eci.plazapp.front.PlazApp;

import java.util.ArrayList;


/**
 * Created by Jeffer on 4/12/2018.
 */

public class Db_Manager {

    private static final String routeUsers = "users";
    private static final String routeProducts = "products";
    private static final String routeSellXproducts = "sales";
    private static final String routeConsumerXproduct = "consumerXproduct";
    private static final String routeMeasure = "measure";
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef ;
    private static boolean isLogged = false;
    private static User current;


    public static void getTypes(){
        DatabaseReference userNameRef = database.getReference(routeProducts);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> populate = new ArrayList<>();
                if(dataSnapshot.exists()) {
                    Iterable<DataSnapshot> typos = dataSnapshot.getChildren();
                    for (DataSnapshot type : typos){
                        populate.add(type.getKey().toString());
                    }
                }
                PlazApp.notifyChargedTypos(populate);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        userNameRef.addListenerForSingleValueEvent(eventListener);
    }

    public static void getProducts(String typo){
        DatabaseReference userNameRef = database.getReference(routeProducts).child(typo);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> products = (ArrayList<String>) dataSnapshot.child("products").getValue();
                PlazApp.notifyChargedProducts(products);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        userNameRef.addListenerForSingleValueEvent(eventListener);
    }

    public static void getMeasures() {

        DatabaseReference userNameRef = database.getReference(routeMeasure);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> measures = dataSnapshot.getChildren();
                ArrayList<String> populate = new ArrayList<>();
                if(dataSnapshot.exists()) {
                    for (DataSnapshot measure : measures){
                        String var = measure.child("name").getValue().toString() + " (" +measure.child("prefix").getValue().toString()+")";
                        populate.add(var);
                    }
                }
                PlazApp.notifyChargedMeasure(populate);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        userNameRef.addListenerForSingleValueEvent(eventListener);
    }

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

    public static void login(String email, final String pswd){
        myRef = database.getReference(routeUsers);
        final DatabaseReference userNameRef = myRef.child(emailFlat(email));
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean ans = false;
                if(dataSnapshot.exists()) {
                    String userName = dataSnapshot.child("userName").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();
                    String rating = dataSnapshot.child("rating").getValue().toString();
                    String pass = dataSnapshot.child("pass").getValue().toString();
                    ans = pswd.equals(pass);
                    if (ans){
                        current = new User(userName,email,rating,pass);
                    }
                }
                PlazApp.loginStatus(ans);
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

    public static void insertProduct(Offert product){
        String offerter = emailFlat(product.getOfferter());
        myRef = database.getReference(routeSellXproducts).child(offerter);
        String id = myRef.push().getKey();
        myRef.child(id).setValue(product);
        verifyRegisteredOffert(id, offerter, product);
    }

    private static void verifyRegisteredOffert(String id, String user, final Offert current) {
        DatabaseReference offertReference = database.getReference(routeSellXproducts).child(user).child(id);
        offertReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean ans = false;
                if (dataSnapshot.exists()){
                    String offerter = dataSnapshot.child("offerter").getValue().toString();
                    String product = dataSnapshot.child("product").getValue().toString();
                    String quantity = dataSnapshot.child("quantity").getValue().toString();
                    String type = dataSnapshot.child("type").getValue().toString();
                    String unitMeasure = dataSnapshot.child("unitMeasure").getValue().toString();
                    String description = dataSnapshot.child("description").getValue().toString();
                    ans = current.isOffert(offerter,product,type,unitMeasure,quantity,description);
                }
                PlazApp.savedOffert(ans);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }});
    }

    private static void getOfferts(){
        DatabaseReference offertsReference = database.getReference(routeSellXproducts);
        ValueEventListener ev = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Offert> offerts = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    Iterable<DataSnapshot> dataOfferts = dataSnapshot.getChildren();
                    for (DataSnapshot offert : dataOfferts){
                        System.out.println("=================> "+offert.getKey());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        offertsReference.addListenerForSingleValueEvent(ev);
    }

    public static User getCurrent(){
        return current;
    }

    public static void addTypo(final String send) {
        final DatabaseReference typeRef = database.getReference(routeProducts).child(send);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> populate = new ArrayList<>();
                if(!dataSnapshot.exists()) {
                    typeRef.setValue(new TypeProduct( new ArrayList<String>(),send));
                    getTypes();
                }else{
                    PlazApp.notifyTypeExist(send);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        typeRef.addListenerForSingleValueEvent(eventListener);
    }

    public static void addProduct(final String send,  final String typo) {
        final DatabaseReference productRef = database.getReference(routeProducts).child(typo);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> products;
                if (dataSnapshot.exists() && dataSnapshot.child("products").getValue()!=null) {
                     products = (ArrayList<String>) dataSnapshot.child("products").getValue();
                }else{
                    products = new ArrayList<>();
                }
                if (!products.contains(send)){
                    products.add(send);
                    productRef.child("products").setValue(products);
                    getProducts(typo);
                }else{
                    PlazApp.notifyProductExist(send,typo);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        productRef.addListenerForSingleValueEvent(eventListener);

    }

    public static void addMeasure(final String send, final String prefix) {
        final DatabaseReference measureRef = database.getReference(routeProducts).child(send);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    measureRef.setValue(new Measure(send,prefix));
                    getTypes();
                }else{
                    PlazApp.notifyMeasureExist(send);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        measureRef.addListenerForSingleValueEvent(eventListener);

        getMeasures();

    }

    private static void exist(){}

}
