package samu;

public class Mensagem {

	private String descr;
	private String autor;
	private String web;

	public Mensagem(String descrMsg, String autorMsg) {
		this.descr = descrMsg;
		this.autor = autorMsg;
	}
	
	public Mensagem(String descrMsg, String autorMsg, String web) {
		this.descr = descrMsg;
		this.autor = autorMsg;
		this.web = web;
	}
	
	@Override
	public String toString() {
		String resultado = this.descr + " --" + this.autor;
		if (this.web != null) {
			resultado += " (" + this.web + ")";
		}
		return resultado;
	}
	
}
