package com.example.ecommercewebapp.domain.auth.permission.impl;

import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;
import com.example.ecommercewebapp.domain.auth.permission.api.PermissionService;
import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.exception.CoreException;
import com.example.ecommercewebapp.library.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repository;
    private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Override
    public Page<PermissionDto> getAll(Pageable pageable) {
        return PageUtil.pageToDto(repository.findAll(pageable), PermissionMapper::toDto);
    }

    public List<PermissionDto> getAllByIds(List<String> permissionIds) {
        return repository.findAllByIdIn(permissionIds)
                .stream()
                .map(PermissionMapper::toDto)
                .toList();
    }
    public PermissionDto getByName(String name){
        return repository.findByName(name).map(PermissionMapper::toDto)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Permission.class.getSimpleName(),name));
    }
    @Override
    public PermissionDto getById(String id) {
        return repository.findById(id).map(PermissionMapper::toDto)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Permission.class.getSimpleName(),id));
    }

    @Override
    public List<PermissionDto> getAllPermissions() {
        return repository.findAllByType(PermissionType.USER).stream().map(PermissionMapper::toDto).toList();
    }

    public List<String> getAllIds(){
        return repository.findAll().stream().map(Permission::getId).toList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createAll(List<PermissionDto> permissions) {
        for (PermissionDto permission : permissions) {
            create(permission);
        }
    }

    @Transactional
    public Permission create(PermissionDto permission) {
        AtomicReference<Permission> createdPermission = new AtomicReference<>();
            repository.findByName(permission.getName())
                    .ifPresentOrElse(
                            existingPermission -> {
                                log.info("Permission already exists: {}", existingPermission.getName());
                                createdPermission.set(existingPermission);
                            },
                            () -> {
                                Permission newPermission = PermissionMapper.toEntity(new Permission(), permission);
                                createdPermission.set(repository.save(newPermission));
                            }
                    );
            return createdPermission.get();
    }

}

    