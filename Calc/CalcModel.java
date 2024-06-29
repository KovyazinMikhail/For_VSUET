import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalcModel {

    public double calculate(String input) {
        input = input.replaceAll("\\s", "");
        input = input.replaceAll(",", ".");

        String[] components = input.split("(?<=[-+*/^])|(?=[-+*/^])");

        List<String> equationParts = new ArrayList<>(Arrays.asList(components));

        for (int i = 0; i < equationParts.size(); i++) {
            if (equationParts.get(i).equals("*")) {
                double result = Double.parseDouble(equationParts.get(i - 1)) * Double.parseDouble(equationParts.get(i + 1));
                equationParts.set(i - 1, Double.toString(result));
                equationParts.remove(i);
                equationParts.remove(i);
                i = 0;
            } else if (equationParts.get(i).equals("/")) {
                double result = Double.parseDouble(equationParts.get(i - 1)) / Double.parseDouble(equationParts.get(i + 1));
                equationParts.set(i - 1, Double.toString(result));
                equationParts.remove(i);
                equationParts.remove(i);
                i = 0;
            } else if (equationParts.get(i).equals("^")) {
                double result = 1;
                for (int j = 1; j <= Double.parseDouble(equationParts.get(i + 1)); j++) {
                    result = result * Double.parseDouble(equationParts.get(i - 1));
                }
                equationParts.set(i - 1, Double.toString(result));
                equationParts.remove(i);
                equationParts.remove(i);
                i = 0;
            }
        }
        double result = Double.parseDouble(equationParts.get(0));
        for (int i = 1; i < equationParts.size(); i += 2) {
            if (equationParts.get(i).equals("+")) {
                result += Double.parseDouble(equationParts.get(i + 1));
            } else if (equationParts.get(i).equals("-")) {
                result -= Double.parseDouble(equationParts.get(i + 1));
            }
        }

        return result;
    }
}