import ReverseModule.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;

public class ReverseClient {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            Reverse reverse = ReverseHelper.narrow(ncRef.resolve_str("ReverseService"));
            String input = "Distributed System";
            String result = reverse.reverseString(input);
            System.out.println("Input : " + input);
            System.out.println("Output: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
