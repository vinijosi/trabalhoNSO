package br.unb.nso.TrabalhoNSO;

import java.util.concurrent.TimeUnit;

public class CPU {


	Clock cpuTime = new Clock();

	Processo processoPreemptado;

	public void processar(Processo o) throws InterruptedException{

		System.out.printf("\nPID: %s \n",o.pid);
		System.out.printf("blocks: %s \n", o.blocosMemoria);
		System.out.printf("Offset Memoria: %s \n", o.offsetMemoria);
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
			this.cpuTime.incrementa();
			//TimeUnit.MILLISECONDS.sleep(100);
		}

		System.out.printf("P%s return SIGINT\n",o.pid);
		
		Recursos.liberaRecursos(o);
		Memoria.liberaMemoria(o);
		TimeUnit.MILLISECONDS.sleep(10);

	}


}