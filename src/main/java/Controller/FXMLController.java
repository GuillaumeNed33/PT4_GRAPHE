package Controller;

import Model.Graphe;
import Model.Sommet;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FXMLController extends VBox{

    private Parent Pop_up_view;
    private Stage popUpWindow;
    private Graphe graphe;

    public FXMLController() {
        popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setResizable(false);
    }
    @FXML private StackPane subPane;

    private ContextMenu contextMenu = new ContextMenu();
    private MenuItem proprieteSommet = new MenuItem("Tableau de propriétés du sommet");
    private MenuItem supprimerSommet = new MenuItem("Supprimer le sommet");
    private MenuItem etiquetteSommet = new MenuItem("Etiquette du sommet");
    private MenuItem copierEtiquetteSommet = new MenuItem("Copier l'étiquette du sommet");
    private MenuItem collerEtquetteSommet = new MenuItem("Coller l'étiquette au sommet");

    public void clicPane(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            contextMenu = new ContextMenu();
            contextMenu.getItems().addAll(proprieteSommet,etiquetteSommet,supprimerSommet,copierEtiquetteSommet,collerEtquetteSommet);

            proprieteSommet.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ProprietesSommetTab.fxml"));
                        Parent root1 = fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            supprimerSommet.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    clickSupprimer();
                }
            });

            etiquetteSommet.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/EtiquetteSommet.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            copierEtiquetteSommet.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //TODO Il faut pouvoir récupérer un sommet
                }
            });

            collerEtquetteSommet.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //TODO Il faut pouvoir récupérer un sommet
                }
            });

            contextMenu.show(subPane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
        } else {
            //Actions autre que clic droit

            contextMenu.hide();
        }
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
            Parent root1 = (Parent) fxmlLoader.load();
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
    public void clickAjouterSommet() {
        if (graphe != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AjoutSommet.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Ajouter Sommet");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private String sommetEntreSelectionne, sommetSortieSelectionne;
    @FXML
    private
    ListView listViewSommetsE, listViewSommetsS;
    @FXML
    public void clickAjouterArete() {
        if (graphe != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AjoutArete.fxml"));

                fxmlLoader.setRoot(this);
                fxmlLoader.setController(this);

                popUpWindow.setTitle("Ajouter Arête");
                popUpWindow.setScene(new Scene((Parent)fxmlLoader.load()));

                popUpWindow.show();

                List<String> sommetsStr = new ArrayList<String>();

                for (Sommet sommet : graphe.getSommets()) {
                    sommetsStr.add("id : " + sommet.getId() + " ( tag : " + sommet.getTag() + ")");
                }

                ListProperty<String> listProperty = new SimpleListProperty<String>();
                listProperty.set(FXCollections.observableArrayList(sommetsStr));


                listViewSommetsE.itemsProperty().bind(listProperty);
                listViewSommetsS.itemsProperty().bind(listProperty);

                listViewSommetsE.getSelectionModel().selectedItemProperty()
                        .addListener(new ChangeListener<String>() {
                            public void changed(ObservableValue<? extends String> ov,
                                                String old_val, String new_val) {
                                sommetEntreSelectionne = new_val;
                            }
                        });

                listViewSommetsS.getSelectionModel().selectedItemProperty()
                        .addListener(new ChangeListener<String>() {
                            public void changed(ObservableValue<? extends String> ov,
                                                String old_val, String new_val) {
                                sommetSortieSelectionne = new_val;
                            }
                        });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private
    Label erreurAjoutArete;
    @FXML public void ajouterArete() {

        if (sommetEntreSelectionne != null && sommetSortieSelectionne != null) {

            int idSommetEntre = Integer.parseInt(sommetEntreSelectionne.split(" ")[2]);
            int idSommetSortie = Integer.parseInt(sommetSortieSelectionne.split(" ")[2]);


            if (idSommetEntre != -1 && idSommetSortie != 1 && !graphe.ajouterArete(graphe.trouverSommetParID(idSommetEntre), graphe.trouverSommetParID(idSommetSortie))) {
                erreurAjoutArete.setText("Erreur - Arete existante ou 2 sommets identiques \nsélectionnés.");
            }
        }
        else {
            erreurAjoutArete.setText("Erreur - Sélectionnez 2 sommets.");
        }
    }

    @FXML
    private
    Button boutonAnnulerAjoutArete;
    @FXML public void fermerAjoutArete() {
        fermerPopup(boutonAnnulerAjoutArete);
    }

    @FXML public void clickSupprimer() {
        if (graphe != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SupprSommer.fxml"));
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
    @FXML public void clickZoomPlus() {
        //TODO zoom plus
    }
    /**
     *
     */
    @FXML public void clickCouleurFond(ActionEvent event) {

    }
    /**
     *
     */
    @FXML public void clickCouleurGraphe(ActionEvent event) {
        if (graphe != null) {
            try {
                popUpWindow.setTitle("Changement Couleur");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CouleurGraphe.fxml"));
                popUpWindow.setScene(new Scene((Parent) fxmlLoader.load()));
                popUpWindow.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     */
    @FXML public void clickCouleurElement(ActionEvent event) {

    }

    /**
     *
     */
    @FXML public void clickOptionGraphe() {
        if (graphe != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/TailleGraphe.fxml"));
                popUpWindow.setTitle("Taille du Graphe");

                fxmlLoader.setRoot(this);
                fxmlLoader.setController(this);

                popUpWindow.setScene(new Scene((Parent) fxmlLoader.load()));
                popUpWindow.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private
    TextField idMinSommet, idMaxSommet, idMinArete, idMaxArete;
    @FXML
    private
    Button okTailleGraphe;
    @FXML
    private
    Label erreurMessageId;
    @FXML public void changementTailleGraphe() {

        if (idMinSommet.getText().matches("^[0-9]+$") && idMaxSommet.getText().matches("^[0-9]+$") &&
                idMinArete.getText().matches("^[0-9]+$") && idMaxArete.getText().matches("^[0-9]+$")) {

            float minSommet = (float) Integer.parseInt(idMinSommet.getText());
            float maxSommet = (float) Integer.parseInt(idMaxSommet.getText());

            float minArete = (float) Integer.parseInt(idMinArete.getText());
            float maxArete = (float) Integer.parseInt(idMaxArete.getText());

            if ((minSommet < maxSommet && minArete < maxArete) &&
                    (minSommet >= 1 && maxSommet > 1 && minArete >= 1 && maxArete > 1)) {
                this.graphe.changerTailleGraphe(maxSommet, minSommet, maxArete, minArete);
                fermerPopup(okTailleGraphe);
            } else {
                erreurMessageId.setText("Erreur - Valeurs incorrectes (max > min, min >= 1 et max >1).");
            }
        } else {
            erreurMessageId.setText("Erreur - Vérifiez la présence de lettres, oublie de valeurs et/ou valeurs négatives.");
        }
    }

    @FXML
    private
    Button boutonAnnuler;
    @FXML public void fermerPopupChangementTaille() {
        fermerPopup(boutonAnnuler);
    }

    private void fermerPopup(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();

        stage.close();
    }
}
