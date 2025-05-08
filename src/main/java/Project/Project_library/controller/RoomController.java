package Project.Project_library.controller;

import Project.Project_library.Repository.TimeRepository;
import Project.Project_library.Repository.UserRepository;
import Project.Project_library.UserService.TimeService;
import Project.Project_library.UserService.UserService;
import Project.Project_library.domain.Reservation;
import Project.Project_library.domain.Room;
import Project.Project_library.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RoomController {

    private final UserService userService;
    private final TimeService timeService;
    private final TimeRepository timeRepository;

    /* 나중에 변경.*/

    @Autowired
    public RoomController(UserService userService, TimeService timeService, TimeRepository timeRepository) {
        this.userService = userService;
        this.timeService = timeService;
        this.timeRepository = timeRepository;

    }



    @GetMapping("/reserve") //방 선택 후.
    public String reserveRoom(@RequestParam("room")Room room,
                              HttpSession session,
                              Model model)
    {

        String email = (String) session.getAttribute("loginUser");
        User user = userService.findOne(email);
        if (email != null) {

//            User valid = userRepository.findOne(email);
//
////            if (valid.getReservation()==null) {
////                //예약이 없으면 룸 번호 추가.
////                User user = userService.roomAdd(email, room);
////            }

            List<String> timeTemplate = timeService.timeShow(room);

//            model.addAttribute("email",email);
            model.addAttribute("room",room);
            model.addAttribute("times",timeTemplate);

            model.addAttribute("user",user);

            //현재 날짜 반환.
            model.addAttribute("date", LocalDate.now());

        }

        return "detail"; //날짜, 시간 예약페이지 detail로 이동.
    }

    @PostMapping("/reserve") //detail.html 에서 시간 날짜 정보 반환.
    public String reserve(@RequestParam LocalDate date,
                          @RequestParam List<String> times,
                          @RequestParam Room room,
                          HttpSession session,
                          Model model) {
        String email = (String) session.getAttribute("loginUser");

        if (email!=null && times.size() <=3) {
            Reservation reservation = new Reservation(date, times, room);
            userService.reserve(email, reservation);
            timeService.reserve(date,room,times);

            User user = userService.findOne(email);

            //임시
//            List<String> reserved = timeRepository.getReservedTime(date, room);

//            model.addAttribute("email", email);
            model.addAttribute("user",user);

            model.addAttribute("date", date);
            model.addAttribute("times",times);
            model.addAttribute("room",room);


            return "main";
        }

        else {
            model.addAttribute("error", "예약은 연속 3시간까지 가능합니다.");
            return "detail";
        }

    }

    @GetMapping("/cancel")
    public String cancelTime(HttpSession session, Model model)
    {
        //세션이 넘어오면
        String email = (String) session.getAttribute("loginUser");
        timeService.timeCancel(email);

        User user = userService.findOne(email);

        //인라인으로 변경.

        model.addAttribute("user", user);

//        model.addAttribute("email", user.getEmail());
//        model.addAttribute("room", user.getReservation().getRoom());
//        model.addAttribute("date", user.getReservation().getDate());
//        model.addAttribute("times", user.getReservation().getTimes());



        return "main";
    }





}
