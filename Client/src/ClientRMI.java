import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ClientRMI extends Application
{
    static String pseudo;
    static ServicesDistants services;
    static ObjetsClients obj;

    /**
     * Show Login window
     * @param primaryStage stage
     * @throws IOException
     */
    public void LoginWindow(Stage primaryStage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        Pane page = loader.load();
        Scene scene = new Scene(page);
        primaryStage.setTitle("Login Window");
        primaryStage.setScene(scene);
        primaryStage.show();
        ClientController.stage = primaryStage;
    }

    
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        LoginWindow(primaryStage);   
    }

    
    @Override
    public void stop() throws RemoteException
    {
        System.out.println("Window Closing...");
        ClientController.services.Quitter(ClientController.obj);  
    }

    

    /**lancement de la fenetre
     * @throws NotBoundException
     * @throws RemoteException
     * @throws MalformedURLException
     * @throws AlreadyBoundException*/
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, AlreadyBoundException 
    {
        services = (ServicesDistants) Naming.lookup("ServicesDistants");
        launch(args);
    }

}
