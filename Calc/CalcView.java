import java.util.Scanner;

public class CalcView {

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public void displayResult(double result) {
        System.out.println("Результат: " + result);
    }

    public void clearDisplay(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displayHeader(){
        System.out.println("КАЛЬКУЛЯТОР УРАВНЕНИЙ");
        System.out.println();
        System.out.println("///////////////////////////////////////////");
        System.out.println("Для вызова справки введите 'help'");
        System.out.println("Для выхода из программы введите 'exit'");
        System.out.println("///////////////////////////////////////////");
        System.out.println();
        System.out.println("Введите ваше уравнение:");
    }
    public void displayHelp(){
        System.out.println("СПРАВКА ПО ОПЕРАЦИЯМ");
        System.out.println("///////////////////////////////////////////");
        System.out.println("Примеры стандартных операций:");
        System.out.println("///////////////////////////////////////////");
        System.out.println();
        System.out.println("Сложение - 5+5");
        System.out.println("Вычитание - 5-2");
        System.out.println("Умножение - 5*3");
        System.out.println("Деление - 5/3");
        System.out.println("Возведение в степень - или 5^2");
        System.out.println("Деление без остатка - 5//3");
        System.out.println("Остаток от деления - 5%3");
        System.out.println();
        System.out.println("///////////////////////////////////////////");
        System.out.println();
        System.out.println("Калькулятор поддерживает стандартные арифметические операции, за исключением выражения в скобках.");
        System.out.println("///////////////////////////////////////////");
        System.out.println();
        System.out.println("Введите ваше уравнение:");
    }
}