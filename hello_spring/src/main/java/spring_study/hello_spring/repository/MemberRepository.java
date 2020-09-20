package spring_study.hello_spring.repository;

import spring_study.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

/** 회원 레포지토리 인터페이스 */
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
