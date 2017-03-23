package Controller;

import Model.Graphe;
import Model.Sommet;
import View.Arete;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FXMLController extends VBox {
    protected Stage popUpWindow;
    protected View.Graphe grapheView;
    protected Model.Graphe grapheModel;
    private VBox vbox;

    public VBox getVbox() {
        return vbox;
    }

    public FXMLController() throws IOException {
        initPopup();
    }

    public void setGrapheView(View.Graphe g) {
        grapheView = g;
    }

    private void initPopup() {
        popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setResizable(false);
    }

    protected void refreshGrapheView() {
        grapheView = new View.Graphe();
        grapheView.chargerGraphe(grapheModel);
        getVbox().getChildren().remove(1);
        getVbox().getChildren().addAll(grapheView.getScrollPane());
        setPane(grapheView.getCanvas());
        grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
    }


    /**
     * Fonction ouvrant une fenetre (FileChooser) permettant l'importation d'un fichier dans le logiciel.
     */
    @FXML public void clickFichierImporter() {
        if (grapheView == null) {
            FileChooser fileChooser = createFileChooser("Importer");
            fileChooser.setInitialDirectory(new File("./ressources/"));
            File file = fileChooser.showOpenDialog(null);
            if (file != null){
                grapheView = new View.Graphe();
                grapheModel = new Graphe(file.getAbsolutePath(), 1200, 680);
                grapheView.chargerGraphe(grapheModel);
                getVbox().getChildren().addAll(grapheView.getScrollPane());
                setPane(grapheView.getCanvas());
            }
        }
        else {
            FileChooser fileChooser = createFileChooser("Importer");
            fileChooser.setInitialDirectory(new File("./ressources/"));
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                grapheModel = null;
                grapheModel = new Graphe(file.getAbsolutePath(), 1200,680);
                grapheView = null;
                grapheView = new View.Graphe();
                grapheView.chargerGraphe(grapheModel);
                getVbox().getChildren().remove(1);
                getVbox().getChildren().addAll(grapheView.getScrollPane());
                setPane(grapheView.getCanvas());
                grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
            }
        }
    }

    /**
     * Fonction ouvrant une fenetre (FileChooser) permettant l'exportation d'un fichier dans le logiciel.
     */
    @FXML public void clickFichierExporter() {
        if (grapheModel != null) {
            FileChooser fileChooser = createFileChooser("Exporter");
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                grapheModel.sauvegarderGraphe(file.getAbsolutePath());
            }
        }
        else {
            afficherFenetreAlerte("Vous ne pouvez pas exporter si vous n'avez pas importer un graphe avant.");
        }
    }

    private FileChooser createFileChooser(String exporter) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(exporter);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("GV", "*.gv"),
                new FileChooser.ExtensionFilter("GRAPHML", "*.graphml")
        );
        return fileChooser;
    }

    /**
     * Fonction ouvrant une fenetre (FileChooser) permettant l'enregistrement d'un fichier dans le logiciel.
     */
    @FXML public void clickFichierEnregistrer() {
        if (grapheModel != null) {
        grapheModel.sauvegarderGraphe(null);
        }
        else {
            afficherFenetreAlerte("Vous ne pouvez pas enregistrer si vous n'avez pas importer un graphe avant.");
        }
    }

    /**
     * Fonction permettant d'appliquer une distribution aléatoire des positions des sommets du graphe
     */
    @FXML public void clickRepresentationAleatoire() {
        if (grapheModel != null) {
        grapheModel.setAlgorithmeRepresentation('a',(int)grapheView.getScrollPane().getWidth(),(int)grapheView.getScrollPane().getHeight());
        refreshGrapheView();
        }
        else
            afficherFenetreAlerte("Vous ne pouvez pas appliquer la représentation aléatoire si vous n'avez pas importé un graphe avant.");

    }

    /**
     * Fonction permettant d'appliquer une distribution circulaire des positions des sommets du graphe
     */
    @FXML public void clickRepresentationCirculaire() {
        if (grapheModel != null) {
            grapheModel.setAlgorithmeRepresentation('c', (int) grapheView.getScrollPane().getWidth(), (int) grapheView.getScrollPane().getHeight());
            refreshGrapheView();
        }
        else {
            afficherFenetreAlerte("Vous ne pouvez pas appliquer la représentation circulaire si vous n'avez pas importer un graphe avant.");
        }
    }

    /**
     * Fonction permettant d'appliquer une distribution aléatoire des positions des sommets du graphe
     */
    @FXML
    public void clickRepresentationForces() {
        if (grapheModel != null && grapheModel.indiceFixe()) {
            grapheModel.setAlgorithmeRepresentation('f', (int) grapheView.getScrollPane().getWidth(), (int) grapheView.getScrollPane().getHeight());
            refreshGrapheView();
        }
        else {
            afficherFenetreAlerte("Vous ne pouvez pas appliquer la représentation modèle de forces si vous n'avez pas " +
                    "importer un graphe avant ou si vous n'avez pas indicé le graphe.");
        }
    }

    /**
     * Fonction permettant d'indicer aléatoirement tous les sommets du graphe
     */
    @FXML
    public void clickIndicageAleatoire(){
        if (grapheModel != null) {
            this.grapheModel.setIndiceAleatoire();
        }
        else {
            afficherFenetreAlerte("Vous ne pouvez pas appliquer un indice aléatoire si vous n'avez pas importé un graphe avant");
        }
    }

    @FXML
    public void clickIndicageDegré() {
        if (grapheModel != null) {
            this.grapheModel.setIndiceDegre();
        }
        else {
            afficherFenetreAlerte("Vous ne pouvez pas appliquer un degré si vous n'avez pas importé un graphe avant");
        }
    }

    @FXML
    public void clickAjouterSommet() throws IOException {
        new AjoutSommetController(grapheModel, grapheView);
    }

    @FXML
    public void clickAjouterArete() throws IOException {
        new AjoutAreteController(grapheModel, grapheView);
    }
    @FXML
    public void clickTailleGraphe(MouseEvent mouseEvent) throws IOException {
        new TailleGrapheController(grapheModel);
    }

    /**
     *
     * @param event
     */
    @FXML public void clickCouleurGraphe(MouseEvent event) throws IOException {
        new CouleurGrapheController(grapheModel, grapheView);
    }

    protected void fermerPopup(Button button) {
        popUpWindow.close();
    }

    @FXML public void clickSupprimer() {
        if (grapheModel != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SupprSommet.fxml"));
                popUpWindow.setTitle("Confirmer suppression");
                popUpWindow.setScene(new Scene((Parent) fxmlLoader.load()));
                popUpWindow.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Fonction traitant le ToggleButton permettant l'affichage ou non des sommets du graphe.
     */
    @FXML public void clickToggleSommet(ActionEvent event) {
        if(grapheView != null) {
            if (((ToggleButton) event.getSource()).isSelected()) {
                for (View.Sommet s : grapheView.getSommets()) {
                    s.getVue().setVisible(false);
                    grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
                }

            } else {
                for (View.Sommet s : grapheView.getSommets()) {
                    s.getVue().setVisible(true);
                    grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
                }
            }
        }
    }
    /**
     * Fonction traitant le ToggleButton permettant l'affichage ou non des aretes du graphe.
     */
    @FXML public void clickToggleArete(ActionEvent event) {
        if(grapheView != null) {
            if (((ToggleButton) event.getSource()).isSelected()) {
                for (Arete a : grapheView.getAretes()) {
                    a.setVisible(false);
                    grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
                }
            } else {
                for (Arete a : grapheView.getAretes()) {
                    a.setVisible(true);
                    grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
                }
            }
        }
    }
    /**
     * Fonction traitant le ToggleButton permettant l'affichage ou non des etiquettes du graphe.
     */
    @FXML public void clickToggleEtiquette(ActionEvent event) {
        if(grapheView != null) {
            if (((ToggleButton) event.getSource()).isSelected()) {
                for (View.Sommet s : grapheView.getSommets()) {
                    s.getLb().setVisible(false);
                    grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
                }
            } else {
                for (View.Sommet s : grapheView.getSommets()) {
                    s.getLb().setVisible(true);
                    grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
                }
            }
        }
    }
    /**
     *
     */
    @FXML public void clickZoomMinus() {
        //TODO zoom arriere
    }

    /**
     *
     */
    @FXML public void clickZoomPlus() throws IOException {
        //TODO zoom plus
    }
    /**
     *
     */
    @FXML private ColorPicker couleurFond;
    @FXML public void clickCouleurFond(MouseEvent event) throws IOException {
        couleurFond.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Color c = couleurFond.getValue();
                if (grapheView != null) {
                    grapheView.getCanvas().setPrefSize(grapheView.getScrollPane().getWidth(), grapheView.getScrollPane().getHeight());
                    grapheView.getCanvas().setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
                }
                else {
                    afficherFenetreAlerte("Importer un graphe avant de changer les couleurs.");
                }
            }
        });
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    @FXML public void clickModifyArete() throws IOException {
        //TODO modif aretes
        new ModifierAreteController(grapheModel, grapheView);
    }


    private ContextMenu contextMenu = new ContextMenu();
    private MenuItem proprieteSommet = new MenuItem("Tableau de propriétés du sommet");
    private MenuItem modifierSommet = new MenuItem("Modifier le sommet");
    private MenuItem supprimerSommet = new MenuItem("Supprimer le sommet");
    private MenuItem etiquetteSommet = new MenuItem("Tag du sommet");
    private MenuItem copierEtiquetteSommet = new MenuItem("Copier le tag du sommet");
    private MenuItem collerEtquetteSommet = new MenuItem("Coller le tag au sommet");

    /**
     * Fonction d'insertion des options du contextMenu
     */
    public void setContextMenu() {

        contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(proprieteSommet, modifierSommet, etiquetteSommet,supprimerSommet,copierEtiquetteSommet,collerEtquetteSommet);

        proprieteSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new AffichageProprieteSommet(grapheModel, sommetSelectionneModel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        modifierSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new ModifierSommet(grapheModel, sommetSelectionneModel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        supprimerSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new SuppressionSommet(grapheModel, sommetSelectionneModel, sommetSelectionneView, grapheView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        etiquetteSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new ModifierTagSommet(grapheModel, grapheView, sommetSelectionneModel, sommetSelectionneView);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        copierEtiquetteSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tagSommetSelectionne = sommetSelectionneModel.getTag() + "test";
            }
        });

        collerEtquetteSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tagSommetSelectionne != null) {
                    sommetSelectionneModel.setTag(tagSommetSelectionne);
                }
            }
        });

    }

    Sommet sommetSelectionneModel;
    View.Sommet sommetSelectionneView;
    String tagSommetSelectionne;
    public void setPane(Pane pane){
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    if(event.getClickCount() == 2){
                        if (grapheModel!=null){

                            setContextMenu();
                            boolean trouve = estTrouveSommet(event);

                            if(trouve) { //Si c'est sur un sommet, affichage du contextMenu.
                                contextMenu.show(vbox, event.getScreenX(), event.getScreenY());
                                event.consume();
                            }
                        }
                    }
                    else
                        enleverMenuContextuel();
                } else {
                    enleverMenuContextuel();
                }
            }
        });

        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (estTrouveSommet(event)) {
                    sommetSelectionneView.setCursor(Cursor.HAND);
                    sommetSelectionneView.setOnMouseDragged(sommetOnMouseDraggedEventHandler);
                }
            }
        });


    }


    private double dragDeltaX, dragDeltaY, orgTranslateX, orgTranslateY;

    private EventHandler<MouseEvent> sommetOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    sommetSelectionneView.setLayoutX(t.getSceneX() - sommetSelectionneModel.getX());
                    sommetSelectionneView.setLayoutY(t.getSceneY() - sommetSelectionneModel.getY());

                    sommetSelectionneModel.setX((float)sommetSelectionneView.getLayoutX());
                    sommetSelectionneModel.setY((float)sommetSelectionneView.getLayoutY());

                }
            };

    private boolean estTrouveSommet(MouseEvent event) {
        boolean trouve = false; // VRAI LIGNE, A GARDER
        int cpt = 0;
        sommetSelectionneModel = null; // VRAI LIGNE, A GARDER // LIGNE TEST, A EFFACER MAIS PAS DE SUITE (je test quoi)

        while(!trouve && cpt<grapheModel.getSommets().size()) { // DETECTION CLIC DROIT SUR SOMMET (On ne veut pas clic droit n'importe où).
            sommetSelectionneModel = grapheModel.getSommets().get(cpt);
            sommetSelectionneView = grapheView.getSommets().get(cpt);

            if(event.getX() >= sommetSelectionneModel.getX() &&
                    event.getX() <= sommetSelectionneModel.getX() + sommetSelectionneModel.getTaille().width*2 &&
                    event.getY() >= sommetSelectionneModel.getY() &&
                    event.getY() <= sommetSelectionneModel.getY() + sommetSelectionneModel.getTaille().height*2){
                trouve = true;
            }

            ++cpt;
        }
        return trouve;
    }

    private void enleverMenuContextuel() {
        sommetSelectionneModel = null;
        contextMenu.hide();
    }


    private void afficherFenetreAlerte(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fenêtre d'erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
