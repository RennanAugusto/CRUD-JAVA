package view.janela;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Autor;
import model.Editora;
import model.dao.daoPostgres.DaoPostgres;
import view.janela.JanelaInsereLivros.apagar;
import view.janela.JanelaInsereLivros.click;
import view.janela.JanelaInsereLivros.comportamentoBuscar;
import view.janela.panels.JanelaAutores;

public class AlteraLivro extends JFrame {
	
	DaoPostgres dao = new DaoPostgres();
	
	static final Font font = new Font("sans serif", Font.BOLD, 23);
	
	JLabel espaco = new JLabel(" ");
	int author_id;
	
	JLabel infoLivro;
	JLabel lTitulo;
	JTextField  tTitulo;
	JLabel lPreco;
	JTextField  tPreco;
	JButton alterarLivro;
	
	JLabel infoEditora;
	JLabel nomeEditora;
	JComboBox comboBoxEditora;
	JButton alterarEditora;
	
	JLabel infoAutor;
	JComboBox comboBoxAutor;
	JButton removerAutor;
	
	JTextField buscaAutor;
	JButton buscar;
	JButton limparAutoresSelecionados;
	JButton inserirAutores;
	JLabel labelInserirAutores;
	List<Integer> autoresId = new ArrayList<Integer>();
	int linha;
	
	
	Object [][] data = new Object[0][3];
	Object[] coluna = {"Resultados:", "Selecionado","OBjeto"};
	DefaultTableModel dtm;
	JTable tabela;
	
	
	
	String isbn;
	
	
	public AlteraLivro(String isbn, String titulo, String preco) {
		
		
		setLayout(new BorderLayout());
		setTitle("Altera Livro");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setSize(300,300);
		
		this.isbn = isbn;
		JPanel alterarDados= new JPanel();
		
		alterarDados.setLayout(
				new BoxLayout(alterarDados, BoxLayout.PAGE_AXIS));
		
		
		infoLivro = new JLabel("Informações do Livro: ");
		infoLivro.setFont(font);
		lTitulo = new JLabel("Insira o titulo do livro: ");
		tTitulo = new JTextField(titulo);
		lPreco = new JLabel("Insira o preço do livro:  ");
		tPreco = new JTextField(preco);
		alterarLivro = new JButton("Alterar");
		alterarLivro.addActionListener(new comportamentoAlterar());
		
		alterarDados.add(infoLivro);
		alterarDados.add(lTitulo);
		alterarDados.add(tTitulo);
		alterarDados.add(lPreco);
		alterarDados.add(tPreco);
		alterarDados.add(alterarLivro);
		alterarDados.add(espaco);
		
		infoAutor = new JLabel("Autores deste livro: ");
		infoAutor.setFont(font);
		comboBoxAutor = new JComboBox();
		removerAutor = new JButton("Remover");
		removerAutor.addActionListener(new comportamentoExcluirAutor());
		alterarDados.add(infoAutor);
		buscaAutoresComboBox();
		JPanel autoresExistentes = new JPanel(new FlowLayout());
		autoresExistentes.add(comboBoxAutor);
		autoresExistentes.add(removerAutor);
		alterarDados.add(autoresExistentes);
		
		JPanel alteraEditora = new JPanel(new FlowLayout());
		infoEditora = new JLabel("Alterar Editora: ");
		nomeEditora = new JLabel("Editora Atual: ");
		editoraLivro();
		infoEditora.setFont(font);
		comboBoxEditora = new JComboBox();
		alterarEditora = new JButton("Alterar");
		alterarEditora.addActionListener(new comportamentoAlterarEditora());
		alterarDados.add(infoEditora);
		alterarDados.add(nomeEditora);
		buscaEditorasComboBox();
		alteraEditora.add(comboBoxEditora);
		alteraEditora.add(alterarEditora);
		alterarDados.add(alteraEditora);
		
		
		labelInserirAutores = new JLabel("Inserir Autores: ");
		labelInserirAutores.setFont(font);
		alterarDados.add(labelInserirAutores);
		
		limparAutoresSelecionados = new JButton("Limpar Autores");
		limparAutoresSelecionados.addActionListener(new apagar());
		buscar = new JButton("Buscar");
		buscar.addActionListener(new comportamentoBuscarAutor());
		
		dtm = new DefaultTableModel(data,coluna);
		 tabela = new JTable(dtm) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		 };
		 
