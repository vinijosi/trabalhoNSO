package br.unb.nso.TrabalhoNSO;

import java.util.LinkedList;
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
		entregaEscalonador();
	}

	public void entregaEscalonador() throws InterruptedException {
		Processo auxi = new Processo();
		if (global.size() > 0){
			auxi = this.global.get(0);
		}
		while ((Cpu.nsoCpu.cpuTime.relogio == auxi.tempoInicializacao)&&(global.size()>0)){

			if (temRecursos(auxi)){
				alocaRecursos(auxi);
				escalonador.nsoEscalonador.incluiComoPronto(auxi);
				escalonador.nsoEscalonador.processoProntoDistribui(auxi);

			} else {
				escalonador.nsoEscalonador.incluiComoBloqueado(auxi);
			}


			if (global.size()>0){
				this.global.remove(0); 
				if (global.size() > 0){
					auxi = this.global.get(0);
				} /*else {
					auxi = null;
				}*/
				
			}
				this.global.remove();
				
			if (this.global.size()>0){
				auxi = this.global.get(0);
			}

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
			if(escalonador.nsoEscalonador.verificaProximoProntodasFilas() != false){
				Cpu.nsoCpu.processar(escalonador.nsoEscalonador.proximoProntodasFilas());
				
			}
			else{
				Cpu.nsoCpu.cpuTime.incrementa();
			}
		}

	}

	public void desbloqueiaProximo(){
			if (temRecursos(escalonador.nsoEscalonador.proximoBloqueado())){
				escalonador.nsoEscalonador.processoBloueadoDistribui(escalonador.nsoEscalonador.proximoBloqueado());
			}
	}


}