package com.example.login.domain.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
//@NoArgsConstructor(access = AccessLevel.PROTECTED)//jpa -> protected -> proxy 사용 위함
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "userId", "password"})
public class Member implements Serializable {

    /**
     * id => PK (Auto Increment) userId => String , 8~12자 영어 대소문자 가능 password => String , 8~16자 영어 대소문자 특수문자 1개씩 포함
     * email => 이메일 형태 name => 한글, 3~5자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]{8,12}$", message = "알파벳 8~12")
    @Column(unique = true)
    private String userId;
    //    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "알파벳 소문자, 대문자, 특수문자 1개 이상 그리고 8~16")
    private String password;
    @Email
    private String email;
    @Pattern(regexp = "^[가-힣]{3,5}$", message = "한글 3~5")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "member_roles", joinColumns = {@JoinColumn(name = "member_id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id")})
    private Set<Role> userRoles = new HashSet<>();


}
