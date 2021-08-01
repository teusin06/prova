package telas;

import dados.Repositorio;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import negocio.Model;
import negocio.Produto;

public class ConsultaProduto extends Tela implements TelaExibicaoDados {
  public ConsultaProduto(String paramString, int paramInt) {
    super(paramString, paramInt);
  }
  
  public void exibirDados(Scanner paramScanner, Repositorio<Model> paramRepositorio) {
    System.out.print("Nome do produto a buscar (ENTER para todos): ");
    String str = paramScanner.nextLine();
    List list = (List)paramRepositorio.obterStream().filter(paramModel -> ((Produto)paramModel).getNome().toUpperCase().contains(paramString.toUpperCase())).sorted().collect(Collectors.toList());
    if (list.isEmpty()) {
      System.out.println("\nNenhum produto localizado.");
    } else {
      System.out.println("\nProdutos encontrados:");
      System.out.println("*********************");
      Objects.requireNonNull(System.out);
      list.forEach(System.out::println);
    } 
  }
}
