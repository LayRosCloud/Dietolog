package com.example.mydietolog.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydietolog.R;
import com.example.mydietolog.model.Exercise;

import java.util.List;

public class ExerciseAdapter extends BaseAdapter {

    Context _context;
    List<Exercise> _exercises;
    LayoutInflater _inflater;

    public ExerciseAdapter(Context context, List<Exercise> exercises){
        _context = context;
        _exercises = exercises;
        _inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return _exercises.size();
    }

    @Override
    public Exercise getItem(int i) {
        return _exercises.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = _inflater.inflate(R.layout.activity_exercise_listview, null);

        TextView title = (TextView) view.findViewById(R.id.title_exercise);
        TextView description = (TextView) view.findViewById(R.id.description_exercise);

        ImageView image = (ImageView) view.findViewById(R.id.imageIcon);

        title.setText(_exercises.get(i).getTitle());
        description.setText(_exercises.get(i).getDescription());
        image.setImageResource(_exercises.get(i).getImage());

        return view;
    }
}
