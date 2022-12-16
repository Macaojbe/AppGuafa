package com.macobe.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileInputStream;

public class NewAccount extends AppCompatActivity {

    private Button btnNewAccount;
    private EditText editEmailNewAccount, editPasswordNewAccount, editConfirmPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        btnNewAccount = (Button) findViewById(R.id.btnNewAccount);

        editEmailNewAccount = (EditText) findViewById(R.id.editEmailNewAccount);
        editPasswordNewAccount = (EditText) findViewById(R.id.editPasswordNewAccount);
        editConfirmPassword = (EditText) findViewById(R.id.editConfirmPassword);

        mAuth = FirebaseAuth.getInstance();

        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmailNewAccount.getText().toString().trim();
                String pass = editPasswordNewAccount.getText().toString().trim();
                String confirm = editConfirmPassword.getText().toString().trim();

                if(pass.compareTo(confirm) == 0) {
                    mAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Usuario Registrado Correctamente", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Register.class);
                                        startActivity(intent);
                                    }
                                }
                            }) .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error en Registro", Toast.LENGTH_LONG).show();
                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(), "Confirma Contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}