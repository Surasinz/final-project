package com.example.final_project.exception;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class FirebaseConfig {

    private static final Logger logger = Logger.getLogger(FirebaseConfig.class.getName());

    @Bean
    public FirebaseApp initializeFirebaseApp() throws IOException {
        logger.log(Level.INFO, "Initializing Firebase...");

        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("finalproject-f1a6e-firebase-adminsdk-rb9vv-375df5d548.json");

        if (serviceAccount == null) {
            throw new IOException("File not found");
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://finalproject-f1a6e-default-rtdb.firebaseio.com")
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
