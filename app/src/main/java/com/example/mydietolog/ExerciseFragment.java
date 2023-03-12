package com.example.mydietolog;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mydietolog.data.Contants;
import com.example.mydietolog.databinding.FragmentExerciseBinding;
import com.example.mydietolog.databinding.FragmentNotificationsBinding;
import com.example.mydietolog.ui.notifications.NotificationsViewModel;

public class ExerciseFragment extends Fragment {

    private FragmentExerciseBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentExerciseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView tvTitle = binding.menuTitle;
        final TextView tvEmail = binding.menuEmail;

        tvTitle.setText(Contants.CurrentUser.getLogin());
        tvEmail.setText(Contants.CurrentUser.getEmail());



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}