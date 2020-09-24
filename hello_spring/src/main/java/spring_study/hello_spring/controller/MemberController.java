package spring_study.hello_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring_study.hello_spring.domain.Member;
import spring_study.hello_spring.service.MemberService;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
