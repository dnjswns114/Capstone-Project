package com.hooong.simpleMember.Controller;

import com.hooong.simpleMember.Dto.MemberDto;
import com.hooong.simpleMember.Service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    @GetMapping("/")
    public String index() {
        return "/home/index";
    }

    @GetMapping("/member/signup")
    public String signupForm(Model model) {
        model.addAttribute("member",new MemberDto());

        return "/member/signupForm";
    }

    @PostMapping("/member/signup")
    public String signup(MemberDto memberDto) {
        memberService.signUp(memberDto);

        return "redirect:/";
    }

    @GetMapping("/member/login")
    public String login() {
        return "/member/loginForm";
    }

    @GetMapping("/accouncement")
    public String accouncement() {
        {return "/accouncement/accouncement";}
    }


    @GetMapping("/weather")
    public String weather() {
        {return "/weather/weather";}
    }

    @GetMapping("/calendar")
    public String calendar() {
        {return "/calendar/calendar";}
    }

    @GetMapping("/memo")
    public String memo() {
        {return "/memo/memo";}
    }

    @GetMapping("/main")
    public String main() {
        {return "/main/main";}
    }


}
