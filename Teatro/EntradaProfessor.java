public class EntradaProfessor extends Entrada {
    public EntradaProfessor(int numeroDoAssento, double precoEspetaculo) {
        super(numeroDoAssento, precoEspetaculo);
    }

    @Override
    public double calculaValor() {
        return precoEspetaculo * 0.4;
    }
}