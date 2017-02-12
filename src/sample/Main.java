package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
<<<<<<< HEAD
import sample.Model.Sommet;

=======
import sample.Model.Arete;
import sample.Model.Sommet;

import java.util.ArrayList;
import java.util.Iterator;

>>>>>>> 659a1f51e926b9696f1b39347754ef2a26911c8d
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
<<<<<<< HEAD
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
=======
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
>>>>>>> 659a1f51e926b9696f1b39347754ef2a26911c8d
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
<<<<<<< HEAD
    }

    public static void addSommet(float x, float y) {
        Sommet s = new Sommet(true, x,y);
=======

    }

    public static void addSommet(float x, float y) {
        Sommet s = new Sommet("name", x,y);
>>>>>>> 659a1f51e926b9696f1b39347754ef2a26911c8d
    }
}
