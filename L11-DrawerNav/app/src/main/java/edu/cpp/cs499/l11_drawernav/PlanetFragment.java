package edu.cpp.cs499.l11_drawernav;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yusun on 4/21/15.
 */
public class PlanetFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        TextView text = (TextView) view.findViewById(R.id.textView);
        int position = this.getArguments().getInt("position");
        text.setText("Fragment " + position);
        return view;
    }

}
