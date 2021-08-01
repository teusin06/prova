package telas;

import dados.Repositorio;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;
import java.util.stream.Collectors;
import negocio.Model;
import negocio.Produto;

public class RelatorioProduto extends Tela implements TelaExibicaoDados {
  public RelatorioProduto(String paramString, int paramInt) {
    super(paramString, paramInt);
  }
  
  public void exibirDados(Scanner paramScanner, Repositorio<Model> paramRepositorio) {
    if (paramRepositorio.estaVazio()) {
      System.out.println("\nNhdados para emissdo relat");
      return;
    } 
    System.out.printf("\nC\t%30s\t%15s\t%15s\n", new Object[] { "Nome", "Valor (R$)", "Estoque" });
    System.out.println("---------------------------------------------------------------------------");
    paramRepositorio.obterStream().sorted().forEach(paramModel -> {
          Produto produto = (Produto)paramModel;
          System.out.printf("%6d\t%30s\t%15.2f\t%15d\n", new Object[] { Integer.valueOf(produto.getCodigo()), produto.getNome(), Double.valueOf(produto.getValor()), Integer.valueOf(produto.getQuantidadeEstoque()) });
        });
    DoubleSummaryStatistics doubleSummaryStatistics = (DoubleSummaryStatistics)paramRepositorio.obterStream().collect(Collectors.summarizingDouble(paramModel -> ((Produto)paramModel).getValor()));
    System.out.println("---------------------------------------------------------------------------");
    System.out.printf("Maior valor: R$ %.2f\tMenor valor: R$ %.2f\tValor mR$ %.2f\n", new Object[] { Double.valueOf(doubleSummaryStatistics.getMax()), Double.valueOf(doubleSummaryStatistics.getMin()), Double.valueOf(doubleSummaryStatistics.getAverage()) });
  }
}