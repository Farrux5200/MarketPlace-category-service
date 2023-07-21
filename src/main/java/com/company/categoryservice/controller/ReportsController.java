package com.company.categoryservice.controller;
import com.company.categoryservice.dto.ReportsDto;
import com.company.categoryservice.dto.ResponseDto;
import com.company.categoryservice.service.ReportsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reports")
@RequiredArgsConstructor
public class ReportsController {
 private final ReportsService reportsService;

    @Operation(
            tags = "Magazine Market Place Create ",
            summary = "Your summary create method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ReportsDto.class
                            )
                    )
            )
    )
    @PostMapping("/create")
    public ResponseDto<ReportsDto> create(@RequestBody ReportsDto dto) {
        return this.reportsService.create(dto);
    }

    @Operation(
            tags = "Magazine Market Place Get ",
            summary = "Your summary get method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            )
                    )
            )
    )
    @GetMapping("/get/{id}")
    public ResponseDto<ReportsDto> get(@PathVariable("id") Integer reportsId) {
        return this.reportsService.get(reportsId);
    }


    @Operation(
            tags = "Magazine Market Place Update ",
            summary = "Your summary update method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            )
                    )
            )
    )
    @PutMapping("/update/{id}")
    public ResponseDto<ReportsDto> update(@RequestBody ReportsDto dto,
                                       @PathVariable("id") Integer reportsId) {
        return this.reportsService.update(dto, reportsId);
    }

    @Operation(
            tags = "Magazine Market Place Delete ",
            summary = "Your summary delete method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            )
                    )
            )
    )
    @DeleteMapping("/delete/{id}")
    public ResponseDto<ReportsDto> delete(@PathVariable("id") Integer reportsId) {
        return this.reportsService.delete(reportsId);
    }


    @Operation(
            tags = "Magazine Market Place GetAll ",
            summary = "Your summary getAll method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            )
                    )
            )
    )
    @GetMapping("/get-all")
    public ResponseDto<List<ReportsDto>> getAll() {
        return this.reportsService.getAll();
    }


}
