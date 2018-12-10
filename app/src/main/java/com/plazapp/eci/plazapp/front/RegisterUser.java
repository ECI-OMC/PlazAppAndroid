package com.plazapp.eci.plazapp.front;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.plazapp.eci.plazapp.R;

public class RegisterUser extends AppCompatActivity {
    private static EditText userName,email,pass,pass2;
    private static TextView alertIncorrectPass, alertIncorrectPassL;
    private String photoSrc;
    private static AlertDialog alertDialog, cargando;
    private String dialogTittle;
    private String dialogMessage;
    private static boolean close;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initialize(){
        alertDialog = new AlertDialog.Builder(RegisterUser.this).create();
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(close){
                            finish();
                        }
                    }
                });

        alertIncorrectPass = findViewById(R.id.informer);
        alertIncorrectPassL = findViewById(R.id.informerPass);
        pass2=findViewById(R.id.passInput2);
        pass=findViewById(R.id.passInput);
        userName = findViewById(R.id.nameInput);
        email = findViewById(R.id.emailInput);
        pass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!pass.getText().toString().equals(pass2.getText().toString())){
                    alertIncorrectPass.setText("las contraseñas no coinciden *");
                    alertIncorrectPass.setTextColor(Color.RED);
                }else{
                    alertIncorrectPass.setText("Las contraseñas coinciden");
                    alertIncorrectPass.setTextColor(Color.GREEN);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(pass.getText().toString().length()<5){
                    alertIncorrectPassL.setText("la contraseña debe contener al menos 6 caracteres");
                    alertIncorrectPassL.setTextColor(Color.RED);
                }else{
                    alertIncorrectPassL.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialogMessage="";
        dialogTittle="";
        createLigthBox();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createLigthBox(){
        AlertDialog.Builder lightBox = new AlertDialog.Builder(this);
        lightBox.setView(R.layout.ligthboxloading);
        cargando = lightBox.create();
        cargando.setCanceledOnTouchOutside(false);
        cargando.setCancelable(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initialize();

    }

    private boolean validateUserInfo(String name, String e, String pass){
        boolean ans=true;
        if (!e.contains("@") || !e.contains(".")){
            dialogTittle="Datos invalidos de registro";
            dialogMessage = "Correo no valido, debe contener '@' y '.'\n";
            ans=false;
        }if(name.length()<6){
            dialogTittle="Datos invalidos de registro";
            dialogMessage+="el nombre del usuario no puede ser tan corto\n";
            ans=false;
        }if(pass.length()<5){
            dialogTittle="Datos invalidos de registro";
            dialogMessage+="Ingrese una contraseña valida"+pass;
            ans=false;
        }
        return ans;
    }

    public void goBack (View v){
        finish();
    }


    public void registrar(View v){
        //userName.getText().toString(), email.getText().toString(), "0", pass.getText().toString());
        close = false;
        if (validateUserInfo(userName.getText().toString(),email.getText().toString(),pass.getText().toString())) {
            PlazApp.tryRegister(userName.getText().toString(), email.getText().toString(), pass.getText().toString(),"0");
            cargando.show();
        }else{
            alertDialog.setTitle(dialogTittle);
            alertDialog.setMessage(dialogMessage);
            alertDialog.show();
        }
    }

    private static void clear(){
        userName.setText("");
        email.setText("");
        pass.setText("");
        pass2.setText("");
        alertIncorrectPassL.setText("");
        alertIncorrectPass.setText("");
    }

    public static void notifyFromServer(boolean statusRegistered){
        cargando.dismiss();
        if (statusRegistered){
            close=true;
            alertDialog.setTitle("Registro Exitoso!");
            alertDialog.setMessage("Bienvenido a PlazApp "+userName.getText().toString());
            alertDialog.show();
        }else {
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Nose pudo registrar en la base de datos, intente de nuevo");
            alertDialog.show();
        }
        clear();
    }

    public static void notifyUserExist() {
        cargando.dismiss();
        alertDialog.setTitle("Registro Fallido!");
        alertDialog.setMessage("El correo ya se encuentra asociado a una cuenta. intente con otro");
        alertDialog.show();
        clear();
    }
}
