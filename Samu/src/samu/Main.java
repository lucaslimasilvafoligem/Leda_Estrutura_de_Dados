package samu;

public class Main {

	public static void main(String[] args) {
		// https://matheusgr.github.io/modeligado/edit.html
		Samu samu = new Samu();
		// scanner
		// while....
		//   cadastra ajuda...
		     String descr = ""; // sc.nextLine();
		     String autor = ""; // sc.nextLine();
			 samu.cadastraAjuda(descr, autor);
		//   apoia
			 int id = 0;
			 samu.apoieAjuda(id);
		//	 exibe
			 id = 0;
			 System.out.println(samu.exibeAjuda(id));
		//	 cadastraMsg
			 id = 0;
			 descr = "";
			 autor = "";
			 String web = "";
			 samu.cadastraMensagem(id, descr, autor);
			 samu.cadastraMensagem(id, descr, autor, web);
		// 	 exibirmsg
			 for (String s: samu.listaMensagens()) {
				 System.out.println(s);
			 }
	}
	
	
}
