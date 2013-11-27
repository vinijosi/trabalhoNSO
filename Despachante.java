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
			if (temRecurso(processo)){
				memoria.alocaMemoria(processo);// Criar codigo do metodo
				recursos.alocaRecursos(processo);//Criar codigo do metodo

				escalonador.incluiComoPronto(processo);
			} else {
				escalonador.bloquearProcesso(processo);
			}
		}
	}

	private boolean temRecurso(Processo processo) {

		if (memoria.memoriaLivre(processo.prioridade, processo.blocosMemoria) >= processo.blocosMemoria 
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
