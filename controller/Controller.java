package controller;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import model.Autor;
import model.Editora;
import model.Livro;
import model.dao.Dao;
import model.dao.daoPostgres.DaoPostgres;
import view.View;
import view.janela.AlteraAutor;
import view.janela.AlteraEditora;
import view.janela.AlteraLivro;
import view.janela.Janela;
import view.janela.JanelaInsereAutores;
import view.janela.JanelaInsereEditoras;
import view.janela.JanelaInsereLivros;
import view.janela.avisos.ConfirmacaoAutores;
import view.janela.avisos.ConfirmacaoEditoras;
import view.janela.panels.JanelaLivros;

public class Controller {
	
	Dao dao;
	View view;
	
	public Controller( Dao dao,View view) {
		this.dao = dao;
		this.view = view;
		
		//listeners janela principal
		view.adicionaComportamentoInserirEditora(new comportamentoInserirEditora());
		view.adicionaComportamentoInserirAutor(new comportamentoInserirAutor());
		view.adicionaComportamentoInserirLivro(new comportamentoInserirLivro());
		
		//listeners panelBuscaLivros
		view.adicionaComportamentoBuscaLivro(new comportamentoBuscarLivro() );
		view.panelLivrosMostraLivro(new clickMostraLivro());
		view.janelaLivroscomportamentoExcluir(new comportamentoExcluirLivro());
		view.janelaLivrosComportamentoAlterar(new comportamentoAlterarLivro());
		
		//listeners panelBuscaAutores
		view.panelAutoresComportamentoBuscarAutor(new comportamentoBuscarAutor());
		view.panelAutoresComportamentoMostrarAutor(new clickMostraAutor());
		view.janelaAutoresComportamentoExcluir(new comportamentoExcluirAutor());
		view.janelaAutoresComportamentoAlterar(new comportamentoAlterarAutor());
		
		//listeners panelBuscaEditoras
		view.panelEditorasComportamentoBuscarEditora(new comportamentoBuscarEditora());
		view.panelEditoraMostrarEditora(new clickMostraEditora());
		view.janelaEditorascomportamentoExcluirEditora(new comportamentoExcluirEditora());
		view.janelaEditoraComportamentoAlterarEditora(new comportamentoAlterarEditora());
	}
	
	
	//listeners da Janela principal
	
	class comportamentoInserirEditora implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			new JanelaInsereEditoras();
			
		}
	}
	
class comportamentoInserirAutor implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			new JanelaInsereAutores();
			
		}
	}

class comportamentoInserirLivro implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		new JanelaInsereLivros();
		
	}
}

	//listeners PanelBuscaLivros
class comportamentoBuscarLivro implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String titulo = view.panelLivrosGetBusca();
		
		List<Livro> livros = dao.buscaLivros(titulo);
		
			if(livros.isEmpty())
				view.panelLivrosErroLivro();
			else
				view.panelLivrosPrintaLivros(livros);
		
	}
}

class clickMostraLivro implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount() == 2) {
			
			Livro livro =  view.panelLivrosgetLivroSelecionado(); 
			view.janelaLivrosPrintaLivros(livro);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	
}
	
class comportamentoExcluirLivro implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		DaoPostgres.excluiLivroAutor(view.janelaLivrosGetIsbn());
		JOptionPane.showMessageDialog(null, "Livro excluido com sucesso");
		
		
	}
	
}
	
class comportamentoAlterarLivro implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		AlteraLivro alteraLivro = new AlteraLivro(view.janelaLivrosGetIsbn(), view.janelaLivrosGetTitulo(), view.janelaLivrosGetPreco());
		
		
	}
	
}


	//listeners panelBuscaAutores
class comportamentoBuscarAutor implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nome = view.panelAutoresGetBusca();
		
		List<Autor> autores = dao.buscaAutores(nome);
		
			if(autores.isEmpty())
				System.out.println("não há autores");
			else
				view.panelAutoresPrintaAutores(autores);
		
	}
	
}

public class clickMostraAutor implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount() == 2) {
			
			Autor autor = view.panelAutoresGetAutorSelecionado();
			view.JanelaAutoresPrintaAutores(autor);
			view.JanelaAutoresPrintaLivros();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	
}

class comportamentoExcluirAutor implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ConfirmacaoAutores confirmar = new ConfirmacaoAutores(view.janelaAutoresGetId());
		
	}
	
}

class comportamentoAlterarAutor implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		AlteraAutor alterar = new AlteraAutor(view.janelaAutoresGetNome(), view.JanelaAutoresGetSobrenome(), view.janelaAutoresGetId());				
		
	}
	
}

	
	//listeners panelBuscaEditoras
class comportamentoBuscarEditora implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nome = view.panelEditorasGetBusca();
		
		List<Editora> editoras = dao.buscaEditoras(nome);
		
			if(editoras.isEmpty())
				System.out.println("não há editoras");
			else
				view.panelEditorasPrintaEditoras(editoras);
		
	}
	
}

class clickMostraEditora implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount() == 2) {
			Editora editora = view.panelEditorasGetEditoraSelecionado();
			view.janelaEditorasPrintaEditora(editora);
			view.JanelaEditorasPrintaLivros();
			JOptionPane.showMessageDialog(null, "HAHAHAHA");
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	
}

class comportamentoExcluirEditora implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ConfirmacaoEditoras confirmar = new ConfirmacaoEditoras(view.JanelaEditorasGetId());
		
		
	}
	
}

class comportamentoAlterarEditora implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		AlteraEditora altera = new AlteraEditora(view.JanelaEditorasGetNome(), view.JanelaEditorasGetUrl(), view.JanelaEditorasGetId());
		
		
	}
	
}
}
	
