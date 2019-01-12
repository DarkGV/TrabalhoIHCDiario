/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diariosecreto;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author DiogoSilva
 */
public class DiarioLayoutController implements Initializable {
    
    protected static Stage thisStage;
    protected static Stage diaryStage;
    
    private String loggedInUser;
    
    @FXML
    private ComboBox sexoComboBox;
    
    @FXML
    private DatePicker datePick;
    
    @FXML
    private  Circle userPhoto;
    
    @FXML
    private ImageView imagePrev;
    
    @FXML
    private ImageView openCloseSidebar;
    
    @FXML
    private AnchorPane optionSideBar;
    
    @FXML
    private AnchorPane mainAnchor;
    
    @FXML
    private TextField userName;
    
    @FXML
    private PasswordField passwdLabel;
    
    @FXML
    public void exitApp(){
        System.exit(0);
    }
    
    @FXML
    public void loggedInBtn(ActionEvent event) {
        
        DatabaseConnection dbConn = new DatabaseConnection("UsersDB.db");
        System.out.println("userName -> " + userName.getText() + "\nPassword -> " + passwdLabel.getText());
        if(!dbConn.existsInDB(userName.getText(), passwdLabel.getText())) return;
        dbConn.closeConnection();
        
        loggedInUser = userName.getText();
        
        try{
            Parent loggedInPage = FXMLLoader.load(getClass().getResource("InterfaceUser.fxml"));
            Scene loggedInScene = new Scene(loggedInPage, 1000, 618);
            Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newStage.hide();
            newStage.setScene(loggedInScene);
            newStage.show();
        }catch(IOException e){
            System.out.println(e);
        }
        
        
        
       /*try{
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
        }*/
    }
    
    @FXML
    public void registerButton(){
        System.out.println(datePick.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image userImg = new Image(getClass().getResource("nonLoggedIn.png").toExternalForm());
        userPhoto.setFill(new ImagePattern(userImg));
        
        imagePrev.setImage(userImg);
        
        sexoComboBox.getItems().addAll(
                "Masculino",
                "Feminino");
        
        openCloseSidebar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                openCloseSidebar();
            }
            
        });
        
    }    
    
}
