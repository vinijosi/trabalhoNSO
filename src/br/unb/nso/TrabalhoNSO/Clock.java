package br.unb.nso.TrabalhoNSO;

import br.unb.nso.TrabalhoNSO.CPU.Cpu;

public class Clock {

	int relogio = 0; // Tempo total de Uso da CPU
	public void incrementa(){
		Cpu.minhaCpu.cpuTime.relogio++;
		//Verifaca a cada incrementa se existem bloqueados
	}


	/*boy, a manha agora eh concentrar pra fazer uma verificacao toda vez que o clock incrementar... Varios metodos precisam ser chamados ao incrementar o clock.
	 * Acho que o esquema de idade nos bloqueados tambem n�o eh legal, 
	 * pois soh precisamos aumentar a prioridade depois de prontos. Nao faz sentido envelhecer um processo antes dele estar pronto.
	 * Ao voltar pra casa continuo.
	 * 
	 * */
}