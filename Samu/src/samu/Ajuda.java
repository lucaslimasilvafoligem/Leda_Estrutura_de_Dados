package samu;

public class Ajuda {

	private String descr;
	
	private String autor;
	
	private int apoios;
	
	private Mensagem[] mensagens;
	
	public Ajuda(String descr, String autor) {
		this.descr = descr;
		this.autor = autor;
		this.apoios = 0;
		this.mensagens = new Mensagem[1000];
	}

	public void apoie() {
		this.apoios += 1;
	}
	
	public String toString() {
		return this.descr + " --" + this.autor + " (" + this.apoios + " apoio(s))";
	}

	private void cadastraMensagem(Mensagem msg) {
		for (int i = 0; i < mensagens.length; i++) {
			Mensagem mensagem = mensagens[i];
			if (mensagem == null) {
				mensagens[i] = msg;
				return;
			}
		}
	}
	
	public void cadastraMensagem(String descrMsg, String autorMsg) {
		cadastraMensagem(new Mensagem(descrMsg, autorMsg));
	}

	public void cadastraMensagem(String descrMsg, String autorMsg, String web) {
		cadastraMensagem(new Mensagem(descrMsg, autorMsg, web));
	}

}