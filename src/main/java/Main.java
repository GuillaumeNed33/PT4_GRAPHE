
import Model.Forme_Sommet;
import View.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        /** GESTION DES NODES **/
        VBox root = new VBox();
        TabPane menu = FXMLLoader.load(getClass().getResource("/fxml/MainApplication.fxml"));
        Sommet s = new Sommet("id", Forme_Sommet.Cercle, 100,100);
        Sommet s1 = new Sommet("moche", Forme_Sommet.Rectangle, 300,620);
        Arete a = new Arete(s,s1);
        Pane test = new Pane();
        test.getChildren().addAll(s,a,s1);
        root.getChildren().addAll(menu, test);


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
