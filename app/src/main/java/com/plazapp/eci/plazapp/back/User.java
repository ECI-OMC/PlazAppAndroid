package com.plazapp.eci.plazapp.back;

/**
 * Created by Jeffer on 27/10/2018.
 */

public class User {

    private String userName;
    private String email;
    private int rating;
    private String pass;
    private String url;

    public User(String userName, String email, String rating, String pass, String url){
        this.userName = userName;
        this.email = email;
        this.pass = pass;
        this.url = url;
        this.rating= intValueOf(rating);
    }

    private int intValueOf(String p){
        int ans;
        try{
            ans = Integer.parseInt(p);
        }catch(Exception e){
            ans= 0;
        }
        return ans;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public int getRating() {
        return rating;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean correctPass(String pswd) {
        return this.pass.equals(pswd);
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String toString(){
        return ("Name: "+this.userName+"\n"+"Email: "+this.email+"\n"+"Pass: "+this.pass);
    }
}
