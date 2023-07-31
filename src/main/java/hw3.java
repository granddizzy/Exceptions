import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class hw3 {
    public static void main(String[] args) {
        while (true) {
            String str = input();

            // завершение программы при вводе пустой строки
            if (str.isEmpty()) break;

            // проверка количества введенных данных
            int result = checkingTheAmountOfData(str);
            if (result == 1) {
                System.out.println("Данных больше чем нужно");
                continue;
            } else if (result == 2) {
                System.out.println("Данных меньше чем нужно");
                continue;
            }

            String fio = "";
            String birthday = "";
            String gender = "";
            String phoneNumber = "";

            // попытка получить данные из строки
            try {
                fio = parseFIO(str);
                birthday = parseBirthday(str);
                gender = parseGender(str);
                phoneNumber = parsePhoneNumber(str);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // попытка записать данные в файл
            try {
                saveData(fio, birthday, gender, phoneNumber);
                System.out.println("Данные записаны.");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
        System.out.println("Программа завершена");
    }


    /**
     * Записывает данные в файл
     * @param fio
     * @param birthday
     * @param gender
     * @param phoneNumber
     * @throws IOException
     */
    public static void saveData(String fio, String birthday, String gender, String phoneNumber) throws IOException {

        String[] dataFIO = fio.split(" ");
        String path = dataFIO[0] + ".txt";

        boolean hasAlready = false;

        String newLine = createFileLine(fio, birthday, gender, phoneNumber);

        // проверяем если такие данные уже есть
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();
            while (line != null) {

                if (line.replace("\n", "").equals(newLine.replace("\n", ""))) {
                    hasAlready = true;
                }

                line = bufferedReader.readLine();
            }
        } catch (IOException ignored) {

        }

        // добавляем данные в файл
        if (!hasAlready) {
            try (FileWriter fw = new FileWriter(path, true)) {
                fw.write(newLine);
            } catch (IOException e) {
                throw new IOException(e);
            }
        }
    }

    /**
     * Создает строку из данных для записи в файл
     * @param fio
     * @param birthday
     * @param gender
     * @param phoneNumber
     * @return
     */
    public static String createFileLine(String fio, String birthday, String gender, String phoneNumber) {
        String[] dataFIO = fio.split(" ");
        StringBuilder sb = new StringBuilder();

        sb.append("<").append(dataFIO[0]).append(">");
        sb.append("<").append(dataFIO[1]).append(">");
        sb.append("<").append(dataFIO[2]).append(">");
        sb.append("<").append(birthday).append(">");
        sb.append("<").append(phoneNumber).append(">");
        sb.append("<").append(gender).append(">");
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Парсит ФИО
     * @param str
     * @return
     * @throws RuntimeException
     */
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

    /**
     * Парсит пол
     * @param str
     * @return
     */
    public static String parseGender(String str) {
        String[] data = str.split(" ");

        for (String tmp : data) {
            for (int i = 0; i < tmp.length(); i++) {
                if (tmp.length() == 1 && (tmp.equals("f") || tmp.equals("m"))) {
                    return tmp;
                }
            }
        }

        throw new RuntimeException("Не найден пол");
    }

    /**
     * Парсит день рождения
     * @param str
     * @return
     */
    public static String parseBirthday(String str) {
        String[] data = str.split(" ");

        for (String tmp : data) {
            boolean isBirthday = true;
            String[] dataBirhday = tmp.split("\\.");

            if (dataBirhday.length != 3) {
                isBirthday = false;
            }

            if (isBirthday) {
                try {
                    Integer.parseInt(dataBirhday[0]);
                } catch (NumberFormatException e) {
                    throw new WrongBirthdayException("Не верно указан день.");
                }

                try {
                    Integer.parseInt(dataBirhday[1]);
                } catch (NumberFormatException e) {
                    throw new WrongBirthdayException("Не верно указан месяц.");
                }

                try {
                    Integer.parseInt(dataBirhday[2]);
                } catch (NumberFormatException e) {
                    throw new WrongBirthdayException("Не верно указан год.");
                }

                if (dataBirhday[0].length() != 2)
                    throw new WrongBirthdayException("В значении дня должно быть 2 знака.");
                if (dataBirhday[1].length() != 2)
                    throw new WrongBirthdayException("В значении месяца должно быть 2 знака.");
                if (dataBirhday[2].length() != 4)
                    throw new WrongBirthdayException("В значении года должно быть 4 знака.");

                LocalDate currentDate = LocalDate.now();

                if (Integer.parseInt(dataBirhday[0]) < 1)
                    throw new WrongBirthdayException("Не верная дата. День должен быть от 1");
                if (Integer.parseInt(dataBirhday[1]) < 1 || Integer.parseInt(dataBirhday[1]) > 12)
                    throw new WrongBirthdayException("Не верная дата. Месяц должен быть от 1 до 12");
                if (Integer.parseInt(dataBirhday[2]) > currentDate.getYear())
                    throw new WrongBirthdayException("Не верная дата. Год больше " + currentDate.getYear());
                if (Integer.parseInt(dataBirhday[2]) < currentDate.getYear() - 150)
                    throw new WrongBirthdayException("Вы вампир ?");

                Calendar calendar = new GregorianCalendar(Integer.parseInt(dataBirhday[2]),  Integer.parseInt(dataBirhday[1]) -1, 1);
                int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                if (Integer.parseInt(dataBirhday[0]) > maxDays) {
                    throw new WrongBirthdayException("В месяце " + Integer.parseInt(dataBirhday[1]) + " " + Integer.parseInt(dataBirhday[2]) + " года только " + maxDays + " дня.");
                }

                return tmp;
            }
        }

        throw new RuntimeException("Не найдена дата рождения");
    }

    /**
     * Парсит телефонный номер
     * @param str
     * @return
     */
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

    /**
     * Запрашивает данные у пользователя
     * @return
     */
    public static String input() {
        System.out.println("Введите ФИО, Дату рождения, Номер телефона, Пол через пробел");
        System.out.println("Фамилия, Имя, Отчество - строки\nДата рождения - строка формата dd.mm.yyyy\nНомер телефона - целое беззнаковое число без форматирования\nпол - символ латиницей f или m");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

//        String str = "31.02.1982 Лепихов Роман Олегович 375293493335 f";
//        return str;
    }

    /**
     * Проверяет количество данных в строке
     * @param str
     * @return
     */
    public static int checkingTheAmountOfData(String str) {
        String[] data = str.split(" ");

        if (data.length > 6) return 1;
        else if (data.length < 6) return 2;

        return 0;
    }

    /**
     * Класс исключения при неправильной длине номера телефона
     */
    public static class WrongLengthPhoneNumberException extends RuntimeException {
        public WrongLengthPhoneNumberException(int length) {
            super("Длина телефонного номера должна быть 12 цифр, а у вас: " + length);
        }
    }

    /**
     * Класс исключения при неправильном формате даты рождения
     */
    public static class WrongBirthdayException extends RuntimeException {
        public WrongBirthdayException(String str) {
            super(str);
        }
    }
}