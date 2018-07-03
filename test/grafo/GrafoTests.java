package grafo;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GrafoTests {

    final int CANT = 4; // Cardinalidad de referencia
    Grafo g;

    // Vértices
    final int A = 0;
    final int B = 1;
    final int C = 2;
    final int D = 3;
    final int E = 4;
    final int F = 5;
    final int G = 6;
    final int H = 7;

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
        Integer[][] matrizAdy = {
                {null,   5,     2,      3   },
                {null,   null,   4,      null},
                {null,   null,   null,   1   },
                {null,   null,   null,   null}
        };

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

    @Test
    public void kruskal() {
        Integer[][] matrizAdy = {
                {null, 4,    null, null, null, null, null, 8,    null},    // 0
                {4,    null, 8,    null, null, null, null, 11,   null},    // 1
                {null, 8,    null, 7,    null, 4,    null, null, 2   },    // 2
                {null, null, 7,    null, 9,    14,   null, null, null},    // 3
                {null, null, null, 9,    null, 10,   null, null, null},    // 4
                {null, null, 4,    14,   10,   null, 2,    null, null},    // 5
                {null, null, null, null, null, 2,    null, 1,    6   },    // 6
                {8,    11,   null, null, null, null, 1,    null, 7   },    // 7
                {null, null, 2,    null, null, null, 6,    7,    null}     // 8
        };      // 0     1     2     3     4     5     6     7     8

        g = new Grafo(matrizAdy);

        ArrayList<Arista> aristas = new ArrayList<Arista>();
        aristas.add(new Arista(6, 7, 1));
        aristas.add(new Arista(5, 6, 2));
        aristas.add(new Arista(2, 8, 2));
        aristas.add(new Arista(2, 5, 4));
        aristas.add(new Arista(0, 1, 4));
        aristas.add(new Arista(2, 3, 7));
        aristas.add(new Arista(1, 2, 8));
        aristas.add(new Arista(3, 4, 9));
        Assert.assertEquals(new ArbolRecubridorMinimo(aristas, 37), g.kruskal());
    }

    @Test
    public void prim() {
        ArrayList<Arista> aristas;

        g = new Grafo(8);

        g.agregarAristaNoDirigida(A, B, 7);
        g.agregarAristaNoDirigida(A, F, 9);
        g.agregarAristaNoDirigida(B, C, 1);
        g.agregarAristaNoDirigida(B, D, 2);
        g.agregarAristaNoDirigida(C, D, 5);
        g.agregarAristaNoDirigida(C, E, 2);
        g.agregarAristaNoDirigida(D, E, 4);
        g.agregarAristaNoDirigida(D, F, 3);
        g.agregarAristaNoDirigida(E, H, 10);
        g.agregarAristaNoDirigida(F, G, 1);

        aristas = new ArrayList<Arista>();
        aristas.add(new Arista(A, B, 7));
        aristas.add(new Arista(B, C, 1));
        aristas.add(new Arista(B, D, 2));
        aristas.add(new Arista(C, E, 2));
        aristas.add(new Arista(D, F, 3));
        aristas.add(new Arista(F, G, 1));
        aristas.add(new Arista(E, H, 10));

        Assert.assertEquals(new ArbolRecubridorMinimo(aristas, 26), g.prim(A));

        Integer[][] matrizAdy = {
                {null, 4,    null, null, null, null, null, 8,    null},    // 0
                {4,    null, 8,    null, null, null, null, 11,   null},    // 1
                {null, 8,    null, 7,    null, 4,    null, null, 2   },    // 2
                {null, null, 7,    null, 9,    14,   null, null, null},    // 3
                {null, null, null, 9,    null, 10,   null, null, null},    // 4
                {null, null, 4,    14,   10,   null, 2,    null, null},    // 5
                {null, null, null, null, null, 2,    null, 1,    6   },    // 6
                {8,    11,   null, null, null, null, 1,    null, 7   },    // 7
                {null, null, 2,    null, null, null, 6,    7,    null}     // 8
        };      // 0     1     2     3     4     5     6     7     8

        g = new Grafo(matrizAdy);

        aristas = new ArrayList<Arista>();
        aristas.add(new Arista(6, 7, 1));
        aristas.add(new Arista(6, 5, 2));
        aristas.add(new Arista(5, 2, 4));
        aristas.add(new Arista(2, 8, 2));
        aristas.add(new Arista(2, 3, 7));
        aristas.add(new Arista(7, 0, 8));
        aristas.add(new Arista(0, 1, 4));
        aristas.add(new Arista(3, 4, 9));
        Assert.assertEquals(new ArbolRecubridorMinimo(aristas, 37), g.prim(6));
    }

}
