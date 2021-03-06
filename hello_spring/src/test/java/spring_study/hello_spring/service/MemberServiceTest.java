package spring_study.hello_spring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring_study.hello_spring.domain.Member;
import spring_study.hello_spring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 테스트의 메소드 이름은 한글로 해도 괜찮다 (더 직관적)
 */

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // test 실행 전 이루어져야 할 것
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 각각의 test 이후에 이루어져야 할 것
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

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

        /**
         * 정해진 Exception이 발생하는지를 검증하는 방법
         */
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        /**
         * 메시지를 검증하는 방법
         */
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*  try ~ catch 문을 사용해도 되지만 위의 간단한 코드 사용하는 것이 더 좋음
        try{
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}