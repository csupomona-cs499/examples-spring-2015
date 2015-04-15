package edu.cpp.cs499.l06_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yusun on 4/15/15.
 */
public class FriendAdapter extends ArrayAdapter<Friend> {

    private Context context;
    private ArrayList<Friend> friends;

    public FriendAdapter(Context context, ArrayList<Friend> friends) {
        super(context, R.layout.frient_item, friends);
        this.context = context;
        this.friends = friends;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.frient_item, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.friendImage);
        imageView.setImageResource(friends.get(position).getPhotoRes());

        TextView nameText = (TextView) view.findViewById(R.id.nameText);
        nameText.setText(friends.get(position).getName());

        TextView numText = (TextView) view.findViewById(R.id.numText);
        numText.setText(friends.get(position).getNumMutFriends() + " mutual friends");

        Button addButton = (Button) view.findViewById(R.id.addFriendButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friends.remove(position);
                FriendAdapter.this.notifyDataSetChanged();
            }
        });
        return view;
    }
}
