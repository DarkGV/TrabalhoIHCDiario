/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diariosecreto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author joaom
 */
public class ShowMemoryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea mainText;
    
    @FXML
    private Label nomeutilizador;
    
    
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
       InterfaceUserController.diaryStage.hide();
       InterfaceUserController.thisStage.show();
   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mainText.setEditable(false);
        mainText.setText(InterfaceUserController.entryValue);
        nomeutilizador.setText(InterfaceUserController.day);
   }
    
}
