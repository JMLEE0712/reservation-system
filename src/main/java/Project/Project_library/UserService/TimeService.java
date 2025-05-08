package Project.Project_library.UserService;

import Project.Project_library.Repository.TimeRepository;
import Project.Project_library.Repository.UserRepository;
import Project.Project_library.domain.AllTime;
import Project.Project_library.domain.Reservation;
import Project.Project_library.domain.Room;
import Project.Project_library.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeService {
    private final TimeRepository timeRepository;
    private final UserRepository userRepository;

    @Autowired
    public TimeService(TimeRepository timeRepository, UserRepository userRepository) {
        this.timeRepository = timeRepository;
        this.userRepository = userRepository;
    }

    public void reserve(LocalDate date, Room room, List<String> times) {
        timeRepository.addReservation(date, room, times);
    }

    /**
     * 시간을 보여주는 메소드.
     */
    public List<String> timeShow(Room room) {
        AllTime allTime = new AllTime();
        List<String> alltimes = allTime.getAlltime();
        List<User> all = userRepository.findAll(); //모든 유저 리스트로 받음.

        for (User user : all) {
            if (user.getReservation()!=null)
            { //예약이 없을때, 널포인트예외 안터지게.
                if (user.getReservation().getRoom() == room) {   //ex) room2랑 반복 객체 user가 고른 room이 room2랑 같으면.

                        List<String> times1 = user.getReservation().getTimes();
                        /*임시*/
                        List<String> cleanedTimes = times1.stream()
                                .map(String::trim) // 공백 제거
                                .collect(Collectors.toList());

                        alltimes.removeAll(cleanedTimes);



                }
            }
        }
        return alltimes;
    }


    //시간 취소. --> 전체 예약 초기화
    public void timeCancel(String email) {
        //form 태그로 name에 email이 날아왔다 가정. //아니면 세션에서 User 객체
        User user = userRepository.findOne(email);

        //전체 예약 초기화.
        user.setReservation(new Reservation(null, new ArrayList<>(), null));

//        user.getReservation().setTimes(new ArrayList<>());
        userRepository.save(user);


    }




}
