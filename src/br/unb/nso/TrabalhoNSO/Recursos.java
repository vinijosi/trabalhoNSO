package br.unb.nso.TrabalhoNSO;

public class Recursos {
	
	private static int semaforoImpressora = 2;
	
	
	private static int impressora;
	private static int scanner;
	private static int disco;
	private static int modem;

	
	public Recursos(){
		
		setImpressora(0);
		setScanner(0);
		setDisco(0);
		setModem(0);
	}
	interface recursos {
		Recursos nsoRecursos = new Recursos();
	}
	public void alocaRecursos(Processo processo) {
		recursos.nsoRecursos.setImpressora(processo.pid);
	}
	public void liberaRecursos(Processo o) {
		if ((o.impressora == 1) && (Recursos.getImpressora()== o.pid)) recursos.nsoRecursos.setImpressora(0); 
		if ((o.scanner == 1) && (Recursos.getScanner()== o.scanner)) recursos.nsoRecursos.setScanner(0);
		if ((o.disco == 1) && (Recursos.getDisco()== o.pid)) recursos.nsoRecursos.setDisco(0);
		if ((o.modem == 1) && (Recursos.getModem()== o.pid)) recursos.nsoRecursos.setModem(0);

	}
	public static int getImpressora() {
		return impressora;
	}
	public void setImpressora(int pid) {
		Recursos.impressora = pid;
	}
	public static int getScanner() {
		return scanner;
	}
	public void setScanner(int pid) {
		Recursos.scanner = pid;
	}
	public static int getDisco() {
		return disco;
	}
	public void setDisco(int pid) {
		Recursos.disco = pid;
	}
	public static int getModem() {
		return modem;
	}
	public void setModem(int pid) {
		Recursos.modem = pid;
	}
	
	


}
