import java.util.ArrayList;

public class Teatro {
    private Pedido carrinho;
    private Espetaculo espetaculoSelecionado;

    public Teatro() {
        this.carrinho = null;
        this.espetaculoSelecionado = null;
    }

    public void novaCompra() {
        this.carrinho = new Pedido();
        this.espetaculoSelecionado = null;
    }

    public void apresentaEspetaculo(ArrayList<Espetaculo> listaEspetaculos) {
        if (listaEspetaculos.size() == 0) {
            System.out.println("Nenhum espetáculo cadastrado.");
            return;
        }

        for (int i = 0; i < listaEspetaculos.size(); i++) {
            System.out.println((i + 1) + ") " + listaEspetaculos.get(i).toString());
        }
    }

    public void selecionaEspetaculo(Espetaculo espetaculo) {
        this.espetaculoSelecionado = espetaculo;
        System.out.println("Espetáculo selecionado.");
    }

    public void novaEntrada(int tipo, int assento) {
        if (espetaculoSelecionado == null) {
            System.out.println("Nenhum espetáculo selecionado.");
            return;
        }

        Entrada novaEntrada = espetaculoSelecionado.novaEntrada(tipo, assento);

        if (novaEntrada != null && carrinho != null) {
            carrinho.adicionaEntrada(novaEntrada);
        }
    }

    public double finalizaCompra(String cpf, ArrayList<Cliente> listaClientes) {
        if (carrinho == null || carrinho.getEntradas().size() == 0) {
            System.out.println("O carrinho está vazio ou a compra não foi iniciada.");
            return 0.0;
        }

        if (cpf.isEmpty()) {
            System.out.println("O CPF para finalizar a compra não pode ser vazio.");
            return 0.0;
        }

        Cliente clienteEncontrado = null;

        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            if (cliente.getCpf().equals(cpf)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado == null) {
            System.out.println("Cliente com CPF " + cpf + " não encontrado na base de dados. Finalização cancelada.");
            return 0.0;
        }

        double valorTotal = carrinho.calculaValorTotal();
        clienteEncontrado.adicionaPedido(carrinho);

        this.carrinho = null;
        this.espetaculoSelecionado = null;

        return valorTotal;
    }
}