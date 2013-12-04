package br.unb.nso.TrabalhoNSO;

public class Recursos {

	//private static int semaforoImpressora = 2;


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
		if (processo.impressora == 1) recursos.nsoRecursos.setImpressora(processo.pid);
		if (processo.scanner == 1) recursos.nsoRecursos.setScanner(processo.pid);
		if (processo.disco == 1) recursos.nsoRecursos.setDisco(processo.pid);
		if (processo.modem == 1) recursos.nsoRecursos.setModem(processo.pid);
	}
	public void liberaRecursos(Processo o) {
		if ((o.impressora == 1) && (recursos.nsoRecursos.getImpressora() == o.pid)) recursos.nsoRecursos.setImpressora(0); 
		if ((o.scanner == 1) && (recursos.nsoRecursos.getScanner()== o.scanner)) recursos.nsoRecursos.setScanner(0);
		if ((o.disco == 1) && (recursos.nsoRecursos.getDisco()== o.pid)) recursos.nsoRecursos.setDisco(0);
		if ((o.modem == 1) && (recursos.nsoRecursos.getModem()== o.pid)) recursos.nsoRecursos.setModem(0);

	}
	public int getImpressora() {
		return impressora;
	}
	public void setImpressora(int pid) {
		impressora = pid;
	}
	public int getScanner() {
		return scanner;
	}
	public void setScanner(int pid) {
		scanner = pid;
	}
	public int getDisco() {
		return disco;
	}
	public void setDisco(int pid) {
		disco = pid;
	}
	public int getModem() {
		return modem;
	}
	public void setModem(int pid) {
		modem = pid;
	}
}
