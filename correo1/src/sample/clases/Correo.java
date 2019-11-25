package sample.clases;


import org.apache.commons.mail.util.MimeMessageParser;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    public  String getRemitente(){
        Address[] sub=null;
        try {
            sub=mensaje.getFrom();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return String.valueOf(sub[0]);
    }



    public Message getMensaje() {
        return mensaje;
    }

    public void setMensaje(Message mensaje) {
        this.mensaje = mensaje;
    }

    public String readHtmlContent(Correo correo) throws Exception {
        MimeMessage message=(MimeMessage) correo.getMensaje();
        return new MimeMessageParser(message).parse().getHtmlContent();
    }

    public String readPlainContent(Correo correo) throws Exception {
        MimeMessage message=(MimeMessage) correo.getMensaje();
        return new MimeMessageParser(message).parse().getPlainContent();
    }

}
