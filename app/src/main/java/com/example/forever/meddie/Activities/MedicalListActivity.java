package com.example.forever.meddie.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.forever.meddie.helper.DoctorDatabaseSource;
import com.example.forever.meddie.helper.MedicalHistory;
import com.example.forever.meddie.Adapters.MedicalHistoryAdapter;
import com.example.forever.meddie.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MedicalListActivity extends AppCompatActivity {

    private ListView mListView;
    private MedicalHistoryAdapter medicalHistoryAdapter;
    private ArrayList<MedicalHistory> medicalHistories;
    private DoctorDatabaseSource doctorDatabaseSource;
    private int doctorId;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_list);

        doctorId = getIntent().getIntExtra("docId",0);

        TextView emptyText = (TextView) findViewById(R.id.emptyText);
        mListView = (ListView) findViewById(R.id.medicalList);
        mListView.setEmptyView(emptyText);
        doctorDatabaseSource = new DoctorDatabaseSource(this);
        //medicalHistories = doctorDatabaseSource.getAllHistory();

        medicalHistories = doctorDatabaseSource.getDoctorPrescription(doctorId);

        medicalHistoryAdapter = new MedicalHistoryAdapter(this, medicalHistories);
        mListView.setAdapter(medicalHistoryAdapter);
        firebaseAuth.getInstance();
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
                startActivity(new Intent(MedicalListActivity.this,DoctorListActivity.class));
                break;
            case R.id.logout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(MedicalListActivity.this, LoginFireActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
