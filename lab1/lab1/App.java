package lab1;

import java.util.Scanner;

public class App {
    private static Scanner scanner;
    
    //task_1
    private static int AmountOfSteps(int n){
        int count = 0;

        while(n != 1)
        {
            if(n % 2 == 0){
                n /= 2;
            }
            else{
                n = 3 * n + 1;
            }
            count++;
        }

        return count;
    }

    //task_2
    private static int SeqSum(int n){
        int output = 0;

        for(int i = 0; i < n; i++){
            System.out.print("Print number: ");
            int num = scanner.nextInt();

            if(i % 2 == 0){
                output += num;
            }
            else{
                output -= num;
            }
        }

        return output;
    }

    //task_3
    private static int FindTreasure(int finalX, int finalY){
        int amountOfSteps = 0;
        int counter = 0;
        int x = 0;
        int y = 0;

        String direction = scanner.next();
        while(!direction.equals("stop")){
            int step = scanner.nextInt();
            switch (direction) {
                case "north":
                    y += step;
                    break;
                case "south":
                    y -= step;
                    break;
                case "east":
                    x += step;
                    break;
                case "west":
                    x -= step;
                    break;
                default:
                    break;
            }

            counter ++;
            direction = scanner.next();

            if(x == finalX && y == finalY && amountOfSteps == 0){
                amountOfSteps = counter;
            }
        }
        return amountOfSteps;
    }

    //task_4
    public static void MaxMin(){
        int n = scanner.nextInt();
        int answer = 0;
        int road = 0;
        for (int i = 1; i <= n; ++i) {
            int m = scanner.nextInt();
            int minVal = scanner.nextInt();
            for (int j = 1; j < m; ++j) {
                minVal = Math.min(minVal, scanner.nextInt());
            }
            if(minVal >= answer){
                road = i;
            }
            answer = Math.max(answer, minVal);
        }
        System.out.println(road + "," + answer);
    }

    //task_5
    public static boolean IsDoublyEven(int n){
        if(n < 100 || n > 999){
            System.out.println("number must be three-digit!");
            return false;
        }
        int sum = 0;
        int product = 1;
        while(n > 0){
            int tmp = n % 10;
            sum += tmp;
            product *= tmp;
            n /= 10;
        }
        return (sum % 2 == 0 && product % 2 == 0 ? true : false);
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        
        //task_1
        System.out.print("print start number: ");
        int input = scanner.nextInt();
        System.out.println("Amount of steps: " + AmountOfSteps(input));

        //task_2
        System.out.print("print amount of steps: ");
        input = scanner.nextInt();
        System.out.println("Sum: " + SeqSum(input));

        //task_3
        System.out.print("write the coordinates of the treasure (first X, then Y): ");
        int inputX = scanner.nextInt();
        int inputY = scanner.nextInt();
        System.out.println("amount of steps: " + FindTreasure(inputX, inputY));

        //task_4
        System.out.print("print amount of roads: ");
        MaxMin();

        //task_5
        System.out.print("print three-digit number: ");
        input = scanner.nextInt();
        System.out.print("number is " + (IsDoublyEven(input) ? "doubly even" : "not doubly even"));
    }
}
