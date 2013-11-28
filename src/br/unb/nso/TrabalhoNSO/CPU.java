package br.unb.nso.TrabalhoNSO;

public class CPU {
	
	
	Clock cpuTime = new Clock();
//	int cont;
//	int semaforo;
//	int aux;
	Processo processoPreemptado;
	
	public void processar(Processo o) throws InterruptedException{

//		aux = 1;
		System.out.printf("PID: %s \n",o.pid);
//		System.out.println("offset: 0 ");
		System.out.printf("blocks: %s \n", o.blocosMemoria);
		System.out.printf("priority: %s \n",o.prioridade);
		System.out.printf("time: %s \n",o.tempoExecucao);
		System.out.printf("printers: %s  \n",o.impressora);
		System.out.printf("scanners: %s \n",o.scanner);
		System.out.printf("modems: %s \n",o.modem);
		System.out.printf("drives: %s \n",o.disco);
		System.out.println();
		System.out.println();
		System.out.println();
		 
		 
		System.out.printf("Process %s =>\n",o.pid);
		System.out.printf("P%s STARTED\n",o.pid);
		
		while (o.tempoNaCpu < o.tempoExecucao){			
			System.out.printf("P%s instruction %s \n",o.pid,o.tempoNaCpu+1);
			o.tempoNaCpu++;
			
			//TimeUnit.MILLISECONDS.sleep(100);
			this.cpuTime.incrementa();
			
		}
		
		System.out.printf("P%s return SIGINT\n\n",o.pid);		
		
	}
	
}
