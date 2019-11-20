package sample.clases;

import com.sun.mail.imap.IMAPFolder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import javax.mail.*;
import java.util.Properties;


public class TreeViewSample extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        IMAPFolder folder = null;
        Store store = null;
        String subject = null;
        Flags.Flag flag = null;
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);

            store = session.getStore("imaps");
            // store.connect("imap.googlemail.com", user, pass);
            store.connect("imap.googlemail.com", "eduardocapinjavafx@gmail.com", "eduardojavafx");


            primaryStage.setTitle("Tree View Sample");

            Folder[] a = store.getDefaultFolder().list();
            TreeItem<String> rootItem = new TreeItem<>("eduardocapinjavafx@gmail.com");
            verTodasLasCarpetas(a,rootItem);
            //Ya tenemos el treeitem raiz completamente rellenado.
            //Asignaro al treeview
            TreeView<String> treeView = new TreeView<String>();
            treeView.setRoot(rootItem);


           primaryStage.setScene(new Scene(treeView, 300, 250));
            primaryStage.show();



        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void verTodasLasCarpetas(Folder[] a,TreeItem rootTreeItem) throws MessagingException {

        TreeItem rootItem=null;
        for (Folder itemFolder : a) {

            System.out.println(itemFolder.toString());
            rootItem= new TreeItem(itemFolder.toString());
            rootTreeItem.getChildren().add(rootItem);
            if (itemFolder.list().length > 0) {
                verTodasLasCarpetas(itemFolder.list(),rootItem);
            }
            else{
                /*
                if (!itemFolder.isOpen())
                    itemFolder.open(Folder.READ_WRITE);
                //Si queremos ver los mensajes de cada careta descomentar esto

                Message[] messages = itemFolder.getMessages();
                    for(Message c : messages) {
                        rootItem= new TreeItem(c.toString());
                        rootTreeItem.getChildren().add(rootItem);
                }
                */

            }
        }



    }
}