package grafo;

import java.util.ArrayList;

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
     * Obtener la ponderancia de una arista entre dos vértices
     * @param v - Primer vértice
     * @param w - Segundo vértice
     * @return Ponderancia
     */
    public Integer obtenerArista(int v, int w) {
        return matrizAdy[v][w];
    }
    
    /**
     * Obtener vecinos de un vértice
     * @param v - Vértice
     * @return Lista de vecinos
     */
    public ArrayList<Integer> obtenerVecinos(int v) {
        ArrayList<Integer> vecinos = new ArrayList<Integer>();
        for (int i = 0; i < cardinalidad; i++) {
            if (obtenerArista(v, i) != null) {
                vecinos.add(i);
            }
        }
        return vecinos;
    }
    
    /**
     * Depth-First Search
     * Búsqueda en profundidad
     * O(|V|^2)     con matriz de adyacencia
     * O(|V|+|E|)   con lista de adyacencia
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
        for(Integer i : obtenerVecinos(vActual)) {
            if(!visitado[i])
                recorrido.addAll(dfs(i, visitado));
        }
        
        return recorrido;
    }
    
}
