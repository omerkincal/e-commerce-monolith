
package com.example.ecommercewebapp.domain.auth.usergrouppermission.impl;

import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;
import com.example.ecommercewebapp.domain.auth.permission.api.PermissionService;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupService;
import com.example.ecommercewebapp.domain.auth.usergroup.impl.UserGroup;
import com.example.ecommercewebapp.domain.auth.usergrouppermission.api.UserGroupPermissionDto;
import com.example.ecommercewebapp.domain.auth.usergrouppermission.api.UserGroupPermissionService;
import com.example.ecommercewebapp.library.datasourceloadoptions.DataSourceLoadOptions;
import com.example.ecommercewebapp.library.datasourceloadoptions.DataSourceLoadOptionsUtil;
import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.exception.CoreException;
import com.example.ecommercewebapp.library.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Transactional(readOnly = true)
public class UserGroupPermissionServiceImpl implements UserGroupPermissionService {

    private final UserGroupPermissionRepository repository;
    private final UserGroupService userGroupService;
    private final PermissionService permissionService;

    @Override
    public Page<UserGroupPermissionDto> getAll(Pageable pageable) {
        return PageUtil.pageToDto(repository.findAll(pageable), userGroupPermission -> UserGroupPermissionMapper.toDto(userGroupPermission, permissionService, userGroupService));
    }

    public List<UserGroupPermissionDto> getAllByUserGroupIds(List<String> userGroupIds) {
        return repository.findAllByUserGroupIdIn(userGroupIds)
                .stream()
                .map(userGroupPermission -> UserGroupPermissionMapper.toDto(userGroupPermission, permissionService, userGroupService))
                .toList();
    }

    @Override
    public UserGroupPermissionDto getById(String id) {
        return repository.findById(id).map(userGroupPermission -> UserGroupPermissionMapper.toDto(userGroupPermission, permissionService, userGroupService))
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, UserGroupPermission.class.getSimpleName(), id));
    }

    @Override
    @Transactional
    public UserGroupPermissionDto save(UserGroupPermissionDto dto) {
        return UserGroupPermissionMapper
                .toDto(repository
                        .save(UserGroupPermissionMapper
                                .toEntity(new UserGroupPermission(), dto, permissionService, userGroupService)),
                                            permissionService,
                                            userGroupService);
    }

    @Override
    @Transactional
    public UserGroupPermissionDto update(String id, UserGroupPermissionDto dto) {
        return repository.findById(id)
                .map(userGroupPermission -> UserGroupPermissionMapper.toDto(repository.save(UserGroupPermissionMapper.toEntity(userGroupPermission, dto, permissionService, userGroupService)), permissionService, userGroupService))
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, UserGroupPermission.class.getSimpleName(), id));
    }

    @Override
    @Transactional
    public void delete(String id) {
        var userGroup = repository.findById(id)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, UserGroupPermission.class.getSimpleName(), id));
        repository.delete(userGroup);
    }

    @Override
    public Page<UserGroupPermissionDto> filter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable) {
        Specification<UserGroupPermission> spec = DataSourceLoadOptionsUtil.toSpec(dataSourceLoadOptions, UserGroupPermission.class);
        return PageUtil.pageToDto(repository.findAll(spec, pageable), userGroupPermission -> UserGroupPermissionMapper.toDto(userGroupPermission, permissionService, userGroupService));
    }
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Optional<UserGroupPermission> findByUserGroupIdAndPermissionId(String userGroupId, String permissionId){
        return repository.findByUserGroupIdAndPermissionId(userGroupId, permissionId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveWithoutControl(UserGroupPermissionDto dto) {
        repository.save(UserGroupPermissionMapper
                                        .toEntity(new UserGroupPermission(), dto));
    }

    public Set<PermissionDto> createAll(UserGroup userGroup, Set<PermissionDto> permissions) {
        return Optional.ofNullable(permissions)
                .map(permissionDtos -> permissionDtos.stream()
                        .map(permissionDto -> UserGroupPermissionMapper.toDto(repository.save(UserGroupPermissionMapper.toEntity(new UserGroupPermission(), userGroup, permissionDto, permissionService, userGroupService)), permissionService, userGroupService))
                        .map(UserGroupPermissionDto::getPermission)
                        .collect(Collectors.toSet()))
                .orElse(Set.of());
    }

    public Set<PermissionDto> updateAll(UserGroup updatedUserGroup, Set<PermissionDto> permissions) {
        repository.deleteAllByUserGroupId(updatedUserGroup.getId());
        return createAll(updatedUserGroup, permissions);
    }
}

    