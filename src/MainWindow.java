import com.produits.FormProduitWindow;
import com.produits.ProduitsListWindow;
import com.ventes.AddVenteWindow;
import com.ventes.VenteListWindow;
import com.clients.ClientListWindow;
import com.clients.FormClientsWindow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow extends Application{
    private BorderPane root = new BorderPane();
    private Scene scene = new Scene(root);

    MenuItem nvProduitMenuItem = new MenuItem("Nouveau");
    MenuItem listProduitMenuItem = new MenuItem("Liste");

    MenuItem nvClientsMenuItem = new MenuItem("Nouveau");
    MenuItem listClientsMenuItem = new MenuItem("Liste");

    MenuItem nvVentesMenuItem = new MenuItem("Nouveau");

    MenuItem nvPaimentsMenuItem = new MenuItem("Nouveau");
    MenuItem listPaimentsMenuItem = new MenuItem("Liste");

    MenuItem nvInventaireMenuItem = new MenuItem("Nouveau");
    MenuItem listInventaireMenuItem = new MenuItem("Liste");

    MenuItem helpMenuItem = new MenuItem("?");

    private void creatMenu(){
        MenuBar menuBar = new MenuBar();
        Menu produitsMenu = new Menu("produits");
        Menu clientsMenu = new Menu("Clients");
        Menu ventesMenu = new Menu("Ventes");
        Menu paimentsMenu = new Menu("Paiments");
        Menu InventaireMenu = new Menu("Inventaire");
        Menu helpMenu = new Menu("Aide");

        produitsMenu.getItems().addAll(nvProduitMenuItem,listProduitMenuItem);
        clientsMenu.getItems().addAll(nvClientsMenuItem,listClientsMenuItem);
        ventesMenu.getItems().addAll(nvVentesMenuItem);
        paimentsMenu.getItems().addAll(nvPaimentsMenuItem,listPaimentsMenuItem);
        InventaireMenu.getItems().addAll(nvInventaireMenuItem,listInventaireMenuItem);
        helpMenu.getItems().addAll(helpMenuItem);

        menuBar.getMenus().addAll(produitsMenu,clientsMenu,paimentsMenu,ventesMenu,InventaireMenu,helpMenu);
        root.setTop(menuBar);      
    }

    private void addStylesToNodes(){
        scene.getStylesheets().add("Style/styles.css");
        root.getStyleClass().add("mainWindow");
    }

    private void addEvents(){

        nvProduitMenuItem.setOnAction(event->{
            new FormProduitWindow();
        });
        listProduitMenuItem.setOnAction(event->{
            new ProduitsListWindow();
        });

        nvClientsMenuItem.setOnAction(event->{
            new FormClientsWindow();
        });
        listClientsMenuItem.setOnAction(event->{
            new ClientListWindow();
        });
        nvVentesMenuItem.setOnAction(event->{
            new AddVenteWindow();
        });
        

    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage window) throws Exception {
        creatMenu();
        addEvents();
        addStylesToNodes();
        window.setScene(scene);
        window.setWidth(1100);
        window.setHeight(700);
        window.setTitle("Gestion de magasin");
        window.getIcons().add( new Image("icon.PNG"));
        window.show();
    }
}
