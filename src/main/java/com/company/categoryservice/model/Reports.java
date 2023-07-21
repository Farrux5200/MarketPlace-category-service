package com.company.categoryservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = ("reports"))
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("reports_id"))
    private Integer reportsId;
    private String prodName;
    private Double prodPresent;

    @OneToMany(mappedBy ="reportsId", cascade = CascadeType.ALL)
    private List<Category> categories;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

}
