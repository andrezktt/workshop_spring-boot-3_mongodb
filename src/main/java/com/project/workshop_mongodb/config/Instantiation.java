package com.project.workshop_mongodb.config;

import com.project.workshop_mongodb.domain.Post;
import com.project.workshop_mongodb.domain.User;
import com.project.workshop_mongodb.dto.AuthorDTO;
import com.project.workshop_mongodb.dto.CommentDTO;
import com.project.workshop_mongodb.repository.PostRepository;
import com.project.workshop_mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User mike = new User(null, "Killer Mike", "k.mike@gmail.com");
        User kdot = new User(null, "Kendrick Lamar", "kdot@gmail.com");
        User billy = new User(null, "Billy Woods", "b.woods@gmail.com");

        userRepository.saveAll(Arrays.asList(mike, kdot, billy));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(mike));
        Post post2 = new Post(null, sdf.parse("23/08/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(mike));

        CommentDTO c1 = new CommentDTO("Boa viagem, mano!", sdf.parse("21/03/2018"), new AuthorDTO(kdot));
        CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(billy));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(kdot));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1, post2));

        mike.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(mike);
    }
}
