package com.example.ecommercewebapp.domain.auth.user.web;

import com.example.ecommercewebapp.domain.auth.user.api.UserMapper;
import com.example.ecommercewebapp.domain.auth.user.api.UserService;
import com.example.ecommercewebapp.library.rest.BaseController;
import com.example.ecommercewebapp.library.rest.MetaResponse;
import com.example.ecommercewebapp.library.rest.PageResponse;
import com.example.ecommercewebapp.library.rest.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    private UserService service;
    public UserController(UserService service) {
        this.service = service;
    }
    @PostMapping
    public Response<UserResponse> createUser(@RequestBody UserRequest request) {
        return respond(UserMapper.toResponse(service.createUser(UserMapper.toDto(request))));
    }

    @GetMapping("{id}")
    public Response<UserResponse> getUser(@PathVariable String id) {
        return respond(UserMapper.toResponse(service.getById(id)));
    }

    @PostMapping("{id}")
    public Response<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest request) {
        return respond(UserMapper.toResponse(service.update(UserMapper.toDto(request), id)));
    }

    @DeleteMapping("{id}")
    public Response<Void> deleteUser(@PathVariable String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }

    @GetMapping
    public Response<PageResponse<UserResponse>> getAllUsers(Pageable pageable) {
        return respond(UserMapper.toPageResponse(service.getAll(pageable)));
    }

}
