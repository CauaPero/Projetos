public class Espetaculo {
    private String nome;
    private String data;
    private String hora;
    private double preco;
    private boolean[] assentos = new boolean[50];


    public Espetaculo(String nome, String data, String hora, double preco) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.preco = preco;

        for (int i = 0; i < assentos.length; i++) {
            assentos[i] = false;
        }
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s R$ %.2f", nome, data, hora, preco);
    }

    public void apresentaAssentos() {
        System.out.println(" --- Assentos disponíveis --- ");

        for (int i = 4; i >= 0; i--) {
            for (int j = 9; j >= 0; j--) {
                int assentoNumero = i * 10 + j + 1;
                int indice = assentoNumero - 1;

                if (assentos[indice]) {
                    System.out.print("XX ");
                }
                else {
                    System.out.printf("%02d ", assentoNumero);
                }
            }
            System.out.println();
        }
    }

    public boolean isAssentoDisponivel(int assento) {
        int indice = assento - 1;

        if (this.assentos[indice] == false) {
            return true;
        }
        else {
            return false;
        }
    }

    public Entrada novaEntrada(int tipo, int assento) {
        if (assento < 1 || assento > 50) {
            System.out.println("Assento inválido (1-50).");
            return null;
        }
        if (assentos[assento - 1]) {
            System.out.println("Assento ocupado. Escolha outro.");
            return null;
        }

        Entrada entrada = null;

        switch (tipo) {
            case 1:
                entrada = new EntradaInteira(assento, this.preco);
                break;
            case 2:
                entrada = new EntradaMeia(assento, this.preco);
                break;
            case 3:
                entrada = new EntradaProfessor(assento, this.preco);
                break;
            default:
                System.out.println("Tipo inválido. Tipos válidos: 1 (Inteira), 2 (Meia), 3 (Professor).");
                return null;
        }

        marcarAssento(assento - 1);

        double valor = entrada.calculaValor();
        System.out.println("Entrada para o assento " + assento + " criada. Valor: R$" + String.format("%.2f", valor));
        return entrada;
    }

    private void marcarAssento(int indiceAssento) {
        assentos[indiceAssento] = true;
    }
}