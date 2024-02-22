
package com.example.ecommercewebapp.domain.auth.userusergroup.web;

import com.example.ecommercewebapp.domain.auth.userusergroup.api.UserUserGroupMapper;
import com.example.ecommercewebapp.domain.auth.userusergroup.api.UserUserGroupService;
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
@RequestMapping("user-user-groups")
@RequiredArgsConstructor
@Log
public class UserUserGroupController extends BaseController {

    private final UserUserGroupService service;

    @GetMapping
    @Log(response = false)
    public Response<PageResponse<UserUserGroupResponse>> getAllUserUserGroups(Pageable pageable) {
        return respond(UserUserGroupMapper.toPageResponse(service.getAll(pageable)));
    }

    @GetMapping("filter")
    @Log(response = false)
    public Response<PageResponse<UserUserGroupResponse>> filter(@ModelAttribute DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable) {
        return respond(UserUserGroupMapper.toPageResponse(service.filter(dataSourceLoadOptions, pageable)));
    }

    @GetMapping("/{id}")
    public Response<UserUserGroupResponse> getUserUserGroupById(@PathVariable String id) {
        return respond(UserUserGroupMapper.toResponse(service.getById(id)));
    }

    @PostMapping
    public Response<UserUserGroupResponse> createUserUserGroup(@Valid @RequestBody UserUserGroupRequest request) {
        return respond(UserUserGroupMapper.toResponse(service.save(UserUserGroupMapper.toDto(request))));
    }

    @PutMapping("/{id}")
    public Response<UserUserGroupResponse> updateUserUserGroup(@PathVariable String id, @RequestBody UserUserGroupRequest request) {
        return respond(UserUserGroupMapper.toResponse(service.update(id, UserUserGroupMapper.toDto(request))));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteUserUserGroup(@PathVariable String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }
}

    