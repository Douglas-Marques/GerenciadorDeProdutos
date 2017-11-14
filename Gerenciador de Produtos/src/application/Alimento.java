package application;

public class Alimento extends Produto {
	String validade;
	public Alimento(double preco, String descricao, String tipo, String nome, String validade) {
		super(preco, descricao, tipo, nome);
		this.validade = validade;
	}
	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}
	@Override
	public String toString() {
		return nome;
	}	
}