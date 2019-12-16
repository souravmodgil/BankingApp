package com.bankingapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bankingapp.R;
import com.bankingapp.sql.Accouts;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Accouts> {

    LayoutInflater flater;
    private Context context;

    public CustomAdapter(Activity context, int resouceId, int textviewId, List<Accouts> list) {


        super(context, resouceId, textviewId, list);
        this.context =context;
        flater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Accouts rowItem = getItem(position);

        View rowview = flater.inflate(R.layout.spinner_layout, null, true);

        TextView txtTitle = (TextView) rowview.findViewById(R.id.title);
        txtTitle.setText(rowItem.accountnumber);



        return rowview;
    }
}
