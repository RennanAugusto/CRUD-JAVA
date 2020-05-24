package model.dao.daoPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Autor;
import model.Editora;
import model.Livro;
import model.dao.Dao;
import model.dao.daoPostgres.Conexao;

public class DaoPostgres implements Dao {


	public List<Livro> buscaLivros(String titulo){
		String query = "SELECT b.title,b.isbn,b.price,a.name,a.fname FROM books b "
				+ "INNER JOIN booksauthors ba ON b.isbn = ba.isbn "
				+ "INNER JOIN authors a ON ba.author_id = a.author_id WHERE LOWER (title) LIKE LOWER(?)"  ;
		
		List<Livro> livros = new ArrayList<>();
		
		try(
				Connection c = Conexao.getConexao();
				PreparedStatement pstm = c.prepareStatement(query);
				){
			
			pstm.setString(1, "%"+titulo+"%");
			ResultSet rs = pstm.executeQuery();
			
			Livro livroAnterior = null;
			while(rs.next()) {
				List<String> autorLista = new ArrayList<String>();
				
				String title = rs.getString("title").trim();
				String isbn = rs.getString("isbn");
				String autor = rs.getString("fname").trim() + " " + rs.getString("name").trim();
				String preco = rs.getString("price");
				autorLista.add(autor);
				Livro livro = new Livro(title,isbn,autorLista,preco);
				
				
				
				 if(livroAnterior == null) {
					System.out.println("toqui");
					livroAnterior = livro;
					livros.add(livro);
				}
				
				 else if((livroAnterior.title).equals(livro.title)) {
					System.out.println("entrei no elseif");
					
					livroAnterior.nomeAutor.add(autor);
					
				}
				
				 else {
					System.out.println("Entrei no else");
					
					livros.add(livro);
					livroAnterior=livro;
					
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return livros;
	}
	
	public List<Autor> buscaAutores(String nome){
		String query = "SELECT * FROM authors "
				+ "WHERE LOWER(fname) LIKE LOWER(?) OR LOWER(name) LIKE LOWER(?)"  ;
		
		List<Autor> autores = new ArrayList<>();
		
		try(
				Connection c = Conexao.getConexao();
				PreparedStatement pstm = c.prepareStatement(query);
				){
			
			pstm.setString(1, "%"+nome+"%");
			pstm.setString(2, "%"+nome+"%");
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				String pNome = rs.getString("fname").trim();
				String aNome = rs.getString("name").trim();
				int id = rs.getInt("author_id");

				autores.add(new Autor(pNome, aNome,id));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return autores;
	}
	
	public List<Editora> buscaEditoras(String nome){
		String query = "SELECT * FROM publishers "
				+ "WHERE LOWER(name) LIKE LOWER(?)"  ;
		
		List<Editora> editoras = new ArrayList<>();
		
		try(
				Connection c = Conexao.getConexao();
				PreparedStatement pstm = c.prepareStatement(query);
				){
			
			pstm.setString(1, "%"+nome+"%");
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				String aNome = rs.getString("name").trim();
				int id = rs.getInt("publisher_id");
				String url = rs.getString("url").trim();
				
				editoras.add(new Editora(id, aNome, url));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return editoras;
	}
	
	public static List<Editora> comboBoxEditora(){
		String query = "SELECT * FROM publishers";
		
		List<Editora> editoras = new ArrayList<>();
		
		try(
				Connection c = Conexao.getConexao();
				PreparedStatement pstm = c.prepareStatement(query);
				){
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				String aNome = rs.getString("name").trim();
				int id = rs.getInt("publisher_id");
				String url = rs.getString("url").trim();
				
				editoras.add(new Editora(id, aNome, url));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return editoras;
	}
	
	public static int comboBoxEditoraId(String nome){
		String query = "SELECT publisher_id FROM publishers"
				+ " WHERE name = ?";
		
		
		int id = 0;
		try(
				Connection c = Conexao.getConexao();
				PreparedStatement pstm = c.prepareStatement(query);
				){
			
			pstm.setString(1,nome);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				id = rs.getInt("publisher_id");
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
		
	}
	
	public static boolean addEditora(String name, String url) {
		boolean resultado = false;
		try(Connection c = Conexao.getConexao()){
			
			String query = "INSERT INTO publishers "
					+ "VALUES(DEFAULT,?,?)";
			PreparedStatement pstm = c.prepareStatement(query);
	
			pstm.setString(1, name);
			pstm.setString(2, url);
			resultado = (pstm.executeUpdate()>0 ? true : false);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public static boolean addAuthor( String firstName, String name) {
		boolean resultado = false;
		try(Connection c = Conexao.getConexao()){
			
			String query = "INSERT INTO authors "
					+ "VALUES(DEFAULT,?,?)";
			PreparedStatement pstm = c.prepareStatement(query);
		
			pstm.setString(1, name);
			pstm.setString(2, firstName);
			resultado = (pstm.executeUpdate()>0 ? true : false);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public static boolean addLivro_Autor(String isbn, int author_id, int seq_no) {
		boolean resultado = false;
		try(Connection c = Conexao.getConexao()){
			
			String query = "INSERT INTO booksauthors "
					+ "VALUES(?,?,?)";
			PreparedStatement pstm = c.prepareStatement(query);
			
			pstm.setString(1,isbn);
			pstm.setInt(2, author_id);
			pstm.setInt(3, seq_no);
			resultado = (pstm.executeUpdate()>0 ? true : false);
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return resultado;
	}
	
	public static boolean addLivro(String title, String isbn, int publisher_id, String price) {
		boolean resultado = false;
		try(Connection c = Conexao.getConexao()){
			
			String query = "INSERT INTO books "
					+ "VALUES(?,?,?,?)";
			PreparedStatement pstm = c.prepareStatement(query);
			
			float aPrice = Float.parseFloat(price);
			pstm.setString(1,title);
			pstm.setString(2, isbn);
			pstm.setInt(3, publisher_id);
			pstm.setFloat(4, aPrice);
			
			resultado = (pstm.executeUpdate()>0 ? true : false);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public static int excluiLivro(String isbn) {
		
		
		String query = "DELETE FROM books"
				+ " WHERE isbn = ?";
		int rows = 0;
		try(Connection c = Conexao.getConexao()){
			
			
			PreparedStatement pstm = c.prepareStatement(query);
			pstm.setString(1, isbn);
			rows = pstm.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rows;
	}
	
	public static int excluiLivroAutor(String isbn) {
		
		String query = "DELETE FROM booksauthors"
				+ " WHERE isbn = ?";
		
		int rows = 0;
		
		try(Connection c = Conexao.getConexao()){
			
			
			PreparedStatement pstm = c.prepareStatement(query);
			pstm.setString(1, isbn);
			rows = pstm.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return rows;
	}
	
public static int excluiAutorLivro(int id) {
		
		String query = "DELETE FROM booksauthors"
				+ " WHERE author_id = ?";
		
		int rows = 0;
		
		try(Connection c = Conexao.getConexao()){
			
			
			PreparedStatement pstm = c.prepareStatement(query);
			pstm.setInt(1, id);
			rows = pstm.executeUpdate();
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	

	public static List<String> getIsbnAutor(int autorId) {
		String query = "SELECT isbn FROM booksauthors"
				+ " WHERE  author_id = ?";
		
		List<String> isbns = new ArrayList<String>();
		
		
		try(Connection c = Conexao.getConexao()){
			
			
			PreparedStatement pstm = c.prepareStatement(query);
			pstm.setInt(1, autorId);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				String isbn = rs.getString("isbn").trim();
				System.out.println("ISBN CAPTURADO");
				isbns.add(isbn);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();

		}
		
		
		return isbns;
		
	}
	
	public static int excluiAutor(int autorId) {
		
		String query = "DELETE FROM authors"
				+ " WHERE author_id = ?";
		List<String> isbns = getIsbnAutor(autorId);
		
		for(String isbn : isbns){
			excluiLivroAutor(isbn);
		}
		
		
		int rows = 0;
		
		try(Connection c = Conexao.getConexao()){
			
			
			PreparedStatement pstm = c.prepareStatement(query);
			pstm.setInt(1, autorId);
			rows = pstm.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		
		
		return rows;
	}
	
	public static List<String> getIsbnEditora(int editoraId) {
		String query = "SELECT isbn FROM books"
				+ " WHERE  publisher_id = ?";
		
		List<String> isbns = new ArrayList<String>();
		
		
		try(Connection c = Conexao.getConexao()){
			
			
			PreparedStatement pstm = c.prepareStatement(query);
			pstm.setInt(1, editoraId);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				String isbn = rs.getString("isbn").trim();
				System.out.println("ISBN CAPTURADO");
				isbns.add(isbn);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();

		}
		
		
		return isbns;
		
	}
	
	
	public static int excluiEditora(int editoraId) {
		
		List<String> isbns = getIsbnEditora(editoraId);
		
		for(String isbn : isbns) {
			System.out.println("ENTREI AQUI");
			excluiLivroAutor(isbn);
			excluiLivro(isbn);
		}
		
		String query = "DELETE FROM publishers"
				+ " WHERE publisher_id = ?";
		
		
		int rows = 0;
		
		try(Connection c = Conexao.getConexao()){
			
			
			PreparedStatement pstm = c.prepareStatement(query);
			pstm.setInt(1, editoraId);
			rows = pstm.executeUpdate();
			

			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return rows;
	}
	
	public static int alteraLivro(String isbn, String title, String preco) {
		
		
		String query = "UPDATE books SET title = ?"
				+ ", price = ? WHERE isbn = ?";
		
		float aPreco = Float.parseFloat(preco);
		int rows = 0;
		
		try(Connection c = Conexao.getConexao()){
			
			
			PreparedStatement pstm = c.prepareStatement(query);
			pstm.setString(1, title);
			pstm.setFloat(2, aPreco);
			pstm.setString(3, isbn);
			rows = pstm.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return rows;
	}
	
	public static int alteraAutor(String nome, String sobrenome, int id) {
		
		
		String query = "UPDATE authors SET fname = ?"
				+ ", name = ? WHERE  author_id = ?";
		
		int rows = 0;
		
		try(Connection c = Conexao.getConexao()){
			
			
			PreparedStatement pstm = c.prepareStatement(query);
			pstm.setString(1, nome);
			pstm.setString(2, sobrenome);
			pstm.setInt(3, id);
			rows = pstm.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return rows;
	}
	
	public static int alteraEditora(String nome, String url, int id) {
		
		
		String query = "UPDATE publishers SET name = ?"
				+ ", url = ? WHERE publisher_id = ?";
		
		int rows = 0;
		
		try(Connection c = Conexao.getConexao()){
			
			
			PreparedStatement pstm = c.prepareStatement(query);
			pstm.setString(1, nome);
			pstm.setString(2, url);
			pstm.setInt(3, id);
			rows = pstm.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}

		return rows;
	}
	
	public static List<Livro> editoraBuscaLivros(int id){
		String query = "SELECT * FROM books"
				+ " WHERE publisher_id = ? ";
		
		List<Livro> livros = new ArrayList<>();
		List<String> nomes = new ArrayList<String>();
		
		try(
				Connection c = Conexao.getConexao();
				PreparedStatement pstm = c.prepareStatement(query);
				){
			
			pstm.setInt(1,id);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				String title = rs.getString("title").trim();
				String isbn = rs.getString("isbn");
				String autor = rs.getString("isbn");
				String preco = rs.getString("price");
				nomes.add(autor);
				livros.add(new Livro(title, isbn,nomes,preco));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return livros;
	}
	
	public static List<Autor> livroBuscaAutores(String isbn){
		String query = "SELECT * FROM books b INNER JOIN booksauthors ba"
				+ " ON b.isbn = ba.isbn INNER JOIN authors a"
				+ " ON ba.author_id = a.author_id"
				+ " WHERE ba.isbn = ? ";
		
		List<Autor> autores = new ArrayList<>();
		
		try(
				Connection c = Conexao.getConexao();
				PreparedStatement pstm = c.prepareStatement(query);
				){
			
			
			pstm.setString(1,isbn);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				String pNome = rs.getString("fname").trim();
				String name = rs.getString("name");
				int id = rs.getInt("author_id");
				
				Autor autor = new Autor(pNome,name,id);
				
				autores.add(autor);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return autores;
	}
	
	public static int comboBoxAutorId(String nome){
		String query = "SELECT author_id FROM authors"
				+ " WHERE name LIKE (?)";
		
		
		int id;
		int ids = 0;
		try(
				Connection c = Conexao.getConexao();
				PreparedStatement pstm = c.prepareStatement(query);
				){
			
			pstm.setString(1, "%"+nome+"%");
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				id = rs.getInt("author_id");
				ids = id;
			}
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return ids;
	}
			
			public static int excluirAutorIdIsbn(int id, String isbn){
				String query = "DELETE FROM booksauthors"
						+ " WHERE author_id = ? AND isbn = ?";
				
				int rows = 0;
				
				try(
						Connection c = Conexao.getConexao();
						PreparedStatement pstm = c.prepareStatement(query);
						){
					
					pstm.setInt(1, id);
					pstm.setString(2, isbn);
					rows = pstm.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
				return rows;
	}
			
			
			public static int alteraEditora(String isbn, int id){
				String query = "UPDATE books SET publisher_id = ?"
						+ " WHERE isbn = ?";
				
				int rows = 0;
				
				try(
						Connection c = Conexao.getConexao();
						PreparedStatement pstm = c.prepareStatement(query);
						){
					
					pstm.setInt(1, id);
					pstm.setString(2, isbn);
					rows = pstm.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
				return rows;
	}	
			
			public static String editoraPeloIsbn(String isbn){
				String query = "SELECT p.name FROM publishers p INNER JOIN"
						+ " books b ON p.publisher_id = b.publisher_id WHERE"
						+ " isbn = ?";
				
				String nome = null;
				
				try(
						Connection c = Conexao.getConexao();
						PreparedStatement pstm = c.prepareStatement(query);
						
						){
					pstm.setString(1, isbn);
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {
						nome = rs.getString("name");
					}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
				return nome;
	}	
			
	
	public static List<Livro> autorBuscaLivros(int id){
		String query = "SELECT * FROM books b INNER JOIN booksauthors ba"
				+ " ON b.isbn = ba.isbn INNER JOIN authors a ON a.author_id = ba.author_id"
				+ " WHERE ba.author_id = ?";
		
		List<Livro> livros = new ArrayList<>();
		List<String> nomes = new ArrayList<String>();
		
		try(
				Connection c = Conexao.getConexao();
				PreparedStatement pstm = c.prepareStatement(query);
				){
			
			pstm.setInt(1,id);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				String title = rs.getString("title").trim();
				String isbn = rs.getString("isbn");
				String autor = rs.getString("isbn");
				String preco = rs.getString("price");
				nomes.add(autor);
				livros.add(new Livro(title, isbn,nomes,preco));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return livros;
	}
	
	
	
	
	public static int strToInt(String valor, int padrao) 
	{
	   try {
	       return Integer.valueOf(valor); // Para retornar um Integer, use Integer.parseInt
	   } 
	   catch (NumberFormatException e) {  // Se houver erro na convers�o, retorna o valor padr�o
	       return padrao;
	   }
	}
	
}
