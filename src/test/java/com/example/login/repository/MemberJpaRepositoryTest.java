package com.example.login.repository;

import static org.assertj.core.api.Assertions.*;

import com.example.login.entity.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;
    EntityManager em;

    @Test
    public void basicCURD() {
        Member member1 = new Member("membermembe", "asdasdASD!@#", "sd@sd.com", "김정은");
        Member member2 = new Member("membermember", "asdASDasd!@#", "sd@sd.com", "김일성");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        //단건 조회
        Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        Member findMember3 = memberJpaRepository.findFirstByUserId(member1.getUserId()).get();
        Member findMember4 = memberJpaRepository.findFirstByUserId(member2.getUserId()).get();
        assertThat(findMember3).isEqualTo(member1);
        assertThat(findMember4).isEqualTo(member2);

        //리스트 조회
        List<Member> all = memberJpaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        //데이터 수 검증
        long count = memberJpaRepository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증
        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);
        long delCount = memberJpaRepository.count();
        assertThat(delCount).isEqualTo(0);
    }
}