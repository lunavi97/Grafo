package grafo;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GrafoTests {
    
    final int CANT = 4;
    Grafo g;
    
    @Before
    public void SetUp() {
        g = new Grafo(CANT);
    }
    
    @Test
    public void dfs() {
        g.agregarArista(0, 1);
        g.agregarArista(1, 2);
        g.agregarArista(1, 2);
        g.agregarArista(2, 0);
        g.agregarArista(2, 3);
        g.agregarArista(3, 3);
        
        ArrayList<Integer> recorridoRes = new ArrayList<Integer>();
        recorridoRes.add(2);
        recorridoRes.add(0);
        recorridoRes.add(1);
        recorridoRes.add(3);
        
        Assert.assertEquals(recorridoRes, g.dfs(2));
    }
    
    @Test
    public void bfs() {
        g.agregarArista(0, 1);
        g.agregarArista(1, 2);
        g.agregarArista(1, 2);
        g.agregarArista(2, 0);
        g.agregarArista(2, 3);
        g.agregarArista(3, 3);
        
        ArrayList<Integer> recorridoRes = new ArrayList<Integer>();
        recorridoRes.add(2);
        recorridoRes.add(0);
        recorridoRes.add(3);
        recorridoRes.add(1);
        
        Assert.assertEquals(recorridoRes, g.bfs(2));
    }
    
    @Test
    public void dijkstra() {
        Integer[][] matrizAdy = {{null,   5,     2,      3   },
                                {null,   null,   4,      null},
                                {null,   null,   null,   1   },
                                {null,   null,   null,   null}};
        
         g = new Grafo(matrizAdy);
         
         Integer[] distancia = {0, 5, 2, 3};
         Integer[] predecesor = {null, 0, 0, 0};
         CaminoMasCorto cmc = new CaminoMasCorto(distancia, predecesor);
         Assert.assertEquals(cmc, g.dijkstra(0));
         
         ArrayList<Integer> al = new ArrayList<Integer>();
         al.add(0);
         al.add(3);
         
         Assert.assertEquals(al, cmc.obtenerRecorrido(3));
    }

}
