package calculator;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class CalculatorService {

    @WebMethod
    public int add(int a, int b) {
        return a + b;
    }

    @WebMethod
    public int subtract(int a, int b) {
        return a - b;
    }

    @WebMethod
    public int multiply(int a, int b) {
        return a * b;
    }

    @WebMethod
    public double divide(int a, int b) {
        if (b == 0) return 0;
        return (double) a / b;
    }
}
