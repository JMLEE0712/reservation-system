package Project.Project_library.UserService;

import Project.Project_library.Repository.TimeRepository;
import Project.Project_library.Repository.UserRepository;
import Project.Project_library.domain.AllTime;
import Project.Project_library.domain.Reservation;
import Project.Project_library.domain.Room;
import Project.Project_library.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TimeServiceTest {

   @Autowired private TimeRepository timeRepository;
    @Autowired private UserRepository userRepository;



//    @Test
//    public void timeShowTest() {
//
//        List<String> temp = List.of("09:00", "10:00");
//
//        User user1 = new User("aa@na.com", "aa", Room.ROOM1);
//        Reservation reservation = new Reservation("2025-05-04", temp, Room.ROOM1);
//        user1.reserve(reservation);
//
//
//        userRepository.save(user1);
//
//        AllTime allTime = new AllTime();
//        List<String> alltimes = allTime.getAlltime();
//        List<User> all = userRepository.findAll(); //모든 유저 리스트로 받음.
//
//        for (User user : all) {
//            if (user.getRoom() == Room.ROOM1) {   //ex) room2랑 반복 객체 user가 고른 room이 room2랑 같으면.
//                if (user.getReservation() != null) { //처음이 아닌경우.
//                    List<String> times1 = user.getReservation().getTimes();
//                    alltimes.removeAll(times1);
//
//                    System.out.println("if문 실행 됨.");
//                    System.out.println("times1 = " + times1);
//                    System.out.println("alltimes = " + alltimes);
//
//
//                }
//            }
//
//
//        }
//    }


}