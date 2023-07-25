package com.example.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

//    Toolbar toolbardetails;
//    private EditText fullnametxt, phonenumtxt, emailtxt, addresstxt;
//    private ImageView editIcon1, editIcon2, editIcon3, editIcon4;
//    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
//        fullnametxt = findViewById(R.id.fullnametxt);
//        phonenumtxt = findViewById(R.id.phonenumtxt);
//        emailtxt = findViewById(R.id.emailtxt);
//        addresstxt = findViewById(R.id.addresstxt);
//
//        editIcon1 = findViewById(R.id.editicon1);
//        editIcon2 = findViewById(R.id.editicon2);
//        editIcon3 = findViewById(R.id.editicon3);
//        editIcon4 = findViewById(R.id.editicon4);
//
//        saveButton = findViewById(R.id.saveButton);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveChanges();
//            }
//        });
//
//        editIcon1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                enableEditMode(fullnametxt);
//            }
//        });
//
//        editIcon2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                enableEditMode(phonenumtxt);
//            }
//        });
//
//        editIcon3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                enableEditMode(emailtxt);
//            }
//        });
//
//        editIcon4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                enableEditMode(addresstxt);
//            }
//        });
//        ActionToolbar();
    }

//    private void ActionToolbar() {
//        setSupportActionBar(toolbardetails);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbardetails.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    private void enableEditMode(EditText editText) {
//        editText.setEnabled(true);
//        editText.setFocusableInTouchMode(true);
//        editText.requestFocus();
//    }
//
//    private void saveChanges() {
//        String newName = fullnametxt.getText().toString();
//        String newPhoneNumber = phonenumtxt.getText().toString();
//        String newEmail = emailtxt.getText().toString();
//        String newAddress = addresstxt.getText().toString();
//
//        // TODO: Perform actions to save the new information (e.g., update database, send to server)
//
//        // After saving, disable the edit mode for all fields
//        disableEditMode(fullnametxt);
//        disableEditMode(phonenumtxt);
//        disableEditMode(emailtxt);
//        disableEditMode(addresstxt);
//    }
//
//    private void disableEditMode(EditText editText) {
//        editText.setEnabled(false);
//        editText.setFocusableInTouchMode(false);
//    }
}
