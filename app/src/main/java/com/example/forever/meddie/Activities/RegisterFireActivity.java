package com.example.forever.meddie.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forever.meddie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFireActivity extends AppCompatActivity {

    private EditText userName , userPassword , userEmail ;

    private Button regButton ;
    private Button userLogin ;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_fire);
        setupUIViews();
        mAuth = FirebaseAuth.getInstance();
        userLogin = (Button)findViewById(R.id.Loginbtn);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Upload database
                if(validate()) {
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();
                    mAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            closeKeyboard();
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterFireActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterFireActivity.this,LoginFireActivity.class));
                            }else{
                                Toast.makeText(RegisterFireActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterFireActivity.this,LoginFireActivity.class));
            }
        });



    }

    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.signUpName);
        userPassword = (EditText)findViewById(R.id.signUpPassword);
        userEmail = (EditText)findViewById(R.id.signUpEmail);
        regButton = (Button)findViewById(R.id.signUpBtnBtn);


    }

    private boolean validate(){

        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();


        if( name.isEmpty() || password.isEmpty() || email.isEmpty() ){
            Toast.makeText(this,"Please Enter All The Deatils",Toast.LENGTH_LONG).show();
        }else{
            return true;
        }

        return false;

    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}

