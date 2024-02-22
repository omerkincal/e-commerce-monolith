
package com.example.ecommercewebapp.domain.auth.permission.web;

import com.example.ecommercewebapp.domain.auth.permission.api.PermissionMapper;
import com.example.ecommercewebapp.domain.auth.permission.api.PermissionService;
import com.example.ecommercewebapp.library.logrequestresponse.Log;
import com.example.ecommercewebapp.library.rest.BaseController;
import com.example.ecommercewebapp.library.rest.DataResponse;
import com.example.ecommercewebapp.library.rest.PageResponse;
import com.example.ecommercewebapp.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("permissions")
@RequiredArgsConstructor
@Log
public class PermissionController extends BaseController {

    private final PermissionService service;

    @GetMapping
    @Log(response = false)
    public Response<PageResponse<PermissionResponse>> getAllPermissions(Pageable pageable) {
        return respond(PermissionMapper.toPageResponse(service.getAll(pageable)));
    }

    @GetMapping("/all")
    @Log(response = false)
    public Response<DataResponse<PermissionResponse>> getAllPermissions() {
        return respond(PermissionMapper.toDataResponse(service.getAllPermissions()));
    }

}

    