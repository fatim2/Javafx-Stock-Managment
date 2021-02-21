package com.produits;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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


public class FormProduitWindow {
    ProduitAddHandler handler = new ProduitAddHandler(this);
    FormProduitWindow formWindow;
    GridPane grid = new GridPane();
    VBox root = new VBox();
    HBox buttonsBox = new HBox();
    Scene scene = new Scene(grid, 300, 275);
    Stage window = new Stage();
    VBox fieldBox = new VBox();
    HBox field = new HBox();
    Label titleLabel = new Label("Ajouter un nouveau produit");
    Label produitDesignationLabel = new Label("Designation:");
    TextField produitDesignationTextField = new TextField();
    
    Label produitCategorieLabel = new Label("Catégorie:");
    TextField produitCategorieTextField = new TextField();

    Label produitPrixLabel = new Label("Prix:");
    TextField produitPrixTextField = new TextField();

    Label produitQteLabel = new Label("Quantité:");
    TextField produitQteTextField = new TextField();

    Label produitDateLabel = new Label("Date:");
    DatePicker produitPickDatePicker = new DatePicker();
    Button produitAddButton = new Button("Ajouter");
    Button produitCancelButton = new Button("Annuler");

    private void initWindow(){
       /* window.setWidth(700);
        window.setHeight(500);*/
        window.setScene(scene);
        window.setTitle("Nouveau produit");
        window.getIcons().add(new Image("icon.PNG"));
        window.initModality(Modality.APPLICATION_MODAL);
    }

    
    private void addNodesToWindow(){
        /*root.getChildren().addAll(titleLabel);
        root.getChildren().addAll(produitDesignationLabel, produitDesignationTextField);
        root.getChildren().addAll(produitCategorieLabel, produitCategorieTextField);
        root.getChildren().addAll(produitPrixLabel, produitPrixTextField);
        root.getChildren().addAll(produitDateLabel,produitPickDatePicker);
        root.getChildren().addAll(produitQteLabel, produitQteTextField);
        buttonsBox.getChildren().addAll(produitAddButton,produitCancelButton);
        root.getChildren().addAll(buttonsBox);*/
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(produitDesignationLabel, 0, 1);
        grid.add(produitDesignationTextField, 1, 1);
        grid.add(produitCategorieLabel, 0,2);
        grid.add(produitCategorieTextField,1, 2);
        grid.add(produitPrixLabel, 0,3);
        grid.add(produitPrixTextField, 1,3);
        grid.add(produitDateLabel, 0,4);
        grid.add(produitPickDatePicker, 1,4);
        grid.add(produitQteLabel, 0, 5);
        grid.add(produitQteTextField, 1, 5);
        buttonsBox.getChildren().addAll(produitAddButton, produitCancelButton);
        grid.add(buttonsBox, 1, 6);
       
    }

    private void addStylesToNodes(){
        scene.getStylesheets().add("Style/styles.css");
        //titleLabel.getStyleClass().add("titleLabel");
        titleLabel.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
        /*titleLabel.setMinWidth(window.getWidth());
        produitDesignationLabel.getStyleClass().add("labelForm");
        produitCategorieLabel.getStyleClass().add("labelForm");
        produitPickDatePicker.getStyleClass().add("labelForm");
        produitQteLabel.getStyleClass().add("labelForm");
        produitDateLabel.getStyleClass().add("labelForm");
        produitCategorieTextField.getStyleClass().add("textField");
        produitDesignationTextField.setMinWidth(10);
        root.setSpacing(15);*/
        buttonsBox.setSpacing(20);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

    }


    private void addEvents(){
        produitCancelButton.setOnAction(event->{
            window.close();
        });
        produitAddButton.setOnAction(event->{
            handler.addClick();
            produitDesignationTextField.clear();
            produitCategorieTextField.clear();
            produitQteTextField.clear();
            produitPrixTextField.clear(); 
        });
        window.setOnCloseRequest(event->{
            event.consume();
        });
        
    }

    public  FormProduitWindow() {
        initWindow();
        addNodesToWindow();
        addEvents();
        window.show();
        addStylesToNodes();
    }
}
