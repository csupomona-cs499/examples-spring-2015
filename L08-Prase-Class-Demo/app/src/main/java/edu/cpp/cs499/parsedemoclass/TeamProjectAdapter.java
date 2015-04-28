package edu.cpp.cs499.parsedemoclass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;

import java.util.List;

/**
 * Created by yusun on 4/22/15.
 */
public class TeamProjectAdapter extends ArrayAdapter<TeamProject> {

    private Context context;
    private List<TeamProject> projects;

    public TeamProjectAdapter(Context context, List<TeamProject> objects) {
        super(context, R.layout.list_item, objects);
        this.context = context;
        this.projects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = (View) inflater.inflate(R.layout.list_item, parent, false);
        TextView text = (TextView) view.findViewById(R.id.contentText);
        text.setText(projects.get(position).getName() + " " + projects.get(position).getMember());

        final ImageView imageView = (ImageView) view.findViewById(R.id.imageItem);
        if (projects.get(position).getTeamImage() != null ) {
            projects.get(position).getTeamImage().getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] bytes, ParseException e) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imageView.setImageBitmap(bitmap);
                }
            });
        }

        return view;
    }
}
