import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import javax.swing.ImageIcon;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ObjetsClients extends UnicastRemoteObject implements Notify
{
    Label Titre;
    Text Description;
    Text Prix;
    ImageView Image;
    TextField Enchere;
    Text Informations;
    Text Date;

    protected ObjetsClients(Label Titre, ImageView Image, Text Description, Text Prix, TextField Enchere, Text Informations, Text Date) throws RemoteException 
    {
        this.Titre = Titre;
        this.Description = Description;
        this.Prix = Prix;
        this.Enchere = Enchere;
        this.Informations = Informations;
        this.Image = Image;
        this.Date = Date;
    }

    @Override
    public void setInterface(String titre, ImageIcon photo, Double prixInitial, String description, Date date) throws RemoteException 
    {
        System.out.println("setInterface");
        Titre.setText(titre);
        BufferedImage bufim = new BufferedImage(photo.getIconWidth(), photo.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        bufim.createGraphics().drawImage(photo.getImage(), 0, 0, null);
        Image image = SwingFXUtils.toFXImage(bufim, null);
        Image.setImage(image);
        Prix.setText(prixInitial.toString());
        Description.setText(description);
        Date.setText(date.toString());
    }

    @Override
    public void setVendeur(String nom, String num) throws RemoteException 
    {
        System.out.println("setVendeur...");
        String message = "Felicitations!!!\nNom Vendeur: " + nom + "\nNumero Vendeur: " + num;
        Informations.setText(message);
    }

    @Override
    public void updateBid(Double prixNouveaux) throws RemoteException 
    {
        System.out.println("updateBid...");
        Prix.setText(prixNouveaux.toString());
    }
}
