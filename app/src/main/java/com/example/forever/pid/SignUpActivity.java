package com.example.forever.pid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText emailET;
    private EditText passwordET;
    //private Button showUserBtn;
    private DoctorDatabaseHelper doctorDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
   // private TextView showEmailTV,showPasTV;
    private SharedPreferences userPreference;
    private SharedPreferences.Editor editor;
    private UserAuthentication userAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameET = (EditText) findViewById(R.id.signUpName);
        emailET = (EditText) findViewById(R.id.signUpEmail);
        passwordET = (EditText) findViewById(R.id.signUpPassword);


        userAuthentication = new UserAuthentication(this);
        doctorDatabaseHelper = new DoctorDatabaseHelper(this);

        userPreference = getSharedPreferences("User Authenticate",MODE_PRIVATE);
        editor = userPreference.edit();
    }

    public void saveUser(View view) {
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if (name.isEmpty()){
            nameET.setError("Please Enter Your Name");
        }
        if (email.isEmpty()){
            emailET.setError("Please Enter Your Email");
        }
        if (password.isEmpty()){
            passwordET.setError("Please Enter the Password");
        }

        else {
            userAuthentication.saveUser(name, email, password);

            if (editor.commit() == true) {
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
               // String dbName = doctorDatabaseHelper.getDatabaseName();
                //doctorDatabaseHelper.onCreate(sqLiteDatabase);
                //doctorDatabaseHelper.onUpgrade(sqLiteDatabase db,1,1);
                doctorDatabaseHelper.clearDatabase();
                startActivity(new Intent(this, dashboard.class));
            } else {
                Toast.makeText(this, "Could not save", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
