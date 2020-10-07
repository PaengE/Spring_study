package spring_study.hello_spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring_study.hello_spring.domain.Member;
import spring_study.hello_spring.repository.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 테스트의 메소드 이름은 한글로 해도 괜찮다 (더 직관적)
 */

@SpringBootTest
@Transactional  // @Transactional 어노테이션의 기능: 테스트가 끝나면 ROLLBACK 하는 기능
                // test case 에서만 ROLLBACK
                // 개발 코드에서는 COMMIT 해줌 (커밋하고 싶으면 @COMMIT 을 써도 됨)
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);


        // 정해진 Exception 이 발생하는지를 검증하는 방법
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 메시지를 검증하는 방법
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}