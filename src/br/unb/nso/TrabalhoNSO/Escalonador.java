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


	interface escalonador {
		Escalonador nsoEscalonador = new Escalonador();
	}

	public Escalonador(){	
		this.processoTempoReal = new ArrayList<Processo>();
		this.processoUsuario1 = new ArrayList<Processo>();
		this.processoUsuario2 = new ArrayList<Processo>();
		this.processoUsuario3 = new ArrayList<Processo>();
		this.processosProntos = new ArrayList<Processo>();
		this.processosBloqueados = new ArrayList<Processo>();
	}



	public Processo escalonar(){
		Processo retorno = proximoProntodasFilas();
		return retorno;

		//		return retorno;	
	}

	public void incluiComoPronto(Processo novoProcesso) {		
		this.processosProntos.add(novoProcesso);
		System.out.printf("\nProcesso %s Incluido como Pronto\n", novoProcesso.pid);	
	}

	public void incluiComoBloqueado(Processo novoProcesso){
		this.processosBloqueados.add(novoProcesso);
		System.out.printf("\nProcesso %s Incluido como Bloqueado\n", novoProcesso.pid);
	}

	public void processoProntoDistribui (Processo novoProcesso){		
		if (novoProcesso.prioridade == 0){			
			processoTempoReal.add(novoProcesso);	
			processosProntos.remove(0);
		}		
		if (novoProcesso.prioridade == 1){
			this.processoUsuario1.add(novoProcesso);
			this.processosProntos.remove(0);
		}
		if (novoProcesso.prioridade == 2){
			this.processoUsuario2.add(novoProcesso);
			this.processosProntos.remove(0);
		}
		if (novoProcesso.prioridade >= 3){
			this.processoUsuario3.add(novoProcesso);
			this.processosProntos.remove(0);
		}
	}



	/* Insere o processo na fila de bloqueados
	 * */


	public void bloquearProcesso(Processo novoProcesso) {
		this.processosBloqueados.add(novoProcesso);
		System.out.println("Processo Bloqueado\n");
	}

	int qtdeProntos(){
		return processosProntos.size();
	}

	public int qtdeBloqueados() {
		return processosBloqueados.size();
	}

	public Processo proximoPronto() {
		return processosProntos.get(0);
	}

	
	/*
	 * Verifica qual o proximo processo das quatro filas
	 * Esta pronto no tempo atual
	 * */
	public Processo verificaProximoProntodasFilas() {
		Processo retorno = null;
		if (processoTempoReal.size() > 0 && processoTempoReal.get(0).tempoInicializacao == CPU.Cpu.nsoCpu.cpuTime.relogio){
			retorno = escalonador.nsoEscalonador.processoTempoReal.get(0);
		} else	if (processoUsuario1.size() > 0 && processoUsuario1.get(0).tempoInicializacao == CPU.Cpu.nsoCpu.cpuTime.relogio){
			retorno = escalonador.nsoEscalonador.processoUsuario1.get(0);
		} else	if (processoUsuario2.size() > 0 && processoUsuario2.get(0).tempoInicializacao == CPU.Cpu.nsoCpu.cpuTime.relogio){
			retorno = escalonador.nsoEscalonador.processoUsuario2.get(0);
		} else	if (processoUsuario3.size() > 0 && processoUsuario3.get(0).tempoInicializacao == CPU.Cpu.nsoCpu.cpuTime.relogio){
			retorno = escalonador.nsoEscalonador.processoUsuario3.get(0);
		}
		return retorno;


	}

	/*
	 * Retorna e remove o proximo processo das quatro filas
	 * Esta pronto no tempo atual
	 * */
	public Processo proximoProntodasFilas() {
		Processo proxPronto = new Processo();
		if (processoTempoReal.get(0).tempoInicializacao == CPU.Cpu.nsoCpu.cpuTime.relogio){
			proxPronto = escalonador.nsoEscalonador.processoTempoReal.get(0);
			escalonador.nsoEscalonador.processoTempoReal.remove(0);
		} else	if (processoUsuario1.get(0).tempoInicializacao == CPU.Cpu.nsoCpu.cpuTime.relogio){
			proxPronto = escalonador.nsoEscalonador.processoUsuario1.get(0);
			escalonador.nsoEscalonador.processoUsuario1.remove(0);
		} else	if (processoUsuario2.get(0).tempoInicializacao == CPU.Cpu.nsoCpu.cpuTime.relogio){
			proxPronto = escalonador.nsoEscalonador.processoUsuario2.get(0);
			escalonador.nsoEscalonador.processoUsuario2.remove(0);
		} else	if (processoUsuario3.get(0).tempoInicializacao == CPU.Cpu.nsoCpu.cpuTime.relogio){
			proxPronto = escalonador.nsoEscalonador.processoUsuario3.get(0);
			escalonador.nsoEscalonador.processoUsuario3.remove(0);
		}
		return proxPronto;

	}



}

