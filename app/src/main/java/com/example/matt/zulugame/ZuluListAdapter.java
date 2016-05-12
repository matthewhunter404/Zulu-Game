package com.example.matt.zulugame;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by matt on 2016/05/10.
 */
public class ZuluListAdapter extends ArrayAdapter<DisplayAnswer> {

    Context context;
    int layoutResourceId;
    DisplayAnswer data[] = null;

    public ZuluListAdapter(Context context, int layoutResourceId, int textlayoutResourceId,  DisplayAnswer[] data) {
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

            row.setTag(holder);
        }
        else
        {
            holder = (ZuluAdapterHolder)row.getTag();
        }
        holder = new ZuluAdapterHolder();
        holder.answers_list_item_textview = (TextView)row.findViewById(R.id.answers_list_item_textview);
        if (data[position].getCorrect()==true){
            holder.answers_list_item_textview.setBackgroundResource(R.drawable.touch_selector_right);
            //Log.v("Green","index");
        }
        else {
            holder.answers_list_item_textview.setBackgroundResource(R.drawable.touch_selector_wrong);
            //Log.v("Red", "index");
        }
        String displaytext = data[position].getAnswer();
        holder.answers_list_item_textview.setText(displaytext);
        return row;
    }

    static class ZuluAdapterHolder
    {
        TextView answers_list_item_textview;
    }
}

