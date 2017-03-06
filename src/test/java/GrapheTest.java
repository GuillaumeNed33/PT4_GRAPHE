import Model.Graphe;
import Model.Sommet;
import com.sun.glass.ui.Size;
import javafx.scene.paint.Color;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(1,g.getSommets().size());


        //On ajoute le même sommet afin de voir si le graphe le prend tout de même en compte
        // (ce qui n'est pas attendu)
        g.ajouterSommet(new Sommet("s1",1,1),m);
        Assert.assertEquals(1,g.getSommets().size());
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
        Assert.assertEquals(1,g.getSommets().size());
        g.supprimerSommet(s);
        Assert.assertEquals(0,g.getSommets().size());
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
        Assert.assertEquals(1,g.getAretes().size());
        g.ajouterArete(s,s3);
        g.ajouterArete(s4,s2);
        g.ajouterArete(s3,s4);
        Assert.assertEquals(4,g.getAretes().size());

        //Test ajout même arete
        g.ajouterArete(s,s2);
        Assert.assertEquals(4,g.getAretes().size());
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
        Assert.assertEquals(1,g.getAretes().size());
        g.supprimerSommet(s);
        Assert.assertEquals(0,g.getAretes().size());
    }

    @Test
    public void testIndiceDegre() throws Exception{

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
        g.ajouterArete(s,s3);
        g.ajouterArete(s,s4);

        g.setIndiceDegre(s);
        g.setIndiceDegre(s2);
        g.setIndiceDegre(s3);
        g.setIndiceDegre(s4);
        Assert.assertEquals(s.getIndice(),3);
        Assert.assertEquals(s2.getIndice(),1);
        Assert.assertEquals(s3.getIndice(),1);
        Assert.assertEquals(s4.getIndice(),1);

        g.ajouterArete(s2,s3);
        g.setIndiceDegre(s2);
        g.setIndiceDegre(s3);
        Assert.assertEquals(s2.getIndice(),2);
        Assert.assertEquals(s3.getIndice(),2);

        g.supprimerSommet(s3);
        g.setIndiceDegre();
        g.setIndiceDegre(s2);
        g.setIndiceDegre(s4);
        Assert.assertEquals(s2.getIndice(),1);
        Assert.assertEquals(s.getIndice(),2);
    }

    @Test
    public void testsDegreMaxMin() throws Exception{

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
        g.ajouterArete(s,s3);
        g.ajouterArete(s,s4);

        g.setIndiceDegre(s);
        g.setIndiceDegre(s2);
        g.setIndiceDegre(s3);
        g.setIndiceDegre(s4);
        Assert.assertEquals(g.indiceMaxSommet(),3);
        Assert.assertEquals(g.indiceMinSommet(),1);
    }

    @Test
    public void testRandIndice() throws Exception {

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
        g.ajouterArete(s,s3);
        g.ajouterArete(s,s4);

        //On affecte des valeurs aléatoires aux indices et on les stocke en mémoire
        g.setIndiceAleatoire();
        int tmpS,tmpS2,tmpS3,tmpS4;
        tmpS = s.getIndice();
        tmpS2 = s2.getIndice();
        tmpS3 = s3.getIndice();
        tmpS4 = s4.getIndice();

        //On affecte de nouvelles valeurs aléatoires et on vérifie que ce ne sont pas les mêmes
        g.setIndiceAleatoire();
        Assert.assertFalse(s.getIndice()==tmpS);
        Assert.assertFalse(s2.getIndice()==tmpS2);
        Assert.assertFalse(s3.getIndice()==tmpS3);
        Assert.assertFalse(s4.getIndice()==tmpS4);
    }

    @Test
    public void testSourceDestinationArete() {

        Size m = new Size(10,10);
        Graphe g = new Graphe();

        Sommet s = new Sommet("s1",1,1);
        Sommet s2 = new Sommet("s2",2,2);

        g.ajouterSommet(s, m);
        g.ajouterSommet(s2, m);

        g.ajouterArete(s,s2);

        Assert.assertEquals(g.source(g.getAretes().get(0)),s);
        Assert.assertEquals(g.destination(g.getAretes().get(0)),s2);
    }

    @Test
    public void testSize() {

        Size m = new Size(10,10);
        Graphe g = new Graphe();

        g.setTaille(m);
        Assert.assertEquals(m,g.getTaille());
    }

    @Test
    public void testChangerCouleurArete() {
        Size m = new Size(10,10);
        Graphe g = new Graphe();

        Sommet s = new Sommet("s1",1,1);
        Sommet s2 = new Sommet("s2",2,2);

        g.ajouterSommet(s, m);
        g.ajouterSommet(s2, m);

        g.ajouterArete(s,s2);
        g.setIndiceDegre();

        g.changerCouleurArete(g.getAretes().get(0), Color.BLACK,Color.BLACK);

        Assert.assertEquals(g.getAretes().get(0).getCouleurArete(),Color.BLACK);
    }

    @Test
    public void testDeplaceSommet() {

        Size m = new Size(10,10);
        Graphe g = new Graphe();

        Sommet s = new Sommet("s1",1,1);

        g.ajouterSommet(s,m);

        g.deplacerSommet(s,3,3,m);

        Assert.assertEquals(s.getX(),3,0);
        Assert.assertEquals(s.getY(),3,0);
    }

    @Test
    public void testIndiceMinMaxArete() {

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
        g.ajouterArete(s,s3);
        g.ajouterArete(s,s4);
        g.ajouterArete(s2,s3);

        g.getAretes().get(0).setPoids(1);
        g.getAretes().get(1).setPoids(4);
        g.getAretes().get(2).setPoids(7);
        g.getAretes().get(3).setPoids(12);

        Assert.assertEquals(g.indiceMaxArete(),12,0);
        Assert.assertEquals(g.indiceMinArete(),1,0);
    }
}