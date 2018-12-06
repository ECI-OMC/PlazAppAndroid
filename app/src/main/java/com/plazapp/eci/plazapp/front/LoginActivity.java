package com.plazapp.eci.plazapp.front;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.plazapp.eci.plazapp.R;

public class LoginActivity extends AppCompatActivity {

    private static LoginActivity reference;
    private EditText userName;
    private EditText userPass;
    private TextView register;
    private Button login;
    private String user = "customer";
    private String passUser = "123456";
    private static AlertDialog alertDialog;
    private static Intent index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reference = this;
        index = new Intent(LoginActivity.this, Index.class);
        alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.userN);
        userPass = findViewById(R.id.userPas);
    }

    public void loginSuccesfull(){
        startActivity(index);
    }

    public static void loginFailed(){
        dialog("No se pudo iniciar sesion","Error, el usuario o la contrraseña no son validos");
    }

    private static void dialog (String tittle, String message){
        alertDialog.setTitle(tittle);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public static final LoginActivity getInstance(){
        return reference;
    }

    public void loginUser(View v){
        PlazApp.loginApp(userName.getText().toString(), userPass.getText().toString());
    }

    public void registerUser(View v){
        startActivity(new Intent(LoginActivity.this,RegisterUser.class));
    }


}

