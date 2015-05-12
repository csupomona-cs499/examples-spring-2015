package edu.cpp.cs499.l16_ui_responsiveness;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yusun on 5/12/15.
 */
public class MyListViewAdapter extends ArrayAdapter<MyData> {

    private Context context;
    public MyListViewAdapter(Context context, List<MyData> objects) {
        super(context, R.layout.list_view_item, objects);
        this.context = context;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.list_view_item, parent, false);
//
//        Log.i("TEST", "Inflating item view for postion " + position);
//
//        TextView textView1 = (TextView) view.findViewById(R.id.text1);
//        textView1.setText(getItem(position).getName());
//        TextView textView2 = (TextView) view.findViewById(R.id.text2);
//        textView2.setText(getItem(position).getMember());
//
//        return view;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_item, parent, false);
            Log.i("TEST", "Inflating item view for position " + position);

            holder = new ViewHolder();
            holder.textView1 = (TextView) view.findViewById(R.id.text1);
            holder.textView2 = (TextView) view.findViewById(R.id.text2);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.textView1.setText(getItem(position).getName());
        holder.textView2.setText(getItem(position).getMember());

        return view;
    }

    class ViewHolder {
        TextView textView1;
        TextView textView2;
    }
}
