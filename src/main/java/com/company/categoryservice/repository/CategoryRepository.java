package com.company.categoryservice.repository;

import com.company.categoryservice.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

Optional<Category> findAllByCategoryIdAndDeleteAtIsNull(Integer categoryId);

Boolean existsByCategoryName(String categoryName);


@Query(value = "select c from Category c where " +
        "coalesce(:categoryId,c.categoryId)=c.categoryId AND " +
        "coalesce(:categoryName,c.categoryName)=c.categoryName AND " +
        "coalesce(:reportsId,c.reportsId)=c.reportsId ")
Page<Category> getCategoryByBasicSearch(@Param(value = "categoryId") Integer categoryId,
                                        @Param(value = "categoryName") String categoryName,
                                        @Param(value = "reportsId") Integer reportsId,
                                        Pageable pageable);

}
