package Controller;

import Model.Graphe;
import Model.Sommet;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FXMLController extends VBox{

    protected Parent Pop_up_view;
    protected Stage popUpWindow;
    protected Graphe graphe;
    private Stage primaryStage;
    private VBox vbox;
    private Pane pane;

    public FXMLController() throws IOException {

        initPopup();
    }

    public void initPopup() {
        popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setResizable(false);
    }


    /**
     * Fonction ouvrant une fenetre (FileChooser) permettant l'importation d'un fichier dans le logiciel.
     */
    @FXML public void clickFichierImporter() {
        FileChooser fileChooser = createFileChooser("Importer");
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            this.graphe = new Graphe(file.getAbsolutePath(), 600);
        }
    }

    /**
     * Fonction ouvrant une fenetre (FileChooser) permettant l'exportation d'un fichier dans le logiciel.
     */
    @FXML public void clickFichierExporter() {
        if (graphe != null) {
            FileChooser fileChooser = createFileChooser("Exporter");
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                this.graphe.sauvegarderGraphe(file.getAbsolutePath());
            }
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
        if (graphe != null)
            this.graphe.sauvegarderGraphe(null);
    }

    /**
     * Fonction permettant d'appliquer une distribution aléatoire des positions des sommets du graphe
     */
    @FXML public void clickRepresentationAleatoire() {
        this.graphe.setAlgorithmeRepresentation('a',600);
    }

    /**
     * Fonction permettant d'appliquer une distribution circulaire des positions des sommets du graphe
     */
    @FXML public void clickRepresentationCirculaire() {
        this.graphe.setAlgorithmeRepresentation('c',600);
    }

    /**
     * Fonction permettant d'appliquer une distribution aléatoire des positions des sommets du graphe
     */
    @FXML
    public void clickRepresentationForces() {
        this.graphe.setAlgorithmeRepresentation('f',600);
    }

    /**
     * Fonction permettant d'indicer aléatoirement tous les sommets du graphe
     */
    @FXML
    public void clickIndicageAleatoire(){
        if (graphe != null) {
            this.graphe.setIndiceAleatoire();
        }
    }

    @FXML
    public void clickIndicageDegré() {
        if (graphe != null) {
            this.graphe.setIndiceDegre();
        }
    }

    @FXML
    public void clickToggleRepresentation(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ModeleRepresentation.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Choix du modèle de la représentation");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void clickAjouterSommet() throws IOException {
        new AjoutSommetController(graphe);
    }

    @FXML
    public void clickAjouterArete() throws IOException {
        new AjoutAreteController(graphe);
    }
    @FXML
    public void clickTailleGraphe(MouseEvent mouseEvent) throws IOException {
        new TailleGrapheController(graphe);
    }

    /**
     *
     * @param event
     */
    @FXML public void clickCouleurGraphe(MouseEvent event) throws IOException {
        CouleurGrapheController c = new CouleurGrapheController(graphe);
    }

    protected void fermerPopup(Button button) {
        popUpWindow.close();
    }

    @FXML public void clickSupprimer() {
        if (graphe != null) {
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
        if(((ToggleButton)event.getSource()).isSelected()) {
            //TODO affichage Sommet

        } else {
            //TODO suppression de l'affichage sommet
        }
    }
    /**
     * Fonction traitant le ToggleButton permettant l'affichage ou non des aretes du graphe.
     */
    @FXML public void clickToggleArete(ActionEvent event) {
        if(((ToggleButton)event.getSource()).isSelected()) {
            //TODO affichage Arete
        } else {
            //TODO suppression de l'affichage arete
        }
    }
    /**
     * Fonction traitant le ToggleButton permettant l'affichage ou non des etiquettes du graphe.
     */
    @FXML public void clickToggleEtiquette(ActionEvent event) {
        if(((ToggleButton)event.getSource()).isSelected()) {
            //TODO affichage etiquette
        } else {
            //TODO suppression de l'affichage etiquette
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
        couleurFond.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color c = couleurFond.getValue();
                vbox.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));

            }
        });
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    @FXML public void clickModifyArete() throws IOException {
        //TODO modif aretes
        new ModifierAreteController(graphe);
    }


    private ContextMenu contextMenu = new ContextMenu();
    private MenuItem proprieteSommet = new MenuItem("Tableau de propriétés du sommet");
    private MenuItem supprimerSommet = new MenuItem("Supprimer le sommet");
    private MenuItem etiquetteSommet = new MenuItem("Tag du sommet");
    private MenuItem copierEtiquetteSommet = new MenuItem("Copier le tag du sommet");
    private MenuItem collerEtquetteSommet = new MenuItem("Coller le tag au sommet");

    /**
     * Fonction d'insertion des options du contextMenu
     */
    public void setContextMenu() {

        contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(proprieteSommet,etiquetteSommet,supprimerSommet,copierEtiquetteSommet,collerEtquetteSommet);

        proprieteSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new AffichageProprieteSommet(graphe, sommetSelectionne);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        supprimerSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new SuppressionSommet(graphe, sommetSelectionne);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        etiquetteSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new ModifierTagSommet(graphe, sommetSelectionne);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        copierEtiquetteSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tagSommetSelectionne = sommetSelectionne.getTag() + "test";
            }
        });

        collerEtquetteSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tagSommetSelectionne != null) {
                    sommetSelectionne.setTag(tagSommetSelectionne);
                }
            }
        });

    }

    /**
     * Récupère le Pane du Main et gère le contextMenu lors d'un clic droit.
     * @param pane
     */
    private Sommet sommetSelectionne;
    private String tagSommetSelectionne;
    public void setPane(Pane pane){
        this.pane = pane;
        pane.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {

                if (graphe!=null){

                    setContextMenu();
                    //boolean trouve = false; // VRAI LIGNE, A GARDER
                    boolean trouve = true; // LIGNE TEST, A EFFACER MAIS PAS DE SUITE (je test quoi)
                    int cpt = 0;
                    //Sommet tmp = null; // VRAI LIGNE, A GARDER // LIGNE TEST, A EFFACER MAIS PAS DE SUITE (je test quoi)
                    sommetSelectionne = graphe.getSommets().get(0);

                        /*while(!trouve && cpt<graphe.getSommets().size()) { // DETECTION CLIC DROIT SUR SOMMET (On ne veut pas clic droit n'importe où).
                            sommetSelectionne = graphe.getSommets().get(cpt);

                            if(event.getSceneX() >= sommetSelectionne.getX()-sommetSelectionne.getTailleForme().width &&
                                    event.getSceneX() <= sommetSelectionne.getX() + sommetSelectionne.getTailleForme().width &&
                                    event.getSceneY() >= sommetSelectionne.getY() - sommetSelectionne.getTailleForme().height &&
                                    event.getSceneY() <= sommetSelectionne.getY() + sommetSelectionne.getTailleForme().height){
                                trouve = true;
                            }

                            ++cpt;
                        }*/

                    if(trouve) { //Si c'est sur un sommet, affichage du contextMenu.
                        contextMenu.show(vbox, event.getScreenX(), event.getScreenY());
                        event.consume();
                    }
                }
            }
        });

        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sommetSelectionne = null;
                contextMenu.hide();
            }
        });
    }


    @FXML public void suppressionSommetSelectionne() {
        graphe.supprimerSommet(sommetSelectionne);
        sommetSelectionne = null;
    }

}
