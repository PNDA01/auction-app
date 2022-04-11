import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClientController implements Initializable
{
    @FXML
    Button SignIn;
    @FXML
    TextField Username;
    @FXML
    Label ErrorSignIn;
    @FXML
    Button BidButton;
    @FXML
    Text Description;
    @FXML
    Text Prix;
    @FXML
    Label MessageBid;
    @FXML
    ImageView Image;
    @FXML
    Label Titre;
    @FXML
    Text Informations;
    @FXML
    TextField Enchere;
    @FXML
    Text Date;
    
    static Stage stage;
    static boolean started = false;
    static ObjetsClients obj = ClientRMI.obj;
    static ServicesDistants services = ClientRMI.services;

    public void SignIn() throws IOException, AlreadyBoundException, NotBoundException
    {
        String username = Username.getText();
        if(!username.isBlank())
        {
            ClientRMI.pseudo = username;
            stage.close();
            Interface();
        }
        else
        {
            ErrorSignIn.setText("Insert valid username");
        }
    }

    public void Interface() throws IOException, AlreadyBoundException, NotBoundException
    {
        started = true;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
        Pane page = loader.load();
        Scene scene = new Scene(page);
        stage.setTitle("Bidding Window");
        stage.setScene(scene);
        stage.show();
    }
    
    public void Bid() throws RemoteException, MalformedURLException, NotBoundException
    {
        try
        {    
            double bid = Double.parseDouble(Enchere.getText());
            MessageBid.setText("");
            services.Encherir(bid, ClientRMI.pseudo, obj);
        } 
        catch (NumberFormatException e) 
        {
            MessageBid.setText("Insert valid number");
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
        if(started)
        {
            try 
            {    
                System.out.println("Connected...");
                obj = new ObjetsClients(Titre, Image, Description, Prix, Enchere, Informations, Date);
                Naming.rebind("Notify", obj);
                
                services.Rejoindre(obj);
            } 
            catch (RemoteException | MalformedURLException e) 
            {
                e.printStackTrace();
            }
        }
    }
}
