package Project.Project_library.UserService;

import Project.Project_library.Repository.UserRepository;
import Project.Project_library.domain.Reservation;
import Project.Project_library.domain.Room;
import Project.Project_library.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 로그인 처리
    public boolean login(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    // 회원가입 처리
    public void join(User user) {
        userRepository.save(user);
    }


    // 한 사용자 조회
    public User findOne(String email) {
        return userRepository.findOne(email);
    }

    // 모든 사용자 조회
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

////    ROOM 추가.
//    public User roomAdd(String email, Room room) {
//        String Idemail = userRepository.saveRoom(email, room);
//        User roomuser = userRepository.findOne(Idemail);
//        return roomuser;
//
//    }




    // 예약 추가 (detail 시간 추가)
    public void reserve(String email, Reservation reservation) {
        User user = userRepository.findByEmail(email);
        if(user != null)
        {
            user.reserve(reservation);
            userRepository.save(user); //User에 reserve까지 포함한 객체 HahMap에 put.

        }
    }



}
