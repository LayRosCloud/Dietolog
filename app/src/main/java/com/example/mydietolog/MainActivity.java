package com.example.mydietolog;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydietolog.data.Contants;
import com.example.mydietolog.data.DatabaseHelper;
import com.example.mydietolog.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText edLogin, edPassword;
    FirebaseAuth _mAuth = FirebaseAuth.getInstance();

    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        DatabaseHelper mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        }
        catch (SQLException mSQLException) {
            throw mSQLException;
        }


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){

            getCurrentUser(Objects.requireNonNull(user.getEmail()));

            Intent intent = new Intent(MainActivity.this, Container.class);

            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        edLogin = findViewById(R.id.edLogin);
        edPassword = findViewById(R.id.pass);
    }

    public void auth(View view) {
        String login = edLogin.getText().toString();
        String password = edPassword.getText().toString();

        login = login.toLowerCase().trim();

        final String finalLogin = login;
        _mAuth.signInWithEmailAndPassword(login, password).
                addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        getCurrentUser(finalLogin);
                        Intent intent = new Intent(this, Container.class);

                        startActivity(intent);
                    } else {
                        Toast.makeText(this, Contants.Notifications.NOT_COMPLETE_AUTH, Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void getCurrentUser(String email){
        Cursor cursor = mDb.rawQuery("SELECT * FROM User WHERE email LIKE \'" + email +"\'", null);

        if(cursor.getCount() > 0){
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String login = cursor.getString(1);
            int age = cursor.getInt(2);
            double weight = cursor.getDouble(3);
            double height = cursor.getDouble(4);
            double spentCalories = cursor.getDouble(5);
            int sex = cursor.getInt(6);
            int activity = cursor.getInt(7);

            Contants.CurrentUser = new User(id, login, email, "", age, weight, height, spentCalories, sex, activity);
        }
    }

    public void navigateToRegister(View view){
        Intent intent = new Intent(this, Register.class);

        startActivity(intent);
    }
}