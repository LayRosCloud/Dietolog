package com.example.mydietolog;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mydietolog.data.Contants;
import com.example.mydietolog.data.DatabaseHelper;
import com.example.mydietolog.databinding.FragmentAddExerciseViewBinding;
import com.example.mydietolog.model.Exercise;
import com.example.mydietolog.model.UserExercises;

import java.util.ArrayList;
import java.util.List;

public class AddExerciseView extends Fragment {

    private FragmentAddExerciseViewBinding binding;

    public static AddExerciseView newInstance() {
        return new AddExerciseView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AddExerciseViewViewModel vm =
                new ViewModelProvider(this).get(AddExerciseViewViewModel.class);

        binding = FragmentAddExerciseViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button btn = binding.addExercise;
        final EditText edCount = binding.etCountRepeat;
        final Spinner spExercises = binding.spinnerOfExercise;
        final TextView tvDescription = binding.tvDescription;
        final ImageView imageView = binding.exerciseImage;

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Exercise", null);
        List<String> exercisesStr = new ArrayList<>();
        List<Exercise> exercises = new ArrayList<>();

        cursor.moveToNext();
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            double spentCalories = cursor.getDouble(3);
            int image = cursor.getInt(4);

            exercisesStr.add(name);
            exercises.add(new Exercise(id, image, name, description, spentCalories));
            cursor.moveToNext();
        }

        ArrayAdapter<String> autoBaseAdapter;
        autoBaseAdapter = new ArrayAdapter<String>(getContext(), simple_spinner_item, exercisesStr);

        autoBaseAdapter.setDropDownViewResource(simple_spinner_dropdown_item);

        spExercises.setAdapter(autoBaseAdapter);

        spExercises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvDescription.setText(exercises.get(i).getDescription());
                imageView.setImageResource(exercises.get(i).getImage());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn.setOnClickListener(view -> {
            Cursor cursor1 = db.rawQuery("SELECT * FROM Exercise WHERE Name LIKE \'"
                    + spExercises.getSelectedItem().toString() + "\'", null);
            cursor1.moveToNext();
            int id = cursor1.getInt(0);
            String name = cursor1.getString(1);
            String description = cursor1.getString(2);
            double spentCalories = cursor1.getDouble(3);
            int image = cursor1.getInt(4);
            final int valueRepeat = Integer.parseInt(edCount.getText().toString());

            Exercise exercise = new Exercise(id, image, name, description, spentCalories);
            UserExercises userExercises = new UserExercises(Contants.CurrentUser);
            userExercises.addExercise(db, exercise, valueRepeat);

            NavController hostFragment = NavHostFragment.findNavController(this);
            hostFragment.navigate(R.id.action_addExerciseView_to_navigation_exercise);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}