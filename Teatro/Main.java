import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static Scanner entrada = new Scanner(System.in);
    public static ArrayList<Espetaculo> listaEspetaculos = new ArrayList<Espetaculo>();
    public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
    public static Teatro teatro = new Teatro();

    private static int parseSafe(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static boolean isIntegerValido(String str) {
        if (str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char charac = str.charAt(i);

            if (charac < '0' || charac > '9') {
                return false;
            }
        }
        return true;
    }

    private static boolean isDoubleValido(String str) {
        if (str.isEmpty()) {
            return false;
        }
        int pontoDecimal = 0;
        for (int i = 0; i < str.length(); i++) {
            char charac = str.charAt(i);


            if (charac == '.') {
                pontoDecimal++;
            }
            else if (charac < '0' || charac > '9') {
                return false;
            }
        }
        return pontoDecimal <= 1 && str.length() > 0 && !str.equals(".");
    }

    private static boolean isHoraValida(String hora) {
        if (hora.length() != 5) {
            return false;
        }

        char sep = hora.charAt(2);
        if (sep != ':' && sep != 'h') {
            return false;
        }

        String horasStr = hora.substring(0, 2);
        String minutosStr = hora.substring(3, 5);

        if (!isIntegerValido(horasStr) || !isIntegerValido(minutosStr)) {
            return false;
        }

        int horas = parseSafe(horasStr);
        int minutos = parseSafe(minutosStr);

        if (horas < 0 || horas > 23) {
            return false;
        }
        if (minutos < 0 || minutos > 59) {
            return false;
        }

        return true;
    }

    private static boolean isDataValida(String data) {
        if (data.length() != 10) {
            return false;
        }

        if (data.charAt(2) != '/' || data.charAt(5) != '/') {
            return false;
        }

        String diaStr = data.substring(0, 2);
        String mesStr = data.substring(3, 5);
        String anoStr = data.substring(6, 10);

        if (!isIntegerValido(diaStr) || !isIntegerValido(mesStr) || !isIntegerValido(anoStr)) {
            return false;
        }

        int dia = parseSafe(diaStr);
        int mes = parseSafe(mesStr);

        if (mes < 1 || mes > 12) {
            return false;
        }
        if (dia < 1 || dia > 31) {
            return false;
        }

        return true;
    }

    private static boolean isNomeValido(String str) {
        if (str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char charac = str.charAt(i);

            if (!Character.isLetter(charac) && charac != ' ') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int option = 0;
        do {
            apresentarMenuPrincipal();
            String inputMenu = entrada.nextLine();

            if (isIntegerValido(inputMenu)) {
                option = Integer.parseInt(inputMenu);
            }
            else {
                System.out.println("Opção inválida. Digite um número de 1 a 4.");
                option = 0;
                continue;
            }

            switch (option) {
                case 1:
                    cadastrarEspetaculo();
                    break;
                case 2:
                    cadastrarCliente();
                    break;
                case 3:
                    comprarEntradas();
                    break;
                case 4:
                    System.out.println("Saindo do sistema. TCHAAAUUU!");
                    break;
                default:
                    System.out.println("Opção inválida. Selecione 1, 2, 3 ou 4.");
                    break;
            }
        } while (option != 4);
    }

    public static void apresentarMenuPrincipal() {
        System.out.println("\n--- MACK THEATHER ---");
        System.out.println("1. Cadastrar Espetáculo");
        System.out.println("2. Cadastrar Cliente");
        System.out.println("3. Compra de Entradas");
        System.out.println("4. Sair");
        System.out.print("Selecione uma opção: ");
    }

    public static void cadastrarEspetaculo() {
        System.out.println("\n--- CADASTRO DE ESPETÁCULO ---");

        System.out.print("Nome do Espetáculo: ");
        String nome = entrada.nextLine();

        if (nome.isEmpty()) {
            System.out.println("O nome do espetáculo é obrigatório. Cadastro cancelado.");
            return;
        }

        System.out.print("Data (formato DD/MM/AAAA): ");
        String data = entrada.nextLine();

        if (!isDataValida(data)) {
            System.out.println("Data inválida. Use o formato DD/MM/AAAA e verifique se o dia (1-31) e o mês (1-12) são válidos. Cadastro cancelado.");
            return;
        }

        System.out.print("Hora (formato HH:MM ou HHhMM): ");
        String hora = entrada.nextLine();

        if (!isHoraValida(hora)) {
            System.out.println("Hora inválida. Use o formato HH:MM (00:00 a 23:59) ou HHhMM. Cadastro cancelado.");
            return;
        }

        double preco = 0.0;
        System.out.print("Preço da Entrada Inteira: ");
        String precoStr = entrada.nextLine();

        if (isDoubleValido(precoStr)) {
            preco = parseSafe(precoStr);
        }
        else {
            System.out.println("Valor de preço inválido (use apenas números e ponto). Cadastro cancelado.");
            return;
        }

        if (preco <= 0) {
            System.out.println("O preço deve ser um valor positivo. Cadastro cancelado.");
            return;
        }

        Espetaculo novoEspetaculo = new Espetaculo(nome, data, hora, preco);
        listaEspetaculos.add(novoEspetaculo);
        System.out.println("Espetaculo cadastrado!");
    }

    public static void cadastrarCliente() {
        System.out.println("\n--- CADASTRO DE CLIENTE ---");
        String nomeCli = "";

        while (!isNomeValido(nomeCli)) {
            System.out.print("Digite seu nome: ");
            nomeCli = entrada.nextLine();

            if (!isNomeValido(nomeCli)) {
                System.out.println("Nome inválido! Digite um nome com letras.");
            }
        }

        System.out.print("CPF: ");
        String cpf = entrada.nextLine();

        if (cpf.isEmpty()) {
            System.out.println("CPF é obrigatório. Cadastro cancelado.");
            return;
        }

        if (cpf.length() != 11) {
            System.out.println("O CPF deve conter 11 dígitos. Digite um CPF válido.");
            return;
        }

        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getCpf().equals(cpf)) {
                System.out.println("Já existe um cliente cadastrado com este CPF (" + cpf + ")");
                return;
            }
        }

        Cliente novoCliente = new Cliente(nomeCli, cpf);
        listaClientes.add(novoCliente);

        System.out.println(nomeCli + " cadastrado!");
    }

    public static void comprarEntradas() {
        if (listaEspetaculos.size() == 0) {
            System.out.println("Não há nenhum espetáculo cadastrado. Cadastre um espetáculo antes de tentar comprar entradas.");
            return;
        }

        System.out.println("\n--- VENDA DE ENTRADAS ESPETÁCULOS ---");
        teatro.novaCompra();

        int espetaculoIndex = -1;
        while (espetaculoIndex < 0 || espetaculoIndex >= listaEspetaculos.size()) {
            teatro.apresentaEspetaculo(listaEspetaculos);
            System.out.print("Selecione um espetáculo: ");

            String espetaculoStr = entrada.nextLine();

            if (isIntegerValido(espetaculoStr)) {
                espetaculoIndex = parseSafe(espetaculoStr) - 1;
            } else {
                System.out.println("Seleção inválida. Digite o número do espetáculo.");
                return;
            }

            if (espetaculoIndex < 0 || espetaculoIndex >= listaEspetaculos.size()) {
                System.out.println("Número de espetáculo inválido. Tente novamente.");
            }
        }

        Espetaculo espetaculoSelecionado = listaEspetaculos.get(espetaculoIndex);
        teatro.selecionaEspetaculo(espetaculoSelecionado);

        String comprarMais = "S";
        while (comprarMais.toUpperCase().equals("S")) {
            espetaculoSelecionado.apresentaAssentos();
            int assento = -1;
            int tipo = -1;

            System.out.print("Selecione um assento (numero): ");
            String assentoStr = entrada.nextLine();

            if (isIntegerValido(assentoStr)) {
                assento = parseSafe(assentoStr);
            } else {
                System.out.println("Assento inválido (não numérico). Compra de entradas cancelada.");
                return;
            }

            if (assento < 1 || assento > 50 || !espetaculoSelecionado.isAssentoDisponivel(assento)) {
                System.out.println("Assento inválido ou indisponível. Escolha um assento disponível (1-50) para continuar a compra.");

                continue;
            }

            apresentaTiposDeEntrada();
            System.out.print("Selecione um tipo de entrada (1, 2 ou 3): ");
            String tipoStr = entrada.nextLine();

            if (isIntegerValido(tipoStr)) {
                tipo = parseSafe(tipoStr);
            } else {
                System.out.println("Tipo de entrada inválido, compra de entradas cancelada.");
                return;
            }

            if (tipo < 1 || tipo > 3) {
                System.out.println("Tipo de entrada inválido (somente 1, 2 ou 3). Compra de entradas cancelada.");
                return;
            }

            teatro.novaEntrada(tipo, assento);

            String resp = "";
            boolean entradaValida = false;

            while (!entradaValida) {
                System.out.print("Deseja comprar outra entrada (S/N)? ");
                resp = entrada.nextLine().toUpperCase();

                if (resp.equals("S")) {
                    comprarMais = "S";
                    entradaValida = true;
                } else if (resp.equals("N")) {
                    comprarMais = "N";
                    entradaValida = true;
                } else {
                    System.out.println("Opção inválida. Digite exatamente 'S' (Sim) ou 'N' (Não).");
                }
            }
        }

        System.out.print("\nInforme o CPF do Cliente Cadastrado: ");
        String cpf = entrada.nextLine();

        double valorTotal = teatro.finalizaCompra(cpf, listaClientes);

        if (valorTotal > 0.0) {
            System.out.println("Compra finalizada!");
            System.out.println("Valor Total: R$" + String.format("%.2f", valorTotal));
        }
    }

    public static void apresentaTiposDeEntrada() {
        System.out.println("--- Tipos de Entrada ---");
        System.out.println("1) Inteira");
        System.out.println("2) Meia (50% do valor da entrada)");
        System.out.println("3) Professor (40% do valor da entrada)");
    }
}