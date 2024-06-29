public class Main {
    public static void main(String[] args) {
        CalcController controller = new CalcController();
        controller.onStart();

        while (true) {

            controller.processUserInput();
        }

    }
}
