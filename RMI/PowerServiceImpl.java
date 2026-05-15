import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class PowerServiceImpl extends UnicastRemoteObject implements PowerService {
    protected PowerServiceImpl() throws RemoteException
    {
        super();
    }

    @Override
    public long calculatePower(int n) throws RemoteException {
        System.out.println("Processing request for " + n + " by " + Thread.currentThread().getName());
        return (long) Math.pow(2, n);
    }
}
