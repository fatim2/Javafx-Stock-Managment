package com.ventes;

import com.paiements.Clients.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.clients.*;
import com.produits.*;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class AddVenteWindow {
    AddVenteHandler handler = new AddVenteHandler(this);
    List<String> list = new ArrayList<String>();
    ProduitDaolmpl pdao = new ProduitDaolmpl();
    ProduitsListHandler produitHandler ;
    Stage window = new Stage();
    VBox root = new VBox();
    VBox root1 = new VBox();
    Label titleBLLabel = new Label("Ajouter ventes");
    Label titleCommandeLabel = new Label("Liste des ventes ajoutés");
    HBox detail = new HBox();
     Scene scene = new Scene(detail);
    TextField textField = new TextField();
    Button delete = new Button("delete");
    ChoiceBox<String> choiceBox = new ChoiceBox<>();
    GridPane grid = new GridPane();
    HBox searchProduitBox = new HBox();
    HBox searchVenteBox = new HBox();
    VenteDaolmpl dao = new VenteDaolmpl();
    HBox buttonBox = new HBox();
    HBox items =new HBox();
    VBox vbox = new VBox();
    Label idClientLabel = new Label("id client:");
    TextField idClientTextField = new TextField();
    Label nomClientLabel = new Label("Nom:");
    TextField nomClientTextField = new TextField();
    Label prenomClientLabel = new Label("Prenom:");
    TextField prenomClientTextField = new TextField();

    Label designationLabel = new Label("Designation:");
    TextField designationTextField = new TextField();

    Label categorieLabel = new Label("Catégorie");
    TextField categorieTextField = new TextField();

    Label idLabel = new Label("id_produit:");
    TextField idTextField = new TextField();

    Label prixVenteLabel = new Label("Prix vente:");
    TextField prixVenteTextField = new TextField();
    Label venteQteLabel = new Label("Qte:");
    TextField venteQteTextField = new TextField();
    Label dateVenteLabel = new Label("Date:");
    DatePicker datePickerVente = new DatePicker();
    Button venteAddButton = new Button("Ajouter");
    Button payerButton = new Button("Payer");

    TableColumn<Produit, Long> idColumn = new TableColumn<>("Id");
    TableColumn<Produit, String> designationColumn = new TableColumn<>("Designation");
    TableColumn<Produit, String> categorieColumn = new TableColumn<>("Catégorie");
    TableColumn<Produit, Double> prixColumn = new TableColumn<>("Prix");
    TableColumn<Produit, Integer> qteColumn = new TableColumn<>("Quantité");
    TableView<Produit> ProduitsTableView = new TableView<>();
    ObservableList<Produit> ProduitObservableList = FXCollections.observableArrayList();

    TableColumn<Vente, String> idVenteColumn = new TableColumn<>("id");
    TableColumn<Vente, String> clientNomVenteColumn = new TableColumn<>("nom");
    TableColumn<Vente, String> clientPrenomVenteColumn = new TableColumn<>("prenom");
    TableColumn<Vente, String> designationProduitColumn = new TableColumn<>("designation");
    TableColumn<Vente, String> categoriePorduitColumn = new TableColumn<>("categorie");
    TableColumn<Vente, Double> prixVenteColumn = new TableColumn<>("prix");
    TableColumn<Vente, LocalDate> dateVenteColumn = new TableColumn<>("date");
    TableColumn<Vente, Integer> qteVenteColumn = new TableColumn<>("qte");
    TableColumn<Vente, Double> sousTotalColumn = new TableColumn<>("total");
    TableColumn<Vente,Vente> actionColumn = new TableColumn<>("Sup");
    TableColumn<Vente,Vente> payerColumn = new TableColumn<>("Payer");
    TableView<Vente> VentesTableView = new TableView<>();
    
    TableColumn<Client, Long> idClientColumn = new TableColumn<>("id");
    TableColumn<Client, String> nomClientColumn = new TableColumn<>("nom");
    TableColumn<Client, String> prenomClientColumn = new TableColumn<>("prenom");
    TableView<Client> clientTableView = new TableView<>();
    ObservableList<Client> clientObservableList = FXCollections.observableArrayList();
    ObservableList<Vente> venteProduitObservableList = FXCollections.observableArrayList();
    
    
    private void addColomnsToTableView(){
        ProduitsTableView.getColumns().addAll(idColumn,designationColumn,categorieColumn,qteColumn,prixColumn);
        ProduitsTableView.setItems(ProduitObservableList);

        VentesTableView.getColumns().addAll(idVenteColumn,clientNomVenteColumn,clientPrenomVenteColumn,designationProduitColumn,categoriePorduitColumn,prixVenteColumn,qteVenteColumn,sousTotalColumn,dateVenteColumn,payerColumn,actionColumn);
        VentesTableView.setItems(venteProduitObservableList);

        clientTableView.getColumns().addAll(idClientColumn,nomClientColumn,prenomClientColumn);
        clientTableView.setItems(clientObservableList);

    }

    private void initWindow(){
        
        window.setScene(scene);
        window.setTitle("Ajouter vente");
        window.getIcons().add( new Image("icon.PNG"));
    }
    private void addEvents(){
		ProduitsTableView.setRowFactory(tv -> {
            TableRow<Produit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 2) {
                    long clickedRowId = row.getItem().getId();
                    idTextField.setText(String.valueOf(clickedRowId));
                    String clickedRowDesignation = row.getItem().getDesignation();
                    designationTextField.setText(clickedRowDesignation);
                    String clickedRowCategorie = row.getItem().getCategorie();
                    categorieTextField.setText(clickedRowCategorie);
                }
            });
            return row ;
        });

        clientTableView.setRowFactory(tv -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 2) {
                    long clickedRowClientId = row.getItem().getId();
                    idClientTextField.setText(String.valueOf(clickedRowClientId));
                    String clickedRowNom = row.getItem().getNom();
                    nomClientTextField.setText(clickedRowNom);
                    String clickedRowPrenom = row.getItem().getPrenom();
                    prenomClientTextField.setText(clickedRowPrenom);
                }
            });
            return row ;
        });


        venteAddButton.setOnAction(actionEvent->{
            handler.addToLigneCommande();
            VentesTableView.getItems().clear();
            handler.updateVenteWindows();
            venteQteTextField.clear();
            designationTextField.clear();
            prixVenteTextField.clear();
            idTextField.clear(); 
            categorieTextField.clear();
            idClientTextField.clear();
            nomClientTextField.clear();
            prenomClientTextField.clear();
            
        });
	}
    private void updateColumns(){
        VentesTableView.setEditable(true);
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        idColumn.setPrefWidth(50);
        designationColumn.setCellValueFactory(new PropertyValueFactory("designation"));
        designationColumn.setPrefWidth(90);
        categorieColumn.setCellValueFactory(new PropertyValueFactory("categorie"));
        categorieColumn.setPrefWidth(90);
        
        qteColumn.setCellValueFactory(new PropertyValueFactory("qte"));
        qteColumn.setPrefWidth(50);
        prixColumn.setCellValueFactory(new PropertyValueFactory("prix"));
        prixColumn.setPrefWidth(90);

        idClientColumn.setCellValueFactory(new PropertyValueFactory("id"));
        idClientColumn.setPrefWidth(50);
        nomClientColumn.setCellValueFactory(new PropertyValueFactory("nom"));
        nomClientColumn.setPrefWidth(100);
        prenomClientColumn.setCellValueFactory(new PropertyValueFactory("prenom"));
        prenomClientColumn.setPrefWidth(100);
       
        idVenteColumn.setCellValueFactory(new PropertyValueFactory("id"));
        idVenteColumn.setPrefWidth(50);

        clientNomVenteColumn.setCellValueFactory(new Callback<CellDataFeatures<Vente,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Vente, String> param) {
                return new SimpleStringProperty(param.getValue().getClient().getNom());
            }
        });
        clientNomVenteColumn.setPrefWidth(100);

        clientPrenomVenteColumn.setCellValueFactory(new Callback<CellDataFeatures<Vente,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Vente, String> param) {
                return new SimpleStringProperty(param.getValue().getClient().getPrenom());
            }
        });
        clientPrenomVenteColumn.setPrefWidth(100);

        designationProduitColumn.setCellValueFactory(new Callback<CellDataFeatures<Vente,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Vente, String> param) {
                return new SimpleStringProperty(param.getValue().getProduit().getDesignation());
            }
        });
        designationProduitColumn.setPrefWidth(100);
        
        categoriePorduitColumn.setCellValueFactory(new Callback<CellDataFeatures<Vente,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Vente, String> param) {
                return new SimpleStringProperty(param.getValue().getProduit().getCategorie());
            }
        });
        categoriePorduitColumn.setPrefWidth(150);
        
        prixVenteColumn.setCellValueFactory(new Callback<CellDataFeatures<Vente,Double>,ObservableValue<Double>>(){

            @Override
            public ObservableValue<Double> call(CellDataFeatures<Vente, Double> param) {
                return new SimpleDoubleProperty(param.getValue().getPrixVente()).asObject();
            }
        });
        prixVenteColumn.setPrefWidth(90);
        prixVenteColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        prixVenteColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vente,Double>>(){
            @Override
            public void handle(javafx.scene.control.TableColumn.CellEditEvent<Vente, Double> t) {(
                (Vente) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPrixVente(t.getNewValue());
                dao.updatePrixVente(t.getNewValue(), t.getRowValue().getId());
                handler.updateVenteWindows();
                VentesTableView.refresh();
            }
        });
        qteVenteColumn.setCellValueFactory(new Callback<CellDataFeatures<Vente,Integer>,ObservableValue<Integer>>(){

            @Override
            public ObservableValue<Integer> call(CellDataFeatures<Vente, Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getQtevendu()).asObject();
            }
        });
        qteVenteColumn.setPrefWidth(50);
        qteVenteColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        qteVenteColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vente,Integer>>(){
            @Override
            public void handle(javafx.scene.control.TableColumn.CellEditEvent<Vente, Integer> t) {(
                (Vente) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQtevendu(t.getNewValue());
                System.out.println(t.getNewValue() + " " + t.getRowValue() + t.getTablePosition().getRow());
                System.out.println(t.getRowValue().getId());
                dao.updateQteVendu(t.getNewValue(),t.getRowValue().getId());
                handler.updateVenteWindows();
                handler.updateProduitsVenteWindows();
                VentesTableView.refresh();
                
            }
        });
        
        sousTotalColumn.setCellValueFactory(new Callback<CellDataFeatures<Vente,Double>,ObservableValue<Double>>(){

            @Override
            public ObservableValue<Double> call(CellDataFeatures<Vente, Double> param) {
                return new SimpleDoubleProperty(param.getValue().getTotal()).asObject();
            }
        });

        dateVenteColumn.setCellValueFactory(new PropertyValueFactory("date"));
        dateVenteColumn.setPrefWidth(50);

        actionColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        actionColumn.setCellFactory(param->new TableCell<Vente,Vente>(){
            private final Button delete = new Button("Delete");

            @Override
            protected void updateItem(Vente vente, boolean empty) {
                super.updateItem(vente, empty);

                if (vente == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(delete);
                delete.setOnAction(event -> {
                    handler.delete(vente);
                    });
            }
        });
        actionColumn.setPrefWidth(70);
        
        payerColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        payerColumn.setCellFactory(param->new TableCell<Vente,Vente>(){
            private final Button payer = new Button("Payer");

            @Override
            protected void updateItem(Vente vente, boolean empty) {
                super.updateItem(vente, empty);

                if (vente == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(payer);
                payer.setOnAction(event -> {
                    new PaimentWindow();
                    handler.payerVente(vente);
                        
                    
                    });
            }
        });
        payerColumn.setPrefWidth(50);
        
    }


    private void addStylesToNodes(){
        scene.getStylesheets().add("Style/styles.css");
        titleBLLabel.getStyleClass().add("titleLabel");
        titleCommandeLabel.getStyleClass().add("titleLabel");
        titleBLLabel.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        root.setPadding(new Insets(10, 50, 50, 50));
        root.setSpacing(10);
    }
    
    private void addNodesToPane(){
        grid.add(idClientLabel,0,1);
        grid.add(idClientTextField,1,1);
        grid.add(nomClientLabel,0,2);
        grid.add(nomClientTextField,1,2);
        grid.add(prenomClientLabel,0,3);
        grid.add(prenomClientTextField,1,3);
        grid.add(idLabel,0,4);
        grid.add(idTextField,1,4);
        grid.add(designationLabel,0,5);
        grid.add(designationTextField,1,5);
        grid.add(categorieLabel,0,6);
        grid.add(categorieTextField,1,6);

        grid.add(venteQteLabel,0,7);
        grid.add(venteQteTextField,1,7);
        grid.add(prixVenteLabel,0,8);
        grid.add(prixVenteTextField,1,8);
        grid.add(dateVenteLabel, 0,9);
        grid.add(datePickerVente, 1,9);

        vbox.getChildren().addAll(titleBLLabel,grid);
        items.getChildren().addAll(vbox, clientTableView);
        buttonBox.getChildren().addAll(venteAddButton);
        grid.add(buttonBox, 1,10);
        searchProduitBox.getChildren().addAll(textField);
        root.getChildren().addAll(items,ProduitsTableView);
        root1.getChildren().addAll(titleCommandeLabel,VentesTableView);
        detail.getChildren().addAll(root,root1);
    }


    

    public AddVenteWindow(){
        initWindow();
        addStylesToNodes();
        updateColumns();
        addColomnsToTableView();
        handler.updateProduitsVenteWindows();
        handler.updateClientsVenteWindows();
        handler.updateVenteWindows();
        addNodesToPane();
        addEvents();
        window.show();

    }
}
