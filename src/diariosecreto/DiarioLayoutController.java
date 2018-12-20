/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diariosecreto;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author DiogoSilva
 */
public class DiarioLayoutController implements Initializable {
    
    @FXML
    private  Circle userPhoto;
    
    @FXML
    private ImageView openCloseSidebar;
    
    @FXML
    private AnchorPane optionSideBar;
    
    @FXML
    private AnchorPane mainAnchor;
    
    @FXML
    public void exitApp(){
        System.exit(0);
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
        
        openCloseSidebar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                openCloseSidebar();
            }
            
        });
        
    }    
    
}
