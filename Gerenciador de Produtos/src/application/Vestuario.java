package application;

public class Vestuario extends Produto {
	int quantEstoque;
	public Vestuario(double preco, String descricao, String tipo, String nome, int quantEstoque) {
		super(preco, descricao, tipo, nome);
		this.quantEstoque = quantEstoque;
	}
	public int getQuantEstoque() {
		return quantEstoque;
	}
	public void setQuantEstoque(int quantEstoque) {
		this.quantEstoque = quantEstoque;
	}
	@Override
	public String toString() {
		return nome;
	}
}