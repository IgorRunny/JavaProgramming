package lab3;

public class Film {
    private String title;
    private int duration;

    public Film(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String GetTitle() {
        return title;
    }

    public int GetDuration() {
        return duration;
    }
}
