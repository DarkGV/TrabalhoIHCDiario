/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diariosecreto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class InterfaceUserController implements Initializable {
    
    protected static Stage thisStage;
    protected static Stage diaryStage;
    protected static String entryValue;
    protected static String day;
    
    /*
    *   info[0] -> nome
    *   info[1] -> DataNsc
    *   info[2] -> Sexo
    *   info[3] -> Password
    *   info[4] -> LoginUser
    *   info[5] -> Fotografia
    */
    private String[] info;
    
    @FXML
    private AnchorPane optionSideBar;
    
    @FXML
    private AnchorPane mainAnchor;
    
    @FXML
    private  Circle userPhoto;
    
    @FXML
    private Label nomeuser;
    
    @FXML
    private Label nome;
    
    @FXML
    private Label dataAct;
    
    @FXML
    private Label horaAct;
    
    @FXML
    private DatePicker memorias;
    
    class UpdateHour extends Thread{
        private Label horaAct;
        private Label dataAct;
        
        UpdateHour(Label horaAct, Label dataAct){
            this.dataAct = dataAct;
            this.horaAct = horaAct;
        }
        
        @Override
        public void run(){
            while(true){
                
            }
            
        }
    }
    
    private UpdateHour updateHour;
    
    @FXML
    public void exitApp(Event event){
        try{
            Parent loggedInPage = FXMLLoader.load(getClass().getResource("DiarioLayout.fxml"));
            Scene loggedInScene = new Scene(loggedInPage, 1000, 618);
            Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newStage.hide();
            newStage.setScene(loggedInScene);
            newStage.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    @FXML
    public void onExitout(MouseEvent event){
        //#c65b5b
        ((Button)event.getSource()).setStyle("-fx-background-color:   #cc4747;");
    }
    
    @FXML
    public void onExitIn(MouseEvent event){
        //#c65b5b
        ((Button)event.getSource()).setStyle("-fx-background-color:  #bf2d2d;");
    }
    
    @FXML
    public void onButtonOut(MouseEvent event){
        ((Button)event.getSource()).setStyle("-fx-background-color:  #ff9393;");
    }
    
    @FXML
    public void onButtonIn(MouseEvent event){
        ((Button)event.getSource()).setStyle("-fx-background-color: rgb(255, 104, 104);");
    }
    
    @FXML
    public void diarioBtn(ActionEvent event) {
        
        try{
            Parent loggedInPage = FXMLLoader.load(getClass().getResource("LoggedIn.fxml"));
            Scene loggedInScene = new Scene(loggedInPage, 1000, 618);
            Stage loggedInStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisStage = loggedInStage;
            Stage newStage = new Stage();
            diaryStage = newStage;
            loggedInStage.hide();
            newStage.getIcons().add( new Image( getClass().getResourceAsStream("diaryIcon.png") ) );
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(loggedInScene);
            newStage.show();
            
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
        
    }
    
    private String dayToString(String day){
        String[] dayArray = day.split( Pattern.quote("/") );
        
        switch(dayArray[1]){
            case "01":
                return dayArray[2] + " Janeiro " + dayArray[0];
            case "02":
                return dayArray[2] + " Fevereiro " + dayArray[0];
            case "03":
                return dayArray[2] + " Março " + dayArray[0];
            case "04":
                return dayArray[2] + " Abril " + dayArray[0];
            case "05":
                return dayArray[2] + " Maio " + dayArray[0];
            case "06":
                return dayArray[2] + " Junho " + dayArray[0];
            case "07":
                return dayArray[2] + " Julho " + dayArray[0];
            case "08":
                return dayArray[2] + " Agosto " + dayArray[0];
            case "09":
                return dayArray[2] + " Setembro " + dayArray[0];
            case "10":
                return dayArray[2] + " Outubro " + dayArray[0];
            case "11":
                return dayArray[2] + " Novembro " + dayArray[0];
            case "12":
                return dayArray[2] + " Dezembro " + dayArray[0];
            
        }
        
        return null;
    }
    
    public void memoriasBtn(ActionEvent event) {
    
       entryValue = null;
       String dateToSearch = memorias.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    
        
       DatabaseConnection dbConn = new DatabaseConnection("UsersDB.db");
       info = dbConn.getInfo(DiarioLayoutController.LoggedInUser());
        
       entryValue = dbConn.hasEntrance(dateToSearch, DiarioLayoutController.LoggedInUser());
       dbConn.closeConnection();
       
       day = dayToString(dateToSearch);
       
       if(entryValue != null){
           try{
            Parent loggedInPage = FXMLLoader.load(getClass().getResource("ShowMemory.fxml"));
            Scene loggedInScene = new Scene(loggedInPage, 1000, 618);
            Stage loggedInStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisStage = loggedInStage;
            Stage newStage = new Stage();
            diaryStage = newStage;
            loggedInStage.hide();
            newStage.getIcons().add( new Image( getClass().getResourceAsStream("diaryIcon.png") ) );
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(loggedInScene);
            newStage.show();
            
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
       }
       
       
   } 
    
    public void openCloseSidebar(){
        TranslateTransition setSideBar = new TranslateTransition(new Duration(350), optionSideBar);        
        TranslateTransition setLayout = new TranslateTransition(new Duration(350), mainAnchor);
        //closeBar.setToX(-(optionSideBar.getWidth()));
        
        if(optionSideBar.getTranslateX() != 0){ 
            setSideBar.setToX(0);
            setLayout.setToX(0);
            setSideBar.play();
            setLayout.play();
            
        }
        else{
            setSideBar.setToX(-(optionSideBar.getWidth()));
            setLayout.setToX(-(optionSideBar.getWidth()));
            mainAnchor.setPrefWidth(618.0);
            System.out.println(mainAnchor.getPrefWidth());
            setSideBar.play();
            setLayout.play();
        }
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            DatabaseConnection dbConn = new DatabaseConnection("UsersDB.db");
            //System.out.println("INITIALIZE");
            info = dbConn.getInfo(DiarioLayoutController.LoggedInUser());
            dbConn.closeConnection();
            
            File f = new File (info[5]);
            if(f.exists()){
                Image userImg = new Image(f.toURI().toString());
                userPhoto.setFill(new ImagePattern(userImg));
            }
            else{
                Image userImg = new Image(getClass().getResource("nonLoggedIn.png").toExternalForm());
                userPhoto.setFill(new ImagePattern(userImg));
            }
                
            
            
            nomeuser.setText("Bem-vindo,");
            nome.setText(info[0]);
            
            Calendar now = Calendar.getInstance();

                int ano = now.get(Calendar.YEAR);
                int dia = now.get(Calendar.DAY_OF_MONTH);
                int mes = now.get(Calendar.MONTH) + 1;

                dataAct.setText(ano + "/" + mes + "/" + dia);

                GregorianCalendar calend = new GregorianCalendar();

                int hora = calend.get(Calendar.HOUR_OF_DAY);
                int minuto = calend.get(Calendar.MINUTE);

                if(minuto < 10)
                    horaAct.setText("" + hora + ":0" + minuto);
                else
                    horaAct.setText("" +hora + ":" + minuto);
            
    }    
    
}
