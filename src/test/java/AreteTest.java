import Model.Arete;
import Model.Sommet;
import com.sun.glass.ui.Size;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mathieu on 06/02/2017.
 */
public class AreteTest {
    @Test
    public void testCreationArete() throws Exception {
        Sommet depart = new Sommet("s1",1,1);
        Sommet departB = new Sommet("s3",1,3);
        Sommet arrivee = new Sommet("s2",2,2);
        Sommet arriveeB = new Sommet("s4",2,4);


        Arete arete = new Arete(depart,arrivee);
        assertEquals(arete.getEntree(),depart);

        Arete areteB = new Arete(departB,arriveeB);
        assertEquals(areteB.getSortie(), arriveeB);
    }

    @Test
    public void testCouleur() throws Exception {
        Sommet depart = new Sommet("s1",1,1);
        Sommet arrivee = new Sommet("s2",2,2);

        Color couleurArete = javafx.scene.paint.Color.web("000000");

        Arete arete= new Arete(depart,arrivee);
        assertEquals(arete.getCouleurArete(), couleurArete);

        Color couleur2 = javafx.scene.paint.Color.web("555555");

        arete.setCouleurArete(couleur2);
        assertEquals(arete.getCouleurArete(),couleur2);
    }

    @Test
    public void testPoids() throws Exception {
        Sommet depart = new Sommet("s1",1,1);
        Sommet arrivee = new Sommet("s2",2,2);

        int poids = 13;

        Arete areteBis = new Arete(depart,arrivee,3);
        assertEquals(areteBis.getPoids(),3);

        Arete arete = new Arete(depart,arrivee);
        assertEquals(arete.getPoids(),0);

        arete.setPoids(poids);
        assertEquals(arete.getPoids(),poids);

    }

    @Test
    public void testEpaisseur() {

        Sommet depart = new Sommet("s1",1,1);
        Sommet arrivee = new Sommet("s2",2,2);

        Size size = new Size(0,0);
        Arete arete = new Arete(depart,arrivee);

        arete.setEpaisseur(size);
        assertEquals(arete.getEpaisseur(),size);

        Size size1 = new Size(3,3);
        arete.setEpaisseur(size1);

        assertEquals(arete.getEpaisseur(),size1);
    }

    @Test
    public void testSetEntreeSortie() {

        Sommet depart = new Sommet("s1",1,1);
        Sommet arrivee = new Sommet("s2",2,2);

        Arete arete = new Arete(depart,arrivee);
        assertEquals(arete.getEntree(),depart);
        assertEquals(arete.getSortie(),arrivee);

        Sommet nouveauDepart = new Sommet("s1",0,3);
        Sommet nouvelleArrivee = new Sommet("s1",0,4);

        arete.setEntree(nouveauDepart);
        arete.setSortie(nouvelleArrivee);
        assertEquals(arete.getEntree(),nouveauDepart);
        assertEquals(arete.getSortie(),nouvelleArrivee);
    }

    @Test
    public void testSetTag() {
        Sommet depart = new Sommet("s1",1,1);
        Sommet arrivee = new Sommet("s2",2,2);

        Arete arete = new Arete(depart,arrivee,"LeTag");
        assertEquals(arete.getTag(),"LeTag");

        arete.setTag("AutreTag");
        assertEquals(arete.getTag(),"AutreTag");
    }
}
