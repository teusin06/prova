package dados;

import java.util.List;
import java.util.stream.Stream;

public interface Repositorio<T> {
  T obterPorId(T paramT);
  
  List<T> obterTodos();
  
  boolean adicionar(T paramT);
  
  boolean alterar(T paramT);
  
  boolean remover(T paramT);
  
  Stream<T> obterStream();
  
  boolean estaVazio();
}
