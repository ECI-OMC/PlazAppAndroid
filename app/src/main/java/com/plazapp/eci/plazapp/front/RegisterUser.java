package com.plazapp.eci.plazapp.front;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.plazapp.eci.plazapp.R;

public class RegisterUser extends AppCompatActivity {
    private String userName;
    private String email;
    private String pass;
    private String photoSrc;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        alertDialog = new AlertDialog.Builder(RegisterUser.this).create();

    }

    private boolean validateUserInfo(String n, String e, String k){
        boolean ans = e.contains("@");
        return ans;
    }

    public void goBack (View v){
        finish();
    }

    public void registrar(View v){
        EditText var = findViewById(R.id.nameImput);
        userName = var.getText().toString();
        var= findViewById(R.id.emailImput);
        email = var.getText().toString();
        var = findViewById(R.id.passInput);
        pass = var.getText().toString();
        if (PlazApp.registerUser(userName,email,"11111","url", pass)){
            alertDialog.setTitle("Registro Exitoso");
            alertDialog.setMessage("Se ha registrado satisfactoriamente");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
            alertDialog.show();
        }else{
            alertDialog.setTitle("Datos de Usuario incorrectos");
            alertDialog.setMessage("el email, o nick requerido no esta disponible");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

    }

}
