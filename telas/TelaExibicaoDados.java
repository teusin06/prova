package telas;

import dados.Repositorio;
import java.util.Scanner;
import negocio.Model;

public interface TelaExibicaoDados {
  void exibirDados(Scanner paramScanner, Repositorio<Model> paramRepositorio);
}

