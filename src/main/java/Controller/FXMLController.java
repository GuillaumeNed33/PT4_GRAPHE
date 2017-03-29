package Controller;

import Model.Graphe;
import Model.Sommet;
import View.Arete;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Classe FXMLController
 * permettant de gérer la fenêtre principale
 */
public class FXMLController extends VBox {
    /**
     * Représente la fenêtre principale
     */
    protected Stage popUpWindow;
    /**
     * Représente le graphe de View
     */
    protected View.Graphe grapheView;
    /**
     * Représente la graphe du Model
     */
    protected Model.Graphe grapheModel;
    /**
     * Représente la scène
     */
    private VBox vbox;

    /**
     * Représente le choix de couleur de fond de la fenêtre
     */
    @FXML private ColorPicker couleurFond;

    /**
     * Représente le sommet sélectionné dans le Model
     */
    Sommet sommetSelectionneModel;

    /**
     * Représente le sommet sélectionné dans la View
     */
    View.Sommet sommetSelectionneView;

    /**
     * Représente l'étiquette du sommet sélectionné
     */
    private String tagSommetSelectionne;

    /**
     * Représente le fait d'avoir déjà cliqué su importer/exporter.
     */
    private static boolean clicImporterExporter = false;

    /**
     * Constructeur initialisant la fenêtre principale
     * @throws IOException lève une exception
     */
    public FXMLController() throws IOException {
        popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setResizable(false);
    }

    // Accesseur
    public VBox getVbox() {
        return vbox;
    }

