package grafo;

public class Arista implements Comparable<Arista> {

    private Integer ver1;
    private Integer ver2;
    private Integer ponderancia;

    public Arista(int ver1, int ver2, int ponderancia) {
        this.ver1 = ver1;
        this.ver2 = ver2;
        this.ponderancia = ponderancia;
    }

    public String toString() {
        return "(" + ver1 + "--" + ver2 + ", " + ponderancia + ")";
    }

    @Override
    public int compareTo(Arista otra) {
        return this.ponderancia.compareTo(otra.ponderancia);
    }

    public Integer getVer1() {
        return ver1;
    }

    public void setVer1(Integer ver1) {
        this.ver1 = ver1;
    }

    public Integer getVer2() {
        return ver2;
    }

    public void setVer2(Integer ver2) {
        this.ver2 = ver2;
    }

    public Integer getPonderancia() {
        return ponderancia;
    }

    public void setPonderancia(Integer ponderancia) {
        this.ponderancia = ponderancia;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ponderancia == null) ? 0 : ponderancia.hashCode());
        result = prime * result + ((ver1 == null) ? 0 : ver1.hashCode());
        result = prime * result + ((ver2 == null) ? 0 : ver2.hashCode());
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
        Arista other = (Arista) obj;
        if (ponderancia == null) {
            if (other.ponderancia != null)
                return false;
        } else if (!ponderancia.equals(other.ponderancia))
            return false;
        if (ver1 == null) {
            if (other.ver1 != null)
                return false;
        } else if (!ver1.equals(other.ver1))
            return false;
        if (ver2 == null) {
            if (other.ver2 != null)
                return false;
        } else if (!ver2.equals(other.ver2))
            return false;
        return true;
    }

}
