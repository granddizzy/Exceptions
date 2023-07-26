import java.util.Scanner;

public class hw2 {
    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
        task4();
    }

    public static Float task1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число: ");
        while (true) {
            String line = sc.nextLine();
            if (checkInput(line)) return Float.parseFloat(line);
            else System.out.println("Вы ввели не число. Введите заново !!!");
        }
    }

    public static boolean checkInput(String line) {
        try {
            Float.parseFloat(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void task2() {
        try {
            int d = 0;

            double[] intArray = new double[8];

            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        }
    }

    public static void task3() {
        try {
            int a = 90;
            int b = 3;
            System.out.println(a / b);
            printSum(23, 234);
            int[] abc = {1, 2};
            abc[3] = 9;
//        } catch (NullPointerException ex) {
//            System.out.println("Указатель не может указывать на null!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Массив выходит за пределы своего размера!");
        } catch (Throwable ex) {
            System.out.println("Что-то пошло не так...");
        }
    }

    public static void printSum(Integer a, Integer b) {
        System.out.println(a + b);
    }

    public static void task4() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите строку (пустые нельзя): ");

        String line = sc.nextLine();

        try {
            if (line.length() == 0) {
                throw new RuntimeException("Пустые строки вводить НЕЛЬЗЯ !!!");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
}
