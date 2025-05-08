package Project.Project_library.domain;

import java.time.LocalDate;
import java.util.List;

public class Reservation {
    private LocalDate date; //yyyy-MM-dd
    private List<String> times; //["10:00", "11:00", "12:00"]
    private Room room;

    //생성자
    public Reservation(LocalDate date, List<String> times, Room room) {
        this.date = date;
        this.times = times;
        this.room = room;
    }

    //Getter Setter
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
