package com.example.mydietolog.data;

import com.example.mydietolog.model.Recept;
import com.example.mydietolog.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DataContext {
    private final FirebaseDatabase _database = FirebaseDatabase.getInstance();

    public final DatabaseReference DataUsers = _database.getReference(Contants.User.TABLE);
    public final DatabaseReference DataRecepts = _database.getReference(Contants.User.TABLE);

    public interface DataChanged{
        void changed();
    }
    public interface DataLoad{ void loaded(User user);}
    public interface DataLoadRecept{void loaded(List<Recept> recept);}
}
