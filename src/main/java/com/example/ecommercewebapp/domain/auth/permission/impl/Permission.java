
package com.example.ecommercewebapp.domain.auth.permission.impl;

import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = Permission.TABLE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends AbstractEntity {
    public static final String TABLE = "permission";
    private static final String COL_NAME = "name";
    private static final String COL_DISPLAY_NAME = "display_name";
    private static final String COL_DESCRIPTION = "description";

    @Column(name = COL_NAME,nullable = false)
    private String name;

    @Column(name = COL_DISPLAY_NAME,nullable = false)
    private String displayName;

    @Column(name = COL_DESCRIPTION)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PermissionType type;


}
    