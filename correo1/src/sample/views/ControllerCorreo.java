package sample.views;




import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import sample.clases.Correo;
import sample.logica.Logica;
import javax.mail.Folder;
import javax.mail.Store;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class ControllerCorreo  implements  Initializable{

    @FXML
    private MenuItem menu;

    @FXML
    private MenuItem menuModificar;

    @FXML
    private TableView<Correo> tableViewCorreo;

    @FXML
    private TreeView<String> treeView;
    @FXML
    private TextField vewMensaje;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Store store = Logica.getInstance().cargarStore("eduardocapinjavafx@gmail.com", "eduardojavafx");
        Folder[] a = Logica.getInstance().getCarpetasCorreo(store);
        Logica.getInstance().cargarListaCorreos(store, a);
        tableViewCorreo.setItems(Logica.getInstance().getListaCorreos());
        TreeItem<String> rootItem = new TreeItem<>(Logica.getInstance().getListaCuentas().get(0).getUser());
        Logica.getInstance().verTodasLasCarpetas(a, rootItem);
        treeView.setRoot(rootItem);

        EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                Correo correo = (Logica.getInstance().getListaCorreos().get(tableViewCorreo.getSelectionModel().getSelectedIndex()));
                vewMensaje.setText("correo.verContenidoCorreo(correo)");

            }

        };
        tableViewCorreo.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseHandler);


    }








}
