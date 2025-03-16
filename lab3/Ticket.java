package lab3;

public class Ticket {
    public void buyTicket(Session session, int row, int column) {
        try {
            if (session.isSeatAvailable(row, column)) {
                session.bookSeat(row, column);
                System.out.println("Место успешно забронировано!");
            } else {
                throw new Exception("Место уже занято!");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
