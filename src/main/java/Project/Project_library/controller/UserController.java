package Project.Project_library.controller;

import Project.Project_library.UserService.UserService;
import Project.Project_library.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 화면
    @GetMapping("/join")
    public String showJoinForm() {
        return "join"; // 회원가입 페이지로 이동
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String join(@RequestParam String email, @RequestParam String password) {
        User user = new User(email, password);
        userService.join(user); // 서비스 계층에서 회원 가입 처리
        return "redirect:/"; // 회원가입 후 로그인 페이지로 리다이렉트
    }

    // 회원 목록 화면
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers()); // 모든 회원 정보 모델에 담기
        return "user-list"; // 회원 목록 화면으로 이동
    }
}
