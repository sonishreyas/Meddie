package com.example.forever.meddie.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forever.meddie.R;
import com.example.forever.meddie.helper.UserAuthentication;

public class LoginActivity extends AppCompatActivity {

    private EditText emailET;
    private EditText passwordET;
    private SharedPreferences userPreference;
    private SharedPreferences.Editor editor;
    private UserAuthentication userAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = (EditText) findViewById(R.id.loginEmail);
        passwordET = (EditText) findViewById(R.id.loginPassword);

        userAuthentication = new UserAuthentication(this);

        userPreference = getSharedPreferences("User Authenticate",MODE_PRIVATE);
        editor = userPreference.edit();
    }

    public void goSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void justLogin(View view) {
        String userEmail = emailET.getText().toString();
        String userPass = passwordET.getText().toString();
       // emailET.setText(userAuthentication.getEmail());
        String savedEmail = userAuthentication.getEmail();
        String savedPass = userAuthentication.getPassword();

        if (userEmail.isEmpty()){
            emailET.setError("Login Email Needed !!!");
        }
        if (userPass.isEmpty()){
            passwordET.setError("Login Password Needed !!!");
        }
        else {
            if (userEmail.equals(savedEmail) && userPass.equals(savedPass)) {

                //Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, dashboard.class));
                //startActivity(new Intent(LoginActivity.this, DoctorListActivity.class));
            } else {
                Toast.makeText(this, "Couldn't find you !!!", Toast.LENGTH_SHORT).show();
            }
            //showPassTV.setText(userAuthentication.getPassword());

        }
    }
}
