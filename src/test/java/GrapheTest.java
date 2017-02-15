import com.sun.glass.ui.Size;
import org.junit.Assert;
import org.junit.Test;
import Model.Graphe;
import Model.Sommet;

/**
 * Created by Mathieu on 06/02/2017.
 */
public class GrapheTest {

    @Test
    public void testAjoutSommet() throws Exception{
        Graphe g = new Graphe();

        //Test ajout sommet
        Size m = new Size(10,10);
        g.ajouterSommet(new Sommet("s1",1,1),m);
        Assert.assertEquals(1,g.getM_sommets().size());


        //On ajoute le même sommet afin de voir si le graphe le prend tout de même en compte
        // (ce qui n'est pas attendu)
        g.ajouterSommet(new Sommet("s1",1,1),m);
        Assert.assertEquals(1,g.getM_sommets().size());
    }


    @Test
    public void testSuppressionSommet() throws Exception{
        Size m = new Size(10,10);
        Graphe g = new Graphe();
        Sommet s = new Sommet("s1",1,1);
        Sommet s2 = new Sommet("s2",2,2);

        g.ajouterSommet(s, m);
        g.ajouterSommet(s2, m);

        //Test Suppression sommets
        g.supprimerSommet(s2);
        Assert.assertEquals(1,g.getM_sommets().size());
        g.supprimerSommet(s);
        Assert.assertEquals(0,g.getM_sommets().size());
    }

    @Test
    public void testAjoutArete() throws Exception{
        Size m = new Size(10,10);
        Graphe g = new Graphe();

        Sommet s = new Sommet("s1",1,1);
        Sommet s2 = new Sommet("s2",2,2);
        Sommet s3 = new Sommet("s3",3,3);
        Sommet s4 = new Sommet("s4",4,4);

        g.ajouterSommet(s, m);
        g.ajouterSommet(s2, m);
        g.ajouterSommet(s3, m);
        g.ajouterSommet(s4, m);

        //Test ajout arete
        g.ajouterArete(s,s2);
        Assert.assertEquals(1,g.getM_aretes().size());
        g.ajouterArete(s,s3);
        g.ajouterArete(s4,s2);
        g.ajouterArete(s3,s4);
        Assert.assertEquals(4,g.getM_aretes().size());

        //Test ajout même arete
        g.ajouterArete(s,s2);
        Assert.assertEquals(4,g.getM_aretes().size());
    }

    @Test
    public void testSuppressionArete() throws Exception {
        Size m = new Size(10,10);
        Graphe g = new Graphe();

        Sommet s = new Sommet("s1",1,1);
        Sommet s2 = new Sommet("s2",2,2);
        Sommet s3 = new Sommet("s3",3,3);
        Sommet s4 = new Sommet("s4",4,4);

        g.ajouterSommet(s, m);
        g.ajouterSommet(s2, m);
        g.ajouterSommet(s3, m);
        g.ajouterSommet(s4, m);

        g.ajouterArete(s,s2);

        //Test suppression sommet dans arete qui entraine la suppression de l'arete
        Assert.assertEquals(1,g.getM_aretes().size());
        g.supprimerSommet(s);
        Assert.assertEquals(0,g.getM_aretes().size());
    }
}