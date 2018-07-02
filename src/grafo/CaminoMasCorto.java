package grafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CaminoMasCorto {

    private Integer[] distancia;
    private Integer[] predecesor;

    public CaminoMasCorto(Integer[] distancia, Integer[] predecesor) {
        this.distancia = distancia;
        this.predecesor = predecesor;
    }

    public Integer[] getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer[] distancia) {
        this.distancia = distancia;
    }

    public Integer getDistancia(int pos) {
        return distancia[pos];
    }

    public Integer[] getPredecesor() {
        return predecesor;
    }

    public Integer getPredecesor(int pos) {
        return predecesor[pos];
    }

    public void setPredecesor(Integer[] predecesor) {
        this.predecesor = predecesor;
    }

    /**
     * Recorrido hasta destino
     * O(|V|)
     * @param destino - Vértice destino
     * @return Recorrido
     */
    public ArrayList<Integer> obtenerRecorrido(int destino) {
        ArrayList<Integer> camino = new ArrayList<Integer>();

        camino.add(destino);
        Integer predecesor = getPredecesor(destino);
        while(predecesor != null) {
            camino.add(predecesor);
            predecesor = getPredecesor(predecesor);
        }

        Collections.reverse(camino);

        return camino;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(distancia);
        result = prime * result + Arrays.hashCode(predecesor);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CaminoMasCorto other = (CaminoMasCorto) obj;
        if (!Arrays.equals(distancia, other.distancia))
            return false;
        if (!Arrays.equals(predecesor, other.predecesor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[distancia=" + Arrays.toString(distancia) + ","
                + "predecesor=" + Arrays.toString(predecesor) + "]";
    }

}
