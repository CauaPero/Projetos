public abstract class Entrada {
    private int numeroDoAssento;
    protected double precoEspetaculo;

    public Entrada(int numeroDoAssento, double precoEspetaculo) {
        this.numeroDoAssento = numeroDoAssento;
        this.precoEspetaculo = precoEspetaculo;
    }

    public int getNumeroDoAssento() {
        return numeroDoAssento;
    }

    public abstract double calculaValor();
}