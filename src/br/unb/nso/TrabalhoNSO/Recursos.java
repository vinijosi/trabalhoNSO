package br.unb.nso.TrabalhoNSO;

public class Recursos {

	//private static int semaforoImpressora = 2;


	private static int impressora1 = 0;
	private static int impressora2 = 0;
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
		if (processo.impressora == 1) {
			recursos.nsoRecursos.setImpressora(processo.pid);
			System.out.print("Impressora Alocada\n");
		}
		if (processo.scanner == 1) {
			recursos.nsoRecursos.setScanner(processo.pid);
			System.out.print("Scanner Alocado\n");
		}
		if (processo.disco == 1) {
			recursos.nsoRecursos.setDisco(processo.pid);
			System.out.print("Disco Alocado\n");
		}
		if (processo.modem == 1) {
			recursos.nsoRecursos.setModem(processo.pid);
			System.out.print("Modem Alocado\n");
		}
	}
	public void liberaRecursos(Processo o) {
		if ((o.impressora == 1) && (recursos.nsoRecursos.getImpressora() == o.pid)) {
			recursos.nsoRecursos.setImpressora(0); 
			System.out.print("Impressora Liberada\n");
		}
		if ((o.scanner == 1) && (recursos.nsoRecursos.getScanner()== o.scanner)) {
			recursos.nsoRecursos.setScanner(0);
			System.out.print("Scanner Liberado\n");
		}
		if ((o.disco == 1) && (recursos.nsoRecursos.getDisco()== o.pid)) {
			recursos.nsoRecursos.setDisco(0);
			System.out.print("Disco Liberado\n");
		}
		if ((o.modem == 1) && (recursos.nsoRecursos.getModem()== o.pid)) {
			recursos.nsoRecursos.setModem(0);
			System.out.print("Modem Liberado\n");
		}

	}
	public int getImpressora() {
		int retorno;
		if (impressora1==0){
			retorno = impressora1;
		} else if (impressora2 == 0) {
			retorno =  impressora2;
		} else {
			retorno = -1;
		} 
		return retorno;
	}
	public void setImpressora(int pid) {
		if (impressora1==0){
			impressora1 = pid;
		} else if (impressora2 == 0) {
			impressora2 = pid;
		} 
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
	//private void down(int recurso) {
	//}
}
