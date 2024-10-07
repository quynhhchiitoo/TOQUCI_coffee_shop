package com.example.coffee;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class Profile extends AppCompatActivity {

    Toolbar toolbarprofile;
    private EditText fullnametxt, phonenumtxt, emailtxt, addresstxt;
    private ImageView editIcon1, editIcon2, editIcon3, editIcon4;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        toolbarprofile = findViewById(R.id.toolbar_profile);

        fullnametxt = findViewById(R.id.fullnametxt);
        phonenumtxt = findViewById(R.id.phonenumtxt);
        emailtxt = findViewById(R.id.emailtxt);
        addresstxt = findViewById(R.id.addresstxt);

        editIcon1 = findViewById(R.id.editicon1);
        editIcon2 = findViewById(R.id.editicon2);
        editIcon3 = findViewById(R.id.editicon3);
        editIcon4 = findViewById(R.id.editicon4);

        saveButton = findViewById(R.id.saveButton);
        Database database = Database.getInstance(this);

        User user = database.getFirstUser();
        if(user != null)
        {
            fullnametxt.setText(user.getFullname());
            phonenumtxt.setText(user.getPhonenum());
            emailtxt.setText(user.getEmail());
            addresstxt.setText(user.getAddress());

            disableEditMode(fullnametxt);
            disableEditMode(phonenumtxt);
            disableEditMode(emailtxt);
            disableEditMode(addresstxt);

            saveButton.setEnabled(false);
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = fullnametxt.getText().toString();
                String phonenum = phonenumtxt.getText().toString();
                String email = emailtxt.getText().toString();
                String address = addresstxt.getText().toString();
                if(fullname.length() == 0 || phonenum.length() == 0 ||
                        email.length() == 0 || address.length() == 0)
                    Toast.makeText(getApplicationContext(), "Please fill all required information", Toast.LENGTH_SHORT).show();
                else
                {
                    if(checkPhoneNum(phonenum) == 1 && checkEmail(email) == 1)
                    {
                        User user = database.getFirstUser();
                        if(user == null) {
                            database.insertUser(fullname, phonenum, email, address, 0);
                            Toast.makeText(getApplicationContext(), "Successfully save", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            database.clear("users");
                            database.insertUser(fullname, phonenum, email, address, user.getPoint());
                            Toast.makeText(getApplicationContext(), "Successfully edit", Toast.LENGTH_SHORT).show();
                        }
                        saveChanges();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Invalid phone number or email", Toast.LENGTH_SHORT).show();
                }

            }
        });
        editIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableEditMode(fullnametxt);
                saveButton.setEnabled(true);
            }
        });

        editIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableEditMode(phonenumtxt);
                saveButton.setEnabled(true);
            }
        });

        editIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableEditMode(emailtxt);
                saveButton.setEnabled(true);
            }
        });

        editIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableEditMode(addresstxt);
                saveButton.setEnabled(true);
            }
        });
        ActionToolbar();
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarprofile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarprofile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void enableEditMode(EditText editText) {
        editText.setEnabled(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
    }

    private void saveChanges() {
        disableEditMode(fullnametxt);
        disableEditMode(phonenumtxt);
        disableEditMode(emailtxt);
        disableEditMode(addresstxt);
        saveButton.setEnabled(false);
    }

    private void disableEditMode(EditText editText) {
        editText.setEnabled(false);
        editText.setFocusableInTouchMode(false);
    }
    private int checkPhoneNum(String phonenum){
        if(phonenum.length() == 10)
        {
            for(int p = 0; p < phonenum.length(); p++)
                if(Character.isLetter(phonenum.charAt(p)))
                    return 0;
            return 1;
        }
        return 0;
    }
    private int checkEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (Pattern.matches(emailRegex, email)) {
            return 1; // Valid email format
        } else {
            return 0; // Invalid email format
        }
    }
}