package com.example.mydietolog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydietolog.data.Contants;
import com.example.mydietolog.data.DataContext;
import com.example.mydietolog.data.ReaderDatabase;
import com.example.mydietolog.data.WritableDatabase;
import com.example.mydietolog.model.Exercise;
import com.example.mydietolog.model.User;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edLogin, edPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(Contants.LocalData.USER_LOGIN, "");

        if(!savedText.isEmpty() || !(savedText == "")){
            Intent intent = new Intent(MainActivity.this, Container.class);

            startActivity(intent);
        }

        init();
    }

    private void init(){
        edLogin = findViewById(R.id.edLogin);
        edPassword = findViewById(R.id.pass);
    }

    public void auth(View view){
        String login = edLogin.getText().toString();
        String password = edPassword.getText().toString();

        new ReaderDatabase().readUser(login.toLowerCase(Locale.ROOT), user -> {
            if (user != null) {
                Log.d("UserFinding", "User is exists" + user.getLogin());

                if (password.equals(user.getPassword())) {
                    Toast.makeText(
                            MainActivity.this,
                            Contants.Notifications.COMPLETE_AUTH,
                            Toast.LENGTH_LONG).show();
                    SharedPreferences sPref = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString(Contants.LocalData.USER_LOGIN, user.getLogin());
                    ed.apply();

                    Intent intent = new Intent(MainActivity.this, Container.class);

                    startActivity(intent);
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            Contants.Notifications.NOT_COMPLETE_AUTH,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void navigateToRegister(View view){
        Intent intent = new Intent(this ,Register.class);

        startActivity(intent);
    }
    
}