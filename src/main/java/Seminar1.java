import java.util.Scanner;

public class Seminar1 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3 ,4};
//        System.out.println(test(arr, 10));

        printError(test1(arr, 10, 5));
        test2(new int[][]{{1, 2}, {1, 2}});
    }

//    Реализуйте метод, принимающий в качестве аргумента целочисленный массив.
//    Если длина массива меньше некоторого заданного минимума, метод возвращает -1, в качестве кода ошибки, иначе - длину массива.
    public static int test(int[] arr, int minValue) {
        if (arr.length < minValue) {
            return -1;
        }

        return arr.length;
    }

//    Реализуйте метод, принимающий в качестве аргумента целочисленный массив и некоторое значение. Метод ищет в массиве заданное значение и возвращает его индекс. При этом, например:
//    если длина массива меньше некоторого заданного минимума, метод возвращает -1, в качестве кода ошибки.
//    если искомый элемент не найден, метод вернет -2 в качестве кода ошибки.
//    если вместо массива пришел null, метод вернет -3
//    придумайте свои варианты исключительных ситуаций и верните соответствующие коды ошибок.
//    Напишите метод, в котором реализуйте взаимодействие с пользователем. То есть, этот метод запросит искомое число у пользователя, вызовет первый, обработает возвращенное значение и покажет читаемый результат пользователю. Например, если вернулся -2, пользователю выведется сообщение: “Искомый элемент не найден”.
    public static int test1(int[] arr, int value, int minValue) {
        if (arr == null) {
            return -3;
        }

        if (arr.length < minValue) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }

        return -2;
    }

    public static void printError(int error) {
        if (error == -1) System.out.println("Длина массива меньше заданного минимума");
        if (error == -2) System.out.println("Искомый элемент не найден");
        if (error == -3) System.out.println("Null");
    }

//    Реализуйте метод, принимающий в качестве аргумента целочисленный двумерный массив.
//    Необходимо посчитать и вернуть сумму элементов этого массива.
//    При этом накладываем на метод 2 ограничения: метод может работать только с квадратными массивами (кол-во строк = кол-ву столбцов), и в каждой ячейке может лежать только значение 0 или 1.
//    Если нарушается одно из условий, метод должен бросить RuntimeException с сообщением об ошибке.
    public static int test2(int[][] arr) {
        if (arr.length != arr[0].length) throw new RuntimeException("Массив не квадратный");

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] !=0 && arr[i][j] !=1) throw  new RuntimeException("Значение не 0 и не 1: " + arr[i][j] + " на индексах " + i + " " + j);
                sum += arr[i][j];
            }
        }

        return sum;
    }

//    Реализуйте метод checkArray(Integer[] arr), принимающий в качестве аргумента целочисленный одномерный массив.
//    Метод должен пройтись по каждому элементу и проверить что он не равен null.
//    А теперь реализуйте следующую логику:
//    Если в какой-то ячейке встретился null, то необходимо “оповестить” об этом пользователя
//    Если null’ы встретились в нескольких ячейках, то идеально было бы все их “подсветить”

    public static void checkArray(Integer[] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) sb.append(i).append(" ");
        }

        if (sb.length() > 0) throw new RuntimeException("Массив не пустой на индексах" + sb);
    }
}
