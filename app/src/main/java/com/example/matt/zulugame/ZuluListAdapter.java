package com.example.matt.zulugame;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 2016/05/10.
 */
public class ZuluListAdapter extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    String data[] = null;

    public ZuluListAdapter(Context context, int layoutResourceId, int textlayoutResourceId,  String[] data) {
        super(context, layoutResourceId,textlayoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ZuluAdapterHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ZuluAdapterHolder();
            holder.answers_list_item_textview = (TextView)row.findViewById(R.id.answers_list_item_textview);
            holder.answers_list_item_textview.setBackgroundResource(R.drawable.touch_selector2);
            row.setTag(holder);
        }
        else
        {
            holder = (ZuluAdapterHolder)row.getTag();
        }

        String displaytext = data[position];
        holder.answers_list_item_textview.setText(displaytext);

        return row;
    }

    static class ZuluAdapterHolder
    {
        TextView answers_list_item_textview;
    }
}

