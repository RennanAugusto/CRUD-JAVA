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
import model.Editora;
import model.Livro;
import model.dao.daoPostgres.DaoPostgres;


public class PanelBuscaEditoras extends JPanel{
	
	Object [][] data = new Object[0][2];
	Object[] coluna = {"Resultados:", "OBjeto"};
	DefaultTableModel dtm;
	JTable tabela;
	JanelaEditoras janelaEditoras;
	
	public JTextField busca;
	public JButton btnBuscar;
	
		public PanelBuscaEditoras(Color cor, String texto) {
			
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
			 
			 JPanel editoras = new JPanel();
			 janelaEditoras = new JanelaEditoras();
			 editoras.add(janelaEditoras);
			 add(editoras,BorderLayout.CENTER);
		
	}
	
public String getBusca() {
	return busca.getText();
}

public void printaEditoras(List<Editora> editoras) {
	
	dtm.setRowCount(0);
	for(Editora editora : editoras) {
		Object[] data = new Object[2];
		
		data[0] = editora.name;
		data[1] = editora;
		dtm.addRow(data);
	}
}


public Editora getEditoraSelecionado() {
	
	int linha = tabela.getSelectedRow();

	Editora editora =  (Editora) dtm.getValueAt(linha, 1);
	
	return editora;
	}

	public void comportamentoBuscar(ActionListener al) {
		busca.addActionListener(al);
		btnBuscar.addActionListener(al);
	}

	public void mostrarEditora(MouseListener al) {
		tabela.addMouseListener(al);
	}
	
	public void janelaEditorasPrintaEditora(Editora editora) {
		janelaEditoras.printaEditoras(editora);
	}
	
	public void JanelaEditorasPrintaLivros() {
		janelaEditoras.printaLivros();
	}
	
	public int janelaEditorasGetId() {
		return janelaEditoras.getId();
	}
	
	public String janelaEditorasGetNome() {
		return janelaEditoras.getNome();
	}
	
	public String janelaEditorasGetUrl() {
		return janelaEditoras.getUrl();
	}
	
	public void janelaEditorascomportamentoExcluirEditora(ActionListener al) {
		janelaEditoras.comportamentoExcluir(al);
	}
	
	public void janelaEditoraComportamentoAlterarEditora(ActionListener al) {
		janelaEditoras.comportamentoAlterar(al);
	}
}