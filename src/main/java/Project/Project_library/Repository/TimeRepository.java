package Project.Project_library.Repository;

import Project.Project_library.domain.Reservation;
import Project.Project_library.domain.Room;
import Project.Project_library.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TimeRepository {
    // key: value "ROOM1|2025-05-03",: 예약된 시간 리스트

    private final Map<String, List<String>> reservedTimes = new HashMap<>();
    private List<String> allTimeSlots;


    public TimeRepository() {
        allTimeSlots = new ArrayList<>();
        for (int hour = 9; hour <= 21; hour++) {
            allTimeSlots.add(String.format("%02d:00", hour));
        }
    }


    //예약 추가시
    public void addReservation(LocalDate date, Room room, List<String> times) {
        String key = generateKey(date, room); //ROOM1|2025-05-03으로 키 만듬.
        reservedTimes.computeIfAbsent(key, k -> new ArrayList<>()).addAll(times); //해당 키 있으면 기존 리스트 반환, 없으면 times 다 넣어서 new ArrayList<>() 만들어서 맵에 추가.
    }



    public List<String> getReservedTimes(LocalDate date, Room room) {
        return reservedTimes.getOrDefault(generateKey(date, room), new ArrayList<>());//getOrDefault : 키 있으면 값 반환, 없으면 new ArrayList<>() 기본값 반환.
    }

    public boolean isTimeAvailable(LocalDate date, Room room, String time) {
        return !getReservedTimes(date, room).contains(time);
    }

    private String generateKey(LocalDate date, Room room) {
        return room.name() + "|" + date;
        //room.name() : enum 상수의 이름을 문자열로 반환 메소드.
    }


    /**
     * 임시.
     */ //사용된 시간 확인용.
//    public List<String> getReservedTimes(List<User> users, String date, Room room) {
//        List<String> reserved = new ArrayList<>();
//        for (User user : users) {
//            Reservation reservation = user.getReservation();
//            if (reservation != null &&
//                    reservation.getDate().equals(date) &&
//                    reservation.getRoom().equals(room)) {
//                reserved.addAll(reservation.getTimes());
//            }
//        }
//        return reserved;
//    }


}
