import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicesDistants extends Remote
{
    public void Rejoindre(Notify n) throws RemoteException;
    public void Encherir(Double prix, String pseudo, Notify n) throws RemoteException;
    public void Quitter(Notify n) throws RemoteException;
}
