package lab3;

import java.util.Scanner;

public class Console {
    public static void Start(Session session) {
        Scanner scanner = new Scanner(System.in);
        session.GetHall().printSeating();

        System.out.print("Введите ряд: ");
        int row = scanner.nextInt();
        System.out.print("Введите место: ");
        int column = scanner.nextInt();

        if(session.isSeatAvailable(row, column)) {
            session.bookSeat(row, column);
            System.out.println("Место забронировано!");
        } else {
            System.out.println("Место занято!");
        }
    }
}
