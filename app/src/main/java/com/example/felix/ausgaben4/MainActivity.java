package com.example.felix.ausgaben4;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private TextView sumTextView;
    private TextView amountTextView;
    private static final int REQUEST_ID = 1;
    private expensesAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expensesDBHelper dbHelper = new expensesDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new expensesAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);


        sumTextView = findViewById(R.id.sum);
        amountTextView = findViewById(R.id.amount);
        //set the Text of the Sum of all Database entries
        amountTextView.setText(String.valueOf(sumOfAllItems()));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, enterDataActivity.class);
                startActivityForResult(i, REQUEST_ID);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_ID) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String retEnteredDataTitle = data.getStringExtra("title");
                String retEnteredDataDate = data.getStringExtra("date");
                String retEnteredDataAmount = data.getStringExtra("amount");

                System.out.println(retEnteredDataTitle);
                //TODO convert Date String into Date format
                addItem(retEnteredDataTitle, retEnteredDataDate, retEnteredDataAmount);

            }

        }
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                expensesContract.expensesEntry.TABLE_NAME,
                null,
                null,
                null,
                null
                , null,
                null);
    }

    private void addItem(String titleToString, String dateToString, String amountToString) {
        ContentValues cv = new ContentValues();
        cv.put(expensesContract.expensesEntry.COLUMN_TITLE, titleToString);
        cv.put(expensesContract.expensesEntry.COLUMN_DATE, dateToString);
        cv.put(expensesContract.expensesEntry.COLUMN_AMOUNT, amountToString);
        mDatabase.insert(expensesContract.expensesEntry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());
    }

    private double sumOfAllItems() {
        String summenString;
        //database query to get the Sum of all table entries
        String query = "SELECT SUM(amount) as newamount FROM expensesList";
        Cursor cursor = mDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            //get the String from the Cursor
            summenString = cursor.getString(cursor.getColumnIndex("newamount"));
            double castSummeStringToDouble = Double.parseDouble(summenString);
            return castSummeStringToDouble;

        }

        return 0;
    }
}
