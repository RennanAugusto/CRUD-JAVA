package view.janela.panels;


import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Autor;
import model.Livro;
import model.dao.daoPostgres.DaoPostgres;
import view.janela.AlteraAutor;
import view.janela.avisos.ConfirmacaoAutores;

public class JanelaAutores extends JPanel {
	
	Object [][] data = new Object[0][1];
	Object[] coluna = {"Livros:"};
	DefaultTableModel dtm;
	JTable tabela;
		
		JLabel nome;
		JLabel autorId;
		JLabel espaco = new JLabel(" ");
		JButton excluir;
		JButton alterar;
		ConfirmacaoAutores confirmar;
		
		String aNome;
		String sobrenome;
		int id;
		
		AlteraAutor alteraAutor;
		
		static final Font font = new Font("sans serif", Font.BOLD, 18);
		static final Font font2 = new Font("sans serif", Font.BOLD, 16);
		
		public JanelaAutores() {
			
			setLayout(new BorderLayout(5,5));
			setPreferredSize(new Dimension(400, 400));
			
			JPanel autorDados= new JPanel();
			autorDados.setLayout(
					new BoxLayout(autorDados, BoxLayout.PAGE_AXIS));
			
			nome = new JLabel("NOME DO AUTOR: ");
			nome.setFont(font);
			autorId = new JLabel("ID DO AUTOR: ");
			autorId.setFont(font2);
	
			autorDados.add(nome);
			autorDados.add(espaco);
			autorDados.add(espaco);
			autorDados.add(autorId);
			
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
			 
			 autorDados.add(scrollPane);
			
			excluir = new JButton("ExcluirAutor");
			autorDados.add(excluir);
			
			alterar = new JButton("Alterar");
			autorDados.add(alterar);
			
			add(autorDados);
			
			
		}
		
				public void printaAutores(Autor autor) {
					
					nome.setText("NOME DO AUTOR: " + autor.pNome + " " + autor.nome);
				autorId.setText("ID DO AUTOR: " + autor.id); 
				id = autor.id;
				aNome = autor.pNome;
				sobrenome = autor.nome;
				
		}
				
				public int getId() {
					return id;
				}
				
				public String getNome() {
					return aNome;
				}
				
				public String getSobrenome() {
					return sobrenome;
				}
				
				public void printaLivros() {
					
					dtm.setRowCount(0);
					
				     List<Livro> livros = DaoPostgres.autorBuscaLivros(id);
					
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
