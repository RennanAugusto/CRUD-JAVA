package view.janela.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Editora;
import model.Livro;
import model.dao.daoPostgres.DaoPostgres;
import view.janela.AlteraEditora;
import view.janela.avisos.ConfirmacaoEditoras;

public class JanelaEditoras extends JPanel {
	
	Object [][] data = new Object[0][1];
	Object[] coluna = {"Livros:"};
	DefaultTableModel dtm;
	JTable tabela;
	
	JLabel nome;
	JLabel publisherId;
	JLabel url;
	JLabel espaco = new JLabel(" ");
	JLabel livros;
	JButton buscar;
	JTextField buscaLivros;
	JButton excluir;
	JButton alterar;
	
	
	AlteraEditora alteraEditora;
	ConfirmacaoEditoras confirmar;
	
	public int id;
	String aNome;
	String aUrl;
	
	static final Font font = new Font("sans serif", Font.BOLD, 18);
	static final Font font2 = new Font("sans serif", Font.BOLD, 16);
	
	public JanelaEditoras() {
		
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(400, 400));
		
		JPanel editoraDados= new JPanel();
		editoraDados.setLayout(
				new BoxLayout(editoraDados, BoxLayout.PAGE_AXIS));
		
		nome = new JLabel("NOME DA EDITORA: ");
		nome.setFont(font);
		publisherId = new JLabel("ID DA EDITORA: ");
		publisherId.setFont(font2);
		url = new JLabel("SITE: ");
		url.setFont(font2);
		livros = new JLabel("Livros:");
		livros.setFont(font2);
		

		editoraDados.add(nome);
		editoraDados.add(espaco);
		editoraDados.add(espaco);
		editoraDados.add(publisherId);
		editoraDados.add(url);
		
		
		
		excluir = new JButton("Excluir Editora");
		editoraDados.add(excluir);
		
		alterar = new JButton("Alterar");
		editoraDados.add(alterar);
		
		editoraDados.add(livros);
		
		add(editoraDados);
		
		JPanel mostraLivros = new JPanel();
		mostraLivros.setLayout(new BoxLayout(mostraLivros,BoxLayout.PAGE_AXIS));
		
		
		dtm = new DefaultTableModel(data,coluna);
		 tabela = new JTable(dtm) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		 };
		 
		 tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);;
		 tabela.getColumnModel().getColumn(0).setPreferredWidth(400);
		 TableColumnModel tcm = tabela.getColumnModel();
		 JScrollPane scrollPane = new JScrollPane(tabela);
		 mostraLivros.add(scrollPane);
		 add(mostraLivros);
		
		
		
		
	}
	
			public void printaEditoras(Editora editora) {
				
				nome.setText("NOME DA EDITORA: " + editora.name);
				publisherId.setText("ID DA EDITORA: " + editora.id); 
				url.setText("SITE: " + editora.url);
				id = editora.id;
				aNome = editora.name;
				aUrl = editora.url;
	}
			
			public int getId() {
				return id;
			}
			
			public String getNome() {
				return aNome;
			}
			
			public String getUrl() {
				return aUrl;
			}
			
			public void printaLivros() {
				
				dtm.setRowCount(0);
				
			     List<Livro> livros = DaoPostgres.editoraBuscaLivros(id);
				
				for(Livro livro : livros) {
					Object[] data = new Object[1];
					
					data[0] = livro.title;
					dtm.addRow(data);
				}
			}
			
			public void comportamentoExcluir(ActionListener al) {
				excluir.addActionListener(al);
			}
			
			public void comportamentoAlterar(ActionListener al) {
				alterar.addActionListener(al);
			}

}
