package br.unb.nso.TrabalhoNSO;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import br.unb.nso.TrabalhoNSO.CPU.Cpu;
import br.unb.nso.TrabalhoNSO.Despachante.despachante;

public class Escalonador {

	
	private List<Processo> processoTempoReal;
	private List<Processo> processoUsuario1;
	private List<Processo> processoUsuario2;
	private List<Processo> processoUsuario3;
	private List<Processo> processosProntos;
	private LinkedList<Processo> processosBloqueados;


	interface escalonador {
		Escalonador nsoEscalonador = new Escalonador();
	}

	public Escalonador(){	
		this.processoTempoReal = new ArrayList<Processo>();
		this.processoUsuario1 = new ArrayList<Processo>();
		this.processoUsuario2 = new ArrayList<Processo>();
		this.processoUsuario3 = new ArrayList<Processo>();
		this.processosProntos = new ArrayList<Processo>();
		this.processosBloqueados = new LinkedList<Processo>();
	}

	public void mandaCpu () throws InterruptedException{		
		if (proximoProntodasFilas() != null){	
			while(proximoProntodasFilas() != null){			
				Cpu.nsoCpu.processar(proximoProntodasFilas());
			}
		}
		else{
			if(processosBloqueados.size() > 0){
				
				despachante.nsoDespachante.entregaEscalonador();
				
			}
			else{
			Cpu.nsoCpu.cpuTime.incrementa();
			}
		}
	}	
	
	


	public Processo escalonar(){
		Processo retorno = proximoProntodasFilas();
		return retorno;
	}

	public void incluiComoPronto(Processo novoProcesso) {		
		escalonador.nsoEscalonador.processosProntos.add(novoProcesso);
		System.out.printf("\nProcesso %s Incluido como Pronto\n", novoProcesso.pid);	
	}

	public void incluiComoBloqueado(Processo novoProcesso){
		escalonador.nsoEscalonador.processosBloqueados.add(novoProcesso);
		System.out.printf("\nProcesso %s Incluido como Bloqueado\n", novoProcesso.pid);
		Collections.sort(processosBloqueados);
	}

	public void processoProntoDistribui (Processo novoProcesso){
		
		System.out.printf("Distirbuido %s\n", novoProcesso.pid);
		if (novoProcesso.prioridade == 0){			
			escalonador.nsoEscalonador.processoTempoReal.add(novoProcesso);	
			//escalonador.nsoEscalonador.processosProntos.remove(0);
		}		
		if (novoProcesso.prioridade == 1){
			escalonador.nsoEscalonador.processoUsuario1.add(novoProcesso);
			//escalonador.nsoEscalonador.processosProntos.remove(0);
		}
		if (novoProcesso.prioridade == 2){
			escalonador.nsoEscalonador.processoUsuario2.add(novoProcesso);
			//escalonador.nsoEscalonador.processosProntos.remove(0);
		}
		if (novoProcesso.prioridade >= 3){
			escalonador.nsoEscalonador.processoUsuario3.add(novoProcesso);
			//escalonador.nsoEscalonador.processosProntos.remove(0);
		}
	}



	/* Insere o processo na fila de bloqueados
	 * */


	public void bloquearProcesso(Processo novoProcesso) {
		escalonador.nsoEscalonador.processosBloqueados.add(novoProcesso);
		System.out.println("Processo Bloqueado\n");
	}

	int qtdeProntos(){
		return escalonador.nsoEscalonador.processosProntos.size();
	}

	public int qtdeBloqueados() {
		return escalonador.nsoEscalonador.processosBloqueados.size();
	}

