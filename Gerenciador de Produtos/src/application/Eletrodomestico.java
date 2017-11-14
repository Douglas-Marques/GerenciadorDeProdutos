package application;

public class Eletrodomestico extends Produto {
	double ipi;
	public Eletrodomestico(double preco, String descricao, String tipo, String nome, double ipi) {
		super(preco, descricao, tipo, nome);
		this.ipi = ipi;
	}
	public double getIpi() {
		return ipi;
	}
	public void setIpi(double ipi) {
		this.ipi = ipi;
	}
	@Override
	public String toString() {
		return nome;
	}
}