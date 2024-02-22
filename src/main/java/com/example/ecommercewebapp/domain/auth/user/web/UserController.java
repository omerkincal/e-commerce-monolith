package com.example.ecommercewebapp.domain.auth.user.web;

import com.example.ecommercewebapp.domain.auth.user.api.UserMapper;
import com.example.ecommercewebapp.domain.auth.user.api.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
@Log
public class UserController extends BaseController {

    private final UserService service;

    @GetMapping
    @Log(response = false)
    public Response<PageResponse<UserResponse>> getAllUsers(Pageable pageable) {
        return respond(UserMapper.toPageResponse(service.getAll(pageable)));
    }

    @GetMapping("panel-filter")
    @Log(response = false)
    public Response<PageResponse<UserResponse>> panelFilter(@ModelAttribute DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable) {
        return respond(UserMapper.toPageResponse(service.panelFilter(dataSourceLoadOptions, pageable)));
    }

    @GetMapping("me")
    public Response<UserResponse> getMe() {
        return respond(UserMapper.toResponse(service.getMe()));
    }

    @GetMapping("filter")
    @Log(response = false)
    public Response<PageResponse<UserResponse>> filter(@ModelAttribute DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable) {
        return respond(UserMapper.toPageResponse(service.filter(dataSourceLoadOptions, pageable)));
    }

    @GetMapping("/{id}")
    public Response<UserResponse> getUserById(@PathVariable String id) {
        return respond(UserMapper.toResponse(service.getById(id)));
    }

    @PostMapping
    public Response<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        return respond(UserMapper.toResponse(service.save(UserMapper.toDto(request))));
    }


    @PutMapping("/{id}")
    public Response<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest request) {
        return respond(UserMapper.toResponse(service.update(id, UserMapper.toDto(request))));
    }

    @PutMapping("/me")
    public Response<UserResponse> updateUser(@RequestBody UserRequest request) {
        return respond(UserMapper.toResponse(service.updateMe(UserMapper.toDto(request))));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteUser(@PathVariable String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }
}
