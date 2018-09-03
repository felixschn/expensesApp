package com.example.felix.ausgaben4;

import android.provider.BaseColumns;

public class expensesContract {
    private expensesContract(){}

    public static class expensesEntry implements BaseColumns{
        public static final String TABLE_NAME = "expensesList";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
