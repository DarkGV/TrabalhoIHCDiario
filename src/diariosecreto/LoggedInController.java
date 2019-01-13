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
    
    private boolean update;
    
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
       Calendar now = Calendar.getInstance();
       int month = (now.get(Calendar.MONTH) + 1);
       String monthRight;
       
       if(month < 10)
           monthRight = "0" + month;
       else
           monthRight = String.valueOf(month);
       
       String date = now.get(Calendar.YEAR) + "/" + monthRight + "/" +now.get(Calendar.DAY_OF_MONTH);
       GregorianCalendar calend = new GregorianCalendar();
       int hour = calend.get(Calendar.HOUR_OF_DAY);
       int minute = calend.get(Calendar.MINUTE);
       String hourStr;
       if(minute < 10)
           hourStr = hour + ":0" + minute;
       else
           hourStr = hour + ":" + minute;
       
       DatabaseConnection dbConnection = new DatabaseConnection("UsersDB.db");
       dbConnection.insertEntry(date, mainText.getText(), hourStr, DiarioLayoutController.LoggedInUser(), update);
       dbConnection.closeConnection();
       InterfaceUserController.diaryStage.hide();
       InterfaceUserController.thisStage.show();
   }
   
   
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        update = false;
        
        Calendar now = Calendar.getInstance();

        int ano = now.get(Calendar.YEAR);
        int dia = now.get(Calendar.DAY_OF_MONTH);
        int mes = now.get(Calendar.MONTH) + 1;
        
        String monthRight;
       
       if(mes < 10)
           monthRight = "0" + mes;
       else
           monthRight = String.valueOf(mes);
        
        String strData = ano + "/" + monthRight + "/" + dia;
        
         data.setText(strData);
         
        int horas;
        int minutos;
        
        GregorianCalendar calend = new GregorianCalendar();
        horas= calend.get(Calendar.HOUR_OF_DAY);
        minutos = calend.get(Calendar.MINUTE);
        
        hora.setText("" + horas + ":" + minutos);
        
        mainText.setWrapText(true);
        
        DatabaseConnection dbConnection = new DatabaseConnection("UsersDB.db");
        
        String s = dbConnection.hasEntrance(strData, DiarioLayoutController.LoggedInUser());
        
        if(s != null){
            update = true;
            mainText.setText(s);
        }
        
        dbConnection.closeConnection();
        
        
    }
   
    
}
