package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class Estoque {
	Produto[] p;
	File f;
	int qAlimentos = 0, qVestuarios = 0, qEletrodomesticos = 0;
	public Estoque(File f) throws IOException {
		this.f = f;
		@SuppressWarnings("resource")
		LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(f));
		linhaLeitura.skip(f.length());
		this.p = new Produto[linhaLeitura.getLineNumber()];
		
	}
	
	public void preencheEstoque() throws IOException{
		try {
			FileReader fr = new FileReader(f);
			BufferedReader in = new BufferedReader(fr);
			String line = in.readLine();
			int controle = 0;
			do {
				line = in.readLine();
				if(line != null) {
					if(line.equalsIgnoreCase(""))
						break;
					String variaveis[] = line.split("\\*");
					String produto = variaveis[0].trim();
					String descricao = variaveis[1].trim();
					String preco = variaveis[2].trim();
					String tipo = variaveis[3].trim();
					String extra = variaveis[4].trim();
					if(tipo.equalsIgnoreCase("Alimento")) {
						this.p[controle] = new Alimento(Double.parseDouble(preco), descricao, tipo, produto, extra);
						controle++;
						this.qAlimentos++;
					}
					else if(tipo.equalsIgnoreCase("Vestuario")) {
						this.p[controle] = new Vestuario(Double.parseDouble(preco), descricao, tipo, produto, Integer.parseInt(extra));
						controle++;
						this.qVestuarios++;
					}
					else if(tipo.equalsIgnoreCase("Eletrodomestico")) {
						this.p[controle] = new Eletrodomestico(Double.parseDouble(preco), descricao, tipo, produto, Double.parseDouble(extra));
						controle++;
						this.qEletrodomesticos++;
					}
				}
			} while(line != null);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	public Produto[] ordenaPreco(Produto[] pr) {
		boolean troca = true;
        Produto aux;
        while(troca) {
            troca = false;
            for(int i = 0; i < p.length - 1; i++) {
                if(pr[i].getPreco() > pr[i + 1].getPreco()) {
                    aux = p[i];
                    pr[i] = pr[i + 1];
                    pr[i + 1] = aux;
                    troca = true;
                }
            }
        }
        return pr;
	}
	
	public static double buscaBinaria(Produto pr[ ], double preco) {
        return buscaBinariaDois(pr, preco, 0, pr.length-1);
    }
	
	private static double buscaBinariaDois(Produto pr[ ], double preco, int min, int max) {
		if(min > max)
			return 0.0;
		
		int mid = (min + max) / 2;
		
		if(pr[mid].getPreco() > preco)
			return buscaBinariaDois(pr, preco, mid+1, max);
		else if(pr[mid].getPreco() < preco)
			return buscaBinariaDois( pr, preco, min, mid-1);
		else
			return mid;
	}
	
	public Produto[] imprimeEstoque() {
		Produto[] conc = new Produto[this.p.length];
		for(int i = 0; i < this.p.length; i++)
			conc[i] = this.p[i];
		return conc;
	}
}