	public Processo proximoPronto() {
		Processo retorno = null;
		if (escalonador.nsoEscalonador.processosProntos.size() > 0){
			 retorno = escalonador.nsoEscalonador.processosProntos.get(0);
			 escalonador.nsoEscalonador.processosProntos.remove(0);
		}
		return retorno;

	}

	
	/*
	 * Verifica qual o proximo processo das quatro filas
	 * Esta pronto no tempo atual
	 * */
	public boolean verificaProximoProntodasFilas() {
		boolean retorno = false;
		if (processoTempoReal.size() > 0 && processoTempoReal.get(0).tempoInicializacao <= CPU.Cpu.nsoCpu.cpuTime.relogio){
			retorno = true;
		}
		if (processoUsuario1.size() > 0 && processoUsuario1.get(0).tempoInicializacao <= CPU.Cpu.nsoCpu.cpuTime.relogio){
			retorno = true;
		} 
		if (processoUsuario2.size() > 0 && processoUsuario2.get(0).tempoInicializacao <= CPU.Cpu.nsoCpu.cpuTime.relogio){
			retorno = true;
		}
		if (processoUsuario3.size() > 0 && processoUsuario3.get(0).tempoInicializacao <= CPU.Cpu.nsoCpu.cpuTime.relogio){
			retorno = true;
		}
		return retorno;


	}

	/*
	 * Retorna e remove o proximo processo das quatro filas
	 * Esta pronto no tempo atual
	 * */
	public Processo proximoProntodasFilas() {
		Processo proxPronto = new Processo();
		proxPronto = null;
		if (this.processoTempoReal.size() > 0 && this.processoTempoReal.get(0).tempoInicializacao <= CPU.Cpu.nsoCpu.cpuTime.relogio){
			proxPronto = escalonador.nsoEscalonador.processoTempoReal.get(0);
			escalonador.nsoEscalonador.processoTempoReal.remove(0);
		}
		if (this.processoUsuario1.size() > 0 && this.processoUsuario1.get(0).tempoInicializacao <= CPU.Cpu.nsoCpu.cpuTime.relogio){
			proxPronto = escalonador.nsoEscalonador.processoUsuario1.get(0);
			escalonador.nsoEscalonador.processoUsuario1.remove(0);
		} 
		if (this.processoUsuario2.size() > 0 && this.processoUsuario2.get(0).tempoInicializacao <= CPU.Cpu.nsoCpu.cpuTime.relogio){
			proxPronto = escalonador.nsoEscalonador.processoUsuario2.get(0);
			escalonador.nsoEscalonador.processoUsuario2.remove(0);
		}
		if (this.processoUsuario3.size() > 0 && this.processoUsuario3.get(0).tempoInicializacao <= CPU.Cpu.nsoCpu.cpuTime.relogio){
			proxPronto = escalonador.nsoEscalonador.processoUsuario3.get(0);
			escalonador.nsoEscalonador.processoUsuario3.remove(0);
		}
		return proxPronto;

	}

	public Processo proximoBloqueado() {
		Processo retorno = processosBloqueados.get(0);
		return retorno;
	}

	public void processoBloueadoDistribui(Processo proximoBloqueado) {
		
		System.out.printf("Distirbuido %s\n", proximoBloqueado.pid);
		if (proximoBloqueado.prioridade == 0){			
			escalonador.nsoEscalonador.processoTempoReal.add(proximoBloqueado);	
			//escalonador.nsoEscalonador.processosBloqueados.remove(0);
		}		
		if (proximoBloqueado.prioridade == 1){
			escalonador.nsoEscalonador.processoUsuario1.add(proximoBloqueado);
			//escalonador.nsoEscalonador.processosBloqueados.remove(0);
		}
		if (proximoBloqueado.prioridade == 2){
			escalonador.nsoEscalonador.processoUsuario2.add(proximoBloqueado);
			//escalonador.nsoEscalonador.processosBloqueados.remove(0);
		}
		if (proximoBloqueado.prioridade >= 3){
			escalonador.nsoEscalonador.processoUsuario3.add(proximoBloqueado);
			//escalonador.nsoEscalonador.processosBloqueados.remove(0);
		}
		
	}



}

