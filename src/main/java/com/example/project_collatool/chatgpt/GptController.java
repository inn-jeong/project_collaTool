package com.example.project_collatool.chatgpt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.example.project_collatool.db.AiRequestEntity;
import com.example.project_collatool.dto.AiRequestDto;
import com.example.project_collatool.service.AiRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/chatgpt")
@RequiredArgsConstructor
public class GptController {
    private final RestTemplate restTemplate;
    private final AiRequestService aiRequestService;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.max-completions}")
    private int maxCompletions;

    @Value("${openai.temperature}")
    private double temperature;

    @Value("${openai.max_tokens}")
    private int maxTokens;

    @Value("${openai.api.url}")
    private String apiUrl;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/chat")
    public GptResponse chat(@RequestParam("prompt") String prompt, @RequestParam("projectId") Integer projectId) {
        log.info("@# gpt "+prompt);
        String requestPrompt = "백엔드" +prompt+"을 만들건데 해야 할 일 목록을 리스트 형식으로 작성해줘. " +
                "이때 너의 답변은 html div 태그 안에 작성할 것이기 때문에 html에서 리스트 형식으로 출력할 수 있게 작성해줘." +
                "그리고 h3 태그로 제목도 '~ 제작 To-do List'로 지어줘.";
        GptRequest request = new GptRequest(model,
                List.of(new Message("user", requestPrompt)),
                maxCompletions,
                temperature,
                maxTokens);

        GptResponse response = restTemplate.postForObject(apiUrl, request, GptResponse.class);
        String content = response.getChoices().get(0).getMessage().getContent().trim();
        log.info("test "+content);
        aiRequestService.insertContent(projectId,content);
//        try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/templates/gpt/generated.html"))){
//            writer.write(generatedHTML);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        return response;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/check")
    public ResponseEntity<String> checkRequest(@RequestParam("projectId") Integer projectId){
        log.info("=========@# ai check =======");
        return new ResponseEntity<>(aiRequestService.checkContent(projectId), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete")
    public ResponseEntity<String> deleteRequest(@RequestParam("projectId") Integer projectId){
        aiRequestService.deleteContent(projectId);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}