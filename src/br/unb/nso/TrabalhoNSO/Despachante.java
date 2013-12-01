package br.unb.nso.TrabalhoNSO;

import java.util.List;

public class Despachante {

	Escalonador escalonador = new Escalonador();
	Memoria memoria = new Memoria();
	CPU cpu = new CPU();
	Recursos recursos = new Recursos();


	public void entregaEscalonador(List<Processo> global) {
		for (int i=0;i < global.size();i++){
			Processo processo = global.get(i);
			if (temRecursos(processo)){
				alocaRecursos(processo);
				escalonador.incluiComoPronto(processo);

			} else {
				escalonador.bloquearProcesso(processo);
			}
		}
	}

	private void alocaRecursos(Processo processo) {
		memoria.alocaMemoria(processo);
		recursos.alocaRecursos(processo);
	}

	private boolean temRecursos(Processo processo) {

		if (memoria.memoriaLivre(processo) >= processo.blocosMemoria 
				/* && Incluir todos os teste de Recursos*/){
			return true;
		} else

			return false;
	}

	public boolean temProcessos() {
		if (escalonador.qtdeProntos() > 0){
			return true;
		} else {
			return false;			
		}

	}

	public void despachaProximo() throws InterruptedException {
		try{
			cpu.processar(escalonador.escalonar());			
		} catch (Exception e) {
			//cpu.cpuTime.incrementa();
		}


	}
}
