package com.example.mydietolog.data;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StorageController {
    final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    final StorageReference storage = firebaseStorage.getReference();

    public StorageReference get(){
        return storage;
    }
}
