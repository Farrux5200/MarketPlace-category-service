package com.company.categoryservice.service.mapper;

import com.company.categoryservice.dto.ReportsDto;
import com.company.categoryservice.model.Reports;
import com.company.categoryservice.repository.CategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ReportsMapper {

    @Autowired
    protected CategoryMapper categoryMapper;

    @Autowired
    protected CategoryRepository categoryRepository;


    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract Reports toEntity(ReportsDto dto);


    @Mapping(target = "categoryDto", expression = "java(categoryRepository.findAll().stream().map(categoryMapper::toDto).toList())")
    public abstract ReportsDto toDto(Reports reports);


    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    @Mapping(target = "categoryDto", ignore = true)
    public abstract ReportsDto toDtoByNotCategory(Reports reports);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void fromReportsByUpdate(@MappingTarget Reports reports, ReportsDto dto);


}
