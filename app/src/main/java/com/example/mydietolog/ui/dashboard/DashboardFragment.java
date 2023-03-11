package com.example.mydietolog.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mydietolog.databinding.FragmentDashboardBinding;

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
        dashboardViewModel.getSpentCalories().observe(getViewLifecycleOwner(), tvSpentCalories::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}