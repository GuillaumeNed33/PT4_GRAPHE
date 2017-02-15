package main.java.Test;

import org.junit.Test;
import main.java.Model.Sommet;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mathieu on 06/02/2017.
 */
public class SommetTest {
    @Test
    public void testAjoutSommet() throws Exception {
        Sommet sommet= new Sommet("s1",10,10);
        assertEquals(sommet.getTag(),"s1");
    }
}