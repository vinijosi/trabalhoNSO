package br.unb.nso.TrabalhoNSO;


public class Memoria {

	private static int MEMCPU = 64; //TAMANHO MAXIMO DA MEMORIA DE CPU
	private static int MEMUSER	 = 1024 - MEMCPU; //TAMANHO MAXIMO DA MEMORIA DE USUARIO

	private static  int[] memoriaCPU = new int[MEMCPU];
	private static  int[] memoriaUsuario = new int[MEMUSER];

	public Memoria(){
		for (int i = 0; i < MEMCPU; i++) {
			memoriaCPU[i] = 0;
		}
		for (int i = 0; i < MEMUSER; i++) {
			memoriaUsuario[i] = 0;
		}

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
	 * */

	public void alocaMemoria(Processo processo) {
		int blocosParaAlocar = processo.blocosMemoria;
		if (processo.pid == 0) {
			System.out.printf("Blocos: ");
			for (int i = 0; i < MEMCPU; i++){
				if ((blocosParaAlocar > 0) && (memoriaCPU[i]==0)){

					memoriaCPU[i] = processo.pid;
					blocosParaAlocar--;
					processo.offsetMemoria = (i - processo.blocosMemoria);

					/* 
					 * DELETAR ESTA SAIDA
					 **/

					System.out.printf("%s, ", i);

				}
				System.out.printf("da memoria da CPU alocados\n");
			}

		} else {
			System.out.printf("Blocos: ");
			for (int i = 0; i < MEMUSER; i++){
				if ((blocosParaAlocar > 0) && (memoriaUsuario[i]==0)){
					memoriaUsuario[i] = processo.pid;
					blocosParaAlocar--;
					processo.offsetMemoria = (i - processo.blocosMemoria);
					System.out.printf("%s, ", i);
				}
			}
			System.out.printf("da Memoria de Usuário alocados\n");

		}
	}

	public static  void liberaMemoria(Processo processo) {
		if (processo.prioridade == 0) {
			System.out.printf("Blocos: ");
			for (int i = 0; i < MEMCPU; i++){
				if (memoriaCPU[i]==processo.pid){
					System.out.printf("%s, ",i);
					memoriaCPU[i] = 0;
				}
			}
			System.out.printf("da Memoria de CPU liberados\n");
		} else {
			System.out.printf("Blocos: ");
			for (int i = 0; i < MEMUSER; i++){
				if (memoriaUsuario[i]==processo.pid){
					memoriaUsuario[i] = 0;
					System.out.printf("%s ",i);					
				}
			}
			System.out.printf("da Memoria de Usuário liberados\n");

		}
		// Criar metodo que chame o Despachante para incluir como pronto processos
		//bloqueados
	}
}