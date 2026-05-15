import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PowerService extends Remote
{
    public long calculatePower(int n) throws RemoteException;
}
