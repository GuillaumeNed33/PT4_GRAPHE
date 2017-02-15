import Model.Arete;
import org.junit.Test;
import Model.Sommet;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mathieu on 06/02/2017.
 */
public class AreteTest {
    @Test
    public void testAjoutArete() throws Exception {
        Sommet depart = new Sommet("s1",1,1);
        Sommet departB = new Sommet("s3",1,3);
        Sommet arrivee = new Sommet("s2",2,2);
        Sommet arriveeB = new Sommet("s4",2,4);


        Arete arete = new Arete(depart,arrivee);
        assertEquals(arete.id(),5);
        Arete areteB = new Arete(departB,arriveeB);
        assertEquals(areteB.id(),6);
    }
}