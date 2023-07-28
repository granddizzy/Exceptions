import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Seminar3 {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            doSomethin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Counter counter = new Counter()) {
            counter.add();
            System.out.println(counter.getCount());
            counter.close();
            System.out.println(counter.getCount());
        } catch (Exception e) {
//            throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }

        try {
            int a = 5 / 0;
        } catch (ArithmeticException e) {
//            throw new DivByZero();
            System.out.println(new DivByZero().getMessage());
        }

        try {
            Integer[] a = {null, 2, 3};
            int index = 0;
            a[index]++;
        } catch (NullPointerException e) {
//            throw new NullElement(0);
            System.out.println(new NullElement(0).getMessage());
        }

        String path = "fsdfsdf";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        } catch (IOException e) {
//            throw new FileNotFoundException();
            System.out.println(new FileNotExist(path).getMessage());
        }

        String[][] arr = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1d", "2", "3", "4"}};
        try {
            int sum = checkArray(arr);
            System.out.println(sum);
        } catch (MyArrayDataException | MyArraySizeException e) {
            System.out.println(e.getMessage());
        }
    }

//    Создайте метод doSomething(), который может быть источником одного из типов checked exceptions (тело самого метода прописывать не обязательно).
//    Вызовите этот метод из main и обработайте в нем исключение, которое вызвал метод doSomething().

    public static void doSomethin() throws IOException {

    }

//    Создайте класс Счетчик, у которого есть метод add(), увеличивающий значение внутренней int переменной на 1.
//    Сделайте так, чтобы с объектом такого типа можно было работать в блоке try-with-resources.
//    Подумайте, что должно происходить при закрытии этого ресурса? Напишите метод для проверки, закрыт ли ресурс.
//    При попытке вызвать add() у закрытого ресурса, должен выброситься IOException

    public static class Counter implements AutoCloseable {
        private Integer count = 0;

        public void add() throws IOException {
            if (count == null) throw new IOException("Закрыто");
            count++;
        }

        public Integer getCount() throws IOException {
            if (count == null) throw new IOException("Закрыто");
            return count;
        }

        public void close() throws Exception {
            count = null;
        }
    }

//    Создайте класс исключения, который будет выбрасываться при делении на 0.
//    Исключение должно отображать понятное для пользователя сообщение об ошибке.
//    Создайте класс исключений, которое будет возникать при обращении к пустому элементу в массиве ссылочного типа.
//    Исключение должно отображать понятное для пользователя сообщение об ошибке
//    Создайте класс исключения, которое будет возникать при попытке открыть несуществующий файл.
//    Исключение должно отображать понятное для пользователя сообщение об ошибке.

    static class DivByZero extends ArithmeticException {
        public DivByZero() {
            super("Деление на 0");
        }
    }

    static class NullElement extends NullPointerException {
        public NullElement(int index) {
            super("Элемент пуст: " + index);
        }

        public NullElement() {
            super("Элемент пуст");
        }
    }

    static class FileNotExist extends FileNotFoundException {
        public FileNotExist(String path) {
            super("Файла не существует: " + path);
        }

        public FileNotExist() {
            super("Файла не существует");
        }
    }

//    Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
//    При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//    Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
//    Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
//    должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
//    В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и MyArrayDataException
//    и вывести результат расчета (сумму элементов, при условии что подали на вход корректный массив).

    public static int checkArray(String[][] arr) {
        if (arr.length != 4 || arr[0].length != 4) {
            throw new MyArraySizeException(arr.length, arr[0].length);
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i , j);
                }
            }
        }

        return sum;
    }

    public static class MyArraySizeException extends RuntimeException {
        public MyArraySizeException(int x, int y) {
            super("Размер массива не подходящий. Ваш размер: " + x + " на " + y);
        }
    }

    public static class MyArrayDataException extends NumberFormatException {
        public MyArrayDataException(int x, int y) {
            super("Не могу преобразовать значение: " + x + " : " + y);
        }
    }
}
