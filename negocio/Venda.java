package negocio;

import java.time.LocalDate;

public class Venda implements Model, Comparable<Venda> {
  private LocalDate data;
  
  private Produto produto;
  
  private int quantidade;
  
  public Venda() {}
  
  public Venda(Produto paramProduto) {
    this.produto = paramProduto;
  }
  
  public Venda(LocalDate paramLocalDate, Produto paramProduto, int paramInt) {
    this.data = paramLocalDate;
    this.produto = paramProduto;
    this.quantidade = paramInt;
  }
  
  public LocalDate getData() {
    return this.data;
  }
  
  public void setData(LocalDate paramLocalDate) {
    this.data = paramLocalDate;
  }
  
  public Produto getProduto() {
    return this.produto;
  }
  
  public void setProduto(Produto paramProduto) {
    this.produto = paramProduto;
  }
  
  public int getQuantidade() {
    return this.quantidade;
  }
  
  public void setQuantidade(int paramInt) {
    this.quantidade = paramInt;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject != null && paramObject instanceof Venda) {
      Venda venda = (Venda)paramObject;
      return (this.data.equals(venda.getData()) && this.produto.equals(venda.getProduto()));
    } 
    return false;
  }
  
  public String toString() {
    return String.format("Data: %s - Produto: [%s] - Quantidade: %d", new Object[] { this.data, this.produto
          .toString(), Integer.valueOf(this.quantidade) });
  }
  
  public int compareTo(Venda paramVenda) {
    return this.data.compareTo(paramVenda.getData());
  }
}