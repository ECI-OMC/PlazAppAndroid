package com.plazapp.eci.plazapp;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userPass;
    private TextView register;
    private Button login;
    private String user = "customer";
    private String passUser = "123456";
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.userN);
        userPass = findViewById(R.id.userPas);
    }

    public void loginUser(View v){
        if (userName.getText().toString().equals(user) && userPass.getText().toString().equals(passUser)){
            startActivity(new Intent(LoginActivity.this,UserIndex.class));
        }else{
            alertDialog.setTitle("Usuario o contraseña incorrecta");
            alertDialog.setMessage("el usuario o la contraseña no coinciden...Intente de nuevo");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        userName.setText("");
        userPass.setText("");
    }

    public void registerUser(View v){
        startActivity(new Intent(LoginActivity.this,RegisterUser.class));
    }
}

