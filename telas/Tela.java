package telas;

public class Tela {
  private String texto;
  
  private int id;
  
  public Tela(String paramString, int paramInt) {
    this.texto = paramString;
    this.id = paramInt;
  }
  
  public String getTexto() {
    return this.texto;
  }
  
  public void setTexto(String paramString) {
    this.texto = paramString;
  }
  
  public int getId() {
    return this.id;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof Tela && ((Tela)paramObject).id == this.id);
  }
}
