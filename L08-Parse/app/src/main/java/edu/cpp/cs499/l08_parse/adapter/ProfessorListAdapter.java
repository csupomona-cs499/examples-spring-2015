package edu.cpp.cs499.l08_parse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import edu.cpp.cs499.l08_parse.R;
import edu.cpp.cs499.l08_parse.data.Professor;

/**
 * Created by yusun on 3/31/15.
 */
public class ProfessorListAdapter extends ArrayAdapter<Professor> {

    Context context;
    List<Professor> professors;

    public ProfessorListAdapter(Context context, List<Professor> objects) {
        super(context, R.layout.list_item_professor, objects);
        this.context = context;
        this.professors = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_professor, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.nameInItem);
        textView.setText(professors.get(position).getName());

        Button button = (Button) view.findViewById(R.id.deleteButtonInItem);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete the object from Parse database
                Professor p = professors.get(position);
                p.deleteInBackground();
                // delete the object from the list adapter
                professors.remove(p);
                notifyDataSetChanged();
            }
        });

        return view;
    }

}
