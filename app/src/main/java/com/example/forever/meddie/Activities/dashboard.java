package com.example.forever.meddie.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.forever.meddie.R;


public class dashboard extends AppCompatActivity implements View.OnClickListener {

    private CardView bankCard,ideasCard,addCard,linkCard,wifiCard;
    private WebView webview;
    public String email_id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //getbprevious intent data
        Intent intent = getIntent();
        email_id = intent.getStringExtra("email");
        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

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
            case R.id.bank_card :
                i = new Intent(this, DoctorListActivity.class);
                i.putExtra("email",email_id);
                startActivity(i);
                break;
            case R.id.ideas_card :
                openWebPage("https://keep.google.com/u/0/"); ;break;
            case R.id.add_card :
                i = new Intent(this, DisplayPrescription.class);
                i.putExtra("email",email_id);
                startActivity(i);
                break;
            case R.id.link_card :
                openWebPage("https://www.healthline.com/");break;
            case R.id.wificard :
                openWebPage("https://www.google.com/maps/search/hospitals+near+me");;break;
            default:break;

        }

    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
