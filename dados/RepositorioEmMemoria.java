package dados;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RepositorioEmMemoria<T> implements Repositorio<T> {
  private final List<T> elementos = new ArrayList<>();
  
  public T obterPorId(T paramT) {
    int i = this.elementos.indexOf(paramT);
    return (i > -1) ? this.elementos.get(i) : null;
  }
  
  public List<T> obterTodos() {
    return new ArrayList<>(this.elementos);
  }
  
  public boolean adicionar(T paramT) {
    return this.elementos.add(paramT);
  }
  
  public boolean alterar(T paramT) {
    int i = this.elementos.indexOf(paramT);
    if (i == -1)
      return false; 
    this.elementos.set(i, paramT);
    return false;
  }
  
  public boolean remover(T paramT) {
    return this.elementos.remove(paramT);
  }
  
  public Stream<T> obterStream() {
    return this.elementos.stream();
  }
  
  public boolean estaVazio() {
    return this.elementos.isEmpty();
  }
}
