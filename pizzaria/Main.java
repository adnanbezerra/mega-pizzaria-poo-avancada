package pizzaria;

import java.util.Scanner;

class Main {
  public static final String TRAVESSAO = "--------------------------";
	public static final String APERTE_ENTER = "Aperte Enter para continuar";
	public static final String DIGITE_CODIGO = "Digite o código do produto.";
	public static final String NAO_ENCONTRADO = "Produto não encontrado.";

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    imprimeEntreTravessao("Bem-vindo à pizzaria!");
    while (true) {
      listarComandos();
      String comando = sc.nextLine();

      if (comando.equals("6")) {
        System.out.println("Até mais ver.");
        break;

      } else if (comando.equals("1")) {
        // TODO - Receber um pedido aleatório
      } else if (comando.equals("2")) {
        // TODO - Olhar pedido atual
      } else if (comando.equals("3")) {
        // TODO - Preparar pizza
      } else if (comando.equals("4")) {
        // TODO - Servir pedido
      } else if (comando.equals("5")) {
        // TODO - Estatística dos pedidos
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

}
