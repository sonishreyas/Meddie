package com.example.forever.meddie.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forever.meddie.helper.DoctorDatabaseSource;
import com.example.forever.meddie.helper.MedicalHistory;
import com.example.forever.meddie.R;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;

//import android.icu.util.Calendar;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AddPrescription extends AppCompatActivity {
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    private ImageButton imageView;
    private TextView showImgePathTV;
    private File image;
    private String imageFileName;

    private TextView doctorNameET;
    private TextView doctorDeatilsET;
    // private EditText doctorIDET;


    private Button prestionDateBTN;

    private int year, month, day, hour, minute;
    private Calendar calendar;
    FirebaseAuth firebaseAuth;
    private Button addHistoryBtn;

    private MedicalHistory medicalHistory;
    private DoctorDatabaseSource doctorDatabaseSource;
//    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    static String nopath = "Select Video Only";

    ImageView ImgUserPhoto;
    Uri pickedImgUri ;


    //for get intent variable
    private String docName, docSpecialist, docApoint, doctorPhone, doctorEmail;
    private int rowId;
    private String ImgePath = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        imageView = (ImageButton) findViewById(R.id.presImageButton);
        doctorDatabaseSource = new DoctorDatabaseSource(this);
        showImgePathTV = (TextView) findViewById(R.id.showImgePath);

        doctorNameET = (TextView) findViewById(R.id.doctorNameET);
        doctorDeatilsET = (TextView) findViewById(R.id.doctorDeatilsET);
        // doctorIDET = (EditText) findViewById(R.id.docId);

        prestionDateBTN = (Button) findViewById(R.id.prestionDate);
        calendar = Calendar.getInstance(Locale.getDefault());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        addHistoryBtn = (Button) findViewById(R.id.addMedicalBtn);

        // pass data for edit
        docName = getIntent().getStringExtra("doctorName");
        docSpecialist = getIntent().getStringExtra("docSpecialist");
        rowId = getIntent().getIntExtra("id", 0);
        //firebaseAuth.getInstance();
        //set for data update
        doctorNameET.setText(docName);
        doctorDeatilsET.setText(docSpecialist);
        //doctorIDET.setText(""+rowId);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent gallery  = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                //startActivityForResult(gallery,REQUEST_TAKE_PHOTO);
                openGallery();
            }
        });

    }

    private void openGallery() {
        //TODO: open gallery intent and wait for user to pick an image !

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivity(galleryIntent);
        pickedImgUri = galleryIntent.getData() ;
        ImgUserPhoto.setImageURI(pickedImgUri);

        //startActivityForResult(galleryIntent,REQUESCODE);
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
                startActivity(new Intent(AddPrescription.this, DoctorListActivity.class));
                break;
            case R.id.logout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(AddPrescription.this, LoginFireActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    public void showDate(View view) {
        DatePickerDialog dpd = new DatePickerDialog(this, dateListner, year, month, day);
        dpd.show();
    }

    private DatePickerDialog.OnDateSetListener dateListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // Toast.makeText(MainActivity.this, "year:"+year, Toast.LENGTH_SHORT).show();
            prestionDateBTN.setText(dayOfMonth + "-" + (month+1) + "-" + year);
        }
    };


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData( );
            String picturePath = getPath( AddPrescription.this, selectedImageUri );
            showImgePathTV.setText(picturePath);
            Log.d("Picture Path", picturePath);
        }
    }

    public static String getPath( Context context, Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }

    public void addMedicalHistory(View view) {
        String imagePath =   showImgePathTV.getText().toString();
        String addDate  =   prestionDateBTN.getText().toString();
        // String addId  =   doctorIDET.getText().toString();

        if(imagePath.isEmpty()){
            showImgePathTV.setError("Please use your Camera !");
        }
        if(addDate.isEmpty()){
            prestionDateBTN.setError("Set Date !");
        }

        else{
            // it condition for update
            /*if(rowId > 0){
                doctor =   new Doctor(name,details,appointment,phone,email);
                boolean status  = doctorDatabaseSource.editDoctor(doctor,rowId);
                if(status){
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,DoctorListActivity.class));
                }else{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }else{   */         //it condition for add
            //MedicalHistory  medicalHistory =   new MedicalHistory(addDate,imagePath);
            MedicalHistory medicalHistory = new MedicalHistory(addDate,imagePath,rowId);
            boolean status  =   doctorDatabaseSource.addHistory(medicalHistory);
            if(status){
                Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
                this.finish();
                startActivity(new Intent(AddPrescription.this, MedicalListActivity.class)
                        .putExtra("docId",rowId));
            }else{
                Toast.makeText(this, "Could not save", Toast.LENGTH_SHORT).show();
            }
            // }
        }
    }

    private static int PICK_IMAGE = 123;
    Uri imagepath;
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_IMAGE && resultCode ==RESULT_OK && data.getData() != null){

            imagepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);
                //profile_image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
*/
}