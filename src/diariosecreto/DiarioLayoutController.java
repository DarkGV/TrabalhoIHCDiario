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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author DiogoSilva
 */
public class DiarioLayoutController implements Initializable {
    
    
    
    private static String loggedInUser;
    
    @FXML
    private BorderPane LayoutChange;
    
    /* REGISTER LAYOUT */
    
    @FXML
    private ComboBox sexoComboBox;
    
    @FXML
    private DatePicker datePick;
    
    @FXML
    private PasswordField passwdInsert;
    
    @FXML
    private PasswordField paswdVerification;
    
    @FXML
    private TextField nameInsert;
    
    @FXML
    private TextField userNameInsert;
    
    @FXML
    private Button searchImage;
    
    @FXML
    private TextField imagePathInsert;
    
    /* END */
    
    /*Login Nav Bar */
    
    @FXML
    private Button logInbtn;
    
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
    
    /* END*/
    
    
    
    @FXML
    public void exitApp(){
        System.exit(0);
    }
    
    /* Button  Entrar */
    
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
    }
    
    /* Register to the Database */
    @FXML
    public void registerButton(){
        DatabaseConnection dbConn = new DatabaseConnection("UsersDB.db");
        if(passwdInsert.getText().equals(paswdVerification.getText()))
             dbConn.insertUser(userNameInsert.getText(), nameInsert.getText(), (String)sexoComboBox.getValue(), passwdInsert.getText(), imagePathInsert.getText(), 
                datePick.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) );
        
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
        
        File f = new File (getClass().getResource("nonLoggedIn.png").toExternalForm());
        
        
        imagePathInsert.setText(f.getAbsolutePath());

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
        
        searchImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Stage toChooseFile = new Stage();
                FileChooser fileChoose = new FileChooser();
                fileChoose.setTitle("Escolha a sua imagem...");
                fileChoose.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PNG", "*.png"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg")
                );
                File f = fileChoose.showOpenDialog(toChooseFile);
                imagePathInsert.setText(f.getAbsolutePath());
                imagePrev.setImage( new Image(f.toURI().toString()));
            }
        });
        
        LayoutChange.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if(e.getCode() == KeyCode.ENTER){
                    DatabaseConnection dbConn = new DatabaseConnection("UsersDB.db");
                    System.out.println("userName -> " + userName.getText() + "\nPassword -> " + passwdLabel.getText());
                    if(!dbConn.existsInDB(userName.getText(), passwdLabel.getText())) return;
                    dbConn.closeConnection();

                    loggedInUser = userName.getText();

                    try{
                        Parent loggedInPage = FXMLLoader.load(getClass().getResource("InterfaceUser.fxml"));
                        Scene loggedInScene = new Scene(loggedInPage, 1000, 618);
                        Stage newStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        newStage.hide();
                        newStage.setScene(loggedInScene);
                        newStage.show();
                    }catch(IOException z){
                        System.out.println(z);
                    }
                }
            }
        });
        
    }    
    
    public static String LoggedInUser(){
        return loggedInUser;
    }
    
}
