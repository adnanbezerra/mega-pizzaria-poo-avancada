package objetos;

import java.util.HashMap;
import java.util.Map;

public class Pedido {
  private String nomeDoCliente;
  private Pizza pizza;
  private int pedidoId;
  private static int pedidos = 1;

  public Pedido(String nomeDoCliente, Pizza pizza) {
    this.nomeDoCliente = nomeDoCliente;
    this.pizza = pizza;

    this.pedidoId = pedidos;
    pedidos++;
  }

  @Override
  public String toString() {
    return "Cód.: " + pedidoId + ". Nome do cliente: " + nomeDoCliente + "\nSabores: " + pizza.toString();
  }

  public String getNomeDoCliente() {
    return nomeDoCliente;
  }

  public int getPedidoId() {
    return pedidoId;
  }

  public Pizza getPizza() {
    return this.pizza;
  }

  public int contadorRepeticoes(String[] saboresDoPedidoAServir) {
    Map<String, Integer> contadorArray1 = contarElementos(saboresDoPedidoAServir);
    Map<String, Integer> contadorArray2 = contarElementos(pizza.getIngredientes());

    int totalRepeticoes = contarRepeticoes(contadorArray1, contadorArray2);

    return totalRepeticoes;
  }

  private static Map<String, Integer> contarElementos(String[] array) {
        Map<String, Integer> contador = new HashMap<>();

        for (String elemento : array) {
            contador.put(elemento, contador.getOrDefault(elemento, 0) + 1);
        }

        return contador;
  }

  private static int contarRepeticoes(Map<String, Integer> contador1, Map<String, Integer> contador2) {
    int totalRepeticoes = 0;

    for (Map.Entry<String, Integer> entry : contador1.entrySet()) {
        String elemento = entry.getKey();
        int ocorrenciasArray1 = entry.getValue();
        int ocorrenciasArray2 = contador2.getOrDefault(elemento, 0);

        totalRepeticoes += Math.min(ocorrenciasArray1, ocorrenciasArray2);
    }

    return totalRepeticoes;
}

}
