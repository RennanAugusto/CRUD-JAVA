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

import model.Autor;
import model.Livro;
import model.dao.daoPostgres.DaoPostgres;


public class PanelBuscaAutores extends JPanel{
	
	Object [][] data = new Object[0][2];
	Object[] coluna = {"Resultados:", "OBjeto"};
	DefaultTableModel dtm;
	JTable tabela;
	JanelaAutores janelaAutores;
	
	public JTextField busca;
	public JButton btnBuscar;
	
	
		public PanelBuscaAutores(Color cor, String texto) {
			
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(400, 400));
			add(new JLabel(texto));
			setBackground(cor);
			
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
			 add(scrollPane,BorderLayout.LINE_START);
			 
			 JPanel autores = new JPanel();
			 janelaAutores = new JanelaAutores();
			 autores.add(janelaAutores);
			 add(autores,BorderLayout.CENTER);
		
	}
		
		public String getBusca() {
			return busca.getText();
		}
		
		public void printaAutores(List<Autor> autores) {
			
			dtm.setRowCount(0);
			for(Autor autor : autores) {
				Object[] data = new Object[2];
				
				data[0] = autor.pNome + " " + autor.nome;
				data[1] = autor;
				dtm.addRow(data);
			}
		}
		
		
		public Autor getAutorSelecionado() {
			
			int linha = tabela.getSelectedRow();

			Autor autor = (Autor) dtm.getValueAt(linha, 1);
			
			return autor;
			}
		
		public void comportamentoBuscar(ActionListener al) {
			busca.addActionListener(al);
			btnBuscar.addActionListener(al);
		}
		
		public void comportamentoMostrar(MouseListener al) {
			tabela.addMouseListener(al);
		}
		
		//listeners e metodos janelaAutores
		public void JanelaAutoresPrintaAutores(Autor autor) {
			janelaAutores.printaAutores(autor);
			}
		
		public void JanelaAutoresPrintaLivros() {
			janelaAutores.printaLivros();
		}
		
		public void janelaAutoresComportamentoExcluir(ActionListener al) {
			janelaAutores.comportamentoExcluir(al);
		}
		
		public int janelaAutoresGetId() {
			return janelaAutores.getId();
		}
		
		public String janelaAutoresGetNome() {
			return janelaAutores.getNome();
		}
		
		public String JanelaAutoresGetSobrenome() {
			return janelaAutores.getSobrenome();
		}
		
		public void janelaAutoresComportamentoAlterar(ActionListener al) {
			janelaAutores.comportamentoAlterar(al);
		}
		
		
		
		
}