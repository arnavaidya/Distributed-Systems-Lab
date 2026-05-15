import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {
            String serverIP = "127.0.0.1";

            Registry registry = LocateRegistry.getRegistry(serverIP, 1099);
            PowerService service = (PowerService) registry.lookup("PowerService");

            int n = 10;
            long result = service.calculatePower(n);
            System.out.println(result);
        } catch (Exception e)   {
            e.printStackTrace();
        }
    }
}
