package com.example.login.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @Id @GeneratedValue
    private Long id;
    private String userId;
    private String password;
    private String email;
    private String name;

    public Member(String userId, String password, String email, String name) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
    }
}
