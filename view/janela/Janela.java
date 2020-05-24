package view.janela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Autor;
import model.Editora;
import model.Livro;
import view.View;
import view.janela.panels.PanelBuscaAutores;
import view.janela.panels.PanelBuscaEditoras;
import view.janela.panels.PanelBuscaLivros;

public class Janela extends JFrame implements View{
	
		
		JButton inserirLivro;
		JButton inserirAutor;
		JButton inserirEditora;
		
		PanelBuscaLivros panelLivros;
		PanelBuscaAutores panelAutores;
		PanelBuscaEditoras panelEditoras;
		
		
		
	public Janela() {
	setLayout(new BorderLayout());
	setTitle("Livraria Agustinho");
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 setSize(1000,700);
	 setVisible(true);
	 
	 
	 JTabbedPane tabbedPane = new JTabbedPane();
		
	    panelLivros = new PanelBuscaLivros( "panel livros");
	    panelAutores = new PanelBuscaAutores(Color.PINK, "panel autores");
	    panelEditoras = new PanelBuscaEditoras(Color.YELLOW, "panel editoras");
		tabbedPane.add("Livros", panelLivros);
		tabbedPane.add("Autores", panelAutores);
		tabbedPane.add("Editoras", panelEditoras);
		add(tabbedPane, BorderLayout.CENTER);
		
	 
	 JPanel insere = new JPanel( new FlowLayout());
	 inserirLivro = new JButton("Inserir Livro");
	 inserirAutor = new JButton("Inserir Autor");
	 inserirEditora = new JButton("Inserir Editora");
	 insere.add(inserirLivro);
	 insere.add(inserirAutor);
	 insere.add(inserirEditora);
	 add(insere, BorderLayout.PAGE_END);
	
	 
	 
		
	 
	}
	
	//listeners janela
	public void adicionaComportamentoInserirEditora(ActionListener al) {
		
		inserirEditora.addActionListener(al);
		
	}
	
	public void adicionaComportamentoInserirAutor(ActionListener al) {
		
		inserirAutor.addActionListener(al);
		
	}
	
	public void adicionaComportamentoInserirLivro(ActionListener al) {
		
		inserirLivro.addActionListener(al);
		
	}
	
	//listeners e metodos panelBuscaLivros
	public void adicionaComportamentoBuscaLivro(ActionListener al) {
		panelLivros.buscaLivro(al);
	}
	
	public String panelLivrosGetBusca() {
		return panelLivros.getBusca();
	}
	
	public void panelLivrosErroLivro() {
		panelLivros.mostrarErroNaoExisteLivro();
	}
	
	public void panelLivrosPrintaLivros(List<Livro> livros) {
		
		panelLivros.printaLivros(livros);
		}
	
	public void panelLivrosMostraLivro(MouseListener al) {
		panelLivros.mostraLivroSelecionado(al);
	}
	
	public Livro panelLivrosgetLivroSelecionado() {
		
		return panelLivros.getLivroSelecionado();
		}
	
	public void janelaLivrosPrintaLivros(Livro livro) {
		panelLivros.janelaLivrosPrintaLivros(livro);
	}
	
	public void janelaLivroscomportamentoExcluir(ActionListener al) {
		panelLivros.janelaLivroscomportamentoExcluir(al);
	}
	
	public void janelaLivrosComportamentoAlterar(ActionListener al) {
		panelLivros.janelaLivrosComportamentoAlterar(al);
	}
	
	public String janelaLivrosGetIsbn() {
		return panelLivros.janelaLivrosGetIsbn();
	}
	
	public String janelaLivrosGetTitulo() {
		return panelLivros.janelaLivrosGetTitulo();
	}
	
	public String janelaLivrosGetPreco() {
		return panelLivros.janelaLivrosGetPreco();
	}	
	
	
	//listeners panelAutores
	public String panelAutoresGetBusca() {
		return panelAutores.getBusca();
	}
	
	public void panelAutoresPrintaAutores(List<Autor> autores) {
		panelAutores.printaAutores(autores);
	}
	
	public void panelAutoresComportamentoBuscarAutor(ActionListener al) {
		panelAutores.comportamentoBuscar(al);
	}
	
	public void panelAutoresComportamentoMostrarAutor(MouseListener al) {
		panelAutores.comportamentoMostrar(al);
	}
	
	public Autor panelAutoresGetAutorSelecionado() {
		return panelAutores.getAutorSelecionado();
		}
	
	public void JanelaAutoresPrintaAutores(Autor autor) {
		panelAutores.JanelaAutoresPrintaAutores(autor);
}
	
	public void JanelaAutoresPrintaLivros() {
		panelAutores.JanelaAutoresPrintaLivros();
	}
	
	public void janelaAutoresComportamentoExcluir(ActionListener al) {
		panelAutores.janelaAutoresComportamentoExcluir(al);
	}
	
	public int janelaAutoresGetId() {
		return panelAutores.janelaAutoresGetId();
	}
	
	public String janelaAutoresGetNome() {
		return panelAutores.janelaAutoresGetNome();
	}
	
	public String JanelaAutoresGetSobrenome() {
		return panelAutores.JanelaAutoresGetSobrenome();
	}
	
	public void janelaAutoresComportamentoAlterar(ActionListener al) {
		panelAutores.janelaAutoresComportamentoAlterar(al);
	}
	
	
	//listeners e metodos panelBuscaEditoras
	public void panelEditorasComportamentoBuscarEditora(ActionListener al) {
		panelEditoras.comportamentoBuscar(al);
	}
	
	public void panelEditoraMostrarEditora(MouseListener al) {
		panelEditoras.mostrarEditora(al);
	}
	
	public String panelEditorasGetBusca() {
		return panelEditoras.getBusca();
	}
	
	public void panelEditorasPrintaEditoras(List <Editora> editoras) {
		 panelEditoras.printaEditoras(editoras);
	}
	
	public Editora panelEditorasGetEditoraSelecionado() {
		return panelEditoras.getEditoraSelecionado();
		}
	
	public void janelaEditorasPrintaEditora(Editora editora) {
		panelEditoras.janelaEditorasPrintaEditora(editora);
	}
	
	public void JanelaEditorasPrintaLivros() {
		panelEditoras.JanelaEditorasPrintaLivros();
	}
	
	public int JanelaEditorasGetId() {
		return panelEditoras.janelaEditorasGetId();
	}
	
	public String JanelaEditorasGetNome() {
		return panelEditoras.janelaEditorasGetNome();
	}
	
	public String JanelaEditorasGetUrl() {
		return panelEditoras.janelaEditorasGetUrl();
	}
	
	public void janelaEditorascomportamentoExcluirEditora(ActionListener al) {
		panelEditoras.janelaEditorascomportamentoExcluirEditora(al);
	}
	
	public void janelaEditoraComportamentoAlterarEditora(ActionListener al) {
		panelEditoras.janelaEditoraComportamentoAlterarEditora(al);
	}
	
}
	


