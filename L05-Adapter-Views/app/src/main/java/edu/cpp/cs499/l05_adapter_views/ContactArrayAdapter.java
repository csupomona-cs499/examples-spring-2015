package edu.cpp.cs499.l05_adapter_views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yusun on 3/25/15.
 */
public class ContactArrayAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private List<Contact> contacts;

    public ContactArrayAdapter(Context context, List<Contact> contacts) {
        super(context, R.layout.item_list_view, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public void add(Contact object) {
        contacts.add(object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_list_view, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.textInItem);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageInItem);

        // load image from URL
        // see: http://square.github.io/picasso/
        if (contacts.get(position).getImageUrl() == null
                || contacts.get(position).getImageUrl().isEmpty()) {
            imageView.setImageResource(R.drawable.user_icon);
        } else {
            Picasso.with(context).load(contacts.get(position).getImageUrl()).into(imageView);
        }

        textView.setText(position + " : " + contacts.get(position).getName());
        return rowView;
    }
}
