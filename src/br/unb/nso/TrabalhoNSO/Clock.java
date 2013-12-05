package br.unb.nso.TrabalhoNSO;

import br.unb.nso.TrabalhoNSO.CPU.Cpu;
import br.unb.nso.TrabalhoNSO.Despachante.despachante;


/*
 * O Clock representa o tempo do sistema
 * */

public class Clock {

	int relogio = 0; // Tempo total de Uso da CPU
	public void incrementa() throws InterruptedException{
		Cpu.nsoCpu.cpuTime.relogio++;
		despachante.nsoDespachante.entregaEscalonador();
	}
}