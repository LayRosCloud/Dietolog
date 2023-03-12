package com.example.mydietolog.ui.notifications;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mydietolog.R;
import com.example.mydietolog.controllers.ExerciseAdapter;
import com.example.mydietolog.controllers.ReceptAdapter;
import com.example.mydietolog.data.DatabaseHelper;
import com.example.mydietolog.databinding.FragmentNotificationsBinding;
import com.example.mydietolog.model.Recept;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotificationsFragment extends Fragment {
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        DatabaseHelper helper = new DatabaseHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        final TextView textView = binding.textNotifications;
        final FloatingActionButton button = binding.navigateToCreateView;

        final ListView recepts = binding.receptsList;

        ReceptAdapter receptAdapter = new ReceptAdapter(getContext(), Recept.getRecepts(db));
        recepts.setAdapter(receptAdapter);

        button.setOnClickListener(view -> {
            NavController hostFragment = NavHostFragment.findNavController(this);
            hostFragment.navigate(R.id.action_navigation_recepts_to_createRecept);
        });

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}