package com.example.forever.pid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dashboard extends AppCompatActivity implements View.OnClickListener {

    private CardView bankCard,ideasCard,addCard,linkCard,wifiCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bankCard = (CardView) findViewById(R.id.bank_card);
        ideasCard = (CardView) findViewById(R.id.ideas_card);
        addCard = (CardView) findViewById(R.id.add_card);
        linkCard = (CardView) findViewById(R.id.link_card);
        wifiCard = (CardView) findViewById(R.id.wificard);

        bankCard.setOnClickListener(this);
        ideasCard.setOnClickListener(this);
        addCard.setOnClickListener(this);
        linkCard.setOnClickListener(this);
        wifiCard.setOnClickListener(this);



    }
    @Override
    public void onClick(View v){
        Intent i ;

        switch(v.getId()) {
            case R.id.bank_card : i = new Intent(this,AddDoctorActivity.class); startActivity(i);  break;
            case R.id.ideas_card : i = new Intent(this,AddPrescription.class);startActivity(i) ;break;
            case R.id.add_card : i = new Intent(this,DoctorListActivity.class); startActivity(i);break;
            case R.id.link_card : i = new Intent(this,AddPrescription.class);  startActivity(i);break;
            case R.id.wificard : i = new Intent(this,AddPrescription.class); startActivity(i);break;
            default:break;

        }

    }

}
