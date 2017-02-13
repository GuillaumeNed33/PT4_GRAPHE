package sample.Test;

import org.junit.Assert;
import org.junit.Test;
import sample.Model.Graphe;
import sample.Model.Sommet;

/**
 * Created by Mathieu on 06/02/2017.
 */
public class GrapheTest {

    @Test
    public void testAjoutSommet() throws Exception{
        Graphe g = new Graphe();

        //Test ajout sommet valide
        g.ajouterSommet("s1",1,1);
        Assert.assertEquals(1,g.getM_sommets().size());
    }


    @Test
    public void testSuppressionSommet() throws Exception{
        Graphe g = new Graphe();
        Sommet s = new Sommet("s1",1,1);
        Sommet s2 = new Sommet("s2",2,2);

        g.ajouterSommet("s1",1,1);
        g.ajouterSommet("s2",2,2);

        //Test Suppression Sommet invalide
        g.supprimerSommet(s2);
        Assert.assertEquals(1,g.getM_sommets().size());

        //Test suppression sommet valide
        g.supprimerSommet(s);
        Assert.assertEquals(0,g.getM_sommets().size());
    }

    @Test
    public void testAjoutArete() throws Exception{
        Graphe g = new Graphe();

        Sommet s = new Sommet("s1",1,1);
        Sommet s2 = new Sommet("s2",2,2);
        Sommet s3 = new Sommet("s3",3,3);
        Sommet s4 = new Sommet("s4",4,4);

        g.ajouterSommet("s1",1,1);
        g.ajouterSommet("s2",2,2);
        g.ajouterSommet("s3",3,3);
        g.ajouterSommet("s4",4,4);

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
        Graphe g = new Graphe();

        Sommet s = new Sommet("s1",1,1);
        Sommet s2 = new Sommet("s2",2,2);
        Sommet s3 = new Sommet("s3",3,3);
        Sommet s4 = new Sommet("s4",4,4);

        g.ajouterSommet("s1",1,1);
        g.ajouterSommet("s2",2,2);
        g.ajouterSommet("s3",3,3);
        g.ajouterSommet("s4",4,4);

        g.ajouterArete(s,s2);

        //Test suppression sommet dans arete qui entraine la suppression de l'arete
        g.supprimerSommet(s);
        Assert.assertEquals(0,g.getM_aretes().size());
    }
}