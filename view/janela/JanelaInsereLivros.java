package view.janela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Autor;
import model.Editora;
import model.dao.daoPostgres.DaoPostgres;
import view.janela.JanelaInsereAutores.comportamentoInserir;
import view.janela.panels.JanelaAutores;
import view.janela.panels.PanelBuscaAutores;

public class JanelaInsereLivros extends JFrame {
	
	DaoPostgres dao = new DaoPostgres();
	
	JLabel titulo = new JLabel("Titulo do livro:");
	JTextField title;
	JLabel lIsbn = new JLabel("Isbn do livro:");;
	JTextField isbn;
	public static JComboBox publisher;
	JLabel preco = new JLabel("Preço do livro: ");;
	JTextField price;
	JTextField authorId;
	JLabel editora = new JLabel("Escolha a editora");
	JButton inserir;
	JButton buscar;
	JButton limparAutoresSelecionados;
	
	Object [][] data = new Object[0][3];
	Object[] coluna = {"Resultados:", "Selecionado","OBjeto"};
	DefaultTableModel dtm;
	JTable tabela;
	JanelaAutores janelaAutores;
	
	List<Integer> autoresId;
	
	
	
	int linha;
	int publisher_id;
	
	
	public JanelaInsereLivros() {
			
			setLayout(new BorderLayout());
			setTitle("Insira um livro");
			setSize(300,500);
			setVisible(true);
			
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			setTitle("Insira um livro");
			
			JPanel insere = new JPanel();
			insere.setLayout(new BoxLayout(insere,BoxLayout.PAGE_AXIS));
			title = new JTextField("Titulo do Livro");
			title.addFocusListener(new FocusListener() {
			    @Override
			    public void focusGained(FocusEvent e) {
			        if (title.getText().equals("Titulo do Livro")) {
			        	title.setText("");
			        	title.setForeground(Color.BLACK);
			        }
			    }
			    @Override
			    public void focusLost(FocusEvent e) {
			        if (title.getText().isEmpty()) {
			        	title.setForeground(Color.GRAY);
			        	title.setText("Titulo do Livro");
			        }
			    }
			    });
			
			isbn = new JTextField("Isbn do Livro");
			isbn.addFocusListener(new FocusListener() {
			    @Override
			    public void focusGained(FocusEvent e) {
			        if (isbn.getText().equals("Isbn do Livro")) {
			        	isbn.setText("");
			        	isbn.setForeground(Color.BLACK);
			        }
			    }
			    @Override
			    public void focusLost(FocusEvent e) {
			        if (isbn.getText().isEmpty()) {
			        	isbn.setForeground(Color.GRAY);
			        	isbn.setText("Isbn do Livro");
			        }
			    }
			    });
			
//			publisher = new JTextField("Id da Editora");
//			publisher.addFocusListener(new FocusListener() {
//			    @Override
//			    public void focusGained(FocusEvent e) {
//			        if (publisher.getText().equals("Id da Editora")) {
//			        	publisher.setText("");
//			        	publisher.setForeground(Color.BLACK);
//			        }
//			    }
//			    @Override
//			    public void focusLost(FocusEvent e) {
//			        if (publisher.getText().isEmpty()) {
//			        	publisher.setForeground(Color.GRAY);
//			        	publisher.setText("Id da Editora");
//			        }
//			    }
//			    });
			
			publisher = new JComboBox();
			limparAutoresSelecionados = new JButton("Limpar Autores");
			limparAutoresSelecionados.addActionListener(new apagar());
			
			price = new JTextField("Preço do Livro");
			price.addFocusListener(new FocusListener() {
			    @Override
			    public void focusGained(FocusEvent e) {
			        if (price.getText().equals("Preço do Livro")) {
			        	price.setText("");
			        	price.setForeground(Color.BLACK);
			        }
			    }
			    @Override
			    public void focusLost(FocusEvent e) {
			        if (price.getText().isEmpty()) {
			        	price.setForeground(Color.GRAY);
			        	price.setText("Preço do Livro");
			        }
			    }
			    });
			
			authorId = new JTextField("Busque pelo nome do autor");
			authorId.addActionListener(new comportamentoBuscar());
			authorId.addFocusListener(new FocusListener() {
			    @Override
			    public void focusGained(FocusEvent e) {
			        if (authorId.getText().equals("Busque pelo nome do autor")) {
			        	authorId.setText("");
			        	authorId.setForeground(Color.GRAY);
			        }
			    }
			    @Override
			    public void focusLost(FocusEvent e) {
			        if (authorId.getText().isEmpty()) {
			        	authorId.setForeground(Color.GRAY);
			        	authorId.setText("Busque pelo nome do autor");
			        }
			    }
			    });
			
			buscar = new JButton("Buscar");
			buscar.addActionListener(new comportamentoBuscar());
			dtm = new DefaultTableModel(data,coluna);
			 tabela = new JTable(dtm) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			 };
			 
