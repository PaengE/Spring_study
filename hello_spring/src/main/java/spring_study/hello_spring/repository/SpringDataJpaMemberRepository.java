package spring_study.hello_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_study.hello_spring.domain.Member;

import java.util.Optional;

/**
 * 스프링 데이터 JPA 가 공통 구현체를 만들어 제공해줌
 * 인터페이스만으로 단순 쿼리가 해결됨.
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
