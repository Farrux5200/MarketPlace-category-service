package com.company.categoryservice.service;

import com.company.categoryservice.dto.CategoryDto;
import com.company.categoryservice.dto.ErrorDto;
import com.company.categoryservice.dto.ResponseDto;
import com.company.categoryservice.model.Category;
import com.company.categoryservice.repository.CategoryRepository;
import com.company.categoryservice.service.mapper.CategoryMapper;
import com.company.categoryservice.service.validate.CategoryValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    private final CategoryValidate categoryValidate;


    public ResponseDto<CategoryDto> create(CategoryDto dto) {

        List<ErrorDto> errors = categoryValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<CategoryDto>builder()
                    .success(false)
                    .code(-2)
                    .message("Validate error")
                    .errors(errors)
                    .build();
        }

        try {
            Category category = this.categoryMapper.toEntity(dto);
            category.setCreateAt(LocalDateTime.now());
            this.categoryRepository.save(category);
            return ResponseDto.<CategoryDto>builder()
                    .message("Category successful created")
                    .success(true)
                    .code(0)
                    .date(categoryMapper.toDtoByNotProduct(category))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message(String.format("Category while saving error :" , e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<CategoryDto> get(Integer categoryId) {
       return this.categoryRepository.findAllByCategoryIdAndDeleteAtIsNull(categoryId)
               .map(category -> ResponseDto.<CategoryDto>builder()
                       .message("Category successful get method")
                       .success(true)
                       .code(0)
                       .date(categoryMapper.toDto(category))
                       .build())
               .orElse(ResponseDto.<CategoryDto>builder()
                       .code(-1)
                       .message(String.format("Category id is not found"+categoryId))
                       .build());
    }

    public ResponseDto<CategoryDto> update(CategoryDto dto, Integer categoryId) {
        try {
          return this.categoryRepository.findAllByCategoryIdAndDeleteAtIsNull(categoryId)
                    .map(category -> {
                        category.setUpdateAt(LocalDateTime.now());
                        categoryMapper.fromCategoryGet(category, dto);
                        categoryRepository.save(category);
                        return ResponseDto.<CategoryDto>builder()
                                .message("Category successful update!")
                                .success(true)
                                .code(0)
                                .date(categoryMapper.toDto(category))
                                .build();
                    }).orElse(ResponseDto.<CategoryDto>builder()
                            .code(-1)
                            .message(String.format("Category id is not found"+categoryId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message(String.format("Category while updating error :" , e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<CategoryDto> delete(Integer categoryId) {
        try {
            return this.categoryRepository.findAllByCategoryIdAndDeleteAtIsNull(categoryId)
                    .map(category -> {
                        category.setUpdateAt(LocalDateTime.now());
                        categoryRepository.save(category);
                        return ResponseDto.<CategoryDto>builder()
                                .message("Category successful delete!")
                                .success(true)
                                .code(0)
                                .date(categoryMapper.toDto(category))
                                .build();
                    }).orElse(ResponseDto.<CategoryDto>builder()
                            .code(-1)
                            .message(String.format("Category id is not found"+categoryId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message(String.format("Category while deleting error :" , e.getMessage()))
                    .build();
        }
    }
}
