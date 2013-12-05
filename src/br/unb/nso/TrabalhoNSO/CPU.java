package br.unb.nso.TrabalhoNSO;

import br.unb.nso.TrabalhoNSO.Memoria.memoria;
import br.unb.nso.TrabalhoNSO.Recursos.recursos;

public class CPU {


	Clock cpuTime = new Clock();

	Processo processoPreemptado;
	
	
	interface Cpu {
		CPU nsoCpu = new CPU();
	}

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
			Cpu.nsoCpu.cpuTime.incrementa();
			System.out.printf("Clock atual: %s\n",Cpu.nsoCpu.cpuTime.relogio );
			//TimeUnit.MILLISECONDS.sleep(100);
		}

		System.out.printf("P%s return SIGINT\n",o.pid);
		
		
		recursos.nsoRecursos.liberaRecursos(o);
		System.out.print("liberou?\n");
		memoria.nsoMemoria.liberaMemoria(o);

	}


}
