package com.kuri.motorcontroller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    Button registerMainButton;
    FirebaseAuth firebaseAuth;
    TextInputLayout regNameInput;
    TextInputLayout regEmailInput;
    TextInputLayout regPassInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog = new ProgressDialog(this);
        registerMainButton = (Button) findViewById(R.id.registerMainButton);
        firebaseAuth = FirebaseAuth.getInstance();

        regNameInput = (TextInputLayout) findViewById(R.id.textInputLayout2);
        regEmailInput = (TextInputLayout) findViewById(R.id.textInputLayout3);
        regPassInput = (TextInputLayout) findViewById(R.id.textInputLayout4);





        registerMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String regName = regNameInput.getEditText().getText().toString().trim();
                final String regEmail = regEmailInput.getEditText().getText().toString().trim();
                final String regPass = regPassInput.getEditText().getText().toString().trim();

                progressDialog.setTitle("Registration....");
                progressDialog.setMessage("Just a moment....We are registering you.");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                registerUser(regName, regEmail, regPass);
            }
        });

    }

    public void registerUser(String regName, String regEmail, String regPass){

        firebaseAuth.createUserWithEmailAndPassword(regEmail, regPass)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Intent goToControlsPage = new Intent(RegisterActivity.this, ControlsActivity.class);
                            startActivity(goToControlsPage);
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Something went wrong....Please Enter information again", Toast.LENGTH_SHORT);
                            progressDialog.dismiss();
                        }
                    }
                });
    }
}
