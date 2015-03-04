package info.herstell.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.application.FacesMessage;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validiert eMail-Adressen auf syntaktische Korrektheit
 */
//@FacesValidator(value = "myEmailValidator")
public class EmailValidator implements Validator {

   /**
    * Validiert den Wert des �bergebenen Objekt obj
    */
   public void validate(FacesContext ctx, UIComponent component,
                        Object obj)
      throws ValidatorException {

      // Ressourcen-Bundle laden
      ResourceBundle bundle = ResourceBundle.getBundle(
         "myMessages",
         ctx.getViewRoot().getLocale()
      );
       System.out.println("UI Komponente::::"+component.getClientId());
      // Fehlermeldung auslesen
      String errorKey = "errors_email";
      String errorMsg = bundle.getString(errorKey);

      // Fehlermeldung im Kontext speichern, damit diese ggf. auch
      // in der JSP zur Verf�gung steht
      FacesMessage msg = new FacesMessage(errorMsg);

      // 'null' ist ebenfalls ein ung�ltiger Wert
      if (obj == null) {
         throw new ValidatorException(msg);
      }

      // Muster g�ltiger eMail-Adressen als Regular Expression
      String pattern =
         "^[\\w-]+(?:\\.[\\w-]+)*@(?:[\\w-]+\\.)+[a-zA-Z]{2,7}$";

      // Pattern-Instanz erzeugen
      Pattern p = Pattern.compile(pattern);

      // Auf G�ltigkeit �berpr�fen
      Matcher m = p.matcher(obj.toString());
      if (!m.matches()) {
         // Wert nicht g�ltig
         throw new ValidatorException(msg);
      }
   }
}

