package Project.Project_library.Repository;

import Project.Project_library.domain.Room;
import Project.Project_library.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    //email을 키 값으로 user 객체 저장.
    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    //email을 키 값으로 user 값 반환.
    public User findByEmail(String email) {
        return users.get(email);
    }

    //한 회원 리턴
    public User findOne(String email) {
        return users.get(email);
    }


    //모든 회원 리턴
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }



//    //방 추가 --> 기존 이전 방식.
//    public String saveRoom(String email, Room room) {
//        User oldUser = users.get(email);
//        User updatedUser = oldUser.withRoom(room);
//        users.put(email, updatedUser); // 주어진 키에 값을 저장하거나, 키가 이미 존재하면 기존 값 덮어씀. --> 업데이트 됨.
//
//        return email;
//
//
//    }


}
