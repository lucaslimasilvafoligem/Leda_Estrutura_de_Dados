package samu;

public class Samu {

	private Ajuda[] ajudas;
	
	public Samu() {
		this.ajudas = new Ajuda[1000];
	}
	
	public int cadastraAjuda(String descr, String autor) {
		// for em ajudas.. achar posicao vazia
		for (int i = 0; i < ajudas.length; i++) {
			Ajuda ajuda = ajudas[i];
			if (ajuda == null) {
				this.ajudas[i] = new Ajuda(descr, autor);
				return i;
			}
		}
		throw new IndexOutOfBoundsException();
	}

	public void apoieAjuda(int id) {
		this.ajudas[id].apoie();
	}

	public String exibeAjuda(int id) {
		return this.ajudas[id].toString();
	}

	public void cadastraMensagem(int id, String descr, String autor) {
		this.ajudas[id].cadastraMensagem(descr, autor);
	}

	public void cadastraMensagem(int id, String descr, String autor, String web) {
		this.ajudas[id].cadastraMensagem(descr, autor, web);
	}

	public String[] listaMensagens() {
		int totalAjudas = 0;
		for (int i = 0; i < ajudas.length; i++) {
			Ajuda ajuda = ajudas[i];
			if (ajuda == null) {
				totalAjudas = i;
			}
		}
		
		String[] ajudasStr = new String[totalAjudas];
		
		for (int i = 0; i < totalAjudas; i++) {
			Ajuda ajuda = ajudas[i];
			ajudasStr[i] = ajuda.toString();
		}
		
		return ajudasStr;
	}

}
