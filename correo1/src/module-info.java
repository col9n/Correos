module correo1 {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.mail;
    requires javafx.web;
    requires commons.email;

    exports sample.clases;
    exports sample.logica;
    exports sample.views;
    exports sample.utils;
    exports sample;
    opens sample.views to javafx.fxml;

}