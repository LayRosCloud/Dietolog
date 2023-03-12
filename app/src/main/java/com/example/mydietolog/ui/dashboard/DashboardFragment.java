package com.example.mydietolog.ui.dashboard;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mydietolog.data.Contants;
import com.example.mydietolog.data.DatabaseHelper;
import com.example.mydietolog.databinding.FragmentDashboardBinding;
import com.example.mydietolog.model.Exercise;
import com.example.mydietolog.model.UserExercises;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        final TextView tvHeight = binding.tvHeight;
        final TextView tvWeight = binding.tvWeight;
        final TextView tvAge = binding.tvAge;
        final TextView tvSpentCalories = binding.tvSpentCalories;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        dashboardViewModel.getHeight().observe(getViewLifecycleOwner(), tvHeight::setText);
        dashboardViewModel.getWeight().observe(getViewLifecycleOwner(), tvWeight::setText);
        dashboardViewModel.getAge().observe(getViewLifecycleOwner(), tvAge::setText);

        double spentCalories = Contants.CurrentUser.getSpentCalories();


        DatabaseHelper helper = new DatabaseHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT e.SpentCalories, eu.Number\n" +
                                "FROM ExerciseUser eu INNER JOIN Exercise e ON eu.ExerciseID = e._id\n" +
                                "WHERE UserID = " + Contants.CurrentUser.getId(),
                        null);

        while(cursor.moveToNext()) {
            double spentCaloriesExercise = cursor.getDouble(0);
            int number = cursor.getInt(1);

            spentCalories += spentCaloriesExercise * number;
        }

        tvSpentCalories.setText(String.valueOf(Math.round(spentCalories)));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}