
package com.example.ecommercewebapp.domain.auth.usergrouppermission.web;

import com.example.ecommercewebapp.domain.auth.usergrouppermission.api.UserGroupPermissionMapper;
import com.example.ecommercewebapp.domain.auth.usergrouppermission.api.UserGroupPermissionService;
import com.example.ecommercewebapp.library.datasourceloadoptions.DataSourceLoadOptions;
import com.example.ecommercewebapp.library.logrequestresponse.Log;
import com.example.ecommercewebapp.library.rest.BaseController;
import com.example.ecommercewebapp.library.rest.MetaResponse;
import com.example.ecommercewebapp.library.rest.PageResponse;
import com.example.ecommercewebapp.library.rest.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-group-permissions")
@RequiredArgsConstructor
@Log
public class UserGroupPermissionController extends BaseController {

    private final UserGroupPermissionService service;

    @GetMapping
    @Log(response = false)
    public Response<PageResponse<UserGroupPermissionResponse>> getAllUserGroupPermissions(Pageable pageable) {
        return respond(UserGroupPermissionMapper.toPageResponse(service.getAll(pageable)));
    }

    @GetMapping("filter")
    @Log(response = false)
    public Response<PageResponse<UserGroupPermissionResponse>> filter(@ModelAttribute DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable) {
        return respond(UserGroupPermissionMapper.toPageResponse(service.filter(dataSourceLoadOptions, pageable)));
    }

    @GetMapping("/{id}")
    public Response<UserGroupPermissionResponse> getUserGroupPermissionById(@PathVariable String id) {
        return respond(UserGroupPermissionMapper.toResponse(service.getById(id)));
    }

    @PostMapping
    public Response<UserGroupPermissionResponse> createUserGroupPermission(@Valid @RequestBody UserGroupPermissionRequest request) {
        return respond(UserGroupPermissionMapper.toResponse(service.save(UserGroupPermissionMapper.toDto(request))));
    }

    @PutMapping("/{id}")
    public Response<UserGroupPermissionResponse> updateUserGroupPermission(@PathVariable String id, @RequestBody UserGroupPermissionRequest request) {
        return respond(UserGroupPermissionMapper.toResponse(service.update(id, UserGroupPermissionMapper.toDto(request))));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteUserGroupPermission(@PathVariable String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }
}

    