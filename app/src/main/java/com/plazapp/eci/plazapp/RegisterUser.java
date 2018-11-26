package com.plazapp.eci.plazapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.plazapp.eci.plazapp.app.App;

public class RegisterUser extends AppCompatActivity {
    private String userName;
    private String email;
    private String nick;
    private RadioButton type;
    private String rol;
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
        var = findViewById(R.id.nickImput);
        nick = var.getText().toString();
        if (PlazApp.registerUser(userName,nick,email,rol,"11111","url")){
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

    public void selectType(View v){

        if (findViewById(R.id.Vendedor)==v){
            type = findViewById(R.id.Comprador);
            type.setChecked(false);
            rol = "Vendedor";
        }else if (findViewById(R.id.Comprador)==v){
            type = findViewById(R.id.Vendedor);
            type.setChecked(false);
            rol= "Comprador";
        }
    }

}
