package com.example.felix.ausgaben4;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class enterDataActivity extends AppCompatActivity {

    private TextView enterTitle, enterDate, enterAmount;
    private EditText userEnterTitle, userEnterDate, userEnterAmount;
    private Button saveButton;
    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the right xml file
        setContentView(R.layout.enterdata_activity);

        //link to the xml EditText
        userEnterTitle = findViewById(R.id.userEnterTitle);
        userEnterDate = findViewById(R.id.userEnterDate);
        userEnterAmount = findViewById(R.id.userEnterAmount);


        userEnterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(enterDataActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        saveButton = findViewById(R.id.saveData);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()) {
                    Intent saveData = new Intent();
                    saveData.putExtra("title", userEnterTitle.getText().toString());
                    saveData.putExtra("date", userEnterDate.getText().toString());
                    saveData.putExtra("amount", userEnterAmount.getText().toString());
                    setResult(Activity.RESULT_OK, saveData);
                    finish();
                }

            }
        });


    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.GERMAN);
        userEnterDate.setText(simpleDateFormat.format(myCalendar.getTime()));
    }

    //check if the user fills in all three fields
    private boolean checkInput() {
        if (!TextUtils.isEmpty(userEnterTitle.getText()) && !TextUtils.isEmpty(userEnterAmount.getText()) && !TextUtils.isEmpty(userEnterDate.getText())) {
            return true;

        } else {
            Toast.makeText(this, "Bitte alle Felder ausfüllen", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
