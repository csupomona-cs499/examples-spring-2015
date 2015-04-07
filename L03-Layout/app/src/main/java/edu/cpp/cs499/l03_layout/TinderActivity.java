package edu.cpp.cs499.l03_layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs499.l03_layout.user.User;

/**
 * Created by yusun on 4/7/15.
 */
public class TinderActivity extends Activity {

    private List<User> users;
    private int userIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tinder_demo);

        // init users
        users = new ArrayList<User>();
        users.add(new User("Alex", 23, R.drawable.s_1, 10, 4 ,7));
        users.add(new User("Amy", 20, R.drawable.s_2, 12, 6 ,17));
        users.add(new User("Sarah", 24, R.drawable.s_3, 20, 6 ,27));
        users.add(new User("Zach", 26, R.drawable.s_4, 30, 16 ,7));

        ImageButton button = (ImageButton) findViewById(R.id.likeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViews();
            }
        });
    }

    private void updateViews() {
        userIndex++;
        if (userIndex < users.size()) {
            User user = users.get(userIndex);

            ImageView imageView = (ImageView) findViewById(R.id.photoView);
            TextView nameAgeTextView = (TextView) findViewById(R.id.nameAgeText);
            TextView photosTextView = (TextView) findViewById(R.id.photosText);
            TextView messagesTextView = (TextView) findViewById(R.id.messagesText);
            TextView friendsTextView = (TextView) findViewById(R.id.friendsText);

            nameAgeTextView.setText(user.getName() + "," + user.getAge());
            photosTextView.setText(user.getPhotos() + "");
            messagesTextView.setText(user.getMessages() + "");
            friendsTextView.setText(user.getFriends() + "");

            imageView.setImageResource(user.getPhotoRes());
        }
    }
}
