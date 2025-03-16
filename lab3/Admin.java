package lab3;

public class Admin extends User {
    public Admin(String userName, String password) {
        super(userName, password);
    }

    public void AddMovie(Cinema cinema, Film movie) {
        System.out.println("Фильм \"" + movie.GetTitle() + "\" добавлен в кинотеатр \"" + cinema.GetName() + "\"!");
    }
}
