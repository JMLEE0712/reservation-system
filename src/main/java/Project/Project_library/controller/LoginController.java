package Project.Project_library.controller;

import Project.Project_library.UserService.UserService;
import Project.Project_library.domain.Room;
import Project.Project_library.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {


    private final UserService userService;


    // 회원가입용 테스트 데이터 (초기 세팅)
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String root(HttpSession session) {
        return "login"; // 루트 접근 시 로그인 페이지로 :login.html
    }



    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        if (userService.login(email, password)) { //수정 : 서비스 이용.
            session.setAttribute("loginUser", email);
            return "redirect:/main"; // 로그인 성공 url, GetMapping으로 이동.
        } else {
            model.addAttribute("error", "이메일 또는 비밀번호가 틀렸습니다.");
            return "login"; // 로그인 실패 → 다시 login.html
        }
    }


    @GetMapping("/main")//main 요청 받음.
    public String home(HttpSession session, Model model) {
        String email = (String) session.getAttribute("loginUser");

        User user = userService.findOne(email);
        model.addAttribute("user", user);

        //뷰에 데이터 보낼 때 사용.
        return "main"; //main 뷰를 찾아서 보냄. url은 redirect로 가져감.
    }


    // 나중에 리팩토링 예정. 회원목록에서 메인으로 돌아갈때,
    //로그인 처음하고 main가면, 계정명만 나오고.
    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate(); //세션 무효화, 세션 데이터 삭제.
        return "redirect:/"; // /.html은 없어서 TemplateInputException터짐.
    }







}