package com.example.forever.meddie.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forever.meddie.helper.Doctor;
import com.example.forever.meddie.helper.DoctorDatabaseSource;
import com.example.forever.meddie.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Locale;

public class AddDoctorActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText detailsET;
    //private EditText appoinmentET;
    private Button appoinmentET;
    private int year,month,day;
    private Calendar calendar;

    private String email_id ;
//    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private EditText phoneET;
    private EditText emailET;
    private Button btnAdd;

    private Doctor doctor;
    private DoctorDatabaseSource doctorDatabaseSource;

    //for get intent variable
    private String docName,docSpecialist,docApoint,doctorPhone,doctorEmail;
    private  int rowId ;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        nameET        = (EditText) findViewById(R.id.doctorName);
        detailsET     = (EditText) findViewById(R.id.doctorDetails);
        appoinmentET  = (Button) findViewById(R.id.doctorAppoinment);
        phoneET       = (EditText) findViewById(R.id.doctorPhone);
        emailET       = (EditText) findViewById(R.id.doctorEmail);

        calendar      = Calendar.getInstance(Locale.getDefault());
        year          = calendar.get(Calendar.YEAR);
        month         = calendar.get(Calendar.MONTH);
        day           = calendar.get(Calendar.DAY_OF_MONTH);

        btnAdd        = (Button) findViewById(R.id.btnAdd);
        doctorDatabaseSource = new DoctorDatabaseSource(this);

        // pass data for edit
        docName         = getIntent().getStringExtra("doctorName");
        docSpecialist   = getIntent().getStringExtra("docSpecialist");
        docApoint       = getIntent().getStringExtra("doctorApoint");
        doctorPhone     = getIntent().getStringExtra("doctorPhone");
        doctorEmail     = getIntent().getStringExtra("doctorEmail");
        rowId           = getIntent().getIntExtra("id",0);

        //set for data update
        if(rowId > 0){
            nameET.setText(docName);
            detailsET.setText(docSpecialist);
            appoinmentET.setText(docApoint);
            phoneET.setText(doctorPhone);
            emailET.setText(doctorEmail);
            btnAdd.setText("Update");
        }

        Intent intent = getIntent();
        email_id = intent.getStringExtra("email");
        Toast.makeText(getApplicationContext(), email_id, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(AddDoctorActivity.this,DoctorListActivity.class));
                break;
            case R.id.logout:
                //Intent loginscreen=new Intent(this,LoginFireActivity.class);
                //loginscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(loginscreen);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,LoginFireActivity.class));
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void addDoctor(View view) {
        String name         =   nameET.getText().toString();
        String details      =   detailsET.getText().toString();
        String appointment  =   appoinmentET.getText().toString();
        String phone        =   phoneET.getText().toString();
        String email        =   emailET.getText().toString();
        if(name.isEmpty()){
            nameET.setError("This field must not be Empty !");
        }
        if(details.isEmpty()){
            detailsET.setError("This field must not be Empty !");
        }

        if(appointment.isEmpty()){
            appoinmentET.setError("This field must not be Empty !");
        }

        if(phone.isEmpty()){
            phoneET.setError("This field must not be Empty !");
        }

        if(email.isEmpty()){
            emailET.setError("This field must not be Empty !");
        }else{
            if (emailET.getText().toString().trim().matches(emailPattern)) {
                if(rowId > 0){
                    doctor =   new Doctor(name,details,appointment,phone,email);
                    boolean status  = doctorDatabaseSource.editDoctor(doctor,rowId);
                    if(status){
                        Intent i = new Intent(this, DoctorListActivity.class);
                        i.putExtra("email",email_id);
                        startActivity(i);
                        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                        // startActivity(new Intent(AddDoctorActivity.this,DoctorListActivity.class));
                    }else{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }

                }else{            //it condition for add
                    doctor =   new Doctor(name,details,appointment,phone,email);
                    boolean status  =   doctorDatabaseSource.addDoctorInfo(doctor);
                    if(status){
                        Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddDoctorActivity.this,DoctorListActivity.class));
                    }else{
                        Toast.makeText(this, "Could not save", Toast.LENGTH_SHORT).show();
                    }
                }
            }else {
                Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
            }


            }
        }


    public void showDate(View view) {
        DatePickerDialog dpd    =   new DatePickerDialog(this,dateListner,year,month,day);
        dpd.show();
    }

    private DatePickerDialog.OnDateSetListener dateListner  =   new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // Toast.makeText(MainActivity.this, "year:"+year, Toast.LENGTH_SHORT).show();
            appoinmentET.setText(dayOfMonth+"-"+(month+1)+"-"+year);
        }
    };
}
