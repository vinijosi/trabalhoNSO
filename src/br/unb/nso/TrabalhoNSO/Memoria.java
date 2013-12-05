package br.unb.nso.TrabalhoNSO;

import br.unb.nso.TrabalhoNSO.Escalonador.escalonador;

/*
 * Memoria
 * 
 * A alocacao de memoria é feita se houver 
 * quantidade suficiente de blocos contiguos 
 * de memoria para o processo em questao
 * 
 * */

public class Memoria {

	/*
	 * Dois vetores de interio representam nossas memorias
	 * MEMCPU representa a memoria de Tempo Real ou de CPU
	 * MEMUSER e a memoria para processos de usuário e sera o tamanho
	 * da memoria decrescido da MEMCPU
	 * 
	 * */

	private static int MEMCPU = 64; //TAMANHO MAXIMO DA MEMORIA DE CPU
	private static int MEMUSER	 = 1024 - MEMCPU; //TAMANHO MAXIMO DA MEMORIA DE USUARIO

	private static  int[] memoriaCPU = new int[MEMCPU];
	private static  int[] memoriaUsuario = new int[MEMUSER];

	/*
	 * Semaforo para proteger a alocacao de memoria
	 * */
	private int semaforo = 1;

	/*
	 * Nossa proposta para memoria
	 * 
	 * Um vetor de inteiros onde cada posicsao representa um bloco de memoria 
	 * Se o bloco esta em zero ele esta livre
	 * Se um processo ocupa este bloco seu PID estara escrito nesta posicao
	 * 
	 * */

	public Memoria(){
		for (int i = 0; i < MEMCPU; i++) {
			memoriaCPU[i] = 0;
		}
		for (int i = 0; i < MEMUSER; i++) {
			memoriaUsuario[i] = 0;
		}

	}

	/*
	 * Com esta interface a memoria estara disponivel para todas as classes
	 * */
	interface memoria {
		Memoria nsoMemoria = new Memoria();
	}

	/*
	 * Retorna true se existirem blocos contiguos suficientes para 
	 * aquele processo.
	 *   
	 * */
	public boolean memoriaLivre(Processo o) {
		if (o.prioridade == 0){
			return existemBlocosContiguosLivres(memoriaCPU, o);
		} else {
			return existemBlocosContiguosLivres(memoriaUsuario, o);
		}

	}
	/*
	 * Verifica se ha memoria livre na quantidade desejada 
	 * */
	private boolean existemBlocosContiguosLivres(int[] memoriaTipo, Processo o) {
		int blocosLivres = 0;
		for (int i = 0; i < memoriaTipo.length; i++){
			if (memoriaTipo[i] == 0 && blocosLivres == o.blocosMemoria){
				return true;
			}
			if (memoriaTipo[i] == 0 && blocosLivres < o.blocosMemoria){
				blocosLivres++;
			}

		}
		return false;
	}


	/*
	 * Aloca na memoria a quantidade de blocos necessarios
	 * para o processo 
	 * Antes a memoria tenta fazer um down no semaforo
	 * Em caso de erro a alocacao nao é feita
	 * Porem como a verificacao de espaco é feita ante da tentativa de alocacao
	 * este teste passa a ter somente fins didaticos.
	 * */

	public void alocaMemoria(Processo processo) {
		if (memoria.nsoMemoria.down() == true){
			int blocosParaAlocar = processo.blocosMemoria;
			if (processo.pid == 0) {
				System.out.printf("\nBlocos: ");
				for (int i = 0; i < MEMCPU; i++){
					if ((blocosParaAlocar > 0) && (memoriaCPU[i]==0)){

						memoriaCPU[i] = processo.pid;
						blocosParaAlocar--;
						processo.offsetMemoria = (i - processo.blocosMemoria);
					}
					System.out.printf("da memoria da CPU alocados\n");
				}

			} else {
				System.out.printf("\nBlocos: ");
				for (int i = 0; i < MEMUSER; i++){
					if ((blocosParaAlocar > 0) && (memoriaUsuario[i]==0)){
						memoriaUsuario[i] = processo.pid;
						blocosParaAlocar--;
						processo.offsetMemoria = (i - processo.blocosMemoria + 1);
						System.out.printf("%s, ", i);
					}
				}
				System.out.printf("da Memoria de Usuário alocados\n");

			}			
			memoria.nsoMemoria.up();
		} else {
			escalonador.nsoEscalonador.bloquearProcesso(processo);
		}

	}

	public  void liberaMemoria(Processo processo) {
		if (memoria.nsoMemoria.down() == true){
			if (processo.prioridade == 0) {
				System.out.printf("\nBlocos: ");
				for (int i = 0; i < MEMCPU; i++){
					if (memoriaCPU[i]==processo.pid){
						System.out.printf("%s, ",i);
						memoriaCPU[i] = 0;
					}
				}
				System.out.printf("da Memoria de CPU liberados\n");
			} else {
				System.out.printf("\nBlocos: ");
				for (int i = 0; i < MEMUSER; i++){
					if (memoriaUsuario[i]==processo.pid){
						memoriaUsuario[i] = 0;
						System.out.printf("%s ",i);					
					}
				}
				System.out.printf("da Memoria de Usuário liberados\n");

			}

		}
		memoria.nsoMemoria.up();

		// Criar metodo que chame o Despachante para incluir como pronto processos
		//bloqueados
	}

	private void up() {
		memoria.nsoMemoria.semaforo++;

	}
	public boolean down() {
		if (memoria.nsoMemoria.semaforo == 0) {
			return false;
		} 
		memoria.nsoMemoria.semaforo--;
		return true;

	}
}