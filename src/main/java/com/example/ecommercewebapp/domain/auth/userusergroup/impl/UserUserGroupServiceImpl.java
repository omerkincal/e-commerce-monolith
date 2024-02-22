
package com.example.ecommercewebapp.domain.auth.userusergroup.impl;

import com.example.ecommercewebapp.domain.auth.user.api.UserService;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupService;
import com.example.ecommercewebapp.domain.auth.userusergroup.api.UserUserGroupDto;
import com.example.ecommercewebapp.domain.auth.userusergroup.api.UserUserGroupService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserUserGroupServiceImpl implements UserUserGroupService {

    private final UserUserGroupRepository repository;
    private final UserGroupService userGroupService;
    private final UserService userService;

    @Override
    public Page<UserUserGroupDto> getAll(Pageable pageable) {
        return PageUtil.pageToDto(repository.findAll(pageable), userUserGroup -> UserUserGroupMapper.toDto(userUserGroup,userGroupService));
    }

    public List<UserUserGroupDto> getAllByUserId(String userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(UserUserGroupMapper::toDto)
                .toList();
    }

    @Override
    public UserUserGroupDto getById(String id) {
        return repository.findById(id).map(userUserGroup -> UserUserGroupMapper.toDto(userUserGroup,userGroupService))
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, UserUserGroup.class.getSimpleName(), id));
    }

    @Override
    @Transactional
    public UserUserGroupDto save(UserUserGroupDto dto) {
        return UserUserGroupMapper.toDto(repository.save(UserUserGroupMapper.toEntity(new UserUserGroup(), dto,userGroupService,userService)),userGroupService);
    }

    @Override
    @Transactional
    public UserUserGroupDto update(String id, UserUserGroupDto dto) {
        return repository.findById(id)
                .map(userUserGroup -> UserUserGroupMapper.toDto(repository.save(UserUserGroupMapper.toEntity(userUserGroup, dto,userGroupService,userService)),userGroupService))
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, UserUserGroup.class.getSimpleName(), id));
    }

    @Override
    @Transactional
    public void delete(String id) {
        var userGroup = repository.findById(id)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, UserUserGroup.class.getSimpleName(), id));
        repository.delete(userGroup);
    }

    @Override
    public Page<UserUserGroupDto> filter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable) {
        Specification<UserUserGroup> spec = DataSourceLoadOptionsUtil.toSpec(dataSourceLoadOptions, UserUserGroup.class);
        return PageUtil.pageToDto(repository.findAll(spec, pageable), userUserGroup -> UserUserGroupMapper.toDto(userUserGroup,userGroupService));
    }
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Optional<UserUserGroup> findByUserGroupIdAndUserId(String userGroupId, String userId){
        return repository.findByUserGroupIdAndUserId(userGroupId, userId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveWithoutControl(UserUserGroupDto dto) {
        repository.save(UserUserGroupMapper.toEntity(new UserUserGroup(), dto));
    }

    public List<UserUserGroupDto> getAllByUserIds(List<String> userIds) {
        return repository.findAllByUserIdIn(userIds)
                .stream()
                .map(UserUserGroupMapper::toDto)
                .toList();
    }
    @Transactional
    public List<UserUserGroupDto> saveAll(String id, List<UserGroupDto> userGroups) {
        if(userGroups == null){
            return new ArrayList<>();
        }
        List<UserUserGroup> userUserGroups = new ArrayList<>();
        repository.deleteAll(repository.findAllByUserId(id));
        userGroups.forEach(userGroup -> {
            UserUserGroup userUserGroup = new UserUserGroup();
            userUserGroup.setUserGroupId(userGroup.getId());
            userUserGroup.setUserId(id);
            userUserGroups.add(userUserGroup);
        });
        return repository.saveAll(userUserGroups).stream()
                .map(UserUserGroupMapper::toDto)
                .toList();
    }
}

    