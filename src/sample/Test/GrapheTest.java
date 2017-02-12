package sample.Test;

import org.junit.Assert;
import org.junit.Test;
import sample.Model.Arete;
import sample.Model.Graphe;
import sample.Model.Sommet;

/**
 * Created by Mathieu on 06/02/2017.
 */
public class GrapheTest {

    @Test
    public void testAjoutSommet() throws Exception{
        Graphe g = new Graphe();
        Sommet s = new Sommet(true,1,1);
        Sommet s2 = new Sommet(false,2,2);

        //Test ajout sommet valide
        g.ajouterSommet(s);
        Assert.assertEquals(1,g.getM_sommets().size());

        //Test ajout sommet invalide
        g.ajouterSommet(s2);
        Assert.assertEquals(1,g.getM_sommets().size());
    }


    @Test
    public void testSuppressionSommet() throws Exception{
        Graphe g = new Graphe();
        Sommet s = new Sommet(true,1,1);
        Sommet s2 = new Sommet(false,2,2);

        g.ajouterSommet(s);
        g.ajouterSommet(s2);

        //Test Suppression Sommet invalide
        g.supprimetSommet(s2);
        Assert.assertEquals(1,g.getM_sommets().size());

        //Test suppression sommet valide
        g.supprimetSommet(s);
        Assert.assertEquals(0,g.getM_sommets().size());
    }

    @Test
    public void testAjoutArete() throws Exception{
        Graphe g = new Graphe();

        Sommet s = new Sommet(true,1,1);
        Sommet s2 = new Sommet(true,2,2);
        Sommet s3 = new Sommet(false,3,3);
        Sommet s4 = new Sommet(false,4,4);

        g.ajouterSommet(s);
        g.ajouterSommet(s2);
        g.ajouterSommet(s3);
        g.ajouterSommet(s4);

        //Test ajout arete valide sur sommets valides
        Arete a = g.ajouterArete(s,s2);
        Assert.assertEquals(1,g.getM_aretes().size());

        //Test ajout arete valide sur sommets invalides
        Arete b = g.ajouterArete(s,s3);
        Arete c = g.ajouterArete(s4,s2);
        Arete d = g.ajouterArete(s3,s4);
        Assert.assertEquals(1,g.getM_aretes().size());
    }

    @Test
    public void testSuppressionArete() throws Exception {
        Graphe g = new Graphe();

        Sommet s = new Sommet(true,1,1);
        Sommet s2 = new Sommet(true,2,2);
        Sommet s3 = new Sommet(false,3,3);
        Sommet s4 = new Sommet(false,4,4);

        g.ajouterSommet(s);
        g.ajouterSommet(s2);
        g.ajouterSommet(s3);
        g.ajouterSommet(s4);

        g.ajouterArete(s,s2);

        //Test suppression sommet dans arete qui entraine la suppression de l'arete
        g.supprimetSommet(s);
        Assert.assertEquals(0,g.getM_aretes().size());
    }
}