			 tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);;
			 tabela.getColumnModel().getColumn(0).setPreferredWidth(350);
			 tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
			 tabela.getColumnModel().getColumn(2).setPreferredWidth(0);
			 tabela.addMouseListener(new click());
			 TableColumnModel tcm = tabela.getColumnModel();
			 tcm.removeColumn( tcm.getColumn(2) );
			 JScrollPane scrollPane = new JScrollPane(tabela);
			 add(scrollPane,BorderLayout.LINE_START);
			 
			 autoresId = new ArrayList<Integer>();
			 
			 JPanel panelBusca = new JPanel(new FlowLayout());
			 panelBusca.add(authorId);
			 panelBusca.add(buscar);
			 panelBusca.add(limparAutoresSelecionados);
			 
				titulo.setAlignmentX(0.0F);
				title.setAlignmentX(0.0F);
				lIsbn.setAlignmentX(0.0F);
				isbn.setAlignmentX(0.0F);
				preco.setAlignmentX(0.0F);
				price.setAlignmentX(0.0F);
				editora.setAlignmentX(0.0F);
				publisher.setAlignmentX(0.0F);
			insere.add(titulo);
			insere.add(title);
			insere.add(lIsbn);
			insere.add(isbn);
			insere.add(preco);
			insere.add(price);
			insere.add(editora);
			insere.add(publisher);
			insere.add(panelBusca);
			insere.add(scrollPane);
			buscaAutomaticaAutores();
			buscarEditora();
			add(insere, BorderLayout.CENTER);
			
			inserir = new JButton("Inserir");
			inserir.addActionListener(new comportamentoInserir());
			add(inserir, BorderLayout.PAGE_END);
			
			pack();
		
	}
	
class comportamentoInserir implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String nome;
			
			List<Integer> id = autoresId;
			nome = (String) publisher.getSelectedItem();
			System.out.println(nome);
			
			publisher_id = dao.comboBoxEditoraId(nome);
			System.out.println(publisher_id);
			
			dao.addLivro(title.getText(), isbn.getText(), publisher_id, price.getText());
			
			int i = 1;
			
			for(int ids : id){
				dao.addLivro_Autor(isbn.getText(), ids, i);
				i++;
			}
			
			JOptionPane.showMessageDialog(null, "Livro inserido com sucesso!");
			
		}
		
	}

class apagar implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		autoresId.clear();
		
		
		
		for(int i = 0; i<dtm.getRowCount();i++) {
			
			dtm.setValueAt("", i, 1);
			
		}
		
		List<Integer> id = autoresId;
		for(int ids: id) {
		System.out.println(ids);
	}
		
	}
	
}

public void printaAutores(List<Autor> autores) {
	
	dtm.setRowCount(0);
	for(Autor autor : autores) {
		Object[] data = new Object[3];
		
		data[0] = autor.pNome + " "+ autor.nome;
		data[2] = autor;
		dtm.addRow(data);
	}
}

public void buscarEditora() {
	
	
	
	List<Editora> editoras = dao.comboBoxEditora();

	for(Editora editora: editoras) {
		
		publisher.addItem(editora.name);
		
		
	}
	

}

public void buscaAutomaticaAutores() {
	
	
	List<Autor> autores = dao.buscaAutores("");
	
		if(autores.isEmpty())
			System.out.println("Sem Autores");
		else
			printaAutores(autores);
	
}



public Autor getAutorSelecionado() {
	
	int linha = tabela.getSelectedRow();

	Autor autor = (Autor) dtm.getValueAt(linha, 2);
	
	return autor;
	}


class comportamentoBuscar implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nome = authorId.getText();
		
		List<Autor> autores = dao.buscaAutores(nome);
		
			if(autores.isEmpty())
				System.out.println("Sem Autores");
			else
				printaAutores(autores);
		
	}
	
}

class comportamentoBuscarEditora implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nome = authorId.getText();
		
		List<Autor> autores = dao.buscaAutores(nome);
		
			if(autores.isEmpty())
				System.out.println("sem livros");
			else
				printaAutores(autores);
		
	}
	
}



class click implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int ascii = 10003;
		if(e.getClickCount() == 1) {
			int id;
			Autor autor = getAutorSelecionado();
			id = autor.id;
			autoresId.add(id);
			System.out.println(id);
			int linha1 = tabela.getSelectedRow();
			linha = tabela.getSelectedRow();
			dtm.setValueAt((char)ascii, linha1, 1);
			
}
			
			
		
		if(e.getClickCount() ==2) {
			

			
		}
		
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	
}

}
