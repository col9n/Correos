package sample.clases;

import javafx.beans.Observable;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;

public class Correo   {

    private Message mensaje;

    public Correo(Message mensaje) {
        this.mensaje = mensaje;
    }


    public String getAsunto(){
        String sub=null;
        try {
            sub = mensaje.getSubject();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return sub;
    }

    public Object getContenido(){
        Object obj=null;
        try {
            obj = mensaje.getContent();
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public  String getRemitente(){
        Address[] sub=null;
        try {
            sub=mensaje.getFrom();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return String.valueOf(sub[0]);
    }


}
