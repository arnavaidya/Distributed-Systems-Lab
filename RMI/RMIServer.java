import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            PowerService service = new PowerServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099); // Port Number
            registry.rebind("PowerService", service);
            System.out.println("Server Running ...");
        }   catch (Exception e) {
            e.printStackTrace();
        }
    }
}
