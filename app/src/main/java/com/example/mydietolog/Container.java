package com.example.mydietolog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mydietolog.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mydietolog.databinding.ActivityContainerBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Container extends AppCompatActivity {

    private ActivityContainerBinding binding;

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
        View fragment = findViewById(R.id.nav_host_fragment_activity_container);
        navController.getContext();

        NavigationUI.setupWithNavController(binding.navView, navController);

    }
    public void exitFromProfile(View view){
        openQuitDialog();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                this);
        quitDialog.setTitle("Вы действительно хотите выйти?");

        quitDialog.setPositiveButton("Выйти", (dialog, which) ->{
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        });

        quitDialog.setNegativeButton("Остаться", (dialog, which) -> {

        });

        quitDialog.show();
    }
}