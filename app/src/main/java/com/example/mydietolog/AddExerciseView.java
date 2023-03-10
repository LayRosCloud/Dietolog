package com.example.mydietolog;

import static android.R.*;
import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mydietolog.databinding.FragmentAddExerciseViewBinding;
import com.example.mydietolog.databinding.FragmentDashboardBinding;
import com.example.mydietolog.ui.dashboard.DashboardViewModel;

public class AddExerciseView extends Fragment {

    private FragmentAddExerciseViewBinding binding;

    public static AddExerciseView newInstance() {
        return new AddExerciseView();
    }
    final private String[] allListItems = {"Приседания", "Отжимания", "Пресс", "Планка"};
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

        ArrayAdapter<String> autoBaseAdapter;
        autoBaseAdapter = new ArrayAdapter<String>(getContext(), simple_spinner_item, allListItems);

        autoBaseAdapter.setDropDownViewResource(simple_spinner_dropdown_item);

        spExercises.setAdapter(autoBaseAdapter);

        btn.setOnClickListener(view -> {
            if(spExercises.isSelected()){
                switch(spExercises.getSelectedItemPosition()){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
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