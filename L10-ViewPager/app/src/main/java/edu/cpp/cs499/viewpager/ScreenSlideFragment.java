package edu.cpp.cs499.viewpager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yusun on 4/21/15.
 */
public class ScreenSlideFragment extends Fragment {

    private String name;
    private int photoRes;

    public ScreenSlideFragment(String name, int photoRes) {
        this.name = name;
        this.photoRes = photoRes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_slide_screen, container, false);
        TextView t = (TextView) viewGroup.findViewById(R.id.textView);
        t.setText(name);
        ImageView image = (ImageView) viewGroup.findViewById(R.id.userImage);
        image.setImageResource(photoRes);

        return viewGroup;
    }

}
