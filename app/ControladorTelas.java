package app;

import dados.Repositorio;
import dados.RepositorioEmMemoria;
import java.util.HashMap;
import java.util.Map;
import negocio.Model;
import telas.CadastroProduto;
import telas.ConsultaProduto;
import telas.RegistroVenda;
import telas.RelatorioProduto;
import telas.RelatorioVendaConsolidado;
import telas.RelatorioVendaDetalhado;
import telas.Tela;

public final class ControladorTelas {
  public static final String ROTA_PRIMEIRA_TELA = "00";
  
  private static final int MENU_PRINCIPAL = 0;
  
  private static final int MENU_RELATORIOS = 2;
  
  private static final int MENU_REGISTRAR_VENDA = 3;
  
  private static final int MENU_CADASTRO_PRODUTOS = 1;
  
  private static final int MENU_CADASTRO_CONSULTAR_PRODUTO = 11;
  
  private static final int MENU_CADASTRO_INCLUIR_PRODUTO = 12;
  
  private static final int MENU_RELATORIO_PRODUTO = 21;
  
  private static final int MENU_RELATORIO_VENDA_DETALHADO = 22;
  
  private static final int MENU_RELATORIO_VENDA_CONSOLIDADO = 23;
  
  private final Map<String, Tela> telas = new HashMap<>();
  
  private final Map<Integer, Repositorio<Model>> repositorios = new HashMap<>();
  
  private static ControladorTelas instancia;
  
  private ControladorTelas() {
    iniciarRepositorios();
    iniciarRotasTelas();
  }
  
  public static ControladorTelas obterInstancia() {
    if (instancia == null)
      instancia = new ControladorTelas(); 
    return instancia;
  }
  
  public Tela obterProximaTela(String paramString) {
    return this.telas.get(paramString);
  }
  
  public Repositorio<Model> obterRepositorio(int paramInt) {
    return this.repositorios.get(Integer.valueOf(paramInt));
  }
  
  private Tela menuPrincipal() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("**************************************\n");
    stringBuilder.append("*********** MENU PRINCIPAL ***********\n");
    stringBuilder.append("**************************************\n");
    stringBuilder.append("1 - Cadastro de produtos\n");
    stringBuilder.append("2 - Relat\n");
    stringBuilder.append("3 - Registrar venda\n");
    stringBuilder.append("0 - Sair\n\n");
    stringBuilder.append("Op");
    return new Tela(stringBuilder.toString(), 0);
  }
  
  private Tela menuInternoCadastro() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("**************************************\n");
    stringBuilder.append("******** CADASTRO DE PRODUTOS ********\n");
    stringBuilder.append("**************************************\n");
    stringBuilder.append("1 - Consultar\n");
    stringBuilder.append("2 - Incluir\n");
    stringBuilder.append("0 - Voltar\n\n");
    stringBuilder.append("Op");
    return new Tela(stringBuilder.toString(), 1);
  }
  
  private Tela menuRelatorios() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("**************************************\n");
    stringBuilder.append("************* RELAT*************\n");
    stringBuilder.append("**************************************\n");
    stringBuilder.append("1 - Produtos\n");
    stringBuilder.append("2 - Vendas por per- detalhado\n");
    stringBuilder.append("3 - Vendas por per- consolidado\n");
    stringBuilder.append("0 - Voltar\n\n");
    stringBuilder.append("Op");
    return new Tela(stringBuilder.toString(), 2);
  }
  
  private Tela cadastroIncluir() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("***************************************\n");
    stringBuilder.append("********* PRODUTOS - INCLUS*********\n");
    stringBuilder.append("***************************************\n");
    return (Tela)new CadastroProduto(stringBuilder.toString(), 12);
  }
  
  private Tela cadastroConsultar() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("***************************************\n");
    stringBuilder.append("********* PRODUTOS - CONSULTA *********\n");
    stringBuilder.append("***************************************\n");
    return (Tela)new ConsultaProduto(stringBuilder.toString(), 11);
  }
  
  private Tela registrarVenda() {
    return (Tela)new RegistroVenda("", 3, 
        obterRepositorio(11));
  }
  
  private Tela relatorioProdutos() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("****************************************\n");
    stringBuilder.append("********* LISTAGEM DE PRODUTOS *********\n");
    stringBuilder.append("****************************************\n");
    return (Tela)new RelatorioProduto(stringBuilder.toString(), 21);
  }
  
  private Tela relatorioVendasDetalhado() {
    return (Tela)new RelatorioVendaDetalhado("", 22);
  }
  
  private Tela relatorioVendasConsolidado() {
    return (Tela)new RelatorioVendaConsolidado("", 23);
  }
  
  private void iniciarRotasTelas() {
    this.telas.put("00", menuPrincipal());
    this.telas.put("01", menuInternoCadastro());
    this.telas.put("02", menuRelatorios());
    this.telas.put("03", registrarVenda());
    this.telas.put("20", menuPrincipal());
    this.telas.put("11", cadastroConsultar());
    this.telas.put("12", cadastroIncluir());
    this.telas.put("10", menuPrincipal());
    this.telas.put("21", relatorioProdutos());
    this.telas.put("22", relatorioVendasDetalhado());
    this.telas.put("23", relatorioVendasConsolidado());
  }
  
  private void iniciarRepositorios() {
    RepositorioEmMemoria repositorioEmMemoria1 = new RepositorioEmMemoria();
    RepositorioEmMemoria repositorioEmMemoria2 = new RepositorioEmMemoria();
    this.repositorios.put(Integer.valueOf(12), repositorioEmMemoria1);
    this.repositorios.put(Integer.valueOf(11), repositorioEmMemoria1);
    this.repositorios.put(Integer.valueOf(21), repositorioEmMemoria1);
    this.repositorios.put(Integer.valueOf(3), repositorioEmMemoria2);
    this.repositorios.put(Integer.valueOf(22), repositorioEmMemoria2);
    this.repositorios.put(Integer.valueOf(23), repositorioEmMemoria2);
  }
}