import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class Seminar2 {
    public static void main(String[] args) {
        test();
    }

//    📔 Запишите в файл следующие строки:
//    Анна=4
//    Елена=5
//    Марина=6
//    Владимир=?
//    Константин=?
//    Иван=4
//    Реализуйте метод, который считывает данные из файла и сохраняет в двумерный массив (либо HashMap, если студенты с ним знакомы). В отдельном методе нужно будет пройти по структуре данных, если сохранено значение ?, заменить его на соответствующее число.Если на каком-то месте встречается символ, отличный от числа или ?, бросить подходящее исключение.Записать в тот же файл данные с замененными символами ?.

    public static void test() {
        BufferedReader br;

        try {
            FileReader fr = new FileReader("seminar2.txt");
            br = new BufferedReader(fr);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        HashMap<String, Integer> map = new HashMap<>();

        String line;

        try {
            while ((line = br.readLine()) != null) {
                String[] data = line.split("=");

                try {
                    map.put(data[0], Integer.parseInt(data[1]));
                } catch (Exception e) {
                    map.put(data[0], data[0].length());
                }

            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        saveFile(map);
    }

    public static void saveFile(HashMap<String, Integer> map) {
        FileWriter fr;

        try {
            fr = new FileWriter("seminar2_.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        StringBuilder sb = new StringBuilder();

        Set<String> keys = map.keySet();
        for (String key: keys) {
            sb.append(key).append("=").append(map.get(key).toString()).append("\n");
        }

        try {
            fr.write(sb.toString());
            fr.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
