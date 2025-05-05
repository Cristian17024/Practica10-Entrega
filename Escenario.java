import java.util.*;

public class Escenario {
    private String nombre;                   
    private ArrayList<Elemento> elementos;  
    private final int TAM = 10;              

    
    public Escenario(String nombre) {
        this.nombre = nombre;
        elementos = new ArrayList<>();  
    }

    public void addElemento(Elemento e) {
        elementos.add(e);
    }

    public String toString() {
        String[][] matriz = new String[TAM][TAM];  
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                matriz[i][j] = "0";
            }
        }

       
        for (Elemento e : elementos) {
            int r = e.getPosicion().getRenglon();
            int c = e.getPosicion().getColumna();
            matriz[r][c] = e.toString();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                sb.append(matriz[i][j]);
            }
            sb.append("\n");
        }

        return sb.toString(); 
    }

    public void destruirElementos(Posicion centro, int radio) {
        ArrayList<Elemento> afectados = new ArrayList<>();

        for (Elemento e : elementos) {
            Posicion p = e.getPosicion();

            int dr = Math.abs(p.getRenglon() - centro.getRenglon());
            int dc = Math.abs(p.getColumna() - centro.getColumna());

            if (dr <= radio && dc <= radio) {
                afectados.add(e);
            }
        }

        for (Elemento e : afectados) {
            if (e instanceof Destruible) {
                Destruible d = (Destruible) e;  
                System.out.println(d.destruir());  
            }
        }
    }
}
