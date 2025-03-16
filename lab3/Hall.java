package lab3;
import java.util.Arrays;

public class Hall {
    private String name;
    private boolean[][] seats;

    public Hall(String name, int rows, int columns) {
        this.name = name;
        this.seats = new boolean[rows][columns];
    }

    public String GetName() {
        return name;
    }

    public boolean isSeatAvailable(int row, int column) {
        return !seats[row][column];
    }

    public void bookSeat(int row, int column) {
        seats[row][column] = true;
    }

    public void printSeating() {
        for(boolean[] row : seats) {
            for(boolean seat : row) {
                System.out.print(seat ? "[X] " : "[ ]");
            }
        }
    }
}
