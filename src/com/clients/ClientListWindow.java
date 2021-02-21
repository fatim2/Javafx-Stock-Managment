package com.clients;

import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientListWindow {
    ClientListHandler handler = new ClientListHandler(this);
    List<String> list = new ArrayList<String>();
    Stage window = new Stage();
    VBox root = new VBox();
    Scene scene = new Scene(root);
    Label titleLabel = new Label("Liste des clients");
    TextField textField = new TextField();
    Button delete = new Button("delete");
    HBox searchBox = new HBox();
    ClientDaolmpl dao = new ClientDaolmpl();
    ClientAddHandler addHandler = new ClientAddHandler();
    TableColumn<Client, Long> idColumn = new TableColumn<>("Id");
    TableColumn<Client, String> nomColumn = new TableColumn<>("Nom");
    TableColumn<Client, String> prenomColumn = new TableColumn<>("Prenom");
    TableColumn<Client, String> telephoneColumn = new TableColumn<>("Téléphone");
    TableColumn<Client, String> emailColum = new TableColumn<>("email");
    TableColumn<Client, String> adressColumn = new TableColumn<>("Adresse");
    TableColumn<Client, Client> actionColumn = new TableColumn<>("Sup");
    TableView<Client> clientsTableView = new TableView<>();
    ObservableList<Client> clientObservableList = FXCollections.observableArrayList();
    
    private void addColomnsToTableView(){
        clientsTableView.getColumns().addAll(idColumn,nomColumn,prenomColumn,telephoneColumn,emailColum,adressColumn,actionColumn);
        clientsTableView.setItems(clientObservableList);
    }

    private void addStylesToNodes(){
        scene.getStylesheets().add("Style/styles.css");
        titleLabel.getStyleClass().add("titleLabel");
        searchBox.setAlignment(Pos.CENTER);
        clientsTableView.getStyleClass().add("table-row-cell");
        clientsTableView.setMinWidth(500);
        titleLabel.setMinWidth(window.getWidth());
        textField.getStyleClass().add("textField");
        textField.setPromptText("Chercher nom client");
        delete.getStyleClass().add("buttondelete");
        searchBox.setSpacing(20);
        root.setSpacing(10);
    }

    private void updateColumns(){
        clientsTableView.setEditable(true);
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        idColumn.setPrefWidth(100);

        nomColumn.setCellValueFactory(new PropertyValueFactory("nom"));
        nomColumn.setPrefWidth(150);
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client,String>>(){
            @Override
            public void handle(javafx.scene.control.TableColumn.CellEditEvent<Client, String> t) {(
                (Client) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNom(t.getNewValue());
                dao.updateNom(t.getNewValue(),t.getRowValue().getId());
                handler.updateClient();
                clientsTableView.refresh();
            }
        });
        prenomColumn.setCellValueFactory(new PropertyValueFactory("prenom"));
        prenomColumn.setPrefWidth(150);
        prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client,String>>(){
            @Override
            public void handle(javafx.scene.control.TableColumn.CellEditEvent<Client, String> t) {(
                (Client) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPrenom(t.getNewValue());
                dao.updatePrenom(t.getNewValue(),t.getRowValue().getId());
                handler.updateClient();
                clientsTableView.refresh();
            }
        });
        telephoneColumn.setCellValueFactory(new PropertyValueFactory("telephone"));
        telephoneColumn.setPrefWidth(150);
        telephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telephoneColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client,String>>(){
            @Override
            public void handle(javafx.scene.control.TableColumn.CellEditEvent<Client, String> t) {(
                (Client) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTelephone(t.getNewValue());
                if(addHandler.validateNumber(t.getNewValue())){
                    dao.updateTelephone(t.getNewValue(),t.getRowValue().getId());
                    handler.updateClient(); clientsTableView.refresh();
                }                
            }
        });
        emailColum.setCellValueFactory(new PropertyValueFactory("email"));
        emailColum.setPrefWidth(150);
        emailColum.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client,String>>(){
            @Override
            public void handle(javafx.scene.control.TableColumn.CellEditEvent<Client, String> t) {(
                (Client) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
                if(addHandler.validateEmail(t.getNewValue())){
                    dao.updateEmail(t.getNewValue(),t.getRowValue().getId());
                    handler.updateClient();
                    clientsTableView.refresh();
                }
                
                
            }
        });

        adressColumn.setCellValueFactory(new PropertyValueFactory("adresse"));
        adressColumn.setPrefWidth(150);
        adressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        adressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client,String>>(){
            @Override
            public void handle(javafx.scene.control.TableColumn.CellEditEvent<Client, String> t) {(
                (Client) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAdresse(t.getNewValue());
                dao.updateAdresse(t.getNewValue(),t.getRowValue().getId());
                handler.updateClient();
                clientsTableView.refresh();
            }
        });
        actionColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        actionColumn.setCellFactory(param->new TableCell<Client,Client>(){
            private final Button delete = new Button("Delete");

            @Override
            protected void updateItem(Client client, boolean empty) {
                super.updateItem(client, empty);

                if (client == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(delete);
                delete.setOnAction(event -> {
                    handler.delete(client);
                    });
            }
        });
        actionColumn.setPrefWidth(70);
        
        textField.textProperty().addListener((observable)->{
            handler.searchClient(textField.getText());
        });
    }
    

    private void initWindow(){
        window.setWidth(950);
        window.setScene(scene);
        window.setTitle("Liste des clients");
        window.getIcons().add( new Image("icon.PNG"));

    }
    private void addNodesToPane(){
        searchBox.getChildren().addAll(textField);
        root.getChildren().addAll(titleLabel,searchBox,clientsTableView);
    }
    public ClientListWindow(){
        initWindow();
        addStylesToNodes();
        updateColumns();
        addColomnsToTableView();
        handler.updateClientListWindows();
        addNodesToPane();
        window.show();

    }
}
