package com.produits;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.util.Callback;
import javax.swing.Action;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

public class ProduitsListWindow {
    ProduitsListHandler handler = new ProduitsListHandler(this);
    List<String> list = new ArrayList<String>();
    Stage window = new Stage();
    VBox root = new VBox();
    Scene scene = new Scene(root);
    Label titleLabel = new Label("Liste des produits");
    HBox totalHBox = new HBox();
    TextField textField = new TextField();
    Button delete = new Button("delete");
    ChoiceBox<String> choiceBox = new ChoiceBox<>();
    HBox searchBox = new HBox();
    ProduitDaolmpl dao = new ProduitDaolmpl();
    Label totaLabel = new Label("Total");
    Label totLabelVaLue = new Label("0.0");
    TableColumn<Produit, Long> idColumn = new TableColumn<>("Id");
    TableColumn<Produit, String> designationColumn = new TableColumn<>("Designation");
    TableColumn<Produit, String> categorieColumn = new TableColumn<>("Catégorie");
    TableColumn<Produit, Double> prixColumn = new TableColumn<>("Prix");
    TableColumn<Produit, Integer> qteColumn = new TableColumn<>("Quantité");
    TableColumn<Produit, Double> totalColumn = new TableColumn<>("Total");
    TableColumn<Produit, LocalDate> dateColumn = new TableColumn<>("Date");
    TableColumn<Produit, Produit> actionColumn = new TableColumn<>("Sup");
    TableView<Produit> produitsTableView = new TableView<>();
    ObservableList<Produit> produitObservableList = FXCollections.observableArrayList();
    

    private void addColomnsToTableView(){
        produitsTableView.getColumns().addAll(idColumn,designationColumn,categorieColumn,prixColumn,qteColumn,totalColumn,dateColumn,actionColumn);
        produitsTableView.setItems(produitObservableList);
    }

    private void addStylesToNodes(){
        scene.getStylesheets().add("Style/styles.css");
        titleLabel.getStyleClass().add("titleLabel");
        totaLabel.getStyleClass().add("totalLabel");
        totLabelVaLue.getStyleClass().add("totalLabel");
        totalHBox.getStyleClass().add("totalHbox");
        totalHBox.setAlignment(Pos.CENTER);
        searchBox.setAlignment(Pos.CENTER);
        produitsTableView.getStyleClass().add("table-row-cell");
        produitsTableView.setMinWidth(500);
        titleLabel.setMinWidth(window.getWidth());
        totalHBox.setSpacing(20);
        textField.getStyleClass().add("textField");
        textField.setPromptText("Chercher designation");
        delete.getStyleClass().add("buttondelete");
        searchBox.setSpacing(20);
        root.setSpacing(10);
        
    }

   
    private void updateColumns(){
        produitsTableView.setEditable(true);
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        idColumn.setPrefWidth(100);
        designationColumn.setCellValueFactory(new PropertyValueFactory("designation"));
        designationColumn.setPrefWidth(150);
        designationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        designationColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit,String>>(){
            @Override
            public void handle(javafx.scene.control.TableColumn.CellEditEvent<Produit, String> t) {(
                (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDesignation(t.getNewValue());
                dao.updateDesignation(t.getNewValue(),t.getRowValue().getId());
                handler.updateProduits();
                produitsTableView.refresh();
            }
        });
        categorieColumn.setCellValueFactory(new PropertyValueFactory("categorie"));
        categorieColumn.setPrefWidth(150);
        categorieColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        categorieColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit,String>>(){
            @Override
            public void handle(javafx.scene.control.TableColumn.CellEditEvent<Produit, String> t) {(
                (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCategorie(t.getNewValue());
                dao.updateCategorie(t.getNewValue(),t.getRowValue().getId());
                handler.updateProduits();
                produitsTableView.refresh();
            }
        });
        prixColumn.setCellValueFactory(new PropertyValueFactory("prix"));
        prixColumn.setPrefWidth(150);
        prixColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        prixColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit,Double>>(){
            @Override
            public void handle(javafx.scene.control.TableColumn.CellEditEvent<Produit, Double> t) {(
                (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPrix(t.getNewValue());
                dao.updatePrix(t.getNewValue(), t.getRowValue().getId());
                handler.updateProduits();
                produitsTableView.refresh();
            }
        });
        qteColumn.setCellValueFactory(new PropertyValueFactory("qte"));
        qteColumn.setPrefWidth(150);
        qteColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        qteColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit,Integer>>(){
            @Override
            public void handle(javafx.scene.control.TableColumn.CellEditEvent<Produit, Integer> t) {(
                (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQte(t.getNewValue());
                System.out.println(t.getNewValue() + " " + t.getRowValue() + t.getTablePosition().getRow());
                System.out.println(t.getRowValue().getId());
                dao.updateQte(t.getNewValue(),t.getRowValue().getId());
                handler.updateProduits();
                produitsTableView.refresh();
            }
        });
        totalColumn.setCellValueFactory(new PropertyValueFactory("total"));
        totalColumn.setPrefWidth(150);

        dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
        dateColumn.setPrefWidth(150);
        
        actionColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        actionColumn.setCellFactory(param->new TableCell<Produit,Produit>(){
            private final Button delete = new Button("Delete");

            @Override
            protected void updateItem(Produit produit, boolean empty) {
                super.updateItem(produit, empty);

                if (produit == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(delete);
                delete.setOnAction(event -> {
                    handler.delete(produit);
                    });
            }
        });
        actionColumn.setPrefWidth(70);
        
        textField.textProperty().addListener((observable)->{
            handler.searchProduit(textField.getText());
        });
    }
    

    private void initWindow(){
        window.setWidth(1100);
        window.setHeight(600);
        window.setScene(scene);
        window.setTitle("Liste des produits");
        window.getIcons().add( new Image("icon.PNG"));
    }
    private void addEvents(){
		delete.setOnAction(actionEvent -> {
            
        		});
	}
    
    private void addNodesToPane(){
        totalHBox.getChildren().addAll(totaLabel, totLabelVaLue);
        searchBox.getChildren().addAll(textField);
        root.getChildren().addAll(titleLabel,searchBox,produitsTableView,totalHBox);
    }
    public ProduitsListWindow(){
        initWindow();
        addStylesToNodes();
        updateColumns();
        addColomnsToTableView();
        handler.updateProduitsListWindows();
        addNodesToPane();
        addEvents();
        window.show();

    }
}
