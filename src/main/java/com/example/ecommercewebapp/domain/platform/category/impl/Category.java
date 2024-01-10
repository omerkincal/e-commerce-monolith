package com.example.ecommercewebapp.domain.platform.category.impl;

import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = Category.TABLE)
public class Category extends AbstractEntity {
    public static final String TABLE = "category";
    private static final String COL_NAME = "name";
    private static final String COL_DESCRIPTION = "description";

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_DESCRIPTION)
    private String description;
}
