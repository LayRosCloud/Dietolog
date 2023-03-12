package com.example.mydietolog;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydietolog.data.Contants;
import com.example.mydietolog.data.DatabaseHelper;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;

public class Register extends AppCompatActivity {
    private EditText _edLogin, _edEmail, _edPass, _edPassRepeat, _edAge, _edWeight, _edHeight;
    private Switch _sSex;
    private Spinner _activity;
    private FirebaseAuth _mAuth = FirebaseAuth.getInstance();

    private final String[] listOfActivity = {
            "1. Сидячий и малоподвижный",
            "2. Легкая активность (упражнения 1-3 раза в неделю)",
            "3. Средняя активность (упражнения 3-5 раза в неделю)",
            "4. Высокая активность (высокие нагрузки каждый день)",
            "5. Экстремальная активность"
    };

    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseAuth.getInstance().signOut();

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
        _sSex = findViewById(R.id.sex);
        _activity = findViewById(R.id.spActivity);
        ArrayAdapter<String> baseAdapter = new ArrayAdapter<String>(this, simple_spinner_item, listOfActivity);

        baseAdapter.setDropDownViewResource(simple_spinner_dropdown_item);
        _activity.setAdapter(baseAdapter);
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

        boolean sex = _sSex.isChecked();
        int activity = _activity.getSelectedItemPosition();

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

                       double spentCalories = 0;

                       if(sex){
                           spentCalories = 66 + (13.7 * weight) + (5* height)- (6.8 * age);
                       }
                       else{
                           spentCalories = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
                       }

                       double kf = 1.2;

                       switch (activity){
                           case 1:
                               kf = 1.375;
                               break;
                           case 2:
                               kf = 1.55;
                               break;
                           case 3:
                               kf = 1.725;
                               break;
                           case 4:
                               kf = 1.9;
                               break;
                       }
                       spentCalories *= kf;

                       DatabaseHelper helper = new DatabaseHelper(this);
                       SQLiteDatabase db = helper.getWritableDatabase();

                        ContentValues cv = new ContentValues();
                        cv.put("Login", login);
                        cv.put("email", email);
                        cv.put("Age", age);
                        cv.put("Weight", weight);
                        cv.put("Height", height);
                        cv.put("SpentCalories", spentCalories);
                        cv.put("Sex", sex);
                        cv.put("Activity", activity);

                       db.insert(Contants.SQL.TABLE_USER, null, cv);

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