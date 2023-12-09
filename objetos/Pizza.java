package objetos;

public class Pizza {
  String[] ingredientes = new String[5];

  public Pizza(String[] ingredientes) {
    this.ingredientes = ingredientes;
  }

  @Override
  public String toString() {
    return ingredientes[0] + ", " + ingredientes[1] + ", " + ingredientes[2] + ", " + ingredientes[3] + " e " + ingredientes[4] + ".";
  }

  public String[] getSabores() {
    return this.ingredientes;
  }

}
