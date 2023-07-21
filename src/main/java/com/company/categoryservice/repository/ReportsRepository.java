package com.company.categoryservice.repository;
import com.company.categoryservice.model.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Integer> {

    Optional<Reports> findAllByReportsIdAndDeleteAtIsNull(Integer reportsId);




  /*  @Query(value = "select r from Reports r where " +
            "coalesce(:reportsId,r.reportsId)=r.reportsId and " +
            "coalesce(:prodName,r.prodName)=r.prodName and " +
            "coalesce(:prodPresent,r.prodPresent)=r.prodPresent ")
    Page<Reports> getReportsByBasicSearch(@Param(value = "reportsId") Integer reportsId,
                                          @Param(value = "prodName") String prodName,
                                          @Param(value = "  prodPresent") Double prodPresent,
                                          Pageable pageable);*/
}
