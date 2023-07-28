import java.util.Scanner;

public class hw3 {
    public static void main(String[] args) {
        String[] data = new String[0];

        try {
            data = input();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        int indexFIO = 0;
        int indexGender = 0;
        int indexPhoneNumber = 0;
        int indexBirthday = 0;


        for (String str: data) {
            if ()
        }


        // создать файл
    }

    public static boolean isPhoneNumber(String str) throws {
        try {

        } catch () {
            return false;
        }

        return true;
    }

    public static boolean isFIO(String str) {
        try {

        } catch () {
            return false;
        }

        return true;
    }

    public static boolean isBirthday(String str) {
        try {

        } catch () {
            return false;
        }

        return true;
    }

    public static boolean isGender(String str) {
        try {

        } catch () {
            return false;
        }

        return true;
    }

    public static String[] input() throws RuntimeException {
//        System.out.println("Введите ФИО, Дату рождения, Номер телефона, Пол");
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextLine();

        String str =  "06.08.1982 Лепихов Роман +375293493335 Олегович M";

        String[] arr = str.split(" ");
        if (arr.length > 6) throw new RuntimeException("Данных больше чем нужно");
        else if (arr.length < 6) throw new RuntimeException("Данных меньше чем нужно");

        return arr;
    }

//    public static class MoreDataException extends RuntimeException {
//        public MoreDataException() {
//            super("Данных больше чем нужно");
//        }
//    }
//
//    public static class LessDataException extends RuntimeException {
//        public LessDataException() {
//            super("Данных меньше чем нужно");
//        }
//    }

}