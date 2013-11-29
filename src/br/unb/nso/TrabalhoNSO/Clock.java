package br.unb.nso.TrabalhoNSO;

public class Clock {
	
	int relogio = 0; // Tempo total de Uso da CPU
	int velocidade = 1000; // Tempo em milisegundos de cada iteracao da CPU 
	public void incrementa(){
		this.relogio++;
	}
}

