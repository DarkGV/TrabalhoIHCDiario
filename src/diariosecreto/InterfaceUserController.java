/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diariosecreto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class InterfaceUserController implements Initializable {
    
    @FXML
    private AnchorPane optionSideBar;
    
    @FXML
    private AnchorPane mainAnchor;
    
    @FXML
    public void exitApp(){
        System.exit(0);
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

    }    
    
}
