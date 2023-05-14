package com.example.login.service;

import com.example.login.domain.entity.Resources;
import com.example.login.repository.ResourcesRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class SecurityResourceService {
    private ResourcesRepository resourceRepository;

    public SecurityResourceService(ResourcesRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {

        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> resourcesList = resourceRepository.findAllResources();
        resourcesList.forEach(resources -> {
            List<ConfigAttribute> configAttributeList = new ArrayList<>();
            resources.getRoleSet().forEach(role -> {
                configAttributeList.add(new SecurityConfig(role.getRoleName()));
                result.put(new AntPathRequestMatcher(resources.getResourceName()), configAttributeList);
            });
        });
        return result;
    }
}
