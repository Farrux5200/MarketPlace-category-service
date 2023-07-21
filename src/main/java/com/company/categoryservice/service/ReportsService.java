package com.company.categoryservice.service;

import com.company.categoryservice.dto.ReportsDto;
import com.company.categoryservice.dto.ResponseDto;
import com.company.categoryservice.model.Reports;
import com.company.categoryservice.repository.ReportsRepository;
import com.company.categoryservice.service.mapper.ReportsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportsService {
    private final ReportsRepository reportsRepository;
    private final ReportsMapper reportsMapper;

    public ResponseDto<ReportsDto> create(ReportsDto dto) {
        try {
            Reports reports = reportsMapper.toEntity(dto);
            reports.setCreateAt(LocalDateTime.now());
            this.reportsRepository.save(reports);
            return ResponseDto.<ReportsDto>builder()
                    .success(true)
                    .message("Category successful creat")
                    .code(0)
                    .date(reportsMapper.toDtoByNotCategory(reports))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ReportsDto>builder()
                    .message(String.format("Reports while saving error" + e.getMessage()))
                    .code(-1)
                    .build();
        }

    }

    public ResponseDto<ReportsDto> get(Integer reportsId) {
        return this.reportsRepository.findAllByReportsIdAndDeleteAtIsNull(reportsId)
                .map(reports -> ResponseDto.<ReportsDto>builder()
                        .code(0)
                        .success(true)
                        .message("Reports successful get method!")
                        .date(reportsMapper.toDto(reports))
                        .build())
                .orElse(ResponseDto.<ReportsDto>builder()
                        .code(-1)
                        .message(String.format("Reports id is not found!"))
                        .build());
    }

    public ResponseDto<ReportsDto> update(ReportsDto dto, Integer reportsId) {
        try {
            return this.reportsRepository.findAllByReportsIdAndDeleteAtIsNull(reportsId)
                    .map(reports -> {
                        reports.setCreateAt(LocalDateTime.now());
                        reportsMapper.fromReportsByUpdate(reports, dto);
                        reportsRepository.save(reports);
                        return ResponseDto.<ReportsDto>builder()
                                .code(0)
                                .success(true)
                                .message("Reports successful update!")
                                .date(this.reportsMapper.toDtoByNotCategory(reports))
                                .build();
                    })
                    .orElse(ResponseDto.<ReportsDto>builder()
                            .code(-1)
                            .message(String.format("Reports id is not found!"))
                            .build());
        }  catch (Exception e) {
            return ResponseDto.<ReportsDto>builder()
                    .message(String.format("Reports while updating error" + e.getMessage()))
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ReportsDto> delete(Integer reportsId) {
        try {
            return this.reportsRepository.findAllByReportsIdAndDeleteAtIsNull(reportsId)
                    .map(reports -> {
                        reports.setCreateAt(LocalDateTime.now());
                        reportsRepository.save(reports);
                        return ResponseDto.<ReportsDto>builder()
                                .code(0)
                                .success(true)
                                .message("Reports successful delete!")
                                .date(this.reportsMapper.toDtoByNotCategory(reports))
                                .build();
                    })
                    .orElse(ResponseDto.<ReportsDto>builder()
                            .code(-1)
                            .message(String.format("Reports id is not found!"))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<ReportsDto>builder()
                    .message(String.format("Reports while deleting error" + e.getMessage()))
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<List<ReportsDto>> getAll() {
        return ResponseDto.<List<ReportsDto>>builder()
                .success(true)
                .message("Reports successful getAll method!")
                .date(this.reportsRepository.findAll().stream().map(reportsMapper::toDto).toList())
                .build();
    }
}
