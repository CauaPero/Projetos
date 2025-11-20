import java.util.ArrayList;

public class Pedido {
    private ArrayList<Entrada> entradas;

    public Pedido() {
        this.entradas = new ArrayList<Entrada>();
    }

    public void adicionaEntrada(Entrada entrada) {
        if (entrada != null) {
            this.entradas.add(entrada);
        }
    }

    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }

    public double calculaValorTotal() {
        double total = 0.0;
        for (int i = 0; i < this.entradas.size(); i++) {
            total += this.entradas.get(i).calculaValor();
        }
        return total;
    }
}