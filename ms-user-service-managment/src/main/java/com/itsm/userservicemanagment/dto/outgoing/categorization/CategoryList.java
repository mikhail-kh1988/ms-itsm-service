package com.itsm.userservicemanagment.dto.outgoing.categorization;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class CategoryList {

    private String category;
    private HashMap<String, Long> subCategories;
    private int total;

}
