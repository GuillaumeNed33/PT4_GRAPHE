
import Model.Forme_Sommet;
import View.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        /** GESTION DES NODES **/
        Graphe g = new Graphe();
        VBox root = new VBox();
        TabPane menu = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        root.getChildren().addAll(menu,g.getScrollPane());

        /** PARAMETRAGE DE LA FENETRE **/
        primaryStage.setTitle("Gestionnaire de graphe");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }
}
