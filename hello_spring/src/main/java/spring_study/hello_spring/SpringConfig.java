package spring_study.hello_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_study.hello_spring.repository.MemberRepository;
import spring_study.hello_spring.repository.MemoryMemberRepository;
import spring_study.hello_spring.service.MemberService;

/**
 * java 코드로 직접 스프링 빈을 등록하는 방법
 */
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
