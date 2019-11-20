package sample.logica;

import com.sun.mail.imap.IMAPFolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import sample.clases.Correo;
import sample.clases.CuentaCorreo;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

public class Logica {

    private static Logica INSTANCE = null;


    private ObservableList<Correo> listaCorreos;
    private ObservableList<CuentaCorreo> listaCuentas;


    private Logica() {
        listaCorreos = FXCollections.observableArrayList();
        listaCuentas = FXCollections.observableArrayList();

    }

    public static Logica getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Logica();

        return INSTANCE;
    }


    public ObservableList<Correo> getListaCorreos() {
        return listaCorreos;
    }

    public ObservableList<CuentaCorreo> getListaCuentas() {
        return listaCuentas;
    }

    public void añadirCuenta(CuentaCorreo cuenta) {
        listaCuentas.add(cuenta);
    }

    public void borrarCuenta(CuentaCorreo cuenta) {
        listaCuentas.remove(cuenta);
    }


    public void cargarListaCorreos(Store store, Folder[] a) {
        try {
            /*
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);

            store = session.getStore("imaps");
           store.connect("imap.googlemail.com", user, pass);
           //store.connect("imap.googlemail.com", "eduardocapinjavafx@gmail.com", "eduardojavafx");

            // folder = (IMAPFolder) store.getFolder("[Gmail]/Spam"); // This doesn't work for other email account
            */
/*
 for (Folder folder : a) {
                if (!folder.isOpen()) {
                    folder.open(Folder.READ_WRITE);
                    if(folder.getType()==Folder.HOLDS_MESSAGES) {
                        Message[] messages = folder.getMessages();
                        Correo correo;
                        for (int b = 0; b < messages.length; b++) {
                            correo = new Correo(messages[b]/*,messages[a].getSubject());
            listaCorreos.add(correo);
        }
    }
}

 */

            for (Folder folder : a) {
                if (!folder.isOpen()) {
                    folder.open(Folder.READ_WRITE);
                }


                    Message[] messages = folder.getMessages();
                    Correo correo;
                    for (int b = 0; b < messages.length; b++) {
                        correo = new Correo(messages[b]/*,messages[a].getSubject()*/);
                        listaCorreos.add(correo);
                    }
                }


        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public Store cargarStore(String user, String pass) {
        IMAPFolder folder = null;
        Store store = null;
        String subject = null;
        Flags.Flag flag = null;
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);

            store = session.getStore("imaps");
            store.connect("imap.googlemail.com", user, pass);
            // CuentaCorreo cuentaCorreo1 = new CuentaCorreo("hola","ado",store);
            //listaCuentas.add(cuentaCorreo1);
            CuentaCorreo cuentaCorreo = new CuentaCorreo(user, pass, store);
            listaCuentas.add(cuentaCorreo);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return store;
    }

    public void desconectarStore(Store store) {
        try {
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public Folder[] getCarpetasCorreo(Store store) {
        Folder[] a = null;
        try {
            a = store.getDefaultFolder().list();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return a;
    }

    public void verTodasLasCarpetas(Folder[] a, TreeItem rootTreeItem) {
        try {
            TreeItem rootItem = null;
            for (Folder itemFolder : a) {
                //System.out.println(itemFolder.toString());
                rootItem = new TreeItem(itemFolder.getName());
                rootTreeItem.getChildren().add(rootItem);


                if (itemFolder.getType()==Folder.HOLDS_FOLDERS) {
                    verTodasLasCarpetas(itemFolder.list(), rootItem);
                }
            }
            /*
            else{

                if (!itemFolder.isOpen())
                    itemFolder.open(Folder.READ_WRITE);
                //Si queremos ver los mensajes de cada careta descomentar esto

                Message[] messages = itemFolder.getMessages();
                    for(Message c : messages) {
                        rootItem= new TreeItem(c.toString());
                        rootTreeItem.getChildren().add(rootItem);
                }


            }
            */
        } catch (MessagingException e) {
            e.printStackTrace();


        }
    }



}
