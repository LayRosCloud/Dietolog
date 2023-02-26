package com.example.mydietolog.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mydietolog.model.Exercise;
import com.example.mydietolog.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ReaderDatabase {
    final DataContext _data = new DataContext();

    public void readUser(String login, final DataContext.DataLoad status){
        _data.DataUsers.
                child(login.toLowerCase()).
                get().
                addOnCompleteListener(
                task -> {
                    addExercises(Objects.requireNonNull(createUser(login, task)), status);
                });
    }

    private User createUser(String login,Task<DataSnapshot> task){
        if(task.isSuccessful()){
            if(task.getResult().exists()){
                DataSnapshot dataSnapshot = task.getResult();

                String email = String.valueOf(dataSnapshot.child(Contants.User.EMAIL).getValue());
                String password = String.valueOf(dataSnapshot.child(Contants.User.PASSWORD).getValue());

                String ageTemp = String.valueOf(dataSnapshot.child(Contants.User.AGE).getValue());
                String weightTemp = String.valueOf(dataSnapshot.child(Contants.User.WEIGHT).getValue());
                String heightTemp = String.valueOf(dataSnapshot.child(Contants.User.HEIGHT).getValue());
                String spentCaloriesTemp = String.valueOf(dataSnapshot.child(Contants.User.SPENT_CALORIES).getValue());

                int age = Integer.parseInt(ageTemp);
                double weight = Double.parseDouble(weightTemp);
                double height = Double.parseDouble(heightTemp);
                double spentCalories = Double.parseDouble(spentCaloriesTemp);

                User user = new User(login.toLowerCase(Locale.ROOT),
                        email,
                        password,
                        age,
                        weight,
                        height,
                        spentCalories);

                return user;
            }
            else{
                Log.w("FindUser", String.format("User %s is not exist", login.toLowerCase(Locale.ROOT)));
            }
        }
        return null;
    }

    private void addExercises(User user, final DataContext.DataLoad status){
        _data.DataUsers.child(user.getLogin()).child(Contants.User.EXERCISES).
        addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Exercise> exercises = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()){
                    String description = String.valueOf(
                            data.child(Contants.Exercise.DESCRIPTION).getValue());
                    String title = String.valueOf(
                            data.child(Contants.Exercise.TITLE).getValue());

                    int image = Integer.parseInt(String.valueOf(
                            data.child(Contants.Exercise.IMAGE).getValue()));
                    int spentCalories = Integer.parseInt(String.valueOf(
                            data.child(Contants.Exercise.SPENT_CALORIES).getValue()));

                    Exercise exercise = new Exercise(image, title, description, spentCalories);

                    exercises.add(exercise);
                }
                user.addExercise(exercises);
                status.loaded(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void readAllRecepts(final DataContext.DataLoadRecept status){
        _data.DataRecepts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
