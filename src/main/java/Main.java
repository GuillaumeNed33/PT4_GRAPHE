
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainApplication.fxml"));
        primaryStage.setTitle("Gestionnaire de graphe");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        /*** TEST GRAPHE ***/
/*        Graphe g = new Graphe("ressources/sample.graphml");
        String m_name = g.m_name;
        Size m_size=g.m_size;
        ArrayList<Sommet> m_sommets=g.m_sommets;
        ArrayList<Arete> m_aretes=g.m_aretes;
        HashMap<Sommet, ArrayList<Arete> > m_incidente=g.m_incidentes;
        HashMap<Arete, Pair<Sommet,Sommet>> m_extremites=g.m_extremites;    // conteneur liant pour chaque arete sa paire de sommets
        ArrayList<KeyStyleGRAPHML> m_keyGML=g.m_keyGML;
        AlgorithmeRepresentation algoRep = g.algoRep;
        String test = new String("rtest");
        */
        launch(args);

    }
}
