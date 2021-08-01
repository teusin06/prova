package telas;

import app.ControladorInput;
import dados.Repositorio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import negocio.Model;
import negocio.Venda;

public class RelatorioVendaDetalhado extends Tela implements TelaExibicaoDados {
  protected final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  
  public RelatorioVendaDetalhado(String paramString, int paramInt) {
    super(paramString, paramInt);
  }
  
  public void exibirDados(Scanner paramScanner, Repositorio<Model> paramRepositorio) {
    LocalDate[] arrayOfLocalDate = lerDatas(paramScanner);
    List<Model> list = (List)paramRepositorio.obterStream().filter(paramModel -> {
          Venda venda = (Venda)paramModel;
          return (venda.getData().compareTo(paramArrayOfLocalDate[0]) > -1 && venda.getData().compareTo(paramArrayOfLocalDate[1]) < 1);
        }).sorted(Comparator.comparing(paramModel -> ((Venda)paramModel).getData())).collect(Collectors.toList());
    if (list.isEmpty()) {
      System.out.println("\nNhdados para emissdo relat");
      return;
    } 
    imprimirRelatorio(arrayOfLocalDate, list);
  }
  
  protected void imprimirRelatorio(LocalDate[] paramArrayOfLocalDate, List<Model> paramList) {
    imprimirCabecalhoRelatorio(paramArrayOfLocalDate);
    System.out.printf("\n%10s\t%30s\t%12s\t%15s\t%20s\n", new Object[] { "Data", "Produto", "Quantidade", "Valor (R$)", "Valor total (R$)" });
    System.out.println("------------------------------------------------------------------------------------------------------");
    paramList.forEach(paramModel -> {
          Venda venda = (Venda)paramModel;
          System.out.printf("%10s\t%30s\t%12d\t%15.2f\t%20.2f\n", new Object[] { this.df.format(venda.getData()), venda.getProduto().getNome(), Integer.valueOf(venda.getQuantidade()), Double.valueOf(venda.getProduto().getValor()), Double.valueOf(venda.getQuantidade() * venda.getProduto().getValor()) });
        });
    double d = paramList.stream().mapToDouble(paramModel -> {
          Venda venda = (Venda)paramModel;
          return venda.getQuantidade() * venda.getProduto().getValor();
        }).average().getAsDouble();
    System.out.println("------------------------------------------------------------------------------------------------------");
    System.out.printf("Valor mdas vendas no perR$ %.2f\n", new Object[] { Double.valueOf(d) });
  }
  
  protected void imprimirCabecalhoRelatorio(LocalDate[] paramArrayOfLocalDate) {
    ControladorInput.obterInstancia().limparTela();
    imprimirTitulo();
    System.out.printf("\nPerde emiss%s a %s.\n", new Object[] { this.df.format(paramArrayOfLocalDate[0]), this.df.format(paramArrayOfLocalDate[1]) });
  }
  
  protected LocalDate[] lerDatas(Scanner paramScanner) {
    LocalDate[] arrayOfLocalDate = { LocalDate.now(), LocalDate.now() };
    boolean bool = true;
    do {
      ControladorInput.obterInstancia().limparTela();
      imprimirTitulo();
      System.out.println("\nInforme o perde emiss");
      System.out.println("-----------------------------");
      System.out.print("Data inicial [dd/mm/yyyy] (ENTER para a data de hoje): ");
      String str1 = paramScanner.nextLine();
      if (!"".equals(str1))
        try {
          arrayOfLocalDate[0] = LocalDate.parse(str1, this.df);
        } catch (DateTimeParseException dateTimeParseException) {
          System.out.println("\nData inv\nPressione ENTER para tentar novamente...");
          paramScanner.nextLine();
          bool = false;
        }  
      System.out.print("Data final [dd/mm/yyyy] (ENTER para a data de hoje): ");
      String str2 = paramScanner.nextLine();
      if (!"".equals(str2))
        try {
          arrayOfLocalDate[1] = LocalDate.parse(str2, this.df);
        } catch (DateTimeParseException dateTimeParseException) {
          System.out.println("\nData inv\nPressione ENTER para reiniciar o processo...");
          paramScanner.nextLine();
          bool = false;
        }  
      if (arrayOfLocalDate[0].compareTo(arrayOfLocalDate[1]) <= 0)
        continue; 
      System.out.println("\nA data inicial deve ser menor ou igual data final.");
      System.out.println("Pressione ENTER para reiniciar o processo...");
      paramScanner.nextLine();
      bool = false;
    } while (!bool);
    return arrayOfLocalDate;
  }
  
  protected void imprimirTitulo() {
    System.out.println("*****************************************");
    System.out.println("**** RELATDE VENDAS - DETALHADO ****");
    System.out.println("*****************************************");
  }
}