		 tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);;
		 tabela.setSize(new Dimension(520,300));
		 tabela.getColumnModel().getColumn(0).setPreferredWidth(420);
		 tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		 tabela.getColumnModel().getColumn(2).setPreferredWidth(0);
		 tabela.addMouseListener(new click());
		 TableColumnModel tcm = tabela.getColumnModel();
		 tcm.removeColumn( tcm.getColumn(2) );
		 JScrollPane scrollPane = new JScrollPane(tabela);
		 scrollPane.setSize(new Dimension(520,300));
		 
		 JPanel panelBusca = new JPanel(new FlowLayout());
		 buscaAutor = new JTextField(20);
		 panelBusca.add(buscaAutor);
		 panelBusca.add(buscar);
		 panelBusca.add(limparAutoresSelecionados);
		 
		 alterarDados.add(panelBusca);
		 alterarDados.add(scrollPane);
		 buscaAutomaticaAutores();
		 
		 inserirAutores= new JButton("Inserir Autores");
		 inserirAutores.addActionListener(new comportamentoInserirAutores());
		 alterarDados.add(inserirAutores);
		add(alterarDados,BorderLayout.CENTER);
		
		pack();
		
	}
	
	class comportamentoAlterar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			DaoPostgres.alteraLivro(isbn, tTitulo.getText(), tPreco.getText());
			JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso!");
			
		}
		
	}
	
	class comportamentoExcluirAutor implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String nome;
			nome = (String) comboBoxAutor.getSelectedItem();
			System.out.println(nome);
			author_id = DaoPostgres.comboBoxAutorId(nome);
			System.out.println(author_id);
			DaoPostgres.excluirAutorIdIsbn(author_id, isbn);
			buscaAutoresComboBox();
			JOptionPane.showMessageDialog(null, "Autor: " + nome + " Excluido com sucesso");
			
			
		}
		
	}
	
	public void editoraLivro() {
		String nome = DaoPostgres.editoraPeloIsbn(isbn);
		nomeEditora.setText("Editora Atual: " + nome);
	}
	
	class comportamentoAlterarEditora implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String nome = (String) comboBoxEditora.getSelectedItem();
			int id = dao.comboBoxEditoraId(nome);
			
			DaoPostgres.alteraEditora(isbn,id);
			editoraLivro();
			
			JOptionPane.showMessageDialog(null, "Editora: " + nome + " Incluida com sucesso!");
		}
		
	}
	
	public void buscaAutoresComboBox() {
		
	
		comboBoxAutor.removeAllItems();
		
		List <Autor> autores = DaoPostgres.livroBuscaAutores(isbn);
		for(Autor autor : autores) {
			comboBoxAutor.addItem(autor.nome);
		}
	}
	
	public void buscaAutomaticaAutores() {
		
		
		List<Autor> autores = dao.buscaAutores("");
		
			if(autores.isEmpty())
				System.out.println("Sem Autores");
			else
				printaAutores(autores);
		
	}
	
	public void buscaEditorasComboBox() {
		
		
		
		List<Editora> editoras = dao.comboBoxEditora();
		

		for(Editora editora: editoras) {
			
			comboBoxEditora.addItem(editora.name);
			
			
		}
		
		

	}
	
	public void printaAutores(List<Autor> autores) {
		
		dtm.setRowCount(0);
		for(Autor autor : autores) {
			Object[] data = new Object[3];
			
			data[0] = autor.pNome + " "+ autor.nome;
			data[2] = autor;
			dtm.addRow(data);
		}
	}
	
	class comportamentoBuscarAutor implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String nome = buscaAutor.getText();
			
			List<Autor> autores = dao.buscaAutores(nome);
			
				if(autores.isEmpty())
					System.out.println("Sem Autores");
				else
					printaAutores(autores);
			
		}
		
	}
	
	public Autor getAutorSelecionado() {
		
		int linha = tabela.getSelectedRow();

		Autor autor = (Autor) dtm.getValueAt(linha, 2);
		
		return autor;
		}
	
	class apagar implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			autoresId.clear();
			
			
			
			for(int i = 0; i<dtm.getRowCount();i++) {
				
				dtm.setValueAt("", i, 1);
				
			}
			
			JOptionPane.showMessageDialog(null, "Autores removidos com sucesso!");
			
			List<Integer> id = autoresId;
			for(int ids: id) {
			System.out.println(ids);
		}
			
		}
		
	}
	
class comportamentoInserirAutores implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			for(int ids : autoresId){
				int i = 1;
				dao.addLivro_Autor(isbn, ids, i);
				i++;
			}
			buscaAutoresComboBox();
			
			JOptionPane.showMessageDialog(null, "Autores inseridos com sucesso!");
			
		}
		
	}
	
	class click implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int ascii = 10003;
			if(e.getClickCount() == 1) {
				int id;
				Autor autor = getAutorSelecionado();
				id = autor.id;
				autoresId.add(id);
				System.out.println(id);
				int linha1 = tabela.getSelectedRow();
				linha = tabela.getSelectedRow();
				dtm.setValueAt((char)ascii, linha1, 1);
				
	}
				
				
			
			if(e.getClickCount() ==2) {
				

				
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

}
