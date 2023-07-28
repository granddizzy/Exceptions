import java.io.FileWriter;
import java.io.IOException;

public class hw3 {
    public static void main(String[] args) {
        String str = input();

        int result = checkingTheAmountOfData(str);

        if (result == 1) {
            System.out.println("Данных больше чем нужно");
            return;
        } else if (result == 2) {
            System.out.println("Данных меньше чем нужно");
            return;
        }

        String fio = "";
        String birthday = "";
        String gender = "";
        String phoneNumber = "";

        try {
            fio = parseFIO(str);
            birthday = parseBirthday(str);
            gender = parseGender(str);
            phoneNumber = parsePhoneNumber(str);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }

        String path = fio.split(" ")[0] + ".txt";
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(createFileLine(fio, birthday, gender, phoneNumber));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String createFileLine(String fio, String birthday, String gender, String phoneNumber) {
        String[] dataFIO = fio.split(" ");
        StringBuilder sb = new StringBuilder();

        sb.append("<").append(dataFIO[0]).append(">");
        sb.append("<").append(dataFIO[1]).append(">");
        sb.append("<").append(dataFIO[2]).append(">");
        sb.append("<").append(birthday).append(">");
        sb.append("<").append(phoneNumber).append(">");
        sb.append("<").append(gender).append(">");

        return sb.toString();
    }

    public static String parseFIO(String str) throws RuntimeException {
        String[] data = str.split(" ");
        int count = 0;
        StringBuilder fio = new StringBuilder();
        for (String tmp : data) {
            boolean isFIO = true;
            for (int i = 0; i < tmp.length(); i++) {
                if (tmp.length() < 2 || !Character.isAlphabetic(tmp.charAt(i))) {
                    isFIO = false;
                    break;
                }
            }

            if (isFIO) {
                if (!fio.isEmpty()) fio.append(" ");
                fio.append(tmp);
                count++;
            } else if (!fio.isEmpty()) {
                break;
            }
        }

        if (count == 3) return fio.toString();

        throw new RuntimeException("Не найден ФИО");
    }

    public static String parseGender(String str) {
        String[] data = str.split(" ");

        for (String tmp : data) {
            for (int i = 0; i < tmp.length(); i++) {
                if (tmp.length() == 1 && (tmp.equals("f") || !tmp.equals("m"))) {
                    return tmp;
                }
            }
        }

        throw new RuntimeException("Не найден пол");
    }

    public static String parseBirthday(String str) {
        String[] data = str.split(" ");

        for (String tmp : data) {
            boolean isBirthday = true;
            String[] dataBirhday = tmp.split("\\.");

            if (dataBirhday.length != 3) {
                isBirthday = false;
            }

            if (isBirthday) {
                return tmp;
            }
        }

        throw new RuntimeException("Не найдена дата рождения");
    }

    public static String parsePhoneNumber(String str) {
        String[] data = str.split(" ");

        for (String tmp : data) {
            boolean isPhoneNumber = true;
            for (int i = 0; i < tmp.length(); i++) {
                if (!Character.isDigit(tmp.charAt(i))) {
                    isPhoneNumber = false;
                    break;
                }
            }

            if (isPhoneNumber) {
                if (tmp.length() != 12) throw new WrongLengthPhoneNumberException(tmp.length());
                return tmp;
            }
        }

        throw new RuntimeException("Не найден телефонный номер");
    }

    public static String input() {
//        System.out.println("Введите ФИО, Дату рождения, Номер телефона, Пол через пробел");
//        System.out.println("Фамилия, Имя, Отчество - строки\nДата рождения - строка формата dd.mm.yyyy\nНомер телефона - целое беззнаковое число без форматирования\nпол - символ латиницей f или m);
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextLine();

        String str = "06.08.1982 Лепихов Роман Олегович 375293493335 f";

        return str;
    }

    public static int checkingTheAmountOfData(String str) {
        String[] data = str.split(" ");

        if (data.length > 6) return 1;
        else if (data.length < 6) return 2;

        return 0;
    }

    public static class WrongLengthPhoneNumberException extends RuntimeException {
        public WrongLengthPhoneNumberException(int length) {
            super("Длина телефонного номера должна быть 12 цифр а у вас: " + length);
        }
    }
}