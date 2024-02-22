package com.example.ecommercewebapp.domain.auth.user.impl;

import com.example.ecommercewebapp.domain.auth.user.api.UserDto;
import com.example.ecommercewebapp.domain.auth.user.api.UserService;
import com.example.ecommercewebapp.domain.auth.user.api.UserType;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupService;
import com.example.ecommercewebapp.domain.auth.usergroup.impl.UserGroupType;
import com.example.ecommercewebapp.domain.auth.userusergroup.api.UserUserGroupDto;
import com.example.ecommercewebapp.domain.auth.userusergroup.impl.UserUserGroupServiceImpl;
import com.example.ecommercewebapp.library.datasourceloadoptions.DataSourceLoadOptions;
import com.example.ecommercewebapp.library.datasourceloadoptions.DataSourceLoadOptionsUtil;
import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.exception.CoreException;
import com.example.ecommercewebapp.library.security.JwtUtils;
import com.example.ecommercewebapp.library.utils.Functions;
import com.example.ecommercewebapp.library.utils.PageUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.example.ecommercewebapp.domain.auth.usergroup.impl.DefaultUserGroupCreator.CUSTOMER_GROUP_NAME;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserGroupService userGroupService;
    private final UserUserGroupServiceImpl userUserGroupService;

    public UserServiceImpl(UserRepository repository,
                           BCryptPasswordEncoder passwordEncoder,
                           UserGroupService userGroupService,
                           @Lazy UserUserGroupServiceImpl userUserGroupService
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.userGroupService = userGroupService;
        this.userUserGroupService = userUserGroupService;
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return PageUtil.pageToDto(repository.findAll(pageable), UserMapper::toDto);
    }

    @Override
    public UserDto getById(String id) {
        return repository.findById(id).map(UserMapper::toDto)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, User.class.getSimpleName(), id));
    }

    @Override
    @Transactional
    public UserDto save(UserDto dto) {
        checkUserExists(dto.getEmail());
        User user = persistUser(dto);
        user.setPassword(passwordEncoder.encode(StringUtils.hasLength(dto.getPassword())
                ? dto.getPassword()
                : Functions.generateRandomPassword()));
        User save = repository.save(user);
        var userUserGroups = userUserGroupService.saveAll(save.getId(), dto.getUserGroups());
        //TODO password mail atılacak
        return UserMapper.toDto(save, userUserGroups);
    }

    @Override
    public Long countByUserType(UserType userType) {
        return repository.countByUserType(userType);
    }

    @Transactional
    public User save(User user) {
        checkUserExists(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = repository.save(user);
        userUserGroupService.save(UserUserGroupDto.builder()
                .userId(save.getId())
                .userGroup(userGroupService.findByNameAndUserGroupTypeDto(CUSTOMER_GROUP_NAME, UserGroupType.CUSTOMER))
                .build());
        return save;
    }

    @Transactional
    public User update(User user) {
        return repository.save(user);
    }

    public User getByChangePasswordCode(String code) {
        if (!StringUtils.hasLength(code)) {
            throw new CoreException(MessageCodes.FAIL);
        }
        return repository.findByChangePasswordCode(code)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, User.class.getSimpleName(), code));
    }

    @Override
    @Transactional
    public UserDto update(String id, UserDto dto) {
        var user = repository.findById(id).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, User.class.getSimpleName(), id));
        User save = repository.save(setUser(user, dto));
        var userUserGroups = userUserGroupService.saveAll(save.getId(), dto.getUserGroups());
        return UserMapper.toDto(save, userUserGroups);
    }


    @Override
    @Transactional
    public void delete(String id) {
        var user = repository.findById(id).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, User.class.getSimpleName(), id));
        repository.delete(user);
    }

    @Override
    public Page<UserDto> filter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable) {
        Specification<User> spec = DataSourceLoadOptionsUtil.toSpec(dataSourceLoadOptions, User.class);
        return PageUtil.pageToDto(repository.findAll(spec, pageable), UserMapper::toDto);
    }

    @Override
    public Page<UserDto> panelFilter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable) {
        Specification<User> spec = DataSourceLoadOptionsUtil.toSpec(dataSourceLoadOptions, User.class);
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userType"), UserType.ADMIN));
        var users = repository.findAll(spec, pageable);
        List<String> userIds = users.map(User::getId).toList();
        List<UserUserGroupDto> userUserGroups = userUserGroupService.getAllByUserIds(userIds);
        return PageUtil.pageToDto(users, user -> UserMapper.toDto(user, userUserGroups));
    }

    /*@Override
    @Transactional
    public void verify(String key) {
        var verification = verificationService.getByKey(key);
        if (Boolean.TRUE.equals(!verification.getIsUsed()) && new Date().before(verification.getExpireTime())) {
            var user = repository.findByEmail(verification.getEmail())
                    .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, User.class.getSimpleName(), verification.getEmail()));
            user.setVerified(true);
            repository.save(user);
            eventPublisher
                    .publishEvent(new UserVerifiedEvent(verification));
        } else {
            throw new CoreException(MessageCodes.FAIL, User.class.getSimpleName(), key);
        }
    }*/

    @Override
    public void checkUserExists(String email) {
        repository.findByEmail(email)
                .ifPresent(u -> {
                    throw new CoreException(MessageCodes.ENTITY_ALREADY_EXISTS, User.class.getSimpleName(), email);
                });
    }

    @Override
    public UserDto getMe() {
        return getById(JwtUtils.extractUserId());
    }

    @Override
    @Transactional
    public UserDto updateMe(UserDto dto) {
        return update(JwtUtils.extractUserId(), dto);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDto createUserByTenant(UserDto dto) {
        checkUserExists(dto.getEmail());
        User user = persistUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return UserMapper.toDto(repository.save(user));
    }

    public Optional<User> findByEmail(String email, UserType userType) {
        return repository.findByEmailAndUserType(email, userType);
    }

    public User findByEmailAndPassword(String email, String password, UserType userType) {
        Optional<User> user = repository.findByEmailAndUserTypeAndStatusTrueAndVerifiedTrue(email, userType);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get();
        }
        throw new CoreException(MessageCodes.ENTITY_NOT_FOUND, User.class.getSimpleName(), email);
    }

    private User persistUser(UserDto dto) {
        User user = UserMapper.toEntity(new User(), dto);
        return user;
    }


    private User setUser(User user, UserDto dto) {
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setExtensionNumber(dto.getExtensionNumber());
        user.setStatus(dto.getStatus());
        user.setVerified(dto.getVerified() != null && dto.getVerified());
        return user;
    }

}
