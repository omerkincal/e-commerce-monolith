
package com.example.ecommercewebapp.domain.auth.usergroup.web;

import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupMapper;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupService;
import com.example.ecommercewebapp.library.datasourceloadoptions.DataSourceLoadOptions;
import com.example.ecommercewebapp.library.logrequestresponse.Log;
import com.example.ecommercewebapp.library.rest.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-groups")
@RequiredArgsConstructor
@Log
public class UserGroupController extends BaseController {

    private final UserGroupService service;

    @GetMapping
    @Log(response = false)
    public Response<PageResponse<UserGroupResponse>> getAllUserGroups(Pageable pageable) {
        return respond(UserGroupMapper.toPageResponse(service.getAll(pageable)));
    }

    @GetMapping("filter")
    @Log(response = false)
    public Response<PageResponse<UserGroupResponse>> filter(@ModelAttribute DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable) {
        return respond(UserGroupMapper.toPageResponse(service.filter(dataSourceLoadOptions, pageable)));
    }

    @GetMapping("panel-all")
    @Log(response = false)
    public Response<DataResponse<UserGroupResponse>> panelGetAll() {
        return respond(UserGroupMapper.toDataResponse(service.panelGetAll()));
    }

    @GetMapping("/{id}")
    public Response<UserGroupResponse> getUserGroupById(@PathVariable String id) {
        return respond(UserGroupMapper.toResponse(service.getById(id)));
    }

    @PostMapping
    public Response<UserGroupResponse> createUserGroup(@Valid @RequestBody UserGroupRequest request) {
        return respond(UserGroupMapper.toResponse(service.save(UserGroupMapper.toDto(request))));
    }

    @PutMapping("/{id}")
    public Response<UserGroupResponse> updateUserGroup(@PathVariable String id, @RequestBody UserGroupRequest request) {
        return respond(UserGroupMapper.toResponse(service.update(id, UserGroupMapper.toDto(request))));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteUserGroup(@PathVariable String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }
}

    