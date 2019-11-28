package sample.views;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.clases.CuentaCorreo;
import sample.logica.Logica;

import javax.mail.Store;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEscribirCorreo implements Initializable {
    private Stage stage;

    @FXML
    private Button enviarBoton;

    @FXML
    private TextField asunto;

    @FXML
    private TextField cc;

    @FXML
    private TextArea mensaje;

    @FXML
    private ComboBox<CuentaCorreo> combobox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Store store = Logica.getInstance().cargarStore("eduardocapinjavafx@gmail.com", "eduardojavafx");
        Store store1 = Logica.getInstance().cargarStore("damdijb@gmail.com", "123456A@");
        Store store2 = Logica.getInstance().cargarStore("martinlg36dam@gmail.com", "helipi67");

        combobox.getItems().addAll((Logica.getInstance().getListaCuentas()));
    }

    @FXML
    void enviar(ActionEvent event) {

    }

    public void setStage(Stage stage) {
    }
}
