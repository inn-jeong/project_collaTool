package com.example.project_collatool.converter;

public interface Converter<ENTITY, DTO> {
    public ENTITY toEntity(DTO dto);
    public DTO toDto(ENTITY entity);
}
