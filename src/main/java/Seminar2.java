import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class Seminar2 {
    public static void main(String[] args) {
        test();
    }

//    Запишите в файл следующие строки:
//    Анна=4
//    Елена=5
//    Марина=6
//    Владимир=?
//    Константин=?
//    Иван=4
//    Реализуйте метод, который считывает данные из файла и сохраняет в двумерный массив (либо HashMap, если студенты с ним знакомы).
//    В отдельном методе нужно будет пройти по структуре данных, если сохранено значение ?, заменить его на соответствующее число.
//    Если на каком-то месте встречается символ, отличный от числа или ?, бросить подходящее исключение.
//    Записать в тот же файл данные с замененными символами ?.

    public static void test() {
        String path = "seminar2.txt";
        HashMap<String, String> map = readFile(path);
        replace(map);
        saveFile(map, "seminar2_.txt");
    }

    public static boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void replace(HashMap<String, String> map) {
        Set<String> keys = map.keySet();
        for (String key: keys) {
            if (!isNumber(map.get(key))) {
                if (map.get(key).equals("?")) {
                    map.put(key, Integer.toString(map.get(key).length()));
                } else {
                    throw new RuntimeException("Не верные символы");
                }
            }
        }
    }

    public static HashMap<String, String> readFile(String path) {
        BufferedReader br;

        HashMap<String, String> map = new HashMap<>();

        try {
            FileReader fr = new FileReader(path);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return map;
        }

        String line;

        try {
            while ((line = br.readLine()) != null) {
                String[] data = line.split("=");
                map.put(data[0], data[1]);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return map;
    }

    public static void saveFile(HashMap<String, String> map, String path) {
        FileWriter fr;

        try {
            fr = new FileWriter(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        StringBuilder sb = new StringBuilder();

        Set<String> keys = map.keySet();
        for (String key: keys) {
            sb.append(key).append("=").append(map.get(key)).append("\n");
        }

        try {
            fr.write(sb.toString());
            fr.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
