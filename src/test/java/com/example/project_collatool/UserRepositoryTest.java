package com.example.project_collatool;

import com.example.project_collatool.db.UserEntity;
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
                        .uId("static/test" +i)
                        .uPwd("static/test" +i)
                        .uEmail("static/test" +i)
                        .uName("test..."+i)
                        .uJumin(""+i)
                        .uPhone(100+i+"")
                        .uSignout(0)
                        .build();
            userRepository.save(user);
        });
    }

    @Test
    public void test1(){
        UserEntity user = UserEntity.builder()
                .uId("static/test" +1)
                .uPwd("static/test" +1)
                .uEmail("static/test" +1)
                .uName("test..."+1)
                .uJumin("1")
                .uPhone(100+1+"")
                .uSignout(0)
                .build();
        userRepository.save(user);
    }
}
