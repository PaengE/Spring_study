package spring_study.hello_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_study.hello_spring.domain.Member;
import spring_study.hello_spring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

/**
 * 회원 서비스 개발
 */
//@Service
@Transactional
// JPA 는 모든 데이터 변경이 트랜잭션 안에서 실행 되어야 함.
public class MemberService {

    /**
     * 이런 것을 MemberService 입장에서 Dependency Injection 이라고 함.
     * 직접 new를 해서 객체를 생성하는게 아니라 외부에서 객체를 넣임 당하는 것.
     */
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 중복되는 로직은 메소드로 빼는게 좋음 (단축키 : Ctrl + Shift + Alt + v)
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 아이디로 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
