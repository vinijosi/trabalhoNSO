package br.unb.nso.TrabalhoNSO;

import java.util.List;

import br.unb.nso.TrabalhoNSO.CPU.Cpu;
import br.unb.nso.TrabalhoNSO.Escalonador.escalonador;
import br.unb.nso.TrabalhoNSO.Memoria.memoria;
import br.unb.nso.TrabalhoNSO.Recursos.recursos;

public class Despachante {

	//Escalonador escalonador = new Escalonador();
	//Memoria memoria = new Memoria();
	//CPU cpu = new CPU(); // Transferido para classe CPU
	//Recursos recursos = new Recursos();
	
	interface despachante {
		Despachante nsoDespachante = new Despachante();
	}

	
	public void entregaEscalonador(List<Processo> global, int tempo) {
		Processo auxi = new Processo();
		auxi = global.get(0);
		while (tempo == auxi.tempoInicializacao){
		  	
			if (temRecursos(auxi)){
				alocaRecursos(auxi);
				escalonador.nsoEscalonador.incluiComoPronto(auxi);

			} else {
				escalonador.nsoEscalonador.incluiComoBloqueado(auxi);
			}
			
			global.remove(0);
			auxi = global.get(0);
		  
		  }
		 
	/*	for (int i=0;i < global.size();i++){
			Processo processo = global.get(i);
			if (temRecursos(processo)){
				alocaRecursos(processo);
				escalonador.nsoEscalonador.incluiComoPronto(processo);

			} else {
				escalonador.nsoEscalonador.incluiComoBloqueado(processo);
			}
		}*/
	}

	private void alocaRecursos(Processo processo) {
		memoria.nsoMemoria.alocaMemoria(processo);
		recursos.nsoRecursos.alocaRecursos(processo);
	}

	private boolean temRecursos(Processo processo) {
		
		boolean bool = true;
		if (!memoria.nsoMemoria.memoriaLivre(processo)) bool = false;
		if ((processo.impressora == 1) && recursos.nsoRecursos.getImpressora() == 0) bool = false;
		if ((processo.scanner == 1) && recursos.nsoRecursos.getScanner() == 0) bool = false;
		if ((processo.disco == 1) && recursos.nsoRecursos.getDisco() == 0) bool = false;
		if ((processo.modem == 1) && recursos.nsoRecursos.getModem() == 0) bool = false;
			return bool;
	}

	public boolean temProcessos() {
		if (escalonador.nsoEscalonador.qtdeProntos() > 0 || escalonador.nsoEscalonador.qtdeBloqueados() > 0){
			return true;
		} else {
			return false;			
		}

	}

	public void despachaProximo() throws InterruptedException {
		try{
			Cpu.nsoCpu.processar(escalonador.nsoEscalonador.escalonar());
		} catch (Exception e) {
			//Como não existe processo pronto na fila incrementamos a cpu.
			Cpu.nsoCpu.cpuTime.incrementa();
		}
	}
	
	
}