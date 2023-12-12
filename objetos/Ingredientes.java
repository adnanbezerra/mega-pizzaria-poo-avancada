package objetos;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Ingredientes {
  private HashMap<String, Integer> ingredientes;

  public Ingredientes() {
    ingredientes = new HashMap<>();
    ingredientes.put("Banana", 0);
		ingredientes.put("Abacaxi", 0);
		ingredientes.put("Jerimum", 0);
		ingredientes.put("Chocolate", 0);
		ingredientes.put("Sorvete de morango", 0);
  }

  public String[] toArray() {
    return ingredientes.keySet().toArray(new String[0]);
  }

  public void consumirIngredientes(Pedido pedidoServido) {
    String[] ingredientesUtilizados = pedidoServido.getPizza().getIngredientes();

    for (String ingrediente : ingredientesUtilizados) {
      int valorAntigo = ingredientes.get(ingrediente);
      int valorNovo = valorAntigo + 1;
      ingredientes.put(ingrediente, valorNovo);
    }
  }

  public Entry<String, Integer> getIngredienteMaisPedido() {
    int contador = Integer.MIN_VALUE;
    Entry<String, Integer> maisUsado = new AbstractMap.SimpleEntry<String, Integer>("", 0);

    for (var each : this.ingredientes.entrySet()) {
      if (each.getValue() > contador) {
        contador = each.getValue();
        maisUsado = each;
      }
    }

    return maisUsado;
  }

  public String getNaoUsados() {
    ArrayList<String> naoUsados = new ArrayList<>();

    for (var each : this.ingredientes.entrySet()) {
      if (each.getValue() == 0) {
        naoUsados.add(each.getKey());
      }
    }

    String result = naoUsados.stream().collect(Collectors.joining(", "));
    
    return result.substring(0, 1).toUpperCase() + result.substring(1);
  }

}
