package view.janela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Livro;
import model.dao.daoPostgres.DaoPostgres;

public class JanelaInsereEditoras extends JFrame {
	
	DaoPostgres dao = new DaoPostgres();
	
	public static String query;
	
	JTextField name;
	JTextField url;
	JLabel header;
	JButton inserir;
	
	public JanelaInsereEditoras() {
		
		setLayout(new BorderLayout());
		setTitle("Insira uma editora");
		setSize(300,500);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		header = new JLabel("Insira uma Editora");
		add(header, BorderLayout.PAGE_START);
		
		JPanel insere = new JPanel();
		insere.setLayout(
				new BoxLayout(insere, BoxLayout.PAGE_AXIS));
		
		
		name = new JTextField("Nome da Editora");
		name.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (name.getText().equals("Nome da Editora")) {
		        	name.setText("");
		        	name.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (name.getText().isEmpty()) {
		        	name.setForeground(Color.GRAY);
		        	name.setText("Nome da Editora");
		        }
		    }
		    });
		
		url = new JTextField("URL da Editora");
		url.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (url.getText().equals("URL da Editora")) {
		        	url.setText("");
		        	url.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (url.getText().isEmpty()) {
		        	url.setForeground(Color.GRAY);
		        	url.setText("URL da Editora");
		        }
		    }
		    });
		
		insere.add(name);
		insere.add(url);
		add(insere, BorderLayout.CENTER);
		
		inserir = new JButton("Inserir");
		inserir.addActionListener(new comportamentoInserir());
		add(inserir, BorderLayout.PAGE_END);
		
		pack();
		
		
	}
	
class comportamentoInserir implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			dao.addEditora( name.getText(), url.getText());
			JOptionPane.showMessageDialog(null, "Editora inserida com sucesso!");
			
		}
		
	}
	
		
			
			
}
