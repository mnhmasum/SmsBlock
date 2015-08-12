package com.codersact.smsblock.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import activity.masum.com.smsblock.R;

import com.codersact.smsblock.db.CommonDbMethod;
import com.codersact.smsblock.model.SmsData;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private ArrayList<SmsData> mDataset = new ArrayList<>();
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public TextView mTextDesc;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.person_name);
            mTextDesc = (TextView) v.findViewById(R.id.person_age);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<SmsData> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sms_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        holder.mTextView.setText(mDataset.get(position).getSmsAddress());
        holder.mTextDesc.setText(mDataset.get(position).getSmsString());
        holder.mTextDesc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new CommonDbMethod(context).addToSMSBlacklist(mDataset.get(position).getSmsNo(), mDataset.get(position).getSmsAddress(), mDataset.get(position).getSmsString().trim());

            }

        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}