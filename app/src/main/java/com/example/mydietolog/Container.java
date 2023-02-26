package com.example.mydietolog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mydietolog.controllers.ExerciseAdapter;
import com.example.mydietolog.model.Exercise;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mydietolog.databinding.ActivityContainerBinding;

public class Container extends AppCompatActivity {

    private ActivityContainerBinding binding;

    private ListView _list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_exercise, R.id.navigation_profile, R.id.navigation_recepts, R.id.navigation_menu)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_container);
        NavigationUI.setupWithNavController(binding.navView, navController);
        _list = findViewById(R.id.exerciseItems);
    }
    public void enterOnSettingsProfile(View view) {

    }
}