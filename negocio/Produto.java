package negocio;

public class Produto implements Model, Comparable<Produto> {
  private int codigo;
  
  private String nome;
  
  private double valor;
  
  private int quantidadeEstoque;
  
  public Produto() {}
  
  public Produto(int paramInt) {
    this.codigo = paramInt;
  }
  
  public Produto(int paramInt1, String paramString, double paramDouble, int paramInt2) {
    this.codigo = paramInt1;
    this.nome = paramString;
    this.valor = paramDouble;
    this.quantidadeEstoque = paramInt2;
  }
  
  public int getCodigo() {
    return this.codigo;
  }
  
  public void setCodigo(int paramInt) {
    this.codigo = paramInt;
  }
  
  public String getNome() {
    return this.nome;
  }
  
  public void setNome(String paramString) {
    this.nome = paramString;
  }
  
  public double getValor() {
    return this.valor;
  }
  
  public void setValor(double paramDouble) {
    this.valor = paramDouble;
  }
  
  public int getQuantidadeEstoque() {
    return this.quantidadeEstoque;
  }
  
  public void setQuantidadeEstoque(int paramInt) {
    this.quantidadeEstoque = paramInt;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof Produto && ((Produto)paramObject).getCodigo() == this.codigo);
  }
  
  public String toString() {
    return String.format("Codigo: %d - Nome: %s - Valor: %.2f - Estoque: %d", new Object[] { Integer.valueOf(this.codigo), this.nome, Double.valueOf(this.valor), Integer.valueOf(this.quantidadeEstoque) });
  }
  
  public int compareTo(Produto paramProduto) {
    if (this.nome == null || paramProduto.getNome() == null)
      return 0; 
    return this.nome.compareToIgnoreCase(paramProduto.getNome());
  }
}