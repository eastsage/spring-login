package com.example.login.security.configuration;

import com.example.login.security.factory.UrlResourcesFactoryBean;
import com.example.login.security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import com.example.login.service.SecurityResourceService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class AuthenticationConfig {
    private final AuthenticationConfiguration configuration;
    private final SecurityResourceService securityResourceService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
//                .antMatchers("/memberPage").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/")
//                .usernameParameter("userId")
//                .and()
//                .logout().logoutUrl("/logout").invalidateHttpSession(true)
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class)
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(new JwtFilter(LoginService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private AccessDecisionManager affirmativeBased() {
        AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDescriptionVoters());
        return affirmativeBased;
    }

    // AcessDecisionManager 내부에서 찬반 여부를 정하는 Voter 생성
    private List<AccessDecisionVoter<?>> getAccessDescriptionVoters() {

        List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
        accessDecisionVoters.add(roleVoter());

        return accessDecisionVoters;
    }

    @Bean
    public AccessDecisionVoter<? extends Object> roleVoter() {
        return new RoleHierarchyVoter(roleHierarchy());
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        //Spring에 구현되어있음 그냥 가져다 써도 됨 그거 가져다 써볼 예정
        return new RoleHierarchyImpl();
    }

    // 자원에 대한 권한 정보를 읽는 MetadataSource 생성
    @Bean
    public FilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() throws Exception {
//        return new CustomUrlFilterInvocationSecurityMetadataSource();
        return new UrlFilterInvocationSecurityMetadataSource(urlResourcesMapFactoryBean().getObject(),
                securityResourceService);
    }

    private UrlResourcesFactoryBean urlResourcesMapFactoryBean() {
        UrlResourcesFactoryBean urlResourcesFactoryBean = new UrlResourcesFactoryBean();
        urlResourcesFactoryBean.setSecurityResourceService(securityResourceService);
        return urlResourcesFactoryBean;
    }


    // MetadataSource 를 사용하는 권한 체크 필터,
    // 즉 FilterSecurityInterceptor 를 직접 생성한다.
    @Bean
    public FilterSecurityInterceptor customFilterSecurityInterceptor() throws Exception {

        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor
                .setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());

        // 권한체크 필터는 현재 사용자가 인증된 사용자인지 검사하기 때문에 인증 매니저가 필요하다.
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager());
        return filterSecurityInterceptor;
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return configuration.getAuthenticationManager();
    }

}