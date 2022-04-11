import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import javax.swing.ImageIcon;


public interface Notify extends Remote
{
    public void setInterface(String titre,ImageIcon photo, Double prixInitial, String description, Date date) throws RemoteException;
    public void setVendeur(String nom, String num) throws RemoteException;
    public void updateBid(Double prix) throws RemoteException;
}
