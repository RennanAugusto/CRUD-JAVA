package model.dao;

import java.util.List;

import model.Autor;
import model.Editora;
import model.Livro;

public interface Dao {
	
	public List<Livro> buscaLivros(String titulo);
	public List<Autor> buscaAutores(String nome);
	public List<Editora> buscaEditoras(String nome);

}
