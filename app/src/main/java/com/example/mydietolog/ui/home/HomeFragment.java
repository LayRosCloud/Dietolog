package com.example.mydietolog.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mydietolog.Container;
import com.example.mydietolog.MainActivity;
import com.example.mydietolog.R;
import com.example.mydietolog.controllers.ExerciseAdapter;
import com.example.mydietolog.data.Contants;
import com.example.mydietolog.data.DatabaseHelper;
import com.example.mydietolog.databinding.FragmentHomeBinding;
import com.example.mydietolog.model.Exercise;
import com.example.mydietolog.model.UserExercises;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @SuppressLint("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        final ListView listView = binding.exerciseItems;
        final FloatingActionButton button = binding.floatingActionButton;

        button.setOnClickListener(view -> {
            NavController hostFragment = NavHostFragment.findNavController(this);
            hostFragment.navigate(R.id.action_navigation_exercise_to_addExerciseView);
        });

        DatabaseHelper helper = new DatabaseHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        UserExercises userExercises = new UserExercises(Contants.CurrentUser);

        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(getContext(), userExercises.findExercises(db).getExercises());
        listView.setAdapter(exerciseAdapter);

        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        if(userExercises.getExercises().size() > 0){
            createDynamicFooterButton(db, listView);
        }

        return root;
    }

    private void createDynamicFooterButton(SQLiteDatabase db, ListView listView){
        Button footerButton = new Button(getContext());

        footerButton.setText("Очистить");
        Drawable drawable = getResources().getDrawable(R.drawable.whitebackgroundshape);
        footerButton.setBackground(drawable);

        footerButton.setOnClickListener(view -> {
            db.execSQL("DELETE FROM ExerciseUser WHERE UserID = " + Contants.CurrentUser.getId());
            NavController hostFragment = NavHostFragment.findNavController(this);
            hostFragment.navigate(R.id.action_navigation_exercise_self);
        });
        listView.addFooterView(footerButton);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}