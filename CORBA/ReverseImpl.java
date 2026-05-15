import ReverseModule.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

class ReverseImpl extends ReversePOA {
    public String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
