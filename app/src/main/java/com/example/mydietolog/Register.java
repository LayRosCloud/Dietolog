package com.example.mydietolog;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydietolog.data.Contants;
import com.example.mydietolog.data.DatabaseHelper;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText _edLogin, _edEmail, _edPass, _edPassRepeat, _edAge, _edWeight, _edHeight;
    FirebaseAuth _mAuth = FirebaseAuth.getInstance();
    SQLiteDatabase db;

    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(getApplicationContext());

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
        String login = _edLogin.getText().toString();
        String email = _edEmail.getText().toString();
        String pass = _edPass.getText().toString();
        String passRepeat = _edPassRepeat.getText().toString();

        int age = Integer.parseInt(_edAge.getText().toString());
        double weight = Double.parseDouble(_edWeight.getText().toString());
        double height = Double.parseDouble(_edHeight.getText().toString());

        String error = "";

        if(login.length() < 4){
            error = Contants.Notifications.REGISTER_PROBLEM_LOGIN;
        }
        else if(!email.contains("@")){
            error = Contants.Notifications.REGISTER_PROBLEM_EMAIL;
        }
        else if(pass.length() < 6){
            error = Contants.Notifications.REGISTER_PROBLEM_PASS;
        }
        else if(!pass.equals(passRepeat)){
            error = Contants.Notifications.REGISTER_PROBLEM_PASSREPEAT;
        }
        else if(age < 0 || age > 200){
            error = Contants.Notifications.REGISTER_PROBLEM_AGE;
        }
        else if(height < 0 || height > 400){
            error = Contants.Notifications.REGISTER_PROBLEM_HEIGHT;
        }
        else if(weight < 0 || weight > 1000){
            error = Contants.Notifications.REGISTER_PROBLEM_WEIGHT;
        }

        if(!error.equals("")){
            Toast.makeText(this,
                    error,
                    Toast.LENGTH_LONG).show();
            return;
        }

        _mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, task -> {
                    if(task.isSuccessful()){
                       Toast.makeText(this, "регистрация успешна", Toast.LENGTH_LONG).show();

                       databaseHelper.insertInUser(login, age, weight, height);
                       Intent intent = new Intent(this, MainActivity.class);

                       startActivity(intent);
                    }
                    else{
                        Toast.makeText(this, "регистрация провалена", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void navigateToAuth(View view){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}