package com.company.categoryservice.service.validate;

import com.company.categoryservice.dto.CategoryDto;
import com.company.categoryservice.dto.ErrorDto;
import com.company.categoryservice.repository.CategoryRepository;
import com.company.categoryservice.service.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryValidate {

    private final ReportsService reportsService;
    private final CategoryRepository categoryRepository;

    public List<ErrorDto> validate(CategoryDto dto){
        List<ErrorDto> errors=new ArrayList<>();
        if (reportsService.get(dto.getReportsId()).getDate()==null){
            errors.add(new ErrorDto("ReportsId", "Reports Id is not found!"));
        }
        if (categoryRepository.existsByCategoryName(dto.getCategoryName())){
            errors.add(new ErrorDto("categoryName", "CategoryName already exist"));
        }
        return errors;
    }

}
