public class EntradaMeia extends Entrada {
    public EntradaMeia(int numeroDoAssento, double precoEspetaculo) {
        super(numeroDoAssento, precoEspetaculo);
    }

    @Override
    public double calculaValor() {
        return precoEspetaculo / 2;
    }
}