import Controller.FXMLController;
import Model.Forme_Sommet;
import View.Arete;
import View.Sommet;
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
        VBox root = new VBox();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
        TabPane menu = loader.load();
        Sommet s = new Sommet("id", Forme_Sommet.Cercle, 100,100);
        Sommet s1 = new Sommet("moche", Forme_Sommet.Rectangle, 300,620);
        Arete a = new Arete(s,s1);
        Pane test = new Pane();
        test.getChildren().addAll(s,a,s1);
        root.getChildren().addAll(menu, test);


        /** PARAMETRAGE DE LA FENETRE **/
        primaryStage.setTitle("Gestionnaire de graphe");
        primaryStage.setMaximized(true);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        FXMLController controller = (FXMLController)loader.getController();
        controller.setVbox(root);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
