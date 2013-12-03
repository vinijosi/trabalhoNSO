package br.unb.nso.TrabalhoNSO;

public class Recursos {
	
	private static int semaforoImpressora = 2;
	
	
	private int impressora;
	private static int scanner;
	private static int disco;
	private static int modem;

	
	public Recursos(){
		impressora = 0;
		scanner = 0;
		disco = 0;
		modem = 0;
	}

	interface recursos {
		Recursos nsoRecursos = new Recursos();
	}
	public void alocaRecursos(Processo processo) {
		recursos.nsoRecursos.impressora = processo.pid;
	}
	public void liberaRecursos(Processo o) {
		// TODO Auto-generated method stub
	}
	
	


}
