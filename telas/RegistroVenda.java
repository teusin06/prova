package telas;

import app.ControladorInput;
import dados.Repositorio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import negocio.Model;
import negocio.Produto;
import negocio.Venda;

public class RegistroVenda extends Tela implements TelaLeituraDados {
  private Repositorio<Model> repoProdutos;
  
  public RegistroVenda(String paramString, int paramInt, Repositorio<Model> paramRepositorio) {
    super(paramString, paramInt);
    this.repoProdutos = paramRepositorio;
  }
  
  public Model lerDados(Scanner paramScanner) {
    Produto produto;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate localDate = LocalDate.now();
    int i = 0;
    while (true) {
      int j;
      boolean bool = true;
      ControladorInput.obterInstancia().limparTela();
      System.out.println("***************************************");
      System.out.println("*********** REGISTRAR VENDA ***********");
      System.out.println("***************************************");
      try {
        System.out.print("Cdo produto (ou um caractere nnumpara sair): ");
        j = paramScanner.nextInt();
      } catch (Exception exception) {
        return null;
      } 
      paramScanner.nextLine();
      produto = new Produto(j);
      produto = (Produto)this.repoProdutos.obterPorId(produto);
      if (produto == null) {
        System.out.print("\nProduto nencontrado.\nPressione ENTER para tentar novamente...");
        paramScanner.nextLine();
        bool = false;
      } else {
        System.out.printf("\nProduto localizado: [%s]. Confirma (\"S\" ou ENTER confirma; outro caractere volta a busca)? ", new Object[] { produto
              
              .getNome() });
        String str1 = paramScanner.nextLine();
        if ("".equals(str1) || str1.equalsIgnoreCase("s")) {
          if (produto.getQuantidadeEstoque() == 0) {
            System.out.println("\nProduto com estoque zerado, a venda npode ser feita.\nPressione ENTER para reiniciar o processo...");
            paramScanner.nextLine();
            bool = false;
          } 
        } else {
          bool = false;
          if (bool)
            break; 
        } 
        System.out.print("Data [dd/mm/aaaa] (ENTER para a data de hoje): ");
        String str2 = paramScanner.nextLine();
        if (!"".equals(str2))
          try {
            localDate = LocalDate.parse(str2, dateTimeFormatter);
          } catch (DateTimeParseException dateTimeParseException) {
            System.out.println("\nData inv\nPressione ENTER para reiniciar o processo...");
            paramScanner.nextLine();
            bool = false;
          }  
        System.out.print("Quantidade: ");
        i = paramScanner.nextInt();
        paramScanner.nextLine();
        if (produto.getQuantidadeEstoque() < i) {
          System.out.printf("\nProduto com estoque insuficiente (%d) para antender esta venda. Nposscontinuar.\nPressione ENTER para reiniciar o processo...", new Object[] { Integer.valueOf(produto.getQuantidadeEstoque()) });
          paramScanner.nextLine();
          bool = false;
        } 
      } 
      if (bool)
        break; 
    } 
    produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - i);
    this.repoProdutos.alterar(produto);
    return (Model)new Venda(localDate, produto, i);
  }
}
