package com.example.project_collatool;

import com.example.project_collatool.db.BoardEntity;
import com.example.project_collatool.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class BoardTests {
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void testBoard(){
        for(int i=1; i<=300; i++){

            String title = String.format("테스트 테이타입니다.[%03d]",i);
            String content = "내용 없을 무 "+i;
            BoardEntity entity = BoardEntity.builder()
                    .bProjectId(1)
                    .bUId("aaa")
                    .bTitle(title)
                    .bContent(content)
                    .bCreated(LocalDateTime.now())
                    .build();
            this.boardRepository.save(entity);
        }
    }
}
