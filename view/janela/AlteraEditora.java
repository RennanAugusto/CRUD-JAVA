package view.janela;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.dao.daoPostgres.DaoPostgres;
import view.janela.AlteraAutor.comportamentoAlterar;

public class AlteraEditora extends JFrame {
	
	JLabel lNome;
	JTextField  tNome;
	JLabel lUrl;
	JTextField  tUrl;
	JButton alterar;
	
	
	int editoraId;
	
	
	public AlteraEditora(String nome, String url, int editoraId) {
		
		
		setLayout(new BorderLayout());
		setTitle("Altera Editora");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setSize(300,300);
		
		this.editoraId = editoraId;
		JPanel alterarDados= new JPanel();
		alterarDados.setLayout(
				new BoxLayout(alterarDados, BoxLayout.PAGE_AXIS));
		
		
		lNome = new JLabel("Insira o nome da Editora: ");
		tNome = new JTextField(nome);
		lUrl = new JLabel("Insira a URL da Editora: ");
		tUrl = new JTextField(url);
		alterar = new JButton("Alterar");
		alterar.addActionListener(new comportamentoAlterar());
		
		alterarDados.add(lNome);
		alterarDados.add(tNome);
		alterarDados.add(lUrl);
		alterarDados.add(tUrl);
		alterarDados.add(alterar);
		add(alterarDados,BorderLayout.CENTER);
		
		pack();
		
	}
	
	class comportamentoAlterar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			DaoPostgres.alteraEditora(tNome.getText(), tUrl.getText(), editoraId);
			JOptionPane.showMessageDialog(null, "Editora alterado com sucesso!");
			
		}
		
	}

}
