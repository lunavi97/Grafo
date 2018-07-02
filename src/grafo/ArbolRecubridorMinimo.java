package grafo;

import java.util.ArrayList;

public class ArbolRecubridorMinimo {

    private ArrayList<Arista> arbol;
    private Integer longitud;

    public ArbolRecubridorMinimo() {
        arbol = new ArrayList<Arista>();
        longitud = 0;
    }

    public ArbolRecubridorMinimo(ArrayList<Arista> arbol, int longitud) {
        this.arbol = arbol;
        this.longitud = longitud;
    }

    public ArrayList<Arista> getArbol() {
        return arbol;
    }

    public void setArbol(ArrayList<Arista> arbol) {
        this.arbol = arbol;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((arbol == null) ? 0 : arbol.hashCode());
        result = prime * result + ((longitud == null) ? 0 : longitud.hashCode());
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
        ArbolRecubridorMinimo other = (ArbolRecubridorMinimo) obj;
        if (arbol == null) {
            if (other.arbol != null)
                return false;
        } else if (!arbol.equals(other.arbol))
            return false;
        if (longitud == null) {
            if (other.longitud != null)
                return false;
        } else if (!longitud.equals(other.longitud))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[arbol=" + arbol + ", longitud=" + longitud + "]";
    }

}
