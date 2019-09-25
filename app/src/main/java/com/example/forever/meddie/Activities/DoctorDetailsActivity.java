package com.example.forever.meddie.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forever.meddie.helper.Doctor;
import com.example.forever.meddie.helper.DoctorDatabaseSource;
import com.example.forever.meddie.helper.MedicalHistory;
import com.example.forever.meddie.R;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorDetailsActivity extends AppCompatActivity {

    private TextView docNameTV, docSpecialistTV, docApointTV, docPhoneTV, docEmailTV;
    private Doctor doctor;
    private MedicalHistory medicalHistory;
    private FirebaseAuth firebaseAuth;

    //for get intent variable
    private String docName,docSpecialist,docApoint,doctorPhone,doctorEmail;

    private int rowId;
    private DoctorDatabaseSource doctorDatabaseSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        doctorDatabaseSource = new DoctorDatabaseSource(this);

       // doctor = (Doctor) getIntent().getSerializableExtra("doctorObj");
        docName      = getIntent().getStringExtra("doctorName");
        docSpecialist= getIntent().getStringExtra("docSpecialist");
        docApoint    = getIntent().getStringExtra("doctorApoint");
        doctorPhone  = getIntent().getStringExtra("doctorPhone");
        doctorEmail  = getIntent().getStringExtra("doctorEmail");
        rowId        = getIntent().getIntExtra("id",0);

        docNameTV       = (TextView) findViewById(R.id.showName);
        docSpecialistTV = (TextView) findViewById(R.id.showSpecialist);
        docApointTV     = (TextView) findViewById(R.id.showDate);
        docPhoneTV      = (TextView) findViewById(R.id.showPhone);
        docEmailTV      = (TextView) findViewById(R.id.showEmail);

        docNameTV.setText(docName);
        docSpecialistTV.setText(docSpecialist);
        docApointTV.setText(docApoint);
        docPhoneTV.setText(doctorPhone);
        docEmailTV.setText(doctorEmail);
        firebaseAuth.getInstance();

    }

   /* public void editDoctor(View view) {

        startActivity(new Intent(DoctorDetailsActivity.this,
                MainActivity.class)
                .putExtra("id",rowId)
                .putExtra("doctorName",docName)
                .putExtra("docSpecialist",docSpecialist)
                .putExtra("doctorApoint",docApoint)
                .putExtra("doctorPhone",doctorPhone)
                .putExtra("doctorEmail",doctorEmail)
        );
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.doctor_menu, menu);
        return true;
    }

    /*public void deleteDoctor(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Doctor");
        alert.setMessage("Are you sure to delete this item ?");
        alert.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean status = doctorDatabaseSource.deleteDoctor(rowId);
                if(status){
                    Toast.makeText(DoctorDetailsActivity.this, "Doctor Deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DoctorDetailsActivity.this,DoctorListActivity.class));
                }else{
                    Toast.makeText(DoctorDetailsActivity.this, "Couldn't Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.setNegativeButton("Cancel",null);
        alert.show();
    }*/

    public void addPrescription(View view) {
        /*startActivity(new Intent(DoctorDetailsActivity.this,AddPrescription.class)

                .putExtra("id",rowId)
                .putExtra("doctorName",docName)
                .putExtra("docSpecialist",docSpecialist));

         */

        startActivity(new Intent(DoctorDetailsActivity.this, ShowPrescriptionsDemo.class));

    }

    public void viewPrescription(View view) {
       // medicalHistory.setDoctorId(rowId);
        //doctorDatabaseSource.getDoctorPrescription(rowId);
        startActivity(new Intent(DoctorDetailsActivity.this, DisplayPrescription.class)
        .putExtra("docId",rowId));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(DoctorDetailsActivity.this,DoctorListActivity.class));
                break;
            case R.id.menu_update:
                startActivity(new Intent(DoctorDetailsActivity.this,
                        AddDoctorActivity.class)
                        .putExtra("id",rowId)
                        .putExtra("doctorName",docName)
                        .putExtra("docSpecialist",docSpecialist)
                        .putExtra("doctorApoint",docApoint)
                        .putExtra("doctorPhone",doctorPhone)
                        .putExtra("doctorEmail",doctorEmail)
                );
                break;
            case R.id.menu_delete:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Delete Doctor");
                alert.setMessage("Are you sure to delete this item ?");
                alert.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean status = doctorDatabaseSource.deleteDoctor(rowId);
                        if(status){
                            Toast.makeText(DoctorDetailsActivity.this, "Doctor Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DoctorDetailsActivity.this,DoctorListActivity.class));
                        }else{
                            Toast.makeText(DoctorDetailsActivity.this, "Couldn't Delete", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alert.setNegativeButton("Cancel",null);
                alert.show();
                break;
            case R.id.logout:

                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(DoctorDetailsActivity.this, LoginFireActivity.class));

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
