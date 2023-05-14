//package com.example.login.repository;
//
//import static org.assertj.core.api.Assertions.*;
//
//import com.example.login.domain.entity.Member;
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//@SpringBootTest
//@Transactional
//@Rollback(value = false)
//class MemberJpaRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//    EntityManager em;
//
//    @Test
//    public void basicCURD() {
//        Member member1 = new Member("membermembe", "asdasdASD!@#", "sd@sd.com", "김정은");
//        Member member2 = new Member("membermember", "asdASDasd!@#", "sd@sd.com", "김일성");
//        memberRepository.save(member1);
//        memberRepository.save(member2);
//
//        //단건 조회
//        Member findMember1 = memberRepository.findById(member1.getId()).get();
//        Member findMember2 = memberRepository.findById(member2.getId()).get();
//        assertThat(findMember1).isEqualTo(member1);
//        assertThat(findMember2).isEqualTo(member2);
//
//        Member findMember3 = memberRepository.findByUserId(member1.getUserId()).get();
//        Member findMember4 = memberRepository.findByUserId(member2.getUserId()).get();
//        assertThat(findMember3).isEqualTo(member1);
//        assertThat(findMember4).isEqualTo(member2);
//        System.out.println(findMember3);
//        System.out.println(member1);
//        System.out.println(findMember4);
//        System.out.println(member2);
//
////        //리스트 조회
////        List<Member> all = memberRepository.findAll();
////        assertThat(all.size()).isEqualTo(2);
////
////        //데이터 수 검증
////        long count = memberRepository.count();
////        assertThat(count).isEqualTo(2);
////
////        //삭제 검증
////        memberRepository.delete(member1);
////        memberRepository.delete(member2);
////        long delCount = memberRepository.count();
////        assertThat(delCount).isEqualTo(0);
//    }
//}