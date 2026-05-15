import client.*;
public class CalculatorClient {
    public static void main(String[] args) {
        CalculatorServiceService service = new CalculatorServiceService();
        CalculatorService calc = service.getCalculatorServicePort();

        int a = 10, b = 5;

        System.out.println("Add : " + calc.add(a, b) + "\n");
        System.out.println("Sub : " + calc.subtract(a, b) + "\n");
        System.out.println("Mul : " + calc.multiply(a, b) + "\n");
        System.out.println("Div : " + calc.divide(a, b) + "\n");
    }
}
