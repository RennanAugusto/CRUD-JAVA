package view.janela.avisos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.dao.daoPostgres.DaoPostgres;

public class ConfirmacaoAutores extends JFrame{
	
	JLabel confirmar;
	JButton sim;
	JButton nao;
	int id;
	
	public ConfirmacaoAutores(int id) {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Confirmação");
		setLayout(new BorderLayout());
		setVisible(true);
		setSize(300,300);
		setLocationRelativeTo(null);
		this.id = id;
		
		JPanel panelConfirmar = new JPanel();
		panelConfirmar.setLayout(new BoxLayout(panelConfirmar,BoxLayout.PAGE_AXIS));
		
		confirmar = new JLabel("<html><body><p>Essa ação exluira todos os livros relacionados a <br> "
				+ "este Autor,deseja continuar?</p></body></html>");
		panelConfirmar.add(confirmar);
		add(panelConfirmar,BorderLayout.CENTER);
		
		JPanel botoes = new JPanel(new FlowLayout());
		
		sim = new JButton("Sim");
		sim.addActionListener(new isTrue());
		nao = new JButton("Não");
		nao.addActionListener(new isFalse());
		botoes.add(sim);
		botoes.add(nao);
		add(botoes,BorderLayout.PAGE_END);
		
		pack();
	}
	
	class isTrue implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			DaoPostgres.excluiAutor(id);
			JOptionPane.showMessageDialog(null, "Autor e Livros removidos com sucesso!");
			dispose();
		}
		
	}
	
	class isFalse implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			dispose();
			
		}
		
	}
	
	

}
