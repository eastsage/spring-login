package com.example.login.controller;

import com.example.login.repository.RoleRepository;
import com.example.login.security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import com.example.login.service.ResourcesService;
import com.example.login.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ResourcesController {
    private ResourcesService resourcesService;
    private RoleRepository roleRepository;
    private RoleService roleService;
    private UrlFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

}
