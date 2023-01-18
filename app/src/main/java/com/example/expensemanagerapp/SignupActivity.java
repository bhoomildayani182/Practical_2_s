package com.example.expensemanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

    EditText seMail;
    EditText spassword;
    EditText scpassword;
    Button btnSignup;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();

        btnSignup = findViewById(R.id.btnSignup);
        seMail = (EditText) findViewById(R.id.seMail);
        spassword = (EditText) findViewById(R.id.spassword);
        scpassword = (EditText) findViewById(R.id.scpassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        DB = new DBHandler(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String seMail_ = seMail.getText().toString();
                String spassword_ = spassword.getText().toString();
                String scpassword_ = scpassword.getText().toString();

                if (!seMail_.isEmpty()) {
                    seMail.setError(null);

                        if (!spassword_.isEmpty()) {
                            spassword.setError(null);
                            if (!scpassword_.isEmpty()) {
                                scpassword.setError(null);
                                if (spassword_.equals(scpassword_)) {
                                    scpassword.setError(null);
                                    if (spassword_.matches("^(?=.*[0-9])"
                                            + "(?=.*[a-z])(?=.*[A-Z])"
                                            + "(?=.*[@#$%^&+=])"
                                            + "(?=\\S+$).{8,20}$")) {
                                        spassword.setError(null);


                                            Boolean checkuser = DB.checkusername(seMail_);
                                            if(checkuser==false){
                                                Boolean insert = DB.insertData(seMail_, spassword_);
                                                if(insert==true){
                                                    Toast.makeText(SignupActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                                    startActivity(intent);
                                                }else{
                                                    Toast.makeText(SignupActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                            else{
                                                Toast.makeText(SignupActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                                            }

                                        
                                        UserSign();

                                    } else {
                                        spassword.setError("Create Strong Password");
                                    }
                                } else {
                                    scpassword.setError("Please Check Your Password");
                                }
                            } else {
                                scpassword.setError("Please Enter Password");
                            }
                        } else {
                            spassword.setError("Please Enter Password");
                        }
                    } else {
                    seMail.setError("Please Enter Username");
                }
            }
        });
    }

    private void UserSign() {
    }
}