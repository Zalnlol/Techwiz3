package fpt.aptech.KSS;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import java.security.SecureRandom;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class KssApplication {
    
    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("khareersmartstudy-firebase-adminsdk-k3bsp-e6182fff7f.json").getInputStream());
        SecureRandom random = new SecureRandom();
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(googleCredentials)
                .build();
        String name = String.valueOf(random.nextInt());
        FirebaseApp app = FirebaseApp.initializeApp(options,name);
//        FirebaseApp otherApp = FirebaseApp.initializeApp(otherAppConfig, "other");
        return FirebaseMessaging.getInstance(app);
    }
    public static void main(String[] args) {
        SpringApplication.run(KssApplication.class, args);
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

}
