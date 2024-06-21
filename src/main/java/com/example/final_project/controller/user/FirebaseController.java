package com.example.final_project.controller.user;

import com.example.final_project.service.FirebaseService;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class FirebaseController {

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping("/data/detections")
    public List<Map<String, Object>> getDetectionData() throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> documents = firebaseService.fetchDataFromFirestore("detections");
        List<Map<String, Object>> data = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            data.add(document.getData());
        }
        return data;
    }
}
