package spring_study.hello_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spring_study.hello_spring.service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;

    /**
     * 스프링을 실행시키면 스프링 컨테이너가 컨트롤러 객체 생성을 할 때 생성자를 호출하는데
     * Autowired 어노테이션이 있으면 스프링 컨테이너에 있는 서비스 를 연결 시켜줌 -> Dependency Injection
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
