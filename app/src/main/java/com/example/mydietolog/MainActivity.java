package com.example.mydietolog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydietolog.data.DatabaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText edLogin, edPassword;
    FirebaseAuth _mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        helper.onCreate(helper.getWritableDatabase());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
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

        login = login.toLowerCase().trim();

        final String finalLogin = login;
        _mAuth.signInWithEmailAndPassword(login, password).
                addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Intent intent = new Intent(this, Container.class);
                intent.putExtra("login", finalLogin);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Авторизация не выполнена", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void navigateToRegister(View view){
        Intent intent = new Intent(this, Register.class);

        startActivity(intent);
    }
    
}