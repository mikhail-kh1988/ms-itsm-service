package com.itsm.userservicemanagment.entity.category;

import com.itsm.userservicemanagment.entity.User;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;
    private LocalDateTime createDate;
    private LocalDateTime lastModifyDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SubCategory subCategory;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User createBy;
    private String createByLogin;

    private String description;
    private String priorityLevel;
    private String impactLevel;
    private Boolean active;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;

}
