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

    private void dialog (String tittle, String message){
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


    public void loginUser(View v){
        boolean cond = PlazApp.loginApp(userName.getText().toString(), userPass.getText().toString());
        if (cond){
            startActivity(new Intent(LoginActivity.this, Index.class));
        }else{
            dialog("No se pudo iniciar Sesión","Los datos ingresados no son validos");
        }
        userName.setText("");
        userPass.setText("");
    }

    public void registerUser(View v){
        startActivity(new Intent(LoginActivity.this,RegisterUser.class));
    }

}

