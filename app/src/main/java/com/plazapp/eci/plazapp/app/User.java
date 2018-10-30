package com.plazapp.eci.plazapp.app;

/**
 * Created by Jeffer on 27/10/2018.
 */

public class User {

    private String userName;
    private String nickName;
    private String email;
    private String id;
    private String rol;
    private int rating;

    public User(String id, String nickName, String userName, String email, String rol, String rating){
        this.id= id;
        this.userName = userName;
        this.nickName = nickName;
        this.email = email;
        this.rol=rol;
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

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getRol() {
        return rol;
    }

    public int getRating() {
        return rating;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
