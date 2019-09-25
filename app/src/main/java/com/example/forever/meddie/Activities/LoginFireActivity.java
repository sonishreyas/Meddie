package com.example.forever.meddie.Activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forever.meddie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginFireActivity extends AppCompatActivity {

    private EditText Name ;
    private EditText Password ;

    private Button Login ;
    private TextView userRegistration ;
    private int counter = 5 ;

    private FirebaseAuth mAuth ;

    private ProgressDialog progressDialog ;
    private TextView forgotpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fire);

        Name = (EditText)findViewById(R.id.loginEmail);
        Password = (EditText)findViewById(R.id.loginPassword);

        Login = (Button)findViewById(R.id.loginBtn);
        userRegistration = (TextView) findViewById(R.id.signUpBtn);
        //Button mainreg = (Button)findViewById(R.id.reg);
        forgotpassword = (TextView)findViewById(R.id.forgotpassword);


        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        FirebaseUser user = mAuth.getCurrentUser();
/*
        if( user != null ){
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }

  */      /*mainreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });*/

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                closeKeyboard();
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
                Intent i = new Intent(LoginFireActivity.this,RegisterFireActivity.class);
                startActivity(i);
                //Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginFireActivity.this,PasswordActivity.class));
            }
        });

    }

    private void validate(String userName,String userPassword){

        progressDialog.setMessage("Checking....");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(LoginFireActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginFireActivity.this,dashboard.class));
                }else{
                    Toast.makeText(LoginFireActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
                    progressDialog.dismiss();

                }

        });

    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
