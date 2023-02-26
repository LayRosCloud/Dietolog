package com.example.mydietolog.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydietolog.R;
import com.example.mydietolog.model.Ingridient;
import com.example.mydietolog.model.Recept;

import java.util.ArrayList;
import java.util.List;

public class ReceptAdapter extends BaseAdapter {
    private final List<Recept> _recepts;
    final LayoutInflater _inflater;
    final Context _context;
    public ReceptAdapter(Context context, List<Recept> recepts){
        _recepts = recepts;
        _context = context;
        _inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return _recepts.size();
    }

    @Override
    public Recept getItem(int i) {
        return _recepts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = _inflater.inflate(R.layout.activity_recepts_listview, null);

        TextView title = view.findViewById(R.id.titleRecept);
        TextView description = view.findViewById(R.id.descriptionRecept);

        TextView carbohydrates = view.findViewById(R.id.carbohydrates);
        TextView calories = view.findViewById(R.id.calories);
        TextView fats = view.findViewById(R.id.fats);
        TextView protein = view.findViewById(R.id.protein);
        ImageView image = view.findViewById(R.id.imageIcon);

        Recept recept = _recepts.get(i);

        carbohydrates.setText(String.valueOf(recept.getCarbohydrates()));
        calories.setText(String.valueOf(recept.getCalories()));
        fats.setText(String.valueOf(recept.getFats()));
        protein.setText(String.valueOf(recept.getProtein()));
        title.setText(recept.getTitle());
        description.setText(recept.getTitle());

        int index = 0;
        return view;
    }
}
