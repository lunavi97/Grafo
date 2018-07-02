package grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Grafo {

    private Integer[][] matrizAdy;  // Matriz de adyacencia
    private int cardinalidad;       // Cardinalidad

    /**
     * Constructor de grafo con adyacencia nula y cardinalidad recibida como parámetro
     * @param card - cardinalidad
     */
    public Grafo(int card) {
        cardinalidad = card;
        matrizAdy = new Integer[card][card];
    }

    /**
     * Constructor de grafo con matriz recibida como parámetro
     * @param mat - Matriz de adyacencia
     */
    public Grafo(Integer[][] mat) {
        matrizAdy = mat;
        cardinalidad = mat.length;
    }

    /**
     * Crear una arista con ponderancia 1
     * @param v - Primer vértice
     * @param w - Segundo vértice
     */
    public void agregarArista(int v, int w) {
        matrizAdy[v][w] = 1;
    }

    /**
     * Crear una arista
     * @param v - Primer vértice
     * @param w - Segundo vértice
     * @param pond - Ponderancia
     */
    public void agregarArista(int v, int w, int pond) {
        matrizAdy[v][w] = pond;
    }

    /**
     * Crear una arista no dirigida con ponderancia 1
     * @param v - Primer vértice
     * @param w - Segundo Vértice
     */
    public void agregarAristaNoDirigida(int v, int w) {
        matrizAdy[v][w] = 1;
        matrizAdy[w][v] = 1;
    }

    /**
     * Crear una arista no dirigida
     * @param v - Primer vértice
     * @param w - Segundo vértice
     * @param pond - Ponderancia
     */
    public void agregarAristaNoDirigida(int v, int w, int pond) {
        matrizAdy[v][w] = pond;
        matrizAdy[w][v] = pond;
    }

    /**
     * Obtener la ponderancia de una arista entre dos vértices
     * O(1)
     * @param v - Vértice origen
     * @param w - Vértice destino
     * @return Ponderancia si existe, sino null
     */
    public Integer obtenerPonderanciaArista(int v, int w) {
        return matrizAdy[v][w];
    }

    /**
     * Obtener vértices adyacentes
     * O(|V|)
     * @param v - Vértice
     * @return Lista de vértices adyacentes
     */
    public ArrayList<Integer> obtenerAdyacentes(int v) {
        ArrayList<Integer> vecinos = new ArrayList<Integer>();
        for (int i = 0; i < cardinalidad; i++) {
            if (obtenerPonderanciaArista(v, i) != null)
                vecinos.add(i);
        }
        return vecinos;
    }

    /**
     * Depth-First Search
     * Búsqueda en profundidad
     * O(|V|^2)     con matriz de adyacencia
     * O(|V|+|A|)   con lista de adyacencia
     * @param ini - Vértice inicial
     * @return Recorrido de vértices visitados
     */
    public ArrayList<Integer> dfs(int ini) {
        boolean[] visitado = new boolean[cardinalidad];
        return dfs(ini, visitado);
    }
    private ArrayList<Integer> dfs(int vActual, boolean[] visitado) {
        ArrayList<Integer> recorrido = new ArrayList<Integer>();

        visitado[vActual] = true;
        recorrido.add(vActual);
        for (Integer i : obtenerAdyacentes(vActual)) {
            if (!visitado[i]) recorrido.addAll(dfs(i, visitado));
        }

        return recorrido;
    }

    /**
     * Breadth-First Search
     * Búsqueda en anchura
     * O(|V|^2)     con matriz de adyacencia
     * O(|V|+|A|)   con lista de adyacencia
     * @param ini - Vértice inicial
     * @return Recorrido de vértices visitados
     */
    public ArrayList<Integer> bfs(int ini) {
        ArrayList<Integer> recorrido = new ArrayList<Integer>();
        boolean[] visitado = new boolean[cardinalidad];
        LinkedList<Integer> cola = new LinkedList<Integer>();

        cola.offer(ini);
        visitado[ini] = true;

        while (!cola.isEmpty()) {
            Integer vActual = cola.poll();
            recorrido.add(vActual);
            for (Integer i : obtenerAdyacentes(vActual)) {
                if (!visitado[i]) {
                    cola.offer(i);
                    visitado[i] = true;
                }
            }
        }

        return recorrido;
    }


    /**
     * Reducir el costo de arista
     * @param distancia - Vector de distancias
     * @param predecesor - Vector de predecesores
     * @param v - Vértice origen
     * @param w - Vértice destino
     */
    private void relajarArista(Integer[] distancia, Integer[] predecesor, int v, int w) {
        int longitud = obtenerPonderanciaArista(v, w);
        Integer valor = distancia[w];
        if (valor == null || valor > distancia[v] + longitud) {
            distancia[w] = distancia[v] + longitud;
            predecesor[w] = v;
        }
    }

    /**
     * Dijkstra
     * Para encontrar el camino más corto
     * O((|V|+|A|)log|V|)
     * @param origen - Vértice origen
     * @return Camino más corto desde el origen
     */
    public CaminoMasCorto dijkstra(int origen) {
        Integer[] distancia = new Integer[cardinalidad];
        Integer[] predecesor = new Integer[cardinalidad];
        distancia[origen] = 0;
        PriorityQueue<Par> pq = new PriorityQueue<Par>();

        pq.offer(new Par(origen, 0));
        while (!pq.isEmpty()) {
            Par actual = pq.poll();
            int vertice = actual.getVertice();
            int longitudCamino = actual.getLongitudCamino();

            if (longitudCamino == distancia[vertice]) {
                for (Integer i : obtenerAdyacentes(vertice)) {
                    relajarArista(distancia, predecesor, vertice, i);
                    pq.offer(new Par(i, distancia[i]));
                }
            }
        }

        return new CaminoMasCorto(distancia, predecesor);
    }

    /**
     * Recorrido del camino más corto del origen al destino, obtenido a través de Dijkstra
     * O((|V|+|A|)log|V|)
     * @param origen - Vértice origen
     * @param destino - Vértice destino
     * @return Recorrido del camino más corto
     */
    public ArrayList<Integer> recorridoDijkstra(int origen, int destino) {
        return dijkstra(origen).obtenerRecorrido(destino);
    }

    /**
     * Extraer todas las aristas de la matriz de adyacencia
     * @param esDirigido - Si el grafo es dirigido
     * @return Lista de aristas
     */
    public ArrayList<Arista> obtenerAristas(boolean esDirigido) {
        ArrayList<Arista> aristas = new ArrayList<Arista>();

        for (int i = 0; i < cardinalidad; i++) {
            for (int j = 0; j < cardinalidad; j++) {
                if (matrizAdy[i][j] != null) {
                    if (!esDirigido && j <= i) continue;
                    aristas.add(new Arista(i, j, matrizAdy[i][j]));
                }
            }
        }

        return aristas;
    }

    private void mergeSets(ArrayList<HashSet<Integer>> sets, 
            HashSet<Integer> ver1Set, HashSet<Integer> ver2Set) {

        ver1Set.addAll(ver2Set);
        for (Integer vertice: ver1Set) {
            sets.set(vertice, ver1Set);
        }

    }

    /**
     * Kruskal
     * Para encontrar el árbol recubridor mínimo
     * O(|A|log|V|)
     * @return Árbol recubridor mínimo
     */
    public ArbolRecubridorMinimo kruskal() {
        ArbolRecubridorMinimo arm = new ArbolRecubridorMinimo();
        ArrayList<Arista> aristas = obtenerAristas(false); // Grafo no dirigido
        PriorityQueue<Arista> pq = new PriorityQueue<Arista>(aristas);
        ArrayList<HashSet<Integer>> sets = new ArrayList<HashSet<Integer>>(cardinalidad);

        for (int i = 0; i < cardinalidad; i++) {
            HashSet<Integer> componentSet = new HashSet<Integer>();
            componentSet.add(i);
            sets.add(componentSet);
        }

        while (!pq.isEmpty()) {
            Arista arista = pq.poll();
            int ver1 = arista.getVer1();
            int ver2 = arista.getVer2();
            int pond = arista.getPonderancia();

            HashSet<Integer> ver1Set = sets.get(ver1);
            HashSet<Integer> ver2Set = sets.get(ver2);
            if (Collections.disjoint(ver1Set, ver2Set)) {
                mergeSets(sets, ver1Set, ver2Set);
                arm.getArbol().add(arista);
                arm.setLongitud(arm.getLongitud() + pond);
            }
        }

        return arm;
    }

}
