package com.example.mydietolog.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mydietolog.Container;
import com.example.mydietolog.controllers.ExerciseAdapter;
import com.example.mydietolog.databinding.FragmentHomeBinding;
import com.example.mydietolog.model.Exercise;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        final ListView listView = binding.exerciseItems;

        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        homeViewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                ExerciseAdapter exerciseAdapter = new ExerciseAdapter(getContext(), exercises);
                listView.setAdapter(exerciseAdapter);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}