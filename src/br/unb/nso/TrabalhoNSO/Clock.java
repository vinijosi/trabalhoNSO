package br.unb.nso.TrabalhoNSO;

public class Clock {
	
	int relogio = 0; // Tempo total de Uso da CPU
	int velocidade = 1000; // Tempo em milisegundos de cada iteracao da CPU 
	public void incrementa(){
		this.relogio++;
	}
}


/*boy, a manha agora eh concentrar pra fazer uma verificacao toda vez que o clock incrementar... Varios metodos precisam ser chamados ao incrementar o clock.
 * Acho que o esquema de idade nos bloqueados tambem não eh legal, 
 * pois soh precisamos aumentar a prioridade depois de prontos. Nao faz sentido envelhecer um processo antes dele estar pronto.
 * Ao voltar pra casa continuo.
 * 
 * */
