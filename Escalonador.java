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
		return processosProntos.get(0);	
		
	}
	int qtdeProntos(){
		return this.processosProntos.size();
	}
	public void incluiComoPronto(Processo novoProcesso) {
		this.processosProntos.add(novoProcesso);		
	}
	public void bloquearProcesso(Processo novoProcesso) {
		this.processosBloqueados.add(novoProcesso);
		
	}
}
