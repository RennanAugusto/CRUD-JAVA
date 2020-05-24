package model;

public class Autor {
	
	public String pNome;
	public String nome;
	public int id;
	
	public Autor(String pNome, String nome, int id) {
		
		this.pNome = pNome;
		this.nome = nome;
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pNome == null) ? 0 : pNome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pNome == null) {
			if (other.pNome != null)
				return false;
		} else if (!pNome.equals(other.pNome))
			return false;
		return true;
	}
	
	

}
