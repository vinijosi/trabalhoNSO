package br.unb.nso.TrabalhoNSO;


import java.util.ArrayList;
import java.util.List;

public class Escalonador {

	private List<Processo> processosProntos;
	private List<Processo> processosBloqueados;
	
	
	public Escalonador(){
		
		this.processosProntos = new ArrayList<Processo>();
		this.processosBloqueados = new ArrayList<Processo>();
		
	}
	public Processo escalonar(){
		Processo retorno = processosProntos.get(0);
		
		processosProntos.remove(0);
		return retorno;
		
	}
	int qtdeProntos(){
		return this.processosProntos.size();
	}
	public void incluiComoPronto(Processo novoProcesso) {
		
		this.processosProntos.add(novoProcesso);
		System.out.println("Incluido como Pronto\n");
	}
	
	/*
	 * Insere o processo na fila de bloqueados
	 * */
	public void bloquearProcesso(Processo novoProcesso) {
		this.processosBloqueados.add(novoProcesso);
		novoProcesso.envelhece();
		//se o processo estiver como pronto
		//processosProntos.remove(novoProcesso);
		System.out.println("Processo Bloqueado\n");
		
	}
}
