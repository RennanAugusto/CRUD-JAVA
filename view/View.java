package view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import model.Autor;
import model.Editora;
import model.Livro;

public interface View {
	
	// listeners da Janela
	public void adicionaComportamentoInserirEditora(ActionListener al);
	public void adicionaComportamentoInserirAutor(ActionListener al);
	public void adicionaComportamentoInserirLivro(ActionListener al);
	
	//listeners panelBuscaLivro
	public void adicionaComportamentoBuscaLivro(ActionListener al);
	public String panelLivrosGetBusca();
	public void panelLivrosErroLivro();
	public void panelLivrosPrintaLivros(List<Livro> livros);
	public void panelLivrosMostraLivro(MouseListener al);
	public Livro panelLivrosgetLivroSelecionado();
	public void janelaLivrosPrintaLivros(Livro livro);
	public void janelaLivroscomportamentoExcluir(ActionListener al);
	public void janelaLivrosComportamentoAlterar(ActionListener al);
	public String janelaLivrosGetIsbn();
	public String janelaLivrosGetTitulo();
	public String janelaLivrosGetPreco();
	
	//listeneres e metodos panelBuscaAutores
	public String panelAutoresGetBusca();
	public void panelAutoresPrintaAutores(List<Autor> autores);
	public void panelAutoresComportamentoBuscarAutor(ActionListener al);
	public void panelAutoresComportamentoMostrarAutor(MouseListener al);
	public Autor panelAutoresGetAutorSelecionado();
	public void JanelaAutoresPrintaAutores(Autor autor);
	public void JanelaAutoresPrintaLivros();
	public void janelaAutoresComportamentoExcluir(ActionListener al);
	public int janelaAutoresGetId();
	public String janelaAutoresGetNome();
	public String JanelaAutoresGetSobrenome();
	public void janelaAutoresComportamentoAlterar(ActionListener al);
	
	//listeners e metodos panelBuscaEditoras
	public void panelEditorasComportamentoBuscarEditora(ActionListener al);
	public String panelEditorasGetBusca();
	public void panelEditorasPrintaEditoras(List <Editora> editoras);
	public void panelEditoraMostrarEditora(MouseListener al);
	public Editora panelEditorasGetEditoraSelecionado();
	public void janelaEditorasPrintaEditora(Editora editora);
	public void JanelaEditorasPrintaLivros();
	public int JanelaEditorasGetId();
	public String JanelaEditorasGetNome();
	public String JanelaEditorasGetUrl(); 
	public void janelaEditorascomportamentoExcluirEditora(ActionListener al);
	public void janelaEditoraComportamentoAlterarEditora(ActionListener al);
}
