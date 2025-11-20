public class EntradaInteira extends Entrada {
    public EntradaInteira(int numeroDoAssento, double precoEspetaculo) {
        super(numeroDoAssento, precoEspetaculo);
    }

    @Override
    public double calculaValor() {
        return precoEspetaculo;
    }
}