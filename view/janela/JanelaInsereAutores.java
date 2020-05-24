package view.janela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.dao.daoPostgres.DaoPostgres;
import view.View;

public class JanelaInsereAutores extends JFrame{
	
	DaoPostgres dao = new DaoPostgres();

	public static String query;
	
	JTextField firstName;
	JTextField name;
	JLabel header;
	JButton inserir;
	
	public JanelaInsereAutores() {
		
		setLayout(new BorderLayout());
		setSize(300,300);
		setTitle("Insira um Autor");
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		header = new JLabel("Insira um Autor");
		add(header, BorderLayout.PAGE_START);
		
		JPanel insere = new JPanel();
		
		insere.setLayout(
				new BoxLayout(insere, BoxLayout.PAGE_AXIS));
		
		firstName = new JTextField("Insira o nome do autor");
		
		firstName.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (firstName.getText().equals("Insira o nome do autor")) {
		        	firstName.setText("");
		        	firstName.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (firstName.getText().isEmpty()) {
		        	firstName.setForeground(Color.GRAY);
		        	firstName.setText("Insira o nome do autor");
		        }
		    }
		    });
		
		name = new JTextField("Insira o sobrenome do autor");
		name.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (name.getText().equals("Insira o sobrenome do autor")) {
		        	name.setText("");
		        	name.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (name.getText().isEmpty()) {
		        	name.setForeground(Color.GRAY);
		        	name.setText("Insira o sobrenome do autor");
		        }
		    }
		    });
		
		insere.add(firstName);
		insere.add(name);
		add(insere, BorderLayout.CENTER);
		inserir = new JButton("Inserir");
		inserir.addActionListener(new comportamentoInserir());
		add(inserir, BorderLayout.PAGE_END);
		pack();
		
}
	
class comportamentoInserir implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			dao.addAuthor( firstName.getText(), name.getText());
			JOptionPane.showMessageDialog(null, "Autor inserido com sucesso!");
			
		}
		
	}
	

}
