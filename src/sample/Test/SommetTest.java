package sample.Test;

import org.junit.Test;
import sample.Model.Sommet;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mathieu on 06/02/2017.
 */
public class SommetTest {
    @Test
    public void testAjoutSommet() throws Exception {
        Sommet sommet= new Sommet(true);
        assertEquals(sommet.valide(),true);
        assertEquals(sommet.id(),0);

        Sommet sommetF = new Sommet(false);
        assertEquals(sommetF.valide(),false);
        assertEquals(sommetF.id(),-1);

        Sommet sommet2 = new Sommet(true);
        assertEquals(sommet2.valide(),true);
        assertEquals(sommet2.id(),1);
    }

    @Test
    public void testSuppressionSommet() throws Exception {
        Sommet sommet = new Sommet(true);

        sommet.supprimer();
        assertEquals(sommet.valide(),false);
        assertEquals(sommet.id(),-1);
    }
}