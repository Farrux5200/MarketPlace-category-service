package com.company.categoryservice.service.mapper;

import com.company.categoryservice.dto.CategoryDto;
import com.company.categoryservice.model.Category;
import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class CategoryMapper {


    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract Category toEntity(CategoryDto dto);

    public abstract CategoryDto toDto(Category category);


    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract CategoryDto toDtoByNotProduct(Category category);


    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void fromCategoryGet(@MappingTarget Category category, CategoryDto categoryDto);
}
