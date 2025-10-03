package com.itsm.userservicemanagment.entity.category;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "root_category")
public class RootCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;
    private LocalDateTime createDate;
    private LocalDateTime lastModifyDate;
    private String createByLogin;
    private String description;
    // private Boolean incident;
    // private Boolean problem;
    // private Boolean change;

}
