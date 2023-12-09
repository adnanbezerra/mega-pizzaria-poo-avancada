package objetos;

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

}