    // Mutateur
    public void setGrapheView(View.Graphe g) {
        grapheView = g;
    }
    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }


    /**
     * Fonction ouvrant une fenetre (FileChooser) permettant l'importation d'un fichier dans le logiciel.
     */
    @FXML public void clickFichierImporter() {

        if (grapheView == null && !clicImporterExporter) {
            clicImporterExporter = true;

            FileChooser fileChooser = createFileChooser("Importer");
            fileChooser.setInitialDirectory(new File("./ressources/"));
            File file = fileChooser.showOpenDialog(null);

            if (file != null){
                grapheView = new View.Graphe();
                grapheModel = new Graphe(file.getAbsolutePath(), (int)vbox.getWidth(), (int)(vbox.getHeight() - 90));
                grapheView.chargerGraphe(grapheModel);
                getVbox().getChildren().addAll(grapheView.getScrollPane());
                setPane(grapheView.getCanvas());
            }
            clicImporterExporter = false;
        }
        else {
            if (!clicImporterExporter) {
                clicImporterExporter = true;

                FileChooser fileChooser = createFileChooser("Importer");
                fileChooser.setInitialDirectory(new File("./ressources/"));
                File file = fileChooser.showOpenDialog(null);

                if (file != null) {
                    grapheModel = new Graphe(file.getAbsolutePath(), (int)vbox.getWidth(), (int)(vbox.getHeight() - 90));
                    grapheView = new View.Graphe();
                    grapheView.chargerGraphe(grapheModel);
                    getVbox().getChildren().remove(1);
                    getVbox().getChildren().add(grapheView.getScrollPane());
                    setPane(grapheView.getCanvas());
                }
                clicImporterExporter = false;
            }
        }
    }

    /**
     * Fonction ouvrant une fenetre (FileChooser) permettant l'exportation d'un fichier dans le logiciel.
     */
    @FXML public void clickFichierExporter() {
        if (grapheModel != null && !clicImporterExporter) {
            clicImporterExporter = true;

            FileChooser fileChooser = createFileChooser("Exporter");
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                grapheModel.sauvegarderGraphe(file.getAbsolutePath());
            }

            clicImporterExporter = false;
        }
        else if (!clicImporterExporter) {
            afficherFenetreAlerte("Vous ne pouvez pas exporter si vous n'avez pas importé un graphe avant.");
        }
    }

    /**
     * Fonction de création d'une fenêtre de choix de fichiers
     * @param titre de la fenêtre
     * @return le fileChooser
     */
    private FileChooser createFileChooser(String titre) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(titre);
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
            afficherFenetreAlerte("Vous ne pouvez pas enregistrer si vous n'avez pas importé un graphe avant.");
        }
    }

    /**
     * Fonction permettant d'appliquer une distribution aléatoire des positions des sommets du graphe
     */
    @FXML public void clickRepresentationAleatoire() {
        if (grapheModel != null) {
        grapheModel.setAlgorithmeRepresentation('a',(int)vbox.getWidth(), (int)(vbox.getHeight() - 90));
        grapheView.miseAJourPositions(grapheModel);
        grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
        }

        else
            afficherFenetreAlerte("Vous ne pouvez pas appliquer la représentation aléatoire si vous n'avez pas importé un graphe avant.");

    }

    /**
     * Fonction permettant d'appliquer une distribution circulaire des positions des sommets du graphe
     */
    @FXML public void clickRepresentationCirculaire() {
        if (grapheModel != null) {
            grapheModel.setAlgorithmeRepresentation('c', (int)vbox.getWidth(), (int)(vbox.getHeight() - 90));
            grapheView.miseAJourPositions(grapheModel);
            grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
        }
        else {
            afficherFenetreAlerte("Vous ne pouvez pas appliquer la représentation circulaire si vous n'avez pas importé un graphe avant.");
        }
    }

    /**
     * Fonction permettant d'appliquer une distribution aléatoire des positions des sommets du graphe
     */
    @FXML
    public void clickRepresentationForces() {
        if (grapheModel != null && grapheModel.indiceFixe()) {
            grapheModel.setAlgorithmeRepresentation('f', (int)vbox.getWidth(), (int)(vbox.getHeight() - 90));
            grapheView.miseAJourPositions(grapheModel);
            grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
        }
        else {
            afficherFenetreAlerte("Vous ne pouvez pas appliquer la représentation modèle de forces si vous n'avez pas " +
                    "importé un graphe avant ou si vous n'avez pas indicé le graphe.");
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

    /**
     * Fonction permettant d'indicer par leur degré tous les sommets du graphe
     */
    @FXML
    public void clickIndicageDegré() {
        if (grapheModel != null) {
            this.grapheModel.setIndiceDegre();
        }
        else {
            afficherFenetreAlerte("Vous ne pouvez pas appliquer un degré si vous n'avez pas importé un graphe avant");
        }
    }

    /**
     * Fonction de controle de l'ajout d'un sommet
     * redirigeant vers le controller AjoutSommetController
     * @throws IOException lève une exception
     */
    @FXML
    public void clickAjouterSommet() throws IOException {
        if (grapheModel != null)
            new AjoutSommetController(grapheModel, grapheView);
        else
            afficherFenetreAlerte("Vous ne pouvez pas ajouter de sommet si vous n'avez pas importé un graphe avant");
    }

    /**
     * Fonction de controle de l'ajout d'une arête
     * redirigeant vers le controller AjoutAreteController
     * @throws IOException lève une exception
     */
    @FXML
    public void clickAjouterArete() throws IOException {
        if (grapheModel != null)
            new AjoutAreteController(grapheModel, grapheView);
        else
            afficherFenetreAlerte("Vous ne pouvez pas ajouter d'arête si vous n'avez pas importé un graphe avant");
    }

    /**
     * Fonction de controle de la taille du graphe
     * redirigeant vers le controller TailleGrapheController
     * @param mouseEvent clic
     * @throws IOException lève une exception
     */
    @FXML
    public void clickTailleGraphe(MouseEvent mouseEvent) throws IOException {
        if (grapheModel != null)
            new TailleGrapheController(grapheModel, grapheView);
        else
            afficherFenetreAlerte("Vous ne pouvez pas modifier la taille du graphe si vous n'avez pas importé un graphe avant");
    }

    /**
     * Fonction de controle de la coloration du graphe
     * redirigeant vers le controller CouleurGrapheController
     * @param event clic
     * @throws IOException lève une exception
     */
    @FXML public void clickCouleurGraphe(MouseEvent event) throws IOException {
        if (grapheModel != null)
            new CouleurGrapheController(grapheModel, grapheView);
        else
            afficherFenetreAlerte("Vous ne pouvez pas modifier la couleur du graphe si vous n'avez pas importé un graphe avant");
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

            }
            else {
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
            }
            else {
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
            }
            else {
                for (View.Sommet s : grapheView.getSommets()) {
                    s.getLb().setVisible(true);
                    grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
                }
            }
        }
    }


    /**
     * Fonction de zoom -
     */
    @FXML public void clickZoomMinus() {
        if (grapheModel != null)
            grapheView.getScrollPane().zoomOut();
    }

    /**
     * Fonction de zoom +
     */
    @FXML public void clickZoomPlus()  throws IOException{
        if (grapheModel != null)
            grapheView.getScrollPane().zoomIn();
    }

    /**
     * Fonction permettant de modifier la couleur de fond de la fenêtre d'affichage du graphe
     * @param event clic
     * @throws IOException lève une exception
     */
    @FXML public void clickCouleurFond(MouseEvent event) throws IOException {
        couleurFond.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Color c = couleurFond.getValue();

                if (grapheView != null) {
                    grapheView.getScrollPane().setMinSize(getVbox().getWidth(), grapheView.getCanvas().getHeight());
                    grapheView.getCanvas().setMinSize(grapheView.getScrollPane().getWidth(), grapheView.getScrollPane().getHeight());
                    grapheView.getScrollPane().setStyle("-fx-background: rgb(" + c.getRed() * 255 + "," + c.getGreen() * 255 + "," + c.getBlue() * 255 + ");");
                }
                else {
                    afficherFenetreAlerte("Importez un graphe avant de changer les couleurs.");
                }


            }
        });
    }

    /**
     * Fonction de controle de la modification d'une arête du graphe
     * redirigeant vers le controller ModifierAreteController
     * @throws IOException lève une exception
     */
    @FXML public void clickModifyArete() throws IOException {
        if (grapheModel != null)
            new ModifierAreteController(grapheModel, grapheView);
        else
            afficherFenetreAlerte("Vous ne pouvez pas modifier d'arête si vous n'avez pas importé un graphe avant");
    }


    /**
     * Représente le menu contextuel lors d'un double clic sur un sommet
     * avec tous ses sous-menus
     */
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
    private void setContextMenu() {
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
                    new ModifierSommet(grapheModel, grapheView, sommetSelectionneModel, sommetSelectionneView);
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
                tagSommetSelectionne = sommetSelectionneModel.getTag();
            }
        });

        collerEtquetteSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tagSommetSelectionne != null) {
                    sommetSelectionneModel.setTag(tagSommetSelectionne);
                    sommetSelectionneView.setLb(tagSommetSelectionne);
                }
            }
        });

    }

    /**
     * Fonction faisant apparaitre le menu contextuel lors d'un double clic sur un sommet valide
     * @param pane Pane
     */
    private void setPane(Pane pane){
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

                    sommetSelectionneView.setCursor(Cursor.CLOSED_HAND);

                    sommetSelectionneView.setOnMouseReleased(sommetOnMouseReleaseEventHandler);
                    sommetSelectionneView.setOnMouseDragged(sommetOnMouseDraggedEventHandler);
                }
            }
        });
    }

    private EventHandler<MouseEvent> sommetOnMouseReleaseEventHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    sommetSelectionneView.setCursor(null);
                    sommetSelectionneView.setOnMouseDragged(null);
                    sommetSelectionneView = null;
                }
            };

    private EventHandler<MouseEvent> sommetOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    sommetSelectionneView.setCoord(event.getX(), event.getY());
                    grapheView.misAJourAretes(sommetSelectionneView);
                    sommetSelectionneModel.setX((float)event.getX());
                    sommetSelectionneModel.setY((float)event.getY());
                }
            };

    /** Fonction permettant de trouver le sommet sous le curseur
     * @param event curseur
     * @return vrai si le curseur correspond à un sommet faux sinon
     */
    private boolean estTrouveSommet(MouseEvent event) {
        boolean trouve = false;
        int cpt = 0;
        sommetSelectionneModel = null;

        while(!trouve && cpt<grapheModel.getSommets().size()) {
            sommetSelectionneModel = grapheModel.getSommets().get(cpt);
            sommetSelectionneView = grapheView.getSommets().get(cpt);

            if(event.getX() >= sommetSelectionneModel.getX() - sommetSelectionneModel.getTaille().width &&
                    event.getX() <= sommetSelectionneModel.getX() + sommetSelectionneModel.getTaille().width &&
                    event.getY() >= sommetSelectionneModel.getY() - sommetSelectionneModel.getTaille().height &&
                    event.getY() <= sommetSelectionneModel.getY() + sommetSelectionneModel.getTaille().height) {

                trouve = true;
            } else {
                ++cpt;
                sommetSelectionneModel = null;
                sommetSelectionneView = null;
            }
        }

        return trouve;
    }

    /**
     * Fonction cachant le menu contextuel lorsque l'utilisateur n'est plsu sur un sommet
     */
    private void enleverMenuContextuel() {
        sommetSelectionneModel = null;
        contextMenu.hide();
    }

    /**
     * Fenetre d'alerte
     * @param message à faire apparaitre dans la fenêtre
     */
    private void afficherFenetreAlerte(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fenêtre d'erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
