/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidev.services;

/**
 *
 * @author Eya
 */
public class IService {
    
}
*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.edu4u.gui;

import tn.esprit.edu4u.service.Servicecommande;
import tn.esprit.pidev.entities.document;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import tn.esprit.edu4u.service.Servicedocument;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.util.Duration;
import tn.esprit.pidev.entities.commande;

/**
 * FXML Controller class
 *
 * @author noura
 */
public class AjoutercommController implements Initializable {

  @FXML
    private Button btnajouter;

    @FXML
    private Button btnretour;

 @FXML
    private TextField txtediteur;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txttype;
 Servicecommande se = new Servicecommande();

    
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TO
        /* 
                txtRec.setWrapText(true);

             try {
            list= FXCollections.observableList(str.selectAll());
            for(int i=0;i<list.size();i++){
            combotype.getItems().add(list.get(i).getType());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }    
        @FXML
    

    void Ajoutercommande(ActionEvent event) throws SQLException, IOException {
    
        if (txtnom.getText().isEmpty() 
                || txtediteur.getText().isEmpty()
                || txttype.getText().isEmpty() ) {
            
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
            
        }else{

                commande c = new commande(txtnom.getText(),txtediteur.getText(),txttype.getText());
                System.out.println(c);
                try {
                    se.createOne(c);
 
                    
                    Parent root = FXMLLoader.load(getClass().getResource("listcomm.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Ajouter une commande");
                    stage.setScene(scene);
                    stage.show();
         
                    
                    
                } catch (SQLException ex) {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setTitle("Erreur");
                    al.setHeaderText("Erreur Interne");
                    al.setContentText(ex.getMessage());
                    al.show();
                }
                

            
        
        
        }
        }
    @FXML
    void Retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("listcomm.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Ajouter une commande");
                    stage.setScene(scene);
                    stage.show();
  
    }
}
