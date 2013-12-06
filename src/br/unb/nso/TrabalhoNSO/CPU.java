package br.unb.nso.TrabalhoNSO;

import br.unb.nso.TrabalhoNSO.Despachante.despachante;
import br.unb.nso.TrabalhoNSO.Escalonador.escalonador;
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
			
			/*
			 * Preempcao em caso de existir um processo na fila
			 * de prioridade de cpu pronto.
			 * Duas verificacoes
			 * 1 - Se o processo atual ja nao é de prioridade 0
			 * 2 - Se existe um processo na fila de prontos da CPU
			 * 
			 * Porém como sempre será pego os processos de cpu
			 * Antes dos de USUARIOS não ha como um processo de usuário entrar
			 * antes de um de CPU
			 * 
			 * */
			
			Processo aux = escalonador.nsoEscalonador.proximoProntodasFilas();
			if (/*(o.prioridade != 0)&&*/aux!=null && (aux.prioridade == 0)){
				processoPreemptado = o;
				System.out.printf("\n\t\t\t\t\t\t\t\tProcesso PID(%s) preemptado\n",o.pid);
				Cpu.nsoCpu.processarPreemptado(escalonador.nsoEscalonador.proximoProntodasFilas());
				Cpu.nsoCpu.processarPreemptado(processoPreemptado);
			}
			//TimeUnit.MILLISECONDS.sleep(100);
		}

		System.out.printf("P%s return SIGINT\n",o.pid);
		
		
		recursos.nsoRecursos.liberaRecursos(o);
		memoria.nsoMemoria.liberaMemoria(o);
		escalonador.nsoEscalonador.deBloqueadoParaPronto();
	}

	private void processarPreemptado(Processo o) throws InterruptedException {
		System.out.printf("\n\t\t\t\t\t\t\t\tPID: %s \n",o.pid);
		System.out.printf("\t\t\t\t\t\t\t\tblocks: %s \n", o.blocosMemoria);
		System.out.printf("\t\t\t\t\t\t\t\tOffset Memoria: %s \n", o.offsetMemoria);
		System.out.printf("\t\t\t\t\t\t\t\tpriority: %s \n",o.prioridade);
		System.out.printf("\t\t\t\t\t\t\t\ttime: %s \n",o.tempoExecucao);
		System.out.printf("\t\t\t\t\t\t\t\tprinters: %s  \n",o.impressora);
		System.out.printf("\t\t\t\t\t\t\t\tscanners: %s \n",o.scanner);
		System.out.printf("\t\t\t\t\t\t\t\tmodems: %s \n",o.modem);
		System.out.printf("\t\t\t\t\t\t\t\tdrives: %s \n",o.disco);
		System.out.println();
		System.out.println();
		System.out.println();


		System.out.printf("Process %s =>\n",o.pid);
		System.out.printf("P%s STARTED\n",o.pid);

		while (o.tempoNaCpu < o.tempoExecucao){			
			System.out.printf("\t\t\t\t\t\t\t\tP %s instruction %s \n",o.pid,o.tempoNaCpu+1);
			o.tempoNaCpu++;
			Cpu.nsoCpu.cpuTime.incrementa();
			System.out.printf("\t\t\t\t\t\t\t\tClock atual: %s\n",Cpu.nsoCpu.cpuTime.relogio );
			
			
			//TimeUnit.MILLISECONDS.sleep(100);
		}

		System.out.printf("\t\t\t\t\t\t\t\tP %s return SIGINT\n",o.pid);
		
		
		recursos.nsoRecursos.liberaRecursos(o);
		memoria.nsoMemoria.liberaMemoria(o);
		escalonador.nsoEscalonador.deBloqueadoParaPronto();
		
	}


}
