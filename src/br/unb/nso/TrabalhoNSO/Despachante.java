package br.unb.nso.TrabalhoNSO;

import java.util.List;

import br.unb.nso.TrabalhoNSO.CPU.Cpu;
import br.unb.nso.TrabalhoNSO.Escalonador.escalonador;
import br.unb.nso.TrabalhoNSO.Memoria.memoria;

public class Despachante {

	//Escalonador escalonador = new Escalonador();
	//Memoria memoria = new Memoria();
	//CPU cpu = new CPU(); // Transferido para classe CPU
	Recursos recursos = new Recursos();


	public void entregaEscalonador(List<Processo> global) {
		for (int i=0;i < global.size();i++){
			Processo processo = global.get(i);
			if (temRecursos(processo)){
				alocaRecursos(processo);
				escalonador.nsoEscalonador.incluiComoPronto(processo);

			} else {
				escalonador.nsoEscalonador.incluiComoBloqueado(processo);
			}
		}
	}

	private void alocaRecursos(Processo processo) {
		memoria.nsoMemoria.alocaMemoria(processo);
		recursos.alocaRecursos(processo);
	}

	private boolean temRecursos(Processo processo) {

		if (memoria.nsoMemoria.memoriaLivre(processo) 
				/* && Incluir todos os teste de Recursos*/){
			return true;
		} else

			return false;
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
			Cpu.nsoCpu.cpuTime.incrementa();
		}


	}
}