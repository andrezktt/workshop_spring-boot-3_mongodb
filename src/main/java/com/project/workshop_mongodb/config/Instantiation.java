package com.project.workshop_mongodb.config;

import com.project.workshop_mongodb.domain.User;
import com.project.workshop_mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User mike = new User(null, "Killer Mike", "k.mike@gmail.com");
        User kdot = new User(null, "Kendrick Lamar", "kdot@gmail.com");
        User billy = new User(null, "Billy Woods", "b.woods@gmail.com");

        userRepository.saveAll(Arrays.asList(mike, kdot, billy));
    }
}
