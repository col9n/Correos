package sample.views;


import com.sun.mail.imap.IMAPFolder;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import sample.clases.Correo;
import sample.clases.TreeItemPropio;
import sample.logica.Logica;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCorreo implements Initializable {

        @FXML
        private MenuItem menu;

        @FXML
        private MenuItem menuModificar;

        @FXML
        private TableView<Correo> tableViewCorreo;

        @FXML
        private TreeView<String> treeView;

    @FXML
    private WebView webView;



        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            Store store = Logica.getInstance().cargarStore("eduardocapinjavafx@gmail.com", "eduardojavafx");
            Folder[] a = Logica.getInstance().getCarpetasCorreo(store);

            TreeItemPropio rootItem = new TreeItemPropio(Logica.getInstance().getListaCuentas().get(0).getUser(),null,null);
            Logica.getInstance().verTodasLasCarpetas(a, rootItem,Logica.getInstance().getListaCuentas().get(0));
            treeView.setRoot(rootItem);


            EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {

                    Correo correo = (Logica.getInstance().getListaCorreos().get(tableViewCorreo.getSelectionModel().getSelectedIndex()));

                    try {
                        webView.getEngine().loadContent(correo.readHtmlContent(correo));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

            };
            tableViewCorreo.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseHandler);

            EventHandler<MouseEvent> mouseHandler1 = new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    try {
                        tableViewCorreo.getItems().clear();
                        TreeItemPropio treeItem = (TreeItemPropio)treeView.getSelectionModel().getSelectedItem();


                        Logica.getInstance().cargarListaCorreos(store, treeItem.getFolder());
                        tableViewCorreo.setItems(Logica.getInstance().getListaCorreos());

                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            };
            treeView.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseHandler1);


        }








}
