package es.boxinggymmanager.models;

import es.boxinggymmanager.beans.Peticion;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import jakarta.mail.*;
import jakarta.mail.internet.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class Utilities {


    /**
     * metodo para convertir una cadena a MD5
     * @param input Cadena que queremos convertir a MD5
     * @return cadena convertida a MD5
     */
    public static String passwordMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * metodo para transformar de un string a Enum
     * @return un tipo Enum
     */
    public static Enum convertirStringEnum(String tipo){
        Peticion.TipoEntrenamiento tipoEntrenamiento = null;
        switch (tipo) {
            case "BASICO":
                tipoEntrenamiento = Peticion.TipoEntrenamiento.BASICO;

                break;
            case"CONTACTO_DIRIGIDO":
               tipoEntrenamiento = Peticion.TipoEntrenamiento.CONTACTO_DIRIGIDO;
                break;

            case"CARDIOBOX":
                tipoEntrenamiento = Peticion.TipoEntrenamiento.CARDIOBOX;
                break;

            case"TECNICO_ESPECIALIZADO":
                tipoEntrenamiento = Peticion.TipoEntrenamiento.TECNICO_ESPECIALIZADO;
                break;

        }

        return tipoEntrenamiento;

    }

    /**
     * metodo para enviar correos
     * recibe como parametros request, destinatario, nombre, telf, entrenamiento
     * */
    public static void enviarCorreo(HttpServletRequest request, String destinatario, String nombre, String telefono, String tipoEntrenamiento) throws MessagingException {

        final String remitente = "tucorreo"; // correo
        final String claveApp = "tuclave"; //  clave de aplicación de Gmail
        final String asunto = "Petición Gimnasio Boxeo";

        // Creamos el HTML que vamos a enviar
        String html = "<!DOCTYPE html>\n"
                + "<html lang=\"es\">\n"
                + "<head>\n"
                + "  <meta charset=\"UTF-8\">\n"
                + "  <title>Confirmación de Petición</title>\n"
                + "</head>\n"
                + "<body style=\"font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px; color: #333;\">\n"
                + "  <div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; padding: 30px; box-shadow: 0 2px 5px rgba(0,0,0,0.1);\">\n"
                + "    <div style=\"text-align: center;\">\n"
                + "      <img src=\"cid:logoImagen\" alt=\"Logo\" style=\"max-width: 150px; margin-bottom: 20px;\">\n"
                + "    </div>\n"
                + "    <h2 style=\"color: #d32f2f;\">¡Gracias por tu petición, " + nombre + "!</h2>\n"
                + "    <p>Hemos recibido tu solicitud de entrenamiento de boxeo. Estos son los datos que nos proporcionaste:</p>\n"
                + "    <ul style=\"list-style: none; padding-left: 0;\">\n"
                + "      <li><strong>📧 Email:</strong> " + destinatario + "</li>\n"
                + "      <li><strong>📞 Teléfono:</strong> " + telefono + "</li>\n"
                + "      <li><strong>💪 Tipo de Entrenamiento:</strong> " + tipoEntrenamiento + "</li>\n"
                + "    </ul>\n"
                + "    <p>Nos pondremos en contacto contigo lo antes posible. Gracias por confiar en nosotros.</p>\n"
                + "    <div style=\"margin-top: 30px; text-align: center;\">\n"
                + "      <p style=\"font-size: 0.9em; color: #888;\">Este mensaje fue enviado automáticamente. Por favor, no respondas a este correo.</p>\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</body>\n"
                + "</html>";

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.debug", "true");

        // creamos la sesion
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, claveApp);
            }
        });
// creamos el mensaje
        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensaje.setSubject(asunto);


            // Parte HTML
            MimeBodyPart htmlPart = new MimeBodyPart();
           htmlPart.setContent(html, "text/html; charset=utf-8");

            // creamos la imagen y obtenemos la ruta con el request
            MimeBodyPart imagePart = new MimeBodyPart();
            // usamos ServletContext.getRealPath() para obtener la ruta real en tiempo de ejecución
            String rutaLogo = request.getServletContext().getRealPath("/Images/Logo.png");
            File logo = new File(rutaLogo);

            // añadimos a la imagen el logo, el contentID
            imagePart.attachFile(logo);
            imagePart.setContentID("<logoImagen>");
            imagePart.setDisposition(MimeBodyPart.INLINE);

        // Creamos el multipart donde añadimos el html y la imagen
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(imagePart);

// añadimos el multipart al mensaje y lo enviamos
            mensaje.setContent(multipart);
            Transport.send(mensaje);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
