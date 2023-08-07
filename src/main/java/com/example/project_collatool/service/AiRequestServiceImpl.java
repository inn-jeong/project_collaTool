package com.example.project_collatool.service;

import com.example.project_collatool.converter.AiRequestConverter;
import com.example.project_collatool.db.AiRequestEntity;
import com.example.project_collatool.dto.AiRequestDto;
import com.example.project_collatool.repository.AiRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("AiRequestService")
@RequiredArgsConstructor
@Slf4j
public class AiRequestServiceImpl implements AiRequestService{
    private final AiRequestRepository aiRequestRepository;
    private final AiRequestConverter aiRequestConverter;

    @Override
    public String checkContent(Integer projectId) {
        Optional<AiRequestEntity> entity = aiRequestRepository.findByAiProjectId(projectId);
        if(entity.isEmpty()){
            return "is null";
        }else{
            return entity.get().getAiContent();
        }
    }

    @Override
    public void insertContent(Integer projectId, String content) {
        AiRequestEntity entity = AiRequestEntity.builder()
                .aiProjectId(projectId)
                .aiContent(content)
                .build();
        aiRequestRepository.save(entity);
    }

    @Override
    public void deleteContent(Integer projectId) {
        aiRequestRepository.deleteContent(projectId);
    }
}
