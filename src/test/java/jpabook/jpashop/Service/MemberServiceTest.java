package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static org.springframework.test.util.AssertionErrors.fail;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() throws Exception{
        // given
        Member member = new Member();
        member.setName("Kim");

        // when
        Long savedId = memberService.join(member);

        // then
        Assertions.assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test()
    void 중복_회원_예외()  throws Exception{
        // given
        Member member1 = new Member();
        member1.setName("Kim");

        Member member2 = new Member();
        member2.setName("Kim");

        memberService.join(member1);
        Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

}