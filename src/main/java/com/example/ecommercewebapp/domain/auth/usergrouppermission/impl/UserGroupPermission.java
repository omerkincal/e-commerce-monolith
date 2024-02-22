
package com.example.ecommercewebapp.domain.auth.usergrouppermission.impl;

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
@Table(name = UserGroupPermission.TABLE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupPermission extends AbstractEntity {
    public static final String TABLE = "user_group_permission";
    public static final String COL_USER_GROUP_ID = "user_group_id";
    public static final String COL_PERMISSION_ID = "permission_id";

    @Column(name = COL_USER_GROUP_ID)
    private String userGroupId;

    @Column(name = COL_PERMISSION_ID)
    private String permissionId;

}
    