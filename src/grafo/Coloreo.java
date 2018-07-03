package grafo;

import java.util.ArrayList;
import java.util.Arrays;

public class Coloreo {

    private Color[] colorV;

    private ArrayList<Color> coloresUsados;

    public Coloreo(Color[] colorV) {
        this.setColorV(colorV);
        coloresUsados = new ArrayList<Color>();
        boolean esColorGuardado;

        for (int i = 0; i < colorV.length; i++) {
            esColorGuardado = false;

            for (int j = 0; j < coloresUsados.size(); j++) {
                if(colorV[i].equals(coloresUsados.get(j))) {
                    esColorGuardado = true;
                    break;
                }
            }

            if (!esColorGuardado) coloresUsados.add(colorV[i]);
        }
    }

    public Coloreo(Coloreo coloreo) {
        this(coloreo.getColorV().clone());
    }

    public Color[] getColorV() {
        return colorV;
    }

    public void setColorV(Color[] colorV) {
        this.colorV = colorV;
    }

    public int numeroCromatico() {
        return coloresUsados.size();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(colorV);
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
        Coloreo other = (Coloreo) obj;
        if (!Arrays.equals(colorV, other.colorV))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Coloreo [colorV=" + Arrays.toString(colorV) + ", numeroCromatico()=" + numeroCromatico() + "]";
    }
}
