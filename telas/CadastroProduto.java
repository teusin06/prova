package telas;

import java.util.Scanner;
import negocio.Model;
import negocio.Produto;

public class CadastroProduto extends Tela implements TelaLeituraDados {
  public CadastroProduto(String paramString, int paramInt) {
    super(paramString, paramInt);
  }
  
  public Model lerDados(Scanner paramScanner) {
    Produto produto = new Produto();
    try {
      System.out.print("C(ou um caractere nnumpara sair): ");
      produto.setCodigo(paramScanner.nextInt());
    } catch (Exception exception) {
      return null;
    } 
    paramScanner.nextLine();
    System.out.print("Nome: ");
    produto.setNome(paramScanner.nextLine());
    System.out.print("Valor: ");
    produto.setValor(paramScanner.nextDouble());
    System.out.print("Quantidade em estoque: ");
    int i = paramScanner.nextInt();
    paramScanner.nextLine();
    if (i < 0) {
      System.out.println("\nO estoque npode ser negativo. O valor foi substitupor 0.\nPressione ENTER para continuar...");
      paramScanner.nextLine();
      i = 0;
    } 
    produto.setQuantidadeEstoque(i);
    return (Model)produto;
  }
}
