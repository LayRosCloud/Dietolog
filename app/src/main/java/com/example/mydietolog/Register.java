package com.example.mydietolog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydietolog.data.Contants;
import com.example.mydietolog.data.WritableDatabase;
import com.example.mydietolog.model.User;

public class Register extends AppCompatActivity {

    private EditText _edLogin, _edEmail, _edPass, _edPassRepeat, _edAge, _edWeight, _edHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(Contants.LocalData.USER_LOGIN, "");
        ed.apply();

        init();
    }

    private void init(){
        _edLogin = findViewById(R.id.edLogin);
        _edEmail = findViewById(R.id.edEmail);
        _edPass = findViewById(R.id.edPass);
        _edPassRepeat = findViewById(R.id.edPassRepeat);
        _edAge = findViewById(R.id.edAge);
        _edWeight = findViewById(R.id.edWeight);
        _edHeight = findViewById(R.id.edHeight);
    }

    public void registerUser(View view){
        WritableDatabase writer = new WritableDatabase();

        String login = _edLogin.getText().toString();
        String email = _edEmail.getText().toString();
        String pass = _edPass.getText().toString();
        String passRepeat = _edPassRepeat.getText().toString();

        int age = Integer.parseInt(_edAge.getText().toString());
        double weight = Double.parseDouble(_edWeight.getText().toString());
        double height = Double.parseDouble(_edHeight.getText().toString());



        if(login.length() < 4){
            Toast.makeText(this,
                    Contants.Notifications.REGISTER_PROBLEM_LOGIN,
                    Toast.LENGTH_LONG).show();
            return;
        }
        else if(!email.contains("@")){
            Toast.makeText(this,
                    Contants.Notifications.REGISTER_PROBLEM_EMAIL,
                    Toast.LENGTH_LONG).show();
            return;
        }
        else if(pass.length() < 4){
            Toast.makeText(this,
                    Contants.Notifications.REGISTER_PROBLEM_PASS,
                    Toast.LENGTH_LONG).show();
            return;
        }
        else if(!pass.equals(passRepeat)){
            Toast.makeText(this,
                    Contants.Notifications.REGISTER_PROBLEM_PASSREPEAT,
                    Toast.LENGTH_LONG).show();
            return;
        }
        else if(age < 0 || age > 200){
            Toast.makeText(this,
                    Contants.Notifications.REGISTER_PROBLEM_AGE,
                    Toast.LENGTH_LONG).show();
            return;
        }
        else if(height < 0 || height > 400){
            Toast.makeText(this,
                    Contants.Notifications.REGISTER_PROBLEM_HEIGHT,
                    Toast.LENGTH_LONG).show();
            return;
        }
        else if(weight < 0 || weight > 1000){
            Toast.makeText(this,
                    Contants.Notifications.REGISTER_PROBLEM_WEIGHT,
                    Toast.LENGTH_LONG).show();
            return;
        }

        User user = new User(login, email, pass, age, weight, height, 0);

        writer.write(user);

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void navigateToAuth(View view){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}