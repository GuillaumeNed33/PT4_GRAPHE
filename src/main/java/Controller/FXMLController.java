package Controller;

import Model.Graphe;
import Model.Sommet;
import View.Arete;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
                grapheModel = new Graphe(file.getAbsolutePath(), 1200, 600);
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
                grapheModel = new Graphe(file.getAbsolutePath(), 1200,600);
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
        grapheModel.sauvegarderGraphe(null);
    }

    /**
     * Fonction permettant d'appliquer une distribution aléatoire des positions des sommets du graphe
     */
    @FXML public void clickRepresentationAleatoire() {
        grapheModel.setAlgorithmeRepresentation('a',1200, 600);
    }

    /**
     * Fonction permettant d'appliquer une distribution circulaire des positions des sommets du graphe
     */
    @FXML public void clickRepresentationCirculaire() {
        grapheModel.setAlgorithmeRepresentation('c',1200, 600);
    }

    /**
     * Fonction permettant d'appliquer une distribution aléatoire des positions des sommets du graphe
     */
    @FXML
    public void clickRepresentationForces() {
        grapheModel.setAlgorithmeRepresentation('f',1200, 600);
    }

    /**
     * Fonction permettant d'indicer aléatoirement tous les sommets du graphe
     */
    @FXML
    public void clickIndicageAleatoire(){
        if (grapheModel != null) {
            this.grapheModel.setIndiceAleatoire();
        }
    }

    @FXML
    public void clickIndicageDegré() {
        if (grapheModel != null) {
            this.grapheModel.setIndiceDegre();
        }
    }

    @FXML
    public void clickAjouterSommet() throws IOException {
        new AjoutSommetController(grapheModel);
    }

    @FXML
    public void clickAjouterArete() throws IOException {
        new AjoutAreteController(grapheModel);
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
        CouleurGrapheController c = new CouleurGrapheController(grapheModel);
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
        if(((ToggleButton)event.getSource()).isSelected()) {
            for(View.Sommet s : grapheView.getSommets()) {
                s.getVue().setVisible(false);
                grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
            }

        } else {
            for(View.Sommet s : grapheView.getSommets()) {
                s.getVue().setVisible(true);
                grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
            }
        }
    }
    /**
     * Fonction traitant le ToggleButton permettant l'affichage ou non des aretes du graphe.
     */
    @FXML public void clickToggleArete(ActionEvent event) {
        if(((ToggleButton)event.getSource()).isSelected()) {
            for(Arete a : grapheView.getAretes()) {
                a.setVisible(false);
                grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
            }
        } else {
            for(Arete a : grapheView.getAretes()) {
                a.setVisible(true);
                grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
            }
        }
    }
    /**
     * Fonction traitant le ToggleButton permettant l'affichage ou non des etiquettes du graphe.
     */
    @FXML public void clickToggleEtiquette(ActionEvent event) {
        if(((ToggleButton)event.getSource()).isSelected()) {
            for(View.Sommet s : grapheView.getSommets()) {
                s.getLb().setVisible(false);
                grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
            }
        } else {
            for(View.Sommet s : grapheView.getSommets()) {
                s.getLb().setVisible(true);
                grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
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
        couleurFond.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color c = couleurFond.getValue();
                grapheView.getCanvas().setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    @FXML public void clickModifyArete() throws IOException {
        //TODO modif aretes
        new ModifierAreteController(grapheModel);
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
                    new AffichageProprieteSommet(grapheModel, sommetSelectionne);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        modifierSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new ModifierSommet(grapheModel, sommetSelectionne);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        supprimerSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new SuppressionSommet(grapheModel, sommetSelectionne);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        etiquetteSommet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new ModifierTagSommet(grapheModel, sommetSelectionne);
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

        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    if(event.getClickCount() == 2){
                        if (grapheModel!=null){

                            setContextMenu();
                            //boolean trouve = false; // VRAI LIGNE, A GARDER
                            boolean trouve = true; // LIGNE TEST, A EFFACER MAIS PAS DE SUITE (je test quoi)
                            int cpt = 0;
                            //Sommet tmp = null; // VRAI LIGNE, A GARDER // LIGNE TEST, A EFFACER MAIS PAS DE SUITE (je test quoi)
                            sommetSelectionne = grapheModel.getSommets().get(0);

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
                    else {
                        sommetSelectionne = null;
                        contextMenu.hide();
                    }
                } else {
                    sommetSelectionne = null;
                    contextMenu.hide();
                }
            }
        });
    }


    @FXML public void suppressionSommetSelectionne() {
        grapheModel.supprimerSommet(sommetSelectionne);
        sommetSelectionne = null;
    }

}
