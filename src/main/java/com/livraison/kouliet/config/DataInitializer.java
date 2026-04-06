package com.livraison.kouliet.config;
import com.livraison.kouliet.model.Role;
import com.livraison.kouliet.model.User;
import com.livraison.kouliet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            // vérifier si l'utilisateur existe déjà
            if (userRepository.findByEmail("admin@kouliet.com").isEmpty()) {

                User user = new User();
                user.setEmail("admin@kouliet.com");
                user.setPassword(passwordEncoder.encode("admin123"));
                user.setNom("Admin");
                user.setPrenom("Super");
                user.setTelephone("12345678");
                user.setAdresse("Tunis");
                user.setRole(Role.ADMIN);

                userRepository.save(user);

                System.out.println("✅ Utilisateur admin créé !");
            }
        };
    }
}