package model;

import java.util.ArrayList;
import java.util.List;

public class Livro {
	
	public String title;
	public String isbn;
	public List<String> nomeAutor = new ArrayList<String>();
	public String preco;
	
	public Livro(String aTitle, String aIsbn, List<String> aNomeAutor, String aPreco) {
		
		this.title = aTitle;
		this.isbn = aIsbn;
		this.nomeAutor = aNomeAutor;
		this.preco = aPreco;
		
	}

}
