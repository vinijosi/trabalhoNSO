package br.unb.nso.TrabalhoNSO;


import java.util.ArrayList;
import java.util.List;

public class Escalonador {

	private List<Processo> processoTempoReal;
	private List<Processo> processoUsuario1;
	private List<Processo> processoUsuario2;
	private List<Processo> processoUsuario3;
	private List<Processo> processosProntos;
	private List<Processo> processosBloqueados;
		
	public Escalonador(){	
		this.processoTempoReal = new ArrayList<Processo>();
		this.processoUsuario1 = new ArrayList<Processo>();
		this.processoUsuario2 = new ArrayList<Processo>();
		this.processoUsuario3 = new ArrayList<Processo>();
		this.processosProntos = new ArrayList<Processo>();
		this.processosBloqueados = new ArrayList<Processo>();
	}
	
	public Processo escalonar(){
		Processo retorno = processosProntos.get(0);
		processosProntos.remove(0);
		return retorno;	
	}
	
	public void incluiComoPronto(Processo novoProcesso) {
		
		this.processosProntos.add(novoProcesso);
		System.out.printf("\nProcesso %s Incluido como Pronto\n", novoProcesso.pid);
	
	}
	
	public void incluiComoBloqueado(Processo novoProcesso){
		this.processosBloqueados.add(novoProcesso);
		System.out.printf("\nProcesso %s Incluido como Bloqueado\n", novoProcesso.pid);
	}
	
	
	 /* Insere o processo na fila de bloqueados
	 * */
	
//	public void bloquearProcesso(Processo novoProcesso) {
//		this.processosBloqueados.add(novoProcesso);
//		novoProcesso.envelhece();
//		//se o processo estiver como pronto
//		//processosProntos.remove(novoProcesso);
//		System.out.println("Processo Bloqueado\n");
//	}


	public void bloquearProcesso(Processo novoProcesso) {
		this.processosBloqueados.add(novoProcesso);
		System.out.println("Processo Bloqueado\n");
	}
	
	int qtdeProntos(){
		return this.processosProntos.size();
	}

	public int qtdeBloqueados() {
		return this.processosBloqueados.size();
	}
}

