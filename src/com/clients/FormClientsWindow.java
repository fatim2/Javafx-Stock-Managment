package com.clients;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class FormClientsWindow {
    ClientAddHandler handler = new ClientAddHandler(this);
    VBox root = new VBox();
    HBox buttonsBox = new HBox();
    GridPane grid = new GridPane();
    Scene scene = new Scene(grid, 300, 275);
    Stage window = new Stage();
    Label titleLabel = new Label("Ajouter un nouveau client");
    Label clientNomLabel = new Label("Nom:");
    TextField clientNomTextField = new TextField();

    Label clientPrenomLabel = new Label("Prenom:");
    TextField clientPrenomTextField = new TextField();

    Label clientTelephoneLabel = new Label("Téléphone:");
    TextField clientTelephoneTextField = new TextField();
    
    
    Label clientEmailLabel = new Label("Email:");
    TextField clientEmailTextField = new TextField();
    

    Label clientAdressLabel = new Label("Adresse:");
    TextField clientAdressTextField = new TextField();

    Button clientAddButton = new Button("Ajouter");
    Button clientCancelButton = new Button("Annuler");

    private void initWindow(){
        window.setScene(scene);
        window.setTitle("Nouveau client");
        window.getIcons().add(new Image("icon.PNG"));
        window.initModality(Modality.APPLICATION_MODAL);
    }

    private void addNodesToWindow(){
       /* root.getChildren().add(titleLabel);
        root.getChildren().addAll(clientNomLabel, clientNomTextField);
        root.getChildren().addAll(clientPrenomLabel, clientPrenomTextField);
        root.getChildren().addAll(clientTelephoneLabel,clientTelephoneTextField);
        root.getChildren().addAll(clientAdressLabel, clientAdressTextField);
        root.getChildren().addAll(clientEmailLabel, clientEmailTextField);
        buttonsBox.getChildren().addAll(clientAddButton,clientCancelButton);
        root.getChildren().addAll(buttonsBox);*/
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(clientNomLabel, 0, 1);
        grid.add(clientNomTextField, 1, 1);
        grid.add(clientPrenomLabel, 0,2);
        grid.add(clientPrenomTextField,1, 2);
        grid.add(clientTelephoneLabel, 0,3);
        grid.add(clientTelephoneTextField, 1,3);
        grid.add(clientEmailLabel, 0,4);
        grid.add(clientEmailTextField, 1,4);
        grid.add(clientAdressLabel, 0, 5);
        grid.add(clientAdressTextField, 1, 5);
        buttonsBox.getChildren().addAll(clientAddButton, clientCancelButton);
        grid.add(buttonsBox, 1, 6);
    }

    private void addStylesToNodes(){
        scene.getStylesheets().add("Style/styles.css");
        titleLabel.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
        /*titleLabel.getStyleClass().add("titleLabel");
        titleLabel.setMinWidth(window.getWidth());
        clientNomLabel.getStyleClass().add("labelForm");
        clientPrenomLabel.getStyleClass().add("labelForm");
        clientTelephoneLabel.getStyleClass().add("labelForm");
        clientAdressLabel.getStyleClass().add("labelForm");
        clientEmailLabel.getStyleClass().add("labelForm");
        clientNomTextField.setPrefWidth(0);
        root.setSpacing(15);*/
        buttonsBox.setSpacing(20);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    }


    private void addEvents(){
        clientCancelButton.setOnAction(event->{
            window.close();
        });
        clientAddButton.setOnAction(event->{
            handler.addClick();
            clientNomTextField.clear();
            clientPrenomTextField.clear();
            clientTelephoneTextField.clear();
            clientEmailTextField.clear(); 
            clientAdressTextField.clear();
        });
        window.setOnCloseRequest(event->{
            event.consume();
        });

    }

    public  FormClientsWindow() {
        initWindow();
        addNodesToWindow();
        addEvents();
        window.show();
        addStylesToNodes();
    }
}
