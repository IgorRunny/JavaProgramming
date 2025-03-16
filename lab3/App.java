package lab3;
import java.time.LocalDateTime;
import java.util.Scanner;

import lab3.Hall;
import lab3.Session;
import lab3.Admin;
import lab3.Cinema;
import lab3.Film;
import lab3.Ticket;
import lab3.User;
import lab3.Console;
public class App {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Admin admin = new Admin("admin", "admin");
        User user = new User("user", "123456890");

        System.out.print("Введите логин: ");
        String username = scanner.next();
        System.out.print("Введите пароль: ");
        String password = scanner.next();

        User currentUser = null;

        if (admin.Login(username, password)) {
            currentUser = admin;
        } else if (user.Login(username, password)) {
            currentUser = user;
        }

        if (currentUser == null) {
            System.out.println("Ошибка: Неверные данные!");
            return;
        }

        System.out.println("Добро пожаловать, " + username + "!");
        
        Cinema cinema = new Cinema("КиноПарк");
        Hall hall = new Hall("Зал 1", 7, 6);
        cinema.AddHall(hall);
        Film film = new Film("фильм ", 90);
        Session session = new Session(film, hall, LocalDateTime.of(2004, 11, 29, 15, 30));

        if (currentUser instanceof Admin) {
            Admin adminUser = (Admin) currentUser;
            System.out.print("Введите название нового фильма: ");
            scanner.nextLine();
            String newMovieTitle = scanner.nextLine();
            System.out.print("Введите длительность фильма (в минутах): ");
            int duration = scanner.nextInt();

            Film newFilm = new Film(newMovieTitle, duration);
            adminUser.AddMovie(cinema, newFilm);
        } else {
            Console.Start(session);
        }
    }
}
