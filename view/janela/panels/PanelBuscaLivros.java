package view.janela.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Livro;
import model.dao.daoPostgres.DaoPostgres;

public class PanelBuscaLivros extends JPanel{
	
	Object [][] data = new Object[0][2];
	Object[] coluna = {"Resultados:", "Objeto"};
	DefaultTableModel dtm;
	JTable tabela;
	
	JanelaLivros janelaLivros;
	
	public JTextField busca;
	public JButton btnBuscar;
	
		public PanelBuscaLivros( String texto) {
			
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(400, 400));
			add(new JLabel(texto),BorderLayout.LINE_START);
			
			JPanel panelBusca = new JPanel(new FlowLayout());
			busca = new JTextField(20);
			btnBuscar = new JButton("Buscar");
			panelBusca.add(busca);
			panelBusca.add(btnBuscar);
			add(panelBusca, BorderLayout.PAGE_START);
			
			dtm = new DefaultTableModel(data,coluna);
			 tabela = new JTable(dtm) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			 };
			 
			 tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);;
			 tabela.getColumnModel().getColumn(0).setPreferredWidth(400);
			 tabela.getColumnModel().getColumn(1).setPreferredWidth(0);
			 TableColumnModel tcm = tabela.getColumnModel();
			 tcm.removeColumn( tcm.getColumn(1) );
			 JScrollPane scrollPane = new JScrollPane(tabela);
			 add(scrollPane, BorderLayout.LINE_START);
			 
			 JPanel livros = new JPanel();
			 janelaLivros = new JanelaLivros();
			 livros.add(janelaLivros);
			 add(livros,BorderLayout.CENTER);
		
	}
		
		public String getBusca() {
			return busca.getText();
		}
	
		public void mostrarErroNaoExisteLivro() {
			System.out.println("N�o tem livro, esta bem?");
		}
		
		public void printaLivros(List<Livro> livros) {
			
			dtm.setRowCount(0);
			for(Livro livro : livros) {
				Object[] data = new Object[2];
				
				data[0] = livro.title;
				data[1] = livro;
				dtm.addRow(data);
			}
		}
		
		
		
		
		public Livro getLivroSelecionado() {
			
			int linha = tabela.getSelectedRow();

			Livro livro = (Livro) dtm.getValueAt(linha, 1);
			
			return livro;
			}
		
	public void buscaLivro(ActionListener al) {
		busca.addActionListener(al);
		btnBuscar.addActionListener(al);
	}

	public void mostraLivroSelecionado(MouseListener al) {
		tabela.addMouseListener(al);
	}
	
	public void janelaLivrosPrintaLivros(Livro livro) {
		janelaLivros.printaLivros(livro);
	}
	
	public void janelaLivroscomportamentoExcluir(ActionListener al) {
		janelaLivros.comportamentoExcluir(al);
	}
	
	public void janelaLivrosComportamentoAlterar(ActionListener al) {
		janelaLivros.comportamentoAlterar(al);
	}
	
	public String janelaLivrosGetIsbn() {
		return janelaLivros.getIsbn();
	}
	
	public String janelaLivrosGetTitulo() {
		return janelaLivros.getTitulo();
	}
	
	public String janelaLivrosGetPreco() {
		return janelaLivros.getPreco();
	}	
	
		
		
	
}