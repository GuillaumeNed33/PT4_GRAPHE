package View;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Scale;

/**
 * Classe permettant d'initialiser se qui permettra de contenir le panneau affichant le graphe.
 */
public class ZoomableScrollPane extends ScrollPane {

    /**
     * Représente l'échelle générale de la vue.
     */
    private Scale scaleTransform;

    /**
     * Représente le panneau contenant les données permettant l'affichage du graphe.
     */
    private Node content;

    /**
     * Représente l'échelle de la vue.
     */
    private double scaleValue = 1.0;

    /**
     * Représente la valeur delta permettant d'incrémenter ou décrémenter l'échelle de la vue.
     */
    private double delta = 0.1;
    ZoomableScrollPane(Node content) {
        this.content = content;
        setContent(this.content);
        setFitToHeight(true);
        setFitToWidth(true);
        setPannable(true);
        setPadding(new Insets(50.f));
    }

    /**
     * Méthode permettant de mettre à jour le panneau affichant le graphe.
     * @param content Représente le panneau contenant les données permettant l'affichage du graphe.
     */
    public void updateScrollPane(Node content) {
        this.content = content;
        Group contentGroup = new Group();
        Group zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(content);
        setContent(contentGroup);
        scaleTransform = new Scale(scaleValue, scaleValue, 0, 0);
        zoomGroup.getTransforms().add(scaleTransform);
        zoomGroup.setOnScroll(new ZoomHandler());
    }


    /**
     * Méthode permettant de changer l'échelle de la vue.
     * @param scaleValue Représente la nouvelle échelle de la vue.
     */
    private void zoomTo(double scaleValue, ScrollEvent event) {

        this.scaleValue = scaleValue;

        scaleTransform.setPivotX(event.getX());
        scaleTransform.setPivotY(event.getY());
        scaleTransform.setX(scaleValue);
        scaleTransform.setY(scaleValue);
        setLayoutX(0);
        setLayoutY(70);
    }


    private void zoomTo(double scaleValue) {

        this.scaleValue = scaleValue;

        scaleTransform.setX(scaleValue);
        scaleTransform.setY(scaleValue);
    }

    /**
     * Méthode permettant de diminuer l'échelle de la vue.
     */
    public void zoomOut() {
        scaleValue -= delta;

        if (Double.compare(scaleValue, 0.1) < 0) {
            scaleValue = 0.1;
        }

        zoomTo(scaleValue);
    }

    /**
     * Méthode permettant d'augmenter l'échelle de la vue.
     */
    public void zoomIn() {

        scaleValue += delta;

        if (Double.compare(scaleValue, 10) > 0) {
            scaleValue = 10;
        }

        zoomTo(scaleValue);

    }

    /**
     * Classe permettant de vérifier si l'utilisateur utilise la molette pour augmenter ou diminuer l'échelle de la vue.
     */
    private class ZoomHandler implements EventHandler<ScrollEvent> {

        @Override
        public void handle(ScrollEvent scrollEvent) {
            // if (scrollEvent.isControlDown())
            {

                if (scrollEvent.getDeltaY() < 0) {
                    if (scaleValue >0.5)
                        scaleValue -= delta;
                } else {
                    scaleValue += delta;
                }

                zoomTo(scaleValue, scrollEvent);

                scrollEvent.consume();
            }
        }
    }
}
