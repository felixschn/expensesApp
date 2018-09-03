package com.example.felix.ausgaben4;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class enterDataActivity extends AppCompatActivity {

    private TextView enterTitle, enterDate, enterAmount;
    private EditText userEnterTitle, userEnterDate, userEnterAmount;
    private Button saveButton;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the right xml file
        setContentView(R.layout.enterdata_activity);


        //link to the xml TextViews
        enterTitle = findViewById(R.id.enterTitle);
        enterDate = findViewById(R.id.enterDate);
        enterAmount = findViewById(R.id.enterAmount);
        //link to the xml EditText
        userEnterTitle = findViewById(R.id.userEnterTitle);
        userEnterDate = findViewById(R.id.userEnterDate);
        userEnterAmount = findViewById(R.id.userEnterAmount);

        /* TODO: need to use in MainActivity
        final String titleToString = userEnterTitle.getText().toString();
        final String dateToString = userEnterDate.getText().toString();
        final String amountToString = userEnterAmount.getText().toString();*/


        saveButton = findViewById(R.id.saveData);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saveData = new Intent();
                saveData.putExtra("title", userEnterTitle.getText().toString());
                saveData.putExtra("date", userEnterDate.getText().toString());
                saveData.putExtra("amount", userEnterAmount.getText().toString());
                setResult(Activity.RESULT_OK, saveData);




                finish();

            }
        });



    }
}
