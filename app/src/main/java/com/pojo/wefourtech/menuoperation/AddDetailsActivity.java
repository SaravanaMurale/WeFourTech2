package com.pojo.wefourtech.menuoperation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pojo.wefourtech.R;

public class AddDetailsActivity extends AppCompatActivity {

    EditText nameEdit,emailEdit,mobileEdit;
    Button btnAdd;

    String name,email,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        nameEdit=(EditText)findViewById(R.id.addNameEdit);
        emailEdit=(EditText)findViewById(R.id.addEmailEdit);
        mobileEdit=(EditText)findViewById(R.id.addPhoneEdit);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getUserEnteredDetails();

                addFormDataInFirebase();


            }
        });

    }

    private void getUserEnteredDetails() {

        name=nameEdit.getText().toString();
        email=emailEdit.getText().toString();
        mobile=mobileEdit.getText().toString();

    }

    private void addFormDataInFirebase() {



    }
}