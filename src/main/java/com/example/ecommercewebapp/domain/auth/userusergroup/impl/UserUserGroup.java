
package com.example.ecommercewebapp.domain.auth.userusergroup.impl;

import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = UserUserGroup.TABLE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUserGroup extends AbstractEntity {
    public static final String TABLE = "user_user_group";
    public static final String COL_USER_GROUP_ID = "user_group_id";
    public static final String COL_USER_ID = "user_id";

    @Column(name = COL_USER_GROUP_ID)
    private String userGroupId;

    @Column(name = COL_USER_ID)
    private String userId;

}
    