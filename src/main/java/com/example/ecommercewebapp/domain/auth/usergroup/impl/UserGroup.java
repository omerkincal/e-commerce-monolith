
package com.example.ecommercewebapp.domain.auth.usergroup.impl;

import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = UserGroup.TABLE,uniqueConstraints = {
        @UniqueConstraint(columnNames = UserGroup.COL_NAME),
})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserGroup extends AbstractEntity {
    public static final String TABLE = "user_group";
    public static final String COL_NAME = "name";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_USER_GROUP_TYPE = "user_group_type";

    @Column(name = COL_NAME,nullable = false)
    private String name;

    @Column(name = COL_DESCRIPTION)
    private String description;

    @Column(name = COL_USER_GROUP_TYPE)
    @Enumerated(value = EnumType.STRING)
    private UserGroupType userGroupType;

}
    