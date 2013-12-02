package br.unb.nso.TrabalhoNSO;

import java.util.List;

import br.unb.nso.TrabalhoNSO.CPU.Cpu;

public class Despachante {

	Escalonador escalonador = new Escalonador();
	Memoria memoria = new Memoria();
	//CPU cpu = new CPU(); // Transferido para classe CPU
	Recursos recursos = new Recursos();


	public void entregaEscalonador(List<Processo> global) {
		for (int i=0;i < global.size();i++){
			Processo processo = global.get(i);
			if (temRecursos(processo)){
				alocaRecursos(processo);
				escalonador.incluiComoPronto(processo);

			} else {
				escalonador.incluiComoBloqueado(processo);
			}
		}
	}

	private void alocaRecursos(Processo processo) {
		memoria.alocaMemoria(processo);
		recursos.alocaRecursos(processo);
	}

	private boolean temRecursos(Processo processo) {

		if (memoria.memoriaLivre(processo)
				/* && Incluir todos os teste de Recursos*/){
			return true;
		} else

			return false;
	}

	public boolean temProcessos() {
		if (escalonador.qtdeProntos() > 0 || escalonador.qtdeBloqueados() > 0){
			return true;
		} else {
			return false;			
		}

	}

	public void despachaProximo() throws InterruptedException {
		try{
			Cpu.minhaCpu.processar(escalonador.escalonar());			
		} catch (Exception e) {
			Cpu.minhaCpu.cpuTime.incrementa();
		}


	}
}