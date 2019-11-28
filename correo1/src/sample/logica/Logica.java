package sample.logica;


import com.sun.mail.imap.IMAPFolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import sample.clases.Correo;
import sample.clases.CuentaCorreo;
import sample.clases.TreeItemPropio;

import javax.mail.*;
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


    public void cargarListaCorreos(Store store, Folder a) {

        try {


            Folder folder = (IMAPFolder) store.getFolder(a.getFullName()); // This doesn't work for other email account
            if (!folder.isOpen() && folder.getType()==3) {

                folder.open(Folder.READ_WRITE);
            }

            if(folder.getType()==3) {

                Message[] messages = folder.getMessages();
                Correo correo;
                for (int b = 0; b < messages.length; b++) {
                    correo = new Correo(messages[b]);
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

    public void verTodasLasCarpetas(Folder[] a, TreeItemPropio rootTreeItem,CuentaCorreo cuentaCorreo) {
        try {
            TreeItemPropio rootItem = null;
            for (Folder itemFolder : a) {

                rootItem = new TreeItemPropio(itemFolder.getName(),cuentaCorreo,itemFolder); //Con full nombre para poder cargar las carpetas
                rootTreeItem.getChildren().add(rootItem);

                if (itemFolder.getType()==Folder.HOLDS_FOLDERS) {
                    verTodasLasCarpetas(itemFolder.list(), rootItem,cuentaCorreo);
                }
            }

        } catch (MessagingException e) {
            e.printStackTrace();


        }
    }

    public TreeItem cargarTreeView(){
        TreeItem nodoPadre = new TreeItem("Lista de cuentas de correo");

        for(int b=0;b<Logica.getInstance().getListaCuentas().size();b++) {
            nodoPadre.setExpanded(true);

            TreeItemPropio rootItem = new TreeItemPropio(Logica.getInstance().getListaCuentas().get(b).getUser(), null, null);
            Logica.getInstance().verTodasLasCarpetas( Logica.getInstance().getCarpetasCorreo(Logica.getInstance().getListaCuentas().get(b).getStore()), rootItem, Logica.getInstance().getListaCuentas().get(b));
            nodoPadre.getChildren().add(rootItem);
        }
        return nodoPadre;


    }




}
