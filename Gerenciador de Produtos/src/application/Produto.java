package application;

public abstract class Produto {
	protected double preco;
	protected String descricao, tipo, nome;
	public Produto(double preco, String descricao, String tipo, String nome) {
		this.preco = preco;
		this.descricao = descricao;
		this.tipo = tipo;
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return nome;
	}
}