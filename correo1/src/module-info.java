module correo1 {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.mail;


    opens sample.clases;
    opens sample.logica;
    opens sample.views;
    opens sample;

}