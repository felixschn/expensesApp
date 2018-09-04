package com.example.felix.ausgaben4;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class expensesAdapter extends RecyclerView.Adapter<expensesAdapter.expensesViewHolder> {

    private Cursor mCursor;
    private Context mContext;

    public expensesAdapter(Context context, Cursor cursor){
        mCursor = cursor;
        mContext = context;
    }

    public class expensesViewHolder extends RecyclerView.ViewHolder{
        public TextView titleText;
        public TextView dateText;
        public TextView amountText;

        public expensesViewHolder(View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.title);
            dateText = itemView.findViewById(R.id.date);
            amountText = itemView.findViewById(R.id.amount);
        }
    }

    @NonNull
    @Override
    public expensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.ausgaben_item, parent, false);
        return  new expensesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull expensesViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))
            return;

        String title = mCursor.getString(mCursor.getColumnIndex(expensesContract.expensesEntry.COLUMN_TITLE));
        String date = mCursor.getString(mCursor.getColumnIndex(expensesContract.expensesEntry.COLUMN_DATE));
        int amount = mCursor.getInt(mCursor.getColumnIndex(expensesContract.expensesEntry.COLUMN_AMOUNT));
        //add ID for the swipe-delete function in the MainActivity, to identifier the right item from the database
        long id = mCursor.getLong(mCursor.getColumnIndex(expensesContract.expensesEntry._ID));
        holder.titleText.setText(title);
        holder.dateText.setText(date);
        holder.amountText.setText(String.valueOf(amount));
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor (Cursor newCursor){
        if (mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;

        if(newCursor != null){
            notifyDataSetChanged();
        }


    }

}
