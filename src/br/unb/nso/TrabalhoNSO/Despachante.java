package br.unb.nso.TrabalhoNSO;

import java.util.LinkedList;
import java.util.List;
import br.unb.nso.TrabalhoNSO.CPU.Cpu;
import br.unb.nso.TrabalhoNSO.Escalonador.escalonador;
import br.unb.nso.TrabalhoNSO.Memoria.memoria;
import br.unb.nso.TrabalhoNSO.Recursos.recursos;

public class Despachante {

	LinkedList<Processo> global = new LinkedList<Processo>();

	interface despachante {
		Despachante nsoDespachante = new Despachante();
	}
	
	


	
	public void copiaGlobal (LinkedList<Processo> global2) throws InterruptedException {
		this.global = global2;
		entregaEscalonador(this.global);

	}

	public void entregaEscalonador(LinkedList<Processo> Global) throws InterruptedException {
		Processo auxi = new Processo();
		auxi = global.get(0);
		while (Cpu.nsoCpu.cpuTime.relogio == auxi.tempoInicializacao){

			if (temRecursos(auxi)){
				alocaRecursos(auxi);
				escalonador.nsoEscalonador.incluiComoPronto(auxi);

			} else {
				escalonador.nsoEscalonador.incluiComoBloqueado(auxi);
			}
			global.remove(0);
			auxi = global.get(0);
		}
		
	}
	
	
	

	private void alocaRecursos(Processo processo) {
		memoria.nsoMemoria.alocaMemoria(processo);
		recursos.nsoRecursos.alocaRecursos(processo);
	}

	private boolean temRecursos(Processo processo) {

		boolean bool = true;
		if (!memoria.nsoMemoria.memoriaLivre(processo)) bool = false;
		if ((processo.impressora == 1) && recursos.nsoRecursos.getImpressora() != 0) bool = false;
		if ((processo.scanner == 1) && recursos.nsoRecursos.getScanner() != 0) bool = false;
		if ((processo.disco == 1) && recursos.nsoRecursos.getDisco() != 0) bool = false;
		if ((processo.modem == 1) && recursos.nsoRecursos.getModem() != 0) bool = false;
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
		while(this.global.size() >= 0){
			escalonador.nsoEscalonador.mandaCpu();
			
		}
	}




}