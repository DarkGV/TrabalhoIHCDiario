/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diariosecreto;

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class LoggedInController implements Initializable {
    
    private DatabaseConnection dbConnection;
    
    @FXML
    private Label data;
    
   @FXML
    private Label nomeutilizador;
   
   @FXML
    private Label hora;

   @FXML
   private TextArea mainText;
   
   @FXML
   private void mouseEntered(MouseEvent event){
       ImageView imageToChange = (ImageView)event.getSource();
       imageToChange.setImage(new Image(getClass().getResource("backbtnover.png").toString()));
      
   }
   
   @FXML
   private void mouseExited(MouseEvent event){
       ImageView imageToChange = (ImageView)event.getSource();
       imageToChange.setImage(new Image(getClass().getResource("backbtn.png").toString()));
   }
   
   @FXML
   private void returnMain(MouseEvent event){
       DiarioLayoutController.diaryStage.hide();
       DiarioLayoutController.thisStage.show();
   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Calendar now = Calendar.getInstance();

        int ano = now.get(Calendar.YEAR);
        int dia = now.get(Calendar.DAY_OF_MONTH);
        int mes = now.get(Calendar.MONTH) + 1;
        
        String strData = ano + "/" + mes + "/" + dia;
        
         data.setText(strData);
         
        int horas;
        int minutos;
        
        GregorianCalendar calend = new GregorianCalendar();
        horas= calend.get(Calendar.HOUR_OF_DAY);
        minutos = calend.get(Calendar.MINUTE);
        
        hora.setText("" + horas + ":" + minutos);
        
        mainText.setWrapText(true);
        
        dbConnection = new DatabaseConnection("UsersDB.db");
        
        String s = dbConnection.hasEntrance(strData, "AHAHAH MINHA MACHADINHA");
        
        System.out.println(s);
    }    
    
   
    
}
