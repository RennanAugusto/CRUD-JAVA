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
import view.janela.AlteraLivro.comportamentoAlterar;

public class AlteraAutor extends JFrame {

	JLabel lNome;
	JTextField  tNome;
	JLabel lSobrenome;
	JTextField  tSobrenome;
	JButton alterar;
	
	
	int autorId;
	
	
	public AlteraAutor(String nome, String sobrenome, int autorId) {
		
		
		setLayout(new BorderLayout());
		setTitle("Altera Autor");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setSize(300,300);
		setLocationRelativeTo(null);
		setTitle("Alterar Autor");
		
		this.autorId = autorId;
		JPanel alterarDados= new JPanel();
		alterarDados.setLayout(
				new BoxLayout(alterarDados, BoxLayout.PAGE_AXIS));
		
		
		lNome = new JLabel("Insira o nome do autor: ");
		tNome = new JTextField(nome,20);
		lSobrenome = new JLabel("Insira o sobrenome do autor: ");
		tSobrenome = new JTextField(sobrenome,20);
		alterar = new JButton("Alterar");
		alterar.addActionListener(new comportamentoAlterar());
		
		alterarDados.add(lNome);
		alterarDados.add(tNome);
		alterarDados.add(lSobrenome);
		alterarDados.add(tSobrenome);
		alterarDados.add(alterar);
		add(alterarDados,BorderLayout.CENTER);
		
		
	}
	
	class comportamentoAlterar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			DaoPostgres.alteraAutor(tNome.getText(), tSobrenome.getText(), autorId);
			JOptionPane.showMessageDialog(null, "Autor alterado com sucesso!");
			
		}
		
	}

	
}
