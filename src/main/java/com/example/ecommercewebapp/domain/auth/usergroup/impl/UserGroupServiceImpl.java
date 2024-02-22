
package com.example.ecommercewebapp.domain.auth.usergroup.impl;

import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupService;
import com.example.ecommercewebapp.domain.auth.usergrouppermission.api.UserGroupPermissionDto;
import com.example.ecommercewebapp.domain.auth.usergrouppermission.impl.UserGroupPermissionServiceImpl;
import com.example.ecommercewebapp.library.datasourceloadoptions.DataSourceLoadOptions;
import com.example.ecommercewebapp.library.datasourceloadoptions.DataSourceLoadOptionsUtil;
import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.exception.CoreException;
import com.example.ecommercewebapp.library.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupRepository repository;
    private final UserGroupPermissionServiceImpl userGroupPermissionService;


    @Override
    public Page<UserGroupDto> getAll(Pageable pageable) {
        Page<UserGroup> userGroups = repository.findAll(pageable);
        List<String> userGroupIds = userGroups.map(UserGroup::getId).toList();
        List<UserGroupPermissionDto> userGroupPermissions = userGroupPermissionService.getAllByUserGroupIds(userGroupIds);
        return PageUtil.pageToDto(userGroups, userGroup -> UserGroupMapper.toDto(userGroup, userGroupPermissions));
    }

    @Override
    public UserGroupDto getById(String id) {
        return repository.findById(id).map(UserGroupMapper::toDto)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, UserGroup.class.getSimpleName(), id));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserGroupDto save(UserGroupDto dto) {
        var userGroup = repository.save(UserGroupMapper.toEntity(new UserGroup(), dto));
        var permissions = userGroupPermissionService.createAll(userGroup, dto.getPermissions());
        return UserGroupMapper.toDto(userGroup,permissions);
    }

    @Override
    @Transactional
    public UserGroupDto update(String id, UserGroupDto dto) {
        return repository.findById(id)
                .map(userGroup -> {
                    var updatedUserGroup = (repository.save(UserGroupMapper.toEntity(userGroup, dto)));
                    var permissions = userGroupPermissionService.updateAll(updatedUserGroup, dto.getPermissions());
                    return UserGroupMapper.toDto(updatedUserGroup, permissions);
                })
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, UserGroup.class.getSimpleName(), id));
    }

    @Override
    @Transactional
    public void delete(String id) {
        var userGroup = repository.findById(id)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, UserGroup.class.getSimpleName(), id));
        repository.delete(userGroup);
    }

    @Override
    public Page<UserGroupDto> filter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable) {
        Specification<UserGroup> spec = DataSourceLoadOptionsUtil.toSpec(dataSourceLoadOptions, UserGroup.class);
        var userGroups = repository.findAll(spec, pageable);
        List<String> userGroupIds = userGroups.map(UserGroup::getId).toList();
        List<UserGroupPermissionDto> userGroupPermissions = userGroupPermissionService.getAllByUserGroupIds(userGroupIds);
        return PageUtil.pageToDto(userGroups, userGroup -> UserGroupMapper.toDto(userGroup, userGroupPermissions));
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Optional<UserGroup> findByNameAndUserGroupType(String name, UserGroupType userGroupType) {
        return repository.findByNameAndUserGroupType(name, userGroupType);
    }

    @Override
    public UserGroupDto findByNameAndUserGroupTypeDto(String name, UserGroupType userGroupType) {
        return UserGroupMapper.toDto(repository.findByNameAndUserGroupType(name, userGroupType)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, UserGroup.class.getSimpleName(), name)));
    }

    @Override
    public Page<UserGroupDto> panelFilter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable) {
        Specification<UserGroup> spec = DataSourceLoadOptionsUtil.toSpec(dataSourceLoadOptions, UserGroup.class);
        spec = spec.and((root, query, builder) -> builder.equal(root.get("userGroupType"), UserGroupType.ADMIN));
        var userGroups = repository.findAll(spec, pageable);
        return PageUtil.pageToDto(userGroups, UserGroupMapper::toDto);
    }

    @Override
    public List<UserGroupDto> panelGetAll() {
        return repository.findAllByUserGroupType(UserGroupType.ADMIN).stream().map(UserGroupMapper::toDto).toList();
    }
}

    