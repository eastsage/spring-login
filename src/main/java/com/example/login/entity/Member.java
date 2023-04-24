package com.example.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)//jpa -> protected -> proxy 사용 위함
@ToString(of = {"id", "userId", "password"})
public class Member {

    /**
     *  id => PK (Auto Increment)
     * 	userId => String , 8~12자 영어 대소문자 가능
     * 	password => String , 8~16자 영어 대소문자 특수문자 1개씩 포함
     * 	email => 이메일 형태
     * 	name => 한글, 3~5자
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
    private String ROLE_USER;

    public Member(String userId, String password, String email, String name) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
    }
}
