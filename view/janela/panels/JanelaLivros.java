package view.janela.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Livro;
import model.dao.daoPostgres.DaoPostgres;
import view.janela.AlteraLivro;

public class JanelaLivros extends JPanel {
//	
//	Object [][] data = new Object[0][1];
//	Object[] coluna = {"Autores:"};
//	DefaultTableModel dtm;
//	JTable tabela;
	
	JLabel isbn;
	JLabel autores;
	JLabel preco;
	JLabel nome;
	JLabel espaco = new JLabel(" ");
	JButton excluir;
	JButton alterar;
	String aIsbn;
	String aTitulo;
	String aPreco;
	
	JPanel livroDados;
	JPanel panelAutores;
	
	ArrayList <JLabel> label = new ArrayList<JLabel>();
	
	

	List<String> nomesAutores = new ArrayList<String>();
	AlteraLivro alteraLivro;
	
	static final Font font = new Font("sans serif", Font.BOLD, 16);
	static final Font font2 = new Font("sans serif", Font.BOLD, 12);
	
	public JanelaLivros() {
		
		setLayout(new BorderLayout(5,5));
		setPreferredSize(new Dimension(400, 400));
		
		 livroDados= new JPanel();
		livroDados.setLayout(
				new BoxLayout(livroDados, BoxLayout.PAGE_AXIS));
		
		nome = new JLabel("LIVRO: ");
		nome.setFont(font);
		isbn = new JLabel("ISBN: ");
		isbn.setFont(font2);
		autores = new JLabel("AUTORES: ");
		autores.setFont(font2);
		preco = new JLabel("PREÇO: ");
		preco.setFont(font2);
		
//		dtm = new DefaultTableModel(data,coluna);
//		 tabela = new JTable(dtm) {
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//		 };
//		 
//		 tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);;
//		 tabela.getColumnModel().getColumn(0).setPreferredWidth(400);
//		 TableColumnModel tcm = tabela.getColumnModel();
//		 JScrollPane scrollPane = new JScrollPane(tabela);
		
		livroDados.add(nome);
		livroDados.add(espaco);
		livroDados.add(espaco);
		livroDados.add(isbn);
		livroDados.add(preco);
		livroDados.add(espaco);
		livroDados.add(autores);
		panelAutores = new JPanel();
		panelAutores.setLayout(new BoxLayout(panelAutores,BoxLayout.PAGE_AXIS));
;		livroDados.add(panelAutores);
//		livroDados.add(scrollPane);
		
		
		JPanel btn = new JPanel(new FlowLayout());
		livroDados.add(espaco);
		excluir = new JButton("Excluir Livro");
		btn.add(excluir);
		
		alterar = new JButton("Alterar Livro");
		btn.add(alterar);
		add(livroDados,BorderLayout.CENTER);
		add(btn, BorderLayout.PAGE_END);
		
		
	}
	
			
	
			public void printaLivros(Livro livro) {
				
				isbn.setText("ISBN: " + livro.isbn); 
				
				preco.setText("PREÇO: R$" + livro.preco); 
				
				nome.setText("LIVRO: " + livro.title);
				aIsbn = livro.isbn;
				aTitulo = livro.title;
				aPreco = livro.preco;
				
				label.clear();
				
				panelAutores.removeAll();
				for(String autor : livro.nomeAutor) {
					JLabel autores = new JLabel(autor);
					panelAutores.add(autores);
				}
				
				
				
//			int rows =	dtm.getRowCount();
//			for(int i = 0; i<rows; i++) {
//				dtm.removeRow(i);
//			}
//			
//			autores.setText("AUTORES: " + livro.nomeAutor); 
//			for(String autores : livro.nomeAutor) {
//				Object[] data = new Object[1];
//				
//				data[0] = autores;
//				dtm.addRow(data);
//			}
			
			
			
			
	}
			
			public String getIsbn() {
				return aIsbn;
			}
			
			public String getTitulo() {
				return aTitulo;
			}
			
			public String getPreco() {
				return aPreco;
			}
			
			public void comportamentoExcluir(ActionListener al) {
				excluir.addActionListener(al);
			}
			
			public void comportamentoAlterar(ActionListener al) {
				alterar.addActionListener(al);
			}

}