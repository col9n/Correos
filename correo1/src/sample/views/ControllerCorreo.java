package sample.views;


import com.sun.mail.imap.IMAPFolder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.clases.Correo;
import sample.clases.CuentaCorreo;
import sample.clases.TreeItemPropio;
import sample.logica.Logica;


import javax.mail.Store;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCorreo  implements Initializable {
    private Stage stage;
    @FXML
    private MenuItem correo;

    @FXML
    private MenuItem cuenta;

    @FXML
    private TableView<Correo> tableViewCorreo;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private WebView webView;



        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            Store store = Logica.getInstance().cargarStore("eduardocapinjavafx@gmail.com", "eduardojavafx");
            Store store1 = Logica.getInstance().cargarStore("damdijb@gmail.com", "123456A@");
            Store store2 = Logica.getInstance().cargarStore("martinlg36dam@gmail.com", "helipi67");


            treeView.setRoot(Logica.getInstance().cargarTreeView());


            /**Filtro para selecionar las correos de la tabla*/
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


            /**Filtro para selecionar las carpetas del treeView*/
            EventHandler<MouseEvent> mouseHandler1 = new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    try {
                        tableViewCorreo.getItems().clear();
                        TreeItemPropio treeItem = (TreeItemPropio)treeView.getSelectionModel().getSelectedItem();
                        if (treeItem!=null) {
                            Logica.getInstance().cargarListaCorreos(treeItem.getCuenta().getStore(), treeItem.getFolder());
                            tableViewCorreo.setItems(Logica.getInstance().getListaCorreos());
                        }

                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            };
            treeView.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseHandler1);



        }



    @FXML
    void nuevoCorreo(ActionEvent event) {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("controllerEscribirCorreo.fxml"));
            Parent root = fxmlLoader.load();
            ControllerEscribirCorreo controllerEscribirCorreo = (ControllerEscribirCorreo) fxmlLoader.getController();
            controllerEscribirCorreo.setStage(stage);
            stage.setTitle("Escribir Correo");
            stage.setScene(new Scene(root, 600, 400));

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        stage.showAndWait();

    }

    public void setStage(Stage stage) {
    }






}
