import java.util.Arrays;

public class hw1 {
    public static void main(String[] args) {
        task1();
        task2(args);
        task3(args);
    }

    public static void task1() {
        Answer1 ans = new Answer1();
        try {
            ans.arrayOutOfBoundsException();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Выход за пределы массива");
        }

        try {
            ans.divisionByZero();
        } catch (ArithmeticException e) {
            System.out.println("Деление на ноль");
        }

        try {
            ans.numberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования строки в число");
        }
    }

    public static void task2(String[] args) {
        int[] a = {};
        int[] b = {};

        if (args.length == 0) {
            // При отправке кода на Выполнение, вы можете варьировать эти параметры
            a = new int[]{4, 5, 6};
            b = new int[]{1, 2, 3};
        } else {
            a = Arrays.stream(args[0].split(", ")).mapToInt(Integer::parseInt).toArray();
            b = Arrays.stream(args[1].split(", ")).mapToInt(Integer::parseInt).toArray();
        }

        Answer2 ans2 = new Answer2();
        System.out.println(Arrays.toString(ans2.subArrays(a, b)));
    }

    public static void task3(String[] args) {
        int[] a = {};
        int[] b = {};

        if (args.length == 0) {
            // При отправке кода на Выполнение, вы можете варьировать эти параметры
            a = new int[]{12, 8, 16};
            b = new int[]{4, 2, 4};
        } else {
            a = Arrays.stream(args[0].split(", ")).mapToInt(Integer::parseInt).toArray();
            b = Arrays.stream(args[1].split(", ")).mapToInt(Integer::parseInt).toArray();
        }

        Answer3 ans = new Answer3();
        String itresume_res = Arrays.toString(ans.divArrays(a, b));
        System.out.println(itresume_res);
    }

    public static class Answer1 {
        public static void arrayOutOfBoundsException() {
            int[] arr = new int[]{1, 2, 3, 4};
            System.out.println(arr[5]);
        }

        public static void divisionByZero() {
            System.out.println(10 / 0);
        }

        public static void numberFormatException() {
            System.out.println(Integer.parseInt("abc"));
        }
    }

    public static class Answer2 {
        public int[] subArrays(int[] a, int[] b) {
            if (a.length != b.length) {
                return new int[1];
            }

            int[] newArr = new int[a.length];
            for (int i = 0; i < a.length; i++) {
                newArr[i] = a[i] - b[i];
            }

            return newArr;
        }
    }

    public static class Answer3 {
        public int[] divArrays(int[] a, int[] b) {
            // Введите свое решение ниже
            if (a.length != b.length) return new int[1];

            int[] newArr = new int[a.length];
            for (int i = 0; i < a.length; i++) {
                newArr[i] = a[i] / b[i];
            }

            return newArr;
        }
    }
}



