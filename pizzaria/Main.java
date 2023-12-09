package pizzaria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import objetos.Pedido;
import objetos.Pizza;

class Main {
  public static final String TRAVESSAO = "--------------------------";
	public static final String APERTE_ENTER = "Aperte Enter para continuar";
	public static final String DIGITE_CODIGO = "Digite o código do produto.";
	public static final String NAO_ENCONTRADO = "Produto não encontrado.";

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
		HashMap<String, Integer> ingredientesDisponiveis = new HashMap<String, Integer>();
		ingredientesDisponiveis.put("Banana", 0);
		ingredientesDisponiveis.put("Abacaxi", 0);
		ingredientesDisponiveis.put("Jerimum", 0);
		ingredientesDisponiveis.put("Chocolate", 0);
		ingredientesDisponiveis.put("Sorvete de morango", 0);
		ArrayList<Pizza> pizzasDisponiveis = new ArrayList<>();
		LinkedBlockingQueue<Pedido> filaPedidos = new LinkedBlockingQueue<Pedido>();
		String[] nomesDisponiveis = {"Adnan", "Caio", "Gizele", "Natália", "Layla", "Adeval", "Lucas"};
		ArrayList<Pedido> pedidosServidos = new ArrayList<>();

    imprimeEntreTravessao("Bem-vindo à pizzaria!");
    while (true) {
      listarComandos();
      String comando = sc.nextLine();

      if (comando.equals("6")) {
        System.out.println("Até mais ver.");
        break;

      } else if (comando.equals("1")) {
        // Receber um pedido aleatório

				System.out.println("Confirmar criação de pedido aleatório? (s/N)");
				String confirmacao = sc.nextLine();

				if (confirmacao.equals("s") || confirmacao.equals("S")) {
					String[] ingredientes = getNomeAleatorio(ingredientesDisponiveis.keySet().toArray(new String[0]), 5);
					Pizza novaPizza = new Pizza(ingredientes);

					String nomeDoCliente = getNomeAleatorio(nomesDisponiveis, 1)[0];
					Pedido novoPedido = new Pedido(nomeDoCliente, novaPizza);
					filaPedidos.add(novoPedido);

					imprimeEntreTravessao("Pedido aleatório criado: " + novoPedido.toString());
					System.out.println(APERTE_ENTER);
					sc.nextLine();
				}

      } else if (comando.equals("2")) {
        // Olhar pedido atual

				imprimeEntreTravessao("Pedido atual:");
				System.out.println(filaPedidos.peek());

				System.out.println(APERTE_ENTER);
				sc.nextLine();

      } else if (comando.equals("3")) {
        // Preparar pizza

				String[] ingredientesAAdicionar = new String[5];

				for (int i = 0; i < 5; i++) {
					System.out.println("Insira o código do ingrediente a ser adicionado!");
					listarIngredientes(ingredientesDisponiveis.keySet().toArray(new String[0]));
					int escolhido = Integer.parseInt(sc.nextLine());
					ingredientesAAdicionar[i] = ingredientesDisponiveis.keySet().toArray(new String[0])[escolhido - 1];
				}

				pizzasDisponiveis.add(new Pizza(ingredientesAAdicionar));

				imprimeEntreTravessao("Pizza criada com sucesso!");
				System.out.println(APERTE_ENTER);
				sc.nextLine();

      } else if (comando.equals("4")) {
        // Servir pedido

				if (!filaPedidos.isEmpty()) {
					Pedido pedidoAServir = filaPedidos.peek();
					System.out.println("Próximo pedido: " + pedidoAServir.toString());
					System.out.println("Lista de pizzas compativeis para servir:");
					listarPizzasComCompatibilidades(pedidoAServir, pizzasDisponiveis);
					System.out.println("Digite o código:");

					int escolhido = Integer.parseInt(sc.nextLine());
					pizzasDisponiveis.remove(escolhido - 1);
					Pedido servido = filaPedidos.poll();
					pedidosServidos.add(servido);

				} else {
					System.out.println("Não há nenhum pedido na fila!");
				}

				System.out.println(APERTE_ENTER);
				sc.nextLine();

      } else if (comando.equals("5")) {
				// Estatísticas dos pedidos

				System.out.println(TRAVESSAO);

				System.out.println("Quantidade de pizzas servidas: ");
				int quantidadeDePedidos = pedidosServidos.size();
				if (quantidadeDePedidos == 0) {
					System.out.println("Nenhuma pizza pedida ainda");
				} else {
					if (quantidadeDePedidos == 1) {
						System.out.println(quantidadeDePedidos + " pizza foi servida.");
					} else {
						System.out.println(quantidadeDePedidos + " pizzas foram servidas.");
					}
					
				}

				System.out.println(TRAVESSAO);

//				double mediaDeIngredientes = getMediaDeIngredientes(pedidosServidos, pedidos);
//				System.out.printf("Média de sabores de pizza: %.2f%n", mediaDeIngredientes);

//				Entry<String, Integer> saborMaisPedido = ingredientesDisponiveis.getMaisUsado();
//				System.out.println("Sabor de pizza mais pedido: " + saborMaisPedido.getKey() + ". Quantidade de pedidos: " + saborMaisPedido.getValue());
				
//				String saboresNaoPedidos = ingredientesDisponiveis.getNaoUsados();
//				System.out.println("Sabores não pedidos: " + saboresNaoPedidos);

				System.out.println(TRAVESSAO);

				System.out.println(APERTE_ENTER);
				sc.nextLine();
      }
    }

    sc.close();
  }

	public static void listarComandos() {
		System.out.println("Lista dos comandos: ");
		System.out.println("1. Receber um pedido aleatório;");
		System.out.println("2. Olhar pedido atual;");
		System.out.println("3. Preparar pizza");
		System.out.println("4. Servir pedido;");
		System.out.println("5. Estatística dos pedidos;");
		System.out.println("6. Sair.");
		System.out.println(TRAVESSAO);
		System.out.println("Digite o código do comando: ");
	}

	public static void listarIngredientes(String[] ingredientes) {
		for (int i = 0; i < ingredientes.length; i++) {
			System.out.println(i + 1 + ". " + ingredientes[i]);
		}
	}

	public static void listarPizzasComCompatibilidades(Pedido pedidoAServir, List<Pizza> pizzasDisponiveis) {
		for (int i = 0; i < pizzasDisponiveis.size(); i++) {
			int compatibilidade = pedidoAServir.contadorRepeticoes(pizzasDisponiveis.get(i).getSabores());

			if (compatibilidade >= 3) {
				System.out.println("Cód.: " + i+1 + "| Pizza de " + pizzasDisponiveis.get(i).toString());
				System.out.println("Compatibilidade de " + compatibilidade + "/5");
			}
		}
	}

	public static void imprimeEntreTravessao(String imprimir) {
		System.out.println(TRAVESSAO);
		System.out.println(imprimir);
		System.out.println(TRAVESSAO);
	}

  public static int lerNumeroNaoNegativo(Scanner sc) {
		int numero;

		do {
			numero = Integer.parseInt(sc.nextLine());
			if (numero < 0) {
				System.out.println("O número não pode ser negativo. Digite novamente:");
			}

		} while (numero < 0);
		return numero;
	}

	public static String[] getNomeAleatorio(String[] entrada, int quantidade) {
		String[] saida = new String[5];

		for (int i = 0; i < quantidade; i++) {
			int randomIndex = new Random().nextInt(entrada.length);
			saida[i] = entrada[randomIndex];
		}

		return saida;
	}

}
