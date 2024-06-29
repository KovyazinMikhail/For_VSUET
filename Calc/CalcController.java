import java.util.Objects;

public class CalcController {

    private CalcModel model;
    private CalcView view;

    public CalcController() {
        this.model = new CalcModel();
        this.view = new CalcView();
    }

    public CalcController(CalcModel model, CalcView view) {
        this.model = model;
        this.view = view;
    }

    public void onStart(){
        view.displayHeader();
    }

    public void processUserInput() {
        view.clearDisplay();
        String input = view.getUserInput();
        if (Objects.equals(input, "exit")) {
            System.exit(0);
        }
        if (Objects.equals(input, "help")) {
            view.displayHelp();
        }else {
            double result = model.calculate(input);
            view.displayResult(result);
        }
    }

    public static void main(String[] args) {
        CalcModel model = new CalcModel();
        CalcView view = new CalcView();
        CalcController controller = new CalcController(model, view);

        controller.processUserInput();
    }
}