package com.example.final_project.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    public List<QueryDocumentSnapshot> fetchDataFromFirestore(String collectionName) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = db.collection(collectionName).orderBy("detection", Query.Direction.DESCENDING).get();
        return querySnapshot.get().getDocuments();
    }
}
