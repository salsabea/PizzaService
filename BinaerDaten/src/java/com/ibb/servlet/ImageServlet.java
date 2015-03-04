package com.ibb.servlet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// 'Codec' und 'Encoder' sind bereits im Java SDK enthalten
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder ;

import javax.imageio.ImageIO;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Erzeugt ein dynamisches Bild und �bermittelt es an den Client
 */
@WebServlet(name = "myImage",urlPatterns = "/bilder/image")
public class ImageServlet extends HttpServlet {

   /**
    * Service-Methode des Servlets
    */
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {

      // Setzen des Content-Types (MIME), damit der Browser die
      // bin�ren Informationen auch korrekt interpretieren kann
      response.setContentType("image/jpeg");

      // Erzeugen des Grundbildes: ...
      int width = 400;
      int height = 50;
      BufferedImage image =
         new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D g = (Graphics2D) image.getGraphics();

      // ... Setzen der Hintergrundfarbe, ...
      g.setColor(Color.gray);
      g.fillRect(0, 0, width, height);

      // ... ein wenig Text ...
      g.setColor(Color.white);
      g.setFont(new Font("Dialog", Font.PLAIN, 24));
      g.drawString("Java EE ist cool", 68, 35);

      // ... und ein kleiner Rahmen.
      g.setColor(Color.black);
      g.drawRect(0, 0, width - 1, height - 1);

      // Aufr�umen im AWT
      g.dispose();

      // Öffnen des OutputStreams, danach darf 'getWriter()'
      // NICHT mehr gerufen werden!
      ServletOutputStream sos = response.getOutputStream();

      // Senden des erzeugten Bildes an den Client
      
      //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
      ImageIO.write(image, "jpeg", sos);
     // encoder.encode(image);
   }
}
