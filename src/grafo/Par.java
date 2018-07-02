package grafo;

public class Par implements Comparable<Par> {
    
    private Integer vertice;
    private Integer longitudCamino;
    
    public Par(int vertice, int longitudCamino) {
        this.vertice = vertice;
        this.longitudCamino = longitudCamino;
    }
    
    @Override
    public int compareTo(Par otro) {
        return this.longitudCamino.compareTo(otro.longitudCamino);
    }

    public Integer getVertice() {
        return vertice;
    }

    public void setVertice(Integer vertice) {
        this.vertice = vertice;
    }
    
    public Integer getLongitudCamino() {
        return longitudCamino;
    }

    public void setLongitudCamino(Integer longitudCamino) {
        this.longitudCamino = longitudCamino;
    }
}
