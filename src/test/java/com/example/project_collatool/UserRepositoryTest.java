package com.example.project_collatool;

import com.example.project_collatool.dto.UserEntity;
import com.example.project_collatool.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void InsertDummies(){
        IntStream.rangeClosed(1,10).forEach(i -> {
            UserEntity user = UserEntity.builder()
                        .uName("test..."+i)
                        .build();
            userRepository.save(user);
        });
    }
}
