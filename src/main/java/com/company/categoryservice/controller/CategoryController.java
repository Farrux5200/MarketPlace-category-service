package com.company.categoryservice.controller;

import com.company.categoryservice.dto.CategoryDto;
import com.company.categoryservice.dto.ResponseDto;
import com.company.categoryservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

private final CategoryService categoryService;

    @Operation(
            tags = "Magazine Market Place Create ",
            summary = "Your summary create method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CategoryDto.class
                            )
                    )
            )
    )
    @PostMapping("/create")
    public ResponseDto<CategoryDto> create(@RequestBody CategoryDto dto){
        return this.categoryService.create(dto);
    }

    @Operation(
            tags = "Magazine Market Place Get ",
            summary = "Your summary get method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CategoryDto.class
                            )
                    )
            )
    )
    @GetMapping("/get/{id}")
    public ResponseDto<CategoryDto> get(@PathVariable("id") Integer categoryId){
        return this.categoryService.get(categoryId);
    }

    @Operation(
            tags = "Magazine Market Place Update ",
            summary = "Your summary update method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CategoryDto.class
                            )
                    )
            )
    )
    @PutMapping("/update/{id}")
    public ResponseDto<CategoryDto> update(@RequestBody CategoryDto dto,
                                          @PathVariable("id") Integer categoryId){
        return this.categoryService.update(dto, categoryId);
    }

    @Operation(
            tags = "Magazine Market Place Delete ",
            summary = "Your summary delete method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CategoryDto.class
                            )
                    )
            )
    )
    @DeleteMapping("/delete/{id}")
    public ResponseDto<CategoryDto> delete(@PathVariable("id") Integer categoryId){
        return this.categoryService.delete(categoryId);
    }


}
