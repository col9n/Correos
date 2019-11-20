package sample.views;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.clases.Correo;
import sample.logica.Logica;

import javax.mail.Folder;
import javax.mail.Store;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCorreo  implements  Initializable{

    @FXML
    private MenuItem menu;

    @FXML
    private MenuItem menuModificar;

    @FXML
    private TableView<Correo> tableViewCorreo;

    @FXML
    private TreeView<String> treeView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Store store=Logica.getInstance().cargarStore("eduardocapinjavafx@gmail.com","eduardojavafx");
        Folder[] a= Logica.getInstance().getCarpetasCorreo(store);
        Logica.getInstance().cargarListaCorreos(store,a);
        tableViewCorreo.setItems(Logica.getInstance().getListaCorreos());
        TreeItem<String> rootItem = new TreeItem<>(Logica.getInstance().getListaCuentas().get(0).getUser());
        Logica.getInstance().verTodasLasCarpetas(a,rootItem);
        treeView.setRoot(rootItem);

    }
}
