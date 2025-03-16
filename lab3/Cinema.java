package lab3;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String name;
    private List<Hall> halls;

    public Cinema(String name) {
        this.name = name;
        this.halls = new ArrayList<>();
    }

    public String GetName() {
        return name;
    }

    public void AddHall(Hall hall) {
        halls.add(hall);
    }

    public List<Hall> GetHalls() {
        return halls;
    }
}
